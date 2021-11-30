package com.ppmg.ei.service.impl;

import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.service.FundInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BdTFundLockInfoModel;
import com.ppmg.ei.service.BdTFundLockInfoService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component("bdTFundLockInfoService")
@Service(interfaceClass = BdTFundLockInfoService.class)
public class BdTFundLockInfoServiceImpl extends BaseServiceImpl<BdTFundLockInfoModel> implements BdTFundLockInfoService {

	public BdTFundLockInfoServiceImpl(){
		this.setNamespace("BdTFundLockInfo");
	}

	@Resource
	private FundInfoService fundInfoService;

	@Resource
	private BdTFundLockInfoService bdTFundLockInfoService;

	@Override
	public void saveInfo(BdTFundLockInfoModel model,String userId) {
		FundInfoModel  fundM=fundInfoService.selectById(model.getFundId());
		//判断是否修改
		//查询是否存在
		BdTFundLockInfoModel bdModel=new BdTFundLockInfoModel();
		bdModel.setFundId(model.getFundId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<BdTFundLockInfoModel> list=bdTFundLockInfoService.selectList(bdModel);
		if(list!=null&&!list.isEmpty()){
			//判断是否修改了
					if(!list.get(0).getLockName().equals(model.getLockName())){
						model.setLockId(UUID.randomUUID().toString());
						model.setCreateDt(new Date());
						model.setCreateBy(userId);
						model.setUpdateBy(userId);
						model.setUpdateDt(new Date());
						bdTFundLockInfoService.insert(model);
					}else{
						model.setLockId(list.get(0).getLockId());
						model.setUpdateBy(userId);
						model.setUpdateDt(new Date());
						bdTFundLockInfoService.update(model);
					}
		}else{
			//新增
			model.setLockId(UUID.randomUUID().toString());
			model.setCreateDt(new Date());
			model.setCreateBy(userId);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			bdTFundLockInfoService.insert(model);
		}
		FundInfoModel fund=new FundInfoModel();
		fund.setFundId(model.getFundId());
		fund.setKeyPersonLock(model.getLockName());
		fundInfoService.update(fund);

	}
}