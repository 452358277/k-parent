package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.EnteMFinaInfoDTO;
import com.ppmg.ei.model.EnteMFinaInfoModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.EnteMFinaInfoService;
import com.ppmg.ei.vo.EnteMFinaInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(tags={"股权投"})
public class EnteMFinaInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EnteMFinaInfoService enteMFinaInfoService;

	@Reference
	private AppUserService appUserService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="股权投资季度财务", notes="股权投-资季度财务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
		@ApiImplicitParam(name = "projectObject", value = "被投企业", required = true, dataType = "String"),
		@ApiImplicitParam(name = "inveId", value = "出资主体", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/enteMFinaInfoList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String enteMFinaInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("enterpriseId") String enterpriseId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTERPRISE_ID", enterpriseId);

			PageInfo<EnteMFinaInfoModel> rows = enteMFinaInfoService.selectListPage(searchCondition);
			List<EnteMFinaInfoVO> list = new ArrayList<EnteMFinaInfoVO>();
			for(EnteMFinaInfoModel model : rows.getList()){
				EnteMFinaInfoVO vo = new EnteMFinaInfoVO();
				BeanUtils.copyProperties(vo, model);
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
		} catch (Exception e) {
			dataTablesResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiIgnore
	@ApiOperation(value = "管理报告新增股权投资季度财务", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundEnteMFinaInfo/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteMFinaInfoDTO dto) {
		try {
			String userId = this.getLoginUserId();
			EnteMFinaInfoModel model=new EnteMFinaInfoModel();
			org.springframework.beans.BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				if(dto.getIsCheck()){
					EnteMFinaInfoModel ent=	enteMFinaInfoService.selectById(dto.getPkId());
					if(!dto.getOccurMonth().equals(ent.getOccurMonth())){
						EnteMFinaInfoModel modelS=new EnteMFinaInfoModel();
						modelS.setEnterpriseId(dto.getEnterpriseId());
						modelS.setOccurMonth(dto.getOccurMonth());
						List<EnteMFinaInfoModel> list=enteMFinaInfoService.selectList(modelS);
						if(list!=null&&!list.isEmpty()){
							baseResponse.error("-1","该年月已存在");
							return JSONObject.toJSONString(baseResponse);
						}
					}
				}

				enteMFinaInfoService.update(model);
			}else{
				//判断是否存在
				if(dto.getIsCheck()){
					EnteMFinaInfoModel modelS=new EnteMFinaInfoModel();
					modelS.setEnterpriseId(dto.getEnterpriseId());
					modelS.setOccurMonth(dto.getOccurMonth());
					List<EnteMFinaInfoModel> list=enteMFinaInfoService.selectList(modelS);
					if(list!=null&&!list.isEmpty()){
						baseResponse.error("-1","该年月已存在");
						return JSONObject.toJSONString(baseResponse);
					}
				}
				model.setPkId(UUID.randomUUID().toString());
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				enteMFinaInfoService.insert(model);
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
	@GetMapping(value = "/fundEnteMFinaInfo/detail/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "pkId") String pkId){
		try {
			EnteMFinaInfoModel model = enteMFinaInfoService.selectById(pkId);
			EnteMFinaInfoVO vo = new EnteMFinaInfoVO();
			org.springframework.beans.BeanUtils.copyProperties(model, vo);
			if(StringUtils.isNotEmpty(model.getCurrencyType())){
				String currencyTypeName = codeUtils.getCodeNameByValue("103",model.getCurrencyType());
				vo.setCurrencyTypeName(currencyTypeName);
			}


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

	@ApiOperation(value="股权投资季度财务", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/fundEnteMFinaInfo/delete/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "pkId") String pkId){
		try {
			enteMFinaInfoService.deleteById(pkId);
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

