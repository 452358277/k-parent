package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.model.BankProductModel;
import com.ppmg.ei.model.LedgerMagModel;
import com.ppmg.ei.model.ProjAppInfoModel;
import com.ppmg.ei.service.BankInfoService;
import com.ppmg.ei.service.BankProductService;
import com.ppmg.ei.service.LedgerMagService;
import com.ppmg.ei.service.ProjAppInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("ledgerMagService")
@Service(interfaceClass = LedgerMagService.class)
public class LedgerMagServiceImpl extends BaseServiceImpl<LedgerMagModel> implements LedgerMagService {

	public LedgerMagServiceImpl(){
		this.setNamespace("LedgerMag");
	}

	@Resource
	private LedgerMagService ledgerMagService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private BankProductService bankProductService;

	@Resource
	private BankInfoService bankInfoService;


	@Resource
	private ProjAppInfoService projAppInfoService;

	@Override
	public <E> PageInfo<E> selectcashFlowPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectcashFlowPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectLedgerPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectLedgerPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String insertLedgerMag(LedgerMagModel model, String userId, BankProductModel bankProduct) {
		String ledgerId = serialnoService.getSequence("EI.XJL_T_LEDGER_MAG");
		model.setLedgerId(ledgerId);
		model.setCreateBy(userId);
		model.setCreateDt(new Date());
		model.setUpdateDt(new Date());
		model.setUpdateBy(userId);
		model.setGroupId("1");
		model.setStatus("0");
		model.setCurrencyName("人民币");
		model.setCurrencyNum("1");
		model.setInvestPaymentType("2");
		if("1".equals(model.getFinanceType())){
		  //如果是理财产品或者结构性存款 //需要插入产品表
           if("3".equals(model.getRecBillTypeNum())||"4".equals(model.getRecBillTypeNum())){
			 //根据登录人查询是哪个银行
			 BankInfoModel bankInfoModel=bankInfoService.selectByUserId(userId);
			 BankProductModel product=new BankProductModel();
			   product.setProductCode(bankProduct.getProductCode());
			   product.setBankId(bankInfoModel.getBankId());
			 BankProductModel bankProduct1=bankProductService.selectBy(product);
			 //判断相同银行下的编号是否重复
             if(bankProduct1!=null){
             	return "-1";
			 }

			 if(bankInfoModel!=null){
				 bankProduct.setBankId(bankInfoModel.getBankId());
			 }
			   bankProduct.setStatus("0");
			 String productId = serialnoService.getSequence("EI.BD_T_BANK_PRODUCT");
			   bankProduct.setProductId(productId);
			   bankProduct.setCreateBy(userId);
			   bankProduct.setCreateDt(new Date());
			   bankProduct.setUpdateBy(userId);
			   bankProduct.setUpdateDt(new Date());
			   bankProduct.setBuyAmount(model.getAmt());
			   bankProduct.setAccounts(model.getAccounts());
			   model.setProductId(productId);
			   bankProductService.insert(bankProduct);
		  }

		}else{
			if("1".equals(model.getRecBillTypeNum())){
				//入资
				//插入产品的id,
				model.setProductId(bankProduct.getProductId());
				model.setAccountBalance(model.getAccountBalance()+model.getGainAmount());
			}

		}
		ledgerMagService.insert(model);
		return "0";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByName(LedgerMagModel ledgerMagModel, String userId, BankProductModel bankProduct,String productId)throws Exception {
        if("1".equals(ledgerMagModel.getFinanceType())){
        	//付款
			if("3".equals(ledgerMagModel.getRecBillTypeNum())||"4".equals(ledgerMagModel.getRecBillTypeNum())){
              if(StringUtils.isNotEmpty(productId)){
                //修改
				  bankProduct.setUpdateDt(new Date());
				  bankProduct.setUpdateBy(userId);
				  bankProductService.update(bankProduct);
			  }else{
              	//新增
				  bankProduct.setStatus("0");
				  String id = serialnoService.getSequence("EI.BD_T_BANK_PRODUCT");
				  bankProduct.setProductId(id);
				  bankProduct.setCreateBy(userId);
				  bankProduct.setCreateDt(new Date());
				  bankProduct.setUpdateBy(userId);
				  bankProduct.setUpdateDt(new Date());
				  bankProduct.setBuyAmount(ledgerMagModel.getAmt());
				  ledgerMagModel.setProductId(id);
				  bankProductService.insert(bankProduct);
			  }
			}else{
				if(StringUtils.isNotEmpty(productId)){
					bankProductService.deleteById(productId);
					ledgerMagModel.setProductId("");
					ledgerMagModel.setCustomerName("");
				}
			}

		}
		ledgerMagModel.setUpdateDt(new Date());
		ledgerMagModel.setUpdateBy(userId);
		ledgerMagService.update(ledgerMagModel);



	}

	@Override
	public <E> PageInfo<E> selectListFundPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListFundPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertInfo(LedgerMagModel ledgerMagModel, Double intendedAmount, String intendedCurrency)throws Exception {
		ledgerMagService.insert(ledgerMagModel);
		if(StringUtils.isNotEmpty(ledgerMagModel.getProjId())){
			ProjAppInfoModel model=new ProjAppInfoModel();
			model.setProjId(ledgerMagModel.getProjId());
			model.setIntendedAmount(intendedAmount);
			model.setIntendedCurrency(intendedCurrency);
			model.setUpdateDt(new Date());
			model.setUpdateBy(ledgerMagModel.getUpdateBy());
			projAppInfoService.update(model);
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateInfo(LedgerMagModel ledgerMagModel, Double intendedAmount, String intendedCurrency) throws Exception{
		ledgerMagService.update(ledgerMagModel);
		if(StringUtils.isNotEmpty(ledgerMagModel.getProjId())){
			ProjAppInfoModel model=new ProjAppInfoModel();
			model.setProjId(ledgerMagModel.getProjId());
			model.setIntendedAmount(intendedAmount);
			model.setIntendedCurrency(intendedCurrency);
			model.setUpdateDt(new Date());
			model.setUpdateBy(ledgerMagModel.getUpdateBy());
			projAppInfoService.update(model);
		}
	}

	@Override
	public void updateByObject(LedgerMagModel model) {
		ledgerMagService.update("updateByObject",model);
	}

	@Override
	public List<LedgerMagModel> selectListByOccDt(LedgerMagModel ledgerMagModel) {
		return this.selectList("selectListByOccDt",ledgerMagModel);
	}
}