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
		//??????TZ_T_PROJ_INFO??????
		String projId = projAppInfoMemberModel.getProjId();
		String fundId = projAppInfoMemberModel.getFundId();
		ProjInfoModel proInfo = new ProjInfoModel();
		//??????id
		proInfo.setProjId(projId);
		/** ???????????? */
		proInfo.setProjName(projAppInfoMemberModel.getProjName());
		/** ???????????? */
		proInfo.setProjSrc(projAppInfoMemberModel.getProjSrc());
		/** ?????????????????? */
		proInfo.setSrcDesc(projAppInfoMemberModel.getSrcDesc());
		/** ???????????? */
		proInfo.setProjOverview(projAppInfoMemberModel.getProjOverview());
		//??????
		proInfo.setStatus(projAppInfoMemberModel.getStatus());
		/**????????????,TZ_T_PROJ_INFO**/
		proInfo.setFileDesc(projAppInfoMemberModel.getFileDesc());
		/**?????????TZ_T_PROJ_INFO**/
		proInfo.setInveFile(projAppInfoMemberModel.getInveFile());
		//proInfo.setInveId(fundId);//TZ_T_PROJ_INFO???INVE_ID???BD_T_FUND_INFO???FUND_ID
		proInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		if("1".equals(projAppInfoMemberModel.getTag())){
			//??????
			proInfo.setStatus("0");
		}else if("0".equals(projAppInfoMemberModel.getTag())){
			//???????????????
			proInfo.setStatus("1");
		}
		projInfoService.updateprojInfo(proInfo);
		/*//????????????
		ProjInfoModel proInfoSelect = projInfoService.selectById(projId);
		if(proInfoSelect !=null){//????????????
			projInfoService.updateprojInfo(proInfo);
		}else{//????????????
			projInfoService.insertprojInfo(proInfo);
		}*/
		//??????TZ_T_PROJ_INFO???INVE_ID???BD_T_FUND_INFO???FUND_ID??????
		//??????BD_T_FUND_INFO?????????????????????
		FundInfoModel fundInfo = new FundInfoModel();
		fundInfo.setFundId(fundId);//??????
		/** ????????????????????????????????? BD_T_FUND_INFO*/
		fundInfo.setCurrentAmount(projAppInfoMemberModel.getCurrentAmount());
		fundInfo.setCurrentCurrency(projAppInfoMemberModel.getCurrentCurrency());
		/** ??????????????????????????????????????? BD_T_FUND_INFO*/
		fundInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		//?????????
		fundInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		fundInfoService.updateFundInfo(fundInfo);
		/*FundInfoModel f = fundInfoService.selectById(fundId);
		if(f!=null){//??????
			fundInfoService.updateFundInfo(f);
		}else{//??????
			fundInfoService.insertFundInfo(ff);
		}*/
		//??????TZ_T_PROJ_APP_INFO
		ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
		proAppInfo.setProjId(projId);
		proAppInfo.setMajorMatter(projAppInfoMemberModel.getMajorMatter());
		/**????????????**/
		proAppInfo.setInveReason(projAppInfoMemberModel.getInveReason());
		/**????????????**/
		proAppInfo.setFocusInfo(projAppInfoMemberModel.getFocusInfo());
		/**??????????????????**/
		proAppInfo.setInveCurrentAmount(projAppInfoMemberModel.getInveCurrentAmount());
		//??????????????????????????????
		proAppInfo.setApplyAmount(projAppInfoMemberModel.getApplyAmount());
		//?????????????????????
		proAppInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		//?????????
		proAppInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		ProjAppInfoModel per = projAppInfoService.getgetTzProjAppInfo(projId);
		if (per != null) {
			projAppInfoService.updateAppInfo(proAppInfo);
		}else{
			projAppInfoService.insertAppInfo(proAppInfo);
		}
		//??????TZ_T_PROJ_MEMBER,??????
		List<ProjMemberModel> list = projAppInfoMemberModel.getProjMemberModel();
		if (list != null && list.size() > 0) {
			for (ProjMemberModel pm : list) {
				String Pk = UUID.randomUUID().toString();
				pm.setPkId(Pk);
				pm.setProjId(projId);
				pm.setCreateBy(projAppInfoMemberModel.getUserId());
				//?????????
				pm.setUpdateBy(projAppInfoMemberModel.getUserId());
				//?????????
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
		//??????TZ_T_PROJ_INFO??????
		ProjInfoModel proInfo = new ProjInfoModel();
		if(tag != null && "0".equals(tag)){
			//????????????????????????????????????
			proInfo.setProjStatus(projAppInfoMemberModel.getStatus());
			//??????
		}
		proInfo.setProjId(projId);
		//??????id
		proInfo.setProjName(projAppInfoMemberModel.getProjName());
		/** ???????????? */
		proInfo.setProjSrc(projAppInfoMemberModel.getProjSrc());
		/** ???????????? */
		proInfo.setSrcDesc(projAppInfoMemberModel.getSrcDesc());
		/** ?????????????????? */
		proInfo.setProjOverview(projAppInfoMemberModel.getProjOverview());
		/** ???????????? */
		proInfo.setProcessInstId(projAppInfoMemberModel.getProcessInstId());
		//????????????id

		//??????
		proInfo.setFileDesc(projAppInfoMemberModel.getFileDesc());
		/**????????????,TZ_T_PROJ_INFO**/
		proInfo.setInveFile(projAppInfoMemberModel.getInveFile());
		/**?????????TZ_T_PROJ_INFO**/
		//proInfo.setInveId(fundId);//TZ_T_PROJ_INFO???INVE_ID???BD_T_FUND_INFO???FUND_ID
		proInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		/*if(projAppInfoMemberModel.getTag().equals("1")){
			proInfo.setStatus("0");//??????
		}else if(projAppInfoMemberModel.getTag().equals("0")){
			proInfo.setStatus("1");//???????????????
		}*/
		projInfoService.updateprojInfo(proInfo);
		//??????TZ_T_PROJ_APP_INFO
		ProjAppInfoModel proAppInfo = new ProjAppInfoModel();
		proAppInfo.setProjId(projId);
		proAppInfo.setMajorMatter(projAppInfoMemberModel.getMajorMatter());
		/**????????????**/
		proAppInfo.setInveReason(projAppInfoMemberModel.getInveReason());
		/**????????????**/
		proAppInfo.setFocusInfo(projAppInfoMemberModel.getFocusInfo());
		/**??????????????????**/
		proAppInfo.setInveCurrentAmount(projAppInfoMemberModel.getInveCurrentAmount());
		//??????????????????????????????
		proAppInfo.setApplyAmount(projAppInfoMemberModel.getApplyAmount());
		//?????????????????????
		proAppInfo.setCreateBy(projAppInfoMemberModel.getUserId());
		//?????????
		proAppInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		proAppInfo.setUpdateDt(new Date());
		//????????????
		projAppInfoService.updateAppInfo(proAppInfo);

		//??????TZ_T_PROJ_INFO???INVE_ID???BD_T_FUND_INFO???FUND_ID??????
		//??????BD_T_FUND_INFO?????????????????????
		FundInfoModel ff = new FundInfoModel();
		//ff.setFundId(projAppInfoMemberModel.getInveId());
		ff.setFundId(projAppInfoMemberModel.getProjObject());
		ff.setCurrentAmount(projAppInfoMemberModel.getCurrentAmount());
		ff.setCurrentCurrency(projAppInfoMemberModel.getCurrentCurrency());
		ff.setShortName(projAppInfoMemberModel.getProjName());
		//????????????
		fundInfoService.updateFundInfo(ff);
		//??????TZ_T_PROJ_MEMBER,??????
		List<ProjMemberModel> list = projAppInfoMemberModel.getProjMemberModel();
		if (list != null && list.size() > 0) {
			String projIds = projAppInfoMemberModel.getProjId();
			//??????
			projMemberService.deletcProjId(projId);
			//??????
			for (ProjMemberModel pm : list) {
				String Pk = UUID.randomUUID().toString();
				pm.setPkId(Pk);
				pm.setProjId(projIds);
				pm.setCreateBy(projAppInfoMemberModel.getUserId());
				//?????????
				pm.setUpdateBy(projAppInfoMemberModel.getUserId());
				//?????????
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
			//??????TZ_T_PROJ_INFO??????
			projInfoService.deleteById(projId);
			//??????TZ_T_PROJ_APP_INFO
			projAppInfoService.deleteAppInfo(projId);
			//??????TZ_T_PROJ_MEMBER
			projAppInfoService.deleteMember(projId);
			//??????TZ_T_PROJ_INFO???INVE_ID???BD_T_FUND_INFO???FUND_ID??????
			ProjInfoModel projInfoModel = projInfoService.selectById(projId);
			if(projInfoModel != null){
				String fundId = projInfoModel.getInveId();
				System.out.println(fundId);
				//BD_T_FUND_INFO?????????????????????
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

	//-------------------????????????-------------------------
	@Override
	public ProjAppInfoMemberModel getOneProjectDetails(String projId) {
		ProjAppInfoMemberModel one = (ProjAppInfoMemberModel) this.selectOne("selectByIdProjectMapper",projId);
		return one;
	}

	@Override
	public void insertProjectAll(ProjAppInfoMemberModel projAppInfoMemberModel) {
		//??????TZ_T_PROJ_INFO??????
		ProjInfoModel proInfo = new ProjInfoModel();
		proInfo.setStatus(projAppInfoMemberModel.getStatus());
		proInfo.setProjId(projAppInfoMemberModel.getProjId());
		//??????
		proInfo.setInveName(projAppInfoMemberModel.getFundingBody());
		//??????????????????
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		projInfoService.updateprojInfo(proInfo);
		//??????TZ_T_PROJ_APP_INFO???
		List<ProjAppInfoMemberModel> list = new ArrayList<>();
		projAppInfoMemberModel.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		projAppInfoMemberModel.setCreateBy(projAppInfoMemberModel.getUserId());
		//?????????
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
		//??????TZ_T_PROJ_INFO??????
		String tag = projAppInfoMemberModel.getTag();
		ProjInfoModel proInfo = new ProjInfoModel();
		if(tag != null && "0".equals(tag)){
			//????????????????????????????????????
			proInfo.setProjStatus(projAppInfoMemberModel.getStatus());
			//????????????id
			proInfo.setProcessInstId(projAppInfoMemberModel.getProcessInstId());
		}
		proInfo.setProjId(projAppInfoMemberModel.getProjId());
		//??????
		proInfo.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		proInfo.setInveId(projAppInfoMemberModel.getInveId());
		//????????????id
		proInfo.setInveName(projAppInfoMemberModel.getFundingBody());
		//??????????????????
        proInfo.setProjNo(projAppInfoMemberModel.getProjNo());
        //????????????==??????????????????+?????????+????????????*/
		projInfoService.updateprojInfo(proInfo);
		//??????TZ_T_PROJ_APP_INFO
		projAppInfoMemberModel.setUpdateBy(projAppInfoMemberModel.getUserId());
		//?????????
		projAppInfoMemberModel.setUpdateDt(new Date());
		this.update("updatePKProjectMapper",projAppInfoMemberModel);
	}

	@Override
	public void deleteProjectAll(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			String projId = arr[i];
			//??????TZ_T_PROJ_INFO??????
			//projInfoService.deleteById(projId);
			//??????TZ_T_PROJ_APP_INFO
			this.delete("deleteByIdProjectMapper",projId);
		}
	}


	@Override
	public Map<String, Object>  selectByEiNjId(String projId) {

		return (Map<String, Object> )this.selectOne("selectByEiNjId",projId);
	}
}