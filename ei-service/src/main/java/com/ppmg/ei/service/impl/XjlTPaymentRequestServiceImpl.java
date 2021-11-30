package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.service.AppUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.XjlTPaymentRequestModel;
import com.ppmg.ei.service.XjlTPaymentRequestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("xjlTPaymentRequestService")
@Service(interfaceClass = XjlTPaymentRequestService.class)
public class XjlTPaymentRequestServiceImpl extends BaseServiceImpl<XjlTPaymentRequestModel> implements XjlTPaymentRequestService {

	public XjlTPaymentRequestServiceImpl(){
		this.setNamespace("XjlTPaymentRequest");
	}

	@Resource
	private XjlTPaymentRequestService xjlTPaymentRequestService;

	@Resource
	private AppUserService appUserService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	public void insertByPqId(XjlTPaymentRequestModel xjlTPaymentRequestModel, String userId) {
			xjlTPaymentRequestModel.setCreateBy(userId);
			xjlTPaymentRequestModel.setCreateDt(new Date());
			xjlTPaymentRequestModel.setUpdateBy(userId);
			xjlTPaymentRequestModel.setUpdateDt(new Date());
		    xjlTPaymentRequestModel.setAppDt(new Date());
			xjlTPaymentRequestModel.setAppUser(appUserService.getUserNameById(userId));
			xjlTPaymentRequestModel.setAppUserId(userId);
			xjlTPaymentRequestService.insert(xjlTPaymentRequestModel);

	}

	@Override
	public Double selectCountSum(String projectOrFundId) {
		return (Double)this.selectOne("selectCountSum",projectOrFundId);
	}

	@Override
	public Double selectSumRmbActualPayAmount(String projectOrFundId) {
		return (Double)this.selectOne("selectSumRmbActualPayAmount",projectOrFundId);
	}

	@Override
	public Double selectSumPass(String projectOrFundId) {
		return (Double)this.selectOne("selectSumPass",projectOrFundId);
	}
	@Override
	public List<XjlTPaymentRequestModel> selectListByPoId(XjlTPaymentRequestModel xxl) {
		return this.selectList("selectListByPoId",xxl);
	}


	@Override
	public List<XjlTPaymentRequestModel> selectByProjId(String projId) {
		return this.selectList("selectByListProjId",projId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByPo(XjlTPaymentRequestModel xjl, String userId)throws  Exception{
		xjl.setUpdateBy(userId);
		xjl.setCreateDt(new Date());
		xjlTPaymentRequestService.update(xjl);
		//查询 本次修改的所有的付款记录
		XjlTPaymentRequestModel xxl=new XjlTPaymentRequestModel();
		xxl.setPqId(xjl.getPqId());
		xxl.setProjectOrFundId(xjl.getProjectOrFundId());
		List<XjlTPaymentRequestModel> list=xjlTPaymentRequestService.selectListByPoId(xxl);
		if(list!=null&&list.size()>0){
			for(XjlTPaymentRequestModel xjlTPaymentRequestModel:list){
				//查询
				XjlTPaymentRequestModel xjlT=new XjlTPaymentRequestModel();
				xjlT.setPqId(xjlTPaymentRequestModel.getPqId());
				xjlT.setProjectOrFundId(xjlTPaymentRequestModel.getProjectOrFundId());
				Double doub=xjlTPaymentRequestService.selectCountBySum(xjlT);
				XjlTPaymentRequestModel xl=new XjlTPaymentRequestModel();
				if(doub!=null){
					xl.setSumInveAmount(doub);
				}else{
					xl.setSumInveAmount(0.0);
				}
				xl.setPqId(xjlTPaymentRequestModel.getPqId());
				xjlTPaymentRequestService.update(xl);
			}

		}
	}

	@Override
	public Double selectCountBySum(XjlTPaymentRequestModel xjlT) {
		return (Double)this.selectOne("selectCountBySum",xjlT);
	}

	@Override
	public Double selectsumAllPay(String INVE_ID) {
		return (Double)this.selectOne("selectsumAllPay",INVE_ID);
	}
	@Override
	public <E> PageInfo<E> selectInveMoneyListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectInveMoneyListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteByIds(List<String> ids, String userId) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("ids", ids);
		paramsMap.put("userId", userId);
		paramsMap.put("status", "9");
		xjlTPaymentRequestService.update("deleteByIds", paramsMap);
	}

	@Override
	public List<XjlTPaymentRequestModel> selectNoPassList(String projId) {
		return this.selectList("selectNoPassList",projId);
	}

}