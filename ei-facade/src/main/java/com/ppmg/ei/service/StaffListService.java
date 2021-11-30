package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.ProjAssignModel;
import com.ppmg.ei.model.StaffListModel;

import java.util.List;

public interface StaffListService extends BaseService<StaffListModel>  {

    void insertAssAndStaffList(List<StaffListModel> list, ProjAssignModel projAssignModel,String userId)throws Exception;

    void updateAssAndStaffList(List<StaffListModel> list, ProjAssignModel projAssignModel,String userId)throws Exception;

    List<StaffListModel> selectListByUid(StaffListModel model);

    void delateById(String assignId,String userId) throws Exception;
}