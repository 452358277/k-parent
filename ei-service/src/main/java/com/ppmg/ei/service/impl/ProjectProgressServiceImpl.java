package com.ppmg.ei.service.impl;

import com.ppmg.ei.model.SysMessageModel;
import com.ppmg.ei.service.ProgressCommentService;
import com.ppmg.ei.service.SysMessageService;
import com.ppmg.ei.util.otherUtil;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProjectProgressModel;
import com.ppmg.ei.service.ProjectProgressService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Component("projectProgressService")
@Service(interfaceClass = ProjectProgressService.class)
public class ProjectProgressServiceImpl extends BaseServiceImpl<ProjectProgressModel> implements ProjectProgressService {

	public ProjectProgressServiceImpl(){
		this.setNamespace("ProjectProgress");
	}

	@Resource(name="projectProgressService")
	private ProjectProgressService projectProgressService;

	@Resource(name="progressCommentService")
	private ProgressCommentService progressCommentService;

	@Resource(name="sysMessageService")
	private SysMessageService sysMessageService;


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertProessAndMessage(ProjectProgressModel model, String allUserIds, String allUserNames , String userId , String name,String dealMark,String dist,String projStatus) throws Exception{

		//插入项目进展表
		projectProgressService.insert(model);

		//将@的人插入到站内消息
		String[] userIds = allUserIds.split(",");
		String[] userNmaes = allUserNames.split(",");
		SysMessageModel sysMessageModel = new SysMessageModel();
		for(int i=0;i<userIds.length;i++){
			String MSG_ID = otherUtil.getSeqNextValString("BASE.SEQ_YH_SYS_MESSAGE");
			sysMessageModel.setMsgId(MSG_ID);
			sysMessageModel.setMsgTitle(name+"发布了被投企业为:"+model.getProjectObjectName()+",出资主体为:"+model.getInveName()+"的工作进展。并@了你！");
			sysMessageModel.setMsgContext(model.getProgressText());
			sysMessageModel.setReceiveUserId(userIds[i]);
			sysMessageModel.setReceiveUserName(userNmaes[i]);
			sysMessageModel.setMsgType("1");
			sysMessageModel.setStatus("0");
			sysMessageModel.setCreateBy(userId);
			sysMessageModel.setCreateDt(new Date());

			String msgUrl = "/eiweb/enterpriaseInformation/toEnterpraiseInformationJsp.action?"
					+"projectObject="+model.getProjectObject()+""
					+"&dealMark="+dealMark+""
					+"&dist="+dist+""
					+"&PROJ_STATUS="+projStatus+""
					+"&PROJ_OBJECT_NAME="+model.getProjectObjectName()+""
					+"&INVE_ID="+model.getInveId()+""
					+"&INVE_NAME="+model.getInveName()+""
					+"&GROUP_ID="+model.getGroupId();
			sysMessageModel.setMsgUrl(msgUrl);
			sysMessageService.insert(sysMessageModel);

		}



	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateProessAndMessage(ProjectProgressModel model, String allUserIds, String allUserNames , String userId ,String name,String dealMark,String dist,String projStatus) throws Exception{

		//更新项目进展表
		projectProgressService.update(model);

		//将@的人插入到站内消息

		String[] userIds = allUserIds.split(",");
		String[] userNmaes = allUserNames.split(",");
		SysMessageModel sysMessageModel = new SysMessageModel();
		for(int i=0;i<userIds.length;i++){
			String MSG_ID = otherUtil.getSeqNextValString("BASE.SEQ_YH_SYS_MESSAGE");
			sysMessageModel.setMsgId(MSG_ID);
			sysMessageModel.setMsgTitle(name+"编辑了被投企业为:"+model.getProjectObjectName()+",出资主体为:"+model.getInveName()+"的工作进展！");
			sysMessageModel.setMsgContext(model.getProgressText());
			sysMessageModel.setReceiveUserId(userIds[i]);
			sysMessageModel.setReceiveUserName(userNmaes[i]);
			sysMessageModel.setMsgType("1");
			sysMessageModel.setStatus("0");
			sysMessageModel.setCreateBy(userId);
			sysMessageModel.setCreateDt(new Date());
			String msgUrl = "/eiweb/enterpriaseInformation/toEnterpraiseInformationJsp.action?"
					+"projectObject="+model.getProjectObject()+""
					+"&dealMark="+dealMark+""
					+"&dist="+dist+""
					+"&PROJ_STATUS="+projStatus+""
					+"&PROJ_OBJECT_NAME="+model.getProjectObjectName()+""
					+"&INVE_ID="+model.getInveId()+""
					+"&INVE_NAME="+model.getInveName()+""
					+"&GROUP_ID="+model.getGroupId();
			sysMessageModel.setMsgUrl(msgUrl);
			sysMessageService.insert(sysMessageModel);

		}
	}

}