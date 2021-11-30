package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.core.vo.BaseResponse;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.model.BaseInfoExtendModel;
import com.ppmg.common.service.BaseInfoExtendService;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.easyexcel.*;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.util.otherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component("projInfoService")
@Service(interfaceClass = ProjInfoService.class,timeout = 500000,retries = 0)
public class ProjInfoServiceImpl extends BaseServiceImpl<ProjInfoModel> implements ProjInfoService {

	@Resource(name="projInfoService")
	private ProjInfoService projInfoService;

	@Resource(name="projMemberService")
	private ProjMemberService projMemberService;

	@Resource(name="entBaseInfoService")
	private EntBaseInfoService entBaseInfoService;

	@Resource(name="projectProgressService")
	private ProjectProgressService projectProgressService;

	@Resource(name="fundInfoService")
	private FundInfoService fundInfoService;

	@Resource(name="projAppInfoService")
	private ProjAppInfoService projAppInfoService;

	@Resource(name="bdTFitRegulationSelfService")
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@Resource
	private XjlTPaymentRequestService xjlTPaymentRequestService;

	@Reference(check = false)
	private SerialnoService serialnoService;
	@Resource
	private EntLabelService entLabelService;

	@Resource
	private ProjQuitApplService projQuitApplService;

	@Resource
	private CommTGicsService commTGicsService;

	@Resource(name="commTNewService")
	private CommTNewService commTNewService;

	@Resource(name="commTCodeService")
	private CommTCodeService commTCodeService ;

	@Resource
	private BdTAttaItemService bdTAttaItemService;

	@Resource
	private EnteInfoService enteInfoService;


	@Resource
	private LedgerMagService  ledgerMagService;

	@Resource
	private FundEnteService  fundEnteService;

	@Resource
	private AdminService adminService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private ProjValuationService projValuationService;

	@Reference(check = false)
	private BaseInfoExtendService baseInfoExtendService;


	public ProjInfoServiceImpl(){
		this.setNamespace("ProjInfo");
	}

	@Override
	public ProjInfoModel getProjBaseInfoById(String projId) {
		return (ProjInfoModel)this.selectOne("getProjBaseInfoById", projId);
	}

	@Override
	public ProjInfoModel getProjInfoById(String projId) {
		return (ProjInfoModel)this.selectOne("getProjInfoById", projId);
	}

	@Override
	public List<ProjInfoModel> getProjListByFundId(String fundId) {
		return this.selectList("getProjListByFundId",fundId);
	}

	@Override
	public <ProjInfoModel> PageInfo<ProjInfoModel> selectListPageByFundId(SearchCondition var1) {
		return (PageInfo<ProjInfoModel>) this.selectListPage("selectProjByFundIdListPage",var1);
	}

	@Override
	public <ProjInfoModel> PageInfo<ProjInfoModel> selectSubFundListPage(SearchCondition searchCondition) {
		return (PageInfo<ProjInfoModel>) this.selectListPage("selectSubFundListPage",searchCondition);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertProjInfoAndMember(ProjInfoModel model,String memberId,String memberName,String loginUserId,String orgId) throws Exception {


		if(model.getProjObject()==null||model.getProjObject()==""||"".equals(model.getProjObject())){
			String enterpriseId = otherUtil.getSeqNextValString("EI.SEQ_ENTERPRISE_ID");
			model.setProjObject(enterpriseId);

			EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
			entBaseInfoModel2.setEnterpriseId(enterpriseId);
			entBaseInfoModel2.setName(model.getProjObjectName());
			entBaseInfoModel2.setEnName(model.getProjObjectName());
			entBaseInfoModel2.setDeleteFlag("0");
			entBaseInfoModel2.setStatus("未注册");
			entBaseInfoModel2.setCreateBy(loginUserId);
			entBaseInfoModel2.setCreateDt(new Date());
			entBaseInfoModel2.setUpdateBy(loginUserId);
			entBaseInfoModel2.setUpdateDt(new Date());
			entBaseInfoService.insert(entBaseInfoModel2);
		}


		String projId = otherUtil.getSeqNextValString("EI.SEQ_PROJ_ID");
		model.setProjId(projId);
		model.setCreateBy(loginUserId);
		model.setCreateDt(new Date());
		model.setUpdateBy(loginUserId);
		model.setUpdateDt(new Date());

		String getGroupIdsql = "SELECT CODE_VALUE,CODE_NAME FROM COMM_T_CODE WHERE PARENT_ID =266 and code_value='"+orgId+"' ";
		List<Map<String,Object>> list = projInfoService.selectSql2Map(getGroupIdsql);//通过orgId获取所属平台
		long groupId = 4 ;
		if(list.size()>0){
			groupId = Long.valueOf(String.valueOf(list.get(0).get("CODE_NAME")));
		}
		model.setGroupId(groupId);


		ProjectProgressModel progressModel = new ProjectProgressModel();
		String progressId = otherUtil.getSeqNextValString("EI.SEQ_PROJECT_PROGRESS");
		progressModel.setProgressId(progressId);
		progressModel.setInveId("");
		progressModel.setInveName("");
		progressModel.setProjectObject(model.getProjObject());
		progressModel.setProjectObjectName(model.getProjObjectName());
		progressModel.setGroupId(String.valueOf(groupId));
		progressModel.setProjectId(projId);
		progressModel.setProgressDate(new Date());
		progressModel.setStatus("0");
		progressModel.setProgressResource("1");
		progressModel.setCreateBy(loginUserId);
		progressModel.setCreateDt(new Date());
		progressModel.setUpdateBy(loginUserId);
		progressModel.setUpdateDt(new Date());
		String progressHtml = "";
		if(groupId==14||groupId==15){
			model.setProjStatus("1");

			progressHtml = "<span>发起了项目名称为"
					+" <a href='javascript:;' style='color:rgb(53, 108, 203);'  class='viewProjectInfo'  data-id='"+progressId+"' title='"+model.getProjName()+"'>"+model.getProjName()+"</a> 的入库流程！"
					+"</span>";
			progressModel.setField2("0");


		}else{
			model.setProjStatus("2");

			progressHtml = "<span>新增了项目名称为"
					+" <a href='javascript:;' style='color:rgb(53, 108, 203);'  class='viewProjectInfo'  data-id='"+progressId+"'  title='"+model.getProjName()+"'>"+model.getProjName()+"</a> 项目！"
					+"</span>";
			progressModel.setField2("1");
		}
		projInfoService.insert(model);
		//插入项目进展表
		progressModel.setProgressHtml(progressHtml);
		projectProgressService.insert(progressModel);

		//增加项目经理
		ProjMemberModel member = new ProjMemberModel();
		String pkId = otherUtil.getSeqNextValString("EI.SEQ_PROJ_MEMBER");
		member.setPkId(pkId);
		member.setProjId(projId);
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setIsPm("1");
		member.setCreateBy(loginUserId);
		member.setCreateDt(new Date());
		member.setUpdateBy(loginUserId);
		member.setUpdateDt(new Date());
		projMemberService.insert(member);

	}

	@Override
	public void insertprojInfo(ProjInfoModel projInfoModel) {
		List<ProjInfoModel>list = new ArrayList<>();
		list.add(projInfoModel);
		this.insert("ProjInfoModelMapper",list);
	}

	@Override
	public void updateprojInfo(ProjInfoModel projInfoModel) {
		this.update("updateprojInfoMapper",projInfoModel);
	}

	@Override
	public <E> PageInfo<E> selectProjInfoPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectProjInfoPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectProjInfoListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectProjInfoListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectbussinessListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectbussinessListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<ProjInfoModel> selectprojectInvestment(ProjInfoSearchBean projInfoSearchBean) {
		return this.selectList("selectprojectInvestment",projInfoSearchBean);
	}

	@Override
	public List<BussniessResultModel> selectCountBussunessName(ProjInfoSearchBean projInfoSearchBean) {
		return this.selectList("selectCountBussunessName",projInfoSearchBean);
	}

	@Override
	public List<SumProjInfo> seleSumProjInfo(ProjInfoSearchBean projInfoSearchBean) {
		return this.selectList("seleSumProjInfo",projInfoSearchBean);
	}

	@Override
	public <E> PageInfo<E> selectSpvListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectSpvListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> selectdirectProjListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectdirectProjListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> projectInvestedList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("projectInvestedList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> projectGovementList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("projectGovementList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertProjFund(ProjInfoModel projInfoModel, ProjAppInfoModel projAppInfoModel, BdTFitRegulationSelfModel bdTFitRegulationSelfModel, FundInfoModel fund, String userId, EntBaseInfoModel entInfo, String[] ids, String entId, BaseInfoExtendModel baseInfoExtendModel) throws Exception{
		if(!"1".equals(projInfoModel.getProjType())){
			//插入基金
			if("4".equals(projInfoModel.getProjType())){
				fund.setFundSrc("4");
				fund.setSpvType(projInfoModel.getSpvType());
			}else if("2".equals(projInfoModel.getProjType())){
				fund.setFundSrc("2");
			}
			if(StringUtils.isNotEmpty(fund.getFundSts())){
				if("0".equals(fund.getFundSts())){
					//未注册，插入企业表
					String enterpriseId="";
					EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
					entBaseInfoModel1.setName(fund.getRegName());
					List<EntBaseInfoModel> ent = entBaseInfoService.selectList(entBaseInfoModel1);
					if(ent!=null&& !ent.isEmpty()){
						enterpriseId=ent.get(0).getEnterpriseId();
					}else{
						enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
						EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
						entBaseInfoModel2.setEnterpriseId(enterpriseId);
						entBaseInfoModel2.setName(fund.getRegName());
						entBaseInfoModel2.setEnName(fund.getRegName());
						entBaseInfoModel2.setDeleteFlag("0");
						entBaseInfoModel2.setStatus("未注册");
						entBaseInfoModel2.setCreateBy(userId);
						entBaseInfoModel2.setCreateDt(new Date());
						entBaseInfoModel2.setUpdateBy(userId);
						entBaseInfoModel2.setUpdateDt(new Date());
						entBaseInfoService.insert(entBaseInfoModel2);
					}
					fund.setEnteId(enterpriseId);
					//给企业加标签
					if(ids.length>0){
						//删除存在的标签
						EntLabelModel entLabelModel=new EntLabelModel();
						entLabelModel.setEntId(enterpriseId);
						if(StringUtils.isNotEmpty(enterpriseId)) {
							entLabelService.delete(entLabelModel);
						}
						for(int i=0;i<ids.length;i++){
							EntLabelModel entLabel=new EntLabelModel();
							entLabel.setEntId(enterpriseId);
							entLabel.setLabelId(ids[i]);
							entLabel.setCreateBy(userId);
							entLabel.setCreateDt(new Date());
							entLabel.setUpdateBy(userId);
							entLabel.setUpdateDt(new Date());
							entLabelService.insert(entLabel);
						}
					}
				}else{
					fund.setEnteId(entId);
				}
			}
			FundInfoModel fundInfoModel=new FundInfoModel();
			fundInfoModel.setRegName(fund.getRegName());
			//fundInfoModel.setFundSrc(fund.getFundSrc());
			List<FundInfoModel> list=fundInfoService.selectList(fundInfoModel);
			if(list!=null&&list.size()>0){
				projInfoModel.setProjObject(list.get(0).getFundId());
			}else{
				String fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
				fund.setFundId(fundId);
				fund.setFundName(fund.getRegName());
				fund.setCreateBy(userId);
				fund.setCreateDt(new Date());
				fund.setUpdateDt(new Date());
				fund.setUpdateBy(userId);
				fund.setFundCode(projInfoModel.getProjNo());
				fund.setGroupId(1L);
				projInfoModel.setProjObject(fundId);
				fundInfoService.insert(fund);
			}

		}else{
			//判断是否注册且是境内的
			if("2".equals(projInfoModel.getIsRegister())&&"0".equals(projInfoModel.getIsRegion())){
				projInfoModel.setProjObject(projInfoModel.getProjObject());
				baseInfoExtendModel.setEnterpriseId(projInfoModel.getProjObject());
				entInfo.setEnterpriseId(projInfoModel.getProjObject());
				entBaseInfoService.update(entInfo);
			}else{
				String enterpriseId="";
				EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
				entBaseInfoModel1.setName(projInfoModel.getProjName());
				List<EntBaseInfoModel> entList = entBaseInfoService.selectList(entBaseInfoModel1);
				if(entList!=null&&!entList.isEmpty()){
					enterpriseId=entList.get(0).getEnterpriseId();
					projInfoModel.setProjObject(enterpriseId);
					entId=enterpriseId;
				}else{
					enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
					EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
					entBaseInfoModel2.setEnterpriseId(enterpriseId);
					entBaseInfoModel2.setName(projInfoModel.getProjName());
					entBaseInfoModel2.setEnName(projInfoModel.getProjName());
					entBaseInfoModel2.setDeleteFlag("0");
					entBaseInfoModel2.setStatus("未注册");
					entBaseInfoModel2.setCreateBy(userId);
					entBaseInfoModel2.setCreateDt(new Date());
					entBaseInfoModel2.setUpdateBy(userId);
					entBaseInfoModel2.setUpdateDt(new Date());
					entBaseInfoService.insert(entBaseInfoModel2);
					projInfoModel.setProjObject(enterpriseId);
                    entId=enterpriseId;
				}
				baseInfoExtendModel.setEnterpriseId(enterpriseId);
			}
			//查询企业拓展表是否存在，存在修改，不存在新增
			if(StringUtils.isNotEmpty(baseInfoExtendModel.getEnterpriseId())){
				BaseInfoExtendModel extendModel=baseInfoExtendService.selectById(baseInfoExtendModel.getEnterpriseId());
				if(extendModel!=null){
					baseInfoExtendService.update(baseInfoExtendModel);
				}else{
					baseInfoExtendModel.setCreateBy(baseInfoExtendModel.getUpdateBy());
					baseInfoExtendModel.setCreateDt(new Date());
					baseInfoExtendService.insert(baseInfoExtendModel);
				}
			}

		}
        if(!"0".equals(fund.getFundSts())){
        	//已注册传entId
			if(ids!=null&&ids.length>0){
				//删除存在的标签
				EntLabelModel entLabelModel=new EntLabelModel();
				entLabelModel.setEntId(entId);
				if(StringUtils.isNotEmpty(entId)) {
					entLabelService.delete(entLabelModel);
				}
				//String [] id=ids.split(",");
				for(int i=0;i<ids.length;i++){
					EntLabelModel entLabel=new EntLabelModel();
					entLabel.setEntId(entId);
					entLabel.setLabelId(ids[i]);
					entLabel.setCreateBy(userId);
					entLabel.setCreateDt(new Date());
					entLabel.setUpdateBy(userId);
					entLabel.setUpdateDt(new Date());
					entLabelService.insert(entLabel);
				}
			}
		}

		//插入项目表
		String projId = serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
		//String projId = otherUtil.getSeqNextValString("EI.TZ_T_PROJ_INFO");
		projInfoModel.setProjId(projId);
		projInfoModel.setCreateBy(userId);
		projInfoModel.setCreateDt(new Date());
		projInfoModel.setUpdateDt(new Date());
		projInfoModel.setUpdateBy(userId);
		projInfoModel.setGroupId(1L);
		projInfoService.insert(projInfoModel);
		//插入立项表
		projAppInfoModel.setProjId(projId);
		projAppInfoModel.setCreateBy(userId);
		projAppInfoModel.setCreateDt(new Date());
		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoModel.setUpdateBy(userId);
		Integer sum1=projAppInfoService.insert(projAppInfoModel);
		//合规表
		bdTFitRegulationSelfModel.setProjId(projId);
		bdTFitRegulationSelfModel.setFundId(projInfoModel.getInveId());
		bdTFitRegulationSelfModel.setStatus("0");
		bdTFitRegulationSelfModel.setCreateBy(userId);
		bdTFitRegulationSelfModel.setCreateDt(new Date());
		bdTFitRegulationSelfModel.setUpdateDt(new Date());
		bdTFitRegulationSelfModel.setUpdateBy(userId);
		bdTFitRegulationSelfService.insert(bdTFitRegulationSelfModel);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateProjFund(ProjInfoModel model, ProjAppInfoModel projAppInfoModel, BdTFitRegulationSelfModel bdTFitRegulationSelfModel, FundInfoModel fund,EntBaseInfoModel entBaseInfoModel,String[] ids,String userId,BaseInfoExtendModel  baseInfoExtend) throws Exception {
		model.setUpdateDt(new Date());
		projInfoService.update(model);

		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoService.update(projAppInfoModel);

		bdTFitRegulationSelfModel.setUpdateDt(new Date());
		bdTFitRegulationSelfService.update(bdTFitRegulationSelfModel);



		if(!"1".equals(model.getProjType())){


			fund.setUpdateDt(new Date());
			fund.setFundId(model.getProjObject());
			if(StringUtils.isNotEmpty(model.getSpvType())){
				fund.setSpvType(model.getSpvType());
			}
			if(StringUtils.isNotEmpty(model.getProjObject())){
			   FundInfoModel funds=fundInfoService.selectById(model.getProjObject());
			   if(funds!=null){
				   //判断基金名称是否修改了
				   if(!funds.getRegName().equals(fund.getRegName())){
                       //则修改出资主体名称
					   ProjInfoModel model1=new ProjInfoModel();
					   model1.setInveId(fund.getFundId());
					   model1.setInveName(fund.getRegName());
					   projInfoService.update(model1);
				   }
                 if("1".equals(funds.getStatus())){
					 fund.setStatus("1");
				 }
			   }
				fund.setFundName(fund.getRegName());
				fundInfoService.update(fund);
			}
		}else{
			if(StringUtils.isNotEmpty(model.getProjObject())){
				entBaseInfoModel.setUpdateDt(new Date());
				entBaseInfoModel.setEnterpriseId(model.getProjObject());
				entBaseInfoService.update(entBaseInfoModel);
				baseInfoExtend.setEnterpriseId(model.getProjObject());
				BaseInfoExtendModel extendModel=baseInfoExtendService.selectById(model.getProjObject());
				if(extendModel!=null){
					baseInfoExtendService.update(baseInfoExtend);
				}else{
					baseInfoExtend.setCreateBy(baseInfoExtend.getUpdateBy());
					baseInfoExtend.setCreateDt(new Date());
					baseInfoExtendService.insert(baseInfoExtend);
				}
			}
		}
		EntLabelModel entLabelModel=new EntLabelModel();
		entLabelModel.setEntId(entBaseInfoModel.getEnterpriseId());
		if(StringUtils.isNotEmpty(entBaseInfoModel.getEnterpriseId())){
			entLabelService.delete(entLabelModel);
		}

		if(ids!=null&&ids.length>0){
			//先删除之前的标签
			for(int i=0;i<ids.length;i++){
				EntLabelModel entLabel=new EntLabelModel();
				entLabel.setEntId(entBaseInfoModel.getEnterpriseId());
				entLabel.setLabelId(ids[i]);
				entLabel.setCreateDt(new Date());
				entLabel.setCreateBy(userId);
				entLabel.setUpdateDt(new Date());
				entLabel.setUpdateBy(userId);
				entLabelService.insert(entLabel);
			}
		}


	}

	@Override
	public <E> PageInfo<E> selectProjListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectProjListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectProjListByPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectProjListByPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}



	@Override
	public ProjInfoModel selectByprojId(String projId) {
		return (ProjInfoModel)this.selectOne("selectByprojId", projId);

	}

	@Override
	public Map<String,Object>  selectPayAmount(ProjInfoModel model) {
		return (Map<String,Object>)this.selectOne("selectPayAmount", model);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateProjApp(ProjAppInfoModel projAppInfoModel, FundInfoModel fund,String projType,String userId,String projObject,ProjInfoModel model,BdTFitRegulationSelfModel bdTFitRegulationSelfModel) throws Exception{
		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoModel.setUpdateBy(userId);
		projAppInfoService.update(projAppInfoModel);

		model.setUpdateDt(new Date());
		model.setUpdateBy(userId);
		projInfoService.update(model);

		bdTFitRegulationSelfService.update(bdTFitRegulationSelfModel);

		if(!"1".equals(projType)) {
			fund.setUpdateDt(new Date());
			fund.setUpdateBy(userId);
			fund.setFundId(projObject);
			fundInfoService.update(fund);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByFundName(BdTFitRegulationSelfModel bdTFitRegulationSelfModel, ProjInfoModel projInfo1, ProjAppInfoModel projAppInfoModel, String userId, List<String> labels,EntBaseInfoModel entBase,ProjQuitApplModel projQuitApplModel,XjlTPaymentRequestModel xjlTPaymentRequestModel) throws Exception{
		//获取自增长序列
		String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
		projInfo1.setProjId(projId);
		projInfo1.setCreateBy(userId);
		projInfo1.setCreateDt(new Date());
		projInfo1.setUpdateBy(userId);
		projInfo1.setUpdateDt(new Date());
		projInfo1.setStatus("1");
		//直投项目
		//查询是数据库中最新的项目编号
		ProjInfoModel projInfopW=new ProjInfoModel();
		projInfopW.setProjType("1");
		projInfopW.setProjNo("%"+projInfo1.getProjNo()+"%");
		List<ProjInfoModel> list1=projInfoService.selectListByProjNo(projInfopW);
		if(list1!=null&&list1.size()>0){
			//获取最大的编号
			String projNos=list1.get(0).getProjNo();
			if(StringUtils.isNotEmpty(projNos)){
			    if(StringUtils.isNotEmpty(projInfo1.getProjNo())){
                    if(Integer.parseInt(projNos.substring(projInfo1.getProjNo().length()))<=8){
                        projInfo1.setProjNo(projInfo1.getProjNo()+"00"+String.valueOf(Integer.parseInt(projNos.substring(projInfo1.getProjNo().length()))+1));
                    }else if(Integer.parseInt(projNos.substring(projInfo1.getProjNo().length()))<=98){
                        projInfo1.setProjNo(projInfo1.getProjNo()+"0"+String.valueOf(Integer.parseInt(projNos.substring(projInfo1.getProjNo().length()))+1));
                    }else{
                        projInfo1.setProjNo(projInfo1.getProjNo()+""+String.valueOf(Integer.parseInt(projNos.substring(projInfo1.getProjNo().length()))+1));
                    }
                }

			}else{
				projInfo1.setProjNo(projInfo1.getProjNo()+"001");
			}

		}else{
			projInfo1.setProjNo(projInfo1.getProjNo()+"001");
		}
		projInfo1.setGroupId(1L);
		projInfoService.insert(projInfo1);
		bdTFitRegulationSelfModel.setProjId(projId);
		bdTFitRegulationSelfModel.setCreateBy(userId);
		bdTFitRegulationSelfModel.setCreateDt(new Date());
		bdTFitRegulationSelfModel.setUpdateBy(userId);
		bdTFitRegulationSelfModel.setUpdateDt(new Date());
		bdTFitRegulationSelfModel.setStatus("1");
		bdTFitRegulationSelfService.insert(bdTFitRegulationSelfModel);

		projAppInfoModel.setProjId(projId);
		projAppInfoModel.setCreateBy(userId);
		projAppInfoModel.setCreateDt(new Date());
		projAppInfoModel.setUpdateBy(userId);
		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoService.insert(projAppInfoModel);

		String APP_ID=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
		projQuitApplModel.setAppId(APP_ID);
		projQuitApplModel.setIaId(projId);
		projQuitApplModel.setCreateBy(userId);
		projQuitApplModel.setCreateDt(new Date());
		projQuitApplModel.setUpdateBy(userId);
		projQuitApplModel.setUpdateDt(new Date());
		projQuitApplService.insert(projQuitApplModel);

		String PQ_ID=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
		xjlTPaymentRequestModel.setProjectOrFundId(projId);
		xjlTPaymentRequestModel.setPqId(PQ_ID);
		xjlTPaymentRequestModel.setFinanceType("1");
		xjlTPaymentRequestModel.setCreateBy(userId);
		xjlTPaymentRequestModel.setCreateDt(new Date());
		xjlTPaymentRequestModel.setUpdateBy(userId);
		xjlTPaymentRequestModel.setUpdateDt(new Date());
		xjlTPaymentRequestModel.setSumInveAmount(xjlTPaymentRequestModel.getActualPayAmount());
		xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);
		EntLabelModel entLabelModel=new EntLabelModel();
		if(entBase!=null){
			if(StringUtils.isNotEmpty(entBase.getEnterpriseId())){
				entLabelModel.setEntId(entBase.getEnterpriseId());
				entLabelService.delete(entLabelModel);
			}
		}

		if(labels!=null&&labels.size()>0){
			//先删除之前的标签
			for(int i=0;i<labels.size();i++){
				EntLabelModel entLabel=new EntLabelModel();
				entLabel.setEntId(entBase.getEnterpriseId());
				entLabel.setLabelId(labels.get(i));
				entLabel.setCreateDt(new Date());
				entLabel.setCreateBy(userId);
				entLabel.setUpdateDt(new Date());
				entLabel.setUpdateBy(userId);
				entLabelService.insert(entLabel);
			}
		}

	}

	@Override
	public List<Map<String, Object>> getExcelInfo(String fileName,InputStream in) {
		return null;
	}

	@Override
	public List<ProjInfoModel> selectListByProjNo(ProjInfoModel projInfoModel) {
		return this.selectList("selectListByProjNo",projInfoModel);
	}

	@Override
	public Double getsumCurrentAmount(String fundId) {
		return (Double)this.selectOne("getsumCurrentAmount",fundId);
	}

	@Override
	public Double getsumIsExceptAmount(String fundId) {
		return (Double)this.selectOne("getsumIsExceptAmount",fundId);
	}

	@Override
	public Double getSpvSumAmount(String fundId) {
		return (Double)this.selectOne("getSpvSumAmount",fundId);
	}

	@Override
	public Double getSpvIsExceptSumAmount(String fundId) {
		return (Double)this.selectOne("getSpvIsExceptSumAmount",fundId);
	}

	@Override
	public Double getsumCurrentC(String fundId) {
		return (Double)this.selectOne("getsumCurrentC",fundId);
	}

	@Override
	public Double selectSumAmount(String fundId) {
		return (Double)this.selectOne("selectSumAmount",fundId);
	}

	@Override
	public Double selectExceptSumAmount(String fundId) {
		return (Double)this.selectOne("selectExceptSumAmount",fundId);
	}

	@Override
	public Map<String,Object> getsumIsExpCurrentC(String fundId) {
		return (Map<String,Object>)this.selectOne("getsumIsExpCurrentC",fundId);
	}

	@Override
	public Double getsumCurrentRaise(String fundId) {
		return (Double)this.selectOne("getsumCurrentRaise",fundId);
	}

	@Override
	public Double getExSumPayAmount(String fundId) {
		return (Double)this.selectOne("getExSumPayAmount",fundId);
	}

	@Override
	public Map<String, Object> selectSum(String fundId) {
		return(Map<String,Object>)this.selectOne("selectSum", fundId);
	}

	@Override
	public Map<String, Object> selectCountLavel(String fundId) {
		return (Map<String,Object>)this.selectOne("selectCountLavel", fundId);
	}
	@Override
	public Map<String, Object> selectTwoCountLavel(String fundId) {
		return (Map<String,Object>)this.selectOne("selectTwoCountLavel", fundId);
	}

	@Override
	public Map<String, Object> selectFourCountLavel(String fundId) {
		return (Map<String,Object>)this.selectOne("selectFourCountLavel", fundId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void delateOld(String projId) throws Exception{
		projInfoService.deleteById(projId);
		bdTFitRegulationSelfService.deleteById(projId);
		projQuitApplService.deleteById(projId);
		projAppInfoService.deleteById(projId);
		XjlTPaymentRequestModel XjlTPaymentRequestModel =new XjlTPaymentRequestModel();
		XjlTPaymentRequestModel.setProjectOrFundId(projId);
		xjlTPaymentRequestService.delete(XjlTPaymentRequestModel);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateProjOrOthers(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xj, String userId, List<String> labels) throws Exception {
		proj.setUpdateBy(userId);
		proj.setUpdateDt(new Date());
		projInfoService.update(proj);

		bd.setUpdateBy(userId);
		bd.setUpdateDt(new Date());
		bdTFitRegulationSelfService.update(bd);

		projQ.setUpdateBy(userId);
		projQ.setUpdateDt(new Date());
		if(StringUtils.isNotEmpty(projQ.getAppId())){
			projQuitApplService.update("updateInfo",projQ);
		}else{
			projQ.setCreateBy(userId);
			projQ.setCreateDt(new Date());
			String APP_ID=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
			projQ.setAppId(APP_ID);
			projQ.setIaId(proj.getProjId());
			projQuitApplService.insert(projQ);
		}
		projApp.setUpdateBy(userId);
		projApp.setUpdateDt(new Date());
		projAppInfoService.update(projApp);
        //查询
		XjlTPaymentRequestModel xjlTp=new XjlTPaymentRequestModel();
		xjlTp.setProjectOrFundId(proj.getProjId());
		List<XjlTPaymentRequestModel> listX=  xjlTPaymentRequestService.selectList(xjlTp);
		if(listX!=null&&listX.size()>0){
			xj.setUpdateBy(userId);
			xj.setUpdateDt(new Date());
			xjlTPaymentRequestService.update(xj);
		}else{
			xj.setUpdateBy(userId);
			xj.setUpdateDt(new Date());
			xj.setProjectOrFundId(proj.getProjId());
			xj.setCreateDt(new Date());
			xj.setCreateBy(userId);
			String PQ_ID = serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
			xj.setPqId(PQ_ID);
			xjlTPaymentRequestService.insert(xj);
		}

		EntLabelModel entLabelModel=new EntLabelModel();
		if(StringUtils.isNotEmpty(proj.getProjObject())){
			entLabelModel.setEntId(proj.getProjObject());
			entLabelService.delete(entLabelModel);
		}
		if(labels!=null&&labels.size()>0){
			//先删除之前的标签
			for(int i=0;i<labels.size();i++){
				EntLabelModel entLabel=new EntLabelModel();
				entLabel.setEntId(proj.getProjObject());
				entLabel.setLabelId(labels.get(i));
				entLabel.setCreateDt(new Date());
				entLabel.setCreateBy(userId);
				entLabel.setUpdateDt(new Date());
				entLabel.setUpdateBy(userId);
				entLabelService.insert(entLabel);
			}
		}
	}

	@Override
	public void updateProjOrFundInfo(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xjl, String userId, FundInfoModel fund, FundInfoModel fundInfo) throws Exception {
		proj.setUpdateBy(userId);
		proj.setUpdateDt(new Date());
		projInfoService.update(proj);

		bd.setUpdateBy(userId);
		bd.setUpdateDt(new Date());
		bdTFitRegulationSelfService.update(bd);

		projQ.setUpdateBy(userId);
		projQ.setUpdateDt(new Date());
		if(StringUtils.isNotEmpty(projQ.getAppId())){
			projQuitApplService.update(projQ);
		}else{
			projQ.setCreateBy(userId);
			projQ.setCreateDt(new Date());
			String APP_ID=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
			projQ.setAppId(APP_ID);
			projQ.setIaId(proj.getProjId());
			projQuitApplService.insert(projQ);
		}
		projApp.setUpdateBy(userId);
		projApp.setUpdateDt(new Date());
		projAppInfoService.update(projApp);

		xjl.setUpdateBy(userId);
		xjl.setUpdateDt(new Date());
		xjlTPaymentRequestService.update(xjl);

		fund.setUpdateBy(userId);
		fund.setUpdateDt(new Date());
		fundInfoService.update(fund);

		fundInfo.setUpdateBy(userId);
		fundInfo.setUpdateDt(new Date());
		fundInfoService.update(fundInfo);
	}

	@Override
	public void insertProjOrFundInfo(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xjl, String userId, FundInfoModel fund, FundInfoModel fundInfo) throws Exception {

		    proj.setUpdateBy(userId);
			proj.setUpdateDt(new Date());
			proj.setCreateDt(new Date());
			proj.setCreateBy(userId);
		    proj.setProjType("5");
		    proj.setStatus("1");
		    String projNo=getprojNo(proj.getInveId(),"5");
		    proj.setProjNo(projNo);
		    proj.setGroupId(1L);
			projInfoService.insert(proj);

			bd.setUpdateBy(userId);
			bd.setUpdateDt(new Date());
			bd.setCreateDt(new Date());
			bd.setCreateBy(userId);
			bdTFitRegulationSelfService.insert(bd);

			projQ.setUpdateBy(userId);
			projQ.setUpdateDt(new Date());
			projQ.setCreateBy(userId);
			projQ.setCreateDt(new Date());
			String APP_ID=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
			projQ.setAppId(APP_ID);
			projQ.setIaId(proj.getProjId());
			projQuitApplService.insert(projQ);


			projApp.setCreateBy(userId);
			projApp.setCreateDt(new Date());
			projApp.setUpdateBy(userId);
			projApp.setUpdateDt(new Date());
			projAppInfoService.insert(projApp);

			xjl.setUpdateBy(userId);
			xjl.setUpdateDt(new Date());
			xjl.setCreateBy(userId);
			xjl.setCreateDt(new Date());
			xjlTPaymentRequestService.insert(xjl);


			ProjInfoModel projD=new ProjInfoModel();
			projD.setProjName(fund.getFundName());
			projD.setInveId(proj.getInveId());
		     List<ProjInfoModel>  listP= projInfoService.selectList(projD);
		     if(listP==null ||  listP.size()==0){
				 String fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
				 fund.setUpdateBy(userId);
				 fund.setUpdateDt(new Date());
				 fund.setFundId(fundId);
				 //四级基金
				 fund.setFundSrc("5");
				 fund.setStatus("1");
				 fund.setFundCode(projNo);
				 fundInfoService.insert(fund);
			 }
			//三级基金
			fundInfo.setUpdateBy(userId);
			fundInfo.setUpdateDt(new Date());
			fundInfoService.update(fundInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByName(ProjInfoModel projInfo,FundInfoModel fundInfo,ProjAppInfoModel  projApp ,ProjQuitApplModel projQuitApplModel ,FundInfoModel  threeFundInfo ,XjlTPaymentRequestModel  xjlTPaymentRequestModel, String userId,String types,BdTFitRegulationSelfModel bd) throws Exception {
		if("1".equals(types)){
        //四级基金存在了
			//插入立项表
			projInfo.setCreateBy(userId);
			projInfo.setCreateDt(new Date());
			projInfo.setUpdateDt(new Date());
			projInfo.setUpdateBy(userId);
			String projNo=getprojNo(projInfo.getInveId(),projInfo.getProjType());
			projInfo.setProjNo(projNo);
		/*	ProjInfoModel projInfopW=new ProjInfoModel();
			FundInfoModel fundVo=fundInfoService.selectById(projInfo.getInveId());
			if(fundVo!=null){
				if(StringUtils.isNotEmpty(fundVo.getFundCode())){
					projInfopW.setProjType(projInfo.getProjType());
					projInfopW.setProjNo("%"+fundVo.getFundCode()+"J"+"%");
					List<ProjInfoModel> list1=projInfoService.selectListByProjNo(projInfopW);
					if(list1!=null&&list1.size()>0){
						//获取最大的编号
						String projNos=list1.get(0).getProjNo();
						if(StringUtils.isNotEmpty(projNos)){
							if(Integer.parseInt(projNos.substring(projNos.indexOf("J")+1))<=8){
								projInfo.setProjNo(fundVo.getFundCode()+"J0"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("J")+1))+1));
							}else{
								projInfo.setProjNo(fundVo.getFundCode()+"J"+String.valueOf(Integer.parseInt(projNos.substring(projNos.indexOf("J")+1))+1));
							}
						}else{
							projInfo.setProjNo(fundVo.getFundCode()+"J001");
						}

					}else{
						projInfo.setProjNo(fundVo.getFundCode()+"J001");
					}
				}
			}*/
			projInfo.setGroupId(1L);
			projInfoService.insert(projInfo);

			projApp.setCreateBy(userId);
			projApp.setCreateDt(new Date());
			projApp.setUpdateDt(new Date());
			projApp.setUpdateBy(userId);
			projApp.setProjId(projInfo.getProjId());
			projAppInfoService.insert(projApp);

			xjlTPaymentRequestModel.setCreateBy(userId);
			xjlTPaymentRequestModel.setCreateDt(new Date());
			xjlTPaymentRequestModel.setUpdateDt(new Date());
			xjlTPaymentRequestModel.setUpdateBy(userId);
			xjlTPaymentRequestModel.setProjectOrFundId(projInfo.getProjId());
			xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);

			projQuitApplModel.setCreateBy(userId);
			projQuitApplModel.setCreateDt(new Date());
			projQuitApplModel.setUpdateDt(new Date());
			projQuitApplModel.setUpdateBy(userId);
			projQuitApplModel.setIaId(projInfo.getProjId());
			projQuitApplService.insert(projQuitApplModel);

			bd.setCreateBy(userId);
			bd.setCreateDt(new Date());
			bd.setUpdateDt(new Date());
			bd.setUpdateBy(userId);
			bd.setProjId(projInfo.getProjId());
			bdTFitRegulationSelfService.insert(bd);

			fundInfo.setUpdateDt(new Date());
			fundInfo.setUpdateBy(userId);
			fundInfoService.update(fundInfo);

			threeFundInfo.setCreateBy(userId);
			threeFundInfo.setCreateDt(new Date());
			threeFundInfo.setUpdateDt(new Date());
			threeFundInfo.setUpdateBy(userId);
			fundInfoService.update(threeFundInfo);

		}else if("2".equals(types)){

			projInfo.setCreateBy(userId);
			projInfo.setCreateDt(new Date());
			projInfo.setUpdateDt(new Date());
			projInfo.setUpdateBy(userId);
			projInfo.setStatus("1");
			String projNo=getprojNo(projInfo.getInveId(),projInfo.getProjType());
			projInfo.setProjNo(projNo);
			projInfo.setGroupId(1L);
			projInfoService.insert(projInfo);
             //插入立项表
			projApp.setCreateBy(userId);
			projApp.setCreateDt(new Date());
			projApp.setUpdateDt(new Date());
			projApp.setUpdateBy(userId);
			projAppInfoService.insert(projApp);

			xjlTPaymentRequestModel.setCreateBy(userId);
			xjlTPaymentRequestModel.setCreateDt(new Date());
			xjlTPaymentRequestModel.setUpdateDt(new Date());
			xjlTPaymentRequestModel.setUpdateBy(userId);
			xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);

			projQuitApplModel.setCreateBy(userId);
			projQuitApplModel.setCreateDt(new Date());
			projQuitApplModel.setUpdateDt(new Date());
			projQuitApplModel.setUpdateBy(userId);
			projQuitApplModel.setAfterFunderId(projApp.getProjId());
			projQuitApplService.insert(projQuitApplModel);

			bd.setCreateBy(userId);
			bd.setCreateDt(new Date());
			bd.setUpdateDt(new Date());
			bd.setUpdateBy(userId);
			bd.setProjId(projApp.getProjId());
			bdTFitRegulationSelfService.insert(bd);

			fundInfo.setCreateBy(userId);
			fundInfo.setCreateDt(new Date());
			fundInfo.setUpdateDt(new Date());
			fundInfo.setUpdateBy(userId);
			fundInfo.setFundCode(projNo);
			fundInfoService.insert(fundInfo);

			threeFundInfo.setCreateBy(userId);
			threeFundInfo.setCreateDt(new Date());
			threeFundInfo.setUpdateDt(new Date());
			threeFundInfo.setUpdateBy(userId);
			fundInfoService.update(threeFundInfo);

		}else if("3".equals(types)){
			projInfo.setUpdateDt(new Date());
			projInfo.setUpdateBy(userId);
			projInfoService.update(projInfo);
			projApp.setUpdateDt(new Date());
			projApp.setUpdateBy(userId);
			projAppInfoService.update(projApp);

			xjlTPaymentRequestModel.setUpdateDt(new Date());
			xjlTPaymentRequestModel.setUpdateBy(userId);
			XjlTPaymentRequestModel xl=new XjlTPaymentRequestModel();
			xl.setInvestorId(projInfo.getInveId());
			xl.setProjectOrFundId(projInfo.getProjId());
			List<XjlTPaymentRequestModel> list= xjlTPaymentRequestService.selectList(xl);
			if(list!=null&&list.size()>0){
				xjlTPaymentRequestModel.setPqId(list.get(0).getPqId());
				xjlTPaymentRequestService.update(xjlTPaymentRequestModel);
			}

			projQuitApplModel.setUpdateDt(new Date());
			projQuitApplModel.setUpdateBy(userId);
			ProjQuitApplModel PQ=new ProjQuitApplModel();
			PQ.setIaId(projInfo.getProjId());
			List<ProjQuitApplModel> list1=projQuitApplService.selectList(PQ);
			if(list1!=null&&list1.size()>0){
				projQuitApplModel.setAppId(list1.get(0).getAppId());
				projQuitApplService.update(projQuitApplModel);
			}

			bd.setUpdateDt(new Date());
			bd.setUpdateBy(userId);
			bdTFitRegulationSelfService.update(bd);

			fundInfo.setUpdateDt(new Date());
			fundInfo.setUpdateBy(userId);
			fundInfoService.update(fundInfo);

			threeFundInfo.setUpdateDt(new Date());
			threeFundInfo.setUpdateBy(userId);
			fundInfoService.update(threeFundInfo)	;
		}




	}

	public String getprojNo(String inveId,String projType){
		String projNo="";
		ProjInfoModel projInfopW=new ProjInfoModel();
		FundInfoModel fundVo=fundInfoService.selectById(inveId);
		if(fundVo!=null){
			if(StringUtils.isNotEmpty(fundVo.getFundCode())){
				projInfopW.setProjType(projType);
				projInfopW.setProjNo("%"+fundVo.getFundCode()+"J"+"%");
				List<ProjInfoModel> list1=projInfoService.selectListByProjNo(projInfopW);
				if(list1!=null&&list1.size()>0){
					//获取最大的编号
					String projNos=list1.get(0).getProjNo();
					if(StringUtils.isNotEmpty(projNos)){
						if(Integer.parseInt(projNos.substring(projNos.lastIndexOf("J")+1))<=8){
							projNo=fundVo.getFundCode()+"J0"+String.valueOf(Integer.parseInt(projNos.substring(projNos.lastIndexOf("J")+1))+1);
						}else{
							projNo=fundVo.getFundCode()+"J"+String.valueOf(Integer.parseInt(projNos.substring(projNos.lastIndexOf("J")+1))+1);
						}
					}else{
						projNo=fundVo.getFundCode()+"J001";
					}

				}else{
					projNo=fundVo.getFundCode()+"J001";
				}
			}
		}
		return projNo;
	}

	@Override
	public ProjInfoModel selectInfoProj(String projId) {
		return (ProjInfoModel)this.selectOne("selectInfoProj",projId);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertInternalProj(ProjInfoModel pro, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bdFit, String userId,String[] ids,ProjMemberModel projNanage,ProjMemberModel generalManager,List<ProjMemberModel> operators) throws Exception {
		//判断是否注册
		if("0".equals(pro.getIsRegister())){
			//想查询企业名称是否存在
			EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
			entBaseInfoModel1.setName(pro.getProjObjectName());
			entBaseInfoModel1.setDeleteFlag("0");
			List<EntBaseInfoModel> list=entBaseInfoService.selectList(entBaseInfoModel1);
			if(list!=null&& !list.isEmpty()){
				pro.setProjObject(list.get(0).getEnterpriseId());
			}else {
				//未注册先插入企业表
				String enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
				EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
				entBaseInfoModel2.setEnterpriseId(enterpriseId);
				entBaseInfoModel2.setName(pro.getProjObjectName());
				entBaseInfoModel2.setEnName(pro.getProjObjectName());
				entBaseInfoModel2.setDeleteFlag("0");
				entBaseInfoModel2.setStatus("未注册");
				entBaseInfoModel2.setCreateBy(userId);
				entBaseInfoModel2.setCreateDt(new Date());
				entBaseInfoModel2.setUpdateBy(userId);
				entBaseInfoModel2.setUpdateDt(new Date());
				entBaseInfoService.insert(entBaseInfoModel2);
				pro.setProjObject(enterpriseId);
			}

		}

		String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
		pro.setCreateBy(userId);
		pro.setCreateDt(new Date());
		pro.setUpdateDt(new Date());
		pro.setUpdateBy(userId);
		pro.setProjId(projId);
		projInfoService.insert(pro);
			//给企业加标签
			if(ids.length>0){
				if(StringUtils.isNotEmpty(pro.getProjObject())){
					EntLabelModel entLabelModel1=new EntLabelModel();
					if(StringUtils.isNotEmpty(pro.getProjObject())){
						entLabelModel1.setEntId(pro.getProjObject());
						entLabelService.delete(entLabelModel1);
					}
				}
				for(int i=0;i<ids.length;i++){
					EntLabelModel entLabel=new EntLabelModel();
					entLabel.setEntId(pro.getProjObject());
					entLabel.setLabelId(ids[i]);
					entLabel.setCreateBy(userId);
					entLabel.setCreateDt(new Date());
					entLabel.setUpdateBy(userId);
					entLabel.setUpdateDt(new Date());
					entLabelService.insert(entLabel);
				}
			}

		projApp.setCreateBy(userId);
		projApp.setCreateDt(new Date());
		projApp.setUpdateDt(new Date());
		projApp.setUpdateBy(userId);
		projApp.setProjId(projId);
		projAppInfoService.insert(projApp);

		bdFit.setCreateBy(userId);
		bdFit.setCreateDt(new Date());
		bdFit.setUpdateDt(new Date());
		bdFit.setUpdateBy(userId);
		bdFit.setProjId(projId);
		bdTFitRegulationSelfService.insert(bdFit);

		//项目经理，经办人，分管副总存在一张表里面
		if(projNanage!=null){
			//增加项目经理
			if(StringUtils.isNotEmpty(projNanage.getMemberId())){
				String pkId1 = serialnoService.getSequence("EI.TZ_T_PROJ_MEMBER");
				projNanage.setPkId(pkId1);
				projNanage.setProjId(projId);
				projNanage.setMemberId(projNanage.getMemberId());
				projNanage.setMemberName(projNanage.getMemberName());
				projNanage.setIsPm("1");
				projNanage.setCreateBy(userId);
				projNanage.setCreateDt(new Date());
				projNanage.setUpdateBy(userId);
				projNanage.setUpdateDt(new Date());
				projMemberService.insert(projNanage);
			}
		}
		if(generalManager!=null){
			//增加分管副总
			if(StringUtils.isNotEmpty(generalManager.getMemberId())){
				String pkId2 = serialnoService.getSequence("EI.TZ_T_PROJ_MEMBER");
				generalManager.setPkId(pkId2);
				generalManager.setProjId(projId);
				generalManager.setIsPm("2");
				generalManager.setCreateBy(userId);
				generalManager.setCreateDt(new Date());
				generalManager.setUpdateBy(userId);
				generalManager.setUpdateDt(new Date());
				if(StringUtils.isNotEmpty(generalManager.getMemberId())){
					projMemberService.insert(generalManager);
				}
			}
		}
		if(operators!=null&&operators.size()>0){
			//增加分管副总
			for(ProjMemberModel operator:operators){
				String pkId3 =serialnoService.getSequence("EI.TZ_T_PROJ_MEMBER");
				operator.setPkId(pkId3);
				operator.setProjId(projId);
				operator.setIsPm("0");
				operator.setCreateBy(userId);
				operator.setCreateDt(new Date());
				operator.setUpdateBy(userId);
				operator.setUpdateDt(new Date());
				if(StringUtils.isNotEmpty(operator.getMemberId())){
					projMemberService.insert(operator);
				}

			}
		}

		//新增文件库，在表BD_T_ATTA_ITEM插入数据
		//projType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金
		//ITEM_TYPE=1关联项目表的项目id（PROJ_TYPE=1的项目：项目投管），
		//ITEM_TYPE=3关联项目表的项目id（PROJ_TYPE=2的项目：基金投管），
		//母基金
		try{
			//投项目
			bdTAttaItemService.addEiTAttachment(projId, "1", userId);
			System.out.println("---------------新增文件库-投项目" + projId);
		}catch (Exception e){
			throw new RuntimeException("新增文件库失败");
			//System.out.println("新增文件库失败");
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateInternalProj(ProjInfoModel pro, String userId, String[] ids, ProjMemberModel projNanage, ProjMemberModel generalManager, List<ProjMemberModel> operators,BdTFitRegulationSelfModel bdTFitRegulationSelfModel) throws Exception {
		pro.setCreateBy(userId);
		pro.setCreateDt(new Date());
		pro.setUpdateDt(new Date());
		pro.setUpdateBy(userId);
		projInfoService.update(pro);
		bdTFitRegulationSelfModel.setUpdateDt(new Date());
		bdTFitRegulationSelfModel.setUpdateBy(userId);
		bdTFitRegulationSelfService.update(bdTFitRegulationSelfModel);
		//给企业加标签
		if(ids.length>0){
			if(StringUtils.isNotEmpty(pro.getProjObject())){
				EntLabelModel entLabelModel1=new EntLabelModel();
				if(StringUtils.isNotEmpty(pro.getProjObject())){
					entLabelModel1.setEntId(pro.getProjObject());
					entLabelService.delete(entLabelModel1);
				}
			}
			for(int i=0;i<ids.length;i++){
				EntLabelModel entLabel=new EntLabelModel();
				entLabel.setEntId(pro.getProjObject());
				entLabel.setLabelId(ids[i]);
				entLabel.setCreateBy(userId);
				entLabel.setCreateDt(new Date());
				entLabel.setUpdateBy(userId);
				entLabel.setUpdateDt(new Date());
				entLabelService.insert(entLabel);
			}
		}
       //增加分管副总
		ProjMemberModel projMemberM=new ProjMemberModel();
		if(StringUtils.isNotEmpty(pro.getProjId())){
			projMemberM.setProjId(pro.getProjId());
			projMemberService.delete(projMemberM);
		}
		//项目经理，经办人，分管副总存在一张表里面
		if(projNanage!=null){
			//修改项目经理
			if(StringUtils.isNotEmpty(projNanage.getMemberId())){
				String pkId = serialnoService.getSequence("EI.TZ_T_PROJ_MEMBER");
				projNanage.setPkId(pkId);
				projNanage.setProjId(pro.getProjId());
				projNanage.setMemberId(projNanage.getMemberId());
				projNanage.setMemberName(projNanage.getMemberName());
				projNanage.setIsPm("1");
				projNanage.setCreateBy(userId);
				projNanage.setCreateDt(new Date());
				projNanage.setUpdateBy(userId);
				projNanage.setUpdateDt(new Date());
				projMemberService.insert(projNanage);
			}
		}
		if(generalManager!=null){
			if(StringUtils.isNotEmpty(generalManager.getMemberId())){
				String pkId = serialnoService.getSequence("EI.TZ_T_PROJ_MEMBER");
				generalManager.setPkId(pkId);
				generalManager.setProjId(pro.getProjId());
				generalManager.setIsPm("2");
				generalManager.setCreateBy(userId);
				generalManager.setCreateDt(new Date());
				generalManager.setUpdateBy(userId);
				generalManager.setUpdateDt(new Date());
				if(StringUtils.isNotEmpty(generalManager.getMemberId())){
					projMemberService.insert(generalManager);
				}
			}
		}
		if(operators!=null&&operators.size()>0){
			//增加分管副总
			for(ProjMemberModel operator:operators){
				String pkId =serialnoService.getSequence("EI.TZ_T_PROJ_MEMBER");
				operator.setPkId(pkId);
				operator.setProjId(pro.getProjId());
				operator.setIsPm("0");
				operator.setCreateBy(userId);
				operator.setCreateDt(new Date());
				operator.setUpdateBy(userId);
				operator.setUpdateDt(new Date());
				if(StringUtils.isNotEmpty(operator.getMemberId())){
					projMemberService.insert(operator);
				}
			}
		}
	}

	@Override
	public <E> PageInfo<E> getInternalProjList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getInternalProjList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> getJchyProjList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getJchyProjList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}


	@Override
	public List<ProjInfoModel> selectListByInveId(ProjInfoModel projInfo) {
		return this.selectList("selectListByInveId",projInfo);
	}

	@Override
	public Double selectSumPayAmount(String fundId) {
		return (Double)this.selectOne("selectSumPayAmount",fundId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addProjOrOthers(ProjInfoModel proj, ProjAppInfoModel projApp, BdTFitRegulationSelfModel bd, ProjQuitApplModel projQ, XjlTPaymentRequestModel xj, String userId, List<String> labels) throws Exception {
		//判断是否注册过
		if(!"2".equals(proj.getIsRegister())||!"0".equals(proj.getIsRegion())){
			String enterpriseId="";
			EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
			entBaseInfoModel1.setName(proj.getProjName());
			List<EntBaseInfoModel> entList = entBaseInfoService.selectList(entBaseInfoModel1);
			if(entList!=null&&!entList.isEmpty()){
				enterpriseId=entList.get(0).getEnterpriseId();
				proj.setProjObject(enterpriseId);
			}else{
				enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
				EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
				entBaseInfoModel2.setEnterpriseId(enterpriseId);
				entBaseInfoModel2.setName(proj.getProjName());
				entBaseInfoModel2.setEnName(proj.getProjName());
				entBaseInfoModel2.setDeleteFlag("0");
				entBaseInfoModel2.setStatus("未注册");
				entBaseInfoModel2.setCreateBy(userId);
				entBaseInfoModel2.setCreateDt(new Date());
				entBaseInfoModel2.setUpdateBy(userId);
				entBaseInfoModel2.setUpdateDt(new Date());
				entBaseInfoService.insert(entBaseInfoModel2);
				proj.setProjObject(enterpriseId);
			}
		}

		FundInfoModel fundInfoModel = fundInfoService.selectById(proj.getInveId());
		System.out.print("****进入service1********");
		if(fundInfoModel!=null){
			if("2".equals(fundInfoModel.getFundSrc())||"4".equals(fundInfoModel.getFundSrc())){
				//三级基金直投项目
				proj.setProjType("3");
				System.out.print("****进入service2********");
			}else if("5".equals(fundInfoModel.getFundSrc())){
				//四级基金直投
				proj.setProjType("6");
				System.out.print("****进入service3********");
			}
			//查询是数据库中最新的项目编号
			//查询是数据库中最新的项目编号
			ProjInfoModel projInfopW=new ProjInfoModel();
			//projInfopW.setProjType(proj.getProjType());
			projInfopW.setInveId(proj.getInveId());
			//projInfopW.setProjNo("%"+fundInfoModel.getFundCode()+"%");
			List<ProjInfoModel> list1=projInfoService.selectList(projInfopW);
			//List<ProjInfoModel> list1=projInfoService.selectListByProjNo(projInfopW);
			if(list1!=null&&list1.size()>0){
				//获取最大的编号
				int projNos=list1.size();
				if(projNos>0){
					if(StringUtils.isNotEmpty(fundInfoModel.getFundCode())){
						if(projNos<=8){
							proj.setProjNo(fundInfoModel.getFundCode()+"00"+String.valueOf(projNos+1));
						}else if(projNos<=98){
							proj.setProjNo(fundInfoModel.getFundCode()+"0"+String.valueOf(projNos+1));
						}else{
							proj.setProjNo(fundInfoModel.getFundCode()+""+String.valueOf(projNos+1));
						}
					}

				}else{
					proj.setProjNo(fundInfoModel.getFundCode()+"001");
				}

			}else{
				proj.setProjNo(fundInfoModel.getFundCode()+"001");
			}


		}

		proj.setUpdateBy(userId);
		proj.setUpdateDt(new Date());
		proj.setGroupId(1L);
		proj.setCreateBy(userId);
		proj.setCreateDt(new Date());
		proj.setStatus("1");
		projInfoService.insert(proj);
		System.out.print("****进入service555********");
		bd.setUpdateBy(userId);
		bd.setUpdateDt(new Date());
		bd.setCreateBy(userId);
		bd.setCreateDt(new Date());
		bdTFitRegulationSelfService.insert(bd);
		System.out.print("****进入service666********");
			projQ.setUpdateBy(userId);
			projQ.setUpdateDt(new Date());
			projQ.setCreateBy(userId);
			projQ.setCreateDt(new Date());
			projQ.setIaId(proj.getProjId());
		    projQ.setStatus("0");
			projQuitApplService.insert(projQ);

		projApp.setUpdateBy(userId);
		projApp.setUpdateDt(new Date());
		projApp.setCreateBy(userId);
		projApp.setCreateDt(new Date());
		projAppInfoService.insert(projApp);

		xj.setCreateBy(userId);
		xj.setCreateDt(new Date());
		xj.setUpdateBy(userId);
		xj.setUpdateDt(new Date());
		xj.setStatus("1");
		xj.setProjectOrFundId(proj.getProjId());
		xjlTPaymentRequestService.insert(xj);
		//给企业加标签

		if(labels!=null&&labels.size()>0){
			if(StringUtils.isNotEmpty(proj.getProjObject())){
				EntLabelModel entLabelModel1=new EntLabelModel();
				if(StringUtils.isNotEmpty(proj.getProjObject())){
					entLabelModel1.setEntId(proj.getProjObject());
					entLabelService.delete(entLabelModel1);
				}
			}
			//先删除之前的标签
			for(int i=0;i<labels.size();i++){
				EntLabelModel entLabel=new EntLabelModel();
				entLabel.setEntId(proj.getProjObject());
				entLabel.setLabelId(labels.get(i));
				entLabel.setCreateDt(new Date());
				entLabel.setCreateBy(userId);
				entLabel.setUpdateDt(new Date());
				entLabel.setUpdateBy(userId);
				entLabelService.insert(entLabel);
			}
		}
	}

	@Override
	public List<ProjInfoModel> getNewProCode(String projCode) {
		return this.selectList("getNewProCode",projCode);
	}

	@Override
	public Double selectPassPayAmount(String fundId) {
		return (Double)this.selectOne("selectPassPayAmount",fundId);
	}

	@Override
	public <E> PageInfo<E> selectByFofInfoList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectByFofInfoList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectFofDirectList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFofDirectList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public List<ProjInfoModel> selectByInveList(ProjInfoModel projInfo) {
		return this.selectList("selectByInveList", projInfo);
	}

	/**
	 * 导入直投项目（存在进行修改，不存在进行插入）
	 *
	 * @param request
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public BaseResponse importBusinessManager(BusinessManagerImportRequest request) {


		System.out.print("***************jinru0000000000000000000000000000000000000000000000000000000000000000000000000000a****************************************************8");
		BaseResponse response = new BaseResponse();
		List<BusinessManagerModel> list = request.getDateList();
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(request, userModel);
		//BeanUtils.copyProperties(request, userModel);
		for (BusinessManagerModel model : list) {
			EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
			if(StringUtils.isNotEmpty(model.getEntName())){
				entBaseInfoModel.setName(replaceBlank(model.getEntName()));
			}
			entBaseInfoModel.setDeleteFlag("0");
			List<EntBaseInfoModel>  ents=entBaseInfoService.selectList(entBaseInfoModel);
			if(ents==null||ents.size()==0){
				response.error("企业名称为:" +model.getEntName()+"请通知管理员维护。");
				return response;
			}
			System.out.print("***************0000000000000000000000000000000000000000000000000000000000000000000000000000a****************************************************8");
			BdTFitRegulationSelfModel bd=new BdTFitRegulationSelfModel();
			//BeanUtils.copyProperties(bd,model);
			if(StringUtils.isNotEmpty(model.getCurTmoneyPer())){
				bd.setCurTmoneyPer(Double.valueOf(model.getCurTmoneyPer())); //本基金投资金额占本基金认缴出资总额的比例(%)
			}
			if(StringUtils.isNotEmpty(model.getAllTmoney())){
				bd.setAllTmoney(Double.valueOf(model.getAllTmoney())); //本基金对项目累计投资金额（万元）
			}
			if(StringUtils.isNotEmpty(model.getFunToPro())){
				bd.setFunToPro(Double.valueOf(model.getFunToPro()));
			}
            if(StringUtils.isNotEmpty(model.getAllTmoneyPer())){
				bd.setAllTmoneyPer(Double.valueOf(model.getAllTmoneyPer()));
			}
			if(StringUtils.isNotEmpty(model.getIsTwelveNine())){
				bd.setIsTwelve(model.getIsTwelveNine());
			}
			bd.setIndustry(model.getIndustry());
			ProjAppInfoModel projApp = new ProjAppInfoModel();
			projApp.setInveRole(model.getInveRole());
			projApp.setInveType(model.getInveType());
			projApp.setInveRounds(model.getInveRounds());
			CommTCodeModel commTCodeModel7=new CommTCodeModel();
			commTCodeModel7.setParentId("213");
			if(model!=null){
				commTCodeModel7.setCodeName(model.getFinaRounds());
			}
			List<CommTCodeModel> listCom=commTCodeService.selectList(commTCodeModel7);
			if(listCom!=null&&listCom.size()>0){
				projApp.setFinaRounds(listCom.get(0).getCodeValue());
			}
			System.out.print("***************110000000000000000000000000000000000000000000000000000000000000000000000000000a****************************************************8");
			projApp.setFinaTimes(model.getFinaTimes());
			//BeanUtils.copyProperties(projApp,model);
			if(StringUtils.isNotEmpty(model.getFinaAmount())){
				projApp.setFinaAmount(Double.valueOf(model.getFinaAmount()));
			}
			if(StringUtils.isNotEmpty(model.getActualAmount())){
				projApp.setActualAmount(Double.valueOf(model.getActualAmount()));
			}
            if(StringUtils.isNotEmpty(model.getIntendedAmount())){
				projApp.setIntendedAmount(Double.valueOf(model.getIntendedAmount()));
			}
            if(StringUtils.isNotEmpty(model.getPreMoney())){
				projApp.setPreMoney(Double.valueOf(model.getPreMoney()));
			}
            if(StringUtils.isNotEmpty(model.getPostMoney())){
				projApp.setPostMoney(Double.valueOf(model.getPostMoney()));
			}
			System.out.print("***************7770000000000000000000000000000000000000000000000000000000000000000000000000000a****************************************************8");
			ProjQuitApplModel projQ=new ProjQuitApplModel();
			if("全部退出".equals(model.getQuitType())){
				projQ.setQuitType("1");
			}
			if("部分退出".equals(model.getQuitType())){
				projQ.setQuitType("2");
			}
			BeanUtils.copyProperties(projQ,model);
			if(StringUtils.isNotEmpty(model.getRevenue())){
				projQ.setRevenue(Double.valueOf(model.getRevenue()));
			}
			projQ.setIsDistribute(model.getIsDistribute());
			if(StringUtils.isNotEmpty(model.getDistributeMoney())){
				projQ.setDistributeMoney(Double.valueOf(model.getDistributeMoney()));
			}
			if(StringUtils.isNotEmpty(model.getDistributeGovMoney())){
				projQ.setDistributeGovMoney(Double.valueOf(model.getDistributeGovMoney()));

			}
			ProjInfoModel projInfo1 = new ProjInfoModel();
			CommTGicsModel commTGicsModel = new CommTGicsModel();
			if(StringUtils.isNotEmpty(model.getIndustryNameThree())){
				commTGicsModel.setGicsName(model.getIndustryNameThree());
				commTGicsModel.setGicsLevel("3");
				List<CommTGicsModel> commTGicsList = commTGicsService.selectList(commTGicsModel);
				if (commTGicsList != null && commTGicsList.size() > 0) {
					String industryNameId=commTGicsList.get(0).getId();
					projInfo1.setIndustry(industryNameId);
					projInfo1.setIndustryName(commTGicsList.get(0).getGicsName());
				}
			}
			if(StringUtils.isEmpty(model.getIndustryNameThree())&&StringUtils.isNotEmpty(model.getIndustryNameTwo())){
				commTGicsModel.setGicsLevel("2");
				commTGicsModel.setGicsName(model.getIndustryNameTwo());
				List<CommTGicsModel> commTGicsList = commTGicsService.selectList(commTGicsModel);
				if (commTGicsList != null && commTGicsList.size() > 0) {
					String industryNameId=commTGicsList.get(0).getId();
					projInfo1.setIndustry(industryNameId);
					projInfo1.setIndustryName(commTGicsList.get(0).getGicsName());
				}
			}
			//根据填写的所属行业查询
			CommTNewModel commTNewModel=new CommTNewModel();
			if(StringUtils.isNotEmpty(model.getNewIndustryNameThree())){
				commTNewModel.setNewName(model.getNewIndustryNameThree());
				commTNewModel.setNewLevel("3");
				List<CommTNewModel> commTNewList=commTNewService.selectList(commTNewModel);
				String newId="";
				if (commTNewList != null && commTNewList.size() > 0) {
					newId=commTNewList.get(0).getId();
					projInfo1.setNewIndustry(newId);
					projInfo1.setNewIndustryName(commTNewList.get(0).getNewName());
				}
			}

			Date payDate=null;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(StringUtils.isNotEmpty(model.getQuitDt())){
					projQ.setQuitDt(formatter.parse(model.getQuitDt()));
				}
				if(StringUtils.isNotEmpty(model.getActualPayDate())){
					String act=model.getActualPayDate().replaceAll("/","-");
					payDate=formatter.parse(act);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

			FundInfoModel  fundInfoModel = fundInfoService.selectByName(replaceBlank(model.getFundName()));
			if (fundInfoModel == null) {
				response.error("子基金:" + model.getFundName() + "不存在");
				return response;
			}

			projInfo1.setProjStatus(model.getProjStatus());
			projInfo1.setProjProperty(model.getProjProperty());
			BeanUtils.copyProperties(projInfo1,model);
			projInfo1.setProjObject(ents.get(0).getEnterpriseId());
			projInfo1.setProjObjectName(ents.get(0).getName());
			projInfo1.setProjName(ents.get(0).getName());
			projInfo1.setStatus("1");
			projInfo1.setGroupId(1L);
			projInfo1.setInveName(fundInfoModel.getFundName());
			projInfo1.setInveId(fundInfoModel.getFundId());
			XjlTPaymentRequestModel xjlTPaymentRequestModel=new XjlTPaymentRequestModel();
			//出资主体
			if("2".equals(fundInfoModel.getFundSrc())||"4".equals(fundInfoModel.getFundSrc())){
				//三级基金直投项目
				projInfo1.setProjType("3");
			}else if("5".equals(fundInfoModel.getFundSrc())){
				//四级基金直投
				projInfo1.setProjType("6");
			}
			xjlTPaymentRequestModel.setInvestorId(fundInfoModel.getFundId());
			//projInfo1.setProjNo(getZtprojNo(fundInfoModel.getFundCode(),projInfo1.getProjType()));
			projInfo1.setProjNo(getZtNewprojNo(fundInfoModel.getFundId(),fundInfoModel.getFundCode()));
			//暂时吧基金的放在这个字段
			projInfo1.setProjNo(fundInfoModel.getFundCode());
			xjlTPaymentRequestModel.setInvestorId(fundInfoModel.getFundId());
			if(StringUtils.isNotEmpty(model.getActualPayAmount())){
				xjlTPaymentRequestModel.setActualPayAmount(Double.valueOf(model.getActualPayAmount()));
				xjlTPaymentRequestModel.setSumInveAmount(xjlTPaymentRequestModel.getActualPayAmount());
			}
			xjlTPaymentRequestModel.setActualPayDate(payDate);
			xjlTPaymentRequestModel.setFinanceType("1");
			xjlTPaymentRequestModel.setCreateBy(String.valueOf(userModel.getId()));
			xjlTPaymentRequestModel.setCreateDt(new Date());
			xjlTPaymentRequestModel.setUpdateBy(String.valueOf(userModel.getId()));
			xjlTPaymentRequestModel.setUpdateDt(new Date());

			bd.setUpdateBy(String.valueOf(userModel.getId()));
			bd.setUpdateDt(new Date());
			bd.setStatus("1");

			projApp.setUpdateBy(String.valueOf(userModel.getId()));
			projApp.setUpdateDt(new Date());
			projQ.setUpdateBy(String.valueOf(userModel.getId()));
			projQ.setUpdateDt(new Date());
			EntLabelModel entLabelModel = new EntLabelModel();
			if (StringUtils.isNotEmpty(projInfo1.getProjObject())) {
				entLabelModel.setEntId(projInfo1.getProjObject());
				entLabelService.delete(entLabelModel);
			}


			if (model.getLabels() != null && model.getLabels().size()> 0) {
				//先删除之前的标签
				for (int i = 0; i < model.getLabels().size(); i++) {
					EntLabelModel entLabel = new EntLabelModel();
					entLabel.setEntId(projInfo1.getProjObject());
					entLabel.setLabelId(model.getLabels().get(i));
					entLabel.setCreateDt(new Date());
					entLabel.setCreateBy(String.valueOf(userModel.getId()));
					entLabel.setUpdateDt(new Date());
					entLabel.setUpdateBy(String.valueOf(userModel.getId()));
					entLabelService.insert(entLabel);
				}
			}
			System.out.print("***************wwwww0000000000000000000000000000000000000000000000000000000000000000000000000000a****************************************************8");
			ProjInfoModel projInfoDT=new ProjInfoModel();
			if(model!=null){
				if(StringUtils.isNotEmpty(projInfo1.getProjName())){
					projInfoDT.setProjName(projInfo1.getProjName());
				}

				projInfoDT.setInveRounds(model.getInveRounds());
			}
			if(fundInfoModel!=null){
				projInfoDT.setInveId(fundInfoModel.getFundId());
			}

			List<ProjInfoModel> pr=projInfoService.selectByInveList(projInfoDT);
			if( pr!=null && !pr.isEmpty()){

				String projId=pr.get(0).getProjId();
				projQ.setIaId(projId);
				xjlTPaymentRequestModel.setProjectOrFundId(projId);
				ProjQuitApplModel projQ1=new ProjQuitApplModel();
				projQ1.setIaId(projId);
				List<ProjQuitApplModel> list1=projQuitApplService.selectList(projQ1);
				if(list1!=null&&list1.size()>0){
					projQ.setAppId(list1.get(0).getAppId());
				}
				projQuitApplService.update(projQ);
				xjlTPaymentRequestService.update(xjlTPaymentRequestModel);
				projInfo1.setProjId(projId);
				projApp.setProjId(projId);
				projInfoService.update(projInfo1);
				projAppInfoService.update(projApp);
				bd.setProjId(projId);
				bdTFitRegulationSelfService.update(bd);

			}else{
				String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
				String PQ_ID = serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
				String APP_ID = serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");

				projApp.setCreateBy(String.valueOf(userModel.getId()));
				projApp.setCreateDt(new Date());
				projApp.setUpdateBy(String.valueOf(userModel.getId()));
				projApp.setUpdateDt(new Date());
				projApp.setProjId(projId);

				xjlTPaymentRequestModel.setCreateBy(String.valueOf(request.getCreateBy()));
				xjlTPaymentRequestModel.setCreateDt(new Date());
				xjlTPaymentRequestModel.setUpdateBy(String.valueOf(request.getCreateBy()));
				xjlTPaymentRequestModel.setUpdateDt(new Date());
				xjlTPaymentRequestModel.setPqId(PQ_ID);
				xjlTPaymentRequestModel.setProjectOrFundId(projId);
				xjlTPaymentRequestModel.setStatus("4");
				projInfo1.setUpdateBy(String.valueOf(request.getCreateBy()));
				projInfo1.setUpdateDt(new Date());
				projInfo1.setCreateBy(String.valueOf(request.getCreateBy()));
				projInfo1.setCreateDt(new Date());
				projInfo1.setProjId(projId);

				projQ.setAppId(APP_ID);
				projQ.setIaId(projId);
				projApp.setProjId(projId);

				projQ.setUpdateBy(String.valueOf(request.getCreateBy()));
				projQ.setUpdateDt(new Date());
				projQ.setCreateBy(String.valueOf(request.getCreateBy()));
				projQ.setCreateDt(new Date());

				projApp.setCreateBy(String.valueOf(request.getCreateBy()));
				projApp.setCreateDt(new Date());

				bd.setProjId(projId);
				bd.setCreateBy(String.valueOf(request.getCreateBy()));
				bd.setCreateDt(new Date());
				bd.setUpdateBy(String.valueOf(request.getCreateBy()));
				bd.setUpdateDt(new Date());
				projQ.setStatus("bd");
				bdTFitRegulationSelfService.insert(bd);
				projAppInfoService.insert(projApp);
				projQ.setStatus("0");
				projQuitApplService.insert(projQ);
				xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);
				projInfo1.setGroupId(1L);
				projInfoService.insert(projInfo1);
			}
		}
		return response;
	}




	public String getZtprojNo(String fundCode, String projType) {
		String projNo = "";
		ProjInfoModel projInfopW = new ProjInfoModel();
		projInfopW.setProjType("1");
		projInfopW.setProjNo("%" + fundCode + "%");
		List<ProjInfoModel> list1 = projInfoService.selectListByProjNo(projInfopW);
		if (list1 != null && list1.size() > 0) {
			//获取最大的编号
			String projNos = list1.get(0).getProjNo();
			if (StringUtils.isNotEmpty(projNos)) {
				if (StringUtils.isNotEmpty(fundCode)) {
					if (Integer.parseInt(projNos.substring(fundCode.length())) <= 8) {
						projNo=fundCode + "00" + String.valueOf(Integer.parseInt(projNos.substring(fundCode.length())) + 1);
					} else if (Integer.parseInt(projNos.substring(fundCode.length())) <= 98) {
						projNo=fundCode + "0" + String.valueOf(Integer.parseInt(projNos.substring(fundCode.length())) + 1);
					} else {
						projNo=fundCode + "" + String.valueOf(Integer.parseInt(projNos.substring(fundCode.length())) + 1);
					}
				}

			} else {
				projNo=fundCode + "001";
			}

		} else {
			projNo=fundCode + "001";
		}
		return projNo;
	}


	public String getZtNewprojNo(String inveId, String fundCode) {
		String projNo = "";
		//查询是数据库中最新的项目编号
		ProjInfoModel projInfopW=new ProjInfoModel();
		projInfopW.setInveId(inveId);
		List<ProjInfoModel> list1=projInfoService.selectList(projInfopW);
		if(list1!=null&&list1.size()>0){
			//获取最大的编号
			int projNos=list1.size();
			if(projNos>0){
				if(StringUtils.isNotEmpty(fundCode)){
					if(projNos<=8){
						projNo=fundCode+"00"+String.valueOf(projNos+1);
					}else if(projNos<=98){
						projNo=fundCode+"0"+String.valueOf(projNos+1);
					}else{
						projNo=fundCode+String.valueOf(projNos+1);
					}
				}

			}else{
				projNo=fundCode+"001";
			}

		}else{
			projNo=fundCode+"001";
		}
		return projNo;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public BaseResponse importFundManager(FundManagerImportRequest request) {
		BaseResponse response = new BaseResponse();
		List<FundManagerModel> list = request.getDateList();
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(request, userModel);
		//BeanUtils.copyProperties(request, userModel);
		for (FundManagerModel model : list) {
			//判断注册状态
			FundInfoModel fundInfo = new FundInfoModel();
			EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
			entBaseInfoModel.setName(model.getFundName().replaceAll("\r|\n",""));
			entBaseInfoModel.setDeleteFlag("0");
			List<EntBaseInfoModel>  ents=entBaseInfoService.selectList(entBaseInfoModel);
			if("1".equals(model.getFundSts())){
				if(ents==null||ents.size()==0){
					response.error("四级基金名称:" + model.getFundName() + "不存在"+"请通知管理员维护。");
					return response;
				}
				fundInfo.setEnteId(ents.get(0).getEnterpriseId());
			}

			String types="";
			//先查询三级基金或spv基金是否是系统中的基金
			ProjInfoModel projInfo1 = new ProjInfoModel();

			BdTFitRegulationSelfModel bd = new BdTFitRegulationSelfModel();
			ProjAppInfoModel projApp = new ProjAppInfoModel();
			ProjQuitApplModel projQuitApplModel = new ProjQuitApplModel();
			FundInfoModel threeFundInfo = new FundInfoModel();
			XjlTPaymentRequestModel xjlTPaymentRequestModel = new XjlTPaymentRequestModel();
			List<String> labels = new ArrayList<String>();
			projInfo1.setSpvType(model.getSpvType());
			bd.setIsCorr(model.getIsCorr());
			projInfo1.setProjName(model.getFundName());
			bd.setIsJs(model.getIsJs());
			fundInfo.setSpvType(model.getIsSpv());
			if(StringUtils.isNotEmpty(model.getCurrentAmount())){
				fundInfo.setCurrentAmount(Double.valueOf(model.getCurrentAmount()));
			}
			if(StringUtils.isNotEmpty(model.getInveCurrentAmount())){
				projApp.setInveCurrentAmount(Double.valueOf(Double.valueOf(model.getInveCurrentAmount())));
				threeFundInfo.setRaiseAmount(Double.valueOf(model.getInveCurrentAmount()));
			}
			if(StringUtils.isNotEmpty(model.getInveRaiseAmount())){
				projApp.setInveRaiseAmount(Double.valueOf(Double.valueOf(model.getInveRaiseAmount())));
			}
			if(StringUtils.isNotEmpty(model.getCurrentAmount())){
				fundInfo.setCurrentAmount(Double.valueOf(model.getCurrentAmount()));
			}
			if((StringUtils.isNotEmpty(model.getRaiseAmount()))){
				fundInfo.setRaiseAmount(Double.valueOf(model.getRaiseAmount()));
			}
			if(StringUtils.isNotEmpty(model.getPlanAmount())){
				fundInfo.setPlanAmount(Double.valueOf(model.getPlanAmount()));
			}
			if(StringUtils.isNotEmpty(model.getActualPayAmount())){
				xjlTPaymentRequestModel.setActualPayAmount(Double.valueOf(model.getActualPayAmount()));
			}
			if(StringUtils.isNotEmpty(model.getRevenue())){
				projQuitApplModel.setRevenue(Double.valueOf(model.getRevenue()));
			}
			fundInfo.setSocialCode(model.getSocialCode());
			fundInfo.setFundStruct(model.getFundStruct());
			fundInfo.setFundSts(model.getFundSts());
			if (StringUtils.isNotEmpty(model.getIsRecord())) {
				if("已备案".equals(model.getIsRecord())){
					fundInfo.setIsRecord("2");
				}
				if("未备案".equals(model.getIsRecord())){
					fundInfo.setIsRecord("0");
				}
			}
			fundInfo.setRecordCode(model.getRecordCode());
			projApp.setIsDecisionPass(model.getIsDecisionPass());
			projQuitApplModel.setQuitType(model.getQuitType());
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/M/d");
				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/d");
				if(StringUtils.isNotEmpty(model.getQuitDt())){
					if(model.getQuitDt().length()==8){
						projQuitApplModel.setQuitDt(formatter1.parse(model.getDecisionDate()));
					}
					if(model.getQuitDt().length()==9){
						projQuitApplModel.setQuitDt(formatter2.parse(model.getDecisionDate()));
					}
					if(model.getQuitDt().length()==10){
						projQuitApplModel.setQuitDt(formatter.parse(model.getDecisionDate()));
					}
				}


				if(StringUtils.isNotEmpty(model.getDecisionDate())){
					if(model.getDecisionDate().length()==8){
						projApp.setDecisionDate(formatter1.parse(model.getDecisionDate()));
					}
					if(model.getDecisionDate().length()==9){
						projApp.setDecisionDate(formatter2.parse(model.getDecisionDate()));
					}
					if(model.getDecisionDate().length()==10){
						projApp.setDecisionDate(formatter.parse(model.getDecisionDate()));
					}
				}

				if(StringUtils.isNotEmpty(model.getActualPayDate())){
					if(model.getActualPayDate().length()==8){
						xjlTPaymentRequestModel.setActualPayDate(formatter1.parse(model.getActualPayDate()));
					}
					if(model.getActualPayDate().length()==9){
						xjlTPaymentRequestModel.setActualPayDate(formatter2.parse(model.getActualPayDate()));
					}
					if(model.getActualPayDate().length()==10){
						xjlTPaymentRequestModel.setActualPayDate(formatter.parse(model.getActualPayDate()));
					}
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(StringUtils.isNotEmpty(model.getInveName())){
				List<FundInfoModel> list1=fundInfoService.selectByNameAndFundSrc(model.getInveName().replaceAll("\r|\n",""));
				if(list1!=null&&list1.size()>0){
					//判断三级基金投了四级基金，则是数据重复，则需要修改
					projInfo1.setInveId(list1.get(0).getFundId());
					projInfo1.setInveName(list1.get(0).getFundName());
					ProjInfoModel proj=new ProjInfoModel();
					proj.setProjName(model.getFundName());
					proj.setInveId(list1.get(0).getFundId());
					List<ProjInfoModel>  listP= projInfoService.selectList(proj);
					if(listP!=null&&listP.size()>0){
						//不插入只修改其中的信息
						String projId=listP.get(0).getProjId();
						//修改之前的信息
						projInfo1.setProjId(projId);
						//四级基金
						fundInfo.setFundId(listP.get(0).getProjObject());
						bd.setProjId(projId);
						projApp.setProjId(projId);
						projInfo1.setProjObjectName(model.getFundName());
						String appId = serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
						projQuitApplModel.setAppId(appId);
						projQuitApplModel.setIaId(projId);
						//数据重复修改修改
						types="3";

					}else{
						//基金和出资主体不重复
						FundInfoModel fundInfoVO=new FundInfoModel();
						fundInfoVO.setFundName(model.getFundName());
						List<FundInfoModel>	listFund=fundInfoService.selectList(fundInfoVO);
						if(listFund!=null&&listFund.size()>0){
							//四级基金存在出资主体不存在则需要插入企业的信息，不需要插入基金表
							if(!"5".equals(listFund.get(0).getFundSrc())){
								response.error("您填写的四级基金名称:" + model.getFundName() + "已存在");
								return response;
							}else{
								//四级基金存在，不需要插入四级，只需要插入基项目
								types="1";
								String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
								projInfo1.setProjId(projId);
								projInfo1.setProjStatus(model.getProjStatus());
								projInfo1.setProjType("5");
								//四级基金
								projInfo1.setProjObject(listFund.get(0).getFundId());
								//三级基金
								projInfo1.setInveId(list1.get(0).getFundId());
								projInfo1.setInveName(list1.get(0).getFundName());
								projApp.setProjId(projId);
								String appId=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
								projQuitApplModel.setAppId(appId);
								projQuitApplModel.setIaId(projId);
								bd.setProjId(projId);
								projApp.setProjId(projId);
								String PQ_ID=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
								xjlTPaymentRequestModel.setPqId(PQ_ID);
								xjlTPaymentRequestModel.setProjectOrFundId(projId);

							}

						}else{
							//可以全部插入
							types="2";
							String fundId=serialnoService.getSequence("EI.BD_T_FUND_INFO");
							fundInfo.setFundId(fundId);
							fundInfo.setFundSrc("5");
							fundInfo.setStatus("1");
							fundInfo.setRegName(model.getFundName());
							fundInfo.setFundName(model.getFundName());
							String projId=serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
							projInfo1.setProjId(projId);
							projInfo1.setProjStatus(model.getProjStatus());
							projInfo1.setProjObject(fundId);
							projInfo1.setProjType("5");
							projInfo1.setProjObjectName(model.getFundName());
							bd.setProjId(projId);
							projApp.setProjId(projId);
							String appId=serialnoService.getSequence("EI.TZ_T_PROJ_QUIT_APPL");
							projQuitApplModel.setIaId(projId);
							projQuitApplModel.setAppId(appId);
							bd.setProjId(projId);
							projApp.setProjId(projId);
							String PQ_ID=serialnoService.getSequence("EI.XJL_T_PAYMENT_REQUEST");
							xjlTPaymentRequestModel.setPqId(PQ_ID);
							xjlTPaymentRequestModel.setProjectOrFundId(projId);


						}
					}
				}else{
					response.error("三级基金名称:" + model.getInveName() + "不存在"+"请通知管理员维护。");
					return response;
				}

			}else{
				response.error("三级基金名称:" + model.getInveName() + "不存在"+"请通知管理员维护。");
				return response;
			}

			if ("1".equals(types)) {
				//四级基金存在了
				//插入立项表
				projInfo1.setCreateBy(String.valueOf(userModel.getId()));
				projInfo1.setCreateDt(new Date());
				projInfo1.setUpdateDt(new Date());
				projInfo1.setUpdateBy(String.valueOf(userModel.getId()));
				String projNo = getprojNo(projInfo1.getInveId(), projInfo1.getProjType());
				projInfo1.setProjNo(projNo);
				projInfo1.setProjName(model.getFundName());
				projInfo1.setGroupId(1L);
				projInfoService.insert(projInfo1);

				projApp.setCreateBy(String.valueOf(userModel.getId()));
				projApp.setCreateDt(new Date());
				projApp.setUpdateDt(new Date());
				projApp.setUpdateBy(String.valueOf(userModel.getId()));
				projApp.setProjId(projInfo1.getProjId());
				projAppInfoService.insert(projApp);

				xjlTPaymentRequestModel.setCreateBy(String.valueOf(userModel.getId()));
				xjlTPaymentRequestModel.setCreateDt(new Date());
				xjlTPaymentRequestModel.setUpdateDt(new Date());
				xjlTPaymentRequestModel.setUpdateBy(String.valueOf(userModel.getId()));
				xjlTPaymentRequestModel.setProjectOrFundId(projInfo1.getProjId());
				xjlTPaymentRequestModel.setStatus("0");
				xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);

				projQuitApplModel.setCreateBy(String.valueOf(userModel.getId()));
				projQuitApplModel.setCreateDt(new Date());
				projQuitApplModel.setUpdateDt(new Date());
				projQuitApplModel.setUpdateBy(String.valueOf(userModel.getId()));
				projQuitApplModel.setIaId(projInfo1.getProjId());
				projQuitApplService.insert(projQuitApplModel);

				bd.setCreateBy(String.valueOf(userModel.getId()));
				bd.setCreateDt(new Date());
				bd.setUpdateDt(new Date());
				bd.setUpdateBy(String.valueOf(userModel.getId()));
				bd.setProjId(projInfo1.getProjId());
				bdTFitRegulationSelfService.insert(bd);

				fundInfo.setUpdateDt(new Date());
				fundInfo.setUpdateBy(String.valueOf(userModel.getId()));
				fundInfoService.update(fundInfo);

				threeFundInfo.setCreateBy(String.valueOf(userModel.getId()));
				threeFundInfo.setCreateDt(new Date());
				threeFundInfo.setUpdateDt(new Date());
				threeFundInfo.setUpdateBy(String.valueOf(userModel.getId()));
				fundInfoService.update(threeFundInfo);

			} else if ("2".equals(types)) {
				//插入立项表
				projInfo1.setCreateBy(String.valueOf(userModel.getId()));
				projInfo1.setCreateDt(new Date());
				projInfo1.setUpdateDt(new Date());
				projInfo1.setUpdateBy(String.valueOf(userModel.getId()));
				projInfo1.setStatus("1");
				String projNo = getprojNo(projInfo1.getInveId(), projInfo1.getProjType());
				projInfo1.setProjNo(projNo);
				projInfo1.setGroupId(1L);
				projInfoService.insert(projInfo1);

				projApp.setCreateBy(String.valueOf(userModel.getId()));
				projApp.setCreateDt(new Date());
				projApp.setUpdateDt(new Date());
				projApp.setUpdateBy(String.valueOf(userModel.getId()));
				projAppInfoService.insert(projApp);

				xjlTPaymentRequestModel.setCreateBy(String.valueOf(userModel.getId()));
				xjlTPaymentRequestModel.setCreateDt(new Date());
				xjlTPaymentRequestModel.setUpdateDt(new Date());
				xjlTPaymentRequestModel.setUpdateBy(String.valueOf(userModel.getId()));
				xjlTPaymentRequestModel.setStatus("0");
				xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);

				projQuitApplModel.setCreateBy(String.valueOf(userModel.getId()));
				projQuitApplModel.setCreateDt(new Date());
				projQuitApplModel.setUpdateDt(new Date());
				projQuitApplModel.setUpdateBy(String.valueOf(userModel.getId()));
				projQuitApplModel.setAfterFunderId(projApp.getProjId());
				projQuitApplService.insert(projQuitApplModel);

				bd.setCreateBy(String.valueOf(userModel.getId()));
				bd.setCreateDt(new Date());
				bd.setUpdateDt(new Date());
				bd.setUpdateBy(String.valueOf(userModel.getId()));
				bd.setProjId(projApp.getProjId());
				bdTFitRegulationSelfService.insert(bd);

				fundInfo.setCreateBy(String.valueOf(userModel.getId()));
				fundInfo.setCreateDt(new Date());
				fundInfo.setUpdateDt(new Date());
				fundInfo.setUpdateBy(String.valueOf(userModel.getId()));
				fundInfo.setFundCode(projNo);
				fundInfo.setGroupId(1L);
				fundInfoService.insert(fundInfo);

				threeFundInfo.setCreateBy(String.valueOf(userModel.getId()));
				threeFundInfo.setCreateDt(new Date());
				threeFundInfo.setUpdateDt(new Date());
				threeFundInfo.setUpdateBy(String.valueOf(userModel.getId()));
				fundInfoService.update(threeFundInfo);

			} else if ("3".equals(types)) {
				projInfo1.setUpdateDt(new Date());
				projInfo1.setUpdateBy(String.valueOf(userModel.getId()));
				projInfoService.update(projInfo1);
				projApp.setUpdateDt(new Date());
				projApp.setUpdateBy(String.valueOf(userModel.getId()));
				projAppInfoService.update(projApp);

				xjlTPaymentRequestModel.setUpdateDt(new Date());
				xjlTPaymentRequestModel.setUpdateBy(String.valueOf(userModel.getId()));
				XjlTPaymentRequestModel xl = new XjlTPaymentRequestModel();
				xl.setInvestorId(projInfo1.getInveId());
				xl.setProjectOrFundId(projInfo1.getProjId());
				List<XjlTPaymentRequestModel> listXjl = xjlTPaymentRequestService.selectList(xl);
				if (listXjl != null && listXjl.size() > 0) {
					xjlTPaymentRequestModel.setPqId(listXjl.get(0).getPqId());
					xjlTPaymentRequestService.update(xjlTPaymentRequestModel);
				}
				projQuitApplModel.setUpdateDt(new Date());
				projQuitApplModel.setUpdateBy(String.valueOf(userModel.getId()));
				ProjQuitApplModel PQ = new ProjQuitApplModel();
				PQ.setIaId(projInfo1.getProjId());
				List<ProjQuitApplModel> list1 = projQuitApplService.selectList(PQ);
				if (list1 != null && list1.size() > 0) {
					projQuitApplModel.setAppId(list1.get(0).getAppId());
					projQuitApplService.update(projQuitApplModel);
				}

				bd.setUpdateDt(new Date());
				bd.setUpdateBy(String.valueOf(userModel.getId()));
				bdTFitRegulationSelfService.update(bd);

				fundInfo.setUpdateDt(new Date());
				fundInfo.setUpdateBy(String.valueOf(userModel.getId()));
				fundInfoService.update(fundInfo);

				threeFundInfo.setUpdateDt(new Date());
				threeFundInfo.setUpdateBy(String.valueOf(userModel.getId()));
				fundInfoService.update(threeFundInfo);
			}
			if("0".equals(model.getFundSts())){
				String enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
				EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
				entBaseInfoModel2.setEnterpriseId(enterpriseId);
				entBaseInfoModel2.setName(model.getFundName());
				entBaseInfoModel2.setEnName(model.getFundName());
				entBaseInfoModel2.setDeleteFlag("0");
				entBaseInfoModel2.setStatus("未注册");
				entBaseInfoModel2.setCreateBy(String.valueOf(userModel.getId()));
				entBaseInfoModel2.setCreateDt(new Date());
				entBaseInfoModel2.setUpdateBy(String.valueOf(userModel.getId()));
				entBaseInfoModel2.setUpdateDt(new Date());
				entBaseInfoService.insert(entBaseInfoModel2);
			}

		}

		return response;
	}


	@Override
	public <E> PageInfo<E> getJcProjList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getJcProjList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> getReportList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getReportList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectFundInvePage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFundInvePage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> selectSumInvePage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectSumInvePage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}



	@Override
	public List<ProjInfoModel> selectImportInveList(Map<String, Object> param) {
		return this.selectList("selectImportInveList",param);
	}

	@Override
	@Transactional
	public void delThreeProj(String projId, String appId) {
		if(StringUtils.isNotEmpty(projId)){
			String[] var1=projId.split(",");
			projInfoService.deleteBatch(var1);
		}
		if(StringUtils.isNotEmpty(appId)){
			String[] var2=appId.split(",");
			projQuitApplService.deleteBatch(var2);
		}

	}

	public String replaceBlank(String str) {
           String dest = "";
           if(str!=null){
			Pattern p= Pattern.compile("\\s*|\t|\r|\n");
			Matcher m =p.matcher(str);
			dest =m.replaceAll("");
           }
            return dest;
        }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void saveInfoE(ProjAppInfoModel appModel, ProjInfoModel model) throws Exception {
        //判断是否存在
		//查询企查查
		EntBaseInfoModel entBaseInfoModel=null;
		if(StringUtils.isNotEmpty(model.getProjObject())){
			entBaseInfoModel=	entBaseInfoService.selectById(model.getProjObject());
		}

		if(StringUtils.isNotEmpty(model.getProjObjectName())){
			//查询企业在标红是否存在
			EnteInfoModel modelS=new EnteInfoModel();
			modelS.setChName(model.getProjObjectName());
			List<EnteInfoModel> listE=  enteInfoService.selectList(modelS);
			String oldEnterpriseId="";
			if(listE!=null&&!listE.isEmpty()){
				oldEnterpriseId=listE.get(0).getEnterpriseId();
				modelS.setEnterpriseId(oldEnterpriseId);
				modelS.setEnteId(model.getProjObject());
				enteInfoService.update(modelS);
			}else{
				oldEnterpriseId=serialnoService.getSequence("EI.BD_T_ENTE_INFO");
				modelS.setEnterpriseId(oldEnterpriseId);
				modelS.setCreateBy(model.getCreateBy());
				modelS.setCreateDt(new Date());
				modelS.setUpdateDt(new Date());
				modelS.setUpdateBy(model.getCreateBy());
				modelS.setEnteId(model.getProjObject());
				modelS.setIsReg("2");
				modelS.setIsAbroad("1");
				modelS.setStatus("0");
			/*	if(entBaseInfoModel!=null){
					modelS.setRegAdd(entBaseInfoModel.getr);
				}*/
				enteInfoService.insert(modelS);
			}
			model.setOldEnterpriseId(oldEnterpriseId);
		}
		projInfoService.insert(model);
		appModel.setProjId(model.getProjId());
		appModel.setCreateBy(model.getCreateBy());
		appModel.setCreateDt(new Date());
		appModel.setUpdateDt(new Date());
		appModel.setUpdateBy(model.getCreateBy());
		projAppInfoService.insert(appModel);

	}

	@Override
	public void updateInfoE(ProjAppInfoModel appModel, ProjInfoModel model) throws Exception {
		projInfoService.update(model);
		appModel.setProjId(model.getProjId());
		appModel.setUpdateDt(new Date());
		appModel.setUpdateBy(model.getCreateBy());
		//查询是否存在

		ProjAppInfoModel appModelS=projAppInfoService.selectById(model.getProjId());
		if(appModelS!=null){
			projAppInfoService.update(appModel);
		}else{
			appModel.setUpdateDt(new Date());
			appModel.setUpdateBy(model.getCreateBy());
			appModel.setCreateDt(new Date());
			appModel.setCreateBy(model.getCreateBy());
			projAppInfoService.insert(appModel);
		}

	}

	@Override
	public BaseResponse ztImportBusinessManager(ZtBusinessManagerImportRequest request)throws Exception {
		BaseResponse response = new BaseResponse();
		List<DatNewFunctionExcel> list = request.getDateList();
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(request, userModel);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String projIdName="";
		for(DatNewFunctionExcel model:list){
			//先查出资主体是否存在
			FundInfoModel fundInfoModel=fundInfoService.selectById(model.getInveId());
			if(fundInfoModel==null){
				response.error("出资主体id不存在");
				return response;
			}
			ProjInfoModel projModel=new ProjInfoModel();

			//查询企业是否存在库中
			EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
			entBaseInfoModel.setName(model.getProjName().replaceAll("\r|\n",""));
			entBaseInfoModel.setDeleteFlag("0");
			List<EntBaseInfoModel>  ents=entBaseInfoService.selectList(entBaseInfoModel);
				if(ents==null||ents.size()==0){
					response.error("企业名称，请维护企查查"+model.getProjName());
					return response;
				}
			    projModel.setProjName(ents.get(0).getName());
				projModel.setProjObjectName(ents.get(0).getName());
			    projModel.setProjObject(ents.get(0).getEnterpriseId());
				projModel.setInveId(fundInfoModel.getFundId());
			    projModel.setStatus("1");

			    String type="";
			    ProjInfoModel projMo=new ProjInfoModel();
				projMo.setProjObjectName(ents.get(0).getName());
				projMo.setInveId(fundInfoModel.getFundId());
                projMo.setProjType("1");
				//查询项目是否存在
			    List<ProjInfoModel> listProjInfo=projInfoService.selectList(projMo);
			    if(listProjInfo!=null&& !listProjInfo.isEmpty()){
					projModel.setProjId(listProjInfo.get(0).getProjId());
					projModel.setProjName(projMo.getProjObjectName());
					type="update";
				}else{
			    	//为投过则需要插入
					String projId = serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
					projModel.setProjId(projId);
					projModel.setGroupId(3L);
					type="add";
					//查询基金管理人
					if(StringUtils.isNotEmpty(fundInfoModel.getMcId())){
						//查询基金管理人对赢得登录人
						AdminModel adminModel=adminService.selectById(fundInfoModel.getMcId());
						if(adminModel!=null&&StringUtils.isNotEmpty(adminModel.getAdminName())){
							AppUserModel userModelS=appUserService.selectByLoginName(adminModel.getAdminName());
							if(userModelS!=null){
								projModel.setCreateBy(String.valueOf(userModelS.getId()));
							}else{
								projModel.setCreateBy("init");
							}
						}else{
							projModel.setCreateBy("init");
						}
					}else{
						projModel.setCreateBy("init");
					}
				}
			    String typeStr="";
			    //查询 bd_t_ente_info
			EnteInfoModel enteInfol=new EnteInfoModel();
			if(StringUtils.isNotEmpty(model.getSetupDt())){
				enteInfol.setSetupDt(formatter.parse(model.getSetupDt()));
			}
			enteInfol.setIsDirectorCnt(model.getIsDirectorCnt());
			enteInfol.setIsSupervisorCnt(model.getIsSupervisorCnt());
			enteInfol.setRegAdd(model.getRegAdd());
			enteInfol.setIndustryName(model.getIndustryName());
			if(StringUtils.isNotEmpty(model.getRegAmount())){
				enteInfol.setRegAmount(Double.valueOf(model.getRegAmount()));
			}
			String legType="";
			LedgerMagModel ledgeMag=new LedgerMagModel();
			//查询出资是否存在
			ledgeMag.setLedgerType("1");
			ledgeMag.setProjId(projModel.getProjId());
			List<LedgerMagModel> listMag =ledgerMagService.selectListByOccDt(ledgeMag);
			if(listMag!=null&& !listMag.isEmpty()){
				legType="update";
				ledgeMag.setLedgerId(listMag.get(0).getLedgerId());
			}else{
				legType="add";
				String ledgerId = serialnoService.getSequence("EI.XJL_T_LEDGER_MAG");
				ledgeMag.setLedgerId(ledgerId);
				ledgeMag.setStatus("0");
				//出资时间
				if(StringUtils.isNotEmpty(model.getOccurDt())){
					ledgeMag.setOccurDt(formatter.parse(model.getOccurDt()));
				}
			}

           //退出时间 查询退出是否已存在
			LedgerMagModel ledgeMagInfo=new LedgerMagModel();
			//退出收益
			ledgeMagInfo.setLedgerType("7");
			ledgeMagInfo.setProjId(projModel.getProjId());
			List<LedgerMagModel> listLedgeList =ledgerMagService.selectListByOccDt(ledgeMagInfo);
			ledgeMagInfo.setExitType(model.getExitType());
			if(StringUtils.isNotEmpty(model.getOccurAmount1())){
				ledgeMagInfo.setOccurAmount(Double.valueOf(model.getOccurAmount1()));
			}
			if(StringUtils.isNotEmpty(model.getOccurAmount1())){
				ledgeMagInfo.setRmbInveAmount(Double.valueOf(model.getOccurAmount1()));
			}

			//想查询定去估值是否存在
			String projValuationType="";
			ProjValuationModel projValuationModel=new ProjValuationModel();
			projValuationModel.setIaId(projModel.getProjId());
			projValuationModel.setStatus("0");
			List<ProjValuationModel> listProjValuation=projValuationService.selectList(projValuationModel);
			if(listProjValuation!=null&& !listProjValuation.isEmpty()){
				projValuationModel.setPkId(listProjValuation.get(0).getPkId());
				projValuationType="update";
				projValuationModel.setUpdateDt(new Date());
				projValuationModel.setUpdateBy("init");
			}else{
				projValuationType="add";
				String pkId=serialnoService.getSequence("EI.TZ_T_PROJ_VALUATION");
				projValuationModel.setPkId(pkId);
				projValuationModel.setCreateDt(new Date());
				projValuationModel.setCreateBy("init");
				projValuationModel.setUpdateDt(new Date());
				projValuationModel.setUpdateBy("init");

			}
			if(StringUtils.isNotEmpty(model.getInveNewValue())){
				projValuationModel.setInveNewValue(Double.valueOf(model.getInveNewValue()));
			}
			if(StringUtils.isNotEmpty(model.getHoldShare())){
				projValuationModel.setHoldShare(Double.valueOf(model.getHoldShare()));
			}
			if(StringUtils.isNotEmpty(model.getEntePostValue())){
				projValuationModel.setEntePostValue(Double.valueOf(model.getEntePostValue()));
			}
			if("add".equals(projValuationType)){
				projValuationService.insert(projValuationModel);
			}else if("update".equals(projValuationType)){
				projValuationService.update(projValuationModel);
			}

			String legTypeS="";
			if(listLedgeList!=null&& !listLedgeList.isEmpty()){
				ledgeMagInfo.setLedgerId(listLedgeList.get(0).getLedgerId());
				ledgerMagService.update(ledgeMagInfo);

			}else{
				String ledgerId = serialnoService.getSequence("EI.XJL_T_LEDGER_MAG");
				ledgeMagInfo.setLedgerId(ledgerId);
				//退出时间
				if(StringUtils.isNotEmpty(model.getOccurDt1())){
					ledgeMagInfo.setOccurDt(formatter.parse(model.getOccurDt1()));
				}
				ledgeMagInfo.setStatus("0");
				ledgeMagInfo.setCreateDt(new Date());
				ledgeMagInfo.setCreateBy("init");
				ledgeMagInfo.setUpdateDt(new Date());
				ledgeMagInfo.setUpdateBy("init");
				ledgeMagInfo.setOccurCurr("1");
				ledgerMagService.insert(ledgeMagInfo);
			}

			//退出时间 查询退出是否已存在
			LedgerMagModel ledgeMagInfo1=new LedgerMagModel();
			//退出本金
			ledgeMagInfo1.setLedgerType("6");
			ledgeMagInfo1.setProjId(projModel.getProjId());
			ledgeMagInfo1.setStatus("0");
			List<LedgerMagModel> listLedgeList1 =ledgerMagService.selectListByOccDt(ledgeMagInfo1);
			ledgeMagInfo1.setExitType(model.getExitType());
			if(StringUtils.isNotEmpty(model.getOccurAmount())){
				ledgeMagInfo1.setOccurAmount(Double.valueOf(model.getOccurAmount()));
			}
			if(StringUtils.isNotEmpty(model.getOccurAmount())){
				ledgeMagInfo1.setRmbInveAmount(Double.valueOf(model.getOccurAmount()));
			}

			if(listLedgeList1!=null&& !listLedgeList1.isEmpty()){
				ledgeMagInfo1.setLedgerId(listLedgeList1.get(0).getLedgerId());
				ledgerMagService.update(ledgeMagInfo1);

			}else{
				String ledgerId = serialnoService.getSequence("EI.XJL_T_LEDGER_MAG");
				ledgeMagInfo1.setLedgerId(ledgerId);
				//退出时间
				if(StringUtils.isNotEmpty(model.getOccurDt1())){
					ledgeMagInfo1.setOccurDt(formatter.parse(model.getOccurDt1()));
				}
				ledgeMagInfo1.setUpdateDt(new Date());
				ledgeMagInfo1.setUpdateBy("init");
				ledgeMagInfo1.setCreateDt(new Date());
				ledgeMagInfo1.setCreateBy("init");
				ledgerMagService.insert(ledgeMagInfo1);
			}


			EnteInfoModel enteInfoModel=new EnteInfoModel();
			enteInfoModel.setChName(ents.get(0).getName());
			enteInfoModel.setStatus("0");
			List<EnteInfoModel> enteList=  enteInfoService.selectList(enteInfoModel);
			if(enteList!=null&&!enteList.isEmpty()){
				typeStr="update";
				enteInfol.setEnterpriseId(enteList.get(0).getEnterpriseId());
				projModel.setOldEnterpriseId(enteList.get(0).getEnterpriseId());
			}else{
				typeStr="add";
				String oldEnterpriseId=serialnoService.getSequence("EI.BD_T_ENTE_INFO");
				enteInfol.setChName(ents.get(0).getName());
				enteInfol.setEnterpriseId(oldEnterpriseId);
				enteInfol.setStatus("0");
				projModel.setOldEnterpriseId(oldEnterpriseId);
			}
			//查询所属行业
			if(StringUtils.isNotEmpty(model.getIndustryName())){
				CommTCodeModel commTCodeModel=new CommTCodeModel();
				commTCodeModel.setCodeName(model.getIndustryName());
				commTCodeModel.setParentId("9004");
				List<CommTCodeModel>  listC=commTCodeService.selectList(commTCodeModel);
				if(listC!=null && !listC.isEmpty()){
					enteInfol.setIndustry(listC.get(0).getCodeValue());

				}
			}
			//查询企业标签否存在
			//是否属于民营企业
			if("1".equals(model.getLabel1())){
				EntLabelModel entLabelModel=new EntLabelModel();
				entLabelModel.setEntId(projModel.getProjObject());
				entLabelModel.setLabelId("16");
				List<EntLabelModel>	listEnt=entLabelService.selectList(entLabelModel);
				if(null==listEnt || listEnt.size()==0){
					entLabelModel.setUpdateBy("init");
					entLabelModel.setUpdateDt(new Date());
					entLabelModel.setCreateBy("init");
					entLabelModel.setCreateDt(new Date());
					entLabelService.insert(entLabelModel);
				}
			}
           //是否属于科技
			if("1".equals(model.getLabel2())){
				EntLabelModel entLabelModel=new EntLabelModel();
				entLabelModel.setEntId(projModel.getProjObject());
				entLabelModel.setLabelId("15");
				List<EntLabelModel>	listEnt=entLabelService.selectList(entLabelModel);
				if(null==listEnt || listEnt.size()==0){
					entLabelModel.setUpdateBy("init");
					entLabelModel.setUpdateDt(new Date());
					entLabelModel.setCreateBy("init");
					entLabelModel.setCreateDt(new Date());
					entLabelService.insert(entLabelModel);
				}
			}
			//是否属于战略新兴
			if("1".equals(model.getLabel3())){
				EntLabelModel entLabelModel=new EntLabelModel();
				entLabelModel.setEntId(projModel.getProjObject());
				entLabelModel.setLabelId("4");
				List<EntLabelModel>	listEnt=entLabelService.selectList(entLabelModel);
				if(null==listEnt || listEnt.size()==0){
					entLabelModel.setUpdateBy("init");
					entLabelModel.setUpdateDt(new Date());
					entLabelModel.setCreateBy("init");
					entLabelModel.setCreateDt(new Date());
					entLabelService.insert(entLabelModel);
				}
			}
			//防止传入汉子等问题
			if(StringUtils.isNotEmpty(model.getIsProvince())){
				if("1".equals(model.getIsProvince())){
					enteInfol.setIsProvince("1");
				}else if("0".equals(model.getIsProvince())){
					enteInfol.setIsProvince("0");
				}
			}
			if("2".equals(model.getIsPublicComp())){
				enteInfol.setIsApplyIpo("1");
				enteInfol.setIsPublicComp(model.getIsPublicComp());
			}else {
				enteInfol.setIsPublicComp(model.getIsPublicComp());
			}
			//enteInfol.setPhase(model.getPhase());
			enteInfol.setHlightsRemark(model.getHlightsRemark());

			String typeApp="";
			//查询 projinfoapp 是否存在
			ProjAppInfoModel appModel=projAppInfoService.selectById(projModel.getProjId());
			ProjAppInfoModel appModelS=new ProjAppInfoModel();
			if(StringUtils.isNotEmpty(model.getIntendedAmount())){
				appModelS.setRmbIntendedAmount(Double.valueOf(model.getIntendedAmount()));
			}

			if(StringUtils.isNotEmpty(model.getPerShare())){
				appModelS.setPerShare(Double.valueOf(model.getPerShare()));
			}
			appModelS.setProjId(projModel.getProjId());
			appModelS.setFinaRounds(model.getFinaRounds());
			if(StringUtils.isNotEmpty(model.getPostMoney())){
				appModelS.setPostMoney(Double.valueOf(model.getPostMoney()));
				appModelS.setPostCurrency("1");
			}

			if(appModel!=null){
				typeApp="update";
			}else{
				typeApp="add";
			}
       //gf_t_fund_ente
           //查询本年度是否存在
			FundEnteModel enteModel=new FundEnteModel();
			if(StringUtils.isNotEmpty(model.getYear())){
				enteModel.setYear(model.getYear());
			}
			enteModel.setEnteId(projModel.getProjObject());
			if(model.getStaffNum()!=null&& model.getRdStaffNum()!=null){
				List<FundEnteModel> listEnteM=fundEnteService.selectList(enteModel);
				if(listEnteM!=null&&!listEnteM.isEmpty()){
					enteModel.setPkId(listEnteM.get(0).getPkId());
				}else{
					enteModel.setPkId(UUID.randomUUID().toString());
				}
				enteModel.setStaffNum(model.getStaffNum());
				enteModel.setRdStaffNum(model.getRdStaffNum());
				enteModel.setTotalAssets(model.getTotalAssets());
				enteModel.setTotalLiabilities(model.getTotalLiabilities());
				enteModel.setOwnerEquity(model.getOwnerEquity());
				enteModel.setTotalIncome(model.getTotalIncome());
				enteModel.setTotalProfit(model.getTotalProfit());
				enteModel.setNetProfit(model.getNetProfit());
				enteModel.setRdFee(model.getRdFee());
				enteModel.setPayFee(model.getPayFee());
				if(listEnteM==null||listEnteM.size()==0){
					enteModel.setUpdateBy("init");
					enteModel.setUpdateDt(new Date());
					enteModel.setCreateBy("init");
					enteModel.setCreateDt(new Date());
					enteModel.setStatus("0");
					enteModel.setEnteName(ents.get(0).getName());
					fundEnteService.insert(enteModel);
				}else{
					enteModel.setUpdateBy("init");
					enteModel.setUpdateDt(new Date());
					fundEnteService.update(enteModel);
				}
			}
			//获取项目编号
			String projNo="";
			String orderNo="";
			if(fundInfoModel!=null){
				//查询基金投了多少项目
				Integer num= fundInfoService.getSumProj(fundInfoModel.getFundId());
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
				String fundCode=fundInfoModel.getFundCode()==null?"":fundInfoModel.getFundCode();
				projNo=fundCode+year+orderNo;

			}
            if("update".equals(type)){
				projModel.setUpdateDt(new Date());
				projModel.setOldEnterpriseId(enteInfol.getEnterpriseId());
				projModel.setProjNo(projNo);
				projModel.setInveName(fundInfoModel.getFundName());
				projModel.setProjStatus("1");
				projModel.setProjType("1");
                projInfoService.update(projModel);
				projIdName=projIdName+"update"+projModel.getProjId()+";";
				response.setMsg(projIdName);
			}else if("add".equals(type)){
				projModel.setProjNo(projNo);
				projModel.setInveName(fundInfoModel.getFundName());
				projModel.setProjStatus("1");
				projModel.setProjType("1");
				projModel.setUpdateBy("init");
				projModel.setUpdateDt(new Date());
				projModel.setCreateDt(new Date());
				projIdName=projIdName+"add"+projModel.getProjId()+";";
				response.setMsg(projIdName);
				System.out.print("***********************add************************"+projModel.getProjId());
				projInfoService.insert(projModel);
			}

			if("update".equals(legType)){
				ledgerMagService.update(ledgeMag);
			}else if("add".equals(legType)){
				ledgeMag.setUpdateBy("init");
				ledgeMag.setUpdateDt(new Date());
				ledgeMag.setCreateBy("init");
				ledgeMag.setCreateDt(new Date());
				ledgerMagService.insert(ledgeMag);
			}

			if("update".equals(typeApp)){
				appModelS.setInveType("1");
				projAppInfoService.update(appModelS);
			}else if("add".equals(typeApp)){
				appModelS.setUpdateBy("init");
				appModelS.setUpdateDt(new Date());
				appModelS.setCreateBy("init");
				appModelS.setCreateDt(new Date());
				appModelS.setInveType("1");
				projAppInfoService.insert(appModelS);
			}
			if("1".equals(model.getPhase())){
				enteInfol.setPhase("01");
			}else if("2".equals(model.getPhase())){
				enteInfol.setPhase("03");
			}else if("3".equals(model.getPhase())){
				enteInfol.setPhase("04");
			}else if("4".equals(model.getPhase())){
				enteInfol.setPhase("04");
			}
			if("update".equals(typeStr)){
				enteInfol.setEnteId(projModel.getProjObject());
				enteInfoService.update(enteInfol);
			}else if("add".equals(typeStr)){
				enteInfol.setEnteId(projModel.getProjObject());
				enteInfol.setUpdateBy("init");
				enteInfol.setUpdateDt(new Date());
				enteInfol.setCreateBy("init");
				enteInfol.setCreateDt(new Date());
				enteInfoService.insert(enteInfol);
			}



		}
		//response.error("三级基金名称:" + model.getInveName() + "不存在"+"请通知管理员维护。");
		return response;
	}

	@Override
	public <E> PageInfo<E> selectListInfoPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListInfoPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<ProjInfoModel> selectNumList(ProjInfoModel model) {
		return this.selectList("selectNumList",model);
	}

	@Override
	public List<ProjInfoModel> selectAllProjInfo(SearchCondition var1) {
		return this.selectList("selectAllProjInfo",var1);
	}




	@Override
	public BaseResponse hyImportBusinessManager(HyBusinessManagerImportRequest request) throws Exception {
		BaseResponse response = new BaseResponse();
		List<HyNewFunctionExcel> list = request.getDateList();
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(request, userModel);
		for(HyNewFunctionExcel model:list) {
			//查询出资主体投资企业是否存在
			/*if(StringUtils.isEmpty(model.getInveId())){
				response.error("model.getInveId():不能为空");
				return response;
			}
			if(StringUtils.isEmpty(model.getProjName())){
				response.error("model.getProjName():不能为空");
				return response;
			}
			ProjInfoModel modeProj=new ProjInfoModel();
			modeProj.setInveId(model.getInveId());
			modeProj.setProjName(model.getProjName());
			modeProj.setProjType("1");
			List<ProjInfoModel> projlist=projInfoService.selectList(modeProj);
			if(projlist!=null && !projlist.isEmpty()){
				String projId=projlist.get(0).getProjId();
				if(StringUtils.isNotEmpty(projId)){
					ProjAppInfoModel projAppInfoModel=new ProjAppInfoModel();
					projAppInfoModel.setProjId(projId);
					if(StringUtils.isNotEmpty(model.getPerShare())){
						projAppInfoModel.setPerShare(Double.valueOf(model.getPerShare()));
					}
					projAppInfoService.update(projAppInfoModel);
				}

			}*/



			//查询企业对应的id
			if(StringUtils.isNotEmpty(model.getProjName())&& StringUtils.isNotEmpty(model.getIndustryName())){
				EnteInfoModel enteInfoModel=new EnteInfoModel();
				enteInfoModel.setChName(model.getProjName());
			    List<EnteInfoModel>  listS=	enteInfoService.selectList(enteInfoModel);
			    if(listS!=null&& !listS.isEmpty()){
					enteInfoModel.setEnterpriseId(listS.get(0).getEnterpriseId());
					if(StringUtils.isNotEmpty(model.getIndustryName())){
						CommTCodeModel commTCodeModel=new CommTCodeModel();
						commTCodeModel.setCodeName(model.getIndustryName());
						commTCodeModel.setParentId("9004");
						List<CommTCodeModel>  listC=commTCodeService.selectList(commTCodeModel);
						if(listC!=null && !listC.isEmpty()){
							enteInfoModel.setIndustry(listC.get(0).getCodeValue());
						}
						enteInfoModel.setIndustryName(model.getIndustryName());
					}
					if(StringUtils.isNotEmpty(enteInfoModel.getEnterpriseId())){
						enteInfoService.update(enteInfoModel);
					}
				}
			}
            //查询企业是否存在库中
      /*      EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
            entBaseInfoModel.setName(model.getProjName().replaceAll("\r|\n",""));
            entBaseInfoModel.setDeleteFlag("0");
            List<EntBaseInfoModel>  ents=entBaseInfoService.selectList(entBaseInfoModel);
            if(ents==null||ents.size()==0){
                response.error("企业名称，请维护企查查"+model.getProjName());
                return response;
            }*/
  /*         FundEnteModel enteModel=new FundEnteModel();
            response.setMsg("model.getInveTime()"+model.getProjName());
			if(StringUtils.isNotEmpty(model.getInveTime())){
				    String year=String.valueOf(Integer.parseInt(model.getInveTime())-1);
					enteModel.setYear(year);
                response.setMsg("model.getInveTime()"+model.getProjName()+";year"+year);
			}else{
				if(StringUtils.isNotEmpty(model.getYear())){
					enteModel.setYear(model.getYear());
				}
			}*/

   /*         enteModel.setEnteId(ents.get(0).getEnterpriseId());
            if(StringUtils.isNotEmpty(enteModel.getYear())){
                List<FundEnteModel> listEnteM=fundEnteService.selectList(enteModel);
                if(listEnteM!=null&&!listEnteM.isEmpty()){
                    enteModel.setPkId(listEnteM.get(0).getPkId());
                }else{
                    enteModel.setPkId(UUID.randomUUID().toString());
                }
                enteModel.setStaffNum(model.getStaffNum());
                enteModel.setRdStaffNum(model.getRdStaffNum());
                enteModel.setTotalAssets(model.getTotalAssets());
                enteModel.setTotalLiabilities(model.getTotalLiabilities());
                enteModel.setOwnerEquity(model.getOwnerEquity());
                enteModel.setTotalIncome(model.getTotalIncome());
                enteModel.setTotalProfit(model.getTotalProfit());
                enteModel.setNetProfit(model.getNetProfit());
                enteModel.setRdFee(model.getRdFee());
                enteModel.setPayFee(model.getPayFee());
                if(listEnteM==null||listEnteM.size()==0){
                    enteModel.setUpdateBy("init");
                    enteModel.setUpdateDt(new Date());
                    enteModel.setCreateBy("init");
                    enteModel.setCreateDt(new Date());
                    enteModel.setStatus("0");
                    enteModel.setEnteName(ents.get(0).getName());
                    fundEnteService.insert(enteModel);
                }else{
                    enteModel.setUpdateBy("init");
                    enteModel.setUpdateDt(new Date());
                    fundEnteService.update(enteModel);
                }
            }*/

		}

		return response;
	}

	@Override
	public <E> PageInfo<E> selectAllProjList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectAllProjList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
}