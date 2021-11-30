package com.ppmg.ei.utils;

import com.founder.uim.dao.AppRoleDao;
import com.founder.uim.database.Database;
import com.founder.uim.xdk.AppUser;
import org.springframework.mail.SimpleMailMessage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealWorkFlowUtil {

/**
     * 流程結束：发送通知
     * @param taskTitle：标题；随意
     * @param startId:发起人id
     * @param instId:流程编号:说明：流程内直接通过此方法获取：String instId = processInfo.getProcessInstance().getId();
     * @param userId：需要接收通知的人id
     * @throws Exception
     */


    public static void fundProjPaySendEmail(String taskTitle, String startId, String instId,String userId) throws Exception{
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        boolean sendFlag = true;//getMailFlag();
        //String userId ="100,103,104";//要发送人的id
        System.out.println("=============================发起人:"+startId+"流程ID:"+instId);
        try{
            db = new Database(dao.getConnection());
                /*String PROJ_NAME = "项目名称";
                String PROJ_OBJECT_NAME = "被投企业";
                String INVE_NAME = "出资主体";
                String GROUP_NAME = "所属平台";*/

                //站内信
                System.out.println("=============================发送通知开始，发起人:"+startId+"流程ID:"+instId);
                Map<String, Object> arg2 = new HashMap<String, Object>();
                String sqlString2 = "SELECT * FROM BASE.FIXFLOW_RUN_PROCESSINSTANCE P "
                        + "LEFT JOIN BASE.FIXFLOW_RUN_TASKINSTANCE t ON P.PROCESSINSTANCE_ID=T.PROCESSINSTANCE_ID "
                        + "WHERE P.PROCESSINSTANCE_ID='"+instId+"' "
                        + "ORDER BY T.CREATE_TIME DESC ";
                rs = db.getRS(sqlString2);
                List<ProcessUtil> list = convertListProcess(rs);
                if (list != null && list.size() > 0) {
                    String[] toMsg = String.valueOf(userId).split(",");//所要接收通知的所有人
                    String groupId = "3";//TZ_T_PROJ_INFO表：GROUP_ID字段：项目所属公司ID.不晓得
                    String taskId = String.valueOf(list.get(0).getTaskinstanceId());
                    String defId = String.valueOf(list.get(0).getProcessdefinitionId());
                    String bizKey = String.valueOf(list.get(0).getBizkKey());
                    String formUri = String.valueOf(list.get(0).getFormUri());
                    String formUriView = String.valueOf(list.get(0).getFormUriView());
                    for(int i=0;i<toMsg.length;i++){
                        String receive_user_id = (String) toMsg[i];
                        if(!startId.equals(receive_user_id)){
                            MyMessageBean myMessageBean = new MyMessageBean();
                            myMessageBean.setMSG_TITLE("已完成审批【"+ taskTitle+"】");
                            myMessageBean.setMSG_CONTEXT("流程【" + taskTitle + "】审批结果通知");
                            myMessageBean.setRECEIVE_USER_ID(receive_user_id);//接收人ID
                            myMessageBean.setCREATE_BY(startId);
                           /* myMessageBean.setMSG_URL("/sysweb/editor/editor.jsp?dealMark=view&showToolBar=false&xmlFileName=workflowEditorConfig"
                                    + "&groupId="+groupId+"&taskId="+taskId+"&instId="+instId+"&defId="+defId+"&bizKey="+keyValues
                                    + "&formUri=%2Feiweb%2Faction%2FbizObjectBase.action%3FkeyValues%3D"+keyValues+"%26tableName%3DXJL_T_PAYMENT_REQUEST"
                                    + "%26dealMark%3Dview%26sequenceName%3D%26springJndiId%3DYHKG_DS_EI"
                                    + "%26resultJsp%3D..%2FWEB-INF%2Fviews%2Fcashflow%2FviewxjlPayForPrint.jsp%26className%3Dcom.founder.ei.bizobject.xjlPayObj"
                                    + "&formUriView=%2Feiweb%2Faction%2FbizObjectBase.action%3FkeyValues%3D"+keyValues+"%26tableName%3DXJL_T_PAYMENT_REQUEST"
                                    + "%26dealMark%3Dview%26sequenceName%3D%26springJndiId%3DYHKG_DS_EI"
                                    + "%26resultJsp%3D..%2FWEB-INF%2Fviews%2Fcashflow%2FviewxjlPayForPrint.jsp%26className%3Dcom.founder.ei.bizobject.xjlPayObj");*/
                            myMessageBean.setMSG_URL("/sysweb/editor/editor.jsp?xmlFileName=workflowEditorConfig&dealMark=view"
                                    + "&groupId=" + groupId
                                    + "&defId=" + defId
                                    + "&taskId=" + taskId
                                    + "&instId=" + instId
                                    + "&bizKey=" + bizKey
                                    + "&formUri=" + formUri
                                    + "&formUriView=" + formUriView
                                    + "&isHomePage=1");
                            myMessageBean.setMSG_TYPE("0");
                            myMessageBean.setMSG_FROM("");
                            myMessageBean.setPARENT_ID("");
                            myMessageBean.setATTA_FILE("");
                            try {
                                MyMessageUtil.addMyMessage(myMessageBean);
                                System.out.println("=============================发送通知结束，发起人:"+startId+"流程ID:"+instId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static <E> List<E> convertListProcess(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        List<E>  user = new ArrayList<>();
        while (rs.next()) {
            ProcessUtil us = null;
            us = new ProcessUtil();
            String taskinstanceId = rs.getString("TASKINSTANCE_ID");
            String processdefinitionId = rs.getString("PROCESSDEFINITION_ID");
            String bizkKey = rs.getString("BIZ_KEY");
            String formUri = rs.getString("FORMURI");
            String formUriView = rs.getString("FORMURIVIEW");
            us.setTaskinstanceId(taskinstanceId);
            us.setProcessdefinitionId(processdefinitionId);
            us.setBizkKey(bizkKey);
            us.setFormUri(formUri);
            us.setFormUriView(formUriView);
            user.add((E) us);
        }
        return user;
    }
}


