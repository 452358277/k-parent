package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.common.model.AppGroupModel;
import com.ppmg.common.service.AppGroupService;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.CommTCodeModel;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;
import com.ppmg.ei.service.CommTCodeService;
import com.ppmg.ei.service.FixflowRunTaskinstanceService;
import com.ppmg.ei.vo.ProjAppInfoOthVO;
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
import com.ppmg.ei.model.ProjAppInfoOthModel;
import com.ppmg.ei.service.ProjAppInfoOthService;
@Controller
@Scope("prototype")
@Api(tags={"OA投资决策接口"})
public class ProjAppInfoOthController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjAppInfoOthService projAppInfoOthService;

	@Reference
	private CommTCodeService commTCodeService;

	@Reference
	private AppGroupService appGroupService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="OA投资决策列表", notes="OA投资决策列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/projAppInfoOthList", produces = "application/json;charset=UTF-8")
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
			PageInfo<ProjAppInfoOthModel> rows = projAppInfoOthService.selectListPage(searchCondition);
			List<ProjAppInfoOthVO> list = new ArrayList<ProjAppInfoOthVO>();
			for(ProjAppInfoOthModel model : rows.getList()){
				ProjAppInfoOthVO vo = new ProjAppInfoOthVO();
				String status = "";
				if(model.getStatus()!=""&&model.getStatus()!=null){
					status = codeUtils.getCodeNameByValue("264", model.getStatus());//流程状态
				}
				String groupIdT = model.getGroupId();
				String deptIdT = model.getDeptId();
				AppGroupModel appGroupModelG = appGroupService.selectById(groupIdT);
				AppGroupModel appGroupModelD = appGroupService.selectById(deptIdT);
				String groupName = appGroupModelG.getName()+"-"+appGroupModelD.getName();

				BeanUtils.copyProperties(vo, model);
				vo.setStatus(status);
				vo.setGroupName(groupName);

				if(model.getProcessInstId() != null){
					List<FixflowRunTaskinstanceModel> flows = fixflowRunTaskinstanceService.getListByProcessinstanceId(model.getProcessInstId());
					if(flows.size()>0){
						if(flows.get(0).getFormuriview() == null || flows.get(0).getFormuriview() == ""){
							vo.setInstanceModel(flows.get(1));
						}else{
							vo.setInstanceModel(flows.get(0));
						}
					}

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

