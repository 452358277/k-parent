package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.RegulationApproveCommModel;
import com.ppmg.ei.model.RegulationApproveModel;
import com.ppmg.ei.service.RegulationApproveCommService;
import com.ppmg.ei.service.RegulationApproveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("regulationApproveService")
@Service(interfaceClass = RegulationApproveService.class,timeout = 900000,retries = 0)
public class RegulationApproveServiceImpl extends BaseServiceImpl<RegulationApproveModel> implements RegulationApproveService {

	public RegulationApproveServiceImpl(){
		this.setNamespace("RegulationApprove");
	}



	@Resource
	private RegulationApproveService regulationApproveService;


	@Resource
	private RegulationApproveCommService regulationApproveCommService;



	@Reference(check = false)
	private SerialnoService serialnoService;


	@Override
	public void saveInfo(RegulationApproveModel model, List<RegulationApproveCommModel> list) {
		model.setCreateBy(model.getUpdateBy());
		model.setCreateDt(new Date());
		regulationApproveService.insert(model);
		if(list!=null && !list.isEmpty()){
			for(RegulationApproveCommModel regulationApproveCommModel:list){
				String commId = serialnoService.getSequence("EI.TZ_T_REGULATION_APPROVE_COMM");
				regulationApproveCommModel.setCommId(commId);
				regulationApproveCommModel.setIraId(model.getIraId());
				regulationApproveCommModel.setCreateBy(model.getUpdateBy());
				regulationApproveCommModel.setCreateDt(new Date());
				regulationApproveCommModel.setUpdateBy(model.getUpdateBy());
				regulationApproveCommModel.setUpdateDt(new Date());
				regulationApproveCommModel.setIsDelete("0");
				regulationApproveCommService.insert(regulationApproveCommModel);
			}

		}


	}


	@Override
	public void updateInfo(RegulationApproveModel model, List<RegulationApproveCommModel> list) {
		regulationApproveService.update(model);
		if(list!=null && !list.isEmpty()){
			//先删除原来的人在新增
			if(StringUtils.isNotEmpty(model.getIraId())){
				RegulationApproveCommModel re=new RegulationApproveCommModel();
				re.setIraId(model.getIraId());
				regulationApproveCommService.delete(re);
			}
			for(RegulationApproveCommModel regulationApproveCommModel:list){
				String commId = serialnoService.getSequence("EI.TZ_T_REGULATION_APPROVE_COMM");
				regulationApproveCommModel.setCommId(commId);
				regulationApproveCommModel.setIraId(model.getIraId());
				regulationApproveCommModel.setCreateBy(model.getUpdateBy());
				regulationApproveCommModel.setCreateDt(new Date());
				regulationApproveCommModel.setUpdateBy(model.getUpdateBy());
				regulationApproveCommModel.setUpdateDt(new Date());
				regulationApproveCommModel.setIsDelete("0");
				regulationApproveCommService.insert(regulationApproveCommModel);
			}

		}



	}

	@Override
	public void deleteByIds(List<String> ids, String userId) {

		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("ids", ids);
		paramsMap.put("userId", userId);
		paramsMap.put("isDelete", "9");
		regulationApproveService.update("deleteByIds", paramsMap);

	}
}