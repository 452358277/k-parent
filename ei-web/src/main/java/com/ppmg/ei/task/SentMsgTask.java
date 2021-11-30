package com.ppmg.ei.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.ppmg.ei.model.DataQuarterModel;
import com.ppmg.ei.model.SmsInfoModel;
import com.ppmg.ei.service.*;
import com.ppmg.ei.utils.PhoneFormatCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class SentMsgTask   {

    @Reference(check = false, timeout = 100000 )
    private FundInfoService fundInfoService;

    @Reference(check = false, timeout = 100000)
    private DataQuarterService dataQuarterService;

    @Reference(check = false, timeout = 100000)
    private DataQuarterItemService dataQuarterItemService;


    @Reference(check = false, timeout = 100000)
    private ProjInfoService projInfoService;


    @Reference(check = false)
    private AppUserService appUserService;

    @Reference(check = false)
    private SmsInfoService smsInfoService;
    //直投平台
    //省政府的 9081和9083 发送任务只能发1个，注意
    //查询


    @Scheduled(cron = " 0 0 0 25 3,6,9,12 *")
    private void getFundInfoList1(){
        //每个季度的最后一个月的25号定时定时任务，截止日期为，下个季度的第一个月30号
        System.out.print("管理人和出资人代表推送信息");
        String quarter=getQuarter();
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        //截止日期
        String closeT="";
        if("1".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-04-30";
        }if("2".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-07-30";
        }if("3".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-10-30";
        }if("4".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-01-30";
        }

        try{
           List<DataQuarterModel> list=fundInfoService.selectFundInfoList("");
            dataQuarterService.insertList(list,quarter,yearStr,closeT);
            System.out.print("*****************结果*******************************************");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }



    @Scheduled(cron = " 0 0 9 25 3,6,9,12 *")
    private void getMcMsg(){
        //短信发送
        //每个季度的最后一个月的25号定时定时任务，截止日期为，下个季度的第一个月30号
        System.out.print("管理人和出资人代表推送信息");
        String quarter=getQuarter();
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        //截止日期
        String closeT="";
        if("1".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-04-30";
        }if("2".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-07-30";
        }if("3".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-10-30";
        }if("4".equals(quarter)){
            closeT=String.valueOf(yearStr)+"-01-30";
        }

        try{
            List<DataQuarterModel> list=fundInfoService.selectSendAdminList("");
            sendMsg(list,closeT,"quarter");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }


    @Scheduled(cron = " 0 0 0 25 12 *")
    private void getYear(){
        //（发送时间：本年度12月25日
        //截止时间：次年1月31日）
        System.out.print("管理人年出资人代表度推送信息");
        String quarter="";
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        //截止日期
        String closeT=String.valueOf(yearStr+1)+"-01-31";

        try{
            List<DataQuarterModel> list=fundInfoService.selectFundInfoList("");
            dataQuarterService.insertList(list,quarter,yearStr,closeT);

        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }


    @Scheduled(cron = " 0 0 9 25 12 *")
    private void getMcYaerMsg(){
        //每年的12-25号，截止次年的1.31号
        System.out.print("管理人出资人代表年度发送短信");
        String quarter=getQuarter();
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        //截止日期
        String closeT=String.valueOf(yearStr+1)+"-01-31";
        try{
            List<DataQuarterModel> list=fundInfoService.selectSendAdminList("");
            sendMsg(list,closeT,"year");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }


    public String getQuarter(){
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String quarter = "1";
        if (m >= 1 && m <= 3) {
            quarter = "1";
        }
        if (m >= 4 && m <= 6) {
            quarter = "2";
        }
        if (m >= 7 && m <= 9) {
            quarter = "3";
        }
        if (m >= 10 && m <= 12) {
            quarter = "4";
        }
        return quarter;
    }

    // appId
    private static String appId = "1400292074";

    // appId
    private static String appKey = "28167eaf20e1210bc8f49891532c9e95";

    // 短信签名
    private static String smsSign = "金财投资";

    private static String quarterTemplateId = "950242";

    private static String yearTemplateId = "950242";

    public void sendMsg(List<DataQuarterModel> list,String closeT,String type) {
        String quarter=getQuarter();
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        for (DataQuarterModel entry : list ) {
            SmsInfoModel smsInfoModel=new SmsInfoModel();
            smsInfoModel.setSmsId(UUID.randomUUID().toString());
            smsInfoModel.setCreateBy("init");
            smsInfoModel.setCreateDt(new Date());
            smsInfoModel.setSenderId(entry.getUserBizValue());
            smsInfoModel.setSenderName(entry.getLoginName());
            smsInfoModel.setSmsType("1");
            smsInfoModel.setSmsTime(new Date());
            smsInfoModel.setSmsStatus("0");
            smsInfoModel.setUpdateBy("init");
            smsInfoModel.setUpdateDt(new Date());
            smsInfoModel.setSmsModular("1");
            List<String> params = new ArrayList<String>();
            SmsMultiSender msender = new SmsMultiSender(Integer.valueOf(appId), appKey);
            SmsMultiSenderResult result =null;
            try {
                params.add(entry.getLoginName());
                params.add(String.valueOf(yearStr));
                if("quarter".equals(type)){
                    params.add(quarter);
                }
                params.add(closeT);
                List<String> receipts = new ArrayList<String>();
                receipts.add(entry.getPhoneNo());
                //校验手机号是否正确
                if(StringUtils.isNotEmpty(entry.getPhoneNo())&&PhoneFormatCheckUtils.isChinaPhoneLegal(entry.getPhoneNo())){
                    if("quarter".equals(type)){
                        result = msender.sendWithParam("86", (ArrayList)receipts, Integer.valueOf(quarterTemplateId), (ArrayList) params, smsSign, "", "");
                    }else{
                        result = msender.sendWithParam("86", (ArrayList)receipts, Integer.valueOf(yearTemplateId), (ArrayList) params, smsSign, "", "");
                    }

                    if(result!=null && result.result == 0){
                        smsInfoModel.setSmsStatus2("0");
                        smsInfoModel.setSmsStatus("1");
                        smsInfoModel.setIsDelete("0");
                    }else{
                        smsInfoModel.setSmsStatus2("1");
                        smsInfoModel.setIsDelete("0");
                    }
                }else{
                    smsInfoModel.setSmsStatus2("1");
                    smsInfoModel.setIsDelete("0");
                }
                smsInfoService.insert(smsInfoModel);
            }catch (Exception e) {
                log.error("定时任务出现异常:"+e.getMessage());
            }
        }
    }




}
