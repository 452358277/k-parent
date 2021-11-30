package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjAppInfoSnapshotModel;
import com.ppmg.ei.service.ProjAppInfoSnapshotService;

/** 
 * 描述 [Service.impl] 
 * @author null
 * @date 2019-10-16 17:45 
 */ 
@Component("projAppInfoSnapshotService")
@Service(interfaceClass = ProjAppInfoSnapshotService.class)
public class ProjAppInfoSnapshotServiceImpl extends BaseServiceImpl<ProjAppInfoSnapshotModel> implements ProjAppInfoSnapshotService {

	public ProjAppInfoSnapshotServiceImpl(){
		this.setNamespace("ProjAppInfoSnapshot");
	}

}