package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.MeetingRecordModel;
import com.ppmg.ei.service.MeetingRecordService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("meetingRecordService")
@Service(interfaceClass = MeetingRecordService.class)
public class MeetingRecordServiceImpl extends BaseServiceImpl<MeetingRecordModel> implements MeetingRecordService {

	public MeetingRecordServiceImpl(){
		this.setNamespace("MeetingRecord");
	}

	@Override
	public List<MeetingRecordModel> selectByTimeList(String entId,String year) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("OBJECT_ID", entId);
		param.put("MEETING_DATE", "%"+year+"%");
		return this.selectList("selectByTimeList", param);
	}
}