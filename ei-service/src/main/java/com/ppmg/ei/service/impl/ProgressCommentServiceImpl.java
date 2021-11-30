package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.model.ProjectProgressModel;
import com.ppmg.ei.model.SysMessageModel;
import com.ppmg.ei.service.ProjectProgressService;
import com.ppmg.ei.service.SysMessageService;
import com.ppmg.ei.util.otherUtil;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.ProgressCommentModel;
import com.ppmg.ei.service.ProgressCommentService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Component("progressCommentService")
@Service(interfaceClass = ProgressCommentService.class)
public class ProgressCommentServiceImpl extends BaseServiceImpl<ProgressCommentModel> implements ProgressCommentService {

	public ProgressCommentServiceImpl(){
		this.setNamespace("ProgressComment");
	}

	@Resource(name="sysMessageService")
	private SysMessageService sysMessageService;

	@Resource(name="progressCommentService")
	private ProgressCommentService progressCommentService;

	@Resource(name="projectProgressService")
	private ProjectProgressService projectProgressService;


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertCommentAndMessage(ProgressCommentModel model, String allUserIds, String allUserNames , String userId, String name,String dealMark,String dist,String projStatus) throws Exception{

		//插入项目进展评论表
		progressCommentService.insert(model);

		//将@的人插入到站内消息
		String[] userIds = allUserIds.split(",");
		String[] userNmaes = allUserNames.split(",");
		SysMessageModel sysMessageModel = new SysMessageModel();
		for(int i=0;i<userIds.length;i++){
			ProjectProgressModel projectProgressModel = projectProgressService.selectById(model.getProgressId());
			String MSG_ID = otherUtil.getSeqNextValString("BASE.SEQ_YH_SYS_MESSAGE");
			sysMessageModel.setMsgId(MSG_ID);
			if(projectProgressModel.getInveId()==""||projectProgressModel.getInveId()==null){
				projectProgressModel.setInveId("");
				projectProgressModel.setInveName("");
			}
			if(projectProgressModel.getProgressText()==""||projectProgressModel.getProgressText()==null){
				projectProgressModel.setProgressText("");
			}
			if(projectProgressModel.getCreateBy().equals(userIds[i])){
				sysMessageModel.setMsgTitle(name+"评论了被投企业为:"+projectProgressModel.getProjectObjectName()+",出资主体为:"+projectProgressModel.getInveName()+",进展内容为:"+projectProgressModel.getProgressText()+"的工作进展。");
			}else{
				sysMessageModel.setMsgTitle(name+"评论了被投企业为:"+projectProgressModel.getProjectObjectName()+",出资主体为:"+projectProgressModel.getInveName()+",进展内容为:"+projectProgressModel.getProgressText()+"的工作进展。并@了你！");
			}
			sysMessageModel.setParentId(model.getProgressId());
			sysMessageModel.setMsgContext(model.getCommentHtml());
			sysMessageModel.setReceiveUserId(userIds[i]);
			sysMessageModel.setReceiveUserName(userNmaes[i]);
			sysMessageModel.setMsgType("1");
			sysMessageModel.setStatus("0");
			sysMessageModel.setCreateBy(userId);
			sysMessageModel.setCreateDt(new Date());



			String msgUrl = "/eiweb/enterpriaseInformation/toEnterpraiseInformationJsp.action?"
					+"projectObject="+projectProgressModel.getProjectObject()+""
					+"&dealMark="+dealMark+""
					+"&dist="+dist+""
					+"&PROJ_STATUS="+projStatus+""
					+"&PROJ_OBJECT_NAME="+projectProgressModel.getProjectObjectName()+""
					+"&INVE_ID="+projectProgressModel.getInveId()+""
					+"&INVE_NAME="+projectProgressModel.getInveName()+""
					+"&GROUP_ID="+projectProgressModel.getGroupId();
			sysMessageModel.setMsgUrl(msgUrl);
			sysMessageService.insert(sysMessageModel);

		}



	}

}