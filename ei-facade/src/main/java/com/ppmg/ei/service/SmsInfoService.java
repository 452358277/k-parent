package com.ppmg.ei.service;

import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.BaseService;
import com.ppmg.ei.model.AdminModel;
import com.ppmg.ei.model.SmsInfoModel;
import com.ppmg.ei.model.SmsModel;

import java.util.List;

public interface SmsInfoService extends BaseService<SmsInfoModel>  {

    /**
     * 获取企业信息列表
     * @return 企业信息
     */
    List<AdminModel> getEntInfos();

    /**
     * 删除关联信息
     * @param smsId 短信ID
     */
    void deleteDetails(String smsId);

    /**
     * 删除短信信息
     * @param smsId
     */
    void deleteSmsInfo(String smsId);

    /**
     * 获取短信模板信息
     * @return
     */
    List<SmsModel> getSmsModel();

    List<SmsModel> getGovSmsModel(SearchCondition var1);

}