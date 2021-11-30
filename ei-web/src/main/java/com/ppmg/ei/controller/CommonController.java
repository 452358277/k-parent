package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.CommonModel;
import com.ppmg.ei.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@Scope("prototype")
@Api(tags={"公共类接口"})
public class CommonController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private CommonService commonService;

	@ApiOperation(value="更新表数据信息", notes="根据url的主键来指定更新表数据信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableName", value = "表名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "keyName", value = "主键名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "keyValue", value = "主键值", required = true, dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "字段名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "fieldValue", value = "字段值", required = true, dataType = "String")
	})
	@PutMapping(value = "/updateTableDataInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateTableDataInfo(@RequestParam("tableName") String tableName,@RequestParam("keyName") String keyName,
			@RequestParam("keyValue") String keyValue, @RequestParam("fieldName") String fieldName, @RequestParam("fieldValue") String fieldValue){
		
		try {
			CommonModel model = new CommonModel();
			model.setTableName(tableName);
			model.setKeyName(keyName);
			model.setKeyValue(keyValue);
			model.setFieldName(fieldName);
			model.setFieldValue("'"+fieldValue+"'");
			model.setUpdateId("1");
			commonService.updateTableDataInfo(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		
		return JSONObject.toJSONString(baseResponse);
		
	}

	@ApiOperation(value="删除表数据", notes="根据url的主键来指定删除表数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableName", value = "表名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "keyName", value = "主键名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "keyValue", value = "主键值", required = true, dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "字段名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "fieldValue", value = "字段值", required = true, dataType = "String")
	})
	@PutMapping(value = "/deleteTableDataById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteTableDataById(@RequestParam("tableName") String tableName, @RequestParam("keyName") String keyName,
			@RequestParam("keyValue") String keyValue, String fieldName, String fieldValue){
		
		try {
			CommonModel model = new CommonModel();
			model.setTableName(tableName);
			model.setKeyName(keyName);
			if(keyValue.contains(",")){
				keyValue = keyValue.replace(",", "','");
				keyValue = "'" + keyValue + "'";
			}
			model.setKeyValue(keyValue);
			if(fieldName != null){
				model.setFieldName(fieldName);
			}else{
				model.setFieldName("STATUS");
			}
			if(fieldValue != null){
				model.setFieldValue(fieldValue);
			}else{
				model.setFieldValue("9");
			}
			commonService.deleteTableDataById(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		
		return JSONObject.toJSONString(baseResponse);
		
	}

}

