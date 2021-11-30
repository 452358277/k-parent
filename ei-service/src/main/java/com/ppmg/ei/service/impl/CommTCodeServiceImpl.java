package com.ppmg.ei.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommTCodeModel;
import com.ppmg.ei.service.CommTCodeService;

@Component("commTCodeService")
@Service(interfaceClass = CommTCodeService.class)
public class CommTCodeServiceImpl extends BaseServiceImpl<CommTCodeModel> implements CommTCodeService {

	public CommTCodeServiceImpl(){
		this.setNamespace("CommTCode");
	}

	@Override
	public CommTCodeModel getCodeNameByValue(String parentId, String codeValue) {
		
		CommTCodeModel model = new CommTCodeModel();
		model.setParentId(parentId);
		model.setCodeValue(codeValue);
		return this.selectBy(model);
	}

	@Override
	public List<CommTCodeModel> selectByParentId(String parentId) {
		CommTCodeModel model = new CommTCodeModel();
		model.setParentId(parentId);
		return this.selectList(model);
	}

	@Override
	public void updateByParentId266(CommTCodeModel model) {
		this.update("updateByParentId266",model);
	}

	@Override
	public void updateByParentId279(CommTCodeModel model) {
		this.update("updateByParentId279",model);
	}

}