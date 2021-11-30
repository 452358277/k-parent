package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.FundShareInfoModel;
import com.ppmg.ei.model.FundShareItemModel;
import com.ppmg.ei.model.LedgerMagModel;
import com.ppmg.ei.service.FundShareInfoService;
import com.ppmg.ei.service.FundShareItemService;
import com.ppmg.ei.service.LedgerMagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("fundShareInfoService")
@Service(interfaceClass = FundShareInfoService.class)
public class FundShareInfoServiceImpl extends BaseServiceImpl<FundShareInfoModel> implements FundShareInfoService {

	public FundShareInfoServiceImpl(){
		this.setNamespace("FundShareInfo");
	}


	@Resource
	private FundShareInfoService fundShareInfoService;

	@Resource
	private FundShareItemService fundShareItemService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private LedgerMagService ledgerMagService;


	@Override
	public <E> PageInfo<E> selectFundShareInfoPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFundShareInfoPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectByItemListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectByItemListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<FundShareInfoModel> selectListByEntId(FundShareInfoModel fundShareInfoModel) {
		return this.selectList("selectListByEntId",fundShareInfoModel);
	}

	@Override
	public void addFundShareInfoListCZ(FundShareInfoModel fundShareInfoModel) {
		List<FundShareInfoModel> list = new ArrayList<>();
		list.add(fundShareInfoModel);
		this.insert("insertFundShareInfoCZ",list);
	}

	@Override
	public <E> PageInfo<E> selectFundSharePage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFundSharePage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override//分页查询出资信息列表
	public <E> PageInfo<E> selectFundShareInfoPageList(SearchCondition searchCondition) {
		PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
		List<E> list = this.selectList("selectListPageFundShareInfoListCZ", searchCondition);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public FundShareInfoModel selectOneFundShareInfo(String pkId) {
		return (FundShareInfoModel) this.selectOne("selectByIdDetailsCZ",pkId);
	}

	@Override
	public Integer updateFundShareInfo(FundShareInfoModel fundShareInfoModel) {
		if(StringUtils.isEmpty(fundShareInfoModel.getStatus())){
			fundShareInfoModel.setStatus("0");
		}
		return this.update("updateFundShareInfoCZ",fundShareInfoModel);
	}

	@Override
	public <E> PageInfo<E> selectFundShareInfoPageListTwo(SearchCondition searchCondition) {
		PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
		List<E> list = this.selectList("selectListPageWhitCompanyRelationCZ", searchCondition);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public Double getInveAmountSum(String fundId) {
		return (Double) this.selectOne("selectInveAmountSum",fundId);
	}



	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertFundShare(FundShareInfoModel model, FundShareItemModel fundShareItem,String userId) throws Exception {

           //根据fund_id ,和出资人id查询 是否新增过期数
		   FundShareInfoModel fundShare=new FundShareInfoModel();
		   fundShare.setFundId(model.getFundId());
		   fundShare.setInvestorId(model.getInvestorId());
		    FundShareInfoModel  list= fundShareInfoService.selectBy(fundShare);
		    if(list!=null){
			   String pkId= list.getPkId();
			   fundShareItem.setPkId(pkId);
		    }else{
			   model.setCreateBy(userId);
			   model.setCreateDt(new Date());
			   model.setUpdateBy(userId);
			   model.setUpdateDt(new Date());
			   String pkId= serialnoService.getSequence("EI.RZ_T_FUND_SHARE_INFO");
			   model.setPkId(pkId);
			   fundShareInfoService.insert(model);
			   fundShareItem.setPkId(pkId);
		   }
			fundShareItem.setCreateBy(userId);
			fundShareItem.setCreateDt(new Date());
			fundShareItem.setUpdateBy(userId);
			fundShareItem.setUpdateDt(new Date());
		    fundShareItemService.insert(fundShareItem);

	}

	@Override
	public Double getSumPayAmount(FundShareInfoModel fundShareInfoModel) {
		return (Double)this.selectOne("getSumPayAmount",fundShareInfoModel);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByName(String pkId) throws Exception{

		FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
		fundShareInfoModel.setPkId(pkId);
		fundShareInfoModel.setStatus("1");
		fundShareInfoService.update(fundShareInfoModel);

		FundShareItemModel fundShareItemModel=new FundShareItemModel();
		fundShareItemModel.setPkId(pkId);
		fundShareItemModel.setStatus("1");
		fundShareItemService.update(fundShareItemModel);
	}

	@Override
	public void updateShareInfo(FundShareInfoModel model, List<LedgerMagModel> list,String projId,String oldInvestorId) {
		fundShareInfoService.update(model);
		//查询股权投资下存在的台账
		if(StringUtils.isNotEmpty(oldInvestorId) &&StringUtils.isNotEmpty(projId)){
			LedgerMagModel ledgerMagModel=new LedgerMagModel();
			ledgerMagModel.setProjId(projId);
			ledgerMagModel.setObjectId(oldInvestorId);
			ledgerMagModel.setStatus("0");
			List<LedgerMagModel> listMag=ledgerMagService.selectList("getMagList",ledgerMagModel);
			for(LedgerMagModel ledgerMa:listMag){
				ledgerMa.setObjectId(model.getInvestorId());
				ledgerMa.setObjectName(model.getInvestorName());
				ledgerMa.setUpdateDt(new Date());
				ledgerMa.setUpdateBy(model.getUpdateBy());
				ledgerMagService.update(ledgerMa);
			}
		}



		/*for(LedgerMagModel ledgerMagModel:list){
			model.setCreateBy(model.getUpdateBy());
			model.setCreateDt(new Date());
			model.setUpdateBy(model.getUpdateBy());
			model.setUpdateDt(new Date());
			String ledgerId = serialnoService.getSequence("EI.XJL_T_LEDGER_MAG");
			ledgerMagModel.setLedgerId(ledgerId);
			ledgerMagService.insert(ledgerMagModel);
		}*/

	}

	@Override
	public <E> PageInfo<E> getFundShareList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getFundShareList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<FundShareInfoModel> selectListByList(FundShareInfoModel model) {
		return this.selectList("selectListByList",model);
	}
}