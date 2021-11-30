package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.annotations.Token;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.ProjInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.*;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ppmg.ei.model.EpThreemeetingInfoModel;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Scope("prototype")
@Api(tags={"APP-项目管理相关接口"})
public class AppController extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(retries=-1)
	private ProjInfoService projInfoService;

	@Reference
	private CommTVotersService commTVotersService;

	@Reference
	private CommTVoteMagService commTVoteMagService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Reference
	private AppUserService appUserService;

	@Reference(retries=-1)
	private EntBaseInfoService entBaseInfoService;

    @Reference(retries=-1)
    private ProjAppInfoService projAppInfoService;

    @Reference
    private EntLogoService entLogoService;


    @Reference
    private EpThreemeetingInfoService epThreemeetingInfoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

    //@Resource(name="otherUtil")
    //private OtherUtil otherUtil;

    @Reference
    private ProjFilesService projFilesService;

    @Reference
    private ProjMemberService projMemberService;

    @Reference
    private static EiAttachmentService eiAttachmentService;

    @Reference
    private DecisionInfoService decisionInfoService;

    @Reference
    private YhStaffForDecisionService yhStaffForDecisionService;

    @Reference
    private ScParamService scParamService;

    @Reference(retries=-1)
    private CommTCodeService commTCodeService;

    @Reference
    private LedgerMagService ledgerMagService;

    @Reference
    private FounderOaApplySealService founderOaApplySealService;

    @Reference
    private FounderOaApplyInfoService founderOaApplyInfoService;

    @Reference
    private FounderFileInfoService founderFileInfoService;



	@ApiOperation(value="APP-获取企业维度列表", notes="APP-获取企业维度列表")
	@ApiImplicitParams({

	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常")
	})
	@GetMapping(value = "/appEnteList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String appEnteList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String keyword,String groupIds,String inveIds){

		try {

            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addParam("keyword",keyword);

            List<Map<String,Object>> projIds = entBaseInfoService.selectSql2Map("SELECT wmsys.wm_concat(TM.PROJ_ID) as PROJIDS FROM TZ_T_PROJ_MEMBER TM WHERE TM.IS_PM ='2'  AND TM.APPROVE_STATUS='2' AND TM.MEMBER_ID = '"+this.getLoginUserId()+"' ");

            String projIdAlls = "" ;
            if(projIds.size()>0&&projIds.get(0)!=null){
                projIdAlls = String.valueOf(projIds.get(0).get("PROJIDS"));
            }
            searchCondition.addParam("projIdAlls",projIdAlls);

            /*管理平台查询start*/
            if(groupIds!=""&&groupIds!=null){
                List<String> groupIdsList = Arrays.asList(groupIds.split(","));
                searchCondition.addConditionIn("T1.GROUP_ID", groupIdsList);
            }
            /*管理平台查询end*/

            /*出资主体查询start*/
            if(inveIds!=""&&inveIds!=null){
                List<String> inveIdsList = Arrays.asList(inveIds.split(","));
                searchCondition.addConditionIn("T1.INVE_ID", inveIdsList);
            }
            /*出资主体查询end*/



            PageInfo<ProjInfoModel> rows = projInfoService.selectListPage("projInfogetAPPInfo",searchCondition);

            List<appEnteListVO> list = new ArrayList<appEnteListVO>();
            for(ProjInfoModel model : rows.getList()){
                appEnteListVO vo = new appEnteListVO();
                BeanUtils.copyProperties(vo, model);
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("ENT_ID",model.getProjObject());
                EntLogoModel param = new EntLogoModel();
                EntLogoModel modelLogo = entLogoService.getLogoByEntId(params);
                if(modelLogo!=null){
                    vo.setEntLogo(modelLogo.getIconBlob());
                }

                vo.setProjObject(model.getProjObject());
                vo.setProjObjectName(model.getProjObjectName());
                vo.setInveId(model.getInveId());
                vo.setInveName(model.getInveName());
                EntBaseInfoModel ent = entBaseInfoService.selectById(model.getProjObject());
                if(ent!=null){
                    vo.setSevenIndustry(ent.getSevenIndustry());
                    if(ent.getSevenIndustry()!=null&&ent.getSevenIndustry()!=""){
                        List<Map<String,Object>> mapList = entBaseInfoService.selectSql2Map("SELECT T.INDUSTRY_SEVEN,T.INDUSTRY_SEVEN_CODE FROM BASE.COMM_T_GICS_SEVEN T WHERE ID='"+ent.getSevenIndustry()+"' ");
                        if(mapList.size()>0){
                            vo.setSevenIndustryName(String.valueOf(mapList.get(0).get("INDUSTRY_SEVEN")));
                        }else{
                            vo.setSevenIndustryName("");
                        }

                    }else{
                        vo.setSevenIndustryName("");
                    }
                    vo.setScope(ent.getScope());
                }else{
                    vo.setSevenIndustryName("");
                    vo.setScope("");
                }
                if(model.getInveId()==""||model.getInveId()==null||"".equals(model.getInveId())){
                    vo.setInvestAmount("");
                    vo.setHoldShare("");
                    vo.setInveValuation("");
                    vo.setRecoveryAmount("");
                }else{
                    //出资金额
                    List<Map<String,Object>> mapList2 = entBaseInfoService.selectSql2Map(" SELECT RTrim(to_char(NVL(SUM(T2.ACTUALLOCAMT),0)/10000,'FM99999999990.9999'),'.')||' 万元' as INVEST_AMOUNT FROM XJL_T_LEDGER_MAG T2 " +
                            " left join BD_T_FUND_INFO BF ON BF.Ente_Id=T2.COMPANY_NUMBER AND BF.FUND_STS!='8' " +
                            " WHERE t2.CUSTOMER_NUM='"+model.getProjObject()+"' and BF.Fund_Id='"+model.getInveId()+"' and t2.Finance_Type='1' ");
                    if(mapList2.size()>0){
                        vo.setInvestAmount(String.valueOf(mapList2.get(0).get("INVEST_AMOUNT")));
                    }else{
                        vo.setInvestAmount("");
                    }
                    //最新占比
                    List<Map<String,Object>> mapList3 = entBaseInfoService.selectSql2Map(" SELECT RTrim(to_char(NVL(max(INVE_VALUATION) keep(dense_rank LAST ORDER BY VALU_DT),0)/10000,'FM99999999990.9999'),'.')||' 万元' AS INVE_VALUATION, " +
                            " RTrim(to_char(NVL(max(HOLD_SHARE) keep(dense_rank LAST ORDER BY VALU_DT),0),'FM99999999990.9999'),'.') AS HOLD_SHARE from EI_MY_ENTERPRISE_INFO t  " +
                            " WHERE t.enter_id='"+model.getProjObject()+"' and t.invest_id='"+model.getInveId()+"'  ");
                    if(mapList3.size()>0){
                        vo.setHoldShare(String.valueOf(mapList3.get(0).get("HOLD_SHARE"))+"%");
                        vo.setInveValuation(String.valueOf(mapList3.get(0).get("INVE_VALUATION")));
                    }else{
                        vo.setHoldShare("");
                        vo.setInveValuation("");
                    }

                    //回收金额
                    List<Map<String,Object>> mapList4 = entBaseInfoService.selectSql2Map(" SELECT RTrim(to_char(NVL(SUM(T2.ACTUALLOCAMT),0)/10000,'FM99999999990.9999'),'.')||' 万元' as RECOVERY_AMOUNT FROM XJL_T_LEDGER_MAG T2 " +
                            " left join BD_T_FUND_INFO BF ON BF.Ente_Id=T2.COMPANY_NUMBER AND BF.FUND_STS!='8' " +
                            " WHERE t2.CUSTOMER_NUM='"+model.getProjObject()+"' and BF.Fund_Id='"+model.getInveId()+"' and t2.Finance_Type='2' ");
                    if(mapList4.size()>0){
                        vo.setRecoveryAmount(String.valueOf(mapList4.get(0).get("RECOVERY_AMOUNT")));
                    }else{
                        vo.setRecoveryAmount("");
                    }
                }


                vo.setGroupId(String.valueOf(model.getGroupId()));
                String groupName = "" ;
                if(String.valueOf(model.getGroupId())!=""&&String.valueOf(model.getGroupId())!=null){
                    CommTCodeModel commTCodeModel = new CommTCodeModel();
                    commTCodeModel.setParentId("266");
                    commTCodeModel.setCodeName(String.valueOf(model.getGroupId()));
                    commTCodeModel = commTCodeService.selectBy(commTCodeModel);
                    if(commTCodeModel!=null){
                        groupName = commTCodeModel.getCodeDesc();
                    }
                }
                vo.setGroupName(groupName);
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



	@ApiOperation(value="APP-获取企业+项目基本信息", notes="根据url的企业Id来获取企业+项目基本信息")
    @ApiImplicitParams({

    })
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/entInfoDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String entInfoDetail(@RequestParam(value = "projectObject") String projectObject,@RequestParam(value = "inveId") String inveId){
		try {
			EntBaseInfoModel ent = entBaseInfoService.selectById(projectObject);
            AppEntBaseInfoListVO vo = new AppEntBaseInfoListVO();
            if(ent!=null){
                BeanUtils.copyProperties(vo, ent);

                if(ent.getSevenIndustry()!=null&&ent.getSevenIndustry()!=""){
                    List<Map<String,Object>> mapList2 = entBaseInfoService.selectSql2Map("SELECT T.INDUSTRY_SEVEN,T.INDUSTRY_SEVEN_CODE FROM BASE.COMM_T_GICS_SEVEN T WHERE ID='"+ent.getSevenIndustry()+"' ");
                    if(mapList2.size()>0){
                        vo.setSevenIndustryName(String.valueOf(mapList2.get(0).get("INDUSTRY_SEVEN")));
                    }else{
                        vo.setSevenIndustryName("");
                    }

                }else{
                    vo.setSevenIndustryName("");
                }
            }else{
                vo.setSevenIndustryName("");
            }

            ProjInfoModel projInfoModelP = new ProjInfoModel();
            projInfoModelP.setProjObject(projectObject);
            projInfoModelP.setInveId(inveId);
            projInfoModelP.setProjType("1");
            List<ProjInfoModel> projInfoList = projInfoService.selectList(projInfoModelP);

            if(projInfoList.size()>0){
                vo.setCurrentOperationSituation(projInfoList.get(0).getCurrentOperationSituation());
                vo.setInveEvaluation(projInfoList.get(0).getInveEvaluation());
                vo.setEmail(projInfoList.get(0).getEmail());
            }else{
                vo.setCurrentOperationSituation("");
                vo.setInveEvaluation("");
                vo.setEmail("");
            }

            vo.setInvestmentSituationYH("投资情况正常");



			mapResponse.put("model", vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}



    @ApiOperation(value="APP-获取项目维度列表（通过出资主体ID+出资主体ID+平台ID;或者没有参数）", notes="APP-获取项目维度列表")
    @ApiImplicitParams({

    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常")
    })
    @GetMapping(value = "/appProjBeforeList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String appProjBeforeList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String projectObject,String inveId,String groupId,String keyword,
                                    String groupIds,String projStatuss,String projMembers,String inveIds){

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(projectObject!=""&&projectObject!=null){
                searchCondition.addConditionEqualTo("PROJ_OBJECT", projectObject);
            }
            if(inveId!=""&&inveId!=null){
                searchCondition.addConditionEqualTo("INVE_ID", inveId);
            }
            if(groupId!=""&&groupId!=null){
                searchCondition.addConditionEqualTo("P.GROUP_ID", groupId);
            }

            /*管理平台查询start*/
            if(groupIds!=""&&groupIds!=null){
                List<String> groupIdsList = Arrays.asList(groupIds.split(","));
                searchCondition.addConditionIn("P.GROUP_ID", groupIdsList);
            }
            /*管理平台查询end*/

            /*出资主体查询start*/
            if(inveIds!=""&&inveIds!=null){
                List<String> inveIdsList = Arrays.asList(inveIds.split(","));
                searchCondition.addConditionIn("P.INVE_ID", inveIdsList);
            }
            /*出资主体查询end*/

            /*项目状态查询start*/
            if(projStatuss!=""&&projStatuss!=null){
                List<String> projStatussList = Arrays.asList(projStatuss.split(","));
                searchCondition.addConditionIn("P.PROJ_STATUS", projStatussList);
            }
            /*项目状态查询end*/

            /*项目经理查询start*/
            if(projMembers!=""&&projMembers!=null){
                searchCondition.addParam("projMembers",projMembers);
            }
            /*项目经理查询end*/

            searchCondition.addConditionEqualTo("PROJ_TYPE", "1");
            searchCondition.addConditionNotEqualTo("PROJ_STATUS", "7");
            searchCondition.addParam("keyword",keyword);

            List<Map<String,Object>> projIds = entBaseInfoService.selectSql2Map("SELECT wmsys.wm_concat(TM.PROJ_ID) as PROJIDS FROM TZ_T_PROJ_MEMBER TM WHERE TM.IS_PM ='2'  AND TM.APPROVE_STATUS='2' AND TM.MEMBER_ID = '"+this.getLoginUserId()+"' ");

            String projIdAlls = "" ;
            if(projIds.size()>0&&projIds.get(0)!=null){
                projIdAlls = String.valueOf(projIds.get(0).get("PROJIDS"));
            }
            searchCondition.addParam("projIdAlls",projIdAlls);

            //PageInfo<ProjInfoModel> rows = projInfoService.selectListPage(searchCondition);

            PageInfo<ProjInfoModel> rows = projInfoService.selectListPage("projInfogetAPPInfoXMall",searchCondition);

            List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
            for(ProjInfoModel model : rows.getList()){
                ProjInfoVO vo = new ProjInfoVO();
                //BeanUtils.copyProperties(vo, model);
                vo.setProjId(model.getProjId());
                vo.setProjName(model.getProjName());
                vo.setProjObject(model.getProjObject());
                vo.setProjObjectName(model.getProjObjectName());
                vo.setInveId(model.getInveId());
                vo.setInveName(model.getInveName());

                String projStatusName= "";
                if(model.getProjStatus()!=""&&model.getProjStatus()!=null){
                    projStatusName = codeUtils.getCodeNameByValue("216", model.getProjStatus());//项目状态NAME
                }

                vo.setProjStatusName(projStatusName);



                long groupId2 = model.getGroupId();
                String groupName = "" ;
                if(String.valueOf(model.getGroupId())!=""&&String.valueOf(model.getGroupId())!=null){
                    CommTCodeModel commTCodeModel = new CommTCodeModel();
                    commTCodeModel.setParentId("266");
                    commTCodeModel.setCodeName(String.valueOf(model.getGroupId()));
                    commTCodeModel = commTCodeService.selectBy(commTCodeModel);
                    if(commTCodeModel!=null){
                        groupName = commTCodeModel.getCodeDesc();
                    }
                }

                vo.setGroupId(groupId2);
                vo.setGroupName(groupName);

                ProjMemberModel memberModel = new ProjMemberModel();
                memberModel.setProjId(model.getProjId());
                memberModel.setIsPm("1");
                ProjMemberModel memberModel1 = projMemberService.selectBy(memberModel);//项目经理
                if(memberModel1!=null){
                    vo.setMangerName(memberModel1.getMemberName());
                }

                memberModel.setIsPm("0");
                List<ProjMemberModel> memberModel0 = projMemberService.selectList(memberModel);//项目成员
                if(memberModel0!=null){
                    List<String> MemberNames = new ArrayList<String>();
                    for(int i=0;i<memberModel0.size();i++){
                        MemberNames.add(memberModel0.get(i).getMemberName());

                    }
                    vo.setMemberName(String.join(",", MemberNames));
                }

                vo.setDecisionDt(model.getDecisionDt());
                //首次出资时间+累计出资金额
                List<Map<String,Object>> mapList = entBaseInfoService.selectSql2Map(" select min(A.APP_DT) as APP_DT,RTrim(to_char(NVL(SUM(A.RMB_INVE_AMOUNT),0)/10000,'FM99999999990.9999'),'.')||' 万元' as INVEST_AMOUNT  " +
                        "FROM XJL_T_PAYMENT_REQUEST A WHERE A.INVEST_PAYMENT_TYPE = '3' AND A.FINANCE_TYPE='1' " +
                        " AND (A.STATUS='4' or A.STATUS='5') AND A.PROJECT_OR_FUND_ID='"+model.getProjId()+"'  ");
                if(mapList.size()>0){
                    if(mapList.get(0).containsKey("APP_DT")){
                        if(mapList.get(0).get("APP_DT")!=null&&mapList.get(0).get("APP_DT")!=""){
                            vo.setFirstCZDt(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(mapList.get(0).get("APP_DT"))));
                        }else{
                            vo.setFirstCZDt(null);
                        }
                    }else{
                        vo.setFirstCZDt(null);
                    }
                    vo.setAllRequestMoney(String.valueOf(mapList.get(0).get("INVEST_AMOUNT")));
                }else{
                    vo.setFirstCZDt(null);
                    vo.setAllRequestMoney("");
                }


                //首次退出时间+累计退出金额
                List<Map<String,Object>> mapList2 = entBaseInfoService.selectSql2Map(" select min(A.QUIT_DT) as QUIT_DT,RTrim(to_char(NVL(SUM(A.RMB_COST),0)/10000,'FM99999999990.9999'),'.')||' 万元' AS QUIT_MONEY " +
                        "FROM TZ_T_PROJ_QUIT_APPL A WHERE A.STATUS = '4'  " +
                        " AND A.OBJECT_ID='"+model.getProjObject()+"' AND A.AFTER_FUNDER_ID='"+model.getInveId()+"' ");
                if(mapList2.size()>0){
                    if(mapList2.get(0).containsKey("QUIT_DT")){
                        if(mapList2.get(0).get("QUIT_DT")!=null&&mapList2.get(0).get("QUIT_DT")!=""){
                            vo.setQuitDt(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(mapList2.get(0).get("QUIT_DT"))));
                        }else{
                            vo.setQuitDt(null);
                        }
                    }else{
                        vo.setQuitDt(null);
                    }

                    vo.setQuitMoney(String.valueOf(mapList2.get(0).get("QUIT_MONEY")));
                }else{
                    vo.setQuitDt(null);
                    vo.setQuitMoney("");
                }

                //占股比-合同投前
                List<Map<String,Object>> mapList3 = entBaseInfoService.selectSql2Map(" SELECT DECODE(T.SHARE_RATIO,'','',RTrim(To_Char(T.SHARE_RATIO,'FM99999999990.9999'),'.')) as SHARE_RATIO " +
                        "FROM  TZ_T_PROJ_CONTRACT_FILE T  WHERE T.PROJ_ID='"+model.getProjId()+"' AND T.FILE_TYPE='1' " +
                        "AND T.STATUS='3' AND T.FIRST_PARTY IN ('1','2') ORDER BY T.CREATE_DT DESC ");
                if(mapList3.size()>0){
                    if(mapList3.get(0)!=null){
                            vo.setHoldShare(String.valueOf(mapList3.get(0).get("SHARE_RATIO")));
                    }else{
                        vo.setHoldShare("");
                    }
                }else{
                    vo.setHoldShare("");
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



    @ApiOperation(value="APP-获取项目基本信息", notes="根据url的项目Id来获取项目基本信息")
    @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projInfoDetailApp/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projInfoDetailApp(@PathVariable(value = "projId") String projId){
        try {

            ProjInfoModel projInfoModel2 = projInfoService.selectById(projId);
            ProjInfoVO vo = new ProjInfoVO();
            BeanUtils.copyProperties(vo, projInfoModel2);

            String projStatusName= "";
            if(vo.getProjStatus()!=""&&vo.getProjStatus()!=null){
                projStatusName = codeUtils.getCodeNameByValue("216", vo.getProjStatus());//项目状态NAME
            }

            vo.setProjStatusName(projStatusName);

            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value="APP-获取立项信息", notes="根据url的项目Id来获取立项信息")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projAppInfoDetailApp/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projAppInfoDetailApp(@PathVariable(value = "projId") String projId){
        try {
            ProjInfoModel projInfoModel2 = projInfoService.selectById(projId);
            ProjAppInfoModel projAppInfoModel2 = projAppInfoService.selectById(projId);

            ProjAppInfoAppVO vo = new ProjAppInfoAppVO();
            BeanUtils.copyProperties(vo, projAppInfoModel2);
            String InveId = projInfoModel2.getInveId();
            String InveName = projInfoModel2.getInveName();
            Date setupDt = projInfoModel2.getSetupDt();
            if(projInfoModel2.getProjSrc() != null && projInfoModel2.getProjSrc() != ""){//项目表项目来源
                vo.setProjSrc(codeUtils.getCodeNameByValue("229", projInfoModel2.getProjSrc()));
            }else{
                vo.setProjSrc("");

            }
            if(vo.getFinaRounds() != null && vo.getFinaRounds() != ""){//投资轮次ABC
                vo.setFinaRounds(codeUtils.getCodeNameByValue("213", vo.getFinaRounds()));
            }else{
                vo.setFinaRounds("");

            }
            if(vo.getFinaTimes() != null && vo.getFinaTimes() != ""){//投资轮次数字
                vo.setFinaTimes(codeUtils.getCodeNameByValue("214", vo.getFinaTimes()));
            }else{
                vo.setFinaTimes("");
            }

            if(vo.getInveRole()!= null && vo.getInveRole() != ""){//投资角色
                vo.setInveRole(codeUtils.getCodeNameByValue("215", vo.getInveRole()));
            }else{
                vo.setFinaTimes("");
            }

            if(vo.getInveType() != null && vo.getInveType() != ""){//投资类型
                vo.setInveType(codeUtils.getCodeNameByValue("234", vo.getInveType()));
            }else{
                vo.setFinaTimes("");
            }//otherUtil.ClobToString(vo.getMajorMatter());//clob转string

            //立项附件-多个，
            ProjFilesModel projFilesModelP = new ProjFilesModel();
            projFilesModelP.setProjId(projId);
            projFilesModelP.setFileType("1");
            List<ProjFilesModel> projFilesList = projFilesService.selectList(projFilesModelP);

            List<EiTAttachmentModel> allFiles = new ArrayList<>();
            for(int i=0;i<projFilesList.size();i++){
                String fileAtta = projFilesList.get(i).getFileAtta();
                EiTAttachmentModel eiTAttachmentModel = new EiTAttachmentModel();
                eiTAttachmentModel.setFieldToken(fileAtta);

                List<EiTAttachmentModel> listEiAtt = eiAttachmentService.selectList(eiTAttachmentModel);
                for(int j=0;j<listEiAtt.size();j++){

                    String fileSizeS = "" ;
                    Long fileSizeL = Long.parseLong(listEiAtt.get(j).getFileSize());
                    if(fileSizeL<1024){
                        fileSizeS = String.valueOf(fileSizeL)+"B";
                    }else if((fileSizeL/1024)<1024){
                        fileSizeS = String.valueOf(fileSizeL/1024)+"KB";
                    }else if((fileSizeL/1024/1024)<1024){
                        fileSizeS = String.valueOf(fileSizeL/1024/1024)+"M";
                    }
                    listEiAtt.get(j).setFileSize(fileSizeS);

                    allFiles.add(listEiAtt.get(j));
                }
            }

            vo.setInveId(InveId);
            vo.setInveName(InveName);
            vo.setSetupDt(setupDt);
            vo.setFileLists(allFiles);

            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value="APP-获取项目决策信息", notes="根据url的项目Id来获取项目决策信息")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projDecisionInfoDetailApp/{projId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String projDecisionInfoDetailApp(@PathVariable(value = "projId") String projId){
        try {
            ProjInfoModel projInfoModel2 = projInfoService.selectById(projId);
            DecisionInfoModel decisionInfoModel2 = decisionInfoService.selectById(projId);

            ProjDecisionInfoAppVO vo = new ProjDecisionInfoAppVO();
            BeanUtils.copyProperties(vo, decisionInfoModel2);
            String InveId = projInfoModel2.getInveId();
            String InveName = projInfoModel2.getInveName();
            Date decisionDt = projInfoModel2.getDecisionDt();

            ProjAppInfoModel projAppInfoModel2 = projAppInfoService.selectById(projId);
            double IntendedAmount = projAppInfoModel2.getIntendedAmount();
            String IntendedCurrency = projAppInfoModel2.getIntendedCurrency();


            //决策附件-多个，
            ProjFilesModel projFilesModelP = new ProjFilesModel();
            projFilesModelP.setProjId(projId);
            projFilesModelP.setFileType("2");
            List<ProjFilesModel> projFilesList = projFilesService.selectList(projFilesModelP);

            List<EiTAttachmentModel> allFiles = new ArrayList<>();
            for(int i=0;i<projFilesList.size();i++){
                String fileAtta = projFilesList.get(i).getFileAtta();
                EiTAttachmentModel eiTAttachmentModel = new EiTAttachmentModel();
                eiTAttachmentModel.setFieldToken(fileAtta);

                List<EiTAttachmentModel> listEiAtt = eiAttachmentService.selectList(eiTAttachmentModel);
                for(int j=0;j<listEiAtt.size();j++){

                        String fileSizeS = "" ;
                        Long fileSizeL = Long.parseLong(listEiAtt.get(j).getFileSize());
                        if(fileSizeL<1024){
                            fileSizeS = String.valueOf(fileSizeL)+"B";
                        }else if((fileSizeL/1024)<1024){
                            fileSizeS = String.valueOf(fileSizeL/1024)+"KB";
                        }else if((fileSizeL/1024/1024)<1024){
                            fileSizeS = String.valueOf(fileSizeL/1024/1024)+"M";
                        }
                        listEiAtt.get(j).setFileSize(fileSizeS);

                    allFiles.add(listEiAtt.get(j));
                }
            }


            YhStaffForDecisionModel yhStaffForDecisionModel = new YhStaffForDecisionModel();
            yhStaffForDecisionModel.setProjId(projId);
            yhStaffForDecisionModel.setStatus("0");
            yhStaffForDecisionModel.setJobEndDate(null);
            List<YhStaffForDecisionModel> yhStaffForDecisionList = yhStaffForDecisionService.selectList(yhStaffForDecisionModel);//IS_DIRECTOR：董事；IS_SUPERVISOR：监事；IS_TOP_MANAGE：高管；

            vo.setInveId(InveId);
            vo.setInveName(InveName);
            vo.setIntendedAmount(IntendedAmount);
            vo.setIntendedCurrency(IntendedCurrency);

            vo.setDecisionDt(decisionDt);
            vo.setFileLists(allFiles);
            vo.setDJGLists(yhStaffForDecisionList);//董监高列表

            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    @ApiOperation(value="APP-获取质量评估列表", notes="APP-获取质量评估列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/scParamAppList", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String scParamAppList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projId") String projId){

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionNotEqualTo("P.STATUS", "9");
            searchCondition.addParam("PROJ_ID", projId);
            PageInfo<ScParamModel> rows = scParamService.selectListPage(searchCondition);
            List<ScParamListVO> list = new ArrayList<ScParamListVO>();
            for(ScParamModel model : rows.getList()){
                ScParamListVO vo = new ScParamListVO();
                BeanUtils.copyProperties(vo, model);
                String cusType = "" ;
                if(vo.getCusType()!= null && vo.getCusType() != ""){//投资阶段
                    if("新团队".equals(vo.getCusType())){
                        cusType = "1" ;
                    }else if("老团队".equals(vo.getCusType())){
                        cusType = "2" ;
                    }else{
                        cusType = "3" ;
                    }

                    vo.setCusType(codeUtils.getCodeNameByValue("3000", cusType));
                }else{
                    vo.setCusType("");
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




    @ApiOperation(value="APP-现金流列表", notes="现金流列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/ledgerMagAppList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ledgerMagAppList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String projectObject,String inveId,String entId){
        try {


            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if(projectObject!=""&&projectObject!=null){
                searchCondition.addParam("projectObject", projectObject);
            }
            if(inveId!=""&&inveId!=null){
                searchCondition.addParam("inveId", inveId);
            }
            if(entId!=""&&entId!=null){
                searchCondition.addConditionEqualTo("COMPANY_NUMBER",entId);
            }

            PageInfo<LedgerMagModel> rows = ledgerMagService.selectListPage("LedgerMaggetAPPxjl",searchCondition);
            List<LedgerMagVO> list = new ArrayList<LedgerMagVO>();
            for(LedgerMagModel model : rows.getList()){
                LedgerMagVO vo = new LedgerMagVO();

                String investPaymentType = "";
                if(model.getInvestPaymentType()!=""&&model.getInvestPaymentType()!=null){
                    investPaymentType = codeUtils.getCodeNameByValue("61000", model.getInvestPaymentType());//投资支付类型（1投资人，2基金，3项目，4平台）,61000
                }
                String financeType = "";
                if(model.getFinanceType()!=""&&model.getFinanceType()!=null){
                    financeType = codeUtils.getCodeNameByValue("62000", model.getFinanceType());//资金类型（1出资，2入资）,62000
                }
                String recBillTypeNum = "";
                if(model.getRecBillTypeNum()!=""&&model.getRecBillTypeNum()!=null){
                    if("999".equals(String.valueOf(model.getRecBillTypeNum()))){
                        recBillTypeNum = "其他" ;
                    }else{
                        recBillTypeNum = codeUtils.getCodeNameByValue("242", model.getRecBillTypeNum());//收款类型（10其他）
                    }
                }


                BeanUtils.copyProperties(vo, model);
                vo.setInvestPaymentType(investPaymentType);
                vo.setFinanceType(financeType);
                vo.setRecBillTypeNum(recBillTypeNum);
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



    @ApiOperation(value="APP-上市公司公告用印", notes="APP-上市公司公告用印")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/founderOaApplySealList", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String founderOaApplySealList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("projectObject") String projectObject,@RequestParam("inveId") String inveId,@RequestParam("groupId") String groupId){

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("OBJECT_ID",projectObject);
            searchCondition.addConditionEqualTo("AFTER_FUNDER_ID",inveId);
            searchCondition.addConditionEqualTo("GROUP_ID",groupId);
            searchCondition.addConditionIsNotNull("IS_NOTICE");

            PageInfo<FounderOaApplySealModel> rows = founderOaApplySealService.selectListPage(searchCondition);
            List<FounderOaApplySealVO> list = new ArrayList<FounderOaApplySealVO>();
            for(FounderOaApplySealModel model : rows.getList()){
                FounderOaApplySealVO vo = new FounderOaApplySealVO();
                BeanUtils.copyProperties(vo, model);
                String applyNo = model.getApplyNo();
                FounderOaApplyInfoModel OaApplyInfo = founderOaApplyInfoService.selectById(applyNo);
                if(OaApplyInfo!=null){
                    vo.setApplyTime(OaApplyInfo.getApplyTime());
                    vo.setApplyName(OaApplyInfo.getApplyName());
                }
                vo.setTypeNmae(codeUtils.getCodeNameByValue("9006", vo.getType()));

                if(vo.getAttachment()!= null && vo.getAttachment()!=  ""){
                    FounderFileInfoModel eiTAttachmentModel = new FounderFileInfoModel();
                    eiTAttachmentModel.setFieldToken(vo.getAttachment());
                    List<EiTAttachmentModel> listEiAtt = founderFileInfoService.selectList(eiTAttachmentModel);
                    for(int i=0;i<listEiAtt.size();i++){
                        String fileSizeS = "" ;
                        Long fileSizeL = Long.parseLong(listEiAtt.get(i).getFileSize());
                        if(fileSizeL<1024){
                            fileSizeS = String.valueOf(fileSizeL)+"B";
                        }else if((fileSizeL/1024)<1024){
                            fileSizeS = String.valueOf(fileSizeL/1024)+"KB";
                        }else if((fileSizeL/1024/1024)<1024){
                            fileSizeS = String.valueOf(fileSizeL/1024/1024)+"M";
                        }
                        listEiAtt.get(i).setFileSize(fileSizeS);
                    }
                    vo.setFileLists(listEiAtt);
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



    @ApiOperation(value="APP新增项目-EI", notes="根据ProjInfoDTO对象创建项目与项目经理")
    @ApiImplicitParam(name = "ProjInfoDTO", value = "项目表实体ProjInfoDTO", required = true, dataType = "ProjInfoDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/projectInfoApp/add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestBody ProjInfoDTO dto){

        try {
            ProjInfoModel model = new ProjInfoModel();
            dto.setProjType("1");

            BeanUtils.copyProperties(model, dto);
            String memberId = dto.getUserId();
            String memberName = dto.getUserName();
            String orgId = dto.getOrgId();
            projInfoService.insertProjInfoAndMember(model,memberId,memberName,this.getLoginUserId(),orgId);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value="通过groupId查找出资主体", notes="通过groupId查找出资主体")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projectInfo/getInves", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getInves(String groupIds){
        try {
            List<Map<String,Object>> newInvesList = new ArrayList<Map<String,Object>>();
            if(groupIds!=""&&groupIds!=null){
                String sql1 = "SELECT T1.INVE_ID ID,T1.INVE_NAME NAME FROM EI.TZ_T_PROJ_INFO T1 " +
                        "      WHERE T1.PROJ_TYPE='1' AND T1.PROJ_STATUS!='7' AND T1.INVE_ID IS NOT NULL " +
                        "      AND T1.GROUP_ID IN ("+groupIds+") GROUP BY T1.INVE_ID,T1.INVE_NAME ";
                List<Map<String,Object>> invesList = projInfoService.selectSql2Map(sql1);

                for(int i = 0;i<invesList.size();i++){
                    Map<String,Object> newHash = new HashMap<String,Object>();
                    newHash.put("value",invesList.get(i).get("ID"));
                    newHash.put("label",invesList.get(i).get("NAME"));
                    newInvesList.add(newHash);
                }

            }

            mapResponse.put("options", newInvesList);


        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value="通过当前登录人查询项目经理", notes="通过当前登录人查询项目经理")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projectInfo/getMember", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMember(){
        try {

            String sql1 = "SELECT DISTINCT T.MEMBER_ID,T.MEMBER_NAME  "
                    + " FROM EI.TZ_T_PROJ_MEMBER T "
                    + " LEFT JOIN EI.TZ_T_PROJ_INFO T1 ON T1.PROJ_ID=T.PROJ_ID AND T1.PROJ_TYPE='1'  "
                    + " where T.IS_PM='1' AND T.MEMBER_ID IS NOT NULL "
                    + " AND T1.GROUP_ID IN (select "
                    + " substr(t.sql_sta,instr(t.sql_sta,'\"')+1,instr(t.sql_sta,'\"',instr(t.sql_sta,'\"')+1)-instr(t.sql_sta,'\"')-1) "
                    + " from BASE.APP_PERMISSION t "
                    + " WHERE T.ID IN "
                    + " (SELECT RP.PERMISSIONID FROM BASE.APP_ROLEPERMISSION RP "
                    + " WHERE RP.ROLEID IN( "
                    + " select ur.roleid from BASE.APP_USERROLE ur where to_char(ur.userid)='"+this.getLoginUserId()+"')) "
                    + " and T.ID IN (10021,10022,10023,10024,10025,10026,"
                    + " 10031,10032,10029,10030,10201,10202,10198,10199,10200)) AND T1.PROJ_STATUS!='7'";
            List<Map<String,Object>> invesList = projInfoService.selectSql2Map(sql1);
            List<Map<String,Object>> newInvesList = new ArrayList<Map<String,Object>>();
            for(int i = 0;i<invesList.size();i++){
                Map<String,Object> newHash = new HashMap<String,Object>();
                newHash.put("value",invesList.get(i).get("MEMBER_ID"));
                newHash.put("label",invesList.get(i).get("MEMBER_NAME"));
                newInvesList.add(newHash);
            }

            mapResponse.put("options", newInvesList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value="通过当前登录人判断是否为股权平台（YSE：是；NO：不是；）", notes="通过当前登录人判断是否为股权平台")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projectInfo/isGQ", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String isGQ(){
        try {

            String sql1 = "SELECT U.ID FROM BASE.APP_USER U   "
                    + " LEFT JOIN BASE.APP_USERROLE UR ON UR.USERID=U.ID "
                    + " WHERE UR.ROLEID IN (110371,110370,110369,110372) AND U.ID="+this.getLoginUserId()+"";
            List<Map<String,Object>> invesList = projInfoService.selectSql2Map(sql1);
            String isGQ = "NO" ;
            if(invesList.size()>0){
                isGQ="YES";
            }

            mapResponse.put("isGQ", isGQ);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
    }


}

