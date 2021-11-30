package com.ppmg.ei.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.core.util.UimUtils;
import com.founder.ssm.web.annotations.Token;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.enumeration.UserTypeEnmu;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.CashFlowSearchBean;
import com.ppmg.ei.bean.QuarterReportSearchBean;
import com.ppmg.ei.dto.LedgerMagInfoDTO;
import com.ppmg.ei.dto.QuarterReportDTO;
import com.ppmg.ei.model.AppRoleModel;
import com.ppmg.ei.model.LedgerMagModel;
import com.ppmg.ei.service.AppRoleService;
import com.ppmg.ei.vo.LedgerMagVO;
import com.ppmg.ei.vo.QuarterReportModelVO;
import com.ppmg.ei.vo.QuarterReportVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.QuarterReportModel;
import com.ppmg.ei.service.QuarterReportService;
@Controller
@Scope("prototype")
@Api(tags={"季度托管报告接口"})
@RequestMapping("/quarterReport")
public class QuarterReportController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private QuarterReportService quarterReportService;

	@Reference
	private AppRoleService appRoleService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="查询季度托管报告列表", notes="查询季度托管报告列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/cashFlowList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String cashFlowList(@RequestBody QuarterReportSearchBean searchBean){
		try {
			if(StringUtils.isNotEmpty(searchBean.getQuarter())){
				List<String> quarter = Arrays.asList(searchBean.getQuarter().split(","));
				searchCondition.addConditionIn("QUARTER", quarter);
			}

			//查询用户的角色
            List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
            if(listRow!=null&&listRow.size()>0){
                for(AppRoleModel appRoleModel:listRow){
                    if(1005003==appRoleModel.getId()){
                        //银行只能查看自己创建的报告
                        searchCondition.addParam("CREATE_BY", this.getLoginUserId());
                        searchCondition.addParam("NAME", "托管行");
                    }else if(1005002==appRoleModel.getId()) {
                        //管理人只能看到对应基金的托管报告
                        searchCondition.addParam("CREATE_BY", this.getLoginUserId());
                        searchCondition.addParam("NAME", "");
                    }else{
                        searchCondition.addParam("NAME", "其他");
                    }
                }
            }

			searchCondition.setSearchBean(searchBean);
			PageInfo<QuarterReportModel> rows = quarterReportService.selectQuarterReportPage(searchCondition);
			List<QuarterReportModelVO> list = new ArrayList<QuarterReportModelVO>();
			for(QuarterReportModel model : rows.getList()){
				QuarterReportModelVO vo = new QuarterReportModelVO();
				BeanUtils.copyProperties(vo,model);
				vo.setCreateDt(model.getCreateDt());
				if(UimUtils.getUser(Long.valueOf(model.getCreateBy()))!=null){
					String name=UimUtils.getUser(Long.valueOf(model.getCreateBy())).getName();
					vo.setName(name);
				}
				if(StringUtils.isNotEmpty(model.getQuarter())){
					vo.setQuarter(codeUtils.getCodeNameByValue("2233", model.getQuarter()));
				}

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(model.getCreateDt()!=null){
					String dateString = formatter.format(model.getCreateDt());
					vo.setDateString(dateString);
				}
				list.add(vo);
			}
			dataTablesResponse.setData(list, rows);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value="新增季度托管报告", notes="根据QuarterReportDTO对象创建季度托管报告")
	@ApiImplicitParam(name = "QuarterReportDTO", value = "QuarterReportDTO", required = true, dataType = "QuarterReportDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody QuarterReportDTO dto){
		try {
			if(StringUtils.isEmpty(dto.getYear())){
				baseResponse.setMsg("请选择年份");
				return JSONObject.toJSONString(baseResponse);
			}
			//查询季度托管报告
			QuarterReportModel modelS = new QuarterReportModel();
			modelS.setYear(dto.getYear());
			modelS.setStatus("0");
			modelS.setFundId(dto.getFundId());
			if(StringUtils.isNotEmpty(dto.getQuarter())){
				modelS.setQuarter(dto.getQuarter());
				List<QuarterReportModel> list=quarterReportService.selectList(modelS);
				if(list!=null&&!list.isEmpty()){
					if(StringUtils.isNotEmpty(dto.getQuarter())){
						baseResponse.setMsg("该季度已存在");
						return JSONObject.toJSONString(baseResponse);
					}
				}
			}else{
				List<QuarterReportModel> list1=quarterReportService.selectList("getListQ",modelS);
				if(list1!=null&&!list1.isEmpty()){
						baseResponse.setMsg("该年度已存在");
						return JSONObject.toJSONString(baseResponse);
				}
			}



			QuarterReportModel model = new QuarterReportModel();
			BeanUtils.copyProperties(model,dto);
			String userId=this.getLoginUserId();
			//String userId="1005000";
			quarterReportService.insertQuarterReport(model,userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	@ApiIgnore
	@ApiOperation(value="更新季度托管报告", notes="根据url的id来季度托管报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "现金流记录主键ledgerId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "QuarterReportDTO", value = "QuarterReportDTO", required = true, dataType = "QuarterReportDTO")
	})
	@PostMapping(value = "/update/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@Token
	public String update(@PathVariable("id") String id, @RequestBody QuarterReportDTO dto){

		try {
			if(StringUtils.isEmpty(dto.getYear())){
				baseResponse.setMsg("请选择年份");
				return JSONObject.toJSONString(baseResponse);
			}
			QuarterReportModel model = new QuarterReportModel();
			BeanUtils.copyProperties(model, dto);
			model.setReportId(id);
			model.setUpdateDt(new Date());
			model.setUpdateBy(this.getLoginUserId());
			QuarterReportModel qu=quarterReportService.selectById(id);
			QuarterReportModel modelS = new QuarterReportModel();
			modelS.setYear(dto.getYear());
			modelS.setStatus("0");
			modelS.setFundId(dto.getFundId());
			//判断年度是否相同
			if(qu!=null&&qu.getYear().equals(dto.getYear())){
				//判断季度是否相同
				//判断如果原来不为空，改为空
				if((StringUtils.isNotEmpty(qu.getQuarter())&&StringUtils.isEmpty(dto.getQuarter()))||(StringUtils.isEmpty(qu.getQuarter())&&StringUtils.isEmpty(dto.getQuarter()))){
					List<QuarterReportModel> list=quarterReportService.selectList("getListQ",modelS);
					if(list!=null&&list.size()>1){
						baseResponse.setMsg("该年度已存在");
						return JSONObject.toJSONString(baseResponse);
					}

				}else if(StringUtils.isNotEmpty(qu.getQuarter())&&StringUtils.isNotEmpty(dto.getQuarter())&&!qu.getQuarter().equals(dto.getQuarter())){
					//原来不为空改为不是空
					modelS.setQuarter(dto.getQuarter());
					List<QuarterReportModel> list1=quarterReportService.selectList("getListQ",modelS);
					if(list1!=null&&!list1.isEmpty()){
						baseResponse.setMsg("该季度已存在");
						return JSONObject.toJSONString(baseResponse);
					}

				}

			}else{
				//年度不同
				//季度为空
				if(StringUtils.isEmpty(dto.getQuarter())){
					List<QuarterReportModel> list=quarterReportService.selectList("getListQ",modelS);
					if(list!=null&&list.size()>1){
						baseResponse.setMsg("该年度已存在");
						return JSONObject.toJSONString(baseResponse);
					}

				}else if(StringUtils.isNotEmpty(dto.getQuarter())){
					modelS.setQuarter(dto.getQuarter());
					List<QuarterReportModel> list1=quarterReportService.selectList("getListQ",modelS);
					if(list1!=null&&!list1.isEmpty()){
						baseResponse.setMsg("该季度已存在");
						return JSONObject.toJSONString(baseResponse);
					}
				}

			}
			quarterReportService.update("updateQuarter",model);



		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="查询季度托管报告", notes="根据url的id季度托管报告")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/detainfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "id") String id){
		try {
			QuarterReportModel model = quarterReportService.selectById(id);
			QuarterReportVO vo = new QuarterReportVO();
			BeanUtils.copyProperties(vo, model);
			vo.setCreateDt(model.getCreateDt());
			dataResponse.setData(vo);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "reportId", value = "reportId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/delete/{reportId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("reportId") String reportId){
		try {
			QuarterReportModel model = new QuarterReportModel();
			model.setReportId(reportId);
			model.setStatus("1");
			quarterReportService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


}

