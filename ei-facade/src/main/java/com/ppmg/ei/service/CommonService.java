package com.ppmg.ei.service;


import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.CommonModel;

public interface CommonService extends BaseService<CommonModel>  {

	/**
	 * 更新表字段值
	 * @param model
	 * @return
	 */
	public void updateTableDataInfo(CommonModel model);

	/**
	 * 删除表数据
	 * @param model
	 * @return
	 */
	public void deleteTableDataById(CommonModel model);

}