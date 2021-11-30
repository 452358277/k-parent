package com.ppmg.ei.service.impl;

import com.ppmg.ei.model.AgroupModel;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.service.AgroupService;

import java.util.ArrayList;
import java.util.List;

@Component("agroupService")
@Service(interfaceClass = AgroupService.class)
public class AgroupServiceImpl extends BaseServiceImpl<AgroupModel> implements AgroupService {

	public AgroupServiceImpl(){
		this.setNamespace("Agroup");
	}

	@Override
	public AgroupModel getTreeList() {
		List<AgroupModel> list1 = this.selectList("getTreeList", "");
		List<AgroupModel> list2 = list1;
		List<AgroupModel> list = new ArrayList<AgroupModel>();
		for (AgroupModel model : list1) {
			List<AgroupModel> children = new ArrayList<AgroupModel>();
			if(!"-1".equals(model.getId())){
				for(AgroupModel m : list2){
					if(String.valueOf(m.getFatherid()).equals(String.valueOf(model.getId()))){
						children.add(m);
					}
				}
				if(children.size() > 0){
					model.setChildren(children);
				}
			}
			list.add(model);
		}
		if(list.size() > 0){
			return list.get(0);
		}else{
			return new AgroupModel();
		}

	}
}