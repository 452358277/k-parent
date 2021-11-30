package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.EiTAttachmentVO;
import com.ppmg.ei.vo.FounderOaApplyContractListByFundVO;
import com.ppmg.ei.vo.FounderOaApplyContractListVO;
import com.ppmg.ei.vo.OtherThreeMeetingListVO;
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

@Controller
@Scope("prototype")
@Api(tags={"平台管理OA合同接口"})
public class FounderOaApplyContractController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FounderOaApplyContractService founderOaApplyContractService;

	@Reference
	private FounderOaApplyInfoService founderOaApplyInfoService;

	@Reference
	private CommTCodeService commTCodeService;

	@Reference
	private FixflowRunTaskinstanceService fixflowRunTaskinstanceService;

	@Reference
	private FounderFileInfoService founderFileInfoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="平台管理OA合同列表", notes="平台管理OA合同列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/founderOaApplyInfoList", produces = "application/json;charset=UTF-8")
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
			searchCondition.addConditionEqualTo("T.REL_PLAT_ID",groupId);
			PageInfo<FounderOaApplyContractModel> rows = founderOaApplyContractService.selectListPage(searchCondition);
			List<FounderOaApplyContractListVO> list = new ArrayList<FounderOaApplyContractListVO>();
			for(FounderOaApplyContractModel model : rows.getList()){

				String APPLY_NO = model.getApplyNo();
				FounderOaApplyInfoModel founderOaApplyInfoModel = founderOaApplyInfoService.selectById(APPLY_NO);

				String processStatus = founderOaApplyInfoModel.getProcessStatus();
				Date applyTime = founderOaApplyInfoModel.getApplyTime();
				String processInstId = founderOaApplyInfoModel.getProcessInstId();
				Long applicantId = founderOaApplyInfoModel.getApplicantId();
				String applicantName = founderOaApplyInfoModel.getApplicantName();
				String applyStatus = founderOaApplyInfoModel.getApplyStatus();
				String applicantDepartment = founderOaApplyInfoModel.getApplicantDepartment();

				FounderOaApplyContractListVO vo = new FounderOaApplyContractListVO();
				BeanUtils.copyProperties(vo, model);

				vo.setProcessStatus(processStatus);
				vo.setApplyTime(applyTime);
				vo.setProcessInstId(processInstId);
				vo.setApplicantId(applicantId);
				vo.setApplicantName(applicantName);
				vo.setApplyStatus(applyStatus);
				vo.setApplicantDepartment(applicantDepartment);

				String contractType = model.getContractType();
				String contractTypeDesc = "" ;
				if(contractType!=""&&contractType!=null){
					contractTypeDesc = codeUtils.getCodeNameByValue("310", contractType);//合同性质
				}

				vo.setContractTypeDesc(contractTypeDesc);

				String effecStatus = model.getEffecStatus();
				String effecStatusName = "";
				if(!(effecStatus==""||effecStatus==null)){
					effecStatusName = codeUtils.getCodeNameByValue("259", effecStatus);//合同状态
				}

				vo.setEffecStatus(effecStatusName);

				String startTime_endTime="";
				if(model.getStartTime()!=null&&model.getEndTime()!=null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String startTime = sdf.format(model.getStartTime());
					String endTime = sdf.format(model.getEndTime());
					startTime_endTime = startTime+"到"+endTime;
				}
				vo.setStartTime_endTime(startTime_endTime);

				if(founderOaApplyInfoModel.getProcessInstId() != null){
					List<FixflowRunTaskinstanceModel> flows = fixflowRunTaskinstanceService.getListByProcessinstanceId(founderOaApplyInfoModel.getProcessInstId());
					if(flows.size()>0){
						if(flows.get(0).getFormuriview() == null || flows.get(0).getFormuriview() == ""){
							vo.setInstanceModel(flows.get(1));
						}else{
							vo.setInstanceModel(flows.get(0));
						}
					}

				}


				if(vo.getAttachment()!= null && vo.getAttachment()!=  ""){
					FounderFileInfoModel founderFileInfoModel = new FounderFileInfoModel();
					founderFileInfoModel.setFieldToken(vo.getAttachment());
					List<FounderFileInfoModel> listEiAtt = founderFileInfoService.selectList(founderFileInfoModel);
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
		} catch (Exception e) {
			dataTablesResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="删除OA合同表数据-物理删除", notes="根据url的主键来指定删除表数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "applyNos", value = "主键名", required = true, dataType = "String")
})
	@GetMapping(value = "/deleteTableDataByIdWL", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteTableDataByIdWL(@RequestParam("applyNos") String applyNos){
		//founderOaApplyInfoService;founderOaApplyContractService
		try {
			String[] idsArray = applyNos.split(",");
			founderOaApplyInfoService.deleteByIds(Arrays.asList(idsArray));
			founderOaApplyContractService.deleteByIds(Arrays.asList(idsArray));
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiOperation(value="母基金合同列表", notes="母基金合同列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/applyContractListByFundId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String applyContractListByFundId(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			String userId = this.getLoginUserId();
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("REL_FUND_ID", fundId);
			searchCondition.addConditionEqualTo("APPLICANT_ID", userId);

			//TODO 母基金合同取值
//			PageInfo<FounderOaApplyContractModel> rows = new PageInfo<FounderOaApplyContractModel>();
//			FounderOaApplyContractModel model = new FounderOaApplyContractModel();
			/*model.setContractName("小米第一次投资协议");
			rows.setPageSize(10);
			rows.setTotal(1);
			rows.setSize(1);*/
			PageInfo<FounderOaApplyContractModel> rows = founderOaApplyContractService.selectApplyContractListByFundId(searchCondition);
			List<FounderOaApplyContractListByFundVO> list = new ArrayList<FounderOaApplyContractListByFundVO>();
			for(FounderOaApplyContractModel model : rows.getList()){

				FounderOaApplyContractListByFundVO vo = new FounderOaApplyContractListByFundVO();
				BeanUtils.copyProperties(vo, model);
				vo.setContractTypeDesc(codeUtils.getCodeNameByValue("224", model.getContractType()));
				vo.setApplicantName(model.getApplyInfoModel().getApplicantName());

				/*vo.setContractName("小米第一次投资协议");
				vo.setAttachmentList(new ArrayList<EiTAttachmentVO>());
				vo.setSignDate(new Date());
				vo.setApplicantName("季锦梅");
				vo.setContractTypeDesc("投资协议");*/

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

