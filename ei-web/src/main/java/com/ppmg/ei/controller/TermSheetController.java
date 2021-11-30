package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.TermSheetModel;
import com.ppmg.ei.service.TermSheetService;
import com.ppmg.ei.vo.TermSheetListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@Scope("prototype")
@Api(tags={"投前文件相关接口"})
public class TermSheetController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private TermSheetService termSheetService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="获取投前文件列表", notes="获取投前文件列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/preInvestFilesList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String preInvestFilesList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId){
		
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID", projId);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<TermSheetModel> rows = termSheetService.selectListPage(searchCondition);
			List<TermSheetListVO> list = new ArrayList<TermSheetListVO>();
			for(TermSheetModel model : rows.getList()){
				TermSheetListVO vo = new TermSheetListVO();
				BeanUtils.copyProperties(vo, model);
				vo.setStatusName(codeUtils.getCodeNameByValue("225", model.getStatus()));
				vo.setSheetType(codeUtils.getCodeNameByValue("236", model.getSheetType()));
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

}

