package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.EntBaseInfoModel;
import com.ppmg.ei.model.InveResumeModel;
import com.ppmg.ei.service.EntBaseInfoService;
import com.ppmg.ei.service.InveResumeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.InveInfoModel;
import com.ppmg.ei.service.InveInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("inveInfoService")
@Service(interfaceClass = InveInfoService.class)
public class InveInfoServiceImpl extends BaseServiceImpl<InveInfoModel> implements InveInfoService {

	public InveInfoServiceImpl(){
		this.setNamespace("InveInfo");
	}

	@Resource
	private InveInfoService inveInfoService;

	@Resource
	private InveResumeService inveResumeService;

	@Reference(check = false)
	private SerialnoService serialnoService;
	@Resource
	private  EntBaseInfoService entBaseInfoService;

	@Override
	public List<InveInfoModel> selectListByMcName(InveInfoModel in) {
		return this.selectList("selectListByMcName", in);
	}

	@Override
	public InveInfoModel selectOneInveInfo(String investorId) {
		return (InveInfoModel)this.selectOne("selectInveInfoResume",investorId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByInvestorId(InveInfoModel in, String userId, InveResumeModel inveResumeModel) throws Exception{
		if(StringUtils.isNotEmpty(in.getInvestorType())){
			String a="2";
			if(!a.equals(in.getInvestorType())){
            //新增企业
				String enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
				in.setEnteId(enterpriseId);
				EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
				entBaseInfoModel.setEnterpriseId(enterpriseId);
				entBaseInfoModel.setName(in.getInvestorName());
				entBaseInfoModel.setCreateBy(userId);
				entBaseInfoModel.setCreateDt(new Date());
				entBaseInfoModel.setUpdateBy(userId);
				entBaseInfoModel.setUpdateDt(new Date());
				entBaseInfoModel.setStatus("未注册");
				String b="4";
				if(b.equals(in.getInvestorType())){
					entBaseInfoModel.setIsAbroad("1");
				}else{
					entBaseInfoModel.setIsAbroad("0");
				}
				entBaseInfoService.insert(entBaseInfoModel);
			}
		}
		in.setCreateBy(userId);
		in.setCreateDt(new Date());
		in.setUpdateBy(userId);
		in.setUpdateDt(new Date());
		inveInfoService.insert(in);
		inveResumeModel.setCreateBy(userId);
		inveResumeModel.setCreateDt(new Date());
		inveResumeModel.setUpdateBy(userId);
		inveResumeModel.setUpdateDt(new Date());
		inveResumeService.insert(inveResumeModel);

	}

	@Override
	public void updateByInvestorId(InveInfoModel in, String userId, InveResumeModel inveResumeModel) throws Exception {
		if(StringUtils.isNotEmpty(in.getEnteId())){
			String a="2";
			if(!a.equals(in.getInvestorType())){
				//新增企业
				EntBaseInfoModel entBaseInfoModel=new EntBaseInfoModel();
				entBaseInfoModel.setName(in.getInvestorName());
				entBaseInfoModel.setCreateBy(userId);
				entBaseInfoModel.setCreateDt(new Date());
				entBaseInfoModel.setUpdateBy(userId);
				entBaseInfoModel.setUpdateDt(new Date());
				entBaseInfoModel.setStatus("未注册");
				EntBaseInfoModel entBaseInfoModel1=new EntBaseInfoModel();
				entBaseInfoModel1.setName(in.getInvestorName());
				entBaseInfoModel1.setDeleteFlag("0");
				List<EntBaseInfoModel> entBaseInfo=entBaseInfoService.selectList(entBaseInfoModel1);
				if(entBaseInfo!=null&&!entBaseInfo.isEmpty()){
					String b="4";
					if(b.equals(in.getInvestorType())){
						entBaseInfoModel.setIsAbroad("1");
					}else{
						entBaseInfoModel.setIsAbroad("0");
					}
					entBaseInfoModel.setEnterpriseId(entBaseInfo.get(0).getEnterpriseId());
					entBaseInfoService.update(entBaseInfoModel);
				}else{
					String b="4";
					if(b.equals(in.getInvestorType())){
						entBaseInfoModel.setIsAbroad("1");
					}else{
						entBaseInfoModel.setIsAbroad("0");
					}
					String enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
					in.setEnteId(enterpriseId);
					entBaseInfoModel.setEnterpriseId(enterpriseId);
					entBaseInfoService.insert(entBaseInfoModel);
				}
			}
		}

		in.setUpdateBy(userId);
		in.setUpdateDt(new Date());
		inveInfoService.update(in);
		inveResumeModel.setUpdateBy(userId);
		inveResumeModel.setUpdateDt(new Date());
		inveResumeService.update(inveResumeModel);
	}
}