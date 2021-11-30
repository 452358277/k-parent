package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.ProjValuationModel;
import com.ppmg.ei.service.ProjValuationService;
import com.ppmg.ei.vo.ProjRegularAssessVO;
import com.ppmg.ei.vo.ProjValuationVO;
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
import com.ppmg.common.controller.BaseController;
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
import com.ppmg.ei.model.ProjRegularAssessModel;
import com.ppmg.ei.service.ProjRegularAssessService;
@Controller
@Scope("prototype")
@Api(tags={"APP-定期估值接口"})
public class ProjRegularAssessController extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjRegularAssessService projRegularAssessService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private ProjValuationService projValuationService;

	@ApiOperation(value="APP-定期估值列表", notes="APP-定期估值列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/projRegularAssessList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projRegularAssessList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("projectObject") String projectObject,@RequestParam("inveId") String inveId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("OBJECT_ID", projectObject);
			searchCondition.addConditionEqualTo("AFTER_FUNDER_ID", inveId);
			searchCondition.addConditionNotEqualTo("STATUS", "9");
			PageInfo<ProjRegularAssessModel> rows = projRegularAssessService.selectListPage(searchCondition);
			List<ProjValuationVO> list = new ArrayList<ProjValuationVO>();
			for(ProjRegularAssessModel model : rows.getList()){
				ProjValuationVO vo = new ProjValuationVO();

				ProjValuationModel model2 = new ProjValuationModel();
				model2.setPraId(model.getPkId());
				ProjValuationModel projValuationModel = projValuationService.selectBy(model2);
				BeanUtils.copyProperties(vo, projValuationModel);
				if(vo.getValuType()!=null && vo.getValuType()!=""){
					vo.setValuType(codeUtils.getCodeNameByValue("17130", vo.getValuType()));
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


}

