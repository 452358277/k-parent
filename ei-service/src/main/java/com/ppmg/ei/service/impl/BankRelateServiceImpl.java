package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.model.FundUserRelateModel;
import com.ppmg.ei.service.BankInfoService;
import com.ppmg.ei.service.FundUserRelateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BankRelateModel;
import com.ppmg.ei.service.BankRelateService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Component("bankRelateService")
@Service(interfaceClass = BankRelateService.class)
public class BankRelateServiceImpl extends BaseServiceImpl<BankRelateModel> implements BankRelateService {

	public BankRelateServiceImpl(){
		this.setNamespace("BankRelate");
	}

	@Resource
	private BankRelateService bankRelateService;

	@Resource
	private FundUserRelateService fundUserRelateService;

	@Resource
	private BankInfoService bankInfoService;


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertById(BankRelateModel bankRelateModel,String userId) throws  Exception{
        //先插入母基金账户表
		bankRelateModel.setCreateBy(userId);
		bankRelateModel.setCreateDt(new Date());
		bankRelateModel.setUpdateDt(new Date());
		bankRelateModel.setUpdateBy(userId);
		bankRelateModel.setStatus("0");
		bankRelateService.insert(bankRelateModel);

		FundUserRelateModel fundUserRelateModel=new FundUserRelateModel();
		fundUserRelateModel.setCreateBy(userId);
		fundUserRelateModel.setCreateDt(new Date());
		fundUserRelateModel.setUpdateDt(new Date());
		fundUserRelateModel.setUpdateBy(userId);
		fundUserRelateModel.setFundId(bankRelateModel.getFundId());
		fundUserRelateModel.setIsDelete("0");
		fundUserRelateModel.setAccounts(bankRelateModel.getAccounts());
		//根据银行id查询对应的银行userId
		if(StringUtils.isNotEmpty(bankRelateModel.getBankId())){
			BankInfoModel bankInfoModel=bankInfoService.selectById(bankRelateModel.getBankId());
			if(bankInfoModel!=null){
				fundUserRelateModel.setUserId(bankInfoModel.getUserId());
				fundUserRelateModel.setUserRoles("2");
				fundUserRelateService.insert(fundUserRelateModel);
			      }
				}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateOpenOrClose(String bankId, String fundId,String status,String accounts,String userId)throws  Exception {
		BankRelateModel bankRelateModel=new BankRelateModel();
		bankRelateModel.setFundId(fundId);
		bankRelateModel.setBankId(bankId);
		bankRelateModel.setStatus(status);
		bankRelateModel.setAccounts(accounts);
		bankRelateModel.setOldAccounts(accounts);
		bankRelateModel.setUpdateDt(new Date());
		bankRelateModel.setUpdateBy(userId);
		bankRelateService.update(bankRelateModel);
		BankInfoModel bankInfoModel=bankInfoService.selectById(bankId);
		String a="1";
		if(a.equals(status)){
        //禁用
			FundUserRelateModel fundUserRelateModel=new FundUserRelateModel();
			fundUserRelateModel.setFundId(fundId);
			if(bankInfoModel!=null){
				fundUserRelateModel.setUserId(bankInfoModel.getUserId());
				fundUserRelateService.delete(fundUserRelateModel);
			}
		}else{
			//启用
			FundUserRelateModel fundUserRelateModel=new FundUserRelateModel();
			fundUserRelateModel.setCreateBy(userId);
			fundUserRelateModel.setCreateDt(new Date());
			fundUserRelateModel.setUpdateDt(new Date());
			fundUserRelateModel.setUpdateBy(userId);
			fundUserRelateModel.setFundId(fundId);
			fundUserRelateModel.setUserRoles("2");
			fundUserRelateModel.setAccounts(accounts);
			if(bankInfoModel!=null){
				fundUserRelateModel.setUserId(bankInfoModel.getUserId());
				fundUserRelateService.insert(fundUserRelateModel);
			}

		}

	}

	@Override
	public void updateByUserRelate(BankRelateModel model) {
		bankRelateService.update(model);
		if(StringUtils.isNotEmpty(model.getBankId())){
			BankInfoModel bankInfo=	bankInfoService.selectById(model.getBankId());
			if(bankInfo!=null&&StringUtils.isNotEmpty(bankInfo.getUserId())){
				FundUserRelateModel fundUserRelate=new FundUserRelateModel();
				fundUserRelate.setFundId(model.getFundId());
				fundUserRelate.setUserId(bankInfo.getUserId());
				fundUserRelate.setAccounts(model.getAccounts());
				fundUserRelateService.update(fundUserRelate);
			}
		}



	}
}