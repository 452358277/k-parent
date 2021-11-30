package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.util.UimUtils;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.AttachService;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.xdk.AppGroup;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.controller.BasicPersonalInfoController;
import com.ppmg.common.enumeration.UserTypeEnmu;
import com.ppmg.common.model.BaseInfoExtendModel;
import com.ppmg.common.service.BaseInfoExtendService;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.bean.LabelSearchBean;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.dto.*;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.utils.QuarterDateUtils;
import com.ppmg.ei.vo.*;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags = {"项目信息相关接口"})
public class ProjInfoController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private ProjInfoService projInfoService;

    @Reference
    private CommTVotersService commTVotersService;

    @Reference(check = false)
    private FundQuitApplService fundQuitApplyService;

    @Reference
    private CommTVoteMagService commTVoteMagService;

    @Reference
    private ProjContractFileService projContractFileService;

    @Reference
    private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

    @Reference
    private AppUserService appUserService;

    @Reference
    private EntLabelService entLabelService;

    @Reference
    private LabelService labelService;

    @Reference(retries = -1)
    private EntBaseInfoService entBaseInfoService;

    @Reference
    private FundInfoService fundInfoService;

    @Reference
    private XjlTPaymentRequestService xjlTPaymentRequestService;

    @Reference
    private ProjAppInfoService projAppInfoService;

    @Reference
    private BdTFitRegulationSelfService bdTFitRegulationSelfService;

    @Reference
    private ProjQuitApplService projQuitApplService;

    @Reference
    private CommTGicsService commTGicsService;

    @Reference
    private CommTNewService commTNewService;


    @Reference
    private AppRoleService appRoleService;

    @Reference
    private AppUserroleService appUserroleService;


    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

	@Resource(name="basicPersonalInfoController")
	private BasicPersonalInfoController basicPersonalInfoController;

	@Reference(check = false)
	private AttachService attachService;

	@Reference
	private CommTCodeService CommTCodeService ;

	@Reference(check = false)
	private SerialnoService serialnoService;

    @Reference
    private ProjMemberService projMemberService;

    @Reference
    private ProjBackMoneySettleService projBackMoneySettleService;

    @Reference
    private ProjReceiptQuitService projReceiptQuitService;


    @Reference
    private CommTLabelItemService commTLabelItemService;

    @Reference
    private LedgerMagService ledgerMagService;


    @Reference
    private BaseInfoExtendService baseInfoExtendService;


    @Reference
    private DataQuarterService dataQuarterService;

/*	@Reference
	private ImportViewService importViewService;*/

	@ApiOperation(value="获取项目基本信息", notes="根据url的projId来获取项目基本信息")
	@ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/projInfoDetail/{projId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projInfoDetail(@PathVariable(value = "projId") String projId){
		
		try {
			ProjInfoModel model = projInfoService.getProjBaseInfoById(projId);
			ProjInfoVO proj = new ProjInfoVO();
			BeanUtils.copyProperties( model,proj);
			proj.setMangerName(model.getMangerModel().getMemberName());
			List<ProjMemberModel> memberList = model.getMemberList();
			String str = model.getMangerModel().getMemberName();
			String memberName = "";
			if(memberList.size()>0 && memberList.get(0).getMemberName() != null){
				str = str + ",";
				for(ProjMemberModel member: memberList){
					str += (member.getMemberName())+",";
					memberName = str.substring(0,str.length()-1);
				}
			}else{
				memberName = str;
			}
			proj.setMemberName(memberName);
			if(model.getSecrecyLevel() != null && model.getSecrecyLevel() != ""){
				proj.setSecrecyLevel(codeUtils.getCodeNameByValue("230", model.getSecrecyLevel()));
			}else{
				proj.setSecrecyLevel("");
				
			}
			if(model.getProjSrc() != null && model.getProjSrc() != ""){
				proj.setProjSrc(codeUtils.getCodeNameByValue("229", model.getProjSrc()));
			}else{
				proj.setProjSrc("");
			}
			if(model.getChargePartner() != null && model.getChargePartner() != ""){
//				proj.setChargePartner(appUserService.selectById(model.getChargePartner()).getName());
                String chargeId = model.getChargePartner();
                if (chargeId.contains(",")) {
                    chargeId = chargeId.replace(",", "','");
                    chargeId = "'" + chargeId + "'";
                }
                proj.setChargePartner(appUserService.getUserNameById(chargeId));
            } else {
                proj.setChargePartner("");
            }
            mapResponse.put("model", proj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value = "获取立项信息", notes = "根据url的projId来获取项目立项信息")
    @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projAppInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projAppInfo(@PathVariable(value = "projId") String projId) {

        try {
            ProjInfoModel model = projInfoService.getProjInfoById(projId);
            ProjAppInfoVO proj = new ProjAppInfoVO();

            if (model.getProjAppInfoModel() != null) {
                BeanUtils.copyProperties( model.getProjAppInfoModel(),proj);
                if (model.getProjAppInfoModel().getIntendedCurrency() != null) {
                    proj.setIntendedCurrency(codeUtils.getCodeNameByValue("103", model.getProjAppInfoModel().getIntendedCurrency()));
                }
                if (model.getProjAppInfoModel().getIntendedAmount()!=null ) {
                    Double double1 = model.getProjAppInfoModel().getIntendedAmount();
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                    String double2 = decimalFormat.format(double1);
                    if(StringUtils.isNotEmpty(double2)){
                        proj.setIntendedAmount(Double.valueOf(double2));
                    }

                }
            }


            mapResponse.put("model", proj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value = "获取立项表决结果信息列表", notes = "获取项目立项表决结果信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projAppVoteList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projAppVoteList(@RequestParam("projId") String projId) {

        try {
            List<CommTVotersModel> rows = commTVotersService.getProjAppVoteListByProjId(projId);
            List<CommTVotersListVO> list = new ArrayList<CommTVotersListVO>();
            String userId = this.getLoginUserId();
            String roleUserId = appUserService.getUserIdByRoleId("'10134','210426'");
            for (CommTVotersModel model : rows) {
                CommTVotersListVO vo = new CommTVotersListVO();
                BeanUtils.copyProperties(model,vo);
                vo.setVoteResult(codeUtils.getCodeNameByValue("246", model.getVoteResult()));
                if (userId != null) {
                    if (!(userId.equals(model.getVoter()) || (roleUserId != null && roleUserId.contains(userId)))) {
                        vo.setRemark("******");
                    }
                } else {
                    vo.setRemark("******");
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

    @ApiOperation(value = "获取立项决议结果信息", notes = "根据url的projId来获取项目立项信息")
    @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projAppVoteMagInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projAppVoteMagInfo(@PathVariable(value = "projId") String projId) {

        try {
            CommTVoteMagModel model = commTVoteMagService.getProjAppVoteMagInfoByProjId(projId);
            CommTVoteMagVO vo = new CommTVoteMagVO();
            BeanUtils.copyProperties( model,vo);
            vo.setDecisionResult(codeUtils.getCodeNameByValue("267", model.getDecisionResult()));
            vo.setVoteResult(codeUtils.getCodeNameByValue("267", model.getVoteResult()));

            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value = "获取立项审批信息列表", notes = "获取项目立项审批信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projAppFixflowTaskList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projAppFixflowTaskList(@RequestParam("projId") String projId) {

        try {
            List<FixflowRunTaskinstanceModel> rows = fixflowRunTaskinstanceService.getProjAppTaskListByProjId(projId);
            List<FixflowRunTaskinstanceListVO> list = new ArrayList<FixflowRunTaskinstanceListVO>();
            for (FixflowRunTaskinstanceModel model : rows) {
                FixflowRunTaskinstanceListVO vo = new FixflowRunTaskinstanceListVO();
                BeanUtils.copyProperties(model,vo);
                if (model.getUserModel() != null) {
                    vo.setAssigneeName(model.getUserModel().getName());
                } else {
                    vo.setAssigneeName("");
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

    @ApiOperation(value = "获取项目决策信息", notes = "根据url的projId来获取项目决策信息")
    @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projDecisionInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projDecisionInfo(@PathVariable(value = "projId") String projId) {

        try {
            ProjInfoModel model = projInfoService.getProjInfoById(projId);
            ProjDecisionInfoVO proj = new ProjDecisionInfoVO();
            BeanUtils.copyProperties( model,proj);
            if (model.getProjAppInfoModel() != null) {
                BeanUtils.copyProperties(model.getProjAppInfoModel(),proj);
                if (model.getProjAppInfoModel().getIntendedCurrency() != null) {
                    proj.setIntendedCurrency(codeUtils.getCodeNameByValue("103", model.getProjAppInfoModel().getIntendedCurrency()));
                }
                if (model.getProjAppInfoModel().getIntendedAmount() != null) {
                    Double double1 = model.getProjAppInfoModel().getIntendedAmount();
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                    String double2 = decimalFormat.format(double1);
                    proj.setIntendedAmount(double2);
                }
            }


            mapResponse.put("model", proj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "获取项目决策表决结果信息列表", notes = "获取项目立项表决结果信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projDecisionVoteList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projDecisionVoteList(@RequestParam("projId") String projId) {

        try {
            List<CommTVotersModel> rows = commTVotersService.getProjDecisionVoteListByProjId(projId);
            List<CommTVotersListVO> list = new ArrayList<CommTVotersListVO>();
            String userId = this.getLoginUserId();
            String roleUserId = appUserService.getUserIdByRoleId("'10134','210426'");
            System.out.println("userId:" + userId);
            System.out.println("roleUserId:" + roleUserId);
            for (CommTVotersModel model : rows) {
                CommTVotersListVO vo = new CommTVotersListVO();
                BeanUtils.copyProperties( model,vo);
                vo.setVoteResult(codeUtils.getCodeNameByValue("246", model.getVoteResult()));
                if (userId != null) {
                    if (!(userId.equals(model.getVoter()) || (roleUserId != null && roleUserId.contains(userId)))) {
                        vo.setRemark("******");
                    }
                } else {
                    vo.setRemark("******");
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

    @ApiOperation(value = "获取决策决议结果信息", notes = "根据url的projId来获取项目立项信息")
    @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projDecisionVoteMagInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projDecisionVoteMagInfo(@PathVariable(value = "projId") String projId) {

        try {
            CommTVoteMagModel model = commTVoteMagService.getProjDecisionVoteMagInfoByProjId(projId);
            CommTVoteMagVO vo = new CommTVoteMagVO();
            BeanUtils.copyProperties(model,vo);
            vo.setDecisionResult(codeUtils.getCodeNameByValue("267", model.getDecisionResult()));
            vo.setVoteResult(codeUtils.getCodeNameByValue("267", model.getVoteResult()));
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value = "获取决策审批信息列表", notes = "获取项目决策审批信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projDecisionFixflowTaskList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projDecisionFixflowTaskList(@RequestParam("projId") String projId) {

        try {
            List<FixflowRunTaskinstanceModel> rows = fixflowRunTaskinstanceService.getProjDecisionTaskListByProjId(projId);
            List<FixflowRunTaskinstanceListVO> list = new ArrayList<FixflowRunTaskinstanceListVO>();
            for (FixflowRunTaskinstanceModel model : rows) {
                FixflowRunTaskinstanceListVO vo = new FixflowRunTaskinstanceListVO();
                BeanUtils.copyProperties( model,vo);
                if (model.getUserModel() != null) {
                    vo.setAssigneeName(model.getUserModel().getName());
                } else {
                    vo.setAssigneeName("");
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

	/*@ApiOperation(value="获取子基金项目列表", notes="获取子基金项目列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getSubFundList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSubFundList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("userId") String userId){

		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_TYPE", "2");
			searchCondition.addConditionNotEqualTo("PROJ_STATUS", "7");
			//TODO 根据用户ID获取所在平台groupId
			String groupId = "1";
			searchCondition.addConditionEqualTo("GROUP_ID",groupId);
			PageInfo<ProjInfoModel> rows = projInfoService.selectSubFundListPage(searchCondition);
//			PageInfo<ProjInfoModel> rows = new PageInfo<ProjInfoModel>();
			List<SubFundProjListVO> list = new ArrayList<SubFundProjListVO>();
//			String userId = this.getLoginUserId();

//			for(ProjInfoModel model : rows.getList()){
				SubFundProjListVO vo = new SubFundProjListVO();
				vo.setProjId("15481");
				vo.setCurrentAmountDisplay("2000 万元 人民币");
				vo.setInveId("10755");
				vo.setInveName("国创元禾");
				vo.setProjObjectName("松禾一期");
				vo.setSubFundAmontDisplay("5000 万元 人民币");
//				BeanUtils.copyProperties(vo, model);
				list.add(vo);
				SubFundProjListVO vo1 = new SubFundProjListVO();
				vo1.setProjId("15565");
				vo1.setCurrentAmountDisplay("6000 万元 人民币");
				vo1.setInveId("10754");
				vo1.setInveName("国创创投");
				vo1.setProjObjectName("天丰基金");
				vo1.setSubFundAmontDisplay("8000 万元 人民币");
				list.add(vo1);
//			}
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
	}*/

    @ApiOperation(value = "获取子基金项目列表", notes = "获取子基金项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "搜索关键字", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getSubFundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getSubFundList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, String userId, String keyWord) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("PROJ_TYPE", "2");
            searchCondition.addConditionNotEqualTo("PROJ_STATUS", "7");
            String mangerUserId = appUserService.getUserIdByRoleId("110369");
            if (!"1200119390".equals(userId) && mangerUserId != null && !mangerUserId.contains(userId)) {
                String orgId = "";
                AppUserModel userModel = appUserService.selectById(userId);
                if (userModel != null && userModel.getOrgid() != null) {
                    //元禾战略、元禾直投特殊处理
                    if (userModel.getOrgid() == 1000) {
                        orgId = String.valueOf(userModel.getDeptid());
                    } else {
                        orgId = String.valueOf(userModel.getOrgid());
                    }
                }
                String groupId = codeUtils.getCodeNameByValue("266", orgId);
                searchCondition.addParam("GROUP_ID", groupId);
            }
            if (keyWord != null && keyWord != "") {
                keyWord = "'%" + keyWord + "%'";
            }
            searchCondition.addParam("keyWord", keyWord);
            PageInfo<ProjInfoModel> rows = projInfoService.selectSubFundListPage(searchCondition);
//			PageInfo<ProjInfoModel> rows = new PageInfo<ProjInfoModel>();
			/*rows.setPageSize(10);
			rows.setTotal(1);
			rows.setSize(1);*/
            List<SubFundProjListVO> list = new ArrayList<SubFundProjListVO>();
            for (ProjInfoModel model : rows.getList()) {
                SubFundProjListVO vo = new SubFundProjListVO();
                /*vo.setProjId("15481");
                vo.setCurrentAmountDisplay("2000 万元 人民币");
                vo.setInveId("10755");
                vo.setInveName("国创元禾");
                vo.setProjObjectName("松禾一期");
                vo.setSubFundAmontDisplay("5000 万元 人民币");
                ProjMemberModel m = new ProjMemberModel();
                m.setMemberId("1057");
                m.setMemberName("金祖庆");
                vo.setMangerModel(m);
                vo.setProjStatusName("已出资");*/
                BeanUtils.copyProperties( model,vo);
                if("1".equals(model.getGroupId())){
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("216", model.getProjStatus()));
                }else{
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("218", model.getProjStatus()));
                }

                list.add(vo);

                /*SubFundProjListVO vo1 = new SubFundProjListVO();
                vo1.setProjId("15565");
                vo1.setCurrentAmountDisplay("6000 万元 人民币");
                vo1.setInveId("10754");
                vo1.setInveName("国创创投");
                vo1.setProjObjectName("天丰基金");
                vo1.setSubFundAmontDisplay("8000 万元 人民币");
                ProjMemberModel m1 = new ProjMemberModel();
                m1.setMemberId("1061");
                m1.setMemberName("金玉玲");
                vo1.setMangerModel(m1);
                vo1.setProjStatusName("已决策");
                list.add(vo1);*/
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

    @ApiOperation(value = "子基金项目列表", notes = "子基金项目列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/fundProjInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundProjInfoList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getProjStatus())) {
                List<String> projStatus = Arrays.asList(searchBean.getProjStatus().split(","));
                searchCondition.addConditionIn("PROJ_STATUS", projStatus);
            }
            if (StringUtils.isNotEmpty(searchBean.getInveType())) {
                List<String> inveType = Arrays.asList(searchBean.getInveType().split(","));
                searchCondition.addConditionIn("INVE_TYPE", inveType);
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<ProjInfoModel> rows = projInfoService.selectProjInfoListPage(searchCondition);
            List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
            for (ProjInfoModel model : rows.getList()) {
                ProjInfoVO vo = new ProjInfoVO();
                BeanUtils.copyProperties( model,vo);
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

    @ApiOperation(value = "企业层面", notes = "企业层面列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/bussinessInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bussinessInfoList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            searchCondition.setSearchBean(searchBean);
            PageInfo<ProjInfoModel> rows = projInfoService.selectbussinessListPage(searchCondition);
            List<BussinessInfoVO> list = new ArrayList<BussinessInfoVO>();
            for (ProjInfoModel model : rows.getList()) {
                BussinessInfoVO vo = new BussinessInfoVO();
                BeanUtils.copyProperties( model,vo);
                //根据企业名称查询企业所属行业
                if (StringUtils.isNotEmpty(model.getRemark())) {
                    List<EntBaseInfoModel> entBase = entBaseInfoService.selectByHy(model.getRemark());
                    if (entBase != null && entBase.size() > 0) {
                        vo.setQkIndustry(entBase.get(0).getQkIndustry());
                        vo.setSevenIndustry(entBase.get(0).getSevenIndustry());
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

    @ApiOperation(value = "获取项目投资情况2信息", notes = "获取项目投资情况信息")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/projectInvestment", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projectInvestment(@RequestBody ProjInfoSearchBean projInfoSearchBean) {
        try {

            //获取总投资
            List<ProjInfoModel> listProjInfo = projInfoService.selectprojectInvestment(projInfoSearchBean);
            //查询投基金
            List<BussniessResultModel> list = projInfoService.selectCountBussunessName(projInfoSearchBean);
            if (list != null && list.size() > 0) {
                for (BussniessResultModel bus : list) {
                    if (StringUtils.isNotEmpty(bus.getName())) {
                        FundInfoModel fund = new FundInfoModel();
                        fund.setRegName(bus.getName());
                        FundInfoModel fomodel = fundInfoService.selectBy(fund);
                        if (StringUtils.isNotEmpty(fomodel.getPlatProp())) {
                            bus.setPlatProp(codeUtils.getCodeNameByValue("204", fomodel.getPlatProp()));
                        }
                    }
                }
            }
            //投企业
            List<SumProjInfo> sumlist = projInfoService.seleSumProjInfo(projInfoSearchBean);
            //通过统计的项目置换出项目类型和行业，根据项目的id置换出基金的类型
            if (sumlist != null && sumlist.size() > 0) {
                for (SumProjInfo sum : sumlist) {
                    Double pr = Double.parseDouble(sum.getBjjAmount());
                    Double pr1 = Double.parseDouble(sum.getShAnount());
                    if (!"0".equals(pr) && pr1 != 0) {
                        Double persent = pr / pr1 * 100;
                        DecimalFormat df4 = new DecimalFormat("#.##%");//数字格式化
                        sum.setPersent(df4.format(persent));
                    }
                    ProjInfoModel fomodel = projInfoService.selectById(sum.getProjId());
                    if (fomodel != null) {
                        //获取所属行业
                        EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(fomodel.getProjObject());
                        if (entBaseInfoModel != null && StringUtils.isNotEmpty(entBaseInfoModel.getSevenIndustry())) {
                            sum.setHy(codeUtils.getCodeNameByValue("20403", entBaseInfoModel.getSevenIndustry()));

                        }
				/*   	if(StringUtils.isNotEmpty(fomodel.getIsSpv())){
                       if(fomodel.getIsSpv().equals("0")){//不是spv
						   sum.setType("spv基金");
					   }
					}else{
						if(StringUtils.isNotEmpty(fomodel.getProjType())){
                              if("1".equals(fomodel.getProjType())){
								  sum.setType("投项目");
							  }else if("2".equals(fomodel.getProjType())){
								  sum.setType("投基金");
							  }
							  else if("3".equals(fomodel.getProjType())){
								  sum.setType("子基金项目");
							  }
						}
					}*/
                        //查询基金的类型
                        FundInfoModel fundInfo = fundInfoService.selectById(fomodel.getProjObject());
                        if (fundInfo != null) {
                            if (StringUtils.isNotEmpty(fundInfo.getPlatProp())) {
                                sum.setJbType(codeUtils.getCodeNameByValue("204", fundInfo.getPlatProp()));
                            }
                        }
                    }
                }
            }

            ResultVO resultVO = new ResultVO();
            resultVO.setList(list);
            resultVO.setSumProjInfo(sumlist);
            resultVO.setProjinFomodel(listProjInfo);
            dataResponse.setData(resultVO);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "直投项目情况统计表", notes = "直投项目情况情况列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/directProjList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String directProjList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            Calendar cal = Calendar.getInstance();
            int yearStr = cal.get(Calendar.YEAR);
            if(StringUtils.isNotEmpty(searchBean.getYear())){
                yearStr=Integer.parseInt(searchBean.getYear());
                searchCondition.addParam("year",searchBean.getYear());
            }else{
                searchCondition.addParam("year",String.valueOf(yearStr));
            }
            if (StringUtils.isNotEmpty(searchBean.getProjName())) {
                searchBean.setProjName("%" + searchBean.getProjName() + "%");
            }
            if (StringUtils.isNotEmpty(searchBean.getRegName())) {
                searchBean.setRegName("%" + searchBean.getRegName() + "%");
            }
            if (StringUtils.isNotEmpty(searchBean.getProjStatus())) {
                searchCondition.addParam("projStatus",searchBean.getProjStatus());
            }
            if (StringUtils.isNotEmpty(searchBean.getIndustry())) {
                List<String> industryS = Arrays.asList(searchBean.getIndustry().split(","));
                searchCondition.addConditionIn("cc.INDUSTRY", industryS);
            }
            if (StringUtils.isNotEmpty(searchBean.getIsJs())) {
                List<String> isJs = Arrays.asList(searchBean.getIsJs().split(","));
                searchCondition.addConditionIn("cc.IS_JS", isJs);
            }
            if (StringUtils.isNotEmpty(searchBean.getIsPublicComp())) {
                List<String> isPublicComp = Arrays.asList(searchBean.getIsPublicComp().split(","));
                searchCondition.addConditionIn("cc.IS_PUBLIC_COMP", isPublicComp);
            }

            if (StringUtils.isNotEmpty(searchBean.getLabelIds())) {
                searchBean.setLabelIds("%" + searchBean.getLabelIds() + "%");
            }

            if (StringUtils.isNotEmpty(searchBean.getProjProperty())) {
                List<String> projProperty = Arrays.asList(searchBean.getProjProperty().split(","));
                searchCondition.addConditionIn("cc.PROJ_PROPERTY", projProperty);
            }

            //查询登录人的角色
            //查询用户的角色
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (listRow != null && listRow.size() > 0) {
                if (listRow.get(0).getId() == 1005002) {
                    searchCondition.addParam("USER_ID", this.getLoginUserId());
                }else{
                    searchCondition.addParam("USER_ID", "");
                }
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<DirectProjResult> rows = projInfoService.selectdirectProjListPage(searchCondition);
            List<DirectProjResultVO> list = new ArrayList<DirectProjResultVO>();
            for (DirectProjResult model : rows.getList()) {
                DirectProjResultVO vo = new DirectProjResultVO();
                BeanUtils.copyProperties(model,vo);
                //查询省政府基金穿透到二级基金投项目的金额
                if (vo.getActualPayDate() != null) {
                    //投资时间
                    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
                    String tzDate = simple.format(vo.getActualPayDate());
                    //投资时间
                    vo.setTzDate(tzDate);
                }
                if(StringUtils.isNotEmpty(model.getEntePhase())){
                    vo.setEntePhaseName(codeUtils.getCodeNameByValue("208", model.getEntePhase()));
                }
                if (StringUtils.isNotBlank(model.getIndustryCategory())) {
                    vo.setIndustryCategoryName(codeUtils.getCodeNameByValue("5555", model.getIndustryCategory()));
                }

                if(StringUtils.isNotEmpty(model.getIsFit())){
                    vo.setIsFitName(codeUtils.getCodeNameByValue("1013", model.getProjProperty()));
                }
                if(StringUtils.isNotEmpty(model.getFitStatus())){
                    vo.setFitStatusName(codeUtils.getCodeNameByValue("1005", model.getFitStatus()));
                }
				EntBaseInfoModel ent=new EntBaseInfoModel();
				ent.setName(model.getProjName());
                if(StringUtils.isNotEmpty(model.getLabelIds())){
                    List<String> listLabel = Arrays.asList(model.getLabelIds().split(","));
                    for(String label:listLabel){
                        //根据企业的id查询该企业的标签
                        if("1".equals(label)){
                            //高新技术企业
                            vo.setIsGx("是");
                        }
                        if("2".equals(label)){
                            //一带一路
                            vo.setIsYdyl("是");
                        }
                        if("3".equals(label)){
                            //中小企业
                            vo.setIsZx("是");
                        }
                        if("4".equals(label)){
                            //战略新兴
                            vo.setIsZlxx("是");
                        }
                        if("9".equals(label)){
                            //卡脖子
                            vo.setIsKbz("是");
                        }
                        if("10001".equals(label)){
                            //卡脖子
                            vo.setIsKbz("是");
                        }
                        if("10001".equals(label)){
                            //是否属于专精特新小巨人
                            vo.setIsXjr("是");
                        }
                        if("11".equals(label)){
                            //隐形冠军
                            vo.setIsGj("是");
                        }
                    }

                 /*   if(StringUtils.isNotEmpty(model.getProjObject())){
				       EntBaseInfoModel entBaseInfoModel=entBaseInfoService.selectById(model.getProjObject());

                    }*/

/*
                if(entBaseInfoModel!=null){
				  List<LabelModel> listLabel=labelService.selectListByLabel(entBaseInfoModel.getEnterpriseId());
				  for(LabelModel labelModel:listLabel){
					   //根据企业的id查询该企业的标签
						if("高新技术企业".equals(labelModel.getLabelName())){
                               vo.setIsGx("是");
						}
						if("一带一路".equals(labelModel.getLabelName())){
                          vo.setIsYdyl("是");
						}
						if("中小企业".equals(labelModel.getLabelName())){
                         vo.setIsZx("是");
						}
					  if("战略新兴".equals(labelModel.getLabelName())){
						  vo.setIsZlxx("是");
					  }
				}
                    }*/

                }
                //直投投项目
                ProjQuitApplModel projQuitApplModel = new ProjQuitApplModel();
                projQuitApplModel.setIaId(model.getProjId());
                projQuitApplModel.setStatus("0");
                List<ProjQuitApplModel> listProjQuit = projQuitApplService.selectList(projQuitApplModel);
                if (listProjQuit != null && listProjQuit.size() > 0) {
                    //查询退出总金额
                    Double reval = projQuitApplService.seleSumRevenue(model.getProjId());
                    if (reval != null) {
                        vo.setQuitSumAmount(reval);
                    } else {
                        vo.setQuitSumAmount(0.0);
                    }
                    if ("1".equals(listProjQuit.get(0).getQuitType())) {
                        vo.setQuitTypeStatus("已退出");
                    } else {
                        vo.setQuitTypeStatus("正在退出");
                    }
                    //是否分配
                    if("1".equals(listProjQuit.get(0).getIsDistribute())){
                        vo.setIsDistributeName("是");
                        //分配金额
                        vo.setDistributeMoney(listProjQuit.get(0).getDistributeMoney());
                        vo.setDistributeGovMoney(listProjQuit.get(0).getDistributeGovMoney());
                        vo.setDistributeDt(listProjQuit.get(0).getDistributeDt());
                        vo.setQuitDt(listProjQuit.get(0).getQuitDt());
                    }else{
                        vo.setIsDistributeName("否");
                        vo.setQuitDt(listProjQuit.get(0).getQuitDt());
                    }


                } else {
                    vo.setQuitTypeStatus("未退出");
                }

                if (StringUtils.isNotEmpty(model.getProjProperty())) {
                    vo.setProjProperty(codeUtils.getCodeNameByValue("1013", model.getProjProperty()));
                }
                if (StringUtils.isNotEmpty(model.getProjId())) {
                    List<ProjContractFileModel> listPro = projContractFileService.selectByProjIdList(model.getProjId());
                    if (listPro != null && listPro.size() > 0) {
                        if (listPro.get(0).getSignAmount() != null) {
                            Double signAmount = listPro.get(0).getSignAmount();
                            vo.setSignAmount(signAmount);
                        }
                    }
                    if(StringUtils.isNotEmpty(model.getSumAmount())){
                        vo.setSumActualPayAmount(Double.valueOf(model.getSumAmount()));
                    }
           /*         List<XjlTPaymentRequestModel> xjltList = xjlTPaymentRequestService.selectByProjId(model.getProjId());
                    double sumActualPayAmount = 0;
                    if (xjltList != null && xjltList.size() > 0) {
                        for (XjlTPaymentRequestModel xjltpaymentrequestmodel : xjltList) {
                            if (xjltpaymentrequestmodel.getActualPayAmount() != null) {
                                sumActualPayAmount = sumActualPayAmount + xjltpaymentrequestmodel.getActualPayAmount();
                            } else {
                                sumActualPayAmount = sumActualPayAmount;
                            }

                        }
                        vo.setSumActualPayAmount(sumActualPayAmount);
                    }*/
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

    @ApiOperation(value="设立子基金（SPV）情况统计表", notes="设立子基金（SPV）情况统计表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/funderSpvInfoInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String funderSpvInfoInfoList(@RequestBody ProjInfoSearchBean searchBean){
        try {
            if(StringUtils.isNotEmpty(searchBean.getRegName())){
                searchBean.setRegName("%"+searchBean.getRegName()+"%");
            }
            if(StringUtils.isNotEmpty(searchBean.getFundName())){
                searchBean.setFundName("%"+searchBean.getFundName()+"%");
            }
            if(StringUtils.isNotEmpty(searchBean.getFundSrc())){
                List<String> fundSrc = Arrays.asList(searchBean.getFundSrc().split(","));
                searchCondition.addConditionIn("ccc.FUND_SRC", fundSrc);
            }
            if (StringUtils.isNotEmpty(searchBean.getProjStatus())) {
                searchCondition.addParam("projStatus",searchBean.getProjStatus());
            }
            //查询用户的角色
            List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
            if(listRow!=null&&listRow.size()>0){
                if(listRow.get(0).getId()==1005002){
                    searchCondition.addParam("USER_ID", this.getLoginUserId());
                }else{
                    searchCondition.addParam("USER_ID", "");
                }
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<SpvResult> rows = projInfoService.selectSpvListPage(searchCondition);
            List<SpvResulListVO> list = new ArrayList<SpvResulListVO>();
            for(SpvResult model : rows.getList()){
                SpvResulListVO vo = new SpvResulListVO();
                BeanUtils.copyProperties( model,vo);
                if("4".equals(model.getFundSrc())){
                    vo.setFundSrc("是");
                }else{
                    vo.setFundSrc("否");
                }
                if(StringUtils.isNotEmpty(model.getMaxActualPayDate())){
                    vo.setTzTime(model.getMaxActualPayDate());
                }
      /*          Map<String,Object> params = new HashMap<String, Object>();
                params.put("PROJECT_OR_FUND_ID",model.getProjId());
                List<XjlTPaymentRequestModel> xjltList=xjlTPaymentRequestService.selectByProjId(model.getProjId());
                double sumActualPayAmount=0;
                if(xjltList!=null&&xjltList.size()>0){
                    SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
                    if(xjltList.get(0).getActualPayDate()!=null){
                        Date tzTime= xjltList.get(0).getActualPayDate();
                        vo.setTzTime(sim.format(tzTime));
                    }
                    for(XjlTPaymentRequestModel xjltpaymentrequestmodel:xjltList){
                        if(xjltpaymentrequestmodel.getActualPayAmount()!=null){
                            sumActualPayAmount=sumActualPayAmount+xjltpaymentrequestmodel.getActualPayAmount();
                        }
                    }
                    vo.setSumActualPayAmount(sumActualPayAmount);
                }*/
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


	@ApiOperation(value="子基金已投项目情况统计表", notes="子基金已投项目情况统计表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projectInvestedList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String projectInvestedList(@RequestBody ProjInfoSearchBean searchBean){
		try {
			if(StringUtils.isNotEmpty(searchBean.getProjName())){
				searchBean.setProjName("%"+searchBean.getProjName()+"%");
			}
			if(StringUtils.isNotEmpty(searchBean.getRegName())){
				searchBean.setRegName("%"+searchBean.getRegName()+"%");
			}
			if(searchBean.getFundName()!=null){
				if("".equals(searchBean.getFundName().trim())||searchBean.getFundName().trim()==null){
					searchBean.setFundName("");
				}else{
					searchBean.setFundName("%"+searchBean.getFundName().trim()+"%");
				}
			}
            if (StringUtils.isNotEmpty(searchBean.getIsPublicComp())) {
                List<String> isPublicComp = Arrays.asList(searchBean.getIsPublicComp().split(","));
                searchCondition.addConditionIn("ccc.IS_PUBLIC_COMP", isPublicComp);
            }
			if(StringUtils.isNotEmpty(searchBean.getIsJs())){
				List<String> isJs = Arrays.asList(searchBean.getIsJs().split(","));
				searchCondition.addConditionIn("ccc.IS_JS", isJs);
			}
			if(StringUtils.isNotEmpty(searchBean.getProjProperty())){
				List<String> projProperty = Arrays.asList(searchBean.getProjProperty().split(","));
				searchCondition.addConditionIn("ccc.PROJ_PROPERTY", projProperty);
			}
            if (StringUtils.isNotEmpty(searchBean.getProjStatus())) {
                searchCondition.addParam("projStatus",searchBean.getProjStatus());
            }
			//查询用户的角色
			List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
			if(listRow!=null&&listRow.size()>0){
				if(listRow.get(0).getId()==1005002){
					searchCondition.addParam("USER_ID", this.getLoginUserId());
				}else{
                    searchCondition.addParam("USER_ID", "");
                }
			}
			searchCondition.setSearchBean(searchBean);
			PageInfo<DirectProjResult> rows = projInfoService.projectInvestedList(searchCondition);
			List<DirectProjResultVO> list = new ArrayList<DirectProjResultVO>();
			for(DirectProjResult model : rows.getList()){
				DirectProjResultVO vo = new DirectProjResultVO();
				BeanUtils.copyProperties( model,vo);
				EntBaseInfoModel ent=new EntBaseInfoModel();
				ent.setName(model.getProjName());
                ent.setEnterpriseId(model.getProjObject());
				if(StringUtils.isNotEmpty(model.getProjObject())){
				 EntBaseInfoModel entBaseInfoModel=entBaseInfoService.selectById(model.getProjObject());
				//根据企业的id查询该企业的标签
				//entLabelService.selectListByLabel(entBaseInfoModel.getEnterpriseId());
                    if(entBaseInfoModel!=null){
                        List<LabelModel> listLabel=labelService.selectListByLabel(entBaseInfoModel.getEnterpriseId());
                        for(LabelModel labelModel:listLabel){
                            //根据企业的id查询该企业的标签
                            if("1".equals(labelModel.getLabelId())){
                                vo.setIsGx("是");
                            }
                            if("2".equals(labelModel.getLabelId())){
                                vo.setIsYdyl("是");
                            }
                            if("3".equals(labelModel.getLabelId())){
                                vo.setIsZx("是");
                            }
							if("4".equals(labelModel.getLabelId())){
								vo.setIsZlxx("是");
							}
                        }
                    }


				}
				   //根据项目查询项目退出
					//直投投项目
					ProjQuitApplModel projQuitApplModel=new ProjQuitApplModel();
					projQuitApplModel.setIaId(model.getProjId());
					projQuitApplModel.setStatus("0");
				    List<ProjQuitApplModel> listProjQuit=projQuitApplService.selectList(projQuitApplModel);
                     if(listProjQuit!=null&&listProjQuit.size()>0){
                     	//查询退出总金额
						Double reval= projQuitApplService.seleSumRevenue(model.getProjId());
						if(reval!=null){
							vo.setQuitSumAmount(reval);
						}else{
							vo.setQuitSumAmount(0.0);
						}
						if("1".equals(listProjQuit.get(0).getQuitType())){
							vo.setQuitTypeStatus("已退出");
						}else if("2".equals(listProjQuit.get(0).getQuitType())){
							vo.setQuitTypeStatus("部分退出");
						}else{
							vo.setQuitTypeStatus("");
						}
                         //是否分配
                         if("1".equals(listProjQuit.get(0).getIsDistribute())){
                             vo.setIsDistributeName("是");
                             //分配金额
                             vo.setDistributeMoney(listProjQuit.get(0).getDistributeMoney());
                             vo.setDistributeGovMoney(listProjQuit.get(0).getDistributeGovMoney());
                             vo.setDistributeDt(listProjQuit.get(0).getDistributeDt());
                             vo.setQuitDt(listProjQuit.get(0).getQuitDt());
                         }else{
                             vo.setIsDistributeName("否");
                         }
                     	vo.setQuitDt(listProjQuit.get(0).getQuitDt());
					 }else{
						 vo.setQuitTypeStatus("");
					 }
				if(StringUtils.isNotEmpty(model.getProjProperty())){
					vo.setProjProperty(codeUtils.getCodeNameByValue("1013", model.getProjProperty()));
				}

                if (StringUtils.isNotBlank(model.getIndustryCategory())) {
                    vo.setIndustryCategoryName(codeUtils.getCodeNameByValue("5555", model.getIndustryCategory()));
                }
				if(StringUtils.isNotEmpty(model.getProjId())){
					//根据项目的id查询
					/*List<ProjContractFileModel> listPro=projContractFileService.selectByProjIdList(model.getProjId());
					if(listPro!=null && listPro.size()>0){
						if(listPro.get(0).getSignAmount()!=null){
							Double signAmount=listPro.get(0).getSignAmount();
							vo.setSignAmount(signAmount);
						}
						if(listPro.get(0).getSignDt()!=null){
							Date date=listPro.get(0).getSignDt();
							SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
							String tzDate=simple.format(date);
							vo.setTzDate(tzDate);
						}
					}*/
					//查询放款 ACTUAL_PAY_AMOUNT
					List<XjlTPaymentRequestModel> xjltList=xjlTPaymentRequestService.selectByProjId(model.getProjId());
					double sumActualPayAmount=0;
					if(xjltList!=null&&xjltList.size()>0){
						for(XjlTPaymentRequestModel xjltpaymentrequestmodel:xjltList){
							//累加所有的放款金额
							if(xjltpaymentrequestmodel.getActualPayAmount()!=null){
								sumActualPayAmount=sumActualPayAmount+xjltpaymentrequestmodel.getActualPayAmount();
							}
						}
						if(xjltList.get(0).getActualPayDate()!=null){
							SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
							String tzDate=simple.format(xjltList.get(0).getActualPayDate());
							vo.setTzDate(tzDate);

						}
                       vo.setSumActualPayAmount(sumActualPayAmount);
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

	@ApiOperation(value="获取项目列表", notes="查询现金流记录列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/getProjInfoList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String getProjInfoList(@RequestBody ProjInfoSearchBean searchBean){
		try {
			if(searchBean.getKeyword()!=null){
				if("".equals(searchBean.getKeyword().trim())||searchBean.getKeyword().trim()==null){
					searchBean.setKeyword("");
				}else{
					searchBean.setKeyword(searchBean.getKeyword().trim());
				}
			}
			searchCondition.addConditionNotEqualTo("a.STATUS", "2");
			if(StringUtils.isNotEmpty(searchBean.getProjStatus())){
				List<String> PROJ_STATUS = Arrays.asList(searchBean.getProjStatus().split(","));
				searchCondition.addConditionIn("a.PROJ_STATUS", PROJ_STATUS);
			}
            if(StringUtils.isNotEmpty(searchBean.getIsFit())){
                List<String> isFits = Arrays.asList(searchBean.getIsFit().split(","));
                searchCondition.addConditionIn("a.is_fit", isFits);
            }
			if(StringUtils.isNotEmpty(searchBean.getStatus())){
				List<String> status = Arrays.asList(searchBean.getStatus().split(","));
				searchCondition.addConditionIn("a.STATUS", status);
			}
			if(StringUtils.isNotEmpty(searchBean.getProjType())){
				List<String> projType = Arrays.asList(searchBean.getProjType().split(","));
				searchCondition.addConditionIn("a.PROJ_TYPE", projType);
			}
			if(StringUtils.isNotEmpty(searchBean.getInveId())){
				List<String> inveId = Arrays.asList(searchBean.getInveId().split(","));
				searchCondition.addConditionIn("a.INVE_ID", inveId);
			}
			if(StringUtils.isNotEmpty(searchBean.getInveName())){
				searchCondition.addConditionLike("a.INVE_NAME","%"+searchBean.getInveName().trim()+"%");
			}

			searchCondition.setSearchBean(searchBean);

			List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
			if(listRow!=null&&listRow.size()>0){
				if(1005002==listRow.get(0).getId()||1005003==listRow.get(0).getId()){
					searchCondition.addParam("userId", this.getLoginUserId());
				}
			}
			//searchCondition.addParam("userId", "1005000");
           String quarter= QuarterDateUtils.getQuarter();
            String QuarterOneDay="";
            String QuarterTDay="";
			if(StringUtils.isNotEmpty(quarter)){
			    //当前季度第一天
                QuarterOneDay=QuarterDateUtils.getCurrQuarter(Integer.parseInt(quarter));
                //当前季度第20天
                 QuarterTDay=QuarterDateUtils.getCurrQuarterT(Integer.parseInt(quarter));
            }
			PageInfo<ProjInfoModel> rows = projInfoService.selectProjListPage(searchCondition);
			List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
			for(ProjInfoModel model : rows.getList()){
				ProjInfoVO vo = new ProjInfoVO();
				BeanUtils.copyProperties( model,vo);
				//修改时间
                String warnMsg=getMsgInfo(model.getUpdateDt(),QuarterOneDay, QuarterTDay, quarter,model.getProjId());
                vo.setWarnMsg(warnMsg);
				if(model.getUpdateDt()!=null){
                    vo.setUpDt(model.getUpdateDt());
                }
				AppUserModel  appU=appUserService.selectById(this.getLoginUserId());
				if(appU!=null){
				    // 2是外部用户
                    vo.setUsertype(appU.getUsertype());
                }
				//出资主体判断是否需要合规审查
              FundInfoModel fundInfoD= fundInfoService.selectById(model.getInveId());
				if(fundInfoD!=null){
                    vo.setIsFitStr(fundInfoD.getIsFit());
                }
				if(StringUtils.isNotEmpty(model.getProjId())){
                    BdTFitRegulationSelfModel bd=bdTFitRegulationSelfService.selectById(model.getProjId());
                    if(bd!=null){
                        //列表判断，如果 FLOW_STATUS 是 1 或者 4 或者 3的时候
                        vo.setBdTFitRegulationSelfModel(bd);
                    }
                }


				if(model.getProjAppInfoModel()!=null){
					vo.setPerShare(model.getProjAppInfoModel().getPerShare());
					vo.setIntendedAmount(model.getProjAppInfoModel().getIntendedAmount());
					vo.setActualAmount(model.getProjAppInfoModel().getActualAmount());
				}

				if(model.getFundInfo()!=null){
					vo.setIsFit(model.getFundInfo().getIsFit());
					if(StringUtils.isNotBlank(model.getFundInfo().getIsRecord())){
						vo.setIsRecord(codeUtils.getCodeNameByValue("248", model.getFundInfo().getIsRecord()));
					}
				}
				/*if(model.getProjType().equals("2")||model.getProjType().equals("4")){
					FundInfoModel fund=fundInfoService.selectById(model.getProjObject());
					if(fund!=null){
						if(StringUtils.isNotBlank(fund.getIsRecord())){
							vo.setIsRecord(codeUtils.getCodeNameByValue("248", fund.getIsRecord()));
						}

					}
				}*/
				if(StringUtils.isNotBlank(model.getProjStatus())){
					vo.setProjStatusName(codeUtils.getCodeNameByValue("216", model.getProjStatus()));
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




	@ApiIgnore
	@ApiOperation(value="新增项目", notes="新增项目")
	@ApiImplicitParam(name = "ProjInfoDTO", value = "ProjInfoDTO", required = true, dataType = "ProjInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projInfo/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody ProjDtailDTO dto){
		try {
			//数组
			ProjInfoModel model = new ProjInfoModel();
			BeanUtils.copyProperties(dto,model);
			//自动生成项目编号
			if(StringUtils.isNotEmpty(dto.getProjType())){
				ProjInfoModel projInfopW=new ProjInfoModel();
				//查询出资主体的基金编号
				FundInfoModel fundVo=fundInfoService.selectById(dto.getInveId());
				if(fundVo!=null){
					if("1".equals(dto.getProjType())){
						//直投项目
						//查询是数据库中最新的项目编号
						projInfopW.setProjType("1");
						projInfopW.setProjNo("%"+fundVo.getFundCode()+"T"+"%");
						List<ProjInfoModel> list1=projInfoService.selectListByProjNo(projInfopW);
						if(list1!=null&&list1.size()>0){
                           //获取最大的编号
							String projNos=list1.get(0).getProjNo();
							if(StringUtils.isNotEmpty(projNos)){
								if(Integer.parseInt(projNos.substring(projNos.indexOf("T")+1))<=8){
									model.setProjNo(fundVo.getFundCode()+"T00"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("T")+1))+1));
								}else if(Integer.parseInt(projNos.substring(projNos.indexOf("T")+1))<=98){
									model.setProjNo(fundVo.getFundCode()+"T0"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("T")+1))+1));
								}else{
									model.setProjNo(fundVo.getFundCode()+"T"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("T")+1))+1));
								}
							}else{
								model.setProjNo(fundVo.getFundCode()+"T001");
							}

						}else{
							model.setProjNo(fundVo.getFundCode()+"T001");
						}
					}else if("2".equals(dto.getProjType())||"4".equals(dto.getProjType())){
						//直投项目
						//查询是数据库中最新的项目编号
						projInfopW.setProjType(dto.getProjType());
						projInfopW.setProjNo("%"+fundVo.getFundCode()+"J"+"%");
						List<ProjInfoModel> list1=projInfoService.selectListByProjNo(projInfopW);
						if(list1!=null&&list1.size()>0){
							//获取最大的编号
							String projNos=list1.get(0).getProjNo();
							if(StringUtils.isNotEmpty(projNos)){
								if(Integer.parseInt(projNos.substring(projNos.indexOf("J")+1))<=8){
									model.setProjNo(fundVo.getFundCode()+"J0"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("J")+1))+1));
								}else{
									model.setProjNo(fundVo.getFundCode()+"J"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("J")+1))+1));
								}
							}else{
								model.setProjNo(fundVo.getFundCode()+"J01");
							}

						}else{
							model.setProjNo(fundVo.getFundCode()+"J01");
						}
					}

				}


			}
			if(dto.getProjPropertyIds().length>0){
				String projPropertyIds="";
				for(int i=0;i<dto.getProjPropertyIds().length;i++){
					if(StringUtils.isNotEmpty(projPropertyIds)){
						projPropertyIds=projPropertyIds+","+dto.getProjPropertyIds()[i];
					}else{
						projPropertyIds=dto.getProjPropertyIds()[i];
					}
				}
				model.setProjProperty(projPropertyIds);
			}
			//根据出资主体生成编号
			/*if(StringUtils.isNotEmpty(dto.getInveId())){
			  FundInfoModel fund1=fundInfoService.selectById(dto.getInveId());
				if(fund1!=null){
				  String fundCode=fund1.getFundCode();
				}
			}*/

			ProjAppInfoModel projAppInfoModel=new ProjAppInfoModel();
			BeanUtils.copyProperties(dto,projAppInfoModel);

			EntBaseInfoModel entInfo=new EntBaseInfoModel();
			BeanUtils.copyProperties(dto,entInfo);
            String userId=this.getLoginUserId();
            BaseInfoExtendModel  baseInfoExtend=new BaseInfoExtendModel();
            baseInfoExtend.setListedSector(dto.getListedSector());
            baseInfoExtend.setUpdateDt(new Date());
            baseInfoExtend.setUpdateBy(userId);
            baseInfoExtend.setIsDelete("0");

			//String userId="1005000";
			BdTFitRegulationSelfModel bdTFitRegulationSelfModel=new BdTFitRegulationSelfModel();
			BeanUtils.copyProperties(dto,bdTFitRegulationSelfModel);
			FundInfoModel fund=new FundInfoModel();
			BeanUtils.copyProperties(dto,fund);
			if(StringUtils.isEmpty(dto.getProjId())){
					if("2".equals(dto.getProjType())||"4".equals(dto.getProjType())){
					/*	ProjInfoModel projInfos=new ProjInfoModel();
						projInfos.setProjName(dto.getProjName());
						projInfos.setInveId(dto.getInveId());
						List<ProjInfoModel> list=projInfoService.selectListByInveId(projInfos);
						  if(list!=null&&list.size()>0){
							  baseResponse.setMsg("出资主体不能重复");
							  return JSONObject.toJSONString(baseResponse);
							}else{*/
							  projInfoService.insertProjFund(model,projAppInfoModel,bdTFitRegulationSelfModel,fund,userId,entInfo,dto.getLabelId(),dto.getEnteId(),baseInfoExtend);
						 /*}*/
					 }else{
						projInfoService.insertProjFund(model,projAppInfoModel,bdTFitRegulationSelfModel,fund,userId,entInfo,dto.getLabelId(),dto.getEnteId(),baseInfoExtend);
					}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="修改项目", notes="修改项目")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projId", value = "现金流记录主键ledgerId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ProjDtailDTO", value = "ProjDtailDTO", required = true, dataType = "ProjDtailDTO")
	})

	@PostMapping(value = "/projInfo/update/{projId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("projId") String projId, @RequestBody ProjDtailDTO dto){
		try {
			//String userId="1005000";
			String userId=this.getLoginUserId();
			ProjInfoModel model = new ProjInfoModel();
			BeanUtils.copyProperties(dto,model);
			if(dto.getNewIndustry()==null){
				dto.setNewIndustry("");
			}
			if(dto.getNewIndustryName()==null){
				dto.setNewIndustryName("");
			}
			if("2".equals(dto.getProjType())||"4".equals(dto.getProjType())){

                if(StringUtils.isNotEmpty(dto.getProjObject())){
                    FundInfoModel  fundI=fundInfoService.selectById(dto.getProjObject());
                    if(fundI!=null&&!fundI.getFundName().equals(dto.getProjName())){
                        //判断如果修改了基金看是否存
                        FundInfoModel fundInfoDt=new FundInfoModel();
                        fundInfoDt.setFundSrc(dto.getProjType());
                        fundInfoDt.setFundName(dto.getProjName());
                        List<FundInfoModel>  fundListS=fundInfoService.selectList(fundInfoDt);
                        if(fundListS!=null&&!fundListS.isEmpty()){
                            baseResponse.setMsg("基金名称已存在");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                }


            }
			//自动生成项目编号
			if(StringUtils.isNotEmpty(dto.getProjType())){
				ProjInfoModel projInfopW=new ProjInfoModel();
				//查询出资主体的基金编号
				FundInfoModel fundVo=fundInfoService.selectById(dto.getInveId());
				if(fundVo!=null) {
					//查询出资主体是否修改
					ProjInfoModel model1 = projInfoService.selectById(projId);
					model.setProjObject(model1.getProjObject());
					Boolean lx = true;
					if (model1 != null) {
						if (StringUtils.isEmpty(model1.getInveId()) || StringUtils.isEmpty(dto.getInveId())) {
							lx = true;
						}
						if (StringUtils.isNotEmpty(model1.getInveId())) {
							if (StringUtils.isNotEmpty(dto.getInveId())) {
								if (model1.getInveId().equals(dto.getInveId())) {
									lx = false;
								}
							}

                        }

                    }
                    if (lx) {
                        if ("1".equals(dto.getProjType())) {
                            //直投项目
                            //查询是数据库中最新的项目编号
                            projInfopW.setProjType("1");
                            projInfopW.setProjNo("%" + fundVo.getFundCode() + "T" + "%");
                            List<ProjInfoModel> list1 = projInfoService.selectListByProjNo(projInfopW);
                            if (list1 != null && list1.size() > 0) {
                                //获取最大的编号
                                String projNos = list1.get(0).getProjNo();
                                if (StringUtils.isNotEmpty(projNos)) {
                                    if (Integer.parseInt(projNos.substring(projNos.indexOf("T") + 1)) <= 8) {
                                        model.setProjNo(fundVo.getFundCode() + "T00" + String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("T") + 1)) + 1));
                                    } else if (Integer.parseInt(projNos.substring(projNos.indexOf("T") + 1)) <= 98) {
                                        model.setProjNo(fundVo.getFundCode() + "T0" + String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("T") + 1)) + 1));
                                    } else {
                                        model.setProjNo(fundVo.getFundCode() + "T" + String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("T") + 1)) + 1));
                                    }
                                } else {
                                    model.setProjNo(fundVo.getFundCode() + "T001");
                                }

                            } else {
                                model.setProjNo(fundVo.getFundCode() + "T001");
                            }
                        } else if ("2".equals(dto.getProjType()) || "4".equals(dto.getProjType())) {
                            //直投项目
                            //查询是数据库中最新的项目编号
                            projInfopW.setProjType(dto.getProjType());
                            projInfopW.setProjNo("%" + fundVo.getFundCode() + "J" + "%");
                            List<ProjInfoModel> list1 = projInfoService.selectListByProjNo(projInfopW);
                            if (list1 != null && list1.size() > 0) {
                                //获取最大的编号
                                String projNos = list1.get(0).getProjNo();
                                if (StringUtils.isNotEmpty(projNos)) {
                                    if (Integer.parseInt(projNos.substring(projNos.indexOf("J") + 1)) <= 8) {
                                        model.setProjNo(fundVo.getFundCode() + "J0" + String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("J") + 1)) + 1));
                                    } else {
                                        model.setProjNo(fundVo.getFundCode() + "J" + String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("J") + 1)) + 1));
                                    }
                                } else {
                                    model.setProjNo(fundVo.getFundCode() + "J001");
                                }

                            } else {
                                model.setProjNo(fundVo.getFundCode() + "J001");
                            }
                        }
                    }
                }


            }
            if (dto.getProjPropertyIds() != null) {
                if (dto.getProjPropertyIds().length > 0) {
                    String projPropertyIds = "";
                    for (int i = 0; i < dto.getProjPropertyIds().length; i++) {
                        if (StringUtils.isNotEmpty(projPropertyIds)) {
                            projPropertyIds = projPropertyIds + "," + dto.getProjPropertyIds()[i];
                        } else {
                            projPropertyIds = dto.getProjPropertyIds()[i];
                        }
                    }
                    model.setProjProperty(projPropertyIds);
                }
            }

            model.setProjId(projId);
            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties( dto,projAppInfoModel);
            projAppInfoModel.setProjId(projId);
            BdTFitRegulationSelfModel bdTFitRegulationSelfModel = new BdTFitRegulationSelfModel();
            BeanUtils.copyProperties( dto,bdTFitRegulationSelfModel);
            bdTFitRegulationSelfModel.setProjId(projId);
            FundInfoModel fund = new FundInfoModel();
            BeanUtils.copyProperties(dto,fund);
            EntBaseInfoModel entBaseInfoModel = new EntBaseInfoModel();
            BeanUtils.copyProperties( dto,entBaseInfoModel);
            BaseInfoExtendModel  baseInfoExtend=new BaseInfoExtendModel();
            baseInfoExtend.setListedSector(dto.getListedSector());
            baseInfoExtend.setUpdateDt(new Date());
            baseInfoExtend.setUpdateBy(userId);

            projInfoService.updateProjFund(model, projAppInfoModel, bdTFitRegulationSelfModel, fund, entBaseInfoModel, dto.getLabelId(), userId,baseInfoExtend);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value = "查看项目信息", notes = "根据url的id查看项目信息")
    @ApiImplicitParam(name = "projId", value = "出资人ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projInfo/detail/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@PathVariable(value = "projId") String projId) {
        try {
            System.out.println("进入ent");
            ProjInfoModel model = projInfoService.selectByprojId(projId);
            ProjResultDetailVO vo = new ProjResultDetailVO();
            EntBaseInfoModel entBaseInfoModel = null;
            BaseInfoExtendModel baseInfoExtendModel=null;
            if (model != null ) {
                if(StringUtils.isNotEmpty(model.getProjObject())){
                    if ("1".equals(model.getProjType())) {
                        entBaseInfoModel = entBaseInfoService.selectById(model.getProjObject());
                        baseInfoExtendModel =baseInfoExtendService.selectById(model.getProjObject());
                    } else if ("2".equals(model.getProjType()) || "4".equals(model.getProjType())) {
                        FundInfoModel fund = fundInfoService.selectById(model.getProjObject());
                        if (fund != null && StringUtils.isNotEmpty(fund.getEnteId())) {
                            entBaseInfoModel = entBaseInfoService.selectById(fund.getEnteId());
                            baseInfoExtendModel =baseInfoExtendService.selectById(fund.getEnteId());
                        }
                    }
                }
                BeanUtils.copyProperties(model,vo);
                List<String> names = new ArrayList<String>();
                if (StringUtils.isNotEmpty(model.getProjProperty())) {
                    List<String> listIds = Arrays.asList(model.getProjProperty().split(","));
                    vo.setProjPropertyIds(listIds);
                    if (listIds != null && listIds.size() > 0) {
                        for (int i = 0; i < listIds.size(); i++) {
                            //查询码值
                            String projPropertyName = codeUtils.getCodeNameByValue("1013", listIds.get(i));
                            names.add(projPropertyName);
                        }
                    }
                }
                vo.setNames(names);
                if (StringUtils.isNotBlank(model.getEntePhase())) {
                    vo.setEntePhaseName(codeUtils.getCodeNameByValue("208", model.getEntePhase()));
                }
                if (StringUtils.isNotBlank(model.getIndustryCategory())) {
                    vo.setIndustryCategoryName(codeUtils.getCodeNameByValue("5555", model.getIndustryCategory()));
                }
                if (StringUtils.isNotBlank(model.getIsRegion())) {
                    vo.setIsRegionName(codeUtils.getCodeNameByValue("9008", model.getIsRegion()));
                }
                if (StringUtils.isNotBlank(model.getSpvType())) {
                    vo.setSpvTypeName(codeUtils.getCodeNameByValue("1015", model.getSpvType()));
                }
                if (StringUtils.isNotBlank(model.getProjProperty())) {
                    vo.setProjPropertyName(codeUtils.getCodeNameByValue("517", model.getProjProperty()));
                }
                if (StringUtils.isNotBlank(model.getProjStatus())) {
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("216", model.getProjStatus()));
                }
                if (model.getProjAppInfoModel() != null) {

                    if (StringUtils.isNotEmpty(model.getProjAppInfoModel().getFinaRounds())) {
                        vo.setFinaRoundsName(codeUtils.getCodeNameByValue("213", model.getProjAppInfoModel().getFinaRounds()));
                    }

                }
                if ("2".equals(model.getProjType()) || "4".equals(model.getProjType())) {
                    FundInfoModel fund = fundInfoService.selectById(model.getProjObject());
                    if (fund != null) {
                        vo.setFundInfo(fund);
                        if (StringUtils.isNotBlank(fund.getFundStruct())) {
                            String fundStructName = codeUtils.getCodeNameByValue("210", fund.getFundStruct());
                            vo.setFundStructName(StringUtils.isEmpty(fundStructName) ? "" : fundStructName);
                        }
                    }
                }
            }
            if (entBaseInfoModel != null) {
                List<String> list2 = new ArrayList<String>();
                List<LabelModel> listLabel = labelService.selectListByLabel(entBaseInfoModel.getEnterpriseId());
                String labelName = "";
                if (listLabel != null && listLabel.size() > 0) {
                    vo.setListLabel(listLabel);
                    for (LabelModel labelModel : listLabel) {
                        list2.add(labelModel.getLabelId());
                        if ("".equals(labelName)) {
                            labelName = labelModel.getLabelName();
                        } else {
                            labelName = labelName + "," + labelModel.getLabelName();
                        }

                    }
                }
                vo.setLabelName(labelName);
                vo.setListLabels(list2);
            }
            BdTFitRegulationSelfModel bdf = bdTFitRegulationSelfService.selectById(projId);
            ProjAppInfoModel pro = projAppInfoService.selectById(projId);

            vo.setBdTFitRegulationSelfModel(bdf);
            vo.setProjAppInfoModel(pro);
            vo.setEntBaseInfoModel(entBaseInfoModel);
            vo.setBaseInfoExtendModel(baseInfoExtendModel);
            dataResponse.setData(vo);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiIgnore
    @ApiOperation(value = "更新决策信息", notes = "更新决策信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ProjAppDTO", value = "ProjAppDTO", required = true, dataType = "ProjAppDTO")
    })
    @PostMapping(value = "/projAppInfo/updateAppInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateAppInfo(@RequestBody ProjAppDTO dto) {

        try {
            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties( dto,projAppInfoModel);

            BdTFitRegulationSelfModel BdTFitRegulationSelfModel = new BdTFitRegulationSelfModel();
            BeanUtils.copyProperties( dto,BdTFitRegulationSelfModel);
            BdTFitRegulationSelfModel.setProjId(dto.getProjId());
            FundInfoModel fund = new FundInfoModel();
            BeanUtils.copyProperties(dto,fund);
            String userId = this.getLoginUserId();
            //String userId="1005000";

            //修改出资主体
            ProjInfoModel model = new ProjInfoModel();
            BeanUtils.copyProperties(dto,model);
            model.setProjId(dto.getProjId());
            projInfoService.updateProjApp(projAppInfoModel, fund, dto.getProjType(), userId, dto.getProjObject(), model, BdTFitRegulationSelfModel);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value = "查看决策信息详情", notes = "查看决策信息")
    @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projInfo/appInfoDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String appInfoDetail(@RequestParam("projId") String projId, String projObject) {
        try {
            ProjAppInfoModel projAppInfoModel = projAppInfoService.selectById(projId);
            ProjAppInfoVO projAppInfoVO = new ProjAppInfoVO();
            if (StringUtils.isNotEmpty(projId)) {
                ProjInfoModel projInfoModel = projInfoService.selectById(projId);
                mapResponse.put("projInfoModel", projInfoModel);
                FundInfoModel fundInfoModel = fundInfoService.selectById(projInfoModel.getProjObject());
                if (fundInfoModel != null) {
                    mapResponse.put("fundInfoModel", fundInfoModel);
                }
                BdTFitRegulationSelfModel bd = bdTFitRegulationSelfService.selectById(projId);
                if (bd != null) {
                    projAppInfoVO.setAllTmoney(bd.getAllTmoney());
                }
            }


            BeanUtils.copyProperties( projAppInfoModel,projAppInfoVO);
            if (StringUtils.isNotEmpty(projAppInfoVO.getFinaRounds())) {
                projAppInfoVO.setFinaRoundsName(codeUtils.getCodeNameByValue("213", projAppInfoVO.getFinaRounds()));
            }
            if (StringUtils.isNotEmpty(projAppInfoVO.getInveRole())) {
                projAppInfoVO.setInveRoleName(codeUtils.getCodeNameByValue("215", projAppInfoVO.getInveRole()));
            }
            if (StringUtils.isNotEmpty(projAppInfoVO.getInveType())) {
                projAppInfoVO.setInveTypeName(codeUtils.getCodeNameByValue("234", projAppInfoVO.getInveType()));
            }
            if (projAppInfoModel.getIntendedAmount() != null) {
                DecimalFormat df = new DecimalFormat("##########0.00 ");//
                String temp = df.format(projAppInfoModel.getIntendedAmount());
                if(StringUtils.isNotEmpty(temp)){
                    projAppInfoVO.setIntendedAmount(Double.valueOf(temp));
                }

            }
            mapResponse.put("projAppInfoModel", projAppInfoVO);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }



    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    @ApiOperation(value = "获取直投项目列表", notes = "查询现金流记录列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getProjZtInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getProjZtInfoList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getProjName())) {
                searchBean.setProjName("%" + searchBean.getProjName() + "%");
            }
            if (StringUtils.isNotEmpty(searchBean.getFundName())) {
                searchBean.setFundName("%" + searchBean.getFundName() + "%");
            }
            if (StringUtils.isNotEmpty(searchBean.getTwoFundName())) {
                searchBean.setTwoFundName("%" + searchBean.getTwoFundName() + "%");
            }
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (listRow != null && listRow.size() > 0) {
                if (1005002 == listRow.get(0).getId() || 1005003 == listRow.get(0).getId()) {
                    searchCondition.addParam("userId", this.getLoginUserId());
                }
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<ProjInfoModel> rows = projInfoService.selectProjListByPage(searchCondition);
            List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
            for (ProjInfoModel model : rows.getList()) {
                ProjInfoVO vo = new ProjInfoVO();
                BeanUtils.copyProperties( model,vo);
                if(model.getEntBaseInfoModel()!=null){
                    vo.setAddressDetails(model.getEntBaseInfoModel().getAddressDetails());
                }


                if (StringUtils.isNotBlank(model.getIndustryCategory())) {
                    vo.setIndustryCategoryName(codeUtils.getCodeNameByValue("5555", model.getIndustryCategory()));
                }
                //根据项目id查询项目退出
                ProjQuitApplModel projQuitApplModel = new ProjQuitApplModel();
                projQuitApplModel.setIaId(model.getProjId());
                List<ProjQuitApplModel> projQuitAppList = projQuitApplService.selectList(projQuitApplModel);
                if (projQuitAppList != null && projQuitAppList.size() > 0) {
                    if (projQuitAppList.get(0) != null) {
                        if (StringUtils.isNotBlank(projQuitAppList.get(0).getQuitType())) {
                            vo.setQuitTypeName(codeUtils.getCodeNameByValue("262", projQuitAppList.get(0).getQuitType()));
                        }
                    }
                    vo.setProjQuitApplModel(projQuitAppList.get(0));

                }
                XjlTPaymentRequestModel xjl = new XjlTPaymentRequestModel();
                xjl.setProjectOrFundId(model.getProjId());
                List<XjlTPaymentRequestModel> list1 = xjlTPaymentRequestService.selectList(xjl);
                if (list1 != null && list1.size() > 0) {
                    vo.setXjl(list1.get(0));
                }
                //因为库里一百多条的数据都没有ent_id 所以才这么查
                if (StringUtils.isNotEmpty(model.getInveName())) {
                    EntBaseInfoModel ent = new EntBaseInfoModel();
                    ent.setName(model.getInveName());
                    ent.setDeleteFlag("0");
                    List<EntBaseInfoModel> listEnt = entBaseInfoService.selectList(ent);
                    if (listEnt != null && listEnt.size() > 0) {
                        //model.getFundInfo().setSocialCode(listEnt.get(0).getCreditCode());
                        if (vo.getEntBaseInfoModel() != null) {
                            vo.getEntBaseInfoModel().setKeyNo(listEnt.get(0).getCreditCode());
                        }

                    }
                }


				if(model.getProjAppInfoModel()!=null){
					if(StringUtils.isNotBlank(model.getProjAppInfoModel().getInveType())){
						vo.setInveTypeName(codeUtils.getCodeNameByValue("234", model.getProjAppInfoModel().getInveType()));
					}
					if(StringUtils.isNotBlank(model.getProjAppInfoModel().getInveRole())){
						vo.setInveRoleName(codeUtils.getCodeNameByValue("215", model.getProjAppInfoModel().getInveRole()));
					}
					if(StringUtils.isNotBlank(model.getProjAppInfoModel().getFinaRounds())){
						vo.setFinaRoundsName(codeUtils.getCodeNameByValue("213", model.getProjAppInfoModel().getFinaRounds()));
					}
					vo.setPerShare(model.getProjAppInfoModel().getPerShare());
					vo.setIntendedAmount(model.getProjAppInfoModel().getIntendedAmount());
				}
			/*	if(model.getProjAppInfoModel()!=null){
					if(StringUtils.isNotBlank(model.getProjAppInfoModel().getFinaRounds())){
						vo.setFinaRoundsName(codeUtils.getCodeNameByValue("213", model.getProjAppInfoModel().getFinaRounds()));
					}
				}*/
                if(StringUtils.isNotBlank(model.getIsRegion())){
                    vo.setIsRegionName(codeUtils.getCodeNameByValue("9008", model.getIsRegion()));
                }
                if (model.getFundInfo() != null) {
                    vo.setIsFit(model.getFundInfo().getIsFit());
                    if (StringUtils.isNotBlank(model.getFundInfo().getIsRecord())) {
                        vo.setIsRecord(codeUtils.getCodeNameByValue("248", model.getFundInfo().getIsRecord()));
                    }
                }
                if (StringUtils.isNotBlank(model.getProjStatus())) {
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("216", model.getProjStatus()));
                }
                EntBaseInfoModel ent = new EntBaseInfoModel();
                if (StringUtils.isNotEmpty(model.getProjProperty())) {
                    vo.setProjPropertyName(codeUtils.getCodeNameByValue("1013", model.getProjProperty()));
                }
                if (StringUtils.isNotEmpty(model.getProjObject())) {
                    ent.setEnterpriseId(model.getProjObject());
                    EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(model.getProjObject());
                    //根据企业的id查询该企业的标签
                    //entLabelService.selectListByLabel(entBaseInfoModel.getEnterpriseId());
                    if (entBaseInfoModel != null) {
                        List<String> list2 = new ArrayList<String>();
                        List<LabelModel> listLabel = labelService.selectListByLabel(entBaseInfoModel.getEnterpriseId());
                        for (LabelModel labelModel : listLabel) {
                            list2.add(labelModel.getLabelId());
                            vo.setListLabel(listLabel);
                            //根据企业的id查询该企业的标签
                            if ("1".equals(labelModel.getLabelId())) {
                                vo.setIsGx("是");
                            }
                            if ("2".equals(labelModel.getLabelId())) {
                                vo.setIsYdyl("是");
                            }
                            if ("3".equals(labelModel.getLabelId())) {
                                vo.setIsZx("是");
                            }
                            if ("4".equals(labelModel.getLabelId())) {
                                vo.setIsZlxx("是");
                            }


                        }
                        vo.setListLabels(list2);

                    }

                }

                list.add(vo);
            }
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());
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

    @ApiIgnore
    @ApiOperation(value = "三级基金直投项目导入编辑", notes = "三级基金直投项目导入编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "FundMcInfoVO", value = "FundAnalysisReportVO", required = true, dataType = "FundAnalysisReportVO")
    })
    @PostMapping(value = "/projInfo/updateInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@PathVariable("projId") String projId, @RequestBody ProjInfoDTO dto) {
        try {
            //判断修改的时候出资主体投的项目是否存在
            //判断 出资主体，项目，次数是否存在
           ProjAppInfoModel projAp=projAppInfoService.selectById(projId);
           if(StringUtils.isNotEmpty(dto.getInveRounds())){
               if(projAp!=null&&!dto.getInveRounds().equals(projAp.getInveRounds())){
                   Map<String, Object> map = new HashMap<String, Object>();
                   map.put("projName",dto.getProjName());
                   map.put("inveId",dto.getInveId());
                   map.put("inveRounds",dto.getInveRounds());
                   List<ProjInfoModel> list= projInfoService.selectList("selectByThrProj",map);
                   if(list!=null&&!list.isEmpty()){
                       baseResponse.setMsg("该出资主体投资该项目次数已存在");
                       return JSONObject.toJSONString(baseResponse);
                   }
               }
           }


            //修改出资主体
            ProjInfoModel proj = new ProjInfoModel();
            BeanUtils.copyProperties( dto,proj);
            proj.setProjId(projId);
            ProjAppInfoModel projApp = new ProjAppInfoModel();
            BeanUtils.copyProperties(dto,projApp);
            projApp.setProjId(projId);
            BdTFitRegulationSelfModel bd = new BdTFitRegulationSelfModel();
            BeanUtils.copyProperties( dto,bd);
            bd.setProjId(projId);
            ProjQuitApplModel projQ = new ProjQuitApplModel();
            BeanUtils.copyProperties(dto,projQ);
            projQ.setAppId(dto.getAppId());
            XjlTPaymentRequestModel xjl = new XjlTPaymentRequestModel();
            BeanUtils.copyProperties( dto,xjl);
            xjl.setPqId(dto.getPqId());
            String userId = this.getLoginUserId();
            //String userId="1005000";
            projInfoService.updateProjOrOthers(proj, projApp, bd, projQ, xjl, userId, dto.getLabels());


        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }



public Boolean validate(String number){
	Boolean  isNum = number.toString().matches("^(-?\\d+)(\\.\\d+)?$");
	return isNum;
}
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess=false;
		}
		return convertSuccess;
	}

    @ApiOperation(value="获取四级基金列表", notes="获取四级基金列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getFundOrSpvListPageList", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String getFundOrSpvListPageList(@RequestBody ProjInfoSearchBean searchBean){
        try {
            if(searchBean.getKeyword()!=null){
                if("".equals(searchBean.getKeyword().trim())||searchBean.getKeyword().trim()==null){
                    searchBean.setKeyword("");
                }else{
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if(StringUtils.isNotEmpty(searchBean.getProjName())){
                searchBean.setProjName("%"+searchBean.getProjName()+"%");
            }
            if(StringUtils.isNotEmpty(searchBean.getFundName())){
                searchBean.setFundName("%"+searchBean.getFundName()+"%");
            }
            searchCondition.setSearchBean(searchBean);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            PageInfo<FundInfoModel> rows = fundInfoService.selectFundOrSpvListByPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for(FundInfoModel model : rows.getList()){
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties( model,vo);
				if(StringUtils.isNotBlank(model.getFundSts())){
					vo.setFundStsName(codeUtils.getCodeNameByValue("249",model.getFundSts()));
				}
				if(StringUtils.isNotBlank(model.getFundStruct())){
					vo.setFundStructName(codeUtils.getCodeNameByValue("210",model.getFundStruct()));
				}

                if(model.getProjInfo()!=null){
					if(StringUtils.isNotBlank(model.getProjInfo().getSpvType())){
						vo.setSpvTypeName(codeUtils.getCodeNameByValue("1015",model.getProjInfo().getSpvType()));
					}
					if(StringUtils.isNotBlank(model.getProjInfo().getIsCorr())){
						vo.setIsCorrName(codeUtils.getCodeNameByValue("102",model.getProjInfo().getIsCorr()));
					}
					if(StringUtils.isNotBlank(model.getProjInfo().getIsJs())){
						vo.setIsJsName(codeUtils.getCodeNameByValue("102",model.getProjInfo().getIsJs()));
					}
				}
				if(model.getProjAppInfo()!=null){
					if(model.getProjAppInfo().getDecisionDate()!=null){
						model.getProjAppInfo().setDescDate(formatter.format(model.getProjAppInfo().getDecisionDate()));
					}
					if(StringUtils.isNotBlank(model.getProjAppInfo().getIsDecisionPass())){
						vo.setIsDecisionPassName(codeUtils.getCodeNameByValue("102",model.getProjAppInfo().getIsDecisionPass()));
					}
				}


                //根据项目id查询项目退出
                ProjQuitApplModel projQuitApplModel=new ProjQuitApplModel();
                projQuitApplModel.setIaId(model.getProjInfo().getProjId());
                List<ProjQuitApplModel> projQuitAppList=projQuitApplService.selectList(projQuitApplModel);
                if(projQuitAppList!=null&&projQuitAppList.size()>0){
                    if(projQuitAppList.get(0)!=null){
                        if(StringUtils.isNotBlank(projQuitAppList.get(0).getQuitType())){
                            vo.setQuitTypeName(codeUtils.getCodeNameByValue("262",projQuitAppList.get(0).getQuitType()));
                        }
                    }
                    vo.setProjQuitApplModel(projQuitAppList.get(0));
                }
                XjlTPaymentRequestModel xjl=new XjlTPaymentRequestModel();
                xjl.setProjectOrFundId(model.getProjInfo().getProjId());
                List<XjlTPaymentRequestModel> list1=  xjlTPaymentRequestService.selectList(xjl);
                if(list1!=null&&list1.size()>0){
                	if(list1.get(0).getActualPayDate()!=null){
						list1.get(0).setActDate(formatter.format(list1.get(0).getActualPayDate()));
					}
                    vo.setXjl(list1.get(0));
                }
                if(StringUtils.isNotBlank(model.getIsRecord())){
                        vo.setIsRecordName(codeUtils.getCodeNameByValue("248", model.getIsRecord()));
                    }
                    if(model.getProjInfo()!=null){
                        if(StringUtils.isNotBlank(model.getProjInfo().getProjStatus())){
                            vo.setProjStatusName(codeUtils.getCodeNameByValue("216",model.getProjInfo().getProjStatus()));
                        }
                    }

                list.add(vo);
            }
            dataTablesResponse.setData(list, rows);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());
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

    @ApiIgnore
    @ApiOperation(value="四级基金导入编辑", notes="四级基金直投项目导入编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "FundMcInfoVO", value = "FundAnalysisReportVO", required = true, dataType = "FundAnalysisReportVO")
    })
    @PostMapping(value = "/fundInfo/updateOrSpvInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateOrSpvInfo(@PathVariable("projId") String projId, @RequestBody FundInfoUpDTO dto){
        try {
            //修改出资主体
            ProjInfoModel proj = new ProjInfoModel();
            BeanUtils.copyProperties(dto,proj);
            proj.setProjId(projId);
            ProjAppInfoModel projApp = new ProjAppInfoModel();
            BeanUtils.copyProperties(dto,projApp);

            projApp.setProjId(projId);
            BdTFitRegulationSelfModel bd=new BdTFitRegulationSelfModel();
            BeanUtils.copyProperties(dto,bd);

            bd.setProjId(projId);
            ProjQuitApplModel projQ=new ProjQuitApplModel();
            BeanUtils.copyProperties(dto,projQ);

           // projQ.setAppId(dto.getAppId());
            XjlTPaymentRequestModel xjl=new XjlTPaymentRequestModel();
			xjl.setProjectOrFundId(projId);
            BeanUtils.copyProperties(dto,xjl);

            //四级基金
            FundInfoModel fund=new FundInfoModel();
            BeanUtils.copyProperties(dto,fund);

            //三级基金
            FundInfoModel fundInfo=new FundInfoModel();
            fundInfo.setFundId(dto.getInveId());
            fundInfo.setRaiseAmount(dto.getAmount());
           // xjl.setPqId(dto.getPqId());
			String userId=this.getLoginUserId();
			//String userId="1005000";
            projInfoService.updateProjOrFundInfo(proj,projApp,bd,projQ,xjl,userId,fund,fundInfo);


        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    //******************************************************内部用户*********************************************************
    @ApiIgnore
    @ApiOperation(value = "项目投管-内部用户新增项目", notes = "内部用户新增项目")
    @ApiImplicitParam(name = "ProjInfoDTO", value = "ProjInfoDTO", required = true, dataType = "ProjInfoDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/projInfo/internalAdd", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String internalAdd(@RequestBody ProjDtailDTO dto) {
        try {
            //数组
            ProjInfoModel model = new ProjInfoModel();
            BeanUtils.copyProperties(dto,model);
            //自动生成项目编号
            if (StringUtils.isNotEmpty(dto.getProjType())) {
                ProjInfoModel projInfopW = new ProjInfoModel();
                //查询出资主体的基金编号
                FundInfoModel fundVo = fundInfoService.selectById(dto.getInveId());
                if (fundVo != null) {
                    if ("1".equals(dto.getProjType())) {
                       String projNo=getProjCode(dto.getInveId());
                       model.setProjNo(projNo);

                    }
                }
            }
            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties( dto,projAppInfoModel);

            String userId=this.getLoginUserId();
            BdTFitRegulationSelfModel bdTFitRegulationSelfModel = new BdTFitRegulationSelfModel();
            BeanUtils.copyProperties( dto,bdTFitRegulationSelfModel);

            projInfoService.insertInternalProj(model, projAppInfoModel, bdTFitRegulationSelfModel, userId, dto.getLabelId(), dto.getProjManage(), dto.getGeneralManager(), dto.getOperators());
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "项目投管-列表", notes = "项目投管-列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getInternalProjList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getInternalProjList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getProjSrc())) {
                List<String> projSrcs = Arrays.asList(searchBean.getProjSrc().split(","));
                searchCondition.addConditionIn("bb.PROJ_SRC", projSrcs);
            }
            if(StringUtils.isNotEmpty(searchBean.getIsDirect())){
                searchCondition.addConditionEqualTo("ff.ISDIRECT", "1");
            }
            //searchCondition.addConditionEqualTo("bb.GROUP_ID", searchBean.getGroupId());
            searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
            if (StringUtils.isNotEmpty(searchBean.getProjStatus())) {
                List<String> projStatus = Arrays.asList(searchBean.getProjStatus().split(","));
                searchCondition.addConditionIn("bb.PROJ_STATUS", projStatus);
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<ProjInfoModel> rows = projInfoService.getInternalProjList(searchCondition);
            List<ProjInfoInternalVO> list = new ArrayList<ProjInfoInternalVO>();
            for (ProjInfoModel model : rows.getList()) {
                //todo累计出资是流程结束的才算还是，投前估值算哪个，（立项，还是决策）流程审批结束的值
                ProjInfoInternalVO vo = new ProjInfoInternalVO();
                BeanUtils.copyProperties( model,vo);
                //项目来源
                if (StringUtils.isNotEmpty(model.getProjSrc())) {
                    String projSrcName = codeUtils.getCodeNameByValue("229", model.getProjSrc());
                    vo.setProjSrcName(projSrcName);
                }
                //项目经理，经办人，分管副总
                Map<String, Object> mapName = projMemberService.seleByName(model.getProjId());
                vo.setMapName(mapName);
                if (model.getProjAppInfoModel() != null) {
                    vo.setStartDate(model.getProjAppInfoModel().getStartDate());
                    if (StringUtils.isNotEmpty(model.getProjAppInfoModel().getInveRole())) {
                        vo.setInveRoleName(codeUtils.getCodeNameByValue("215", model.getProjAppInfoModel().getInveRole()));
                    }
                    if (StringUtils.isNotEmpty(model.getProjAppInfoModel().getInveType())) {
                        vo.setInveTypeName(codeUtils.getCodeNameByValue("234", model.getProjAppInfoModel().getInveType()));
                    }
                }

                Double doub=xjlTPaymentRequestService.selectSumPass(model.getProjId());
                double sumInveAmount=0;
                if(doub!=null){
                    sumInveAmount=doub;
                    vo.setSumInveAmount(sumInveAmount);
                }else{
                    vo.setSumInveAmount(0.0);
                }
                //查询累计回收金额
              Double sumAmount=projReceiptQuitService.getSumAmount(model.getProjId());
                if(sumAmount!=null){
                    vo.setSumRaiseAmount(String.valueOf(sumAmount));
                }else{
                    vo.setSumRaiseAmount("0");
                }
                //收益
                BigDecimal bd2 = new BigDecimal(Double.toString(vo.getSumInveAmount()));
                BigDecimal bd1 = new BigDecimal(Double.toString(sumAmount==null?Double.valueOf(0):sumAmount));
                 bd1.subtract(bd2).doubleValue();
                BigDecimal result2 = bd1.setScale(4,BigDecimal.ROUND_HALF_DOWN).subtract(bd2.setScale(4,BigDecimal.ROUND_HALF_DOWN));
                Double syAmount=result2.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
                if(syAmount!=null){
                    vo.setSumCurrentAmount(String.valueOf(syAmount));
                }else{
                    vo.setSumCurrentAmount("0");
                }
              /*  Map<String,Object> map= projBackMoneySettleService.sumBackMoney(model.getProjId());
                if(map!=null){
                    if (map.get("SUM_RAISE_AMOUNT") != null) {
                        vo.setSumRaiseAmount(String.valueOf(map.get("SUM_RAISE_AMOUNT").toString()));
                    }else{
                        vo.setSumRaiseAmount("0");
                    }
                    if (map.get("SUM_CURRENT_AMOUNT") != null) {
                        vo.setSumCurrentAmount(String.valueOf(map.get("SUM_CURRENT_AMOUNT").toString()));
                    }else{
                        vo.setSumCurrentAmount("0");
                    }

                }else{
                    vo.setSumRaiseAmount("0");
                    vo.setSumCurrentAmount("0");
                }*/

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

    @ApiIgnore
    @ApiOperation(value = "项目投管-修改项目", notes = "项目投管-修改项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "现金流记录主键ledgerId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "ProjDtailDTO", value = "ProjDtailDTO", required = true, dataType = "ProjDtailDTO")
    })
    @PostMapping(value = "/projInfo/internalUpdate/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String internalUpdate(@PathVariable("projId") String projId, @RequestBody ProjDtailDTO dto) {
        try {
            //String userId="1005000";
            String userId = this.getLoginUserId();
            ProjInfoModel model = new ProjInfoModel();
            BeanUtils.copyProperties(dto,model);
            //自动生成项目编号
            if (StringUtils.isNotEmpty(dto.getProjType())) {
                ProjInfoModel projInfopW = new ProjInfoModel();
                //查询出资主体的基金编号
                FundInfoModel fundVo = fundInfoService.selectById(dto.getInveId());
                if (fundVo != null) {
                    //查询出资主体是否修改
                    ProjInfoModel model1 = projInfoService.selectById(projId);
                    Boolean lx = true;
                    if (model1 != null) {
                        if (StringUtils.isEmpty(model1.getInveId()) || StringUtils.isEmpty(dto.getInveId())) {
                            lx = true;
                        }
                        if (StringUtils.isNotEmpty(model1.getInveId())) {
                            if (StringUtils.isNotEmpty(dto.getInveId())) {
                                if (model1.getInveId().equals(dto.getInveId())) {
                                    lx = false;
                                }
                            }

                        }

                    }
                    if (lx) {
                        if ("1".equals(dto.getProjType())) {
                            String projNo=getProjCode(dto.getInveId());
                            model.setProjNo(projNo);
                        }
                    }
                }
            }
            model.setProjId(projId);
            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties(dto,projAppInfoModel);
            projAppInfoModel.setProjId(projId);
            BdTFitRegulationSelfModel bdTFitRegulationSelfModel = new BdTFitRegulationSelfModel();
            BeanUtils.copyProperties( dto,bdTFitRegulationSelfModel);
            bdTFitRegulationSelfModel.setProjId(projId);
            projInfoService.updateInternalProj(model, userId, dto.getLabelId(), dto.getProjManage(), dto.getGeneralManager(), dto.getOperators(),bdTFitRegulationSelfModel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);

    }


    @ApiOperation(value = "投管流程-查看项目信息详情", notes = "投管流程-查看项目信息详情")
    @ApiImplicitParam(name = "projId", value = "出资人ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projInfo/internalDetail/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String internalDetail(@PathVariable(value = "projId") String projId) {
        try {
            ProjInfoModel model = projInfoService.selectByprojId(projId);
            ProjResultDetailVO vo = new ProjResultDetailVO();
            BeanUtils.copyProperties( model,vo);
            //查询企业标签
            Map<String, Object> map = null;
            if (StringUtils.isNotEmpty(model.getProjObject())) {
                map = labelService.selectListByLabels(model.getProjObject());
            }
            //企业标签
            vo.setMap(map);
            //企业性质
            if (StringUtils.isNotEmpty(model.getProjProperty())) {
                String projPropertyName = codeUtils.getCodeNameByValue("1013", model.getProjProperty());
                vo.setProjPropertyName(projPropertyName);
            }
            //企业所属阶段
            if (StringUtils.isNotEmpty(model.getEntePhase())) {
                String entePhaseName = codeUtils.getCodeNameByValue("208", model.getEntePhase());
                vo.setEntePhaseName(entePhaseName);
            }
            //项目来源
            if (StringUtils.isNotEmpty(model.getProjSrc())) {
                String projSrcName = codeUtils.getCodeNameByValue("229", model.getProjSrc());
                vo.setProjSrcName(projSrcName);
            }
            //项目经理，经办人，分管副总
            Map<String, Object> mapName = projMemberService.seleByName(model.getProjId());
            vo.setMapName(mapName);
            EntBaseInfoModel entBaseInfoModel = entBaseInfoService.selectById(model.getProjObject());
            vo.setEntBaseInfoModel(entBaseInfoModel);
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

    @ApiOperation(value = "项目投管-查询分管副总", notes = "项目投管-查询分管副总")
    @ApiImplicitParam(name = "userId", value = "出资人ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projInfo/getgeneralManager", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getgeneralManager() {
        try {
            String userId = this.getLoginUserId();
           AppUserModel mo= appUserService.selectById(userId);
            Long deptId=mo.getDeptid();
            AppGroup dept = UimUtils.getDept(Long.parseLong(userId));
            String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(deptId, OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
            AppUserModel AppUserModel = appUserService.selectById(divisionVicePresident);
            mapResponse.put("AppUserModel", AppUserModel);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }

   /**获取项目编号 **/
    public String getProjCode(String inveId) {
        try {
           FundInfoModel fund= fundInfoService.selectById(inveId);
            String code="";
            if(fund!=null){
               String fundCode=fund.getFundCode();
                SimpleDateFormat sim = new SimpleDateFormat("yyyy");
                String time = sim.format(new Date());
                String proCode=fundCode+time;
                System.out.print("proCode**********************"+proCode);
                List<ProjInfoModel> projInfoList= projInfoService.getNewProCode("%"+proCode+"%");
                if(projInfoList!=null&&projInfoList.size()>0){
                  String proj=projInfoList.get(0).getProjNo();
                    Integer len = Integer.parseInt(proj.replaceFirst(proCode,"" ));
                   if(len <= 8) {
                        code=proCode + "000" + String.valueOf((len + 1));
                   }else if(len <= 98) {
                        code=proCode + "00" + String.valueOf((len + 1));
                   }else if(len <= 998) {
                        code=proCode + "0" + String.valueOf((len + 1));
                   }else{
                        code=proCode+ String.valueOf((len + 1));
                   }
               }else{

                    code=proCode+"0001";
                    System.out.print("code**********************"+code);
                }
            }
           return code;

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return "";
    }


    @ApiIgnore
    @ApiOperation(value="项目投管删除", notes="项目投管删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meetingId", value = "meetingId", required = true, dataType = "String",paramType = "path"),
    })
    @GetMapping(value = "/projInfo/delete/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable("projId") String projId){

        try {
            ProjInfoModel projInfoModel=projInfoService.selectById(projId);
            if(projInfoModel!=null){
                if(!"1".equals(projInfoModel.getProjStatus())){
                    baseResponse.setMsg("储备项目才允许删除！");
                    return JSONObject.toJSONString(baseResponse);
                }
            }
            ProjInfoModel model = new ProjInfoModel();
            model.setProjId(projId);
            model.setStatus("2");
            projInfoService.update(model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value="母基金投基金", notes="母基金投基金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fof/fofProjInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fofProjInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String fundId){
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addConditionEqualTo("b.fund_id",fundId);
            }
            PageInfo<FofInfoModel> rows = projInfoService.selectByFofInfoList(searchCondition);
            List<FofInfoVO> list = new ArrayList<FofInfoVO>();
            for(FofInfoModel model : rows.getList()){
                FofInfoVO vo = new FofInfoVO();
                BeanUtils.copyProperties(model,vo);
                if(StringUtils.isNotEmpty(model.getProjStatus())){
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("218", model.getProjStatus()));
                }
               if(StringUtils.isNotEmpty(model.getProjSrc())){
                   String projSrcName = codeUtils.getCodeNameByValue("229", model.getProjSrc());
                   vo.setProjSrcName(projSrcName);
               }else{
                   vo.setProjSrcName("");
               }


                //
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

    @ApiOperation(value="母基金投项目", notes="母基金投基金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fof/fofDirectProjInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fofDirectProjInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String fundId){
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addConditionEqualTo("a.inve_id",fundId);
            }
            PageInfo<FofInfoModel> rows = projInfoService.selectFofDirectList(searchCondition);
            List<FofInfoVO> list = new ArrayList<FofInfoVO>();
            for(FofInfoModel model : rows.getList()){
                FofInfoVO vo = new FofInfoVO();
                BeanUtils.copyProperties( model,vo);
                if(StringUtils.isNotEmpty(model.getProjStatus())){
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("218", model.getProjStatus()));
                }
                if(StringUtils.isNotEmpty(model.getProjSrc())){
                    String projSrcName = codeUtils.getCodeNameByValue("229", model.getProjSrc());
                    vo.setProjSrcName(projSrcName);
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
	@ApiOperation(value="四级基金新增", notes="四级基金直投项目导入编辑")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundMcInfoVO", value = "FundAnalysisReportVO", required = true, dataType = "FundAnalysisReportVO")
	})
	@PostMapping(value = "/fundInfo/addOrSpvInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addOrSpvInfo(@RequestBody FundInfoUpDTO dto){
		try {
			String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
			//出资主体必须为三级基金
			//修改出资主体
			ProjInfoModel proj = new ProjInfoModel();
			BeanUtils.copyProperties(dto,proj);
			proj.setProjId(projId);
			ProjAppInfoModel projApp = new ProjAppInfoModel();
			BeanUtils.copyProperties(dto,projApp);

			projApp.setProjId(projId);
			BdTFitRegulationSelfModel bd=new BdTFitRegulationSelfModel();
			BeanUtils.copyProperties(dto,bd);
			bd.setProjId(projId);

			ProjQuitApplModel projQ=new ProjQuitApplModel();
			BeanUtils.copyProperties(dto,projQ);
			String APP_ID=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
			projQ.setAppId(APP_ID);

			XjlTPaymentRequestModel xjl=new XjlTPaymentRequestModel();
			xjl.setProjectOrFundId(projId);
			String PQ_ID=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
			xjl.setPqId(PQ_ID);
			BeanUtils.copyProperties(dto,xjl);

			//四级基金
			FundInfoModel fund=new FundInfoModel();
			BeanUtils.copyProperties(dto,fund);

			//三级基金
			FundInfoModel fundInfo=new FundInfoModel();
			fundInfo.setFundId(dto.getInveId());
			fundInfo.setRaiseAmount(dto.getAmount());
			String userId=this.getLoginUserId();
			projInfoService.insertProjOrFundInfo(proj,projApp,bd,projQ,xjl,userId,fund,fundInfo);


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="直投项目导入新增", notes="直投项目导入新增")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundMcInfoVO", value = "FundAnalysisReportVO", required = true, dataType = "FundAnalysisReportVO")
	})
	@PostMapping(value = "/projInfo/fundOrProjAdd", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundOrProjAdd(@RequestBody ProjInfoDTO dto){
		try {
		    //判断 出资主体，项目，次数是否存在
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("projName",dto.getProjName());
            map.put("inveId",dto.getInveId());
            map.put("inveRounds",dto.getInveRounds());
           List<ProjInfoModel> list= projInfoService.selectList("selectByThrProj",map);
           if(list!=null&&!list.isEmpty()){
               baseResponse.setMsg("该出资主体投资该项目次数已存在");
               return JSONObject.toJSONString(baseResponse);
           }
			//修改出资主体
			String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
			System.out.print("项目id"+projId);
			ProjInfoModel proj = new ProjInfoModel();
			BeanUtils.copyProperties(dto,proj);
            System.out.print("项目"+projId);
			proj.setProjId(projId);
			ProjAppInfoModel projApp = new ProjAppInfoModel();
			BeanUtils.copyProperties(dto,projApp);
            System.out.print("2************");
			projApp.setProjId(projId);
			BdTFitRegulationSelfModel bd=new BdTFitRegulationSelfModel();
			BeanUtils.copyProperties(dto,bd);
            System.out.print("4************");
			bd.setProjId(projId);
			ProjQuitApplModel projQ=new ProjQuitApplModel();
			BeanUtils.copyProperties(dto,projQ);
            System.out.print("222222");
			String APP_ID=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
            System.out.print("APP_ID"+APP_ID);
			projQ.setAppId(APP_ID);
			XjlTPaymentRequestModel xjl=new XjlTPaymentRequestModel();
			BeanUtils.copyProperties(dto,xjl);
			String PQ_ID=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
			xjl.setPqId(PQ_ID);
			String userId=this.getLoginUserId();
			projInfoService.addProjOrOthers(proj,projApp,bd,projQ,xjl,userId,dto.getLabels());


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

    @ApiOperation(value = "金财一期项目投管-列表", notes = "项目投管-列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getJcProjList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getJcProjList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            searchCondition.addConditionNotEqualTo("a.PROJ_STATUS", "7");
            searchCondition.setSearchBean(searchBean);
            PageInfo<NjProjInfoModel> rows = projInfoService.getJcProjList(searchCondition);
            List<NjProjInfoInternalVO> list = new ArrayList<NjProjInfoInternalVO>();
            for (NjProjInfoModel model : rows.getList()) {
                //todo累计出资是流程结束的才算还是，投前估值算哪个，（立项，还是决策）流程审批结束的值
                NjProjInfoInternalVO vo = new NjProjInfoInternalVO();
                BeanUtils.copyProperties(model,vo);
                //查询企业所属行业
                if(StringUtils.isNotEmpty(vo.getIndustry())){
                    Map<String,Object>  mapName=commTGicsService.selectByGisName(vo.getIndustry());
                    vo.setMapName(mapName);
                }
                Map<String, Object> map=projAppInfoService.selectByEiNjId(model.getProjId());
                if(map!=null){
                    vo.setMap(map);
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

    @ApiOperation(value = "项目标签-列表", notes = "项目投管-列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getEntLabelInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getEntLabelInfoList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            UserTypeEnmu userType = getLoginUserType();
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (userType.compareTo(UserTypeEnmu.EXTERNAL) == 0) {
                if (listRow != null && listRow.size() > 0) {
                    if (1005002 == listRow.get(0).getId() || 1005003 == listRow.get(0).getId()) {
                        searchCondition.addParam("USER_ID", this.getLoginUserId());
                    }
                }

            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<ProjInfoModel> rows = projInfoService.getReportList(searchCondition);
            List<projItemVO> list = new ArrayList<projItemVO>();
            for (ProjInfoModel model : rows.getList()) {
                projItemVO vo = new projItemVO();
                BeanUtils.copyProperties(model,vo);
                //查询该企业是否有此标签
                EntLabelModel entLabelModel=new EntLabelModel();
                entLabelModel.setLabelId(searchBean.getLableId());
                entLabelModel.setEntId(model.getProjObject());
                List<EntLabelModel> listTag= entLabelService.selectList(entLabelModel);
                if(listTag!=null&&!listTag.isEmpty()){
                    vo.setTag(true);
                }else{
                    vo.setTag(false);
                }
                //查询企业标签对饮的信息
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("entId",model.getProjObject());
                param.put("labelId",searchBean.getLableId());
                List<Map<String,Object>> listM=commTLabelItemService.selectByLabelInfo(param);
                vo.setListMap(listM);
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

    //
    @ApiIgnore
    @ApiOperation(value="项目管理-企业标签-详情", notes="新增合同")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/label/saveEntItem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveEntItem(@RequestBody List<LabelSearchBean> labels){
        try {
            if(labels!=null && !labels.isEmpty()){
                commTLabelItemService.saveEntItem(labels,this.getLoginUserId());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiIgnore
    @ApiOperation(value="项目管理-企业标签-删除", notes="新增合同")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/label/delEntItem", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delEntItem(@RequestBody List<LabelSearchBean> labels){
        try {
            if(labels!=null && !labels.isEmpty()){
                commTLabelItemService.delEntItem(labels,this.getLoginUserId());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }



    @ApiOperation(value="获取项目列表", notes="查询现金流记录列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getFundInveList", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String getFundInveList(@RequestBody ProjInfoSearchBean searchBean){
        try {
            if(searchBean.getKeyword()!=null){
                if("".equals(searchBean.getKeyword().trim())||searchBean.getKeyword().trim()==null){
                    searchBean.setKeyword("");
                }else{
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            searchCondition.setSearchBean(searchBean);

            List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
            if(listRow!=null&&listRow.size()>0){
                if(1005002==listRow.get(0).getId()||1005003==listRow.get(0).getId()){
                    searchCondition.addParam("userId", this.getLoginUserId());
                }
            }
            PageInfo<ProjInfoModel> rows = projInfoService.selectFundInvePage(searchCondition);
            List<projItemVO> list = new ArrayList<projItemVO>();
            for(ProjInfoModel model : rows.getList()){
                projItemVO vo = new projItemVO();
                BeanUtils.copyProperties(model,vo);

                EntLabelModel entLabelModel=new EntLabelModel();
                entLabelModel.setLabelId(searchBean.getLableId());
                entLabelModel.setEntId(model.getProjObject());
                List<EntLabelModel> listTag= entLabelService.selectList(entLabelModel);
                if(listTag!=null&&!listTag.isEmpty()){
                    vo.setTag(true);
                }else{
                    vo.setTag(false);
                }
                //查询企业标签对饮的信息
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("entId",model.getProjObject());
                param.put("labelId",searchBean.getLableId());
                List<Map<String,Object>> listM=commTLabelItemService.selectByLabelInfo(param);
                vo.setListMap(listM);
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
    @ApiOperation(value="查询统计简报", notes="查询统计简报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/inve/reportItemList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String reportItemList(@RequestParam("length") int length, @RequestParam("currPage") int currPag,String labelId,String keyword){
        try {
            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPag);
            searchCondition.addParam("labelId",labelId);
            if(StringUtils.isNotEmpty(keyword)){
                if (keyword != null && keyword != "") {
                    keyword = "'%" + keyword.trim() + "%'";
                }

            }
            searchCondition.addParam("keyWord", keyword);
            List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
            if(listRow!=null&&listRow.size()>0){
                if(1005002==listRow.get(0).getId()||1005003==listRow.get(0).getId()){
                    searchCondition.addParam("userId", this.getLoginUserId());
                }
            }
            PageInfo<ProjInfoModel> rows = projInfoService.selectSumInvePage(searchCondition);
            List<projItemVO> list = new ArrayList<projItemVO>();
            for(ProjInfoModel model : rows.getList()){
                projItemVO vo = new projItemVO();
                BeanUtils.copyProperties(model,vo);
                //查询企业标签对饮的信息
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("entId",model.getProjObject());
                param.put("labelId",labelId);
                List<Map<String,Object>> listM=commTLabelItemService.selectByLabelInfo(param);
                vo.setListMap(listM);
                list.add(vo);
            }
            dataTablesResponse.setData(list,rows);

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

//删除三级基金投项目
@ApiIgnore
@ApiOperation(value="修改", notes="修改")
@ApiImplicitParams({
        @ApiImplicitParam(name = "pqId", value = "pqId", required = true, dataType = "String",paramType = "path"),
        @ApiImplicitParam(name = "XjlTPaymentRequestDTO", value = "XjlTPaymentRequestDTO", required = true, dataType = "FundQuitApplVO")
})
@GetMapping(value = "/del/threeProj", produces = "application/json;charset=UTF-8")
@ResponseBody
public String threeProj(@RequestParam String projId,String appId){
    try {
        projInfoService.delThreeProj(projId,appId);

    } catch (Exception e) {
        logger.error(e.getMessage());
        baseResponse.error();
    }
    return JSONObject.toJSONString(baseResponse);

}

    @ApiOperation(value = "金财合盈-列表", notes = "项目投管-列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getJchyProjList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getJchyProjList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getProjSrc())) {
                List<String> projSrcs = Arrays.asList(searchBean.getProjSrc().split(","));
                searchCondition.addConditionIn("bb.PROJ_SRC", projSrcs);
            }
            if(StringUtils.isNotEmpty(searchBean.getIsDirect())){
                searchCondition.addConditionEqualTo("ff.ISDIRECT", "1");
            }
            searchCondition.addConditionEqualTo("bb.GROUP_ID", searchBean.getGroupId());
            searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
            if (StringUtils.isNotEmpty(searchBean.getProjStatus())) {
                List<String> projStatus = Arrays.asList(searchBean.getProjStatus().split(","));
                searchCondition.addConditionIn("bb.PROJ_STATUS", projStatus);
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<ProjInfoModel> rows = projInfoService.getJchyProjList(searchCondition);
            List<ProjInfoInternalVO> list = new ArrayList<ProjInfoInternalVO>();
            for (ProjInfoModel model : rows.getList()) {
                //todo累计出资是流程结束的才算还是，投前估值算哪个，（立项，还是决策）流程审批结束的值
                ProjInfoInternalVO vo = new ProjInfoInternalVO();
                BeanUtils.copyProperties( model,vo);
                //项目来源
                if (StringUtils.isNotEmpty(model.getProjSrc())) {
                    String projSrcName = codeUtils.getCodeNameByValue("229", model.getProjSrc());
                    vo.setProjSrcName(projSrcName);
                }
                //项目经理，经办人，分管副总
                Map<String, Object> mapName = projMemberService.seleByName(model.getProjId());
                vo.setMapName(mapName);
                if (model.getProjAppInfoModel() != null) {
                    vo.setStartDate(model.getProjAppInfoModel().getStartDate());
                    if (StringUtils.isNotEmpty(model.getProjAppInfoModel().getInveRole())) {
                        vo.setInveRoleName(codeUtils.getCodeNameByValue("215", model.getProjAppInfoModel().getInveRole()));
                    }
                    if (StringUtils.isNotEmpty(model.getProjAppInfoModel().getInveType())) {
                        vo.setInveTypeName(codeUtils.getCodeNameByValue("234", model.getProjAppInfoModel().getInveType()));
                    }
                }

                Double doub=xjlTPaymentRequestService.selectSumPass(model.getProjId());
                double sumInveAmount=0;
                if(doub!=null){
                    sumInveAmount=doub;
                    vo.setSumInveAmount(sumInveAmount);
                }else{
                    vo.setSumInveAmount(0.0);
                }
                //查询累计回收金额
                Double sumAmount=projReceiptQuitService.getSumAmount(model.getProjId());
                if(sumAmount!=null){
                    vo.setSumRaiseAmount(String.valueOf(sumAmount));
                }else{
                    vo.setSumRaiseAmount("0");
                }
                //收益
                BigDecimal bd2 = new BigDecimal(Double.toString(vo.getSumInveAmount()));
                BigDecimal bd1 = new BigDecimal(Double.toString(sumAmount==null?Double.valueOf(0):sumAmount));
                bd1.subtract(bd2).doubleValue();
                BigDecimal result2 = bd1.setScale(4,BigDecimal.ROUND_HALF_DOWN).subtract(bd2.setScale(4,BigDecimal.ROUND_HALF_DOWN));
                Double syAmount=result2.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
                if(syAmount!=null){
                    vo.setSumCurrentAmount(String.valueOf(syAmount));
                }else{
                    vo.setSumCurrentAmount("0");
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


    //#############################一期老系统-投资企业项目###################################################

    @ApiIgnore
    @ApiOperation(value="老系统-投资企业项目", notes="新增合同")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/proj/saveProjInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveProjInfo(@RequestBody ProjInfoEntDTO dto){
        try {
            ProjInfoModel model=new ProjInfoModel();
            BeanUtils.copyProperties( dto,model);

            ProjAppInfoModel appModel=new ProjAppInfoModel();
            BeanUtils.copyProperties( dto,appModel);
            //新增
            if(StringUtils.isEmpty(dto.getProjId())){
                //项目编号 基金编号+年+四位顺序号，顺序号不需要按照年重新编制
                //查询出资主体的编号
                String  projNo="";
                if(StringUtils.isNotEmpty(dto.getInveId())){
                    FundInfoModel  fundModel=fundInfoService.selectById(dto.getInveId());
                    if(fundModel!=null){
                        //查询基金投了多少项目
                        Integer num= fundInfoService.getSumProj(dto.getInveId());
                        String orderNo = "";
                        if(num+1<10){
                            orderNo = "000" + String.valueOf(num+1);
                        }else if(num+1<100){
                            orderNo = "00" + String.valueOf(num+1);
                        }else if(num+1<1000){
                            orderNo = "0" + String.valueOf(num+1);
                        }else{
                            orderNo = String.valueOf(num+1);
                        }
                        //年份
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        String year = sdf.format(new Date());
                        //组合项目编号
                          projNo=fundModel.getFundCode()==null?"":fundModel.getFundCode()+year+orderNo;

                    }


                }
                String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
                model.setProjNo(projNo);
                model.setProjId(projId);
                model.setCreateDt(new Date());
                model.setCreateBy(this.getLoginUserId());
                model.setUpdateDt(new Date());
                model.setUpdateBy(this.getLoginUserId());
                model.setProjType("1");
                model.setProjStatus("1");
                model.setStatus("0");
                projInfoService.saveInfoE(appModel,model);

            }else{
                model.setUpdateDt(new Date());
                model.setUpdateBy(this.getLoginUserId());
                projInfoService.updateInfoE(appModel,model);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value="一期-投资企业项目列表/投资基金列表", notes="一期-投资企业项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getInveEntList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getInveEntList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,  String fundId,String type,String projObject){
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(StringUtils.isNotEmpty(fundId)){
                searchCondition.addConditionEqualTo("a.INVE_ID",fundId );
            }
            searchCondition.addConditionNotEqualTo("a.STATUS","2");
            if(StringUtils.isNotEmpty(type)){
                searchCondition.addConditionEqualTo("a.PROJ_TYPE", type);
            }
            //查询角色       //判断是基金管理人还是
            AppUserModel appUser=  appUserService.selectById(this.getLoginUserId());
            AppUserroleModel appUserroleModel=new AppUserroleModel();
            appUserroleModel.setUserid(Long.parseLong(this.getLoginUserId()));
            List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
            //直投平台
            String typeStr="";
/*            if (appUser!=null&&5==appUser.getUsertype()) {
                for(AppUserroleModel role:listRole){

                    if(10031L==role.getRoleid()){
                        searchCondition.addConditionEqualTo("create_by", this.getLoginUserId());
                        break;
                    }
                    if(10032L==role.getRoleid()){
                        searchCondition.addConditionEqualTo("create_by", this.getLoginUserId());
                        break;
                    }
                }

            }*/




            if(StringUtils.isNotEmpty(projObject)){
                searchCondition.addConditionEqualTo("a.PROJ_OBJECT", projObject);
            }
            PageInfo<ProjInfoModel> rows = projInfoService.selectListInfoPage(searchCondition);
            List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
            for(ProjInfoModel model : rows.getList()){
                ProjInfoVO vo = new ProjInfoVO();
                BeanUtils.copyProperties( model,vo);
                if(StringUtils.isNotEmpty(model.getProjStatus())){
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("218", model.getProjStatus()));
                }
                if(StringUtils.isNotEmpty(model.getProjSrc())){
                    vo.setProjSrcName(codeUtils.getCodeNameByValue("229", model.getProjSrc()));
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

    @ApiOperation(value="一期投资企业项目-详情", notes="根据url的id来获取合伙人会议细信息")
    @ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projOrAppInfo/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projOrAppInfo(@PathVariable(value = "projId") String projId){
        try {
            ProjInfoModel proj=projInfoService.selectById(projId);
            if(StringUtils.isNotEmpty(proj.getProjSrc())){
               String projSrcName= codeUtils.getCodeNameByValue("229", proj.getProjSrc());
                mapResponse.put("projSrcName",projSrcName);
            }
            if(proj!=null){
                mapResponse.put("proj",proj);
            }else{
                ProjInfoModel mo=new  ProjInfoModel();
                mapResponse.put("proj",mo);
            }
            ProjAppInfoModel projAppInfoModel= projAppInfoService.selectById(projId);
            if(projAppInfoModel!=null){
                //投资金额币种103
                if(StringUtils.isNotEmpty(projAppInfoModel.getIntendedCurrency())){
                    String currName= codeUtils.getCodeNameByValue("103", projAppInfoModel.getIntendedCurrency());
                    mapResponse.put("intendedCurrencyName",currName);
                }
                if(StringUtils.isNotEmpty(projAppInfoModel.getPostCurrency())){
                    String postCurrencyName= codeUtils.getCodeNameByValue("103", projAppInfoModel.getPostCurrency());
                    mapResponse.put("postCurrencyName",postCurrencyName);
                }
                if(StringUtils.isNotEmpty(projAppInfoModel.getInveType())){
                    String inveTypeName= codeUtils.getCodeNameByValue("4444", projAppInfoModel.getInveType());
                    mapResponse.put("inveTypeName",inveTypeName);
                }
                if(StringUtils.isNotEmpty(projAppInfoModel.getFinaRounds())){
                    String finaRoundsName= codeUtils.getCodeNameByValue("213", projAppInfoModel.getFinaRounds());
                    mapResponse.put("finaRoundsName",finaRoundsName);
                }

                mapResponse.put("projAppInfoModel",projAppInfoModel);
            }else{
                ProjAppInfoModel moApp=new ProjAppInfoModel();
                mapResponse.put("projAppInfoModel",moApp);
                mapResponse.put("intendedCurrencyName","");
                mapResponse.put("postCurrencyName","");
                mapResponse.put("inveTypeName","");
                mapResponse.put("finaRoundsName","");
            }

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataResponse.error(e.getMessage());
        } catch (Exception e) {
            dataResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }


    @ApiIgnore
    @ApiOperation(value = "删除", notes = "基金阶段")
    @ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/proj/del", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String del(@RequestParam("projId") String projId) {
        try {
            ProjInfoModel proj=new ProjInfoModel();
            proj.setStatus("2");
            proj.setProjId(projId);
            projInfoService.update(proj);
            //projInfoService.deleteById(projId);
            //projAppInfoService.deleteById(projId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value = "校验是否是投资基金项目", notes = "基金阶段")
    @ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fund/checkFundProj", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkFundProj(@RequestParam("fundId") String fundId) {
        try {
        //查询是否是参股子基金
            ProjInfoModel model=new ProjInfoModel();
            model.setProjObject(fundId);
            model.setProjType("2");
           List<ProjInfoModel> list=projInfoService.selectList(model);
           if(list!=null&&!list.isEmpty()){
               baseResponse.setMsg("请先到母基金中维护该基金的投基金项目");
               baseResponse.setStatus("-1");
               return JSONObject.toJSONString(baseResponse);
           }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value = "校验是否是投资基金项目", notes = "基金阶段")
    @ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fund/checkDelProj", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkDelProj(@RequestParam("projId") String projId) {
        try {
            //查询是否是参股子基金
             LedgerMagModel ledgeMag=new LedgerMagModel();
            ledgeMag.setProjId(projId);
            ledgeMag.setStatus("0");
            List<LedgerMagModel> list=ledgerMagService.selectList(ledgeMag);
            if(list!=null&&!list.isEmpty()){
                baseResponse.setMsg("存在台账数据，不可删除！");
                baseResponse.setStatus("-1");
                return JSONObject.toJSONString(baseResponse);
            }
          FundQuitApplModel quit=new FundQuitApplModel();
            quit.setProjId(projId);
            List<FundQuitApplModel> listQ=fundQuitApplyService.selectList(quit);
            if(listQ!=null&&!listQ.isEmpty()){
                baseResponse.setMsg("存在项目退出数据，不可删除！");
                baseResponse.setStatus("-1");
                return JSONObject.toJSONString(baseResponse);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }


    @ApiIgnore
    @ApiOperation(value = "", notes = "基金阶段")
    @ApiImplicitParam(name = "financeId", value = "financeId", required = true, dataType = "financeId")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/proj/updateDt", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateDt(@RequestParam("projId") String projId) {
        try {
            ProjInfoModel proj=new ProjInfoModel();
            proj.setProjId(projId);
            proj.setUpdateDt(new Date());
            proj.setUpdateBy(this.getLoginUserId());
            projInfoService.update(proj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    public String  getMsgInfo(Date updateDt,String quarterOne,String quarterT,String quarter,String projId){
        //如果当前时间是第二季度
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String now= format.format(new Date());
        String upDt=format.format(updateDt);
       //判断当前时间在第一个月 的1-20号
        Integer o= now.compareTo(quarterOne);
        Integer t= now.compareTo(quarterT);
        Integer i= upDt.compareTo(quarterOne);
        Integer it= upDt.compareTo(quarterT);
        //获取年
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        DataQuarterModel dateQ=new DataQuarterModel();
        dateQ.setYear(String.valueOf(yearStr));
        if("2".equals(quarter) ){
            //说明是第一个月的1-20号
            if(o>=0 &&t <=0){
                //如果修改时间小于1号
                if(i<0){
                    dateQ.setQuarter("1");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第一季度填报已超时";
                    }

                }
            }else if(o>0&&t>0){
                //说明当前时间大于第一季度的20天,修改时间 小于 第一季度一号，则第二季度超时
                if(i<0){
                    //查询该季度是否有任务已发送
                    dateQ.setQuarter("2");
                    //把值存在这里作为参数
                    dateQ.setUserBizValue(projId);
                   List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                   if(listDateQ!=null && !listDateQ.isEmpty()){
                       return "第二季度填报已超时";
                   }

                }
            }

        }else if("3".equals(quarter) ){
            //说明是第一个月的1-20号
            if(o>=0 &&t <=0){
                //如果修改时间小于1号
                if(i<0){
                    dateQ.setQuarter("2");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第二季度填报已超时";
                    }
                }
            }else if(o>0&&t>0){

                if(i<0){
                    dateQ.setQuarter("3");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第三季度填报已超时";
                    }

                }
            }

        } else if("4".equals(quarter) ){
            //说明是第一个月的1-20号
            if(o>=0 &&t <=0){
                //如果修改时间小于1号
                if(i<0){
                    dateQ.setQuarter("3");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第三季度填报已超时";
                    }

                }
            }else if(o>0&&t>0){

                if(i<0){
                    dateQ.setQuarter("4");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第四季度填报已超时";
                    }

                }
            }

        }else if("1".equals(quarter) ){
            //说明是第一个月的1-20号
            if(o>=0 &&t <=0){
                //如果修改时间小于1号
                if(i<0){
                    dateQ.setQuarter("4");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第四季度填报已超时";
                    }

                }
            }else if(o>0&&t>0){
                if(i<0){
                    dateQ.setQuarter("1");
                    dateQ.setUserBizValue(projId);
                    List<DataQuarterModel> listDateQ= dataQuarterService.selectList("getDateList",dateQ);
                    if(listDateQ!=null && !listDateQ.isEmpty()){
                        return "第一季度填报已超时";
                    }

                }
            }

        }


        return "";

    }



    @ApiOperation(value = "现金流中，根据出资主体查询项目", notes = "外部用户列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getProjInfoForInveId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getProjInfoForInveId(@RequestParam("inveId") String inveId,String keyword,@RequestParam("pageSize") int pageSize,@RequestParam("currPage") int  currPage) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(StringUtils.isNotEmpty(keyword)){
                searchCondition.addConditionLike("proj_name","%"+keyword.trim()+"%");
            }
            searchCondition.addConditionEqualTo("inve_Id",inveId);
            PageInfo<ProjInfoModel> rows=projInfoService.selectAllProjList(searchCondition);
            //PageInfo<AppUserModel> rows = appUserService.selectUserAllListPage(searchCondition);
            List<ProjInfoVO> list = new ArrayList<>();
            for (ProjInfoModel model : rows.getList()) {
                ProjInfoVO vo = new ProjInfoVO();
                BeanUtils.copyProperties(model, vo);
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


