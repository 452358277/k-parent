package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.vo.EntDirectorsVO;
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
import com.ppmg.ei.model.EntDirectorsModel;
import com.ppmg.ei.service.EntDirectorsService;
@Controller
@Scope("prototype")
@Api(tags={"APP-董监高接口"})
public class EntDirectorsController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private EntDirectorsService entDirectorsService;


	@ApiOperation(value="APP-董监高列表", notes="APP-董监高列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projectObject", value = "企业ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/entDirectorsInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String entDirectorsInfo(@PathVariable(value = "projectObject") String projectObject){
		try {

			String sql2 = "SELECT " +
					"(select wmsys.wm_concat(t1.person_id) from T_ENT_DIRECTORS t1 where t1.enterprise_id='"+projectObject+"' and t1.JOB like '%董事长%' and DELETE_FLAG='0' ) as DSZ," +
					"(select wmsys.wm_concat(t1.person_id) from T_ENT_DIRECTORS t1 where t1.enterprise_id='"+projectObject+"' and t1.JOB like '%总经理%' and DELETE_FLAG='0'  ) as ZJL," +
					"(select wmsys.wm_concat(t1.person_id) from T_ENT_DIRECTORS t1 where t1.enterprise_id='"+projectObject+"' and t1.JOB like '%董事%' and DELETE_FLAG='0'  ) as DS," +
					"(select wmsys.wm_concat(t1.person_id) from T_ENT_DIRECTORS t1 where t1.enterprise_id='"+projectObject+"' and t1.JOB like '%监事%' and DELETE_FLAG='0'  ) as JS " +
					"from dual ";
			List<Map<String,Object>> list = entDirectorsService.selectSql2Obj(sql2);
			String DSZ = "";
			String ZJL = "";
			String DS = "";
			String JS = "";
			if(list.size()>0){
				DSZ = String.valueOf(list.get(0).get("DSZ"));
				ZJL = String.valueOf(list.get(0).get("ZJL"));;
				DS = String.valueOf(list.get(0).get("DS"));;
				JS = String.valueOf(list.get(0).get("JS"));;
			}

			EntDirectorsVO vo = new EntDirectorsVO();
			vo.setDSZ(DSZ);
			vo.setZJL(ZJL);
			vo.setDS(DS);
			vo.setJS(JS);
			mapResponse.put("model", vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


}

