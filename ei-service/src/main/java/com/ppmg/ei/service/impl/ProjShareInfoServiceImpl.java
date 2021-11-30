package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.ProjShareInfoModel;
import com.ppmg.ei.model.TzTProjOwnershipInfoModel;
import com.ppmg.ei.service.ProjShareInfoService;
import com.ppmg.ei.service.TzTProjOwnershipInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("projShareInfoService")
@Service(interfaceClass = ProjShareInfoService.class)
public class ProjShareInfoServiceImpl extends BaseServiceImpl<ProjShareInfoModel> implements ProjShareInfoService {

	public ProjShareInfoServiceImpl(){
		this.setNamespace("ProjShareInfo");
	}


	@Resource
	private ProjShareInfoService projShareInfoService;

	@Resource
	private TzTProjOwnershipInfoService tzTProjOwnershipInfoService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	public List<ProjShareInfoModel> selectListShare(ProjShareInfoModel model) {
		return this.selectList("selectListShare",model);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateInfo(List<ProjShareInfoModel> listVo, TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel)throws Exception {
		tzTProjOwnershipInfoService.update(tzTProjOwnershipInfoModel);
		//先删除原来的
        if(StringUtils.isNotEmpty(tzTProjOwnershipInfoModel.getOsId())){
			ProjShareInfoModel moShare=new ProjShareInfoModel();
			moShare.setOsId(tzTProjOwnershipInfoModel.getOsId());
			projShareInfoService.delete(moShare);
		}
		if(listVo!=null && !listVo.isEmpty()){
			for(ProjShareInfoModel projShareInfoModel:listVo){
				projShareInfoModel.setCreateBy(tzTProjOwnershipInfoModel.getUpdateBy());
				projShareInfoModel.setCreateDt(new Date());
				projShareInfoModel.setUpdateBy(tzTProjOwnershipInfoModel.getUpdateBy());
				projShareInfoModel.setUpdateDt(new Date());
				String pkId = serialnoService.getSequence("EI.TZ_T_PROJ_SHARE_INFO");
				projShareInfoModel.setPkId(pkId);
				projShareInfoModel.setOsId(tzTProjOwnershipInfoModel.getOsId());
				projShareInfoModel.setStatus("0");
			}
			projShareInfoService.insertBatch(listVo);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void saveInfo(List<ProjShareInfoModel> listVo, TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel) throws Exception {
		tzTProjOwnershipInfoService.insert(tzTProjOwnershipInfoModel);
		if(listVo!=null && !listVo.isEmpty()){
			for(ProjShareInfoModel projShareInfoModel:listVo){
				    projShareInfoModel.setCreateBy(tzTProjOwnershipInfoModel.getUpdateBy());
				    projShareInfoModel.setCreateDt(new Date());
					projShareInfoModel.setUpdateBy(tzTProjOwnershipInfoModel.getUpdateBy());
					projShareInfoModel.setUpdateDt(new Date());
				    String pkId = serialnoService.getSequence("EI.TZ_T_PROJ_SHARE_INFO");
				    projShareInfoModel.setPkId(pkId);
				    projShareInfoModel.setOsId(tzTProjOwnershipInfoModel.getOsId());
				    projShareInfoModel.setStatus("0");
				    projShareInfoService.insert(projShareInfoModel);
			}

		}
	}
}