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
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import com.ppmg.ei.model.ScParamModel;
import com.ppmg.ei.service.FixflowRunTaskinstanceService;
import com.ppmg.ei.service.ScParamService;
import com.ppmg.ei.vo.ScParamListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Controller
@Scope("prototype")
@Api(tags={"质量评估接口"})
public class ScParamController extends BaseAction{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ScParamService scParamService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@ApiOperation(value="获取质量评估列表", notes="获取质量评估列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/scParamList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String scParamList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId){
		
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("SYS_TYPE", "3");
			searchCondition.addConditionNotEqualTo("P.STATUS", "9");
			searchCondition.addParam("PROJ_ID", projId);
			PageInfo<ScParamModel> rows = scParamService.selectListPage(searchCondition);
			List<ScParamListVO> list = new ArrayList<ScParamListVO>();
			for(ScParamModel model : rows.getList()){
				ScParamListVO vo = new ScParamListVO();
				BeanUtils.copyProperties(vo, model);
				if(model.getProcessInstId() != null){
					List<FixflowRunTaskinstanceModel> flows = fixflowRunTaskinstanceService.getListByProcessinstanceId(model.getProcessInstId());
					if(flows.get(0).getFormuriview() == null || flows.get(0).getFormuriview() == ""){				
						vo.setInstanceModel(flows.get(1));
					}else{
						vo.setInstanceModel(flows.get(0));
					}
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

}

