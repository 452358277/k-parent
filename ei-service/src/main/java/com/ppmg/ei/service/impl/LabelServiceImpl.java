package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.bean.LabelSearchBean;
import com.ppmg.ei.model.CommTLabelItemModel;
import com.ppmg.ei.model.EntLabelModel;
import com.ppmg.ei.service.CommTLabelItemService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.LabelModel;
import com.ppmg.ei.service.LabelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("labelService")
@Service(interfaceClass = LabelService.class)
public class LabelServiceImpl extends BaseServiceImpl<LabelModel> implements LabelService {

	public LabelServiceImpl(){
		this.setNamespace("Label");
	}

	@Resource
	private CommTLabelItemService commTLabelItemService;


	@Override
	public List<LabelModel> selectListByLabel(String entId) {
		return this.selectList("selectListByLabel",entId);

	}

	@Override
	public Map<String, Object> selectListByLabels(String entId) {
		return (Map<String,Object>)this.selectOne("selectListByLabels", entId);
	}

	@Override
	public List<LabelModel> selectListByItem() {
		return this.selectList("selectListByItem",null);
	}

	@Override
	public void deleteBy(String labelId) {
		CommTLabelItemModel commTLabelItemModel=new CommTLabelItemModel();
		commTLabelItemModel.setLabelId(labelId);
		commTLabelItemService.delete(commTLabelItemModel);
		this.deleteById(labelId);
	}
}