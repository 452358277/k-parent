package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.ProjAssignModel;
import com.ppmg.ei.service.ProjAssignService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.StaffListModel;
import com.ppmg.ei.service.StaffListService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("staffListService")
@Service(interfaceClass = StaffListService.class)
public class StaffListServiceImpl extends BaseServiceImpl<StaffListModel> implements StaffListService {

	public StaffListServiceImpl(){
		this.setNamespace("StaffList");
	}

	@Resource
	private StaffListService staffListService;

	@Resource
	private ProjAssignService projAssignService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertAssAndStaffList(List<StaffListModel> list, ProjAssignModel projAssignModel , String userId) throws Exception{
		projAssignModel.setCreateBy(userId);
		projAssignModel.setCreateDt(new Date());
		projAssignModel.setUpdateBy(userId);
		projAssignModel.setUpdateDt(new Date());
		projAssignService.insert(projAssignModel);
		if(list!=null&&list.size()>0){
			  for(StaffListModel staffList:list){
				 String pkId= serialnoService.getSequence("EI.TZ_T_YH_STAFF_LIST");
				  staffList.setPkId(pkId);
				  staffList.setCreateBy(userId);
				  staffList.setCreateDt(new Date());
				  staffList.setUpdateBy(userId);
				  staffList.setUpdateDt(new Date());
				  staffList.setProjId(projAssignModel.getProjId());
				  staffList.setAssignId(projAssignModel.getAssignId());
				  staffList.setStatus(projAssignModel.getStatus());
				  staffListService.insert(staffList);
			  }
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateAssAndStaffList(List<StaffListModel> list, ProjAssignModel projAssignModel, String userId)throws Exception{
		projAssignModel.setCreateBy(userId);
		projAssignModel.setCreateDt(new Date());
		projAssignModel.setUpdateBy(userId);
		projAssignModel.setUpdateDt(new Date());
		projAssignService.update(projAssignModel);
		if(list!=null&&list.size()>0){
			StaffListModel staffListModel=new StaffListModel();
			staffListModel.setAssignId(projAssignModel.getAssignId());
			staffListService.delete(staffListModel);
			for(StaffListModel staffList:list){
				String pkId= serialnoService.getSequence("EI.TZ_T_YH_STAFF_LIST");
				staffList.setPkId(pkId);
				staffList.setCreateBy(userId);
				staffList.setCreateDt(new Date());
				staffList.setUpdateBy(userId);
				staffList.setUpdateDt(new Date());
				staffList.setStatus(projAssignModel.getStatus());
				staffList.setProjId(projAssignModel.getProjId());
				staffList.setAssignId(projAssignModel.getAssignId());
				staffListService.insert(staffList);
			}
		}


	}

	@Override
	public List<StaffListModel> selectListByUid(StaffListModel model) {
		return this.selectList("selectListByUid",model);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void delateById(String assignId,String userId) throws Exception {
		ProjAssignModel projAssignModel=new ProjAssignModel();
		projAssignModel.setAssignId(assignId);
		projAssignModel.setUpdateDt(new Date());
		projAssignModel.setUpdateBy(userId);
		projAssignModel.setStatus("9");
		projAssignService.update(projAssignModel);
		StaffListModel staffListModel=new StaffListModel();
		staffListModel.setAssignId(projAssignModel.getAssignId());
		staffListModel.setStatus("9");
		staffListModel.setUpdateDt(new Date());
		staffListModel.setUpdateBy(userId);
		staffListService.update("updateBystaffList",staffListModel);
	}
}