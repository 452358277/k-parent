package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.AssProjectModel;
import com.ppmg.ei.model.CommTCodeModel;
import com.ppmg.ei.model.GroupModel;
import com.ppmg.ei.service.AssProjectService;
import com.ppmg.ei.service.CommTCodeService;
import com.ppmg.ei.service.GroupService;
import com.ppmg.ei.vo.AssInfoListVO;
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
import com.ppmg.ei.model.AssInfoModel;
import com.ppmg.ei.service.AssInfoService;
@Controller
@Scope("prototype")
@Api(tags={"绩效考核接口"})
public class AssInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private AssInfoService assInfoService;

	@Reference
	private CommTCodeService commTCodeService;

	@Reference
	private AssProjectService assProjectService;

	@Reference
	private GroupService groupService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;


	@ApiOperation(value="绩效考核列表", notes="绩效考核列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/assInfoList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("pkId") String pkId, @RequestParam("groupId") String groupId){
		try {

			CommTCodeModel commTCodeModel = new CommTCodeModel();
			commTCodeModel.setCodeName(groupId);
			commTCodeModel.setParentId("266");
			commTCodeModel = commTCodeService.selectBy(commTCodeModel);
			String orgIdOrDeptId = commTCodeModel.getCodeValue();
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addParam("orgIdOrDeptId",orgIdOrDeptId);

			PageInfo<AssInfoModel> rows = assInfoService.selectListPage(searchCondition);
			List<AssInfoListVO> list = new ArrayList<AssInfoListVO>();
			for(AssInfoModel model : rows.getList()){
				String assObj = model.getAssObj();
				if("0".equals(assObj)){
					assObj = "公司";
				}else if("1".equals(assObj)){
					assObj = "部门";
				}else if("2".equals(assObj)){
					assObj = "个人";
				}
				model.setAssObj(assObj);
				String projId = model.getProjId();
				AssProjectModel assProjectModel = assProjectService.selectById(projId);
				String projName = assProjectModel.getProjName();

				AssInfoListVO vo = new AssInfoListVO();
				BeanUtils.copyProperties(vo, model);
				vo.setProjName(projName);

				String status = codeUtils.getCodeNameByValue("7504", model.getStatus());//状态
				vo.setStatus(status);

				String assObjId = model.getAssObjId();
				GroupModel groupModel = groupService.selectById(assObjId);
				String assObjName = groupModel.getName();
				vo.setAssObjName(assObjName);
				vo.setCreateName(this.getLoginUser().getName());
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

