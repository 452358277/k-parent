package com.ppmg.ei.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.manage.interfaces.IWorkflowManager;
import com.founder.core.manage.local.WorkflowManager;
import com.founder.core.util.UimUtils;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.xdk.AppGroup;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.common.utils.ProcessUserUtils;
import com.ppmg.ei.dto.ProjAppInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.OaConstants;
import com.ppmg.ei.vo.ProjAppInfoMemberVO;
import com.ppmg.ei.vo.ProjAppInfoProjectMemberVO;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("/projAppInfo")
@Api(tags={"进展评论接口,基金立项接口"})
public class ProjAppInfoController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    private ProjInfoService projInfoService;

    @Reference
    private ProjAppInfoService projAppInfoService;

    @Reference
    private ProjMemberService projMemberService;

    @Reference
    private FundInfoService fundInfoService;

    @Reference
    private ProjAppInfoSnapshotService projAppInfoSnapshotService;


    @Reference
    private ProjAppInfoSnapshotDService projAppInfoSnapshotDService;

    @Reference
    private  FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

    @Resource(name="codeUtils")
    private CodeUtils codeUtils;

    @ApiOperation(value = "基金立项分页查询列表（基金立项使用，项目投管不用）", notes = "基金立项分页查询列表（基金立项使用，项目投管不用）",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "fundName", value = "母基金名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectProjAppInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectProjAppInfoController(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage
            ,@RequestParam("status") String status,@RequestParam("projId") String projIds) {
        try {
            // String userId = String.valueOf(this.getLoginUser().getUserId());
            //logger.info("----------------->>>"+userId+"<<----->>");
            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            if (StringUtils.isNotEmpty(status)) {
                searchCondition.addConditionEqualTo("STATUS",status);
            }
            if (StringUtils.isNotEmpty(projIds)) {
                //searchCondition.addConditionEqualTo("PROJ_ID",projIds);
                searchCondition.addParam("PROJ_ID", projIds);
            }
            PageInfo<ProjAppInfoMemberModel> rows = projAppInfoService.selectallListPage(searchCondition);
            List<ProjAppInfoMemberVO> list = new ArrayList<ProjAppInfoMemberVO>();
            for (ProjAppInfoMemberModel model : rows.getList()) {
                ProjAppInfoMemberVO vo = new ProjAppInfoMemberVO();
                String projId = model.getProjId();
                vo.setProjName(model.getProjName());
                String fundId = null;
                //备份表
                ProjAppInfoSnapshotModel rows1 = projAppInfoSnapshotService.selectById(projId);
                if(rows1 != null){
                    //取备份表
                    // 决策事项
                    model.setMajorMatter(rows1.getMajorMatter());
                    //投资理由
                    model.setInveReason(rows1.getInveReason());
                    //其中：本基金认缴规模
                    model.setInveCurrentAmount(rows1.getInveCurrentAmount());
                    //申请母基金额度
                    model.setApplyAmount(rows1.getApplyAmount());
                    //基金认缴总规模（万元）
                    model.setCurrentAmount(rows1.getCurrentAmount());
                    //基金认缴总规模（万元）币种
                    model.setCurrentCurrency(rows1.getCurrentCurrency());
                    model.setProcessInstId(rows1.getProcessInstId());
                }
                ProjInfoModel pf = projInfoService.selectById(projId);
                if(pf != null){
                    //出资主体
                    model.setFundingBody(pf.getInveName());
                    fundId = pf.getProjObject();
                    FundInfoModel fm = fundInfoService.selectById(fundId);
                    if(fm != null){
                        //先取得基金简称，基金简称没有再取全称
                        // 基金简称
                        String shortName = fm.getShortName();
                        if (shortName == null || shortName == "") {
                            //基金名称
                            shortName = fm.getFundName();
                        }
                        model.setFundName(fm.getFundName());
                        //项目名称取基金名称
                        //model.setProjName(shortName);
                        // 基金认缴总规模（万元）幣種
                        model.setCurrentCurrency(fm.getCurrentCurrency());
                        // 基金认缴总规模（万元）
                        model.setCurrentAmount(fm.getCurrentAmount());
                    }
                }

                String status_ = model.getCurrentCurrency();
                //幣種
                if(StringUtils.isNotEmpty(status_)){
                    String name = codeUtils.getCodeNameByValue("103",status_);
                    //536:人民幣，美元，103：人民幣，美元，歐元
                    model.setCurrentCurrencyName(name);
                }
                String status_1 = model.getProjSrc();
                //項目來源 9016//項目來源 1019,9016
                if(StringUtils.isNotEmpty(status_1)){
                    String name = codeUtils.getCodeNameByValue("9016",status_1);
                    model.setProjSrcName(name);
                }
                BeanUtils.copyProperties(vo, model);
                //获取taskId
                if(StringUtils.isNotEmpty(model.getProcessInstId())){
                    List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                    if (listMap != null && listMap.size() > 0) {
                        String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
                        vo.setTaskId(taskId);
                    }else{
                        vo.setTaskId("");
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

    @ApiOperation(value = "基金立项详情（基金立项使不用，项目投管使用） ", notes = "基金立项详情（基金立项使不用，项目投管使用）",httpMethod = "GET")
    @ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/selectAppInfoOneDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAppInfoOneDetailController(@RequestParam(value = "projId") String projId) {
        try {
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            String projType = pf_Serch.getProjType();
            if(projType != null && "2".equals(projType)){

            }else if(projType != null && "1".equals(projType)){
                //1：投项目
                ProjAppInfoMemberModel model = new ProjAppInfoMemberModel();
                ProjAppInfoSnapshotModel rows1 = projAppInfoSnapshotService.selectById(projId);
                if(rows1 == null){
                    model = projAppInfoService.getOneProjectDetails(projId);
                }else{
                    BeanUtils.copyProperties(model, rows1);
                }
                ProjAppInfoProjectMemberVO vo = new ProjAppInfoProjectMemberVO();
                String fundId = null;
                ProjInfoModel pf = projInfoService.selectById(projId);
                if(pf != null){
                    //项目名称
                    model.setProjName(pf.getProjName());
                    //出资主体
                    model.setFundingBody(pf.getInveName());
                    //出资主体id
                    model.setInveId(pf.getInveId());
                    //流程狀態
                    model.setStatus(pf.getStatus());
                }
                BeanUtils.copyProperties(vo, model);
                //获取taskId
                if(StringUtils.isNotEmpty(model.getProcessInstId())){
                    List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                    if (listMap != null && listMap.size() > 0) {
                        String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
                        vo.setTaskId(taskId);
                    }else{
                        vo.setTaskId("");
                    }
                }
                //被投企业融资轮次
                if(model.getFinaRounds() != null){
                    String name0 = codeUtils.getCodeNameByValue("213",model.getFinaRounds());
                    vo.setFinaRoundsName(name0);
                }
                if(model.getFinaCurrency() != null){
                    //被投企业融资金额（万元）币种
                    String name1 = codeUtils.getCodeNameByValue("103",model.getFinaCurrency());
                    vo.setFinaCurrencyName(name1);
                }
                if(model.getPreCurrency() != null){
                    //投前估值（万元）币种
                    String name2 = codeUtils.getCodeNameByValue("103",model.getPreCurrency());
                    vo.setPreCurrencyName(name2);
                }
                if(model.getPostCurrency() != null){
                    //投后估值（万元）币种
                    String name3 = codeUtils.getCodeNameByValue("103",model.getPostCurrency());
                    vo.setPostCurrencyName(name3);
                }
                if(model.getIntendedCurrency() != null){
                    //拟投资金额（万元）币种
                    String name4 = codeUtils.getCodeNameByValue("103",model.getIntendedCurrency());
                    vo.setIntendedCurrencyName(name4);
                }
                if(model.getInveType() != null){
                    //投资类型
                    String name5 = codeUtils.getCodeNameByValue("1026",model.getInveType());
                    vo.setInveTypeName(name5);
                }
                if(model.getInveRole() != null){
                    //投资角色215
                    String name6 = codeUtils.getCodeNameByValue("215",model.getInveRole());
                    vo.setInveRoleName(name6);
                }
                if(model.getStatus() != null){
                    //流程狀態
                    vo.setStatusName(codeUtils.getCodeNameByValue("599", model.getStatus()));
                    dataResponse.setData(vo);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "基金立项更新", notes = "基金立项更新", httpMethod = "POST")
    @ApiImplicitParam(name = "projAppInfoMemberModel", value = "实体类", required = true, dataType = "ProjAppInfoMemberModel")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateProjAppInfoMemberModel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateProjAppInfoMemberModel(@RequestBody ProjAppInfoMemberModel projAppInfoMemberModel){
        try {
            if(projAppInfoMemberModel != null){
                String projId_Serch = projAppInfoMemberModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId_Serch);
                //基金fundId
                String fundId = pf_Serch.getProjObject();
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                logger.info("##------->更新基金立项1---------userId"+userId);
                projAppInfoMemberModel.setUserId(userId);
                String projId = projAppInfoMemberModel.getProjId();
                //关联基金表ProjObject
                projAppInfoMemberModel.setProjObject(pf_Serch.getProjObject());
                //獲取部門
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                logger.info("##------->更新基金立项2---------deptId"+deptId);
                String tag = projAppInfoMemberModel.getTag();
                //流程
                IWorkflowManager wm = new WorkflowManager();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                //当前申请人
                String authid = userId;
                // 主键id
                String businessKey = projId;
                if(projType != null && "2".equals(projType)) {
                    //基金项目立项
                    logger.info("##------->更新基金立项3---------projType"+projType);
                    String projIds = projAppInfoMemberModel.getProjId();
                    ProjAppInfoSnapshotModel snap = projAppInfoSnapshotService.selectById(projIds);
                    ProjAppInfoSnapshotModel m = new ProjAppInfoSnapshotModel();
                    BeanUtils.copyProperties(m, projAppInfoMemberModel);
                    m.setCreateDt(new Date());
                    m.setUpdateDt(new Date());
                    m.setCreateBy(userId);
                    m.setUpdateBy(userId);
                    if (tag != null && "0".equals(tag)) {
                        //提交流程并且保存数据
                        logger.info("##------->更新基金立项4---------tag"+tag);
                        //流程编号
                        String processID = "ei_condition_info";
                        //流程主题
                        //String subject = "投管基金-基金立项" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        String subject = "立项申请" + "：" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                        //其他參數
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("deptId", deptId);
                        formMap.put("fundId", fundId);
                         FundInfoModel fund=  fundInfoService.selectById(fundId);
                          if(fund!=null&&fund.getGroupId()!=null){
                              formMap.put("groupId", String.valueOf(fund.getGroupId()));
                          }else{
                              formMap.put("groupId", "");
                          }
                        //启动流程
                        logger.info("##------->更新基金立项5---------start");
                        ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                        //"提交成功！"
                        if (inst != null) {
                            logger.info("##------->流程启动成功<--------##");
                            //保存数据
                            projAppInfoMemberModel.setStatus("12");
                            //流程实例
                            projAppInfoMemberModel.setProcessInstId(inst.getId());
                            projAppInfoService.updateAll(projAppInfoMemberModel);
                            //保存快照
                            if(snap == null){
                                m.setProcessInstId(inst.getId());
                                projAppInfoSnapshotService.insert(m);
                            }else{
                                m.setProcessInstId(inst.getId());
                                projAppInfoSnapshotService.update(m);
                            }
                        } else {
                            logger.error("##------->流程启动失败<--------##");
                            baseResponse.error("流程启动失败");
                        }
                    }else if(tag != null && "1".equals(tag)){
                        //保存数据
                        //projAppInfoMemberModel.setStatus("0");
                        projAppInfoService.updateAll(projAppInfoMemberModel);
                        //保存快照
                        if(snap == null){
                            projAppInfoSnapshotService.insert(m);
                        }else{
                            projAppInfoSnapshotService.update(m);
                        }
                    }else if(tag != null && "3".equals(tag)){
                        //保存数据，不改变任何状态
                        //保存数据
                        projAppInfoService.updateAll(projAppInfoMemberModel);
                        //保存快照
                        if(snap == null){
                            projAppInfoSnapshotService.insert(m);
                        }else{
                            projAppInfoSnapshotService.update(m);
                        }
                    }
                }else if(projType != null && "1".equals(projType)){
                    //项目投管
                    String projIds = projAppInfoMemberModel.getProjId();
                    String beforInveId = pf_Serch.getInveId();
                    String afterInveId = projAppInfoMemberModel.getInveId();
                    String no = "";//出资主体改变了才去修改项目编码
                    if(beforInveId.equals(afterInveId)){
                        no = pf_Serch.getProjNo();
                    }else {
                        no = this.getProjCodes(afterInveId);
                    }
                    logger.info("出资主体项目编号生成--------------->>>" + no);
                    ProjAppInfoSnapshotModel snap = projAppInfoSnapshotService.selectById(projIds);
                    ProjAppInfoSnapshotModel m = new ProjAppInfoSnapshotModel();
                    BeanUtils.copyProperties(m, projAppInfoMemberModel);
                    m.setCreateDt(new Date());
                    m.setUpdateDt(new Date());
                    m.setCreateBy(userId);
                    m.setUpdateBy(userId);
                    if (tag != null && "0".equals(tag)) {
                        //提交流程并且保存数据
                        //10011	(112)朱鑫文、(113)钱子亮
                        //流程编号
                        String processID = "ei_condition_info_project";
                        //流程主题
                        //String subject = "项目投管-基金立项" + "-" + date;
                        //流程名称：项目或基金名称（股权相关的功能）
                        String subject = "立项申请" + "：" + pf_Serch.getProjName();
                        logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                        //其他參數
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("deptId", deptId);
                        formMap.put("fundId", fundId);
                        //启动流程
                        ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                        //"提交成功！"
                        if (inst != null) {
                            logger.info("##------->流程启动成功<--------##");
                            //保存数据
                            projAppInfoMemberModel.setStatus("12");
                            //项目编号：项目编号==出资主体编号+四位年+四位顺序*/
                            projAppInfoMemberModel.setProjNo(no);
                            projAppInfoMemberModel.setProcessInstId(inst.getId());
                            projAppInfoService.updateProjectAll(projAppInfoMemberModel);
                            //保存快照
                            //报错流程id
                            m.setProcessInstId(inst.getId());
                            if(snap == null){
                                projAppInfoSnapshotService.insert(m);
                            }else{
                                projAppInfoSnapshotService.update(m);
                            }
                        } else {
                            logger.error("##------->流程启动失败<--------##");
                            baseResponse.error("流程启动失败");
                        }
                    }else if(tag != null && "1".equals(tag)){
                        //保存数据
                        //projAppInfoMemberModel.setStatus("0");
                        // 项目编号：项目编号==出资主体编号+四位年+四位顺序*/
                        projAppInfoMemberModel.setProjNo(no);
                        projAppInfoService.updateProjectAll(projAppInfoMemberModel);
                        //保存快照
                        if(snap == null){
                            projAppInfoSnapshotService.insert(m);
                        }else{
                            projAppInfoSnapshotService.update(m);
                        }
                    }else if(tag != null && "3".equals(tag)){
                        //保存数据，不改变任何状态
                        //保存数据
                        projAppInfoService.updateProjectAll(projAppInfoMemberModel);
                        //保存快照
                        if(snap == null){
                            projAppInfoSnapshotService.insert(m);
                        }else{
                            projAppInfoSnapshotService.update(m);
                        }
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }

    @ApiOperation(value = "基金立项删除", notes = "基金立项删除", httpMethod = "POST")
    @ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/deleteProjAppInfoMemberModel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteProjAppInfoMemberModelController(@RequestParam("projId") String projId) {
        try {
            String[] arr = null;
            if (projId != "" && projId != null) {
                //主表
                ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                if(projType != null && "2".equals(projType)) {
                    //基金项目立项
                    arr = projId.split(",");
                    //删除
                    projAppInfoService.deleteAll(arr);
                }else if(projType != null && "1".equals(projType)){
                    //项目投管
                    arr = projId.split(",");
                    //删除
                    projAppInfoService.deleteProjectAll(arr);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "查询项目名称跟项目主体", notes = "查询项目名称跟项目主体", httpMethod = "GET")
    @ApiImplicitParam(name = "projId", value = "projId", required = true, dataType = "String")
    @GetMapping(value = "/selectAppInfoOneDetailTwo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAppInfoOneDetailTwoController(@RequestParam(value = "projId") String projId) {
        try {
            ProjInfoModel pf_Serch = projInfoService.selectById(projId);//主表
            //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
            String projType = pf_Serch.getProjType();
            if(projType != null && "1".equals(projType)){//1：直投企业，立项
                ProjAppInfoMemberModel model = projAppInfoService.getOneProjectDetails(projId);
                ProjAppInfoProjectMemberVO vo = new ProjAppInfoProjectMemberVO();
                String fundId = null;
                ProjInfoModel pf = projInfoService.selectById(projId);//主表
                if(pf != null){
                    model.setProjName(pf.getProjName());//项目名称
                    model.setFundingBody(pf.getInveName());//出资主体
                    model.setStatus(pf.getStatus());//流程狀態
                }
                BeanUtils.copyProperties(vo, model);
                dataResponse.setData(vo);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.error();
        }
        return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "增加基金立项", notes = "增加基金立项", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conditionModel", value = "实体类", required = true, dataType = "ConditionModel"),
            @ApiImplicitParam(name = "tag", value = "0:提交并且启动流程，1：提交数据", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/addProjAppInfoMemberModel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addProjAppInfoMemberModelController(@RequestBody ProjAppInfoMemberModel projAppInfoMemberModel) {
        try {
            if (projAppInfoMemberModel != null) {
                String projId_Serch = projAppInfoMemberModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId_Serch);//主表
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                logger.info("##------->增加基金立项---------userId"+userId);
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();//獲取部門
                logger.info("##------->增加基金立项---------deptId"+deptId);
                String projId = projAppInfoMemberModel.getProjId();
                projAppInfoMemberModel.setUserId(userId);//当前登陆人
                String tag = projAppInfoMemberModel.getTag();
                IWorkflowManager wm = new WorkflowManager();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                String authid = userId;
                //主键id
                String businessKey = projId;
                if(projType != null && "2".equals(projType)){//基金项目立项
                    if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                        //流程编号
                        String processID = "ei_condition_info";
                        //流程主题
                        String subject = "投管基金-基金立项" + "-" + date;
                        //其他參數
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("deptId", deptId);
                        //启动流程
                        ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                        //"提交成功！"
                        if (inst != null) {
                            logger.info("##------->流程启动成功<--------##");
                            //保存数据
                            projAppInfoMemberModel.setStatus("12");//立項中
                            projAppInfoService.insertAll(projAppInfoMemberModel);
                        } else {
                            logger.error("##------->流程启动失败<--------##");
                            baseResponse.error("流程启动失败");
                        }
                    }else if(tag != null && "1".equals(tag)){
                        //保存数据
                        projAppInfoMemberModel.setStatus("0");
                        projAppInfoService.insertAll(projAppInfoMemberModel);
                    }
                }else if(projType != null && "1".equals(projType)){//项目投管
                    if (tag != null && "0".equals(tag)) {//提交流程并且保存数据
                        //流程编号
                        String processID = "ei_condition_info_project";
                        //流程主题
                        String subject = "项目投管-基金立项" + "-" + date;
                        //其他參數
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put("taskTitle", subject);
                        formMap.put("businessKey", businessKey);
                        formMap.put("deptId", deptId);
                        //启动流程
                        ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                        //"提交成功！"
                        if (inst != null) {
                            logger.info("##------->流程启动成功<--------##");
                            //保存数据
                            projAppInfoMemberModel.setStatus("1");
                            projAppInfoService.insertProjectAll(projAppInfoMemberModel);
                            ProjAppInfoSnapshotModel m = new ProjAppInfoSnapshotModel();
                            //保存快照
                            BeanUtils.copyProperties(m, projAppInfoMemberModel);
                            m.setCreateDt(new Date());
                            m.setUpdateDt(new Date());
                            m.setCreateBy(userId);
                            m.setUpdateBy(userId);
                            projAppInfoSnapshotService.insert(m);
                        } else {
                            logger.error("##------->流程启动失败<--------##");
                            baseResponse.error("流程启动失败");
                        }
                    }else if(tag != null && "1".equals(tag)){
                        //保存数据
                        projAppInfoMemberModel.setStatus("0");
                        // projAppInfoService.insertProjectAll(projAppInfoMemberModel);
                        //保存快照
                        ProjAppInfoSnapshotModel m = new ProjAppInfoSnapshotModel();
                        BeanUtils.copyProperties(m, projAppInfoMemberModel);
                        m.setCreateDt(new Date());
                        m.setUpdateDt(new Date());
                        m.setCreateBy(userId);
                        m.setUpdateBy(userId);
                        projAppInfoSnapshotService.insert(m);
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }


    /**
     ********↓↓↓↓↓↓↓↓↓↓*********平台名称：金财合盈*******↓↓↓↓↓↓*******管理公司退出决策*******↓↓↓↓↓↓↓↓*********
     */


    @ApiOperation(value = "基金立项更新(管理公司退出决策)", notes = "基金立项更新(管理公司退出决策)", httpMethod = "POST")
    @ApiImplicitParam(name = "projAppInfoMemberModel", value = "实体类", required = true, dataType = "ProjAppInfoMemberModel")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/updateProjAppInfoMemberModelMC", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateProjAppInfoMemberModelMC(@RequestBody ProjAppInfoMemberModel projAppInfoMemberModel){
        try {
            if(projAppInfoMemberModel != null){
                String projId_Serch = projAppInfoMemberModel.getProjId();
                ProjInfoModel pf_Serch = projInfoService.selectById(projId_Serch);
                //基金fundId
                String fundId = pf_Serch.getProjObject();
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                String projType = pf_Serch.getProjType();
                String userId = String.valueOf(this.getLoginUser().getUserId());
                //String userId = UimUtilsUserId.getUserId(projType);
                logger.info("##------->更新基金立项1---------userId"+userId);
                projAppInfoMemberModel.setUserId(userId);
                String projId = projAppInfoMemberModel.getProjId();
                //关联基金表ProjObject
                projAppInfoMemberModel.setProjObject(pf_Serch.getProjObject());
                //獲取部門
                Long deptId = com.founder.core.util.UimUtils.getDept(java.lang.Long.parseLong(userId)).getId();
                logger.info("##------->更新基金立项2---------deptId"+deptId);
                String tag = projAppInfoMemberModel.getTag();
                //流程
                IWorkflowManager wm = new WorkflowManager();
                //当前申请人
                String authid = userId;
                // 主键id
                String businessKey = projId;
                //项目投管
                String beforInveId = pf_Serch.getInveId();
                String afterInveId = projAppInfoMemberModel.getInveId();
                //出资主体改变了才去修改项目编码
                String no = "";
                if(beforInveId.equals(afterInveId)){
                    no = pf_Serch.getProjNo();
                }else {
                    no = this.getProjCodes(afterInveId);
                }
                logger.info("出资主体项目编号生成--------------->>>" + no);
                ProjAppInfoSnapshotModel snap = projAppInfoSnapshotService.selectById(projId_Serch);
                ProjAppInfoSnapshotModel m = new ProjAppInfoSnapshotModel();
                BeanUtils.copyProperties(m, projAppInfoMemberModel);
                m.setCreateDt(new Date());
                m.setUpdateDt(new Date());
                m.setCreateBy(userId);
                m.setUpdateBy(userId);
                if (tag != null && "0".equals(tag)) {
                    //提交流程并且保存数据
                    //流程编号
                    String processID = "MC_WithdrawalDecision";
                    //流程主题
                    String subject = "退出决策" + ":" + pf_Serch.getProjName();
                    logger.info("##--------------ProjName--->>"+pf_Serch.getProjName()+"<--subject--->"+subject);
                    //其他參數
                    Map<String, Object> formMap = new HashMap<String, Object>();
                    formMap.put("taskTitle", subject);
                    formMap.put("businessKey", businessKey);
                    formMap.put("deptId", deptId);
                    formMap.put("fundId", fundId);
                    //启动流程
                    ProcessInstance inst = wm.startAndSubmit(formMap,"HandleCommand_StartAndSubmit",processID,businessKey,authid);
                    //"提交成功！"
                    if (inst != null) {
                        logger.info("##------->流程启动成功<--------##");
                        //保存数据
                        projAppInfoMemberModel.setStatus("12");
                        //项目编号：项目编号==出资主体编号+四位年+四位顺序*/
                        projAppInfoMemberModel.setProjNo(no);
                        projAppInfoMemberModel.setProcessInstId(inst.getId());
                        projAppInfoService.updateProjectAll(projAppInfoMemberModel);
                        //保存快照
                        //流程id
                        m.setProcessInstId(inst.getId());
                        if(snap == null){
                            projAppInfoSnapshotService.insert(m);
                        }else{
                            projAppInfoSnapshotService.update(m);
                        }
                    } else {
                        logger.error("##------->流程启动失败<--------##");
                        baseResponse.error("流程启动失败");
                    }
                }else if(tag != null && "1".equals(tag)){
                    //保存数据
                    // 项目编号：项目编号==出资主体编号+四位年+四位顺序*/
                    projAppInfoMemberModel.setProjNo(no);
                    projAppInfoService.updateProjectAll(projAppInfoMemberModel);
                    //保存快照
                    if(snap == null){
                        projAppInfoSnapshotService.insert(m);
                    }else{
                        projAppInfoSnapshotService.update(m);
                    }
                }else if(tag != null && "3".equals(tag)){
                    //保存数据，不改变任何状态
                    //保存数据
                    projAppInfoService.updateProjectAll(projAppInfoMemberModel);
                    //保存快照
                    if(snap == null){
                        projAppInfoSnapshotService.insert(m);
                    }else{
                        projAppInfoSnapshotService.update(m);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);
    }
    //**********************************************以上，立项**********************************************************************

    @ApiIgnore
    @ApiOperation(value="投资决策", notes="投资决策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "planId", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "SurveyPlanVO", value = "SurveyPlanVO", required = true, dataType = "SurveyPlanVO")
    })
    @PostMapping(value = "/appInfo/saveAppInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveAppInfo(@RequestBody ProjAppInfoDTO dto){
        try {
            ProjAppInfoModel projAppInfo=new ProjAppInfoModel();
            BeanUtils.copyProperties(projAppInfo, dto);
            projAppInfo.setUpdateDt(new Date());
            projAppInfo.setUpdateBy(this.getLoginUserId());
            FundInfoModel fundInfoModel=new FundInfoModel();
            BeanUtils.copyProperties(fundInfoModel, dto);
            ProjAppInfoSnapshotDModel model = new ProjAppInfoSnapshotDModel();
            BeanUtils.copyProperties(model, dto);
            model.setProjAppStatus(dto.getStatus());
            //判断出资主体是否修改
           ProjInfoModel projIF= projInfoService.selectById(dto.getProjId());
            String projCodeS="";
            String fundG="";
            if(projIF!=null){
              if(!projIF.getInveId().equals(dto.getInveId())){
                  projCodeS=getProjCodes(dto.getInveId());

              }else{
                  projCodeS="false";
              }
              //查询出资主体的基金属性
                if(StringUtils.isNotEmpty(dto.getInveId())){
                 FundInfoModel  fundInfoModel2= fundInfoService.selectById(dto.getInveId());
                 if(fundInfoModel2!=null){
                     fundG=fundInfoModel2.getFundGenre();
                 }
                }

            }
            projAppInfoService.updateByInfo(projAppInfo,fundInfoModel,model,projCodeS);

            if(dto.getIsStartFlow()) {//如果点击是提交则添加流程
                startWorkFlow(projAppInfo,fundInfoModel,model,fundG);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);
    }

    @ApiIgnore
    @ApiOperation(value="投资决策详情", notes="投资决策详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "planId", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "SurveyPlanVO", value = "SurveyPlanVO", required = true, dataType = "SurveyPlanVO")
    })
    @PostMapping(value = "/appInfo/appInfoDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String appInfoDetail(@RequestBody ProjAppInfoDTO dto){
        try {
            if(StringUtils.isNotEmpty(dto.getProjId())){
                ProjAppInfoSnapshotDModel model=projAppInfoSnapshotDService.selectById(dto.getProjId());
                if(model!=null){
                    if(StringUtils.isNotEmpty(model.getProcessInstId())){
                        List<Map<String,Object>> listMap=  fixflowRunTaskinstanceService.getFixFlowByTaskId(model.getProcessInstId());
                        if(listMap!=null&&listMap.size()>0){
                            String taskId=listMap.get(0).get("TASKINSTANCE_ID").toString();
                            mapResponse.put("taskId", taskId);
                        }else{
                            mapResponse.put("taskId", "");
                        }
                    }else{
                        mapResponse.put("taskId", "");
                    }
                    //备份表已存在
                    mapResponse.put("projAppInfo", model);
                }else{
                    ProjAppInfoModel projAppInfo= projAppInfoService.selectById(dto.getProjId());
                    mapResponse.put("projAppInfo", projAppInfo);
                    mapResponse.put("taskId", "");
                }

            }
            if(StringUtils.isNotEmpty(dto.getFundId())){
                FundInfoModel fundInfoModel=fundInfoService.selectById(dto.getFundId());
                mapResponse.put("fundInfoModel", fundInfoModel);
                if(StringUtils.isNotEmpty(fundInfoModel.getCurrentCurrency())){
                    String  currentCurrency=codeUtils.getCodeNameByValue("103",fundInfoModel.getCurrentCurrency());
                    mapResponse.put("currentCurrencyName", currentCurrency);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }



    /**获取项目编号 **/
    public String getProjCodes(String inveId) {
        try {
            FundInfoModel fund= fundInfoService.selectById(inveId);
            String code="";
            if(fund!=null){
                String fundCode=fund.getFundCode();
                SimpleDateFormat sim = new SimpleDateFormat("yyyy");
                String time = sim.format(new Date());
                String proCode=fundCode+time;
                List<ProjInfoModel> projInfoList= projInfoService.getNewProCode("%"+proCode+"%");
                if(projInfoList!=null&&projInfoList.size()>0){
                    String proj=projInfoList.get(0).getProjNo();
                    Integer len = Integer.parseInt(proj.replaceFirst(proCode,""));
                    if (len <= 8) {
                        code=proCode + "000" + String.valueOf((len + 1));
                    } else if(len <= 98) {
                        code=proCode + "00" +  String.valueOf((len + 1));
                    }else if(len <= 998) {
                        code=proCode + "0" +  String.valueOf((len + 1));
                    }else{
                        code=proCode+String.valueOf((len + 1));
                    }
                }else{
                    code=proCode+"0001";
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

    private void startWorkFlow( ProjAppInfoModel projAppInfo,FundInfoModel fundInfoModel,ProjAppInfoSnapshotDModel model,String fundGenre) {
        FundInfoModel fundInfo=fundInfoService.selectById(fundInfoModel.getFundId());
        IWorkflowManager wm = new WorkflowManager();
        String fileName = "ei_decision";
        String currUserId = this.getLoginUserId();
        String id = projAppInfo.getProjId();
        String taskTitle =  "投资决策："+fundInfo.getFundName();
        if (wm.isEnd(id, fileName, currUserId)) {
            Map<String, Object> formMap = new HashMap<>();
            formMap.put("taskTitle", taskTitle);
            formMap.put("businessKey", id);
            formMap.put("fundId", fundInfo.getFundId());
            formMap.put("fundGenre", fundGenre);
            if(fundInfo!=null&&fundInfo.getGroupId()!=null){
                formMap.put("groupId", String.valueOf(fundInfo.getGroupId()));
            }else{
                formMap.put("groupId", "");
            }
            AppGroup dept = UimUtils.getDept(Long.parseLong(currUserId));
            //部门负责人
            String departmentManager = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DEPARTMENT_MANAGER_POST_ID);
            formMap.put("departmentManager", departmentManager);
            //分管副总
            String divisionVicePresident = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), OaConstants.DIVISION_VICE_PRESIDENT_POST_ID);
            formMap.put("divisionVicePresident", divisionVicePresident);
            //发起人
            formMap.put("startUser", currUserId);
            //总经理办公会
            List<String> list = UimUtils.getUserIdByRoleId(10006L);
            String users = "";
            for (String s : list) {
                users += s + ",";
            }
            if (users.length() > 0) {
                users = users.substring(0, users.length() - 1);
            }
            formMap.put("managerUsers", users);
            List<String> listOfficeUser = UimUtils.getUserIdByRoleId(10020L);
            String officeUser = "";
            for (String s : listOfficeUser) {
                officeUser += s + ",";
            }
            if (officeUser.length() > 0) {
                officeUser = officeUser.substring(0, officeUser.length() - 1);
            }
            //String officeUser = ProcessUserUtils.getAppUserIdToStringByGroupAndPost(dept.getId(), 110);
            formMap.put("officeUser", officeUser);

            formMap.put("groupId", String.valueOf(fundInfo.getGroupId()));
            formMap.put("fundId", fundInfo.getFundId());
            ProcessInstance inst = wm.startAndSubmit(formMap, "HandleCommand_StartAndSubmit", fileName, id, currUserId);
            if (inst != null) {
                //"提交成功！"
                model.setProcessInstId(inst.getId());
                model.setProjAppStatus("1");
                projAppInfoSnapshotDService.update(model);
            } else {
                throw new SystemException("流程启动失败，请确认下一节点审批人是否存在");
            }
        }
    }


}
