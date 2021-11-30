package com.ppmg.ei.utils;

import com.founder.uim.dao.AppRoleDao;
import com.founder.uim.database.Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessUtils {

    //修改状态
    public static void updateStatus(String planId,String status) {
        Database db =null;
        ResultSet rs = null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
             String sql = "update EI.TZ_T_SURVEY_PLAN  set STATUS='"+status+"' where PLAN_ID='"+planId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static List<Map<String,Object>> selectList(String sql){
        AppRoleDao dao = new AppRoleDao();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Database db = null;
        ResultSet rs = null;
        try {
             db = new Database(dao.getConnection());
             rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }
        return list;
    }

    //修改状态
    public static void updateDueStatus(String rptId,String status) {
        ResultSet rs =null;
        Database db =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_DUE_DILIGENCE_RPT  set STATUS='"+status+"' where RPT_ID='"+rptId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }
    //修改状态
    public static void updateStaffStatus(String assignId,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
             String sql = "update EI.TZ_T_YH_STAFF_LIST  set STATUS='"+status+"' where ASSIGN_ID='"+assignId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }
    public static void updateAggissStatus(String assignId,String status) {
        Database db =null;
        ResultSet rs=null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_ASSIGN  set STATUS='"+status+"' where ASSIGN_ID='"+assignId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }
    //修改状态
    public static void updateFileStatus(String fileId,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_CONTRACT_FILE  set STATUS='"+status+"' where FILE_ID='"+fileId+"'";
             rs = db.getRS(sql);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    //修改状态
    public static void updateFileInStatus(String id,String status) {
        AppRoleDao dao = new AppRoleDao();
        Database db =null;
        ResultSet rs =null;
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_CONTRACT_FILE  set STATUS='"+status+"' where FILE_INFO_ID='"+id+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    //基金出资修改状态
    public static void updateItemStatus(String itemId,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.RZ_T_FUND_SHARE_ITEM  set STATUS='"+status+"' where ITEM_ID='"+itemId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static void updatePayStatus(String pqId,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.XJL_T_PAYMENT_REQUEST  set STATUS='"+status+"' where pq_id='"+pqId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static void updateFitStatus(String projId ,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
             String sql = "update EI.BD_T_FIT_REGULATION_SELF  set FLOW_STATUS='"+status+"' where PROJ_ID='"+projId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static void updateAppStatus(String projId ,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_APP_INFO_SNAPSHOT_D  set PROJ_APP_STATUS='"+status+"' where PROJ_ID='"+projId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static void updateFundStatus(String fundId ,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.BD_T_FUND_INFO  set FUND_STS='"+status+"' where fund_id='"+fundId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static void updateProjStatus(String projId ,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_INFO  set PROJ_STATUS='"+status+"' where PROJ_ID='"+projId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }


    //修改状态
    public static void updateFileStatusByList(String id,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_CONTRACT_FILE  set STATUS='"+status+"' where FILE_INFO_ID='"+id+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    //修改状态
    public static void updateFileInfoStatus(String id,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.TZ_T_PROJ_CONTRACT_INFO  set STATUS='"+status+"' where ID='"+id+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static String selectBy(String sql){
        String result="false";
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
             db = new Database(dao.getConnection());
             rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            if(list!=null&&list.size()>0){
                if(list.get(0).get("INTENDED_AMOUNT")!=null){
                    Double d=Double.valueOf(list.get(0).get("INTENDED_AMOUNT").toString());
                    if(d>=Double.valueOf("5000")){
                        result="true";
                    }else{
                        result="false";
                    }
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }
        return result;
    }
    public static void updateApplyStatus(String applyNo,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
             String sql = "update OA.FOUNDER_OA_APPLY_INFO  set APPLY_STATUS='"+status+"' where APPLY_NO='"+applyNo+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }

    public static String getInfo(String sql){
        String result="false";
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            db = new Database(dao.getConnection());
            rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            if(list!=null&&list.size()>0){
                if(list.get(0).get("IS_FINANCE_APPROVAL")!=null){
                    String d=list.get(0).get("IS_FINANCE_APPROVAL")==null?"":list.get(0).get("IS_FINANCE_APPROVAL").toString();
                    String f=list.get(0).get("LEGAL_APPROVAL")==null?"":list.get(0).get("LEGAL_APPROVAL").toString();
                    if("0".equals(d)&&"0".equals(f)){
                        result="true";
                    }else{
                        result="false";
                    }
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }
        return result;
    }


    public static String getStatus(String sql){
        String result="false";
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            db = new Database(dao.getConnection());
            rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            if(list!=null&&list.size()>0){
                if(list.get(0).get("QUIT_WAY")!=null){
                    String d=String.valueOf(list.get(0).get("QUIT_WAY"));
                    if("2".equals(d)){
                        result="true";
                    }else{
                        result="false";
                    }
                }

            }else{

            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }
        return result;
    }



    public static void updateFundSts(String fundId ,String status) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
             db = new Database(dao.getConnection());
            String sql = "update EI.BD_T_FUND_INFO  set FUND_STS='"+status+"' where fund_id='"+fundId+"'";
             rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }


    //修改状态
    public static void updateTableStatus(String id,String status,String table) {
        Database db =null;
        ResultSet rs =null;
        AppRoleDao dao = new AppRoleDao();
        try {
            db = new Database(dao.getConnection());
            String sql = "update "+ table+ "  set PROCESS_STATUS='"+status+"' where IRA_ID='"+id+"'";
            rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(db!=null){
                    db.close(rs);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            dao.close();
        }

    }


    public static List<String> getCheckUsers(String iraId) {
        AppRoleDao dao = new AppRoleDao();
        String userIds = "";
        List<String> list = new ArrayList<>();
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT COMM_USER_ID AS USER_ID FROM EI.TZ_T_REGULATION_APPROVE_COMM WHERE IRA_ID = '"+iraId+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> appUsers = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                appUsers.add(rowData);
            }

            for(Map m:appUsers){
                userIds = m.get("USER_ID")+"";
                list.add(userIds);
            }

            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return list;
    }

}
