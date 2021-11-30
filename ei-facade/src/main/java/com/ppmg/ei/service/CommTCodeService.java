package com.ppmg.ei.service;

import java.util.List;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.CommTCodeModel;

public interface CommTCodeService extends BaseService<CommTCodeModel>  {

	/**
	 * 通过parentId和codeValue查找单个字典对象
	 * @param parentId
	 * @param codeValue
	 * @return
	 */
	CommTCodeModel getCodeNameByValue(String parentId, String codeValue);

	/**
	 * 通过parentId查找多个字典对象
	 * @param parentId
	 * @return
	 */
	List<CommTCodeModel> selectByParentId(String parentId);

	/**
	 * 通过parentId更新码值266
	 * @param model
	 * @return
	 */
	void updateByParentId266(CommTCodeModel model);

	/**
	 * 通过parentId更新码值279
	 * @param model
	 * @return
	 */
	void updateByParentId279(CommTCodeModel model);

}