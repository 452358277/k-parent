package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.util.Encrypt;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.enumeration.UserTypeEnmu;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.GovernmentFundSearchBean;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.dto.FundInfoCgDTO;
import com.ppmg.ei.dto.FundInfoDTO;
import com.ppmg.ei.dto.FundInfoProjDTO;
import com.ppmg.ei.dto.ProjInfoEntDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.*;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags = {"基金接口"})
public class FundInfoController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private FundInfoService fundInfoService;

    @Reference
    private ProjInfoService projInfoService;

    @Reference
    private FundInvcomService fundInvcomService;

    @Reference
    private FundShareInfoService fundShareInfoService;

    @Reference
    private FounderOaApplyContractService founderOaApplyContractService;

    @Reference
    private FundIrrService fundIrrService;

    @Reference
    private FundInveDescService fundInveDescService;

    @Reference
    private FundMemberService fundMemberService;

    @Reference
    private AppUserService appUserService;

    @Reference
    private FundMcInfoService fundMcInfoService;

    @Reference
    private FundShareItemService fundShareItemService;

    @Reference
    private FundUserRelateService fundUserRelateService;

    @Reference
    private MeetingRecordService meetingRecordService;

    @Reference
    private InveInfoService inveInfoService;

    @Reference
    private BankInfoService bankInfoService;

    @Reference
    private XjlTPaymentRequestService xjlTPaymentRequestService;

    @Reference
    private ProjContractFileService projContractFileService;

    @Reference
    private ProjAppInfoService projAppInfoService;

    @Reference
    private AppRoleService appRoleService;

    @Reference(retries = -1)
    private EntBaseInfoService entBaseInfoService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    @Reference(check = false)
    private SerialnoService serialnoService;

    @Reference
    private FundRecordInfoService fundRecordInfoService;

    @Reference
    private AppUserroleService appUserroleService;


    @Reference
    private FundViewService fundViewService;

    @Reference
    private EnteInfoService enteInfoService;

    @ApiOperation(value = "获取平台管理基金列表", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("groupId") String groupId,
                               String fundName, String fundStruct, String platProp, String fundSts, String fundSrc, String fundGenre, String subPlatProp, String ledgeMagType, String isFit) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            if (StringUtils.isNotEmpty(fundSrc) && "1".equals(ledgeMagType)) {
                searchCondition.addParam("ledgeMagType", ledgeMagType);
            }
            if (StringUtils.isNotEmpty(fundSrc) && !"1".equals(ledgeMagType)) {
                searchCondition.addConditionEqualTo("FUND_SRC", fundSrc);
            }
            //searchCondition.addConditionNotEqualTo("FUND_ID","0");
            if (StringUtils.isNotEmpty(fundGenre)) {
                List<String> fundGenres = Arrays.asList(fundGenre.split(","));
                searchCondition.addConditionIn("FUND_GENRE", fundGenres);
            }
            if (StringUtils.isNotEmpty(subPlatProp)) {
                List<String> subPlatProps = Arrays.asList(subPlatProp.split(","));
                searchCondition.addConditionIn("SUB_PLAT_PROP", subPlatProps);
            }
            if (StringUtils.isNotEmpty(isFit)) {
                List<String> isFitS = Arrays.asList(isFit.split(","));
                searchCondition.addConditionIn("IS_FIT", isFitS);
            }
            if (fundName != null && !"".equals(fundName)) {
                fundName = "'%" + fundName + "%'";
                searchCondition.addParam("keyWord", fundName);
            }
            if (fundStruct != null && !"".equals(fundStruct)) {
                fundStruct.replace(",", "','");
                fundStruct = "'" + fundStruct + "'";
                searchCondition.addParam("FUND_STRUCT", fundStruct);
            }
            if (platProp != null && !"".equals(platProp)) {
                searchCondition.addParam("PLAT_PROP", platProp);
            }
            if (fundSts != null && !"".equals(fundSts)) {
                fundSts.replace(",", "','");
                fundSts = "'" + fundSts + "'";
                searchCondition.addParam("FUND_STS", fundSts);
            }
            //searchCondition.addParam("USER_ID", "1005000");
            UserTypeEnmu userType = getLoginUserType();
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (userType.compareTo(UserTypeEnmu.EXTERNAL) == 0) {
                if (listRow != null && listRow.size() > 0) {
                    if (1005002 == listRow.get(0).getId() || 1005003 == listRow.get(0).getId()) {
                        searchCondition.addParam("USER_ID", this.getLoginUserId());
                    } else {
                        searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
                        searchCondition.addParam("GROUP_ID", groupId);
                    }
                } else {
                    searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
                    searchCondition.addParam("GROUP_ID", groupId);
                }

            } else {
                //searchCondition.addParam("USER_ID",this.getLoginUserId());
                searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
                searchCondition.addParam("GROUP_ID", groupId);
            }
            PageInfo<FundInfoModel> rows = fundInfoService.selectListPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                //判断是否特殊的基金
                if (StringUtils.isNotEmpty(model.getIsExcept())) {
                    //查询这个基金投了
                    if ("1".equals(model.getIsExcept())) {
                        //特殊的基金只统计三级项目，四级投项目
                        Map<String, Object> map = projInfoService.selectSum(model.getFundId());
                        if (map.get("AMOUNT") != null) {
                            vo.setActualAmount((String) map.get("AMOUNT"));
                        }
                        if (map.get("SUMCOUNT") != null) {
                            vo.setProjNum(String.valueOf(map.get("SUMCOUNT")));
                        }

                    }
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    String codeName = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                    vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(model.getPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204", model.getPlatProp());
                    vo.setPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(model.getSubPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204" + model.getPlatProp(), model.getSubPlatProp());
                    vo.setSubPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
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

    @ApiOperation(value = "获取基金投资项目列表", notes = "获取基金投资项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getProjListByFundId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getProjListByFundId(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId) {

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            String[] status = {"5", "9", "11", "13", "14"};
            List<String> statusList = new ArrayList<String>();
            for (int i = 0; i < status.length; i++) {
                statusList.add(status[i]);
            }
            searchCondition.addConditionIn("PROJ_STATUS", statusList);
            searchCondition.addParam("INVE_ID", fundId);
//            searchCondition.addConditionEqualTo("INVE_ID",fundId);
            PageInfo<ProjInfoModel> rows = projInfoService.selectListPage(searchCondition);
            List<ProjListByFundVO> list = new ArrayList<ProjListByFundVO>();
            String userId = this.getLoginUserId();

            for (ProjInfoModel model : rows.getList()) {
                ProjListByFundVO vo = new ProjListByFundVO();
                BeanUtils.copyProperties(vo, model);
                vo.setFundId(fundId);
                if (model.getProjStatus() != null && !"".equals(model.getProjStatus())) {
                    vo.setProjStatusName(codeUtils.getCodeNameByValue("216", model.getProjStatus()));
                }
                if ("1".equals(model.getProjType())) {
                    vo.setProjTypeName("直投");
                } else if ("2".equals(model.getProjType())) {
                    vo.setProjTypeName("基金");
                }
				/*if(model.getProjAppInfoModel() != null){
					BeanUtils.copyProperties(vo, model.getProjAppInfoModel());
					if(model.getProjAppInfoModel().getIntendedCurrency() != null){
						vo.setIntendedCurrency(codeUtils.getCodeNameByValue("103", model.getProjAppInfoModel().getIntendedCurrency()));
					}
				}*/
				/*if(model.getProjAppInfoModel() != null && model.getProjAppInfoModel().getIntendedAmount() != null){
					Double double1 = model.getProjAppInfoModel().getIntendedAmount();
					DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
					String double2 = decimalFormat.format(double1);
					vo.setIntendedAmount(double2);
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


    @ApiOperation(value = "根据登录人获取基金列表", notes = "根据登录人获取基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoListByUserId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoListByUserId(@RequestParam("length") int length, @RequestParam("currPage") int currPage,
                                       @RequestParam("userId") String userId, String platProp, String keyWord) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("FUND_SRC", "1");
            searchCondition.addConditionNotEqualTo("FUND_STS", "8");
            if (platProp != null && platProp.contains(",")) {
                platProp = platProp.replace(",", "','");
                platProp = "'" + platProp + "'";
            }
            searchCondition.addParam("PLAT_PROP", platProp);
            if (keyWord != null && keyWord != "") {
                keyWord = "'%" + keyWord + "%'";
            }
            searchCondition.addParam("keyWord", keyWord);
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
            PageInfo<FundInfoModel> rows = fundInfoService.selectListPage(searchCondition);
            List<FundListVO> list = new ArrayList<FundListVO>();
            for (FundInfoModel model : rows.getList()) {
                FundListVO vo = new FundListVO();
                BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value = "获取基金基本信息", notes = "根据url的基金Id来获取基金基本信息")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoDetail/{fundId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoDetail(@PathVariable(value = "fundId") String fundId, String projId) {
        try {
            FundInfoModel model = fundInfoService.selectById(fundId);
            FundInveDescModel fundInveDescModel = fundInveDescService.selectById(fundId);
            FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
            fundShareInfoModel.setFundId(fundId);
            fundShareInfoModel.setIsDelete("0");
            List<FundShareInfoModel> shareInfoModels = fundShareInfoService.selectList(fundShareInfoModel);
            FundMemberModel mem = new FundMemberModel();
            mem.setFundId(fundId);
            List<FundMemberModel> fundMemberList = fundMemberService.selectList(mem);
            FundInfoVO vo = new FundInfoVO();
            BeanUtils.copyProperties(vo, model);
            if (StringUtils.isNotEmpty(projId)) {
                ProjInfoModel projInfoModel = projInfoService.selectById(projId);
                if (projInfoModel != null && StringUtils.isNotEmpty(projInfoModel.getProjStatus())) {
                    if ("1".equals(projInfoModel.getGroupId()) && "10008".equals(model.getMcId())) {
                        vo.setProjStatusName(codeUtils.getCodeNameByValue("216", projInfoModel.getProjStatus()));
                    } else {
                        vo.setProjStatusName(codeUtils.getCodeNameByValue("218", projInfoModel.getProjStatus()));
                    }

                }
                vo.setProjInfo(projInfoModel);
            }
            if (StringUtils.isNotEmpty(projId)) {
                ProjAppInfoModel projAppInfoModel = projAppInfoService.selectById(projId);
                vo.setProjAppInfo(projAppInfoModel);
            }
            if (StringUtils.isNotEmpty(model.getInveType())) {
                String inveTypeName = codeUtils.getCodeNameByValue("4444", model.getInveType());
                vo.setInveTypeName(StringUtils.isEmpty(inveTypeName) ? "" : inveTypeName);
            }
            if (StringUtils.isNotEmpty(model.getFundCategory())) {
                String fundCategoryName = codeUtils.getCodeNameByValue("9002", model.getFundCategory());
                vo.setFundCategoryName(StringUtils.isEmpty(fundCategoryName) ? "" : fundCategoryName);
            }
            //管理部门
            if (StringUtils.isNotEmpty(model.getManDep())) {
                String manDepName = codeUtils.getCodeNameByValue("90020", model.getManDep());
                vo.setManDepName(StringUtils.isEmpty(manDepName) ? "" : manDepName);
            }
            //批准设立部门
            if (StringUtils.isNotEmpty(model.getAppDep())) {
                String appDepName = codeUtils.getCodeNameByValue("90005", model.getAppDep());
                vo.setAppDepName(StringUtils.isEmpty(appDepName) ? "" : appDepName);
            }
            if (StringUtils.isNotEmpty(model.getCurrentCurrency())) {
                String currentCurrencyName = codeUtils.getCodeNameByValue("103", model.getCurrentCurrency());
                vo.setCurrentCurrencyName(StringUtils.isEmpty(currentCurrencyName) ? "" : currentCurrencyName);
            }
            //省级资金来源
            if (StringUtils.isNotEmpty(model.getGovFundSrc())) {
                String govFundSrcName = codeUtils.getCodeNameByValue("90019", model.getGovFundSrc());
                vo.setGovFundSrcName(StringUtils.isEmpty(govFundSrcName) ? "" : govFundSrcName);
            }
            if (StringUtils.isNotEmpty(model.getFundsType())) {
                String fundsTypeName = codeUtils.getCodeNameByValue("10018", model.getFundsType());
                vo.setFundsTypeName(StringUtils.isEmpty(fundsTypeName) ? "" : fundsTypeName);
            }
            if (model != null && StringUtils.isNotEmpty(model.getParentId())) {
                FundInfoModel info = fundInfoService.selectById(model.getParentId());
                if (info != null) {
                    vo.setParentName(info.getFundName());
                }
            }

            if (model != null && StringUtils.isNotEmpty(model.getEnteId())) {
                EntBaseInfoModel entBaseInfo = entBaseInfoService.selectById(model.getEnteId());
                vo.setEntBaseInfoModel(entBaseInfo);

            }
            //查询母基金投资金额，基金数
            if (StringUtils.isNotBlank(model.getFundSts())) {
                vo.setFundStsName(codeUtils.getCodeNameByValue("216", model.getFundSts()));
            }

            if (StringUtils.isNotBlank(model.getOldSubPlatProp())) {
                vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("12306", model.getOldSubPlatProp()));
            }
            Map<String, Object> map = fundInfoService.selectFundIdList(fundId);
            if (map != null) {
                String countGs = String.valueOf(map.get("COUNTGS"));
                String AMOUNT = String.valueOf(map.get("AMOUNT"));
                if (StringUtils.isNotBlank(AMOUNT)) {
                    vo.setAmount(AMOUNT + "万元人民币");
                } else {
                    vo.setAmount("0" + "万元人民币");
                }

                vo.setCountGs(countGs);
            }
            if (fundInveDescModel == null) {
                FundInveDescModel fundShareInfoModel1 = new FundInveDescModel();
                vo.setFundInveDescModel(fundShareInfoModel1);
            } else {
                vo.setFundInveDescModel(fundInveDescModel);
            }
            vo.setFundMemberList(fundMemberList);
            if (shareInfoModels != null && shareInfoModels.size() > 0) {
                for (FundShareInfoModel shareInfoModel : shareInfoModels) {
                    if (StringUtils.isNotEmpty(shareInfoModel.getCapitalSource())) {
                        String codeName = codeUtils.getCodeNameByValue("1000", shareInfoModel.getCapitalSource());
                        shareInfoModel.setCapitalSourceName(StringUtils.isEmpty(codeName) ? "" : codeName);
                    }
                    if (StringUtils.isNotEmpty(shareInfoModel.getInveType())) {
                        String codeName = codeUtils.getCodeNameByValue("1001", shareInfoModel.getInveType());
                        shareInfoModel.setInveTypeName(StringUtils.isEmpty(codeName) ? "" : codeName);
                    }
                    if (StringUtils.isNotEmpty(shareInfoModel.getInveRole())) {
                        String codeName = codeUtils.getCodeNameByValue("207", shareInfoModel.getInveRole());
                        shareInfoModel.setInveRoleName(StringUtils.isEmpty(codeName) ? "" : codeName);
                    }
                }

            }
            //查询托管行对应的id
            if (StringUtils.isNotEmpty(model.getCustodianBank())) {
                BankInfoModel bankInfoModel = new BankInfoModel();
                bankInfoModel.setBankName(model.getCustodianBank());
                BankInfoModel bankInfo = bankInfoService.selectBy(bankInfoModel);
                if (bankInfo != null) {
                    vo.setCustodianBankId(bankInfo.getBankId());
                }
            }

            vo.setShareInfoList(shareInfoModels);

            if (StringUtils.isNotEmpty(model.getFundStruct())) {
                String codeName = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
            }
            if (StringUtils.isNotEmpty(model.getPlatProp())) {
                String codeName = codeUtils.getCodeNameByValue("204", model.getPlatProp());
                vo.setPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
            }
            if (StringUtils.isNotEmpty(model.getSubPlatProp())) {
                String codeName = codeUtils.getCodeNameByValue("204" + model.getPlatProp(), model.getSubPlatProp());
                vo.setSubPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
            }
            if (StringUtils.isNotEmpty(vo.getFundGenre())) {
                vo.setFundGenreName(codeUtils.getCodeNameByValue("1011", model.getFundGenre()));
            }

            if (StringUtils.isNotEmpty(vo.getIsCustodian())) {
                vo.setIsCustodianName(codeUtils.getCodeNameByValue("102", model.getIsCustodian()));
            }

            if (StringUtils.isNotEmpty(vo.getIsAuth())) {
                vo.setIsAuthName(codeUtils.getCodeNameByValue("102", model.getIsAuth()));
            }
            //查询出资人代办
     /*       FundViewModel viewModel=new FundViewModel();
            viewModel.setFundId(fundId);
            List<FundViewModel> listV=fundViewService.selectList(viewModel);
            String adminNames="";
            String adminIds="";
             for(FundViewModel fundViewModel:listV){
                 if(StringUtils.isNotEmpty(fundViewModel.getAdminName())){
                     if(StringUtils.isNotEmpty(adminNames)){
                         adminNames=adminNames+","+fundViewModel.getAdminName();
                     }else{
                         adminNames=adminNames;
                     }
                 }
                 if(StringUtils.isNotEmpty(fundViewModel.getAdminId())){
                     if(StringUtils.isNotEmpty(adminIds)){
                         adminIds=adminIds+","+fundViewModel.getAdminId();
                     }else{
                         adminIds=adminIds;
                     }
                 }
             }*/
         /*   vo.setAdminNames(adminNames);
            vo.setAdminIds(adminIds);
            vo.setListView(listV);*/
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value = "获取基金内部机构列表", notes = "获取基金内部机构列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInvcomInfoByFundId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInvcomInfoByFundId(@RequestParam("fundId") String fundId) {
        try {
            List<FundInvcomModel> rows = fundInvcomService.getFundInvcomInfoByFundId(fundId);
            List<FundInvcomVO> list = new ArrayList<FundInvcomVO>();
            for (FundInvcomModel model : rows) {
                FundInvcomVO vo = new FundInvcomVO();
                BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value = "获取基金认缴信息列表", notes = "获取基金认缴信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundShareInfoListByFundId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundShareInfoListByFundId(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("T.STATUS", "0");
            searchCondition.addParam("FUND_ID", fundId);
            PageInfo<FundShareInfoModel> rows = fundShareInfoService.selectListPage(searchCondition);
            List<FundShareInfoVO> list = new ArrayList<FundShareInfoVO>();
            for (FundShareInfoModel model : rows.getList()) {
                FundShareInfoVO vo = new FundShareInfoVO();
                BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value = "获取基金投资信息", notes = "根据url的基金Id来获取基金投资信息")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInvestInfoDetail/{fundId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInvestInfoDetail(@PathVariable(value = "fundId") String fundId) {
        try {
            FundInfoModel model = fundInfoService.getFundInvestInfoByFundId(fundId);
            FundInvestInfoVO vo = new FundInvestInfoVO();
			/*vo.setInvestingCount("2");
			vo.setThisYearInvestAmount("23,333.33万元");
			vo.setThisYearInvestCount("1");
			vo.setTotalInvestAmount("23369.33万元");
			vo.setTotalInvestCount("15");*/
            BeanUtils.copyProperties(vo, model);

            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value = "获取基金投资项目列表", notes = "获取基金投资项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInvestListByFundId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInvestListByFundId(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId) {

        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            String[] status = {"5", "9", "11", "13", "14"};
            List<String> statusList = new ArrayList<String>();
            for (int i = 0; i < status.length; i++) {
                statusList.add(status[i]);
            }
            String[] type = {"1", "2"};
            List<String> typeList = new ArrayList<String>();
            for (int i = 0; i < type.length; i++) {
                typeList.add(type[i]);
            }
            searchCondition.addConditionIn("PROJ_STATUS", statusList);
            searchCondition.addConditionIn("PROJ_TYPE", typeList);
            searchCondition.addConditionEqualTo("INVE_ID", fundId);
            PageInfo<ProjInfoModel> rows = projInfoService.selectListPageByFundId(searchCondition);
            List<FundInvestProjListVO> list = new ArrayList<FundInvestProjListVO>();
            String userId = this.getLoginUserId();

            for (ProjInfoModel model : rows.getList()) {
                FundInvestProjListVO vo = new FundInvestProjListVO();
                BeanUtils.copyProperties(vo, model);
                vo.setFundId(fundId);
                vo.setProjTypeName(codeUtils.getCodeNameByValue("600112", model.getProjType()));
                vo.setProjStatusName(codeUtils.getCodeNameByValue("216", model.getProjStatus()));
//				vo.setProjId("15245");
//				vo.setProjName("润新111");
//				vo.setProjType("1");
//				vo.setProjStatusName("已决策");
//				vo.setIntendedAmount("400.00 万元 人民币");
//				vo.setSignAmount("400.00 万元 人民币");
//				vo.setSignDt(new Date());
                list.add(vo);

//			FundInvestProjListVO vo1 = new FundInvestProjListVO();
//			vo1.setProjId("11320");
//			vo1.setFundId(fundId);
//			vo1.setProjName("0125元禾原点项目");
//			vo1.setProjType("2");
//			vo1.setProjTypeName("投基金");
//			vo1.setProjStatusName("已决策");
//			vo1.setIntendedAmount("500.00 万元 人民币");
//			vo1.setSignAmount("500.00 万元 人民币");
//			vo1.setSignDt(new Date());
//			list.add(vo1);
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

    @ApiOperation(value = "基金业绩列表", notes = "基金业绩列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundIrrList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundIrrList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            PageInfo<FundIrrModel> rows = fundIrrService.selectListPage(searchCondition);
            List<FundIrrVO> list = new ArrayList<FundIrrVO>();
//			for(FundIrrModel model : rows.getList()){
            FundIrrVO vo = new FundIrrVO();
            vo.setDPI(5.2);
            vo.setFundIrr(3.4);
            vo.setPIC(2.2);
            vo.setTVPI(6.3);
            vo.setInvestAmount(1700.2);
            vo.setBackAmount(1400.8);
            vo.setInveValuation(1980.5);
            vo.setValuDt(new Date());
//				BeanUtils.copyProperties(vo, model);
            list.add(vo);

            FundIrrVO vo1 = new FundIrrVO();
            vo1.setDPI(3.2);
            vo1.setFundIrr(1.4);
            vo1.setPIC(2.2);
            vo1.setTVPI(4.3);
            vo1.setInvestAmount(1200.2);
            vo1.setBackAmount(1100.8);
            vo1.setInveValuation(1380.5);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            vo1.setValuDt(sdf.parse("2017-09-30"));
            list.add(vo1);
            FundIrrVO vo2 = new FundIrrVO();
            vo2.setDPI(2.2);
            vo2.setFundIrr(6.4);
            vo2.setPIC(3.2);
            vo2.setTVPI(8.3);
            vo2.setInvestAmount(1500.2);
            vo2.setBackAmount(1000.8);
            vo2.setInveValuation(1190.5);
            vo2.setValuDt(sdf.parse("2017-01-30"));
            list.add(vo2);
//			}

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

    @ApiOperation(value = "保存基金基本信息", notes = "保存基金基本信息")
    @ApiImplicitParam(name = "FundInfoDTO", value = "保存基金基本信息实体FundInfoDTO", required = true, dataType = "FundInfoDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/saveFundInfo", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveFundInfo(@RequestBody FundInfoDTO dto) {
        try {
            FundInfoModel model = new FundInfoModel();
            BeanUtils.copyProperties(model, dto);
            if ("0".equals(dto.getIsRecord())) {
                model.setRecordCode("");
            }
            if (StringUtils.isEmpty(dto.getFundId())) {
                //查询基金是否存在
                FundInfoModel fundInfoModel = new FundInfoModel();
                fundInfoModel.setFundName(dto.getFundName());
                fundInfoModel.setFundSrc(dto.getFundSrc());
                List<FundInfoModel> list = fundInfoService.selectList(fundInfoModel);
                if (list != null && list.size() > 0) {
                    baseResponse.setMsg("基金已存在！");
                    return JSONObject.toJSONString(baseResponse);
                }
                //基金序列自动生成
                String fundCode = "";
                if (StringUtils.isNotEmpty(dto.getPlatProp())) {
                    if ("1".equals(dto.getPlatProp())) {
                        //查询数据库的序列排到什么了
                        fundCode = "%" + "NQ" + "%";
                        List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                        if (listFundCode != null && listFundCode.size() > 0) {
                            String sub = listFundCode.get(0).getFundCode();
                            if (StringUtils.isNotEmpty(sub)) {
                                if (Integer.parseInt(sub.substring(2)) <= 8) {
                                    model.setFundCode("N0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                                } else {
                                    model.setFundCode("NQ" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                                }
                            }

                        } else {
                            model.setFundCode("NQ01");
                        }
                    } else if ("2".equals(dto.getPlatProp())) {
                        fundCode = "%" + "NG" + "%";
                        List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                        if (listFundCode != null && listFundCode.size() > 0) {
                            String sub = listFundCode.get(0).getFundCode();
                            if (StringUtils.isNotEmpty(sub)) {
                                if (Integer.parseInt(sub.substring(2)) <= 8) {
                                    model.setFundCode("NG0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                                } else {
                                    model.setFundCode("NG" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                                }

                            }
                        } else {
                            model.setFundCode("NG01");
                        }
                    } else if ("3".equals(dto.getPlatProp())) {
                        fundCode = "%" + "NC" + "%";
                        List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                        if (listFundCode != null && listFundCode.size() > 0) {
                            String sub = listFundCode.get(0).getFundCode();
                            if (StringUtils.isNotEmpty(sub)) {
                                if (Integer.parseInt(sub.substring(2)) <= 8) {
                                    model.setFundCode("NC0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                                } else {
                                    model.setFundCode("NC" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                                }

                            }

                        } else {
                            model.setFundCode("NC01");
                        }
                    }

                }
             /*   FundInfoModel fundInfoModel1=new FundInfoModel();
                fundInfoModel1.setFundCode(dto.getFundCode());
                FundInfoModel fundInfoModel2=fundInfoService.selectBy(fundInfoModel1);
                if(fundInfoModel2!=null){
                    baseResponse.setMsg("基金编号已存在！");
                    return	JSONObject.toJSONString(baseResponse);
                }*/

            } else {
                //查询基金是否存在
                FundInfoModel fundInfoModel = new FundInfoModel();
                fundInfoModel.setFundName(dto.getFundName());
                List<FundInfoModel> list = fundInfoService.selectList(fundInfoModel);
                FundInfoModel fInfo = fundInfoService.selectById(dto.getFundId());
                if (fInfo != null) {
                    if (!fInfo.getFundName().equals(dto.getFundName())) {
                        if (list != null && list.size() > 0) {
                            baseResponse.setMsg("基金已存在！");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                }



                //编辑的时候
              /*  FundInfoModel fundInfoModel3=fundInfoService.selectById(dto.getFundId());
                if(fundInfoModel3!=null){
                    if(fundInfoModel3.getFundCode()!=null){
                        if(!fundInfoModel3.getFundCode().equals(dto.getFundCode())){
                            FundInfoModel fundInfoModel6=new FundInfoModel();
                            fundInfoModel6.setFundCode(dto.getFundCode());
                            FundInfoModel fundInfoModel7=fundInfoService.selectBy(fundInfoModel6);
                            if(fundInfoModel7!=null){
                                baseResponse.setMsg("基金编号已存在！");
                                return	JSONObject.toJSONString(baseResponse);
                            }
                        }
                    }else{
                        FundInfoModel fundInfoModel6=new FundInfoModel();
                        if(StringUtils.isNotEmpty(dto.getFundCode())){
                            fundInfoModel6.setFundCode(dto.getFundCode());
                            FundInfoModel fundInfoModel7=fundInfoService.selectBy(fundInfoModel6);
                            if(fundInfoModel7!=null){
                                baseResponse.setMsg("基金编号已存在！");
                                return	JSONObject.toJSONString(baseResponse);
                            }
                        }

                    }

                }*/

            }
            String userId = this.getLoginUserId();
            //String userId = "1005000";
            String password = Encrypt.DataEncrypt("888888");
            model.setCurrentCurrency("1");
            fundInfoService.updateFundInfo(model, userId, password);
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "江苏省政府投资基金基本情况表", notes = "江苏省政府投资基金基本情况表列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/governmentFundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String governmentFundList(@RequestBody GovernmentFundSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getIsRecord())) {
                List<String> isRecode = Arrays.asList(searchBean.getIsRecord().split(","));
                searchCondition.addConditionIn("a.IS_RECORD", isRecode);
            }
            if (StringUtils.isNotEmpty(searchBean.getPlatProp())) {
                List<String> platProp = Arrays.asList(searchBean.getPlatProp().split(","));
                searchCondition.addConditionIn("a.PLAT_PROP", platProp);
            }
            if (StringUtils.isNotEmpty(searchBean.getFundStruct())) {
                List<String> fundStruct = Arrays.asList(searchBean.getFundStruct().split(","));
                searchCondition.addConditionIn("a.FUND_STRUCT", fundStruct);
            }
            if (StringUtils.isNotEmpty(searchBean.getSubPlatProp())) {
                List<String> subPlatProps = Arrays.asList(searchBean.getSubPlatProp().split(","));
                searchCondition.addConditionIn("a.SUB_PLAT_PROP", subPlatProps);
            }
            if (StringUtils.isNotEmpty(searchBean.getFundGenre())) {
                List<String> fundGenre = Arrays.asList(searchBean.getFundGenre().split(","));
                searchCondition.addConditionIn("a.FUND_GENRE", fundGenre);
            }
            searchCondition.setSearchBean(searchBean);

            if (StringUtils.isNotEmpty(searchBean.getYear()) || StringUtils.isNotEmpty(searchBean.getQuarter())) {
                //选了年份和季度
                if (StringUtils.isNotEmpty(searchBean.getQuarter())) {
                    List<String> quarter = Arrays.asList(searchBean.getQuarter().split(","));
                    searchCondition.addConditionIn("hh.QUARTER", quarter);
                }
                if (StringUtils.isNotEmpty(searchBean.getYear())) {
                    searchCondition.addConditionEqualTo("hh.YEAR", searchBean.getYear());
                }
                if ("1".equals(searchBean.getType())) {
                    //选中5个特殊字段
                    searchCondition.addParam("type", "10");
                } else {
                    searchCondition.addParam("type", "2");
                }

            } else {
                //没选年度和季度，选了5个特殊字段，多个出资人信息
                if ("1".equals(searchBean.getType())) {
                    //选中5个特殊字段
                    searchCondition.addParam("type", "3");
                } else {
                    //不选年度和季度，资产和费用信息 取第一条
                    searchCondition.addParam("type", "0");
                }
            }


            PageInfo<FundResultModel> rows = fundInfoService.selectgovernmentFundListPage(searchCondition);
            List<FundInfoRestVO> list = new ArrayList<FundInfoRestVO>();
            for (FundResultModel model : rows.getList()) {
                FundInfoRestVO vo = new FundInfoRestVO();
                BeanUtils.copyProperties(vo, model);
                if (StringUtils.isNotEmpty(model.getQuarter())) {
                    vo.setQuarterName(codeUtils.getCodeNameByValue("2233", model.getQuarter()));
                }
                //基金形式
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    vo.setFundStructName(codeUtils.getCodeNameByValue("210", model.getFundStruct()));
                }
                if (StringUtils.isNotEmpty(model.getFundGenre())) {
                    vo.setFundGenreName(codeUtils.getCodeNameByValue("1011", model.getFundGenre()));
                }
                //基金类别
                if (StringUtils.isNotEmpty(model.getPlatProp())) {
                    vo.setPlatPropName(codeUtils.getCodeNameByValue("204", model.getPlatProp()));
                }
                if (StringUtils.isNotEmpty(model.getIsRecord())) {
                    if ("0".equals(model.getIsRecord())) {
                        vo.setRecordCode("");
                        vo.setRecordDate(null);
                    }
                    vo.setIsRecord(codeUtils.getCodeNameByValue("248", model.getIsRecord()));
                }
                if (StringUtils.isNotEmpty(model.getSubPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204" + model.getPlatProp(), model.getSubPlatProp());
                    if (StringUtils.isNotEmpty(codeName)) {
                        vo.setPlatPropName(vo.getPlatPropName() + "-" + codeName);
                    } else {
                        vo.setPlatPropName(vo.getPlatPropName());
                    }

                }
                if (StringUtils.isNotEmpty(model.getMcId())) {
                    FundMcInfoModel mc = fundMcInfoService.selectById(model.getMcId());
                    if (mc != null) {
                        vo.setRegisterNo(mc.getRegisterNo());
                    }
                }
                FundInveDescModel fundInveDescModel = fundInveDescService.selectById(model.getFundId());
                if (fundInveDescModel != null) {
                    vo.setFundInveDescModel(fundInveDescModel);
                }
                //根据fundId查询查询省政府投资 基金认缴规模
                FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
                fundShareInfoModel.setFundId(model.getFundId());
                fundShareInfoModel.setEnteId("0");
                List<FundShareInfoModel> fund = fundShareInfoService.selectList(fundShareInfoModel);
                if (fund != null && fund.size() > 0) {
                    vo.setFundShareInfoModel(fund.get(0));
                } else {
                    FundShareInfoModel fundShareInfoModel1 = new FundShareInfoModel();
                    fundShareInfoModel1.setFundId(model.getFundId());
                    fundShareInfoModel1.setEnteId("50");
                    List<FundShareInfoModel> fund1 = fundShareInfoService.selectList(fundShareInfoModel1);
                    if (fund1 != null && fund1.size() > 0) {
                        vo.setFundShareInfoModel(fund1.get(0));
                    }

                }

                //根据fundId查询基金管理人
                FundUserRelateModel fundUserRelateModel = new FundUserRelateModel();
                fundUserRelateModel.setFundId(model.getFundId());
                //List<FundUserRelateModel> listUserRelate = fundUserRelateService.selectList(fundUserRelateModel);

                MeetingRecordModel meetingRecordModel = new MeetingRecordModel();
                meetingRecordModel.setObjectId(model.getEnteId());
                Calendar calendar = Calendar.getInstance();
                int year1 = calendar.get(Calendar.YEAR);
                if (StringUtils.isNotEmpty(model.getEnteId())) {
                    List<MeetingRecordModel> meetingRecordList = meetingRecordService.selectByTimeList(model.getEnteId(), String.valueOf(year1));
                    if (meetingRecordList.size() > 0) {
                        vo.setIszkhy("是");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        if (meetingRecordList.get(0).getMeetingDate() != null) {
                            String dateString = formatter.format(meetingRecordList.get(0).getMeetingDate());
                            vo.setMeetingDate(dateString);
                        }
                        if (meetingRecordList.size() > 1) {
                            //最新的会议时间
                            String dateString1 = formatter.format(meetingRecordList.get(1).getMeetingDate());
                            vo.setOldMeetingDate(dateString1);
                        } else {
                            //最早会议时间
                            String dateString1 = formatter.format(meetingRecordList.get(0).getMeetingDate());
                            vo.setOldMeetingDate(dateString1);
                        }

                    } else {
                        vo.setIszkhy("否");
                        vo.setMeetingDate(null);
                    }

                }
                //查询出资人
                FundShareInfoModel fundShareInfoModel1 = new FundShareInfoModel();
                fundShareInfoModel1.setFundId(model.getFundId());
                List<FundShareInfoModel> listm = fundShareInfoService.selectListByEntId(fundShareInfoModel1);
                if (listm != null && listm.size() > 0) {
                    vo.setGs(listm.size());
                    //出资人名
          /*          StringBuffer investorName = new StringBuffer();
                    for (FundShareInfoModel m : listm) {
                        InveInfoModel mc = inveInfoService.selectById(m.getInvestorId());
                        if (mc != null) {
                            if (investorName != null && investorName.length() > 0) {
                                investorName.append("," + mc.getInvestorName());

                            } else {
                                investorName.append(mc.getInvestorName());
                            }
                        }
                    }
                    vo.setInvestorName(investorName.toString());*/
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

    @ApiOperation(value = "江苏省政府投资基金下设基金投资情况表", notes = "江苏省政府投资基金下设基金投资情况表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/provincialList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String provincialList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getProjName() != null) {
                if ("".equals(searchBean.getProjName().trim()) || searchBean.getProjName().trim() == null) {
                    searchBean.setProjName("");
                } else {
                    searchBean.setProjName("%" + searchBean.getProjName().trim() + "%");
                }
            }
            if (searchBean.getRegName() != null) {
                if ("".equals(searchBean.getRegName().trim()) || searchBean.getRegName().trim() == null) {
                    searchBean.setRegName("");
                } else {
                    searchBean.setRegName("%" + searchBean.getRegName().trim() + "%");
                }
            }
            if (searchBean.getFundName() != null) {
                if ("".equals(searchBean.getFundName().trim()) || searchBean.getFundName().trim() == null) {
                    searchBean.setFundName("");
                } else {
                    searchBean.setFundName("%" + searchBean.getFundName().trim() + "%");
                }
            }
            if (searchBean.getFundId() != null) {
                if ("".equals(searchBean.getFundId().trim()) || searchBean.getFundId().trim() == null) {
                    searchBean.setFundId("");
                } else {
                    searchBean.setFundId(searchBean.getFundId().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getLabel())) {
                searchCondition.addParam("label", searchBean.getLabel());
            }
            if (StringUtils.isNotEmpty(searchBean.getFundGenre())) {
                searchCondition.addParam("fundGenre", searchBean.getFundGenre());
            }
            if (StringUtils.isNotEmpty(searchBean.getIsJs())) {
                List<String> ids = Arrays.asList(searchBean.getIsJs().split(","));
                searchBean.setIds(ids);
            }

            if (StringUtils.isNotEmpty(searchBean.getPlatProp())) {
                List<String> PLAT_PROP = Arrays.asList(searchBean.getPlatProp().split(","));
                searchBean.setPlat(PLAT_PROP);
            }

            if (StringUtils.isNotEmpty(searchBean.getFundStruct())) {
                List<String> fundStruct = Arrays.asList(searchBean.getFundStruct().split(","));
                searchBean.setFundSt(fundStruct);
            }
            if (StringUtils.isNotEmpty(searchBean.getSubPlatProp())) {
                List<String> subPlatProp = Arrays.asList(searchBean.getSubPlatProp().split(","));
                searchBean.setSubPlat(subPlatProp);
            }
            if (StringUtils.isNotEmpty(searchBean.getFundGenre())) {
                List<String> fundGener = Arrays.asList(searchBean.getFundGenre().split(","));
                searchBean.setFundGenres(fundGener);
            }

            //查询用户的角色
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (listRow != null && listRow.size() > 0) {
                if (listRow.get(0).getId() == 1005002) {
                    searchCondition.addParam("USER_ID", this.getLoginUserId());
                }
            }
            searchCondition.setSearchBean(searchBean);

            PageInfo<Map<String, Object>> rows = projInfoService.projectGovementList(searchCondition);
            //PageInfo<Map<String,Object>> rows = projInfoService.projectInvestedList(searchCondition);
            List<ProvincialVO> list = new ArrayList<ProvincialVO>();
            for (Map m : rows.getList()) {
                ProvincialVO vo = new ProvincialVO();
                vo.setName((String) m.get("REG_NAME"));
                vo.setFundId(((String) m.get("FUND_ID")));
                vo.setEnteId(((String) m.get("ENTE_ID")));
                FundInfoModel fundInfoModel1 = new FundInfoModel();
                fundInfoModel1.setRegName(((String) m.get("REG_NAME")));
                fundInfoModel1.setFundId(((String) m.get("FUND_ID")));
                if (StringUtils.isNotEmpty(((String) m.get("FUND_ID")))) {
                    FundInfoModel fundInfo = fundInfoService.selectById(fundInfoModel1.getFundId());
                    if (fundInfo != null) {
                        vo.setPlatProp(fundInfo.getPlatProp());
                        vo.setSubPlatProp(fundInfo.getSubPlatProp());
                        if (StringUtils.isNotEmpty(fundInfo.getFundStruct())) {
                            if (StringUtils.isNotEmpty(fundInfo.getFundStruct())) {
                                String codeName = codeUtils.getCodeNameByValue("210", fundInfo.getFundStruct());
                                vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                            }
                            if (StringUtils.isNotEmpty(fundInfo.getPlatProp())) {
                                String codeName = codeUtils.getCodeNameByValue("204", fundInfo.getPlatProp());
                                vo.setPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                            }
                            if (StringUtils.isNotEmpty(fundInfo.getSubPlatProp())) {
                                String codeName = codeUtils.getCodeNameByValue("204" + fundInfo.getPlatProp(), fundInfo.getSubPlatProp());
                                vo.setSubPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                            }
                        }

                    }
                }
                //根据基金id查询企业类型的值
                if (m.get("FUND_ID") != null) {
                    //Map<String,Object> mapLabel=projInfoService.selectCountLavel((String)m.get("FUND_ID"));
                    if (m.get("ONEGXJSQY") != null) {
                        vo.setOneGxjsqy(Integer.parseInt(m.get("ONEGXJSQY").toString()));
                    } else {
                        vo.setOneGxjsqy(0);
                    }
                    if (m.get("ONEYDYL") != null) {
                        vo.setOneYdyl(Integer.parseInt(m.get("ONEYDYL").toString()));
                    } else {
                        vo.setOneYdyl(0);
                    }
                    if (m.get("ONEZXQY") != null) {
                        vo.setOneZxqy(Integer.parseInt(m.get("ONEZXQY").toString()));
                    } else {
                        vo.setOneZlxx(0);
                    }
                    if (m.get("ONEZLXX") != null) {
                        vo.setOneZlxx(Integer.parseInt(m.get("ONEZLXX").toString()));
                    } else {
                        vo.setOneZlxx(0);
                    }
                    if (m.get("ONEMY") != null) {
                        vo.setOneMy(Integer.parseInt(m.get("ONEMY").toString()));
                    } else {
                        vo.setOneMy(0);
                    }

                    if (m.get("FOURGXJSQY") != null) {
                        vo.setFourGxjsqy(Integer.parseInt(m.get("FOURGXJSQY").toString()));
                    } else {
                        vo.setFourGxjsqy(0);
                    }
                    if (m.get("FOURYDYL") != null) {
                        vo.setFourYdyl(Integer.parseInt(m.get("FOURYDYL").toString()));
                    } else {
                        vo.setFourYdyl(0);
                    }
                    if (m.get("FOURZXQY") != null) {
                        vo.setFourZxqy(Integer.parseInt(m.get("FOURZXQY").toString()));
                    } else {
                        vo.setFourZxqy(0);
                    }
                    if (m.get("FOURZLXX") != null) {
                        vo.setFourZlxx(Integer.parseInt(m.get("FOURZLXX").toString()));
                    } else {
                        vo.setFourZlxx(0);
                    }
                    if (m.get("FOURMY") != null) {
                        vo.setFourMy(Integer.parseInt(m.get("FOURMY").toString()));
                    } else {
                        vo.setFourMy(0);
                    }
                }
                if (m.get("FUND_GENRE") != null) {
                    if (StringUtils.isNotEmpty(m.get("FUND_GENRE").toString())) {
                        String fundGenre = codeUtils.getCodeNameByValue("1011", m.get("FUND_GENRE").toString());
                        vo.setFundGenreName(StringUtils.isEmpty(fundGenre) ? "" : fundGenre);
                    }
                }

                if (m.get("ONECOUNT") != null) {
                    vo.setOneCount(Integer.parseInt(m.get("ONECOUNT").toString()));
                } else {
                    vo.setOneCount(0);
                }
                if (m.get("ONECOUNTJS") != null) {
                    vo.setOneCountJs(Integer.parseInt(m.get("ONECOUNTJS").toString()));
                } else {
                    vo.setOneCountJs(0);
                }
                if (m.get("ONEFINAAMOUNT") != null) {
                    vo.setOneFinaAmount(Double.parseDouble(m.get("ONEFINAAMOUNT").toString()));
                } else {
                    vo.setOneFinaAmount(Double.valueOf(0));
                }
                if (m.get("ONEINTENDEDAMOUNT") != null) {
                    vo.setOneIntendedAmount(Double.parseDouble(m.get("ONEINTENDEDAMOUNT").toString()));
                } else {
                    vo.setOneIntendedAmount(Double.valueOf(0));
                }
                if (m.get("ONEACTUALAMOUNT") != null) {
                    vo.setOneActualAmount(Double.parseDouble(m.get("ONEACTUALAMOUNT").toString()));
                } else {
                    vo.setOneActualAmount(Double.valueOf(0));
                }

                if (m.get("ONEPREMONEY") != null) {
                    vo.setOnepreMoney(Double.parseDouble(m.get("ONEPREMONEY").toString()));
                } else {
                    vo.setOnepreMoney(Double.valueOf(0));
                }
                if (m.get("ONEPOSTMONEY") != null) {
                    vo.setOnePostMoney(Double.parseDouble(m.get("ONEPOSTMONEY").toString()));
                } else {
                    vo.setOnePostMoney(Double.valueOf(0));
                }

                if (m.get("TWOCOUNT") != null) {
                    vo.setTwoCont(Integer.parseInt(m.get("TWOCOUNT").toString()));
                } else {
                    vo.setTwoCont(0);
                }


                if (m.get("TWOCOUNTJS") != null) {
                    vo.setTwoCountJs(Integer.parseInt(m.get("TWOCOUNTJS").toString()));
                } else {
                    vo.setTwoCountJs(0);
                }

                //查询放款总金额
                //根据基金名称获取基金的id，根据基金查询该基金投了哪些项目，根据项目查询放款总金额
                FundInfoModel fundinfo = new FundInfoModel();
                fundinfo.setFundName((String) m.get("REG_NAME"));
                FundInfoModel fund = fundInfoService.selectById((String) m.get("FUND_ID"));
                if (fund != null) {
                    //已拨付金额
                    //todo 判断是否为特殊基金
                    if ("1".equals(fund.getIsExcept())) {
                        Double ztPayAmount = projInfoService.getExSumPayAmount(fund.getFundId());
                        vo.setZtPayAmount(ztPayAmount);
                    } else {
                        Double ztPayAmount = projInfoService.selectSumPayAmount((String) m.get("FUND_ID"));
                        if (ztPayAmount == null) {
                            vo.setZtPayAmount(0.0);
                        } else {
                            vo.setZtPayAmount(ztPayAmount);
                        }
                        /*Double   ztPayAmount=getSumAount("1",fund.getFundId());
                        vo.setZtPayAmount(ztPayAmount);*/
                    }
                    //本基金认缴金额
                    ProjInfoModel projInfoModel = new ProjInfoModel();
                    projInfoModel.setInveId(fund.getFundId());
                    //直投项目
                    projInfoModel.setProjType("1");

                    //本基金认缴规模
                    //判断是否为特殊的基金
                    Double inveCurrentAmount = 0.0;
                    if ("1".equals(fund.getIsExcept())) {
                        Map<String, Object> ddd = projInfoService.getsumIsExpCurrentC(fund.getFundId());
                        if (ddd != null && ddd.containsKey("INVE_CURRENT_AMOUNT")) {
                            if (ddd.get("INVE_CURRENT_AMOUNT") != null) {
                                Double d1 = Double.parseDouble(ddd.get("INVE_CURRENT_AMOUNT").toString());
                                inveCurrentAmount = d1 == null ? 0.0 : d1;
                                vo.setInveCurrentAmount(inveCurrentAmount);
                            }
                        }


                    } else {
                        Double d1 = projInfoService.getsumCurrentC(fund.getFundId());
                        inveCurrentAmount = d1 == null ? 0.0 : d1;
                        vo.setInveCurrentAmount(inveCurrentAmount);
                    }


                    Double promiseAmount = fund.getPromiseAmount();
                    vo.setPromiseAmount(promiseAmount);
                    //查询基金规模
                    //TODO基金规模
                    Double d = 0.0;
                    if ("1".equals(fund.getIsExcept())) {
                        Map<String, Object> ppp = projInfoService.getsumIsExpCurrentC(fund.getFundId());
                        if (ppp != null && ppp.containsKey("CURRENT_AMOUNT")) {
                            if (ppp.get("CURRENT_AMOUNT") != null) {
                                d = Double.parseDouble(ppp.get("CURRENT_AMOUNT").toString());
                                vo.setSumCurrentAmount(d);
                                //vo.setInveCurrentAmount(d);
                            }
                        }
                    } else {
                        d = projInfoService.getsumCurrentAmount(fund.getFundId());
                    }
                    if (d == null) {
                        vo.setSumCurrentAmount(0.0);
                    } else {
                        vo.setSumCurrentAmount(d);
                    }
                    if (inveCurrentAmount != null) {
                        if (inveCurrentAmount != null) {
                            if (fund.getCurrentAmount() == null) {
                                fund.setCurrentAmount(0.0);
                            }
                            BigDecimal num1 = new BigDecimal(vo.getSumCurrentAmount());
                            BigDecimal num2 = new BigDecimal(inveCurrentAmount);
                            BigDecimal result2 = num1.setScale(3, BigDecimal.ROUND_HALF_DOWN).subtract(num2.setScale(3, BigDecimal.ROUND_HALF_DOWN));
                            //BigDecimal result2 = num1.subtract(num2);
                            //double result = result2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            Double qtAmount = result2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            vo.setQtAmount(qtAmount);
                        } else {
                            Double qtAmount = fund.getCurrentAmount() - 0;
                            vo.setQtAmount(qtAmount);
                        }
                        //其他机构认缴金额
                    }
                    //TODO特殊的基金实缴规模
                    //基金实缴规模
                    if (fund.getFundId() != null) {
                        if ("1".equals(fund.getIsExcept())) {
                            Map<String, Object> hh = projInfoService.getsumIsExpCurrentC(fund.getFundId());
                            if (hh != null && hh.containsKey("RAISE_AMOUNT")) {
                                if (hh.get("RAISE_AMOUNT") != null) {
                                    d = Double.parseDouble(hh.get("RAISE_AMOUNT").toString());
                                    vo.setRaiseAmount(d);
                                }
                            }

                        } else {
                            Double raiseAmount = projInfoService.getsumCurrentRaise(fund.getFundId());
                            vo.setRaiseAmount(raiseAmount);
                        }

                    }

                    //已拨付金额
                    if ("1".equals(fund.getIsExcept())) {
                        Double payAmount = projInfoService.getSpvIsExceptSumAmount(fund.getFundId());
                        vo.setPayAmount(payAmount);
                    } else {
                        //投子基金spv基金的拨付金额
                        Double payAmount = projInfoService.getSpvSumAmount(fund.getFundId());
                        vo.setPayAmount(payAmount);
                    }
                    if (m.get("FOURCONT") != null) {
                        vo.setFourCont(Integer.parseInt(m.get("FOURCONT").toString()));
                    } else {
                        vo.setFourCont(0);
                    }

                    if (m.get("FOURCOUNTJS") != null) {
                        vo.setFourCountjs(Integer.parseInt(m.get("FOURCOUNTJS").toString()));
                    } else {
                        vo.setFourCountjs(0);
                    }
                    //本轮投资总额
                    if (m.get("FOURINTENDEDAMOUNT") != null) {
                        vo.setFourIntendedAmount(Double.parseDouble(m.get("FOURINTENDEDAMOUNT").toString()));
                    } else {
                        vo.setFourIntendedAmount(Double.valueOf(0));
                    }
                    //本基金投资额
                    if (m.get("FOURACTUALAMOUNT") != null) {
                        vo.setFourActualAmount(Double.parseDouble(m.get("FOURACTUALAMOUNT").toString()));
                    } else {
                        vo.setFourActualAmount(Double.valueOf(0));
                    }
                    BigDecimal num100 = new BigDecimal(vo.getFourIntendedAmount());
                    BigDecimal num200 = new BigDecimal(vo.getFourActualAmount());
                    BigDecimal result22 = num100.setScale(2, BigDecimal.ROUND_HALF_DOWN).subtract(num200.setScale(2, BigDecimal.ROUND_HALF_DOWN));
                    vo.setQtshzbAount(result22.doubleValue());
                    //基金规模

                  /*  ProvincialVO sumOne=getSumSignAmount(fund.getFundId(),"2");
                    vo.setSumCurrentAmount(sumOne.getSumCurrentAmount());*/

                    ProvincialVO sumSignAmount = getSumSignAmount(fund.getFundId(), "");
                    /**本基金投资额**/
                    vo.setSumSignAmount(sumSignAmount.getSumSignAmount());
                    ProvincialVO sum = getSumSignAmount(fund.getFundId(), "1");
                    /**本基金投资额**/
                    vo.setOneSumSignAmount(sum.getSumSignAmount());
                    //带动其他社会资本
                    if (m.get("ONEINTENDEDAMOUNT") != null) {
                        BigDecimal num1 = new BigDecimal(vo.getOneIntendedAmount() == null ? 0.0 : vo.getOneIntendedAmount());
                        BigDecimal num2 = new BigDecimal(vo.getOneActualAmount() == null ? 0.0 : vo.getOneActualAmount());

                        BigDecimal result3 = num1.setScale(2, BigDecimal.ROUND_HALF_DOWN).subtract(num2.setScale(2, BigDecimal.ROUND_HALF_DOWN));
                        //double  oneQtMoney=vo.getOneFinaAmount()-vo.getOneActualAmount();
                        vo.setOneQtMoney(result3.doubleValue());
                    } else {
                        vo.setOneQtMoney(0.0);
                    }
                    //查询三级基金拨付的金额
                    if ("1".equals(fund.getIsExcept())) {
                        Double payYtAmount = projInfoService.selectExceptSumAmount(fund.getFundId());
                        vo.setPayYtAmount(payYtAmount);
                    } else {
                        Double payYtAmount = projInfoService.selectSumAmount(fund.getFundId());
                        vo.setPayYtAmount(payYtAmount);
                    }

                }
                //投前估值
                if (m.get("FOURPREMONEY") != null) {
                    vo.setFourPreMoney(Double.parseDouble(m.get("FOURPREMONEY").toString()));
                } else {
                    vo.setFourPreMoney(0.0);
                }
                //投后估值
                if (m.get("FOURPOSTMONEY") != null) {
                    vo.setFourPostMoney(Double.parseDouble(m.get("FOURPOSTMONEY").toString()));
                } else {
                    vo.setFourPostMoney(0.0);
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

    //查询已拨付金额
    public Double getSumAount(String projType, String fundId) {
        ProjInfoModel proj = new ProjInfoModel();
        proj.setInveId(fundId);
        if (StringUtils.isNotEmpty(projType)) {
            proj.setProjType(projType);
        }
        //查询基金投资那些项目
        List<ProjInfoModel> proList = projInfoService.selectList(proj);
        //已拨付金额
        double payAmount = 0;
        if (proList != null && proList.size() > 0) {
            for (ProjInfoModel projInfo1 : proList) {
                List<XjlTPaymentRequestModel> xjltList = xjlTPaymentRequestService.selectByProjId(projInfo1.getProjId());
                double sumActualPayAmount = 0;
                if (xjltList != null && xjltList.size() > 0) {
                    for (XjlTPaymentRequestModel xjltpaymentrequestmodel : xjltList) {
                        //累加所有的放款金额
                        if (xjltpaymentrequestmodel.getActualPayAmount() != null) {
                            BigDecimal num14 = new BigDecimal(sumActualPayAmount);
                            if (xjltpaymentrequestmodel.getActualPayAmount() == null) {
                                xjltpaymentrequestmodel.setActualPayAmount(0.0);
                            }
                            BigDecimal num25 = new BigDecimal(xjltpaymentrequestmodel.getActualPayAmount());
                            BigDecimal result12 = num14.setScale(2, BigDecimal.ROUND_HALF_DOWN).add(num25.setScale(2, BigDecimal.ROUND_HALF_DOWN));
                            sumActualPayAmount = result12.doubleValue();
                            //sumActualPayAmount=sumActualPayAmount+xjltpaymentrequestmodel.getActualPayAmount();
                        }
                    }
                    BigDecimal num12 = new BigDecimal(payAmount);
                    BigDecimal num22 = new BigDecimal(sumActualPayAmount);
                    BigDecimal result12 = num12.setScale(2, BigDecimal.ROUND_HALF_DOWN).add(num22);
                    payAmount = result12.doubleValue();
                }
            }

        }
        return payAmount;
    }

    public ProvincialVO getSumSignAmount(String fundId, String projType) {
        ProvincialVO provincialVO = new ProvincialVO();
        //本基金投资额
        ProjInfoModel proj1 = new ProjInfoModel();
        proj1.setInveId(fundId);
        if (StringUtils.isNotEmpty(projType)) {
            proj1.setProjType(projType);
        }
        //查询基金投资所有的项目
        List<ProjInfoModel> proList1 = projInfoService.selectList(proj1);
        //本基金投资额
        double sumSignAmount = 0;
        double sumCurrentAmount = 0;
        if (proList1 != null && proList1.size() > 0) {
            for (ProjInfoModel projInfoModel1 : proList1) {
                double signAmount = 0;
                double currentAmount = 0;

                List<ProjContractFileModel> listPro = projContractFileService.selectByProjIdList(projInfoModel1.getProjId());
                if (listPro != null && listPro.size() > 0) {
                    if (listPro.get(0).getSignAmount() == null) {
                        signAmount = 0;
                    } else {
                        signAmount = listPro.get(0).getSignAmount();
                    }
                    //基金认缴规模
                    if (listPro.get(0).getCurrentAmount() == null) {
                        currentAmount = 0;
                    } else {
                        currentAmount = listPro.get(0).getCurrentAmount();
                    }
                    //合同同约定投资额
                } else {
                    signAmount = 0;
                    currentAmount = 0;
                }
                sumSignAmount = sumSignAmount + signAmount;
                sumCurrentAmount = sumCurrentAmount + currentAmount;
                provincialVO.setSumSignAmount(sumSignAmount);
                provincialVO.setSumCurrentAmount(sumCurrentAmount);
                return provincialVO;
            }
        }
        return provincialVO;
    }

    @ApiOperation(value = "查询二级基金/Spv基金", notes = "查询二级基金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundSrc/getFundInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getFundSrcInfoList(@RequestParam("fundSrc") String fundSrc) {
        try {
            FundInfoModel fundInfoModel = new FundInfoModel();
            fundInfoModel.setFundSrc(fundSrc);
            if ("4".equals(fundSrc)) {
                String userId = this.getLoginUserId();
                fundInfoModel.setCreateBy(userId);
            }
            fundInfoModel.setStatus("1");
            List<FundInfoModel> rows = fundInfoService.selectList(fundInfoModel);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value = "查询基金", notes = "查询二级基金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projId", value = "项目ID", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundSrcAll/getFundInfoAllList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getFundInfoAllList() {
        try {
            FundInfoModel fundInfoModel = new FundInfoModel();
            fundInfoModel.setStatus("1");
            List<FundInfoModel> rows = fundInfoService.selectList(fundInfoModel);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
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

    /**
     * 查询出资人出资情况统计表
     **/

    @ApiOperation(value = "基金基本信息统计表", notes = "基金基本信息表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/fundMc/inveInfoList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String inveInfoList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getPlatProp())) {
                List<String> platProp = Arrays.asList(searchBean.getPlatProp().split(","));
                searchCondition.addConditionIn("a.PLAT_PROP", platProp);
            }

            if (StringUtils.isNotEmpty(searchBean.getFundStruct())) {
                List<String> fundStruct = Arrays.asList(searchBean.getFundStruct().split(","));
                searchCondition.addConditionIn("a.FUND_STRUCT", fundStruct);
            }
            if (StringUtils.isNotEmpty(searchBean.getSubPlatProp())) {
                List<String> subPlatProps = Arrays.asList(searchBean.getSubPlatProp().split(","));
                searchCondition.addConditionIn("a.SUB_PLAT_PROP", subPlatProps);
            }
            if (StringUtils.isNotEmpty(searchBean.getFundGenre())) {
                List<String> fundGenre = Arrays.asList(searchBean.getFundGenre().split(","));
                searchCondition.addConditionIn("a.FUND_GENRE", fundGenre);
            }

            searchCondition.setSearchBean(searchBean);
            PageInfo<FundInfoModel> rows = fundInfoService.fundinveInfoList(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                //根据基金查询期数
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                if ("0".equals(model.getIsRecord())) {
                    vo.setRecordDate(null);
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    String codeName = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                    vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(model.getPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204", model.getPlatProp());
                    vo.setPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(model.getFundGenre())) {
                    String fundGenre = codeUtils.getCodeNameByValue("1011", model.getFundGenre());
                    vo.setFundGenreName(StringUtils.isEmpty(fundGenre) ? "" : fundGenre);
                }
                if (StringUtils.isNotEmpty(model.getSubPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204" + model.getPlatProp(), model.getSubPlatProp());
                    vo.setSubPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                //查询所有的期数总和
                List<FundShareItemModel> shareList = fundShareItemService.selectByFundIdList(model.getFundId());
                //合计所有期数的金额
                Double sumDuesAmount = fundShareItemService.selectSumDuesAmount(model.getFundId());
                if (sumDuesAmount != null) {
                    vo.setSumDuesAmount(sumDuesAmount);
                } else {
                    vo.setSumDuesAmount(0.0);
                }
                for (FundShareItemModel fundShareItemModel : shareList) {
                    //根据基金查询
                    FundShareItemModel fundShare = new FundShareItemModel();
                    fundShare.setFundId(fundShareItemModel.getFundId());
                    fundShare.setCloseRound(fundShareItemModel.getCloseRound());
                    List<FundShareItemModel> list1 = fundShareItemService.selectList(fundShare);
                    for (FundShareItemModel fundShareItem : list1) {
                        if (StringUtils.isNotEmpty(fundShareItem.getDescFile())) {
                            fundShareItemModel.setDescFile("是");
                            continue;
                        }
                    }
                }
                //查询已签署投资
                Double projcontractfile = projContractFileService.getSumSignAmount("", model.getFundId());
                if (projcontractfile != null) {
                    vo.setSumSignAmount(projcontractfile);
                }
                FundShareItemModel fundShareItemModel = new FundShareItemModel();
                fundShareItemModel.setFundId(model.getFundId());
                //根据fundId查询查询省政府投资 基金认缴规模
                FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
                fundShareInfoModel.setFundId(model.getFundId());
                fundShareInfoModel.setEnteId("0");
                List<FundShareInfoModel> fund = fundShareInfoService.selectList(fundShareInfoModel);
                Double szfInveAmount = 0.0;
                Double szfRaiseAmount = 0.0;
                if (fund != null && fund.size() > 0) {
                    //只能查到一条，前台取第一条
                    //vo.setShareInfoList(fund);
                    szfInveAmount = fund.get(0).getInveAmount();
                    szfRaiseAmount = fund.get(0).getRaiseAmount();
                    vo.setSzfInveAmount(szfInveAmount);
                    vo.setSzfRaiseAmount(szfRaiseAmount);
                }
                vo.setShareItem(shareList);
                Calendar calendar = Calendar.getInstance();
                int year1 = calendar.get(Calendar.YEAR);
                if (StringUtils.isNotEmpty(model.getEnteId())) {
                    List<MeetingRecordModel> meetingRecordList = meetingRecordService.selectByTimeList(model.getEnteId(), String.valueOf(year1));
                    if (meetingRecordList != null && meetingRecordList.size() > 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        if (meetingRecordList.size() > 1) {
                            if (meetingRecordList.get(1).getMeetingDate() != null) {
                                String oldMeetingDate = sdf.format(meetingRecordList.get(1).getMeetingDate());
                                vo.setOldMeetingDate(oldMeetingDate);
                            }
                            if (meetingRecordList.get(0) != null && meetingRecordList.get(0).getMeetingDate() != null) {
                                String meetingDate = sdf.format(meetingRecordList.get(0).getMeetingDate());
                                vo.setMeetingDate(meetingDate);
                            }
                        } else if (meetingRecordList.size() == 1) {
                            //如果只有一条则是他本身
                            if (meetingRecordList.get(0) != null && meetingRecordList.get(0).getMeetingDate() != null) {
                                String oldMeetingDate = sdf.format(meetingRecordList.get(0).getMeetingDate());
                                vo.setOldMeetingDate(oldMeetingDate);
                                vo.setMeetingDate(oldMeetingDate);
                            }
                        }
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


    @ApiOperation(value = "基金综合统计表", notes = "基金综合统计表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/fundComprehensiveList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundComprehensiveList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<Map<String, Object>> rows = fundInfoService.fundComprehensiveListPage(searchCondition);
            List<ComprehensiveVO> list = new ArrayList<ComprehensiveVO>();
            for (Map m : rows.getList()) {
                ComprehensiveVO vo = new ComprehensiveVO();
                BeanUtils.copyProperties(vo, m);

                if (m.get("FUND_STRUCT") != null) {
                    String codeName = codeUtils.getCodeNameByValue("210", (String) m.get("FUND_STRUCT"));
                    vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (m.get("PLAT_PROP") != null) {
                    String codeName = codeUtils.getCodeNameByValue("204", (String) m.get("PLAT_PROP"));
                    vo.setPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (m.get("SUB_PLAT_PROP") != null) {
                    String codeName = codeUtils.getCodeNameByValue("204" + (String) m.get("PLAT_PROP"), (String) m.get("SUB_PLAT_PROP"));
                    vo.setSubPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                vo.setRegName((String) m.get("REG_NAME"));
                //基金属性
                if (m.get("FUND_GENRE") != null) {
                    if ("1".equals((String) m.get("FUND_GENRE"))) {
                        //专项子基金
                        vo.setZx("1");
                    } else if ("2".equals((String) m.get("FUND_GENRE"))) {
                        //市场化基金
                        vo.setSch("1");
                    }

                }
                vo.setFundGenre((String) m.get("FUND_GENRE"));
                //基金状态
                if (m.get("FUND_STS") != null) {
                    String FUND_STS = codeUtils.getCodeNameByValue("254", (String) m.get("FUND_STS"));
                    vo.setFundSts(StringUtils.isEmpty(FUND_STS) ? "" : FUND_STS);
                }
                //地址
                if (m.get("ADDRESS_DETAILS") != null) {
                    vo.setRegAdd((String) m.get("ADDRESS_DETAILS"));
                }
                if (m.get("PRIVINCE") != null) {
                    //是江苏
                    if ("JS".equals(m.get("PRIVINCE"))) {
                        vo.setIsJs("1");
                    } else {
                        vo.setIsNoJs("1");
                    }
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //注册日期
                if (m.get("SETUP_DT") != null) {
                    vo.setSetupDt((Date) m.get("SETUP_DT"));
                }
                //备案日期
                if (m.get("RECORD_DATE") != null) {
                    vo.setRecordDate((Date) m.get("RECORD_DATE"));
                }
                //备案编号
                if (m.get("RECORD_CODE") != null) {
                    vo.setRecordCode((String) m.get("RECORD_CODE"));
                }
                //基金管理人
                if (m.get("MC_NAME") != null) {
                    vo.setMcName((String) m.get("MC_NAME"));
                }
                //管理人登记号
                if (m.get("REGISTER_NO") != null) {
                    vo.setRegisterNo((String) m.get("REGISTER_NO"));
                }
                //存续期（年）
                if (m.get("DURATION_PERIOD") != null) {
                    vo.setDurationPeriod((String) m.get("DURATION_PERIOD"));
                }
                //投资期（年）
                if (m.get("INVE_PERIOD") != null) {
                    vo.setInvePeriod((String) m.get("INVE_PERIOD"));
                }
                //投资期起算日
                if (m.get("INVE_START_DATE") != null) {
                    vo.setInveStartDate((Date) m.get("INVE_START_DATE"));
                }
                //托管银行
                if (m.get("CUSTODIAN_BANK") != null) {
                    vo.setCustodianBank((String) m.get("CUSTODIAN_BANK"));
                }
                //查询出资人
                FundShareInfoModel fundShareInfoModel1 = new FundShareInfoModel();
                fundShareInfoModel1.setFundId((String) m.get("fund_id"));
                List<FundShareInfoModel> listm = fundShareInfoService.selectListByEntId(fundShareInfoModel1);
                if (listm != null && listm.size() > 0) {
                    //其余出资人数量
                    vo.setGs(listm.size());
                    //出资人名
                    StringBuffer investorName = new StringBuffer();
                    for (FundShareInfoModel m1 : listm) {
                        InveInfoModel mc = inveInfoService.selectById(m1.getInvestorId());
                        if (mc != null) {
                            if (investorName != null && investorName.length() > 0) {
                                investorName.append("," + mc.getInvestorName());

                            } else {
                                investorName.append(mc.getInvestorName());
                            }
                        }
                    }
                    //主要出资人
                    vo.setInvestorName(investorName.toString());
                }
                //关键人士
                if (m.get("KEY_PERSON_LOCK") != null) {
                    vo.setKeyPersonLock((String) m.get("KEY_PERSON_LOCK"));
                }
                //基金首次出资人会议召开时间
                MeetingRecordModel meetingRecordModel = new MeetingRecordModel();
                meetingRecordModel.setObjectId((String) m.get("ENTE_ID"));
                Calendar calendar = Calendar.getInstance();
                int year1 = calendar.get(Calendar.YEAR);
                if (StringUtils.isNotEmpty((String) m.get("ENTE_ID"))) {
                    List<MeetingRecordModel> meetingRecordList = meetingRecordService.selectByTimeList((String) m.get("ENTE_ID"), String.valueOf(year1));
                    if (meetingRecordList.size() > 0) {
                        vo.setIszkhy("是");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        if (meetingRecordList.get(0).getMeetingDate() != null) {
                            String dateString = formatter.format(meetingRecordList.get(0).getMeetingDate());
                            vo.setMeetingDate(dateString);
                        }
                        if (meetingRecordList.size() > 1) {
                            //最新的会议时间
                            String dateString1 = formatter.format(meetingRecordList.get(1).getMeetingDate());
                            vo.setOldMeetingDate(dateString1);
                        } else {
                            //最早会议时间
                            String dateString1 = formatter.format(meetingRecordList.get(0).getMeetingDate());
                            vo.setOldMeetingDate(dateString1);
                        }

                    } else {
                        //是否召开会议
                        vo.setIszkhy("否");
                        vo.setMeetingDate(null);
                    }

                }
                //基金规模
                if (m.get("PLAN_AMOUNT") != null) {
                    vo.setPlanAmount(Double.parseDouble(m.get("PLAN_AMOUNT").toString()));
                } else {
                    vo.setPlanAmount(0.0);
                }
                //根据fundId查询查询省政府投资 基金认缴规模
                FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
                fundShareInfoModel.setFundId((String) m.get("FUND_ID"));
                fundShareInfoModel.setEnteId("0");
                List<FundShareInfoModel> fund = fundShareInfoService.selectList(fundShareInfoModel);
                if (fund != null && fund.size() > 0) {
                    //其中：省政府投资基金认缴规模,其中：省政府投资基金实缴规模
                    vo.setFundShare(fund.get(0));
                }
                //基金实缴规模
                if (m.get("RAISE_AMOUNT") != null) {
                    vo.setRaiseAmount(Double.parseDouble(m.get("RAISE_AMOUNT").toString()));
                } else {
                    vo.setRaiseAmount(0.0);
                }

                //累计出资
                Double sumDuesAmount = fundShareItemService.selectSumDuesAmount((String) m.get("FUND_ID"));
                if (sumDuesAmount != null) {
                    vo.setSumDuesAmount(sumDuesAmount);
                } else {
                    vo.setSumDuesAmount(0.0);
                }
                //省政府累计出资
                Double sumGovDuesAmount = fundShareItemService.selectSumGovDuesAmount((String) m.get("FUND_ID"));
                if (sumGovDuesAmount != null) {
                    vo.setSumGovDuesAmount(sumGovDuesAmount);
                } else {
                    vo.setSumGovDuesAmount(0.0);
                }
                List<FundShareItemModel> shareList = fundShareItemService.selectByFundIdList((String) m.get("FUND_ID"));
                vo.setShareItem(shareList);
                //直投的
                //总数
                if (m.get("ONECOUNT") != null) {
                    vo.setOneCount(Integer.parseInt(m.get("ONECOUNT").toString()));
                } else {
                    vo.setOneCount(0);
                }
                //直投江苏个数
                if (m.get("ONECOUNTJS") != null) {
                    vo.setOneCountJs(Integer.parseInt(m.get("ONECOUNTJS").toString()));
                } else {
                    vo.setOneCountJs(0);
                }
                //直投本-轮投资总额
                if (m.get("ONEINTENDEDAMOUNT") != null) {
                    vo.setOneIntendedAmount(Double.parseDouble(m.get("ONEINTENDEDAMOUNT").toString()));
                } else {
                    vo.setOneIntendedAmount(Double.valueOf(0));
                }
                //其中：本基金投资额
                if (m.get("ONEACTUALAMOUNT") != null) {
                    vo.setOneActualAmount(Double.parseDouble(m.get("ONEACTUALAMOUNT").toString()));
                } else {
                    vo.setOneActualAmount(Double.valueOf(0));
                }
                //直投-带动其他社会资本
                if (m.get("ONEINTENDEDAMOUNT") != null) {
                    BigDecimal num1 = new BigDecimal(vo.getOneIntendedAmount() == null ? 0.0 : vo.getOneIntendedAmount());
                    BigDecimal num2 = new BigDecimal(vo.getOneActualAmount() == null ? 0.0 : vo.getOneActualAmount());

                    BigDecimal result3 = num1.setScale(2, BigDecimal.ROUND_HALF_DOWN).subtract(num2.setScale(2, BigDecimal.ROUND_HALF_DOWN));
                    //double  oneQtMoney=vo.getOneFinaAmount()-vo.getOneActualAmount();
                    vo.setOneQtMoney(result3.doubleValue());
                } else {
                    vo.setOneQtMoney(0.0);
                }
                //直投-已拨付金额
                if (m.get("FUND_ID") != null) {
                    Double ztPayAmount = projInfoService.selectSumPayAmount((String) m.get("FUND_ID"));
                    if (ztPayAmount == null) {
                        vo.setZtPayAmount(0.0);
                    } else {
                        vo.setZtPayAmount(ztPayAmount);
                    }
                    /* double   ztPayAmount=getSumAount("1",(String)m.get("FUND_ID"));
                     vo.setZtPayAmount(ztPayAmount);*/
                }
                //直投-本轮估值
                if (m.get("ONEPREMONEY") != null) {
                    vo.setOnepreMoney(Double.parseDouble(m.get("ONEPREMONEY").toString()));
                } else {
                    vo.setOnepreMoney(Double.valueOf(0));
                }
                //直投-最新估值
                if (m.get("ONEPOSTMONEY") != null) {
                    vo.setOnePostMoney(Double.parseDouble(m.get("ONEPOSTMONEY").toString()));
                } else {
                    vo.setOnePostMoney(Double.valueOf(0));
                }
                //子基金-总数
                if (m.get("TWOCOUNT") != null) {
                    vo.setTwoCont(Integer.parseInt(m.get("TWOCOUNT").toString()));
                } else {
                    vo.setTwoCont(0);
                }
                //子基金-省内
                if (m.get("TWOCOUNTJS") != null) {
                    vo.setTwoCountJs(Integer.parseInt(m.get("TWOCOUNTJS").toString()));
                } else {
                    vo.setTwoCountJs(0);
                }


                if (m.get("FUND_ID") != null) {
                    //投子基金-基金认缴规模
                    Double d = projInfoService.getsumCurrentAmount((String) m.get("FUND_ID"));
                    if (d == null) {
                        vo.setSumCurrentAmount(0.0);
                    } else {
                        vo.setSumCurrentAmount(d);
                    }
                    //子基金-本基金认缴规模
                    Double d1 = projInfoService.getsumCurrentC((String) m.get("FUND_ID"));
                    //todo 本基金认实缴规模

                    Double inveCurrentAmount = d1 == null ? 0.0 : d1;
                    vo.setInveCurrentAmount(inveCurrentAmount);
                    BigDecimal num1 = new BigDecimal(vo.getSumCurrentAmount());
                    BigDecimal num2 = new BigDecimal(inveCurrentAmount);
                    BigDecimal result2 = num1.subtract(num2);
                    Double qtAmount = result2.doubleValue();
                    //子基金的其他机构认缴规模
                    vo.setQtAmount(qtAmount);
                    //子基金-基金实缴规模
                    Double raiseAmount = projInfoService.getsumCurrentRaise((String) m.get("FUND_ID"));
                    vo.setRaiseAmount(raiseAmount);

                    //子基金-已拨付金额
                    double payAmount = getSumAount("2", (String) m.get("FUND_ID"));

                    vo.setPayAmount(payAmount);
                }
                //子基金已投 总数
                if (m.get("FOURCONT") != null) {
                    vo.setFourCont(Integer.parseInt(m.get("FOURCONT").toString()));
                } else {
                    vo.setFourCont(0);
                }
                //子基金已投 -江苏
                if (m.get("FOURCOUNTJS") != null) {
                    vo.setFourCountjs(Integer.parseInt(m.get("FOURCOUNTJS").toString()));
                } else {
                    vo.setFourCountjs(0);
                }
                //子基金投资项目-本轮投资总额
                if (m.get("FOURINTENDEDAMOUNT") != null) {
                    vo.setFourIntendedAmount(Double.parseDouble(m.get("FOURINTENDEDAMOUNT").toString()));
                } else {
                    vo.setFourIntendedAmount(Double.valueOf(0));
                }
                //本基金投资额
                if (m.get("FOURACTUALAMOUNT") != null) {
                    vo.setFourActualAmount(Double.parseDouble(m.get("FOURACTUALAMOUNT").toString()));
                } else {
                    vo.setFourActualAmount(Double.valueOf(0));
                }
                BigDecimal num100 = new BigDecimal(vo.getFourIntendedAmount());
                BigDecimal num200 = new BigDecimal(vo.getFourActualAmount());
                BigDecimal result22 = num100.setScale(2, BigDecimal.ROUND_HALF_DOWN).subtract(num200.setScale(2, BigDecimal.ROUND_HALF_DOWN));
                //带动其他社会资本
                vo.setQtshzbAount(result22.doubleValue());
                //已投项目-已拨付金额
                //根据二级基金查询投了哪些三级基金
                ProjInfoModel projInfoModelVO = new ProjInfoModel();
                projInfoModelVO.setInveId((String) m.get("FUND_ID"));
                projInfoModelVO.setProjType("2");
                List<ProjInfoModel> projVO = projInfoService.selectList(projInfoModelVO);
                Double payYtAmount = 0.0;
                if (projVO != null && projVO.size() > 0) {
                    for (ProjInfoModel proVO : projVO) {
                        //被投对象作为出资主体(查询三级基金投的项目)
                        ProjInfoModel projInfoModelVO1 = new ProjInfoModel();
                        projInfoModelVO1.setInveId(proVO.getProjObject());
                        projInfoModelVO1.setProjType("3");
                        List<ProjInfoModel> projVO1 = projInfoService.selectList(projInfoModelVO1);
                        Double payYtAmount1 = 0.0;
                        if (projVO1 != null && projVO1.size() > 0) {
                            for (ProjInfoModel proVO1 : projVO1) {
                                //三级基金支付金额
                                Double value1 = xjlTPaymentRequestService.selectCountSum(proVO1.getProjId()) == null ? 0.0 : xjlTPaymentRequestService.selectCountSum(proVO1.getProjId());
                                //四级基金付款
                                Double va = xjlTPaymentRequestService.selectsumAllPay(proVO1.getProjObject()) == null ? 0.0 : xjlTPaymentRequestService.selectsumAllPay(proVO1.getProjObject());
                                Double value = value1 + va;
                                BigDecimal nums = null;
                                if (value != null) {
                                    nums = new BigDecimal(value);
                                } else {
                                    nums = new BigDecimal(0);
                                }

                                BigDecimal numm = new BigDecimal(payYtAmount1);
                                BigDecimal resultM = nums.setScale(2, BigDecimal.ROUND_HALF_DOWN).add(numm);
                                payYtAmount1 = resultM.doubleValue();
                            }
                        }
                        payYtAmount = payYtAmount + payYtAmount1;

                    }
                    //子基金投资项目-本基金
                    //已拨付金额
                    vo.setPayYtAmount(payYtAmount);

                }
                //子基金投资项目-投前估值
                if (m.get("FOURPREMONEY") != null) {
                    vo.setFourPreMoney(Double.parseDouble(m.get("FOURPREMONEY").toString()));
                } else {
                    vo.setFourPreMoney(0.0);
                }
                //子基金投资项目-投后估值
                if (m.get("FOURPOSTMONEY") != null) {
                    vo.setFourPostMoney(Double.parseDouble(m.get("FOURPOSTMONEY").toString()));
                } else {
                    vo.setFourPostMoney(0.0);
                }
              /*  if(StringUtils.isNotEmpty(searchBean.getLabel())){
                    list.add(vo);
                }else{
                    list.add(vo);
                }*/
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
    @ApiOperation(value = "终止/注销", notes = "终止/注销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fundId", value = "fundId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "FundInfoDTO", value = "FundInfoDTO", required = true, dataType = "FundInfoDTO")
    })
    @PostMapping(value = "/fundInfo/updateStatus/{fundId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@PathVariable("fundId") String fundId, @RequestBody FundInfoDTO dto) {

        try {
            //母基金删除
            if ("2".equals(dto.getTypeStr())) {
                //判斷母基金是否投資過其他的項目或者基金
                ProjInfoModel projI = new ProjInfoModel();
                projI.setInveId(fundId);
                List<ProjInfoModel> list = projInfoService.selectList(projI);
                if (list != null && list.size() > 0) {
                    baseResponse.setMsg("该基金-已存在业务数据不允许删除");
                    return JSONObject.toJSONString(baseResponse);
                }
                //母基金刪除
                FundInfoModel fundInfoVO = new FundInfoModel();
                fundInfoVO.setFundId(fundId);
                fundInfoVO.setFundSts(dto.getFundSts());
                fundInfoVO.setUpdateDt(new Date());
                fundInfoVO.setUpdateBy(this.getLoginUserId());
                fundInfoVO.setStatus(dto.getStatus());
                fundInfoService.update(fundInfoVO);
            }else {
                FundInfoModel fundInfoVO = new FundInfoModel();
                fundInfoVO.setFundId(fundId);
                fundInfoVO.setFundSts(dto.getFundSts());
                fundInfoVO.setUpdateDt(new Date());
                fundInfoVO.setUpdateBy(this.getLoginUserId());
                fundInfoVO.setFundSts(dto.getFundSts());
                fundInfoVO.setStatus(dto.getStatus());
                //查询子基金对应的项目是几个
                ProjInfoModel projIno = new ProjInfoModel();
                projIno.setProjType("2");
                projIno.setProjObject(fundId);
                List<ProjInfoModel> listProjI=projInfoService.selectList(projIno);
                if(listProjI!=null&&listProjI.size()==1){
                    fundInfoService.update(fundInfoVO);
                }
                if (StringUtils.isNotEmpty(dto.getProjId())) {
                    ProjInfoModel proj = new ProjInfoModel();
                    proj.setProjId(dto.getProjId());
                    proj.setUpdateDt(new Date());
                    proj.setUpdateBy(this.getLoginUserId());
                    proj.setStatus(dto.getStatus());
                    projInfoService.update(proj);
                }
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value = "外部用户新增二级基金", notes = "外部用户新增二级基金")
    @ApiImplicitParam(name = "FundInfoProjDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/saveFundInfoProj", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveFundInfoProj(@RequestBody FundInfoProjDTO dto) {
        try {
            FundInfoModel fundInfo = new FundInfoModel();
            BeanUtils.copyProperties(fundInfo, dto);

            ProjInfoModel projInfo = new ProjInfoModel();
            BeanUtils.copyProperties(projInfo, dto);

            FundInveDescModel fundInveDescModel = new FundInveDescModel();
            BeanUtils.copyProperties(fundInveDescModel, dto);

            FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
            BeanUtils.copyProperties(fundShareInfoModel, dto);

            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties(projAppInfoModel, dto);
            //TODO  判断出资主体和基金不能重复
            //基金序列自动生成
            String fundCode = "";
            if (StringUtils.isNotEmpty(dto.getPlatProp())) {
                if ("1".equals(dto.getPlatProp())) {
                    //查询数据库的序列排到什么了
                    fundCode = "%" + "NQ" + "%";
                    List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                    if (listFundCode != null && listFundCode.size() > 0) {
                        String sub = listFundCode.get(0).getFundCode();
                        if (StringUtils.isNotEmpty(sub)) {
                            if (Integer.parseInt(sub.substring(2)) <= 8) {
                                fundInfo.setFundCode("N0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                            } else {
                                fundInfo.setFundCode("NQ" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                            }
                        }

                    } else {
                        fundInfo.setFundCode("NQ01");
                    }
                } else if ("2".equals(dto.getPlatProp())) {
                    fundCode = "%" + "NG" + "%";
                    List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                    if (listFundCode != null && listFundCode.size() > 0) {
                        String sub = listFundCode.get(0).getFundCode();
                        if (StringUtils.isNotEmpty(sub)) {
                            if (Integer.parseInt(sub.substring(2)) <= 8) {
                                fundInfo.setFundCode("NG0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                            } else {
                                fundInfo.setFundCode("NG" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                            }

                        }
                    } else {
                        fundInfo.setFundCode("NG01");
                    }
                } else if ("3".equals(dto.getPlatProp())) {
                    fundCode = "%" + "NC" + "%";
                    List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                    if (listFundCode != null && listFundCode.size() > 0) {
                        String sub = listFundCode.get(0).getFundCode();
                        if (StringUtils.isNotEmpty(sub)) {
                            if (Integer.parseInt(sub.substring(2)) <= 8) {
                                fundInfo.setFundCode("NC0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                            } else {
                                fundInfo.setFundCode("NC" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1));
                            }

                        }

                    } else {
                        fundInfo.setFundCode("NC01");
                    }
                }

            }
            if (StringUtils.isNotEmpty(dto.getFundId())) {
                //判断二级基金还是省政府基金
                if ("2".equals(dto.getGroupId())) {
                    //判断是否修改出资主体
                    ProjInfoModel pro = projInfoService.selectById(dto.getProjId());
                    if (pro != null) {
                        if (!pro.getInveId().equals(dto.getInveId())) {
                            //修改基金编号

                        }

                    }
                }

                fundInfoService.updateFundInfoProj(fundInfo, projInfo, this.getLoginUserId(), fundInveDescModel, projAppInfoModel);
            } else {
                ProjInfoModel projInfos = new ProjInfoModel();
                projInfos.setProjName(dto.getProjName());
                projInfos.setInveId(dto.getInveId());
                List<ProjInfoModel> list = projInfoService.selectList(projInfos);
                if (list != null && list.size() > 0) {
                    baseResponse.setMsg("基金已存在！");
                    return JSONObject.toJSONString(baseResponse);
                }
                fundInfoService.insertFundInfoProj(fundInfo, projInfo, this.getLoginUserId(), fundInveDescModel, projAppInfoModel, fundShareInfoModel);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }

        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value = "获取平台管理基金列表", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoProjList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoProjList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("groupId") String groupId,
                                   String fundName, String fundStruct, String platProp, String fundSts, String fundSrc, String fundGenre, String oldSubPlatProp, String keyWord, String projSrc, String projStatus, String projType, String inveS, String isDirect, String fundSrcS, String inveId) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);

            if (StringUtils.isNotEmpty(fundSrcS)) {
                List<String> fundSrcStr = Arrays.asList(fundSrcS.split(","));
                searchCondition.addConditionIn("bb.FUND_SRC", fundSrcStr);
            }
            if (StringUtils.isNotEmpty(fundSrc)) {
                searchCondition.addConditionEqualTo("bb.FUND_SRC", fundSrc);
            }

            if (StringUtils.isNotEmpty(fundGenre)) {
                List<String> fundGenres = Arrays.asList(fundGenre.split(","));
                searchCondition.addConditionIn("bb.FUND_GENRE", fundGenres);
            }
            if (StringUtils.isNotEmpty(projSrc)) {
                List<String> projSrcs = Arrays.asList(projSrc.split(","));
                searchCondition.addConditionIn("ppp.PROJ_SRC", projSrcs);
            }
            if (StringUtils.isNotEmpty(projStatus)) {
                List<String> projStatusS = Arrays.asList(projStatus.split(","));
                searchCondition.addConditionIn("ppp.PROJ_STATUS", projStatusS);
            }
            if (StringUtils.isNotEmpty(inveId)) {
                searchCondition.addConditionEqualTo("ppp.inve_id", inveId);
            }

         /*   if ("1".equals(isDirect)) {
               searchCondition.addConditionEqualTo("bb.ISDIRECT", isDirect);
                searchCondition.addParam("fundsType", isDirect);
            }*/
            if (StringUtils.isNotEmpty(oldSubPlatProp)&&!"2".equals(groupId)) {
                List<String> subPlatProps = Arrays.asList(oldSubPlatProp.split(","));
                searchCondition.addConditionIn("bb.OLD_SUB_PLAT_PROP", subPlatProps);
            }
            if (StringUtils.isNotEmpty(projType)) {
                searchCondition.addConditionEqualTo("ppp.proj_type", projType);
            }
            if (StringUtils.isNotEmpty(keyWord)) {
                if ("".equals(keyWord.trim()) || keyWord.trim() == null) {
                    searchCondition.addParam("keyWord", "");
                } else {
                    keyWord = "'%" + keyWord.trim() + "%'";
                    searchCondition.addParam("keyWord", keyWord);
                }
            }
            if (fundStruct != null && !"".equals(fundStruct)) {
                fundStruct.replace(",", "','");
                fundStruct = "'" + fundStruct + "'";
                searchCondition.addParam("FUND_STRUCT", fundStruct);
            }
            if (platProp != null && !"".equals(platProp)) {
                searchCondition.addParam("PLAT_PROP", platProp);
            }
            if (fundSts != null && !"".equals(fundSts)) {
                fundSts.replace(",", "','");
                fundSts = "'" + fundSts + "'";
                searchCondition.addParam("FUND_STS", fundSts);
            }
            searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
            UserTypeEnmu userType = getLoginUserType();
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            //省政府基金的角色
            if (userType.compareTo(UserTypeEnmu.EXTERNAL) == 0) {
                if (listRow != null && listRow.size() > 0) {
                    if (1005000 != listRow.get(0).getId()) {
                        searchCondition.addParam("USER_ID", this.getLoginUserId());
                        //searchCondition.addParam("GROUP_ID", groupId);
                    }
                }

            } else {
                //其他基金的角色
                //查询的是基金平台
                if (StringUtils.isNotEmpty(groupId)) {
                    //groupId=2是基金平台传的标识
                    searchCondition.addParam("INVES", groupId);
                }
                //判断如果是基金
                if (StringUtils.isNotEmpty(groupId)) {
                    //直投平台有穿透数据
                    //searchCondition.addParam("GROUP_ID", groupId);
                }

            }
            PageInfo<FundInfoModel> rows = fundInfoService.selectListProjPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                if (model.getProjInfo() != null) {
                    vo.setProjInfo(model.getProjInfo());
                }
                vo.setProjectId(model.getProjctId());
                Double paySumAmount = projInfoService.selectPassPayAmount(model.getFundId());
                if (paySumAmount != null) {
                    vo.setPaySumAmount(paySumAmount);
                } else {
                    vo.setPaySumAmount(0.0);
                }
                Integer projNum = fundInfoService.selectByProjNum(model.getFundId());
                if (projNum == null) {
                    vo.setProjNum("0");
                } else {
                    vo.setProjNum(String.valueOf(projNum));
                }
                //判断是否特殊的基金
                if (StringUtils.isNotEmpty(model.getIsExcept())) {
                    //查询这个基金投了
                    if ("1".equals(model.getIsExcept())) {
                        //特殊的基金只统计三级项目，四级投项目
                        Map<String, Object> map = projInfoService.selectSum(model.getFundId());
                        if (map.get("AMOUNT") != null) {
                            vo.setActualAmount((String) map.get("AMOUNT"));
                        }
                        if (map.get("SUMCOUNT") != null) {
                            vo.setProjNum(String.valueOf(map.get("SUMCOUNT")));
                        }

                    }
                }
                if (StringUtils.isNotEmpty(model.getFundGenre())) {
                    String fundGenreName = codeUtils.getCodeNameByValue("1011", model.getFundGenre());
                    vo.setFundGenreName(StringUtils.isEmpty(fundGenreName) ? "" : fundGenreName);
                }
                if (StringUtils.isNotEmpty(model.getCurrentCurrency())) {
                    String currentCurrencyName = codeUtils.getCodeNameByValue("103", model.getCurrentCurrency());
                    vo.setCurrentCurrencyName(StringUtils.isEmpty(currentCurrencyName) ? "" : currentCurrencyName);
                }
                if (model.getProjInfo() != null) {
                    if (StringUtils.isNotEmpty(model.getProjInfo().getProjSrc())) {
                        String projSrcName = codeUtils.getCodeNameByValue("9016", model.getProjInfo().getProjSrc());
                        vo.setProjSrcName(StringUtils.isEmpty(projSrcName) ? "" : projSrcName);
                    }

                }

                if (StringUtils.isNotEmpty(model.getFundSrc())) {
                    String fundSrcName = codeUtils.getCodeNameByValue("210", model.getFundSrc());
                    vo.setFundSrcName(StringUtils.isEmpty(fundSrcName) ? "" : fundSrcName);
                }

                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    String codeName = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                    vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(model.getPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204", model.getPlatProp());
                    vo.setPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(model.getSubPlatProp())) {
                    String codeName = codeUtils.getCodeNameByValue("204" + model.getPlatProp(), model.getSubPlatProp());
                    vo.setSubPlatPropName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                list.add(vo);
            }

            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "直投项目的出资主体", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundZtInveList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundZtInveList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, String groupId, String keyWord, String isDirect) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            if ("1".equals(isDirect)) {
                searchCondition.addParam("fundsType", isDirect);
            }
            if (StringUtils.isNotEmpty(keyWord)) {
                if ("".equals(keyWord.trim()) || keyWord.trim() == null) {
                    searchCondition.addParam("keyWord", "");
                } else {
                    keyWord = "'%" + keyWord.trim() + "%'";
                    searchCondition.addParam("keyWord", keyWord);
                }
            }
            searchCondition.addConditionNotEqualTo("bb.STATUS", "2");
            UserTypeEnmu userType = getLoginUserType();
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (userType.compareTo(UserTypeEnmu.EXTERNAL) == 0) {
                if (listRow != null && listRow.size() > 0) {
                    if (1005000 != listRow.get(0).getId()) {
                        searchCondition.addParam("USER_ID", this.getLoginUserId());
                    }
                } else {

                    searchCondition.addParam("bb.GROUP_ID", groupId);
                }

            } else {
                if (StringUtils.isNotEmpty(groupId)) {
                    searchCondition.addParam("GROUP_ID", groupId);
                }

            }
            //PageInfo<FundInfoModel> rows = fundInfoService.selectListProjPage(searchCondition);
            PageInfo<FundInfoModel> rows = fundInfoService.selectInveListPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                list.add(vo);
            }
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    //#################################外部用户-母基金##############################
    @ApiOperation(value = "外部用户新增母基金", notes = "外部用户新增母基金")
    @ApiImplicitParam(name = "FundInfoProjDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/saveFundFof", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveFundFof(@RequestBody FundInfoProjDTO dto) {
        try {
            FundInfoModel fundInfo = new FundInfoModel();
            BeanUtils.copyProperties(fundInfo, dto);

            EntBaseInfoModel ent = new EntBaseInfoModel();
            BeanUtils.copyProperties(ent, dto);
            ent.setCreditCode(dto.getSocialCode());
            if (StringUtils.isNotEmpty(dto.getTermStartDt())) {
                SimpleDateFormat fom = new SimpleDateFormat();
                Date time = fom.parse(dto.getTermStartDt());
                ent.setTermStart(time);

            }
            FundInveDescModel fundInveDescModel = new FundInveDescModel();
            BeanUtils.copyProperties(fundInveDescModel, dto);
            FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
            BeanUtils.copyProperties(fundShareInfoModel, dto);
            ProjInfoModel projInfoModel = new ProjInfoModel();
            BeanUtils.copyProperties(projInfoModel, dto);
            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties(projAppInfoModel, dto);
            //判断基金是否存在
            FundInfoModel fundInfo1 = new FundInfoModel();
            fundInfo1.setFundName(dto.getFundName());
            fundInfo1.setFundSrc(dto.getFundSrc());
            List<FundInfoModel> listFof = fundInfoService.selectList(fundInfo1);
            fundInfo.setUpdateBy(this.getLoginUserId());
            fundInfo.setUpdateDt(new Date());
            String typeStr = "";
            Integer cou = 0;
            if (StringUtils.isNotEmpty(dto.getOldFundCodeSign())) {
                cou = Integer.parseInt(dto.getOldFundCodeSign()) + 1;
                fundInfo.setFundCodeSign(String.valueOf(cou));
            }
            if (StringUtils.isNotEmpty(dto.getFundId())) {
                //编辑
                FundInfoModel fInfo = fundInfoService.selectById(dto.getFundId());
                if (fInfo != null) {
                    if (!fInfo.getFundName().equals(dto.getFundName())) {
                        if (listFof != null && listFof.size() > 0) {
                            baseResponse.setMsg("基金已存在！");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                    //母基金
                    if ("2".equals(fInfo.getOldSubPlatProp())) {
                        if (!"04".equals(fInfo.getFundCategory()) && !"04".equals(dto.getFundCategory())) {
                            //判断原来是一级基金没有修改为二级基金
                            fundInfo.setFundCodeSign("0");
                            // fundInfoService.update(fundInfo);
                        } else if (!"04".equals(fInfo.getFundCategory()) && "04".equals(dto.getFundCategory())) {
                            //原来是一级基金改为二级基金
                            FundInfoModel fund = fundInfoService.selectById(dto.getParentId());
                            if (fund != null) {
                                //查询最新的fundcode
                                List<FundInfoModel> list1 = fundInfoService.selectNewCode(fund.getFundCode(), cou);
                                if (list1 != null && list1.size() > 0) {
                                    String code1 = list1.get(0).getFundCode().substring(list1.get(0).getFundCode().length());
                                    if (Integer.parseInt(code1) <= 8) {
                                        String fundCode = fund.getFundCode() + "0" + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                    {
                                        String fundCode = fund.getFundCode() + "01";
                                        fundInfo.setFundCode(fundCode);
                                    }
                                } else {
                                    String fundCode = dto.getInveFundCode() + "01";
                                    fundInfo.setFundCode(fundCode);
                                }
                            }
                        } else if ("04".equals(fInfo.getFundCategory()) && !"04".equals(dto.getFundCategory())) {
                            //原来是二级级基金改为一级
                            FundInfoModel fundInfo3 = new FundInfoModel();
                            fundInfo3.setOldSubPlatProp(dto.getOldSubPlatProp());
                            fundInfo3.setFundCodeSign(dto.getOldFundCodeSign());
                            List<FundInfoModel> list = fundInfoService.selectByList(fundInfo3);
                            if (list != null && list.size() > 0) {
                                String fundCode1 = list.get(0).getFundCode();
                                if (StringUtils.isNotEmpty(fundCode1)) {
                                    if (Integer.parseInt(fundCode1) <= 8) {
                                        String fundCode = "0" + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else {
                                        String fundCode = String.valueOf(Integer.parseInt(fundCode1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                }
                            } else {
                                fundInfo.setFundCode("01");
                            }

                        } else if ("04".equals(fInfo.getFundCategory()) && "04".equals(dto.getFundCategory())) {
                            //判断上级基金是否修改吗
                            if (!fInfo.getParentId().equals(dto.getParentId())) {
                                List<FundInfoModel> listStr = fundInfoService.selectNewCode(dto.getInveFundCode(), cou);
                                if (listStr != null && listStr.size() > 0) {
                                    String code1 = listStr.get(0).getFundCode().substring(listStr.get(0).getFundCode().length() - 1);
                                    if (Integer.parseInt(code1) <= 8) {
                                        String fundCode = dto.getInveFundCode() + "0" + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else {
                                        String fundCode = dto.getInveFundCode() + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                } else {
                                    String fundCode = dto.getInveFundCode() + "01";
                                    fundInfo.setFundCode(fundCode);
                                }
                            }
                            //fundInfo.setFundCode(fundCode);
                        }
                    } else if ("4".equals(dto.getOldSubPlatProp()) || "5".equals(dto.getOldSubPlatProp())) {
                        //参股子基金 34
                        //判断出资主体是否修改
                        if (!fInfo.getFundId().equals(dto.getFundId())) {
                            // /判断出资主体是一级还是二级
                            if ("2".equals(dto.getInveTypeStr()) && StringUtils.isNotEmpty(dto.getInveParentId())) {
                                List<FundInfoModel> list11 = fundInfoService.selectNewCode(dto.getInveFundCode(), cou);
                                if (list11 != null && list11.size() > 0) {
                                    //排除母基金自己
                                    String code1 = list11.get(0).getFundCode().substring(list11.get(0).getFundCode().length() - 1);
                                    if (Integer.parseInt(code1) <= 8) {
                                        String fundCode = dto.getInveFundCode() + "0" + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else {
                                        String fundCode = dto.getInveFundCode() + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                } else {
                                    String fundCode = dto.getInveFundCode() + "01";
                                    fundInfo.setFundCode(fundCode);
                                }
                            } else if ("2".equals(dto.getInveTypeStr()) && StringUtils.isEmpty(dto.getInveParentId())) {
                                //出资主体是母基金
                                FundInfoModel fundInfo2 = new FundInfoModel();
                                fundInfo2.setOldSubPlatProp(dto.getOldSubPlatProp());
                                List<FundInfoModel> list1 = fundInfoService.selectByList(fundInfo2);
                                String fundCode11 = list1.get(0).getFundCode().substring(list1.get(0).getFundCode().length() - 1);
                                if (StringUtils.isNotEmpty(fundCode11)) {
                                    if (Integer.parseInt(fundCode11) <= 8) {
                                        String fundCode = dto.getInveFundCode() + "0000" + String.valueOf(Integer.parseInt(fundCode11) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else if (Integer.parseInt(fundCode11) <= 98) {
                                        String fundCode = dto.getInveFundCode() + "000" + String.valueOf(Integer.parseInt(fundCode11) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else if (Integer.parseInt(fundCode11) <= 998) {
                                        String fundCode = dto.getInveFundCode() + "00" + String.valueOf(Integer.parseInt(fundCode11) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else if (Integer.parseInt(fundCode11) <= 9998) {
                                        String fundCode = dto.getInveFundCode() + "0" + String.valueOf(Integer.parseInt(fundCode11) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else {
                                        String fundCode = dto.getInveFundCode() + String.valueOf(Integer.parseInt(fundCode11) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                }
                            }
                        }
                    }
                    fundInfoService.updateByInfo(fundInfo, projInfoModel, fundInveDescModel, projAppInfoModel, ent, typeStr);
                }
            } else {
                //新增
                //前台传一个标识给我，是母基金，代管基金，还是参股子基金
                String fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
                if ("2".equals(dto.getOldSubPlatProp()) || "3".equals(dto.getOldSubPlatProp())) {
                    if (listFof != null && listFof.size() > 0) {
                        baseResponse.setMsg("基金已存在！");
                        return JSONObject.toJSONString(baseResponse);
                    }
                } else {
                    if (listFof != null && listFof.size() > 0) {
                        //表示是1的时候，基金存在只新增项目
                        typeStr = "1";
                        fundId = listFof.get(0).getFundId();
                        System.out.print("listFof.size()*****************************************" + listFof.size());
                    }
                }
                System.out.print("typeStr*****************************************" + typeStr);
                //查询出资主体
                FundInfoModel fundInveM = null;
                if (StringUtils.isNotEmpty(dto.getInveId())) {
                    fundInveM = fundInfoService.selectById(dto.getInveId());
                    if (fundInveM == null) {
                        baseResponse.setMsg("出资主体不正确！");
                        return JSONObject.toJSONString(baseResponse);
                    }
                }

                fundInfo.setFundId(fundId);
                fundInfo.setCreateBy(this.getLoginUserId());
                fundInfo.setCreateDt(new Date());
                fundInfo.setUpdateDt(new Date());
                fundInfo.setUpdateBy(this.getLoginUserId());

                //查询母基金是否存在
                FundInfoModel fundInfo2 = new FundInfoModel();
                fundInfo2.setOldSubPlatProp(dto.getOldSubPlatProp());
                List<FundInfoModel> list = fundInfoService.selectByList(fundInfo2);
                //母基金
                if ("2".equals(dto.getOldSubPlatProp())) {
                    if (list != null && list.size() > 0) {
                        if ("04".equals(dto.getFundCategory()) && StringUtils.isNotEmpty(dto.getParentId())) {
                            //判断类型为4,是二级母基金
                            FundInfoModel fund = fundInfoService.selectById(dto.getParentId());
                            if (fund != null) {
                                //查询最新的fundcode
                                List<FundInfoModel> list1 = fundInfoService.selectNewCode(fund.getFundCode(), cou);
                                if (list1 != null && list1.size() > 0) {
                                    //排除母基金自己
                                    String code1 = list1.get(0).getFundCode().substring(list1.get(0).getFundCode().length() - 1);
                                    if (Integer.parseInt(code1) <= 8) {
                                        String fundCode = fund.getFundCode() + "0" + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else {
                                        String fundCode = fund.getFundCode() + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                } else {
                                    String fundCode = fund.getFundCode() + "01";
                                    fundInfo.setFundCode(fundCode);
                                }
                            }

                        } else {
                            //是母基金
                            String fundCode1 = list.get(0).getFundCode();
                            fundInfo.setFundCodeSign("0");
                            if (StringUtils.isNotEmpty(fundCode1)) {
                                if (Integer.parseInt(fundCode1) <= 8) {
                                    String fundCode = "0" + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                } else {
                                    String fundCode = String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                }
                            }
                        }
                    } else {
                        String fundCode = "01";
                        fundInfo.setFundCode(fundCode);
                    }
                } else if ("3".equals(dto.getOldSubPlatProp())) {
                    //代管基金
                    if (list != null && list.size() > 0) {
                        String code1 = list.get(0).getFundCode().substring(2);
                        if (Integer.parseInt(code1) <= 8) {
                            String fundCode = "DG0" + String.valueOf(Integer.parseInt(code1) + 1);
                            fundInfo.setFundCode(fundCode);
                        } else {
                            String fundCode = "DG" + String.valueOf(Integer.parseInt(code1) + 1);
                            fundInfo.setFundCode(fundCode);
                        }
                    } else {
                        String fundCode = "DG01";
                        fundInfo.setFundCode(fundCode);
                    }

                } else if ("4".equals(dto.getOldSubPlatProp()) || "5".equals(dto.getOldSubPlatProp())) {
                    //基金投资管理（参股子基金,专项基金）
                    if (list != null && list.size() > 0) {
                        //判断出资主体是一级母基金，二级母基金还是，（参股、专项基金）
                        if (StringUtils.isNotEmpty(dto.getInveParentId()) || "4".equals(dto.getInveTypeStr()) || "5".equals(dto.getInveTypeStr())) {
                            //是二级母基金,或者参股子基金,或者专项基金
                            // FundInfoModel fund = fundInfoService.selectById(dto.getParentId());
                            if (StringUtils.isNotEmpty(dto.getInveFundCode())) {
                                //查询最新的fundcode
                                List<FundInfoModel> list2 = fundInfoService.selectNewCodeName(dto.getInveFundCode(), cou);
                                if (list2 != null && list2.size() > 1) {
                                    //排除母基金自己
                                    String code1 = list2.get(0).getFundCode().substring(list2.get(0).getFundCode().length() - 1);
                                    if (Integer.parseInt(code1) <= 8) {
                                        String fundCode = fundInveM.getFundCode() + "00" + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else if (Integer.parseInt(code1) <= 98) {
                                        String fundCode = fundInveM.getFundCode() + "0" + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    } else {
                                        String fundCode = fundInveM.getFundCode() + String.valueOf(Integer.parseInt(code1) + 1);
                                        fundInfo.setFundCode(fundCode);
                                    }
                                } else {
                                    String fundCode = fundInveM.getFundCode() + "001";
                                    fundInfo.setFundCode(fundCode);
                                }
                            }

                        } else {
                            String fundCode1 = list.get(0).getFundCode().substring(list.get(0).getFundCode().length() - 1);
                            if (StringUtils.isNotEmpty(fundCode1)) {
                                if (Integer.parseInt(fundCode1) <= 8) {
                                    String fundCode = fundInveM.getFundCode() + "0000" + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                } else if (Integer.parseInt(fundCode1) <= 98) {
                                    String fundCode = fundInveM.getFundCode() + "000" + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                } else if (Integer.parseInt(fundCode1) <= 998) {
                                    String fundCode = fundInveM.getFundCode() + "00" + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                } else if (Integer.parseInt(fundCode1) <= 9998) {
                                    String fundCode = fundInveM.getFundCode() + "0" + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                } else {
                                    String fundCode = fundInveM.getFundCode() + String.valueOf(Integer.parseInt(fundCode1) + 1);
                                    fundInfo.setFundCode(fundCode);
                                }
                            }
                        }
                    } else {
                        if (StringUtils.isNotEmpty(dto.getParentId()) || "4".equals(dto.getInveTypeStr())) {
                            String fundCode = fundInveM.getFundCode() + "001";
                            fundInfo.setFundCode(fundCode);
                        } else {
                            String fundCode = fundInveM.getFundCode() + "00001";
                            fundInfo.setFundCode(fundCode);
                        }
                    }

                }
                fundInfoService.insertByInfo(fundInfo, projInfoModel, fundInveDescModel, projAppInfoModel, ent, typeStr);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value = "获取平台管理基金列表", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoFofList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoFofList(@RequestParam("length") int length, @RequestParam("currPage") int currPage,  String groupId,
                                  String fundName, String fundCategory, String oldSubPlatProp) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);

            if (fundName != null && !"".equals(fundName)) {
                fundName = "'%" + fundName.trim() + "%'";
                searchCondition.addParam("keyWord", fundName);
            }

            if (StringUtils.isNotEmpty(fundCategory)) {
                List<String> fundCategoryS = Arrays.asList(fundCategory.split(","));
                searchCondition.addConditionIn("a.FUNDCATEGORY", fundCategoryS);
            }
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (StringUtils.isNotEmpty(oldSubPlatProp)) {
                //!=7,是其他基金比较特殊， 不等于2是母基尽平台母基金的查询条件
                if(!"7".equals(oldSubPlatProp) && !"2".equals(groupId)){
                    if("1,7".equals(oldSubPlatProp)){
                        searchCondition.addParam("OLD_SUB_PLAT_PROP", oldSubPlatProp);
                    }else{
                        List<String> oldSubPlatPropS = Arrays.asList(oldSubPlatProp.split(","));
                        searchCondition.addConditionIn("OLD_SUB_PLAT_PROP", oldSubPlatPropS);
                    }

                }
                //2是基金平台，3是直投平台
                searchCondition.addParam("OLD_SUB_PLAT_PROP", oldSubPlatProp);

            }
            searchCondition.addParam("groupId", groupId);
            PageInfo<FundInfoModel> rows = fundInfoService.selectFofListPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                if(model.getStartDate()!=null){
                    vo.setSetupDt(model.getStartDate());
                }
                //查询已投项目数
                Integer projNum = fundInfoService.selectByProjNum(model.getFundId());
                if (projNum == null) {
                    vo.setProjNum("0");
                } else {
                    vo.setProjNum(String.valueOf(projNum));
                }
                //查询出资成功的数据
                Double paySumAmount = projInfoService.selectPassPayAmount(model.getFundId());
                if (paySumAmount != null) {
                    vo.setPaySumAmount(paySumAmount);
                } else {
                    vo.setPaySumAmount(0.0);
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    String codeName = codeUtils.getCodeNameByValue("210", model.getFundStruct());
                    vo.setFundStructName(StringUtils.isEmpty(codeName) ? "" : codeName);
                }
                if (StringUtils.isNotEmpty(vo.getFundGenre())) {
                    vo.setFundGenreName(codeUtils.getCodeNameByValue("1011", model.getFundGenre()));
                }
                if (StringUtils.isNotEmpty(model.getFundCategory())) {
                    String fundCategoryName = codeUtils.getCodeNameByValue("9002", model.getFundCategory());
                    vo.setFundCategoryName(StringUtils.isEmpty(fundCategoryName) ? "" : fundCategoryName);
                }
                list.add(vo);
            }
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "查询下拉款基金", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfo/fundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("groupId") String groupId, String type, String fundName) {
        try {
            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("a.GROUP_ID", groupId);
            //searchCondition.setSearchBean(searchBean);
            if ("1".equals(type)) {
                //三级基金下拉款
                searchCondition.addParam("fundSrc", type);
            } else if ("2".equals(type)) {
                //三级基金，四级基金,spv
                searchCondition.addParam("fundSrc", type);
            }
            if (fundName != null && !"".equals(fundName)) {
                fundName = "'%" + fundName + "%'";
                searchCondition.addParam("keyWord", fundName);
            }
            List<AppRoleModel> listRow = appRoleService.selectByUserId(this.getLoginUserId());
            if (listRow != null && listRow.size() > 0) {
                if (1005002 == listRow.get(0).getId() || 1005003 == listRow.get(0).getId()) {
                    searchCondition.addParam("userId", this.getLoginUserId());
                }
            }
            PageInfo<FundInfoModel> rows = fundInfoService.getInfoListPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value = "金财一期/基金信息-列表", notes = "项目投管-列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/getJcFundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getJcFundList(@RequestBody ProjInfoSearchBean searchBean) {
        try {
            if (searchBean.getKeyword() != null) {
                if ("".equals(searchBean.getKeyword().trim()) || searchBean.getKeyword().trim() == null) {
                    searchBean.setKeyword("");
                } else {
                    searchBean.setKeyword(searchBean.getKeyword().trim());
                }
            }
            if (StringUtils.isNotEmpty(searchBean.getFundSts())) {
                List<String> fundSts = Arrays.asList(searchBean.getFundSts().split(","));
                searchCondition.addConditionIn("FUND_STS", fundSts);
            }
            searchCondition.setSearchBean(searchBean);
            PageInfo<NjFundInfoModel> rows = fundInfoService.getJcFundList(searchCondition);
            List<NjFundInfoVO> list = new ArrayList<NjFundInfoVO>();
            for (NjFundInfoModel model : rows.getList()) {
                NjFundInfoVO vo = new NjFundInfoVO();
                BeanUtils.copyProperties(vo, model);
                //254
                if (StringUtils.isNotEmpty(model.getFundSts())) {
                    String fundSName = codeUtils.getCodeNameByValue("2540", model.getFundSts());
                    vo.setFundStsName(StringUtils.isEmpty(fundSName) ? "" : fundSName);
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


    @ApiOperation(value = "直投项目的出资主体", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "平台代码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundJcInveList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundJcInveList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, String fundSrc, String keyWord) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            if (StringUtils.isNotEmpty(keyWord)) {
                if ("".equals(keyWord.trim()) || keyWord.trim() == null) {
                    searchCondition.addParam("keyWord", "");
                } else {
                    keyWord = "'%" + keyWord.trim() + "%'";
                    searchCondition.addParam("keyWord", keyWord);
                }
            }
            searchCondition.addConditionNotEqualTo("STATUS", "2");
            //金财合盈
            searchCondition.addConditionEqualTo("FUND_SRC", fundSrc);
            PageInfo<FundInfoModel> rows = fundInfoService.selectJcInveListPage(searchCondition);
            //PageInfo<FundInfoModel> rows = fundInfoService.selectInveListPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                list.add(vo);
            }
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    //基金投管列表上的，出资主体 查询条件
    @ApiOperation(value = "基金投管列表上的，出资主体", notes = "获取平台管理基金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fundInfoInveList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fundInfoInveList(@RequestParam("length") int length, @RequestParam("currPage") int currPage,
                                   String fundName) {
        try {
            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            if (fundName != null && !"".equals(fundName)) {
                fundName = "'%" + fundName.trim() + "%'";
                searchCondition.addParam("keyWord", fundName);
            }

            PageInfo<FundInfoModel> rows = fundInfoService.fundInfoInveList(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoModel model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);

                list.add(vo);
            }
            dataTablesResponse.setData(list);
            dataTablesResponse.setTotalCount(Long.valueOf(rows.getTotal()).intValue(), rows.getPageNum(), rows.getPageSize());
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

//###############################一期老系统######################################################

    @ApiIgnore
    @ApiOperation(value = "老系统参股基金", notes = "基金阶段")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/cgFund/cgSave", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String cgSave(@RequestBody FundInfoCgDTO dto) {
        try {
            String userId = this.getLoginUserId();
            FundInfoModel fundInfoModel = new FundInfoModel();
            BeanUtils.copyProperties(fundInfoModel, dto);
            fundInfoModel.setUpdateBy(userId);
            fundInfoModel.setUpdateDt(new Date());
            FundInveDescModel inveDes = new FundInveDescModel();
            BeanUtils.copyProperties(inveDes, dto);
            FundRecordInfoModel record = new FundRecordInfoModel();
            BeanUtils.copyProperties(record, dto);
            record.setRemark(dto.getRecordInfo());
            record.setRcdAdd(dto.getRcdAdd());
            record.setPkId(dto.getPkId2());


            FundMemberModel fundMum = new FundMemberModel();
            fundMum.setMemberName(dto.getFundNums());
            fundMum.setPkId(dto.getPkId1());
            //判断该基金是否存在
            if (StringUtils.isEmpty(dto.getFundId())) {
                //查询名称是否存在
                FundInfoModel mo = new FundInfoModel();
                mo.setRegName(dto.getRegName());
                List<FundInfoModel> list = fundInfoService.selectList(mo);
                if (list != null && !list.isEmpty()) {
                    baseResponse.error("-1", "系统已存在该基金");
                    return JSONObject.toJSONString(baseResponse);
                }
            }else{
               FundInfoModel fund= fundInfoService.selectById(dto.getFundId());
               if(!dto.getRegName().equals(fund.getRegName())){
                   FundInfoModel mo = new FundInfoModel();
                   mo.setRegName(dto.getRegName());
                   List<FundInfoModel> list = fundInfoService.selectList(mo);
                   if (list != null && !list.isEmpty()) {
                       baseResponse.error("-1", "系统已存在该基金");
                       return JSONObject.toJSONString(baseResponse);
                   }
               }
            }
            fundInfoService.savaFundth(fundInfoModel, inveDes, record, fundMum);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getCgFundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCgFundList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, String keyword) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);

            if (keyword != null && !"".equals(keyword)) {
                keyword = "'%" + keyword + "%'";
                searchCondition.addParam("keyWord", keyword);
            }
            //查询角色       //判断是基金管理人还是
            AppUserModel appUser=  appUserService.selectById(this.getLoginUserId());
            AppUserroleModel appUserroleModel=new AppUserroleModel();
            appUserroleModel.setUserid(Long.parseLong(this.getLoginUserId()));
            List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
            //直投平台
            String typeStr="";
            if (appUser!=null&&5==appUser.getUsertype()) {
                    for(AppUserroleModel role:listRole){
                        //基金管理人
                        if(10031L==role.getRoleid()){
                            typeStr="1";
                            break;
                        }
                        //出资人代表
                        if(10032L==role.getRoleid()){
                            typeStr="2";
                            break;
                        }
                    }

            }else if(appUser!=null && 1==appUser.getUsertype()) {
                typeStr="3";

            }

            searchCondition.addParam("typeStr",typeStr);
            searchCondition.addParam("userId",this.getLoginUserId());
            searchCondition.addParam("loginName",appUser.getLoginname());

      /*      searchCondition.addParam("typeStr","");
            searchCondition.addParam("userId","");
            searchCondition.addParam("loginName","");*/
            //判断
            PageInfo<FundInfoResult> rows = fundInfoService.selectCgListPage(searchCondition);
            List<FundInfoVO> list = new ArrayList<FundInfoVO>();
            for (FundInfoResult model : rows.getList()) {
                FundInfoVO vo = new FundInfoVO();
                BeanUtils.copyProperties(vo, model);
                if(model.getUpDt()!=null){
                    vo.setUpdateDt(model.getUpDt());
                }
                if (StringUtils.isNotEmpty(model.getOldSubPlatProp())) {
                    vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("258", model.getOldSubPlatProp()));
                }

                if (StringUtils.isNotEmpty(model.getOrganStruct())) {
                    vo.setOrganStruct(codeUtils.getCodeNameByValue("21010", model.getOrganStruct()));
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



    @ApiOperation(value = "老系统-参股子基金详情", notes = "根据url的id来获取合伙人会议细信息")
    @ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getCgFundDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String promoterInfo(@RequestParam("projId") String projId,String fundId) {
        try {
            FundCgVO vo = new FundCgVO();
          ProjInfoModel proj=projInfoService.selectById(projId);
          if(proj!=null){
              fundId=proj.getProjObject();
              vo.setProjModel(proj);
          }
            FundInfoModel model = fundInfoService.selectById(fundId);
            if (model != null) {
                //根据基金id查询所属企业
                if(StringUtils.isNotEmpty(model.getEnteId())){
                    //查询企查查的数据
                    EntBaseInfoModel entBaseInfoModel=entBaseInfoService.selectById(model.getEnteId());
                    if(entBaseInfoModel!=null){
                        if(entBaseInfoModel.getStartDate()!=null){
                            model.setSetupDt(entBaseInfoModel.getStartDate());
                        }
                        if(StringUtils.isNotEmpty(entBaseInfoModel.getCreditCode())){
                            model.setSocialCode(entBaseInfoModel.getCreditCode());
                        }
                        if(StringUtils.isNotEmpty(entBaseInfoModel.getAddressDetails())){
                            model.setRegAdd(entBaseInfoModel.getAddressDetails());
                        }
                    }

                }
                vo.setOtherFile(model.getOtherFile());
                if (StringUtils.isNotEmpty(model.getOldSubPlatProp())) {
                    vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("258", model.getOldSubPlatProp()));
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    vo.setFundStructName(codeUtils.getCodeNameByValue("210", model.getFundStruct()));
                }
                if (StringUtils.isNotEmpty(model.getOrganStruct())) {
                    vo.setOrganStructName(codeUtils.getCodeNameByValue("21010", model.getOrganStruct()));
                }
                if (StringUtils.isNotEmpty(model.getAppDep())) {
                    vo.setAppDepName(codeUtils.getCodeNameByValue("90005", model.getAppDep()));
                }
                if (StringUtils.isNotEmpty(model.getManDep())) {
                    vo.setManDepName(codeUtils.getCodeNameByValue("90020", model.getManDep()));
                }
                if (StringUtils.isNotEmpty(model.getCurrentCurrency())) {
                    vo.setCurrentCurrencyName(codeUtils.getCodeNameByValue("103", model.getCurrentCurrency()));
                }
                if (StringUtils.isNotEmpty(model.getOldFundGenre())) {
                    vo.setOldFundGenreName(codeUtils.getCodeNameByValue("9026", model.getOldFundGenre()));
                }
                if (StringUtils.isNotEmpty(model.getFundSts())) {
                    vo.setFundStsName(codeUtils.getCodeNameByValue("2540", model.getFundSts()));
                }
                if (StringUtils.isNotBlank(model.getOldSubPlatProp())) {
                    vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("12306", model.getOldSubPlatProp()));
                }
            }


            FundInveDescModel desM = fundInveDescService.selectById(fundId);
            FundMemberModel fundM = new FundMemberModel();
            fundM.setFundId(fundId);
            List<FundMemberModel> list = fundMemberService.selectList(fundM);
            String memberName = "";
            if (list != null && !list.isEmpty()) {
                memberName = list.get(0).getMemberName();
                vo.setPkId1(list.get(0).getPkId());
            }
            String recordInfo = "";
            String rcdAdd = "";
            String rcdAddName = "";
            FundRecordInfoModel fundRecod = new FundRecordInfoModel();
            fundRecod.setFundId(fundId);
            List<FundRecordInfoModel> listR = fundRecordInfoService.selectList(fundRecod);
            if (listR != null && !listR.isEmpty()) {
                recordInfo = listR.get(0).getRemark();
                rcdAdd = listR.get(0).getRcdAdd();
                vo.setRcdAdd(rcdAdd);
                vo.setRecordInfo(recordInfo);
                vo.setPkId2(listR.get(0).getPkId());
                if (StringUtils.isNotEmpty(rcdAdd)) {
                    List<String> rcdAddStr = Arrays.asList(rcdAdd.split(","));
                    for (String rcdS : rcdAddStr) {
                        if (StringUtils.isNotEmpty(rcdS)) {
                            if (StringUtils.isNotEmpty(rcdAdd)) {
                                String nameStr = codeUtils.getCodeNameByValue("2567", rcdS);
                                if (StringUtils.isNotEmpty(rcdAddName)) {
                                    rcdAddName = rcdAddName + "," + nameStr;
                                } else {
                                    rcdAddName = nameStr;
                                }
                            }


                        }
                    }

                }

            }
            vo.setRcdAddName(rcdAddName);
            vo.setFundDesc(desM);
            vo.setModel(model);
            vo.setMemberName(memberName);
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

    @ApiOperation(value = "删除基金", notes = "删除基金管理人库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminIds", value = "主键拼接", required = true, dataType = "String"),
    })
    @GetMapping(value = "/fundDel/delete", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(String fundId) {

        try {
            //判断是否可以删除
            //判断投了哪些企业项目
            ProjInfoModel proj = new ProjInfoModel();
            proj.setInveId(fundId);
            proj.setProjType("1");
            List<ProjInfoModel> list = projInfoService.selectList(proj);
            if (list != null && !list.isEmpty()) {
                baseResponse.setMsg("已存在业务数据，不能删除");
                return JSONObject.toJSONString(baseResponse);
            }
            //判断是否存在
            ProjInfoModel model = new ProjInfoModel();
            model.setProjObject(fundId);
            model.setProjType("2");
            List<ProjInfoModel> listStr = projInfoService.selectList(model);
            if (listStr != null && !listStr.isEmpty()) {
                baseResponse.setMsg("已存在业务数据，不能删除");
                return JSONObject.toJSONString(baseResponse);
            }
            fundInfoService.deleteById(fundId);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);

    }

    @ApiOperation(value="老系统-母基金详情", notes="详情")
    @ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fofFund/fofDetainfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fofDetainfo(String fundId,@RequestParam(value = "projId") String projId){
        try {
            FundCgVO vo = new FundCgVO();
            if(StringUtils.isNotEmpty(projId)){
             ProjInfoModel moProj=   projInfoService.selectById(projId);
             vo.setProjModel(moProj);
            }
            FundInfoModel  model=fundInfoService.selectById(fundId);
            if (model != null) {
                vo.setModel(model);
                if(StringUtils.isNotEmpty(model.getEnteId())){
                  EntBaseInfoModel entBase=  entBaseInfoService.selectById(model.getEnteId());
                    vo.setEntBase(entBase);
                }

                if (StringUtils.isNotEmpty(model.getOldSubPlatProp())) {
                    vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("258", model.getOldSubPlatProp()));
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    vo.setFundStructName(codeUtils.getCodeNameByValue("210", model.getFundStruct()));
                }
                if (StringUtils.isNotEmpty(model.getOrganStruct())) {
                    vo.setOrganStructName(codeUtils.getCodeNameByValue("21010", model.getOrganStruct()));
                }

                if (StringUtils.isNotEmpty(model.getManDep())) {
                    vo.setManDepName(codeUtils.getCodeNameByValue("90020", model.getManDep()));
                }
                if (StringUtils.isNotEmpty(model.getCurrentCurrency())) {
                    vo.setCurrentCurrencyName(codeUtils.getCodeNameByValue("103", model.getCurrentCurrency()));
                }
                if (StringUtils.isNotEmpty(model.getOldFundGenre())) {
                    vo.setOldFundGenreName(codeUtils.getCodeNameByValue("9026", model.getOldFundGenre()));
                }
                if (StringUtils.isNotEmpty(model.getFundSts())) {
                    vo.setFundStsName(codeUtils.getCodeNameByValue("2540", model.getFundSts()));
                }

                if (StringUtils.isNotEmpty(model.getFundCodeType())) {
                    vo.setFundCodeTypeName(codeUtils.getCodeNameByValue("9002", model.getFundCodeType()));
                }
                if (StringUtils.isNotEmpty(model.getInveType())) {
                    vo.setInveTypeName(codeUtils.getCodeNameByValue("4444", model.getInveType()));
                }

                if (StringUtils.isNotBlank(model.getOldSubPlatProp())) {
                    vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("12306", model.getOldSubPlatProp()));
                }
                if (StringUtils.isNotEmpty(model.getFundCategory())) {
                    String fundCategoryName = codeUtils.getCodeNameByValue("9002", model.getFundCategory());
                    vo.setFundCategoryName(StringUtils.isEmpty(fundCategoryName) ? "" : fundCategoryName);
                }
                if (StringUtils.isNotEmpty(model.getIsRecord())) {
                    vo.setIsRecordName(codeUtils.getCodeNameByValue("248", model.getIsRecord()));
                }
                //省级资金来源
                if (StringUtils.isNotEmpty(model.getGovFundSrc())) {
                    String govFundSrcName = codeUtils.getCodeNameByValue("90019", model.getGovFundSrc());
                    vo.setGovFundSrcName(StringUtils.isEmpty(govFundSrcName) ? "" : govFundSrcName);
                }

                //批准设立部门
                if (StringUtils.isNotEmpty(model.getAppDep())) {
                    String appDepName = codeUtils.getCodeNameByValue("90005", model.getAppDep());
                    vo.setAppDepName(StringUtils.isEmpty(appDepName) ? "" : appDepName);
                }

            }


            FundMemberModel fundM = new FundMemberModel();
            fundM.setFundId(fundId);
            List<FundMemberModel> list = fundMemberService.selectList(fundM);
            String memberName = "";
            if (list != null && !list.isEmpty()) {
                //部门负责人
                memberName = list.get(0).getMemberName();
                vo.setMemberName(memberName);
                vo.setMemberName(list.get(0).getMemberId());
                vo.setPkId1(list.get(0).getPkId());
            }
            FundInveDescModel desM = fundInveDescService.selectById(fundId);
            vo.setFundDesc(desM);

            //查询出资人代办
     /*       FundViewModel viewModel=new FundViewModel();
            viewModel.setFundId(fundId);
            List<FundViewModel> listV=fundViewService.selectList(viewModel);
            String adminNames="";
            String adminIds="";
            for(FundViewModel fundViewModel:listV){
                if(StringUtils.isNotEmpty(fundViewModel.getAdminName())){
                    if(StringUtils.isNotEmpty(adminNames)){
                        adminNames=adminNames+","+fundViewModel.getAdminName();
                    }else{
                        adminNames=adminNames;
                    }
                }
                if(StringUtils.isNotEmpty(fundViewModel.getAdminId())){
                    if(StringUtils.isNotEmpty(adminIds)){
                        adminIds=adminIds+","+fundViewModel.getAdminId();
                    }else{
                        adminIds=adminIds;
                    }
                }
            }*/
           /* vo.setAdminNames(adminNames);
            vo.setAdminIds(adminIds);
            vo.setListView(listV);*/

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

    @ApiOperation(value="老系统-母基金项目列表", notes="详情")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getFofFundList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getFofFundList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, String keyword) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            //判断是基金管理人还是
            //查询角色       //判断是基金管理人还是
            AppUserModel appUser=  appUserService.selectById(this.getLoginUserId());
            searchCondition.addParam("loginName",appUser.getLoginname());
            AppUserroleModel appUserroleModel=new AppUserroleModel();
            appUserroleModel.setUserid(Long.parseLong(this.getLoginUserId()));
            List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
            //直投平台
            String typeStr="";
            if (appUser!=null&&5==appUser.getUsertype()) {
                for(AppUserroleModel role:listRole){
                    //基金管理人
                    if(10031==role.getRoleid()){
                        typeStr="1";
                        break;
                    }
                    //出资人代表
                    if(10032==role.getRoleid()){
                        typeStr="2";
                        break;
                    }
                }

            }else if(appUser!=null&&1==appUser.getUsertype()) {
                typeStr="3";

            }
            searchCondition.addParam("typeStr",typeStr);
            if (keyword != null && !"".equals(keyword)) {
                keyword = "'%" + keyword + "%'";
                searchCondition.addParam("keyWord", keyword);
            }
            //PageInfo<FundInfoModel> rows = fundInfoService.selectCgListPage(searchCondition);
            PageInfo<FundFofInfoModel> rows = fundInfoService.getFofListPage(searchCondition);
            List<FundFofInfoVO> list = new ArrayList<FundFofInfoVO>();
            for (FundFofInfoModel model : rows.getList()) {
                FundFofInfoVO vo = new FundFofInfoVO();
                BeanUtils.copyProperties(vo, model);
                if(model.getUpDt()!=null){
                    vo.setUpdateDt(model.getUpDt());
                }
                if (StringUtils.isNotEmpty(model.getIsRecord())) {
                    vo.setIsRecordName(codeUtils.getCodeNameByValue("248", model.getIsRecord()));
                }
                if (StringUtils.isNotEmpty(model.getFundSts())) {
                    vo.setFundStsName(codeUtils.getCodeNameByValue("2540", model.getFundSts()));
                }
                if (StringUtils.isNotEmpty(model.getFundStruct())) {
                    vo.setFundStruct(codeUtils.getCodeNameByValue("210", model.getFundStruct()));
                }
                if (StringUtils.isNotEmpty(model.getOldSubPlatProp())) {
                    vo.setOldSubPlatPropName(codeUtils.getCodeNameByValue("258", model.getOldSubPlatProp()));
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
    @ApiOperation(value = "老系统母基金-基金", notes = "基金阶段")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/fofFund/mSave", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String mSave(@RequestBody FundInfoCgDTO dto) {
        try {
            String userId = this.getLoginUserId();
            FundInfoModel fundInfoModel = new FundInfoModel();
            BeanUtils.copyProperties(fundInfoModel, dto);
            fundInfoModel.setUpdateBy(userId);
            fundInfoModel.setUpdateDt(new Date());
            FundInveDescModel inveDes = new FundInveDescModel();
            BeanUtils.copyProperties(inveDes, dto);

            FundMemberModel fundMum = new FundMemberModel();
            fundMum.setMemberName(dto.getFundLead());
            fundMum.setFundId(dto.getFundId());
            fundMum.setMemberId(dto.getFundLeadId());
            fundMum.setPkId(dto.getPkId1());
            //判断该基金是否存在
            if (StringUtils.isEmpty(dto.getFundId())) {
                //查询名称是否存在
                FundInfoModel mo = new FundInfoModel();
                mo.setRegName(dto.getRegName());
                List<FundInfoModel> list = fundInfoService.selectList(mo);
                if (list != null && !list.isEmpty()) {
                    baseResponse.error("-1", "系统已存在该基金");
                    return JSONObject.toJSONString(baseResponse);
                }
            }
            fundInfoService.savaFundF(fundInfoModel, inveDes, fundMum);

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value="老系统-母基金-投资基金项目", notes="新增合同")
    @ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/projFof/saveFofProjInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveFofProjInfo(@RequestBody ProjInfoEntDTO dto){
        try {
            ProjInfoModel model=new ProjInfoModel();
            org.springframework.beans.BeanUtils.copyProperties( dto,model);

            ProjAppInfoModel appModel=new ProjAppInfoModel();
            org.springframework.beans.BeanUtils.copyProperties( dto,appModel);
            String fundCode="";

            //新增
            if(StringUtils.isEmpty(dto.getProjId())){
                //项目编号 基金编号+年+四位顺序号，顺序号不需要按照年重新编制
                //查询出资主体的编号
                String  projNo="";
                if(StringUtils.isNotEmpty(dto.getInveId())){
                    FundInfoModel  fundModel=fundInfoService.selectById(dto.getInveId());
                    if(fundModel!=null){
                        fundCode= fundModel.getFundCode();
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
                FundInfoModel fundMath= fundInfoService.selectById(dto.getProjObject());
                FundInfoModel fundm=new FundInfoModel();
                if(StringUtils.isEmpty(fundMath.getFundCode())){
                    String  childFundCode=getFundCode(dto.getProjObject(), fundCode, dto.getInveId());
                    fundm.setFundCode(childFundCode);
                }
                fundm.setFundId(dto.getProjObject());
                //查询被投对象是否有出资主体
                if(fundMath!=null&&StringUtils.isEmpty(fundMath.getMotherFundName())){
                    fundm.setMotherFundName(dto.getInveName());
                    fundm.setMotherFundId(dto.getInveId());
                }

                if(StringUtils.isNotEmpty(dto.getProjObject())){
                    fundInfoService.update(fundm);
                }
                projInfoService.saveInfoE(appModel,model);

            }else{
                FundInfoModel  fundModel=fundInfoService.selectById(dto.getInveId());
                if(fundModel!=null){
                    fundCode= fundModel.getFundCode();
                }
                ProjInfoModel proj= projInfoService.selectById(dto.getProjId());
                if(proj!=null && !proj.getProjObject().equals(dto.getProjObject())){
                    //判断被投对象是否被修改
                    String  childFundCode=getFundCode(dto.getProjObject(), fundCode, dto.getInveId());
                    FundInfoModel fundm=new FundInfoModel();
                    fundm.setFundId(dto.getProjObject());
                    fundm.setFundCode(childFundCode);
                    if(StringUtils.isNotEmpty(dto.getProjObject())){
                        fundInfoService.update(fundm);
                    }
                }
                if(proj!=null && proj.getProjObject().equals(dto.getProjObject())&& !proj.getInveId().equals(dto.getInveId())){
                    //判断被投对象是否被修改
                    String  childFundCode=getFundCode(dto.getProjObject(), fundCode, dto.getInveId());
                    FundInfoModel fundm=new FundInfoModel();
                    fundm.setFundId(dto.getProjObject());
                    fundm.setFundCode(childFundCode);
                    if(StringUtils.isNotEmpty(dto.getProjObject())){
                        fundInfoService.update(fundm);
                    }
                }


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

     private String getFundCode(String projObject,String fundCode,String inveId){
         //判断是否是首次投该基金如果是则生成子基金编号
         ProjInfoModel projModel=new ProjInfoModel();
         projModel.setProjType("2");
         projModel.setProjObject(projObject);
         List<ProjInfoModel>  list=projInfoService.selectList(projModel);
         String childFundCode = "";
         if(list==null||list.isEmpty()){
             //首次投资，生成子基金编号更新到BD_T_FUND_INFO表中
             //母基金编号扩充至四位
             if(fundCode.length() < 4){
                 fundCode = fundCode + "00";
             }
         }
         //查询母基金投资了多少子基金项目
         ProjInfoModel projStr=new ProjInfoModel();
         projStr.setInveId(inveId);
         List<ProjInfoModel> listFund=projInfoService.selectNumList(projStr);
         if(listFund!=null&&!listFund.isEmpty()){
             Integer num3 = Integer.valueOf(listFund.size());
             //扩充至三位
             if(num3+1<10){
                 childFundCode = fundCode + "00" + String.valueOf(num3+1);
             }else if(num3+1<100){
                 childFundCode = fundCode + "0" + String.valueOf(num3+1);
             }else{
                 childFundCode = fundCode + String.valueOf(num3+1);
             }
         }else{
             childFundCode = fundCode + "00" + String.valueOf(1);
         }
        return childFundCode;
     }


    @ApiOperation(value="老系统-获取股权投资的-出资主体（母基金和参股基金）", notes="详情")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/ente/getEnteInveList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getEnteInveList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, String keyword,String oldSubPlatProp) {
        try {
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionNotEqualTo("a.status","2");
            //searchCondition.addConditionEqualTo("a.create_by",this.getLoginUserId());
            //查询角色       //判断是基金管理人还是
            AppUserModel appUser=  appUserService.selectById(this.getLoginUserId());
            AppUserroleModel appUserroleModel=new AppUserroleModel();
            appUserroleModel.setUserid(Long.parseLong(this.getLoginUserId()));
            List<AppUserroleModel> listRole=appUserroleService.selectList(appUserroleModel);
            //直投平台
            String typeStr="";
            if (appUser!=null&&5==appUser.getUsertype()) {
                for(AppUserroleModel role:listRole){
                    //基金管理人
                    if(10031==role.getRoleid()){
                        typeStr="1";
                        break;
                    }
                    //出资人代表
                    if(10032==role.getRoleid()){
                        typeStr="2";
                        break;
                    }
                }

            }else if(appUser!=null&&1==appUser.getUsertype()) {
                typeStr="3";

            }
            searchCondition.addParam("typeStr",typeStr);
            searchCondition.addParam("loginName",appUser.getLoginname());
         /*   if (StringUtils.isNotEmpty(oldSubPlatProp)) {
                List<String> oldSubPlatProps = Arrays.asList(oldSubPlatProp.split(","));
                searchCondition.addConditionIn("a.OLD_SUB_PLAT_PROP", oldSubPlatProps);
            }*/

            //判断是基金管理人还是
            if (keyword != null && !"".equals(keyword)) {
                keyword = "'%" + keyword + "%'";
                searchCondition.addParam("keyWord", keyword);
            }
            PageInfo<FundFofInfoModel> rows = fundInfoService.getEnteInveList(searchCondition);
            List<FundFofInfoVO> list = new ArrayList<FundFofInfoVO>();
            for (FundFofInfoModel model : rows.getList()) {
                FundFofInfoVO vo = new FundFofInfoVO();
                BeanUtils.copyProperties(vo, model);
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


    @ApiOperation(value = "内部基金投管", notes = "外部用户新增母基金")
    @ApiImplicitParam(name = "FundInfoProjDTO", value = "SurveyPlanDTO", required = true, dataType = "RequirementsDTO")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/saveNewFundFof", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveNewFundFof(@RequestBody FundInfoProjDTO dto) {
        try {
            FundInfoModel fundInfo = new FundInfoModel();
            BeanUtils.copyProperties(fundInfo, dto);

            EntBaseInfoModel ent = new EntBaseInfoModel();
            BeanUtils.copyProperties(ent, dto);
            ent.setCreditCode(dto.getSocialCode());
            if (StringUtils.isNotEmpty(dto.getTermStartDt())) {
                SimpleDateFormat fom = new SimpleDateFormat();
                Date time = fom.parse(dto.getTermStartDt());
                ent.setTermStart(time);

            }
            FundInveDescModel fundInveDescModel = new FundInveDescModel();
            BeanUtils.copyProperties(fundInveDescModel, dto);
            FundShareInfoModel fundShareInfoModel = new FundShareInfoModel();
            BeanUtils.copyProperties(fundShareInfoModel, dto);
            ProjInfoModel projInfoModel = new ProjInfoModel();
            BeanUtils.copyProperties(projInfoModel, dto);
            ProjAppInfoModel projAppInfoModel = new ProjAppInfoModel();
            BeanUtils.copyProperties(projAppInfoModel, dto);
            //判断基金是否存在
            FundInfoModel fundInfo1 = new FundInfoModel();
            fundInfo1.setFundName(dto.getRegName());
            List<FundInfoModel> listFof = fundInfoService.selectList(fundInfo1);
            fundInfo.setUpdateBy(this.getLoginUserId());
            fundInfo.setUpdateDt(new Date());
           String typeStr = "";
            if (StringUtils.isNotEmpty(dto.getFundId())) {
                //编辑
                FundInfoModel fInfo = fundInfoService.selectById(dto.getFundId());
                if (fInfo != null) {
                    if (!fInfo.getFundName().equals(dto.getFundName())) {
                        if (listFof != null && listFof.size() > 0) {
                            baseResponse.setMsg("基金已存在！");
                            return JSONObject.toJSONString(baseResponse);
                        }
                    }
                    //判断出资主体是否修改

                    //fundInfoService.updateByInfo(fundInfo, projInfoModel, fundInveDescModel, projAppInfoModel, ent, typeStr);
                    fundInfoService.updateByFundOrInfo(fundInfo, projInfoModel, fundInveDescModel, projAppInfoModel, ent, typeStr,dto.getListAdmin());
                }
            } else {
                //新增
              //母基金 和代管基金
                String fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
                if ("1".equals(dto.getOldSubPlatProp()) ||  "7".equals(dto.getOldSubPlatProp())||"3".equals(dto.getOldSubPlatProp())) {
                    if (listFof != null && listFof.size() > 0) {
                        baseResponse.setMsg("基金已存在！");
                        return JSONObject.toJSONString(baseResponse);
                    }
                    FundInfoModel fundInfo2 = new FundInfoModel();
                    fundInfo2.setOldSubPlatProp(dto.getOldSubPlatProp());
                    List<FundInfoModel> list = fundInfoService.selectByList(fundInfo2);
                    //出资主体和其他基金
                    if("1".equals(dto.getOldSubPlatProp())||"7".equals(dto.getOldSubPlatProp())){
                        if(list.size()<10){
                            String fundCode = "0" + String.valueOf(list.size() + 1);
                            fundInfo.setFundCode(fundCode);

                        } else {
                            String fundCode =  String.valueOf(list.size() + 1);
                            fundInfo.setFundCode(fundCode);
                        }
                    }else if("3".equals(dto.getOldSubPlatProp())){
                        //代管基金
                        if (list != null && list.size() > 0) {
                            String code1 = list.get(0).getFundCode().substring(2);
                            if (Integer.parseInt(code1) <= 8) {
                                String fundCode = "DG0" + String.valueOf(Integer.parseInt(code1) + 1);
                                fundInfo.setFundCode(fundCode);
                            } else {
                                String fundCode = "DG" + String.valueOf(Integer.parseInt(code1) + 1);
                                fundInfo.setFundCode(fundCode);
                            }
                        } else {
                            String fundCode = "DG01";
                            fundInfo.setFundCode(fundCode);
                        }
                    }

                }else if("2".equals(dto.getOldSubPlatProp())||"4".equals(dto.getOldSubPlatProp())||"5".equals(dto.getOldSubPlatProp())||(1==dto.getGroupId()&&"0".equals(dto.getInveId()))){
                    FundInfoModel inveFund= fundInfoService.selectById(dto.getInveId());
                    if(inveFund==null){
                        baseResponse.setMsg("出资主体不存在！");
                        return JSONObject.toJSONString(baseResponse);
                    }
                    //生成项目编号
                    String projNo=getFundOrProjCode(inveFund.getFundCode(),dto.getInveId(),"");
                    projInfoModel.setProjNo(projNo);

                    if (listFof != null && listFof.size() > 0) {
                        //表示是1的时候，基金存在只新增项目
                        typeStr = "1";
                        fundId = listFof.get(0).getFundId();
                        //是省政府基金

                    }else{
                        //二级母基金
                        //判断该出资主体投了多少基金
                        if("0".equals(dto.getInveId())){
                            if(StringUtils.isNotEmpty(dto.getPlatProp())){
                               String codeN= getCodeName(dto.getPlatProp());
                                fundInfo.setFundCode(codeN);
                            }
                        }else{
                            String code=getFundOrProjCode(inveFund.getFundCode(),dto.getInveId(),"");
                            fundInfo.setFundCode(code);
                        }


                    }
                }

                fundInfo.setFundId(fundId);
                fundInfo.setCreateBy(this.getLoginUserId());
                fundInfo.setCreateDt(new Date());
                fundInfo.setUpdateDt(new Date());
                fundInfo.setUpdateBy(this.getLoginUserId());
                //查询企业在标红是否存在
                EnteInfoModel modelS=new EnteInfoModel();
                modelS.setChName(dto.getFundName());
                List<EnteInfoModel> listE=  enteInfoService.selectList(modelS);
                String enterpriseId="";
                String typeUpdate="";
                if(listE!=null&&!listE.isEmpty()){
                    enterpriseId=listE.get(0).getEnterpriseId();
                    projInfoModel.setOldEnterpriseId(enterpriseId);
                    typeUpdate="update";
                }else{
                    enterpriseId=serialnoService.getSequence("EI.BD_T_ENTE_INFO");
                    projInfoModel.setOldEnterpriseId(enterpriseId);
                    typeUpdate="add";
                }
                //查询母基金是否存在
               // fundInfoService.insertByInfo(fundInfo, projInfoModel, fundInveDescModel, projAppInfoModel, ent, typeStr);
                fundInfoService.insertByFundOrInfo(fundInfo, projInfoModel, fundInveDescModel, projAppInfoModel, ent, typeStr,dto.getListAdmin(),typeUpdate);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse);

    }

    public String getCodeName(String platProp){
        String fundCode = "";
        if (StringUtils.isNotEmpty(platProp)) {
            if ("1".equals(platProp)) {
                //查询数据库的序列排到什么了
                fundCode = "%" + "NQ" + "%";
                List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                if (listFundCode != null && listFundCode.size() > 0) {
                    String sub = listFundCode.get(0).getFundCode();
                    if (StringUtils.isNotEmpty(sub)) {
                        if (Integer.parseInt(sub.substring(2)) <= 8) {
                            fundCode="N0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1);
                        } else {
                            fundCode="NQ" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1);
                        }
                    }

                } else {
                    fundCode="NQ01";
                }
            } else if ("2".equals(platProp)) {
                fundCode = "%" + "NG" + "%";
                List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                if (listFundCode != null && listFundCode.size() > 0) {
                    String sub = listFundCode.get(0).getFundCode();
                    if (StringUtils.isNotEmpty(sub)) {
                        if (Integer.parseInt(sub.substring(2)) <= 8) {
                            fundCode="NG0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1);
                        } else {
                            fundCode="NG" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1);
                        }

                    }
                } else {
                    fundCode="NG01";
                }
            } else if ("3".equals(platProp)) {
                fundCode = "%" + "NC" + "%";
                List<FundInfoModel> listFundCode = fundInfoService.selectListByFundCode(fundCode);
                if (listFundCode != null && listFundCode.size() > 0) {
                    String sub = listFundCode.get(0).getFundCode();
                    if (StringUtils.isNotEmpty(sub)) {
                        if (Integer.parseInt(sub.substring(2)) <= 8) {
                            fundCode="NC0" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1);
                        } else {
                            fundCode="NC" + String.valueOf(Integer.parseInt(sub.substring(2)) + 1);
                        }

                    }

                } else {
                    fundCode="NC01";
                }
            }

        }
        return fundCode;
    }

    public String getFundOrProjCode(String inveCode,String inveId,String projType){
        System.out.print("*******************inveCode*******************************"+inveCode);
        System.out.print("*******************inveId*******************************"+inveId);
        System.out.print("*******************projType*******************************"+projType);
        String fundCode="";
        ProjInfoModel projFundGs=new ProjInfoModel();
        projFundGs.setInveId(inveId);
        projFundGs.setProjType(projType);
        List<ProjInfoModel> listGs=projInfoService.selectList(projFundGs);
        if(listGs!=null&&!listGs.isEmpty()){
            if (listGs.size() < 9) {
                 fundCode = inveCode + "00" + String.valueOf(listGs.size() + 1);

            } else if (listGs.size()  <= 98) {
                 fundCode = inveCode + "0" + String.valueOf(listGs.size() + 1);
            }else{
                 fundCode = inveCode  + String.valueOf(listGs.size() + 1);
            }
        }else{
            fundCode=inveCode+"001";
        }
        System.out.print("*******************fundCode*******************************"+fundCode);
        return fundCode;
    }

}