package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.HandleRecordModel;
import com.ppmg.ei.service.FundShareInfoRZTService;
import com.ppmg.ei.service.HandleRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FundShareInfoModel;
import com.ppmg.ei.service.FundShareInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-10-23 15:36 
 */ 
@Component("fundShareInfoRZTService")
@Service(interfaceClass = FundShareInfoRZTService.class)
public class FundShareInfoRZTServiceImpl extends BaseServiceImpl<FundShareInfoModel> implements FundShareInfoRZTService {

	public FundShareInfoRZTServiceImpl(){
		this.setNamespace("FundShareInfoRZT");
	}

	@Resource
	private FundShareInfoRZTService fundShareInfoRZTService;

	@Resource(name="handleRecordService")
	private HandleRecordService handleRecordService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByInfo(FundShareInfoModel fundShareInfoModel) throws  Exception{
		fundShareInfoRZTService.update(fundShareInfoModel);
		//判断出资主体是否修改
		HandleRecordModel handleRecordModel=new HandleRecordModel();
		List<String> list=new ArrayList<>();
		//查询原来的值
		FundShareInfoModel  fundShareInfo=fundShareInfoRZTService.selectById(fundShareInfoModel.getPkId());
        if(fundShareInfo!=null){
        	if(StringUtils.isNotEmpty(fundShareInfo.getInvestorId())){
        		if(!fundShareInfo.getInvestorId().equals(fundShareInfoModel.getInvestorId())){
					System.out.println("新"+fundShareInfoModel.getInvestorName()+"老"+fundShareInfoModel.getInvestorName());
					handleRecordModel.setContent(fundShareInfoModel.getInvestorName());
					handleRecordModel.setContentOld(fundShareInfo.getInvestorName());
					handleRecordModel.setColumName("investorName");
					list.add("1");
				}
				if(!fundShareInfo.getInveCurrency().equals(fundShareInfoModel.getInveCurrency())){
					System.out.println("新"+handleRecordModel.getContent()+"老"+handleRecordModel.getContent());
					handleRecordModel.setContent(handleRecordModel.getContent()==null?"":handleRecordModel.getContent()+"-"+fundShareInfoModel.getInveCurrency());
					handleRecordModel.setContentOld(handleRecordModel.getContentOld()==null?"":handleRecordModel.getContentOld()+"-"+fundShareInfo.getInveCurrency());
					handleRecordModel.setColumName(handleRecordModel.getColumName()+"InveCurrency");
					list.add("2");
				}
				if(!fundShareInfo.getInveAmount().equals(fundShareInfoModel.getInveAmount())){
					System.out.println("新"+handleRecordModel.getContent()+"老"+fundShareInfoModel.getInveOldAmount());
					handleRecordModel.setContent(handleRecordModel.getContentOld()==null?"":handleRecordModel.getContentOld()+"-"+fundShareInfoModel.getInveAmount());
					handleRecordModel.setContentOld(handleRecordModel.getContentOld()==null?"":handleRecordModel.getContentOld()+"-"+fundShareInfo.getInveOldAmount());
					handleRecordModel.setColumName(handleRecordModel.getColumName()+"AMOUNT");
					list.add("3");
				}
			}

		}

       if(list!=null&&list.size()>0){
		   String pkId = serialnoService.getSequence("EI.SEQ_EI_MY_HANDLE_RECORD");
		   handleRecordModel.setPkId(pkId);
		   handleRecordModel.setProjectOrFundId(fundShareInfoModel.getFundId());
		   handleRecordModel.setColumResource("基金合伙人");
		   handleRecordModel.setCreateBy(fundShareInfoModel.getUpdateBy());
		   handleRecordModel.setCreateDt(fundShareInfoModel.getCreateDt());
		   handleRecordModel.setUpdateBy(fundShareInfoModel.getUpdateBy());
		   handleRecordModel.setUpdateDt(fundShareInfoModel.getCreateDt());
		   handleRecordService.insert(handleRecordModel);
	   }


	}
}