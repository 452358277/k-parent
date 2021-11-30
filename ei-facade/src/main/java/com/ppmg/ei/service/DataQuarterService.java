package com.ppmg.ei.service;

import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.DataQuarterModel;

import java.util.List;

public interface DataQuarterService extends BaseService<DataQuarterModel>  {
    void insertList(List<DataQuarterModel> list, String quarter, int year, String clostT) throws Exception;

    void sendMsgMcInfo(List<DataQuarterModel> list, String closeDate, String nowYear, String quarter, String dateType)throws Exception;

    void sendMsgBankInfo(List<DataQuarterModel> list, String closeDate, String nowYear, String quarter, String dateType)throws Exception;
}