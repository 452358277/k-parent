package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.AppUserroleModel;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.UserModel;
import com.ppmg.ei.service.AppUserroleService;
import com.ppmg.ei.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.service.BankInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/** 
 * 描述 [Service.impl] 
 * @author yuyongjun
 * @date 2019-06-25 09:21 
 */ 
@Component("bankInfoService")
@Service(interfaceClass = BankInfoService.class)
public class BankInfoServiceImpl extends BaseServiceImpl<BankInfoModel> implements BankInfoService {

	public BankInfoServiceImpl(){
		this.setNamespace("BankInfo");
	}

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private BankInfoService bankInfoService;

	@Resource(name="userService")
	private UserService userService;

	@Resource
	private AppUserroleService appUserroleService;

	@Value("${uim.appId}")
	private Long appId;

	/**
	 * 托管银行角色编号
	 */
	@Value("${uim.outRoles.bank}")
	private Long bank;



	/**
	 * 通过用户编号查询银行信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public BankInfoModel selectByUserId(String userId) {
		return (BankInfoModel) this.selectOne("selectByUserId",userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Integer insertBankInfo(BankInfoModel model, String password,String userId)throws  Exception {
		String bankId = serialnoService.getSequence("EI.BD_T_BANK_INFO");
		String uid = serialnoService.getSequence("BASE.APP_USER");
		if(StringUtils.isNotEmpty(model.getBankName())){
			BankInfoModel bankInfoModel=new BankInfoModel();
			bankInfoModel.setBankName(model.getBankName());
			BankInfoModel bank1=bankInfoService.selectBy(bankInfoModel);
			if(bank1==null){
				UserModel user=new UserModel();
				user.setId(Long.parseLong(uid));
				user.setLoginname(model.getBankName());
				user.setName(model.getBankName());
				user.setDescription(model.getBankName());
				user.setUsertype(2L);
				user.setDisabled("0");
				user.setIsDelete("0");
				user.setPassword(password);
				UserModel user1=new UserModel();
				user1.setLoginname(model.getBankName());
				UserModel user2=userService.selectBy(user1);
				AppUserroleModel appUserroleModel = new AppUserroleModel();
				appUserroleModel.setAppid(appId);
				appUserroleModel.setUserid(user.getId());
				appUserroleModel.setRoleid(bank);
				appUserroleService.insert(appUserroleModel);
				if(user2==null){
					userService.insert(user);
					model.setBankId(bankId);
					model.setCreateBy(userId);
					model.setUserId(uid);
					model.setCreateDt(new Date());
					model.setUpdateBy(userId);
					model.setCreateBy(userId);
					model.setUpdateDt(new Date());
					bankInfoService.insert(model);
				}else{
					return -2;
				}
			}else{
				return -1;
			}
		}
		return 1;
	}


	@Override
	public List<BankInfoModel> getBankList(SearchCondition var1) {
		return this.selectList("getBankList",var1);
	}

	@Override
	public List<FundInfoModel> getFundMcPhoneList(SearchCondition var1) {
		return this.selectList("getFundMcPhoneList",var1);
	}
}