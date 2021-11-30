package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.SurverCooorgModel;
import com.ppmg.ei.model.SurveyPlanUsersModel;
import com.ppmg.ei.service.SurverCooorgService;
import com.ppmg.ei.service.SurveyPlanUsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.SurveyPlanModel;
import com.ppmg.ei.service.SurveyPlanService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("surveyPlanService")
@Service(interfaceClass = SurveyPlanService.class)
public class SurveyPlanServiceImpl extends BaseServiceImpl<SurveyPlanModel> implements SurveyPlanService {

	public SurveyPlanServiceImpl(){
		this.setNamespace("SurveyPlan");
	}

	@Resource
	private SurveyPlanService surveyPlanService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource
	private SurveyPlanUsersService surveyPlanUsersService;

	@Resource
	private SurverCooorgService surverCooorgService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertUser(SurveyPlanModel model, List<SurveyPlanUsersModel> ids) throws Exception{
		surveyPlanService.insert(model);
		if(ids!=null&&ids.size()>0){
			for (SurveyPlanUsersModel surver:ids) {
				//可能有重复的人员
				SurveyPlanUsersModel surveyPlanUsers=new SurveyPlanUsersModel();
				surveyPlanUsers.setPlanId(model.getPlanId());
				surveyPlanUsers.setPlanUseId(surver.getPlanUseId());
				surveyPlanUsers.setStatus("0");
				//判断选人中是否有重复的人员
				List<SurveyPlanUsersModel>  list= surveyPlanUsersService.selectList(surveyPlanUsers);
				if(list==null||list.isEmpty()){
					surver.setPlanId(model.getPlanId());
					surver.setStatus("0");
					surver.setCreateBy(model.getCreateBy());
					surver.setCreateDt(new Date());
					surver.setUpdateBy(model.getCreateBy());
					surver.setUpdateDt(new Date());
					surveyPlanUsersService.insert(surver);
				}

			}
		}
		/*if(StringUtils.isNotEmpty(userIds)){
			List<String> mlist = Arrays.asList(userIds.split(","));
			for(int i=0;i<mlist.size();i++){
				SurveyPlanUsersModel surver=new SurveyPlanUsersModel();
				String userId=mlist.get(i);
				surver.setPlanId(model.getPlanId());
				surver.setPlanUseId(userId);
				surver.setStatus("0");
				surver.setCreateBy(model.getCreateBy());
				surver.setCreateDt(new Date());
				surver.setUpdateBy(model.getCreateBy());
				surver.setUpdateDt(new Date());
				surveyPlanUsersService.insert(surver);
			}

		}*/

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateByUser(SurveyPlanModel model,  List<SurveyPlanUsersModel> ids) throws Exception {
		surveyPlanService.update(model);
		if(ids!=null&&ids.size()>0){
			if(StringUtils.isNotEmpty(model.getPlanId())){
				SurveyPlanUsersModel surverp = new SurveyPlanUsersModel();
				surverp.setPlanId(model.getPlanId());
				surveyPlanUsersService.delete(surverp);
			}
			for (SurveyPlanUsersModel surver:ids) {
				surver.setPlanId(model.getPlanId());
				surver.setStatus("0");
				surver.setCreateBy(model.getCreateBy());
				surver.setCreateDt(new Date());
				surver.setUpdateBy(model.getCreateBy());
				surver.setUpdateDt(new Date());
				surveyPlanUsersService.insert(surver);
			}
		}
	/*	if(StringUtils.isNotEmpty(userId)){
			List<String> mlist = Arrays.asList(userId.split(","));
			if(StringUtils.isNotEmpty(model.getPlanId())){
				surveyPlanUsersService.deleteById(model.getPlanId());
			}
			for(int i=0;i<mlist.size();i++){
				SurveyPlanUsersModel surver=new SurveyPlanUsersModel();
				String id=mlist.get(i);
				surver.setPlanId(model.getPlanId());
				surver.setPlanUseId(id);
				surver.setStatus("0");
				surver.setCreateBy(model.getCreateBy());
				surver.setCreateDt(new Date());
				surver.setUpdateBy(model.getCreateBy());
				surver.setUpdateDt(new Date());
				surveyPlanUsersService.insert(surver);
			}
		}*/


	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertInfo(SurveyPlanModel model, List<SurveyPlanUsersModel> ids, List<SurverCooorgModel> list) throws Exception {
        //内+外+外
		   if(list!=null&&list.size()>0){
				for(SurverCooorgModel surverCooorgModel:list){
					surverCooorgModel.setPlanId(model.getPlanId());
					surverCooorgModel.setCreateBy(model.getCreateBy());
					surverCooorgModel.setCreateDt(new Date());
					surverCooorgModel.setUpdateBy(model.getCreateBy());
					surverCooorgModel.setUpdateDt(new Date());
					surverCooorgService.insert(surverCooorgModel);
				}
		   }
		   //内+外

		if("3".equals(model.getInterior())) {
			if(ids!=null&&ids.size()>0){
				if(StringUtils.isNotEmpty(model.getPlanId())){
					SurveyPlanUsersModel surverp = new SurveyPlanUsersModel();
					surverp.setPlanId(model.getPlanId());
						surveyPlanUsersService.delete(surverp);
				}
				for (SurveyPlanUsersModel surver:ids) {
					surver.setPlanId(model.getPlanId());
					surver.setStatus("0");
					surver.setCreateBy(model.getCreateBy());
					surver.setCreateDt(new Date());
					surver.setUpdateBy(model.getCreateBy());
					surver.setUpdateDt(new Date());
					surveyPlanUsersService.insert(surver);
				}
			}



		/*	if (StringUtils.isNotEmpty(userIds)) {
				if(StringUtils.isNotEmpty(model.getPlanId())){
					SurveyPlanUsersModel surverp = new SurveyPlanUsersModel();
					surverp.setPlanId(model.getPlanId());
					surveyPlanUsersService.delete(surverp);
				}
				List<String> mlist = Arrays.asList(userIds.split(","));
				for (int i = 0; i < mlist.size(); i++) {
					SurveyPlanUsersModel surver = new SurveyPlanUsersModel();
					String id = mlist.get(i);
					surver.setPlanId(model.getPlanId());
					surver.setPlanUseId(id);
					surver.setStatus("0");
					surver.setCreateBy(model.getCreateBy());
					surver.setCreateDt(new Date());
					surver.setUpdateBy(model.getCreateBy());
					surver.setUpdateDt(new Date());
					surveyPlanUsersService.insert(surver);
				}
			}*/
		}
		surveyPlanService.insert(model);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateInfo(SurveyPlanModel model,List<SurveyPlanUsersModel> ids, List<SurverCooorgModel> list) throws Exception {
		//内+外+外
		if(list!=null&&list.size()>0){
			//删除之前的值
			SurverCooorgModel surverCooo=new SurverCooorgModel();
			surverCooo.setPlanId(model.getPlanId());
			if(StringUtils.isNotEmpty(model.getPlanId())){
				surverCooorgService.delete(surverCooo);
			}
			for(SurverCooorgModel surverCooorgModel:list){
				surverCooorgModel.setPlanId(model.getPlanId());
				surverCooorgModel.setCreateBy(model.getCreateBy());
				surverCooorgModel.setCreateDt(new Date());
				surverCooorgModel.setUpdateBy(model.getCreateBy());
				surverCooorgModel.setUpdateDt(new Date());
				surverCooorgService.insert(surverCooorgModel);
			}
		}else{
			SurverCooorgModel surverCooo=new SurverCooorgModel();
			surverCooo.setPlanId(model.getPlanId());
			if(StringUtils.isNotEmpty(model.getPlanId())){
				surverCooorgService.delete(surverCooo);
			}
		}
		//内+外
		if("3".equals(model.getInterior())) {
			if(ids!=null&&ids.size()>0){
				if(StringUtils.isNotEmpty(model.getPlanId())){
					SurveyPlanUsersModel surverp = new SurveyPlanUsersModel();
					surverp.setPlanId(model.getPlanId());
					surveyPlanUsersService.delete(surverp);
				}
				for (SurveyPlanUsersModel surver:ids) {
					surver.setPlanId(model.getPlanId());
					surver.setStatus("0");
					surver.setCreateBy(model.getCreateBy());
					surver.setCreateDt(new Date());
					surver.setUpdateBy(model.getCreateBy());
					surver.setUpdateDt(new Date());
					surveyPlanUsersService.insert(surver);
				}
			}

		}
		surveyPlanService.update(model);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteByIds(List<String> ids, String userId) {
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("ids", ids);
			paramsMap.put("userId", userId);
			paramsMap.put("status", "9");
		    surveyPlanService.update("deleteByIds", paramsMap);
		}

}