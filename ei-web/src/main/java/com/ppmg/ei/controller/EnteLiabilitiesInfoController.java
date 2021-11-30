package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.EnteLiabilitiesInfoModel;
import com.ppmg.ei.service.EnteLiabilitiesInfoService;
import com.ppmg.ei.vo.EnteLiabilitiesInfoVO;
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
@Api(tags={"借款信息接口"})
@RequestMapping("/enteLiabilities")
public class EnteLiabilitiesInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EnteLiabilitiesInfoService enteLiabilitiesInfoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="借款信息接口", notes="借款信息")
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
	@GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("enterpriseId") String enterpriseId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTERPRISE_ID", enterpriseId);

			PageInfo<EnteLiabilitiesInfoModel> rows = enteLiabilitiesInfoService.selectListPage(searchCondition);
			List<EnteLiabilitiesInfoVO> list = new ArrayList<EnteLiabilitiesInfoVO>();
			for(EnteLiabilitiesInfoModel model : rows.getList()){
				EnteLiabilitiesInfoVO vo = new EnteLiabilitiesInfoVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getGuarantyStyle())){
					String guarantyStyleName = codeUtils.getCodeNameByValue("219",model.getGuarantyStyle());
					vo.setGuarantyStyleName(guarantyStyleName);
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
	@ApiOperation(value = "借款信息", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody EnteLiabilitiesInfoModel dto) {
		try {
			String userId = this.getLoginUserId();
			EnteLiabilitiesInfoModel model=new EnteLiabilitiesInfoModel();
			org.springframework.beans.BeanUtils.copyProperties(dto, model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				enteLiabilitiesInfoService.update(model);
			}else{
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setPkId(UUID.randomUUID().toString());
				enteLiabilitiesInfoService.insert(model);
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
	@GetMapping(value = "/detail/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "pkId") String pkId){
		try {
			EnteLiabilitiesInfoModel model = enteLiabilitiesInfoService.selectById(pkId);
			EnteLiabilitiesInfoVO vo = new EnteLiabilitiesInfoVO();
			org.springframework.beans.BeanUtils.copyProperties(model, vo);
			if(StringUtils.isNotEmpty(model.getGuarantyStyle())){
				String guarantyStyleName = codeUtils.getCodeNameByValue("219",model.getGuarantyStyle());
				vo.setGuarantyStyleName(guarantyStyleName);
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
	@GetMapping(value = "/delete/{pkId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable(value = "pkId") String pkId){
		try {
			enteLiabilitiesInfoService.deleteById(pkId);
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

