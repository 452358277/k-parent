package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;

import com.ppmg.ei.model.MeetingRecordModel;

import java.util.List;

public interface MeetingRecordService extends BaseService<MeetingRecordModel>  {

    List<MeetingRecordModel>  selectByTimeList(String entId,String year);

}