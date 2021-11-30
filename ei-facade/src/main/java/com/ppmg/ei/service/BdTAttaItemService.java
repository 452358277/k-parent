package com.ppmg.ei.service;

import java.util.List;
import java.util.Map;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.BdTAttaItemModel;

public interface BdTAttaItemService extends BaseService<BdTAttaItemModel>  {
	
	/**
	 * 获取主键值
	 * @return
	 */
	public String getAttaItemId();

	/**
	 * 获取附件分类列表
	 * @param
	 * @return
	 */
	public List<BdTAttaItemModel> getAttaItemList(Map<String, String> map);

	/**
	 * 金财文件库EI_T_ATTACHMENT表初始化数据
	 * @param projId
	 * @return void
	 * @author zhaokuiyu
	 * @date 2020/2/13 11:52
	 * @creed: The best time to plant a tree is ten years ago, followed by now
	 */
	public void addEiTAttachment(String projId,String projType,String userId)throws Exception;
	/**
	 * 删除EI_T_ATTACHMENT表初始化数据
	 * @param projId
	 * @return void
	 * @author zhaokuiyu
	 * @date 2020/2/13 15:19
	 * @creed: The best time to plant a tree is ten years ago, followed by now
	 */
	public void delectEiTAttachment(String projId);
	/**
	 * 查询EI_T_ATTACHMENT表初始化数据
	 * @param projId
	 * @return java.util.List<com.ppmg.ei.model.BdTAttaItemModel>
	 * @author zhaokuiyu
	 * @date 2020/2/13 15:56
	 * @creed: The best time to plant a tree is ten years ago, followed by now
	 */
	public List<BdTAttaItemModel> selectList(String projId);


}