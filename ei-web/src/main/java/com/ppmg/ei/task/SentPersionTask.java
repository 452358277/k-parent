package com.ppmg.ei.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.ppmg.ei.model.DataQuarterModel;
import com.ppmg.ei.model.SmsInfoModel;
import com.ppmg.ei.service.DataQuarterService;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.SmsInfoService;
import com.ppmg.ei.utils.PhoneFormatCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class SentPersionTask {

    @Reference(check = false, timeout = 200000)
    private DataQuarterService dataQuarterService;

    @Reference(check = false, timeout = 200000 )
    private FundInfoService fundInfoService;


    @Reference(check = false)
    private SmsInfoService smsInfoService;

    //次年1月5日,省政府 基金的年度报告提交通知,948916
    //@Scheduled(cron = "0 0 0 5 1 *")
    private void sendMsgYear(){
        //发送时间：次年1月5日 截止时间：次年4月30日）
        System.out.print("管理人推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        //上一年
        int lastYear=yearStr-1;
        try{
            //截止日期日期每年 04-30
           String nowYear= String.valueOf(yearStr);
            String closeDate=nowYear+"-04-30";
           //查询二级基金的基金管理人
            List<DataQuarterModel> list=fundInfoService.selectList("getFundMcInfo","");
            //管理人年度 基金的年度报告提交通知
            //应为是次年所以发送时候是提心上一年的需要填写年度
            dataQuarterService.sendMsgMcInfo(list,closeDate,String.valueOf(lastYear),"","8");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }

    //次年每年的1.5号,省政府 948916
    @Scheduled(cron = "0 0 10 5 1 *")
    private void sendYearMsg(){
        //发送时间：次年1月5日 截止时间：次年4月30日）
        System.out.print("管理人推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        //上一年
        int lastYear=yearStr-1;
        try{
            //截止日期日期每年 04-30
            String nowYear= String.valueOf(yearStr);
            String closeDate=nowYear+"-04-30";
            //查询二级基金的基金管理人
            List<DataQuarterModel> list=fundInfoService.selectList("getFundMcInfo","");
            //管理人年度 基金的年度报告提交通知
            sendMsg(list,closeDate,"sendYearMsg","3");


        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }




    //下季度第一天
    // @Scheduled(cron = "0 0 0 1 1,4,7,10 *")
    private void sendMsgQuarter(){
        //下季度第一个月第一天 948917
        //截止时间：下季度第一个月20日）
        System.out.print("管理人季度推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        String quarter=getQuarter();
        SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
        try{
            //截止日期日期季度第一个月20日
            String nowYear= String.valueOf(yearStr);
            int m = cal.get(Calendar.MONTH) + 1;
            String nowMoth="";
            if(m<10){
                nowMoth="0"+String.valueOf(m);
            }else{
                nowMoth=String.valueOf(m);
            }
            String closeDate=nowYear+"-"+nowMoth+"-20";
            //查询二级基金的基金管理人
            List<DataQuarterModel> list=fundInfoService.selectList("getFundMcInfo","");
            //管理人季度填报
            //应为是下个季度
            if("1".equals(quarter)){
                //则发送上一年的第四季度
                quarter="4";
                nowYear=String.valueOf(yearStr-1);
            }else{
                quarter= String.valueOf(Integer.parseInt(quarter)-1);
            }
            dataQuarterService.sendMsgMcInfo(list,closeDate,nowYear,quarter,"9");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }


    // @Scheduled(cron = "0 0 9 1 1,4,7,10 *")
    private void sendQuarterMsg(){
        //下季度第一个月第一天 948917
        //截止时间：下季度第一个月20日）
        System.out.print("管理人季度推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        String quarter=getQuarter();
        SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
        try{
            //截止日期日期季度第一个月20日
            String nowYear= String.valueOf(yearStr);
            int m = cal.get(Calendar.MONTH) + 1;
            String nowMoth="";
            if(m<10){
                nowMoth="0"+String.valueOf(m);
            }else{
                nowMoth=String.valueOf(m);
            }
            String closeDate=nowYear+"-"+nowMoth+"-20";
            //查询二级基金的基金管理人
            List<DataQuarterModel> list=fundInfoService.selectList("getFundMcInfo","");
            sendMsg(list,closeDate,"sendQuarterMsg","3");

        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }







    //每季度最后一个月的15号
   // @Scheduled(cron = "0 0 0 15 3,6,9,12 *")
    private void sendLastMonth(){
        //（发送时间：每季度最后一个月15日截止时间：每季度最后一个月25日）
        System.out.print("管理人季度推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        String quarter=getQuarter();
        SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
        try{
            //截止日期日期季度第一个月20日
            String nowYear= String.valueOf(yearStr);
            int m = cal.get(Calendar.MONTH) + 1;
            String nowMoth="";
            if(m<10){
                nowMoth="0"+String.valueOf(m);
            }else{
                nowMoth=String.valueOf(m);
            }
            String closeDate=nowYear+"-"+nowMoth+"-25";
            //查询二级基金的基金管理人
            List<DataQuarterModel> list=fundInfoService.selectList("getFundMcInfo","");
            //管理人审核任务
            dataQuarterService.sendMsgMcInfo(list,closeDate,nowYear,quarter,"10");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }

    //每季度最后一个月的15号
    // @Scheduled(cron = "0 0 9 15 3,6,9,12 *")
    private void sendLastMonthMsg(){
        //（发送时间：每季度最后一个月15日截止时间：每季度最后一个月25日）
        System.out.print("管理人季度推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        String quarter=getQuarter();
        SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
        try{
            //截止日期日期季度第一个月20日
            String nowYear= String.valueOf(yearStr);
            int m = cal.get(Calendar.MONTH) + 1;
            String nowMoth="";
            if(m<10){
                nowMoth="0"+String.valueOf(m);
            }else{
                nowMoth=String.valueOf(m);
            }
            String closeDate=nowYear+"-"+nowMoth+"-25";
            //查询二级基金的基金管理人
            List<DataQuarterModel> list=fundInfoService.selectList("getFundMcInfo","");
            //管理人审核任务
            sendMsg(list,closeDate,"sendLastMonthMsg","3");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }



    //次年1月5日
    //@Scheduled(cron = "0 0 3 5 1 *")
    private void sendBank(){
        //年度托管报告
        System.out.print("托管报告推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        try{
            //截止日期日期每年 04-30
            String nowYear= String.valueOf(yearStr);
            String closeDate=nowYear+"-"+"04-30";
            //查询二级基金的托管银行
            List<DataQuarterModel> list=fundInfoService.selectList("getBankInfo","");
            //管理人年度
            nowYear= String.valueOf(yearStr-1);
            dataQuarterService.sendMsgBankInfo(list,closeDate,nowYear,"","11");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }

    //每年的1.5号
    //@Scheduled(cron = "0 0 9 5 1 *")
    private void sendBankMsg(){
        //年度托管报告 948923
        System.out.print("管理人推送信息");
        Calendar cal = Calendar.getInstance();
        int yearStr = cal.get(Calendar.YEAR);
        try{
            //截止日期日期每年 04-30
            String nowYear= String.valueOf(yearStr);
            String closeDate=nowYear+"-"+"04-30";
            //查询二级基金的托管银行
            List<DataQuarterModel> list=fundInfoService.selectList("getBankInfo","");
            //管理人年度
            sendMsg(list,closeDate,"sendBankMsg","4");
        }catch (Exception e){
            log.error("定时任务出现异常:"+e.getMessage());
        }
    }




    public  String getAfterTime(String date,int amount) throws Exception{
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            //        将字符串转成日期
            Date d = sf.parse(date);
            //        获得calendar对象
            Calendar calendar = Calendar.getInstance();
            //        设置当前时间
            calendar.setTime(d);
            //        在当前时间下加若干天
            calendar.add(calendar.DAY_OF_MONTH, amount);
            Date currTime = calendar.getTime();

            String lastTime = sf.format(currTime);
            return lastTime;

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
    //季度，复核    {1}，{2}年第{3}季度省政府投资基金参股基金及投资数据填报与复核工作已开始,截止日期：{4}。请登录江苏省政府投资基金管理平台系统完成数据复核工作。
    private static String quarterCheckReporting = "948914";

    //年度报告   {1}，{2}年度省政府投资基金参股基金年度报告提交工作已开始,截止日期：{3}。请登录江苏省政府投资基金管理平台系统完成提交。
    private static String yearReport = "948916";

    //季度报告  {1}，{2}年第{3}季度省政府投资基金参股基金季度报告提交工作已开始,截止日期：{4}。请登录江苏省政府投资基金管理平台系统完成提交。
    private static String quarterReport = "948917";
   //年度托管报告
   private static String hostingReport = "948923";


    public void sendMsg(List<DataQuarterModel> list,String closeT,String type,String smsModular) {
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
            //省政府平台短信
            smsInfoModel.setSmsModular(smsModular);
            List<String> params = new ArrayList<String>();
            SmsMultiSender msender = new SmsMultiSender(Integer.valueOf(appId), appKey);
            SmsMultiSenderResult result =null;
            try {
                params.add(entry.getLoginName());
                if("sendYearMsg".equals(type)){
                    params.add(String.valueOf(yearStr-1));
                    params.add(closeT);
                }
                if("sendQuarterMsg".equals(type)){
                    String yearN="";
                    if("1".equals(quarter)){
                        //则发送上一年的第四季度
                        quarter="4";
                        yearN=String.valueOf(yearStr-1);
                        params.add(quarter);
                        params.add(yearN);
                    }else{
                        quarter= String.valueOf(Integer.parseInt(quarter)-1);
                        yearN=String.valueOf(yearStr);
                        params.add(quarter);
                        params.add(yearN);
                    }
                    params.add(closeT);
                }
                if("sendLastMonthMsg".equals(type)){
                    params.add(String.valueOf(yearStr));
                    params.add(quarter);
                    params.add(closeT);
                }
                if("sendBankMsg".equals(type)){
                    params.add(String.valueOf(yearStr-1));
                    params.add(closeT);
                }


                List<String> receipts = new ArrayList<String>();
                receipts.add(entry.getPhoneNo());
                //校验手机号是否正确
                if(StringUtils.isNotEmpty(entry.getPhoneNo())&& PhoneFormatCheckUtils.isChinaPhoneLegal(entry.getPhoneNo())){
                    if("sendYearMsg".equals(type)){
                        result = msender.sendWithParam("86", (ArrayList)receipts, Integer.valueOf(yearReport), (ArrayList) params, smsSign, "", "");
                    }else if("sendQuarterMsg".equals(type)){
                        result = msender.sendWithParam("86", (ArrayList)receipts, Integer.valueOf(quarterReport), (ArrayList) params, smsSign, "", "");
                    }else if("sendLastMonthMsg".equals(type)){
                        result = msender.sendWithParam("86", (ArrayList)receipts, Integer.valueOf(quarterCheckReporting), (ArrayList) params, smsSign, "", "");
                    }else if("sendBankMsg".equals(type)){
                        result = msender.sendWithParam("86", (ArrayList)receipts, Integer.valueOf(hostingReport), (ArrayList) params, smsSign, "", "");
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
