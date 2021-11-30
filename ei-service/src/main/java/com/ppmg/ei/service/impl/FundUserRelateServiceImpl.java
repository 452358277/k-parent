package com.ppmg.ei.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundUserRelateModel;
import com.ppmg.ei.service.FundUserRelateService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("fundUserRelateService")
@Service(interfaceClass = FundUserRelateService.class)
public class FundUserRelateServiceImpl extends BaseServiceImpl<FundUserRelateModel> implements FundUserRelateService {

	public FundUserRelateServiceImpl(){
		this.setNamespace("FundUserRelate");
	}

	@Resource
	private FundUserRelateService fundUserRelateService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertRelate(String relateId, List<FundUserRelateModel> dtoList,String loginUserId )throws Exception {
		FundUserRelateModel mo=new FundUserRelateModel();
		//删除原来绑定的
		if(StringUtils.isNotEmpty(relateId)){
			mo.setUserId(relateId);
			fundUserRelateService.delete(mo);
		}
		//插入新绑定的
		for(FundUserRelateModel dto : dtoList){
			dto.setUpdateBy(loginUserId);
			dto.setUpdateDt(new Date());
			dto.setCreateBy(loginUserId);
			dto.setCreateDt(new Date());
			dto.setUserRoles("1");
			if("0".equals(dto.getFundId())){
				FundUserRelateModel fundUser=new FundUserRelateModel();
				fundUser.setFundId("0");
				fundUser.setUserId(loginUserId);
				FundUserRelateModel fundUse=fundUserRelateService.selectBy(fundUser);
				if(fundUse!=null){
					dto.setAccounts(fundUse.getAccounts());
				}
			}
			fundUserRelateService.insert(dto);
		}

	}


	@Override
	public Map<String, Object> selectFundlist(String userId) {
		return (Map<String, Object>)this.selectOne("selectFundlist",userId);
	}
}