package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundShareItemModel;
import com.ppmg.ei.service.FundShareItemService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("fundShareItemService")
@Service(interfaceClass = FundShareItemService.class)
public class FundShareItemServiceImpl extends BaseServiceImpl<FundShareItemModel> implements FundShareItemService {

	public FundShareItemServiceImpl(){
		this.setNamespace("FundShareItem");
	}

	@Resource
	private FundShareItemService fundShareItemService;

	@Override
	public List<FundShareItemModel> selectItemList(String pkId) {
		return this.selectList("selectItemList", pkId);
	}

	@Override
	public List<FundShareItemModel> selectByFundIdList(String fundId) {
		return this.selectList("selectByFundIdList", fundId);
	}

	@Override
	public Double selectSumDuesAmount(String fundId) {
		return (Double)this.selectOne("selectSumDuesAmount",fundId);
	}

	@Override
	public Double selectByCloseRound(FundShareItemModel fundShareItemModel) {
		return (Double)this.selectOne("selectByCloseRound",fundShareItemModel);
	}

	@Override
	public Double selectByFundCloseRound(FundShareItemModel fundShareItemModel) {
		return (Double)this.selectOne("selectByFundCloseRound",fundShareItemModel);
	}

	@Override
	public FundShareItemModel selectByC(FundShareItemModel base) {
		return (FundShareItemModel)this.selectOne("selectByC", base);
	}

	@Override
	public Double selectSumGovDuesAmount(String fundId) {
		return (Double)this.selectOne("selectSumGovDuesAmount",fundId);
	}



	@Override
	public Double selectBySum(String pkId) {
		return (Double)this.selectOne("selectBySum",pkId);
	}

	@Override
	public Double selectByCR(FundShareItemModel fundShareItemModel) {
		return (Double)this.selectOne("selectByCR",fundShareItemModel);
	}

	@Override
	public Double selectByLastCR(FundShareItemModel fundShareItemModel) {
		return (Double)this.selectOne("selectByLastCR",fundShareItemModel);
	}

	@Override
	public void updateByIds(List<FundShareItemModel> dtoList,String userId) throws Exception{
		if(dtoList!=null&&dtoList.size()>0){
			for(FundShareItemModel dto : dtoList){
				dto.setUpdateDt(new Date());
				dto.setUpdateBy(userId);
				if(StringUtils.isNotEmpty(dto.getItemId())){
					dto.setStatus("1");
					fundShareItemService.update(dto);
				}
			}
		}


	}

	@Override
	public Double selectBySumPay(String fundId) {
		return (Double)fundShareItemService.selectOne("selectBySumPay",fundId);
	}
}