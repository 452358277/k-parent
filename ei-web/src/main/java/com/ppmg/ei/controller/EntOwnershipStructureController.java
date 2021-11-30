package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.vo.CommTVotersListVO;
import com.ppmg.ei.vo.EntOwnershipStructureVO;
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
import com.ppmg.ei.model.EntOwnershipStructureModel;
import com.ppmg.ei.service.EntOwnershipStructureService;
@Controller
@Scope("prototype")
@Api(tags={"APP-股东信息接口"})
public class EntOwnershipStructureController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EntOwnershipStructureService entOwnershipStructureService;


	@ApiOperation(value="APP-股东列表", notes="APP-股东列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projectObject", value = "企业ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/entOwnershipStructureInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String entOwnershipStructureInfo(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("projectObject") String projectObject){

		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("ENTERPRISE_ID", projectObject);
			searchCondition.addConditionNotEqualTo("DELETE_FLAG", "1");
			PageInfo<EntOwnershipStructureModel> rows = entOwnershipStructureService.selectListPage(searchCondition);

			List<EntOwnershipStructureVO> list = new ArrayList<EntOwnershipStructureVO>();
			for(EntOwnershipStructureModel model : rows.getList()){
				EntOwnershipStructureVO vo = new EntOwnershipStructureVO();
				BeanUtils.copyProperties(vo, model);
				if(vo.getStockPercent()!=""&&vo.getStockPercent()!=null){
					vo.setStockPercent(vo.getStockPercent()+"%");
				}else{
					vo.setStockPercent("");
				}

				list.add(vo);
			}

			dataTablesResponse.setData(list);
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

