package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.ProjAppInfoSnapshotDModel;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.ProjAppInfoSnapshotDService;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.ProjInfoService;
import com.ppmg.ei.service.ProjMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.service.ProjAppInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Component("projAppInfoService")
@Service(interfaceClass = ProjAppInfoService.class)
public class ProjAppInfoServiceImpl extends BaseServiceImpl<ProjAppInfoModel> implements ProjAppInfoService {

	public ProjAppInfoServiceImpl(){
		this.setNamespace("ProjAppInfo");
	}
	@Resource
	private ProjInfoService projInfoService;

	@Resource
	private FundInfoService fundInfoService;

	@Resource
	private ProjAppInfoService projAppInfoService;

	@Resource
	private ProjMemberService projMemberService;


    @Resource
    private ProjAppInfoSnapshotDService projAppInfoSnapshotDService;

    @Override
	public <E> PageInfo<E> selectallListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectallListPageMapper", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public ProjAppInfoModel getgetTzProjAppInfo(String projId) {
		return (ProjAppInfoModel) this.selectOne("getgetTzProjAppInfoMapper",projId);
	}

	@Override
	public ProjAppInfoMemberModel getOneDetails(String projId) {
		return (ProjAppInfoMemberModel) this.selectOne("getOneDetailsMapper",projId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertAll(ProjAppInfoMemberModel projAppInfoMemberModel) {
		//插入TZ_T_PROJ_INFO主表
		String projId = projAppInfoMemberModel.getProjId();
		String fundId = projAppInfoMemberModel.getFundId();
		ProjInfoModel proInfo = new ProjInfoModel();
		//主键id
		proInfo.setProjId(projId);
		/** 项目名称 */
		proInfo.setProjName(projAppInfoMemberModel.getProjName());
		/** 项目来源 */
		proInfo.setProjSrc(projAppInfoMemberModel.getProjSrc());
		/** 项目来源說明 */
		proInfo.setSrcDesc(projAppInfoMemberModel.getSrcDesc());
		/** 项目概况 */
		proInfo.setProjOverview(projAppInfoMemberModel.getProjOverview());
		//状态
		proInfo.setStatus(projAppInfoMemberModel.getStatus());
		/**附件说明,TZ_T_PROJ_INFO**/
		proInfo.setFileDesc(projAppInfoMemberModel.getFileDesc());
		/**附件，TZ_T_PROJ_INFO**/
		proInfo.setInveFile(projAppInfoMemberModel.getInveFile());
		//proInfo.setInveId(fundId);//TZ_T_PROJ_INFO的INVE_ID与BD_T_FUND_INFO的FUND_ID
		proInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		if("1".equals(projAppInfoMemberModel.getTag())){
			//草稿
			proInfo.setStatus("0");
		}else if("0".equals(projAppInfoMemberModel.getTag())){
			//流程审批中
			proInfo.setStatus("1");
		}
		projInfoService.updateprojInfo(proInfo);
		/*//查询主表
		ProjInfoModel proInfoSelect = projInfoService.selectById(projId);
		if(proInfoSelect !=null){//主表更新
			projInfoService.updateprojInfo(proInfo);
		}else{//主表增加
			projInfoService.insertprojInfo(proInfo);
		}*/
		//通过TZ_T_PROJ_INFO的INVE_ID与BD_T_FUND_INFO的FUND_ID关联
		//插入BD_T_FUND_INFO基金认缴总规模
		FundInfoModel fundInfo = new FundInfoModel();
		fundInfo.setFundId(fundId);//主键
		/** 基金认缴总规模（万元） BD_T_FUND_INFO*/
		fundInfo.setCurrentAmount(projAppInfoMemberModel.getCurrentAmount());
		fundInfo.setCurrentCurrency(projAppInfoMemberModel.getCurrentCurrency());
		/** 基金认缴总规模（万元）币种 BD_T_FUND_INFO*/
		fundInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		//创建人
		fundInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		fundInfoService.updateFundInfo(fundInfo);
		/*FundInfoModel f = fundInfoService.selectById(fundId);
		if(f!=null){//更新
			fundInfoService.updateFundInfo(f);
		}else{//增加
			fundInfoService.insertFundInfo(ff);
		}*/
		//插入TZ_T_PROJ_APP_INFO
		ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
		proAppInfo.setProjId(projId);
		proAppInfo.setMajorMatter(projAppInfoMemberModel.getMajorMatter());
		/**决策事项**/
		proAppInfo.setInveReason(projAppInfoMemberModel.getInveReason());
		/**投资理由**/
		proAppInfo.setFocusInfo(projAppInfoMemberModel.getFocusInfo());
		/**尽职调查重点**/
		proAppInfo.setInveCurrentAmount(projAppInfoMemberModel.getInveCurrentAmount());
		//其中：本基金认缴规模
		proAppInfo.setApplyAmount(projAppInfoMemberModel.getApplyAmount());
		//申请母基金额度
		proAppInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		//创建人
		proAppInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		ProjAppInfoModel per = projAppInfoService.getgetTzProjAppInfo(projId);
		if (per != null) {
			projAppInfoService.updateAppInfo(proAppInfo);
		}else{
			projAppInfoService.insertAppInfo(proAppInfo);
		}
		//插入TZ_T_PROJ_MEMBER,人員
		List<ProjMemberModel> list = projAppInfoMemberModel.getProjMemberModel();
		if (list != null && list.size() > 0) {
			for (ProjMemberModel pm : list) {
				String Pk = UUID.randomUUID().toString();
				pm.setPkId(Pk);
				pm.setProjId(projId);
				pm.setCreateBy(projAppInfoMemberModel.getUserId());
				//创建人
				pm.setUpdateBy(projAppInfoMemberModel.getUserId());
				//更新人
				pm.setCreateDt(new Date());
				pm.setUpdateDt(new Date());
				projMemberService.insert(pm);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAll(ProjAppInfoMemberModel projAppInfoMemberModel) {
		String tag = projAppInfoMemberModel.getTag();
		String projId = projAppInfoMemberModel.getProjId();
		//更新TZ_T_PROJ_INFO主表
		ProjInfoModel proInfo = new ProjInfoModel();
		if(tag != null && "0".equals(tag)){
			//提交流程才去更改项目状态
			proInfo.setProjStatus(projAppInfoMemberModel.getStatus());
			//状态
		}
		proInfo.setProjId(projId);
		//主键id
		proInfo.setProjName(projAppInfoMemberModel.getProjName());
		/** 项目名称 */
		proInfo.setProjSrc(projAppInfoMemberModel.getProjSrc());
		/** 项目来源 */
		proInfo.setSrcDesc(projAppInfoMemberModel.getSrcDesc());
		/** 项目来源說明 */
		proInfo.setProjOverview(projAppInfoMemberModel.getProjOverview());
		/** 项目概况 */
		proInfo.setProcessInstId(projAppInfoMemberModel.getProcessInstId());
		//流程实例id

		//附件
		proInfo.setFileDesc(projAppInfoMemberModel.getFileDesc());
		/**附件说明,TZ_T_PROJ_INFO**/
		proInfo.setInveFile(projAppInfoMemberModel.getInveFile());
		/**附件，TZ_T_PROJ_INFO**/
		//proInfo.setInveId(fundId);//TZ_T_PROJ_INFO的INVE_ID与BD_T_FUND_INFO的FUND_ID
		proInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		/*if(projAppInfoMemberModel.getTag().equals("1")){
			proInfo.setStatus("0");//草稿
		}else if(projAppInfoMemberModel.getTag().equals("0")){
			proInfo.setStatus("1");//流程审批中
		}*/
		projInfoService.updateprojInfo(proInfo);
		//更新TZ_T_PROJ_APP_INFO
		ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
		proAppInfo.setProjId(projId);
		proAppInfo.setMajorMatter(projAppInfoMemberModel.getMajorMatter());
		/**决策事项**/
		proAppInfo.setInveReason(projAppInfoMemberModel.getInveReason());
		/**投资理由**/
		proAppInfo.setFocusInfo(projAppInfoMemberModel.getFocusInfo());
		/**尽职调查重点**/
		proAppInfo.setInveCurrentAmount(projAppInfoMemberModel.getInveCurrentAmount());
		//其中：本基金认缴规模
		proAppInfo.setApplyAmount(projAppInfoMemberModel.getApplyAmount());
		//申请母基金额度
		proAppInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		//创建人
		proAppInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		proAppInfo.setUpdateDt(new Date());
		//更新时间
		projAppInfoService.updateAppInfo(proAppInfo);

		//通过TZ_T_PROJ_INFO的INVE_ID与BD_T_FUND_INFO的FUND_ID关联
		//更新BD_T_FUND_INFO基金认缴总规模
		FundInfoModel ff = new FundInfoModel();
		//ff.setFundId(projAppInfoMemberModel.getInveId());
		ff.setFundId(projAppInfoMemberModel.getProjObject());
		ff.setCurrentAmount(projAppInfoMemberModel.getCurrentAmount());
		ff.setCurrentCurrency(projAppInfoMemberModel.getCurrentCurrency());
		ff.setShortName(projAppInfoMemberModel.getProjName());
		//项目简称
		fundInfoService.updateFundInfo(ff);
		//更新TZ_T_PROJ_MEMBER,人員
		List<ProjMemberModel> list = projAppInfoMemberModel.getProjMemberModel();
		if (list != null && list.size() > 0) {
			String projIds = projAppInfoMemberModel.getProjId();
			//删除
			projMemberService.deletcProjId(projId);
			//新增
			for (ProjMemberModel pm : list) {
				String Pk = UUID.randomUUID().toString();
				pm.setPkId(Pk);
				pm.setProjId(projIds);
				pm.setCreateBy(projAppInfoMemberModel.getUserId());
				//创建人
				pm.setUpdateBy(projAppInfoMemberModel.getUserId());
				//更新人
				pm.setCreateDt(new Date());
				pm.setUpdateDt(new Date());
				projMemberService.insert(pm);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAll(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			String projId = arr[i];
			//删除TZ_T_PROJ_INFO主表
			projInfoService.deleteById(projId);
			//删除TZ_T_PROJ_APP_INFO
			projAppInfoService.deleteAppInfo(projId);
			//删除TZ_T_PROJ_MEMBER
			projAppInfoService.deleteMember(projId);
			//通过TZ_T_PROJ_INFO的INVE_ID与BD_T_FUND_INFO的FUND_ID关联
			ProjInfoModel projInfoModel = projInfoService.selectById(projId);
			if(projInfoModel != null){
				String fundId = projInfoModel.getInveId();
				System.out.println(fundId);
				//BD_T_FUND_INFO基金认缴总规模
				fundInfoService.deleteById(fundId);
			}
		}
	}

	@Override
	public void insertAppInfo(ProjAppInfoModel projAppInfoModel) {
		List<ProjAppInfoModel> list1 = new ArrayList<>();
		list1.add(projAppInfoModel);
		this.insert("projAppInfoMapper",list1);
	}

	@Override
	public void updateAppInfo(ProjAppInfoModel projAppInfoModel) {
		this.update("updateAppInfoMapper",projAppInfoModel);
	}

	@Override
	public void deleteAppInfo(String projId) {
		this.delete("deleteAppInfoMapper",projId);
	}

	@Override
	public void deleteMember(String projId) {
		this.delete("deleteMemberMapper",projId);
	}

	//-------------------项目投管-------------------------
	@Override
	public ProjAppInfoMemberModel getOneProjectDetails(String projId) {
		ProjAppInfoMemberModel one = (ProjAppInfoMemberModel) this.selectOne("selectByIdProjectMapper",projId);
		return one;
	}

	@Override
	public void insertProjectAll(ProjAppInfoMemberModel projAppInfoMemberModel) {
		//更新TZ_T_PROJ_INFO主表
		ProjInfoModel proInfo = new ProjInfoModel();
		proInfo.setStatus(projAppInfoMemberModel.getStatus());
		proInfo.setProjId(projAppInfoMemberModel.getProjId());
		//主键
		proInfo.setInveName(projAppInfoMemberModel.getFundingBody());
		//出资主体名称
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		projInfoService.updateprojInfo(proInfo);
		//插入TZ_T_PROJ_APP_INFO表
		List<ProjAppInfoMemberModel> list = new ArrayList<>();
		projAppInfoMemberModel.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		projAppInfoMemberModel.setCreateBy(projAppInfoMemberModel.getUserId());
		//更新人
		list.add(projAppInfoMemberModel);
		this.insert("insertBatchProjectMapper",list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByInfo(ProjAppInfoModel projAppInfo, FundInfoModel fundModel, ProjAppInfoSnapshotDModel model,String projCodeS)  throws Exception{
		if(StringUtils.isNotEmpty(projAppInfo.getProjId())){
			projAppInfoService.update(projAppInfo);
			fundModel.setUpdateDt(new Date());
			fundModel.setUpdateBy(projAppInfo.getUpdateBy());
			fundInfoService.update(fundModel);
		}
		ProjAppInfoSnapshotDModel  appModel= projAppInfoSnapshotDService.selectById(projAppInfo.getProjId());
		model.setUpdateDt(new Date());
		model.setUpdateBy(projAppInfo.getUpdateBy());
		if(appModel==null){
			model.setCreateDt(new Date());
			model.setCreateBy(projAppInfo.getUpdateBy());
			projAppInfoSnapshotDService.insert(model);
		}else{
			projAppInfoSnapshotDService.update(model);
		}
		if(!"false".equals(projCodeS)){
			ProjInfoModel proj=new ProjInfoModel();
			proj.setProjNo(projCodeS);
			proj.setProjId(projAppInfo.getProjId());
			projInfoService.update(proj);
		}

	}
	@Override
	public void updateProjectAll(ProjAppInfoMemberModel projAppInfoMemberModel) {
		//更新TZ_T_PROJ_INFO主表
		String tag = projAppInfoMemberModel.getTag();
		ProjInfoModel proInfo = new ProjInfoModel();
		if(tag != null && "0".equals(tag)){
			//提交流程才去更改项目状态
			proInfo.setProjStatus(projAppInfoMemberModel.getStatus());
			//流程实例id
			proInfo.setProcessInstId(projAppInfoMemberModel.getProcessInstId());
		}
		proInfo.setProjId(projAppInfoMemberModel.getProjId());
		//主键
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		proInfo.setInveId(projAppInfoMemberModel.getInveId());
		//出资主体id
		proInfo.setInveName(projAppInfoMemberModel.getFundingBody());
		//出资主体名称
        proInfo.setProjNo(projAppInfoMemberModel.getProjNo());
        //项目编号==出资主体编号+四位年+四位顺序*/
		projInfoService.updateprojInfo(proInfo);
		//更新TZ_T_PROJ_APP_INFO
		projAppInfoMemberModel.setUpdateBy(projAppInfoMemberModel.getUserId());
		//更新人
		projAppInfoMemberModel.setUpdateDt(new Date());
		this.update("updatePKProjectMapper",projAppInfoMemberModel);
	}

	@Override
	public void deleteProjectAll(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			String projId = arr[i];
			//删除TZ_T_PROJ_INFO主表
			//projInfoService.deleteById(projId);
			//删除TZ_T_PROJ_APP_INFO
			this.delete("deleteByIdProjectMapper",projId);
		}
	}


	@Override
	public Map<String, Object>  selectByEiNjId(String projId) {

		return (Map<String, Object> )this.selectOne("selectByEiNjId",projId);
	}
}