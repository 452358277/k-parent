package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.FundEnteDTO;
import com.ppmg.ei.model.EnteMFinaInfoModel;
import com.ppmg.ei.model.FundEnteModel;
import com.ppmg.ei.service.FundEnteService;
import com.ppmg.ei.vo.FundEnteVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Controller
@Scope("prototype")
@Api(tags={"年度财务报表接口"})
@RequestMapping("/fundYertEnte")
public class FundEnteController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundEnteService fundEnteService;

	@Resource(name = "codeUtils")
	private CodeUtils codeUtils;


	@ApiOperation(value="年度财务报", notes="审计报告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundEnteInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundEnteInfo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("enterpriseId") String enterpriseId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTE_ID",enterpriseId);
			PageInfo<FundEnteModel> rows = fundEnteService.selectListPage(searchCondition);
			List<FundEnteVO> list = new ArrayList<FundEnteVO>();
			for(FundEnteModel model : rows.getList()){
				FundEnteVO vo=new FundEnteVO();
				BeanUtils.copyProperties(model,vo);
				if(StringUtils.isNotEmpty(model.getStatus())){
					String statusName = codeUtils.getCodeNameByValue("422",model.getStatus());
					vo.setStatusName(statusName);
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
	@ApiOperation(value = "管理报告新增", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundEnteAudit/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundEnteDTO dto) {
		try {
			String userId = this.getLoginUserId();
			FundEnteModel model=new FundEnteModel();
			org.springframework.beans.BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				FundEnteModel ent=	fundEnteService.selectById(dto.getPkId());
				if(!dto.getYear().equals(ent.getYear())){
					FundEnteModel modelS=new FundEnteModel();
					modelS.setEnteId(dto.getEnteId());
					modelS.setYear(dto.getYear());
					List<EnteMFinaInfoModel> list=fundEnteService.selectList(modelS);
					if(list!=null&&!list.isEmpty()){
						baseResponse.error("-1","该年已存在");
						return JSONObject.toJSONString(baseResponse);
					}
				}
				fundEnteService.update(model);
			}else{
				//判断是否存在
				FundEnteModel modelS=new FundEnteModel();
				modelS.setEnteId(dto.getEnteId());
				modelS.setYear(dto.getYear());
				List<FundEnteModel> list=fundEnteService.selectList(modelS);
				if(list!=null&&!list.isEmpty()){
					baseResponse.error("-1","该年已存在");
					return JSONObject.toJSONString(baseResponse);
				}
				model.setPkId(UUID.randomUUID().toString());
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				fundEnteService.insert(model);
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="获取管理报告详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/enteAuditInfo/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String enteAuditInfo(@PathVariable(value = "pkId") String pkId){
		try {
			FundEnteModel model = fundEnteService.selectById(pkId);
			FundEnteVO vo = new FundEnteVO();
			BeanUtils.copyProperties(model, vo);
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

	@ApiOperation(value="管理报告删除", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/enteAudit/delete/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "pkId") String pkId){
		try {
			fundEnteService.deleteById(pkId);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}





}

