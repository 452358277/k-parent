package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.ConditionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("conditionService")
@Service(interfaceClass = ConditionService.class)
public class ConditionServiceImpl extends BaseServiceImpl<ConditionModel> implements ConditionService {

    /*@Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;*/

    public ConditionServiceImpl() {
        this.setNamespace("Condition");
    }

    //#########征集信息#############
    @Override
    public PageInfo<ConditionInfoModel> selectListPageInfo(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<ConditionInfoModel> list = this.baseDao.selectList("Condition", "selectListPage2", searchCondition);
        PageInfo<ConditionInfoModel> page = new PageInfo(list);
        return page;
    }
   @Override
    public ConditionModel selectConditionInfoDetails(String conditionId) {
        return (ConditionModel) this.selectOne("getAllConditionInfoItim", conditionId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addConditionInfos(ConditionInfoModel conditionInfoModel) {
        //征集信息info
        //String infoIds = UUID.randomUUID().toString();
        String infoIds = conditionInfoModel.getInfoId();
        List<ConditionInfoModel> listInfo = new ArrayList<>();
        if (conditionInfoModel != null) {
            // 根据公开征集id查询征集信息
            conditionInfoModel.setInfoId(infoIds);
            conditionInfoModel.setCreateBy(conditionInfoModel.getUserId());
            conditionInfoModel.setUpdateBy(conditionInfoModel.getUserId());
            if (StringUtils.isEmpty(conditionInfoModel.getStatus())) {
                conditionInfoModel.setStatus("0");
            }
            listInfo.add(conditionInfoModel);
            this.insert("insertConditionInfo", listInfo);
        }
        //征集信息item
        List<ConditionItemModel> list_item = conditionInfoModel.getConditionItemModel();
        if (list_item != null && list_item.size() > 0) {
            for (ConditionItemModel it : list_item) {
                String itemId = UUID.randomUUID().toString();
                it.setItemId(itemId);
                it.setInfoId(infoIds);
                it.setCreateBy(conditionInfoModel.getUserId());
                it.setUpdateBy(conditionInfoModel.getUserId());
                //保存file--start
                List<ConditionFileModel> file = it.getConditionFileModels();
                if (file != null && file.size() > 0) {
                    for (ConditionFileModel cf : file) {
                        cf.setItemId(itemId);
                        cf.setCreateBy(conditionInfoModel.getUserId());
                        cf.setUpdateBy(conditionInfoModel.getUserId());
                    }
                    this.insert("insertConditionFile", file);
                }
                //保存file--end
            }
            //增加item
            this.insert("insertConditionItem", list_item);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateConditionInfos(ConditionInfoModel conditionInfoModel) {
        //更新info
        String infoId = conditionInfoModel.getInfoId();
        if (conditionInfoModel != null) {
            this.update("updateConditionInfo", conditionInfoModel);
        }
        //更新item，file
        List<ConditionItemModel> item = conditionInfoModel.getConditionItemModel();
        String infoIds = conditionInfoModel.getInfoId();
        if (item != null && item.size() > 0) {
            //先删除
            List<ConditionItemModel> itSerch = this.selectList("selectByItemIdOne2", infoId);
            if (itSerch != null && itSerch.size() > 0) {
                for (ConditionItemModel cm : itSerch) {
                    String itemId = cm.getItemId();
                    //删除file（征集材料）
                    this.delete("deleteConditionFile", itemId);
                }
                //删除item，基金管理人
                this.delete("deleteByItemId", infoId);
            }else{
                //删除item，基金管理人
                this.delete("deleteByItemId", infoId);
            }
            //在增加
            for (ConditionItemModel it : item) {
                String itemId = UUID.randomUUID().toString();
                it.setItemId(itemId);
                it.setInfoId(infoIds);
                it.setCreateBy(conditionInfoModel.getUserId());
                it.setUpdateBy(conditionInfoModel.getUserId());
                //保存file--start
                List<ConditionFileModel> file = it.getConditionFileModels();
                if (file != null && file.size() > 0) {
                    for (ConditionFileModel cf : file) {
                        cf.setItemId(itemId);
                        cf.setCreateBy(conditionInfoModel.getUserId());
                        cf.setUpdateBy(conditionInfoModel.getUserId());
                    }
                    this.insert("insertConditionFile", file);
                }
                //保存file--end
            }
            //增加item
            this.insert("insertConditionItem", item);
        }else{
            //先删除
            List<ConditionItemModel> itSerch = this.selectList("selectByItemIdOne2", infoId);
            if (itSerch != null && itSerch.size() > 0) {
                for (ConditionItemModel cm : itSerch) {
                    String itemId = cm.getItemId();
                    //删除file（征集材料）
                    this.delete("deleteConditionFile", itemId);
                }
                //删除item，基金管理人
                this.delete("deleteByItemId", infoId);
            }else{
                //删除item，基金管理人
                this.delete("deleteByItemId", infoId);
            }
        }
    }

    @Override//征集信息删除
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteConditionInfos(String infoId) {
        if (infoId != null) {
            List<ConditionItemModel> it = this.selectList("selectByItemIdOne2", infoId);
            if (it != null && it.size() > 0) {
                for (ConditionItemModel cm : it) {
                    String itemId = cm.getItemId();
                    //删除file（征集材料）
                    this.delete("deleteConditionFile", itemId);
                }
                //删除item，基金管理人
                this.delete("deleteByItemId", infoId);
            }
            //删除info,征集信息
            this.delete("deleteByInfoId", infoId);
        }
    }

    @Override
    public ConditionInfoModel selectOneConditionInfo(String conditionId) {
        ConditionInfoModel conditionInfoModel = (ConditionInfoModel) this.selectOne("selectByInfoId", conditionId);
        return conditionInfoModel;
    }

    @Override
    public ConditionInfoModel selectOneConditionInfoT(String infoId) {
        ConditionInfoModel conditionInfoModel = (ConditionInfoModel) this.selectOne("selectByInfoIdT", infoId);
        return conditionInfoModel;
    }


    //#########基金管理人#############
    @Override
    public PageInfo<ConditionItemModel> selectListPageItem(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<ConditionItemModel> list = this.baseDao.selectList("Condition", "selectListPageItems", searchCondition);
        PageInfo<ConditionItemModel> page = new PageInfo(list);
        return page;
    }

    @Override
    public ConditionItemModel selectConditionItemDetailService(String itemId) {
        return (ConditionItemModel) this.selectOne("selectConditionItemDetails", itemId);
    }

    @Override//增加
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertConditionItems(ConditionItemModel conditionItemModel) {
        List<ConditionItemModel> list = new ArrayList<>();
        String itemIds = UUID.randomUUID().toString();
        conditionItemModel.setItemId(itemIds);//主键
        list.add(conditionItemModel);
        //item
        this.insert("insertConditionItem", list);
        //file
        List<ConditionFileModel> listFile = conditionItemModel.getConditionFileModels();
        if (listFile != null && listFile.size() > 0) {
            for (ConditionFileModel cf : listFile) {
                cf.setItemId(itemIds);
                if (StringUtils.isEmpty(cf.getCreateBy())) {
                    cf.setCreateBy("创建人");
                }
                if (StringUtils.isEmpty(cf.getUpdateBy())) {
                    cf.setUpdateBy("更新人");
                }
            }
            this.insert("insertConditionFile", listFile);
        }
        return itemIds;
    }

    @Override//修改
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateConditionItems(ConditionItemModel conditionItemModel) {
        int count = 0;
        //item
        count += this.update("updateConditionItem", conditionItemModel);
        //file
        List<ConditionFileModel> listFile = conditionItemModel.getConditionFileModels();
        if (listFile != null && listFile.size() > 0) {
            for (ConditionFileModel cf : listFile) {
                count += this.update("updateConditionFile", cf);
            }
        }
        return count;
    }

    @Override//基金管理人删除
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteConditionItems(String[] arr) {
        int count = 0;
        //item
        count += this.delete("deleteConditionItem", arr);
        //file
        for (int i = 0; i < arr.length; i++) {
            String itemId = arr[i];
            count += this.delete("deleteConditionFile", itemId);
        }
        return count;
    }

    @Override//征集材料详情
    public ConditionFileModel selectConditionFileDetails(String fileId) {
        return (ConditionFileModel) this.selectOne("selectConditionFileDetail", fileId);
    }

    @Override
    public PageInfo<FundInfoModel> selectListPageFundInfo(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<FundInfoModel> list = this.baseDao.selectList("Condition", "selectListPageFundInfo", searchCondition);
        PageInfo<FundInfoModel> page = new PageInfo(list);
        return page;
    }

    @Override
    public <E> PageInfo<E> selectAdmins(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<E> list = this.baseDao.selectList("Condition", "selectListAdmins", searchCondition);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }


    @Override
    public Boolean next(String process_key, String bizKey, String task_Number) {

        return true;
    }

}
