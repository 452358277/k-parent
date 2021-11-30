package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjContractFileModel;
import com.ppmg.ei.service.ProjContractFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjContractInfoModel;
import com.ppmg.ei.service.ProjContractInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("projContractInfoService")
@Service(interfaceClass = ProjContractInfoService.class)
public class ProjContractInfoServiceImpl extends BaseServiceImpl<ProjContractInfoModel> implements ProjContractInfoService {

	public ProjContractInfoServiceImpl(){
		this.setNamespace("ProjContractInfo");
	}

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private ProjContractInfoService projContractInfoService;

	@Resource
	private ProjContractFileService projContractFileService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByproj(ProjContractInfoModel model,List<ProjContractFileModel> list, String userId,String majorTerm,String contractFile) throws Exception {
			if(list!=null&&list.size()>0){
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				model.setUpdateBy(userId);
				model.setUpdateDt(new Date());
				projContractInfoService.insert(model);
				for(ProjContractFileModel projContractFileModel:list){
					String  fileId=serialnoService.getSequence("EI.TZ_T_PROJ_CONTRACT_FILE");
					projContractFileModel.setFileId(fileId);
					projContractFileModel.setStatus(model.getStatus());
					projContractFileModel.setCreateDt(new Date());
					projContractFileModel.setCreateBy(userId);
					projContractFileModel.setUpdateBy(userId);
					projContractFileModel.setUpdateDt(new Date());
					//projContractFileModel.setFileId(projContractFileModel.getFileId());
					projContractFileModel.setFileInfoId(model.getId());
					projContractFileModel.setProjId(model.getProjId());
					projContractFileModel.setMajorTerm(majorTerm);
					projContractFileModel.setContractFile(contractFile);
					projContractFileModel.setRemark(model.getRemark());
					projContractFileModel.setSignAmount(model.getSignAmount());
					projContractFileModel.setSignAmountCurr(model.getSignAmountCurr());
					projContractFileModel.setSignQuit("1");
					projContractFileService.insert(projContractFileModel);
				}
			}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByproj(ProjContractInfoModel model, List<ProjContractFileModel> list, String userId,String majorTerm,String contractFile,String groupId, Date signDt, String  finalFile) throws Exception {
		model.setUpdateBy(userId);
		model.setUpdateDt(new Date());
		projContractInfoService.update(model);
		if(StringUtils.isNotEmpty(model.getId())){
			ProjContractFileModel projContractFile=new ProjContractFileModel();
			projContractFile.setFileInfoId(model.getId());
			projContractFileService.delete(projContractFile);
			for(ProjContractFileModel projContractFileModel:list){
				String  fileId=serialnoService.getSequence("EI.TZ_T_PROJ_CONTRACT_FILE");
				projContractFileModel.setFileId(fileId);
				projContractFileModel.setStatus(model.getStatus());
				projContractFileModel.setCreateDt(new Date());
				projContractFileModel.setCreateBy(userId);
				projContractFileModel.setUpdateBy(userId);
				projContractFileModel.setUpdateDt(new Date());
				//projContractFileModel.setFileId(projContractFileModel.getFileId());
				projContractFileModel.setFileInfoId(model.getId());
				projContractFileModel.setMajorTerm(majorTerm);
				projContractFileModel.setContractFile(contractFile);
				projContractFileModel.setRemark(model.getRemark());
				projContractFileModel.setGroupId(groupId);
				projContractFileModel.setProjId(model.getProjId());
				projContractFileModel.setSignAmount(model.getSignAmount());
				projContractFileModel.setSignAmountCurr(model.getSignAmountCurr());
				projContractFileModel.setSignDt(signDt);
				projContractFileModel.setFinalFile(finalFile);
				projContractFileService.insert(projContractFileModel);
			}
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByInfo(String id, String userId) throws Exception {
		ProjContractInfoModel proj=new ProjContractInfoModel();
		proj.setId(id);
		proj.setStatus("9");
		proj.setUpdateDt(new Date());
		proj.setUpdateBy(userId);
		projContractInfoService.update(proj);

		ProjContractFileModel projContractFileModel=new ProjContractFileModel();
		projContractFileModel.setStatus("9");
		projContractFileModel.setFileInfoId(id);
		projContractFileModel.setUpdateDt(new Date());
		projContractFileModel.setUpdateBy(userId);
		projContractFileService.update("updateStatus",projContractFileModel);
	}

	@Override
	public <E> PageInfo<E> selectListByInfoPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListByInfoPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
}