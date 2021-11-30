package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjContractFileDebtModel;
import com.ppmg.ei.model.ProjContractFileModel;
import com.ppmg.ei.model.ProjContractFileQuitModel;
import com.ppmg.ei.model.ProjContractFileQuitUtilModel;
import com.ppmg.ei.service.ProjContractFileDebtService;
import com.ppmg.ei.service.ProjContractFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("projContractFileService")
@Service(interfaceClass = ProjContractFileService.class)
public class ProjContractFileServiceImpl extends BaseServiceImpl<ProjContractFileModel> implements ProjContractFileService {

    public ProjContractFileServiceImpl() {
        this.setNamespace("ProjContractFile");
    }

    @Resource
    private ProjContractFileService projContractFileService;

    @Resource
    private ProjContractFileDebtService projContractFileDebtService;

    @Reference(check = false)
    private SerialnoService serialnoService;

    @Override
    public List<ProjContractFileModel> selectByProjIdList(String projId) {
        return this.selectList("selectByProjIdList", projId);

    }

    @Override
    public Double getSumSignAmount(String projId, String inveId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("projId", projId);
        param.put("inveId", inveId);
        return (Double) this.selectOne("getSumSignAmount", param);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void insertById(ProjContractFileModel projContractFileModel, ProjContractFileDebtModel projContractFileDebtModel, String userId) throws Exception {
        projContractFileModel.setCreateDt(new Date());
        projContractFileModel.setCreateBy(userId);
        projContractFileModel.setUpdateBy(userId);
        projContractFileModel.setUpdateDt(new Date());
        projContractFileModel.setSignQuit("1");
        projContractFileService.insert(projContractFileModel);
        if ("11".equals(projContractFileModel.getFileType())) {
            projContractFileDebtModel.setStatus("0");
            projContractFileDebtModel.setCreateDt(new Date());
            projContractFileDebtModel.setCreateBy(userId);
            projContractFileDebtModel.setUpdateBy(userId);
            projContractFileDebtModel.setUpdateDt(new Date());
            projContractFileDebtModel.setFileId(projContractFileModel.getFileId());
            projContractFileDebtService.insert(projContractFileDebtModel);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateById(ProjContractFileModel projContractFileModel, ProjContractFileDebtModel projContractFileDebtModel, String userId) throws Exception {
        projContractFileModel.setUpdateBy(userId);
        projContractFileModel.setUpdateDt(new Date());
        if("1".equals(projContractFileModel.getIsGov()) && !"1".equals(projContractFileModel.getFileType())){
            projContractFileService.update("updateSignNull",projContractFileModel);
        }else{
            projContractFileService.update(projContractFileModel);
        }

        ProjContractFileDebtModel projContractFileDebt = projContractFileDebtService.selectById(projContractFileModel.getFileId());
        if (projContractFileDebt != null) {
            if (StringUtils.isNotEmpty(projContractFileModel.getFileType())) {
                if ("11".equals(projContractFileModel.getFileType())) {
                    projContractFileDebtModel.setUpdateBy(userId);
                    projContractFileDebtModel.setUpdateDt(new Date());
                    projContractFileDebtService.update(projContractFileDebtModel);
                } else {
                    projContractFileDebtService.deleteById(projContractFileModel.getFileId());
                }
            }

        } else {
            if ("11".equals(projContractFileModel.getFileType())) {
                projContractFileDebtModel.setStatus("0");
                projContractFileDebtModel.setCreateDt(new Date());
                projContractFileDebtModel.setCreateBy(userId);
                projContractFileDebtModel.setUpdateBy(userId);
                projContractFileDebtModel.setUpdateDt(new Date());
                projContractFileDebtModel.setFileId(projContractFileModel.getFileId());
                projContractFileDebtService.insert(projContractFileDebtModel);
            }
        }
    }

    @Override
    public <E> PageInfo<E> selectByListPage(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectByListPage", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }


    @Override
    public <E> PageInfo<E> selectPageListQuit(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getCurrPage(), searchCondition.getPageSize());
        List<E> list = this.baseDao.selectList("ProjContractFile", "selectEiAttachmentQuitMapper", searchCondition);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override
    public void insertIntoQuit(String userId, ProjContractFileQuitModel projContractFileQuitModel) {

        List<ProjContractFileQuitModel> list = new ArrayList<>();
        projContractFileQuitModel.setCreateDt(new Date());
        projContractFileQuitModel.setCreateBy(userId);
        projContractFileQuitModel.setUpdateBy(userId);
        projContractFileQuitModel.setUpdateDt(new Date());

        //projContractFileQuitModel.setStatus("0");
        list.add(projContractFileQuitModel);
        this.insert("insertIntoQuitMapper", list);
    }

    @Override
    public void updateIntoQuit(String userId, ProjContractFileQuitModel projContractFileQuitModel) {
        projContractFileQuitModel.setUpdateBy(userId);
        projContractFileQuitModel.setUpdateDt(new Date());
        this.update("updateIntoQuitMapper", projContractFileQuitModel);
    }

    /**
     * 投项目新增
     *
     * @return
     */
    @Override
    public void insertProjContractFileProject(String userId, ProjContractFileQuitModel Model) {
        List<ProjContractFileQuitModel> list = new ArrayList<>();
        List<ProjContractFileQuitUtilModel> list_s = Model.getProjContractFileQuitUtilModel();
        if (list_s.size() > 0) {
            ProjContractFileQuitModel n = null;
            //for (ProjContractFileQuitUtilModel fl : list_s) {
            String Id = null;
            for (int i = 0; i < list_s.size(); i++) {
                n = new ProjContractFileQuitModel();
                if (i == 0) {
                    //原主键保留
                    Id = Model.getFileId();
                } else {
                    Id = UUID.randomUUID().toString();
                }
                n.setFileId(Id);
                //退出决策id
                n.setAppId(Model.getAppId());
                n.setProjId(Model.getProjId());
                n.setFileTitle(list_s.get(i).getFileTitle());
                n.setFileType(list_s.get(i).getFileType());
                n.setThisAgreeAmont(Model.getThisAgreeAmont());
                n.setThisAgreeAmontCurr(Model.getThisAgreeAmontCurr());
                n.setMajorTerm(Model.getMajorTerm());
                n.setContractFile(Model.getContractFile());
                n.setRemark(Model.getRemark());
                n.setStatus(Model.getStatus());
                //是签署合同，还是退出合同（0：退出；1：签署）
                n.setSignQuit("0");
                list.add(n);
            }
        }
        for (ProjContractFileQuitModel pcfm : list) {
            if ("0".equals(Model.getTag())) {
                pcfm.setProcessInstId(Model.getProcessInstId());
            }
            projContractFileService.insertIntoQuit(userId, pcfm);
        }
    }

    /**
     * 投项目新增
     *
     * @return
     */
    @Override
    public void insertProjContractFileProjectBy(String userId, ProjContractFileQuitModel Model) {
        List<ProjContractFileQuitModel> list = new ArrayList<>();
        List<ProjContractFileQuitUtilModel> list_s = Model.getProjContractFileQuitUtilModel();
        if (list_s!=null&&list_s.size() > 0) {
            ProjContractFileQuitModel n = null;
            for (ProjContractFileQuitUtilModel fl : list_s) {
                n = new ProjContractFileQuitModel();
                String  Id = UUID.randomUUID().toString();
                n.setFileId(Id);
                //退出决策id
                n.setAppId(Model.getAppId());
                n.setProjId(Model.getProjId());
                n.setFileTitle(fl.getFileTitle());
                n.setFileType(fl.getFileType());
                n.setThisAgreeAmont(Model.getThisAgreeAmont());
                n.setThisAgreeAmontCurr(Model.getThisAgreeAmontCurr());
                n.setMajorTerm(Model.getMajorTerm());
                n.setContractFile(Model.getContractFile());
                n.setRemark(Model.getRemark());
                n.setStatus(Model.getStatus());
                //是签署合同，还是退出合同（0：退出；1：签署）
                n.setSignQuit("0");
                list.add(n);
            }
        }
        for (ProjContractFileQuitModel pcfm: list) {
            if("0".equals(Model.getTag())){
                pcfm.setProcessInstId(Model.getProcessInstId());
            }
            projContractFileService.insertIntoQuit(userId,pcfm);
        }
    }

    /**
     * 投项目更新
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateProjContractFileProject(String userId, ProjContractFileQuitModel Model, ProjContractFileQuitModel one) {
        if (one.getAppId().equals(Model.getAppId())) {
            //先删除
            projContractFileService.deleteIntoQuitAppId(Model.getAppId());
        } else if (one != null) {
            //删除旧的
            projContractFileService.deleteIntoQuitAppId(one.getAppId());
        }
        //重新插入数据
        List<ProjContractFileQuitModel> list = new ArrayList<>();
        List<ProjContractFileQuitUtilModel> list_s = Model.getProjContractFileQuitUtilModel();
        if (list_s.size() > 0) {
            ProjContractFileQuitModel n = null;
            //for (ProjContractFileQuitUtilModel fl : list_s) {
            String Id = null;
            for (int i = 0; i < list_s.size(); i++) {
                if (i == 0) {
                    //原主键保留
                    Id = Model.getFileId();
                } else {
                    Id = UUID.randomUUID().toString();
                }

                n = new ProjContractFileQuitModel();
                n.setFileId(Id);
                // 退出决策id
                n.setAppId(Model.getAppId());
                n.setProjId(Model.getProjId());
                n.setFileTitle(list_s.get(i).getFileTitle());
                n.setFileType(list_s.get(i).getFileType());
                n.setThisAgreeAmont(Model.getThisAgreeAmont());
                n.setThisAgreeAmontCurr(Model.getThisAgreeAmontCurr());
                n.setMajorTerm(Model.getMajorTerm());
                n.setContractFile(Model.getContractFile());
                n.setRemark(Model.getRemark());
                if ("3".equals(Model.getTag())) {
                    //审批中
                    n.setStatus("1");
                    //合同签订时间
                    n.setSignDt(Model.getSignDt());
                    //最终版合同
                    n.setFinalFile(Model.getFinalFile());
                } else if ("1".equals(Model.getTag())) {
                    //草稿
                    n.setStatus("0");
                } else if ("0".equals(Model.getTag())) {
                    //审批中
                    n.setStatus("1");
                }
                //是签署合同，还是退出合同（0：退出；1：签署）
                n.setSignQuit("0");
                list.add(n);
            }
        }
        for (ProjContractFileQuitModel m : list) {
            m.setProcessInstId(one.getProcessInstId());
            projContractFileService.insertIntoQuit(userId, m);
        }
    }


    @Override
    public void deleteIntoQuit(String fileId) {
        this.delete("deleteIntoQuitMapper", fileId);
    }

    @Override
    public void deleteIntoQuitAppId(String appId) {
        this.delete("deleteIntoQuitAppIdMapper", appId);
    }

    @Override
    public ProjContractFileQuitModel selectDetailes(String fileId) {
        return (ProjContractFileQuitModel) this.selectOne("selectQuitDetails", fileId);
    }

    @Override
    public ProjContractFileQuitModel selectDetailesAppId(String appId) {
        return (ProjContractFileQuitModel) this.selectOne("selectQuitDetailsAppid", appId);
    }

    @Override
    public List<ProjContractFileQuitModel> selectDetailesAppIdList(String appId) {
        return this.selectList("selectQuitDetailsAppid", appId);
    }

    @Override
    public String selectGetName(String id) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("ID", id);
        return (String) this.selectOne("selectGetName", param);
    }
}