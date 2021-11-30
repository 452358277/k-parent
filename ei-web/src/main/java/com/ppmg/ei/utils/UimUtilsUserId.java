package com.ppmg.ei.utils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.founder.uim.dao.AppRoleDao;
import com.founder.uim.database.Database;
import com.founder.uim.xdk.AppUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.controller.ProjAppInfoQuitController;
import com.ppmg.ei.model.FixflowRunTaskinstanceModel;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UimUtilsUserId {
    protected static final Logger logger = LoggerFactory.getLogger(UimUtilsUserId.class);


    /**
     * 根据岗位,跟部门查找用户
     * @param postId:崗位id
     * @param groupId:group_id
     * @return
     */
    public static List<String> serchUserId(String postId,String groupId){
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            db = new Database(dao.getConnection());
            String sql = "SELECT * FROM base.APP_USER WHERE ID = (SELECT R.USER_ID FROM base.GROUP_POST_USER_RELATION R WHERE POST_ID ='"+postId+"' AND GROUP_ID  ='"+groupId+"')";
            rs = db.getRS(sql);
            List<AppUser> listId = convertList(rs);
            if (listId != null && listId.size() > 0) {
                for (AppUser au : listId) {
                    list.add(au.getId() + "");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(db!=null) {
                    db.close(rs);
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
            dao.close();
        }
        return list;
    }

    /**
     * 根据岗位查找用户
     * @param postId:崗位id
     * @return
     */
    public static List<String> serchUserIdPost(String postId){
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            db = new Database(dao.getConnection());
            String sql = "SELECT * FROM base.app_user WHERE id in (SELECT r.user_id FROM base.GROUP_POST_USER_RELATION r WHERE post_id ='"+postId+"')";
            rs = db.getRS(sql);
            List<AppUser> listId = convertList(rs);
            if (listId != null && listId.size() > 0) {
                for (AppUser au : listId) {
                    list.add(au.getId() + "");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(db!=null) {
                    db.close(rs);
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
            dao.close();
        }
        return list;
    }

    /**
     * 查询流程审批意见
     * @param id：主鍵id
     * @param taskId：task節點
     * @return
     */
    public static Map<String, Object> findTaskComment(String id, String taskId){
        AppRoleDao dao = new AppRoleDao();
        Map<String, Object> formart = new HashMap<String, Object>();
        Database db = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            db = new Database(dao.getConnection());
            String sql = "select * from base.FIXFLOW_RUN_TASKINSTANCE  where bizkey = '" + id + "' and node_id = '" + taskId + "' order by end_time desc nulls Last";
            rs = db.getRS(sql);

            List taskComments = resultSetToList(rs,FixflowRunTaskinstanceModel.class);;
            if(taskComments.size() > 0){
                Gson gson = new Gson();
                String jsonStr = gson.toJson(taskComments.get(0));
                formart = gson.fromJson(jsonStr,Map.class);
                System.out.println("审批意见-------->>>>" + formart.get("commandMessage"));
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(db!=null) {
                    db.close(rs);
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
            dao.close();
        }

        return formart;
    }

    /**
     * 获取当前用户orgid
     * @param userId
     * @return
     */
    public static String getAppUserOrgId(String userId) {
        AppRoleDao dao = new AppRoleDao();
        String userIds = "";
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT ORGID FROM base.APP_USER WHERE ID='"+userId+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> appUsers = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                appUsers.add(rowData);
            }

            for(Map m:appUsers){
                userIds += m.get("ORGID")+",";
            }
            if(userIds.length()>0){
                userIds = userIds.substring(0,userIds.length()-1);
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return userIds;
    }

    /**
     * 获取当前用户group
     * @param userId
     * @return
     */
    public static String getAppUserGroupId(String userId) {
        AppRoleDao dao = new AppRoleDao();
        String userIds = "";
        ResultSet rs = null;
        Database db = null;
        try {
            db = new Database(dao.getConnection());
            String sql = "SELECT GROUP_ID GROUPID FROM base.GROUP_POST_USER_RELATION WHERE USER_ID='" + userId + "'";
            rs = db.getRS(sql);

            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> appUsers = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                appUsers.add(rowData);
            }

            for(Map m:appUsers){
                userIds += m.get("GROUPID")+",";
            }
            if(userIds.length()>0){
                userIds = userIds.substring(0,userIds.length()-1);
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return userIds;
    }

    /**
     * 岗位跟用户id查找对应处理人
     * @param userId
     * @return
     */
    public static String getAppUserUserPostId(String userId,String postId) {
        AppRoleDao dao = new AppRoleDao();
        String userIds = "";
        ResultSet rs = null;
        Database db = null;
        try {
            db = new Database(dao.getConnection());
            String sql = "SELECT ID FROM base.APP_USER WHERE ID IN (SELECT R.USER_ID FROM base.GROUP_POST_USER_RELATION R WHERE POST_ID ='"
                    + postId + "' AND GROUP_ID  IN (SELECT GROUP_ID FROM base.GROUP_POST_USER_RELATION WHERE USER_ID ='" + userId + "'))";
            rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> appUsers = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                appUsers.add(rowData);
            }

            for(Map m:appUsers){
                userIds += m.get("ID")+",";
            }
            if(userIds.length()>0){
                userIds = userIds.substring(0,userIds.length()-1);
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return userIds;
    }

    /**根据用户查找岗位
     *
     */
    public static List<String> getPostId(String userId) {
        AppRoleDao dao = new AppRoleDao();
        String userIds = "";
        List<String> list = new ArrayList<>();
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT post_id POSTID FROM base.GROUP_POST_USER_RELATION WHERE user_id = '"+userId+"'order by post_id desc";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> appUsers = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                appUsers.add(rowData);
            }

            for(Map m:appUsers){
                userIds = m.get("POSTID")+"";
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

    /**
     * 投后事项，获取是否需要法务审批
     * @param
     */
    public static String selectSomeThing(String projId){
        AppRoleDao dao = new AppRoleDao();
        String legalApproval = null;
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT LEGAL_APPROVAL FROM EI.TZ_T_FP_THREEMEETING_INFO where PK_ID='"+projId+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> some = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                some.add(rowData);
            }
            for(Map m:some){
                legalApproval = m.get("LEGAL_APPROVAL")+"";
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return legalApproval;
    }
    /**
     * 投后事项，查询是否重大事项；1：一般事项，2：重大事项
     * @param
     */
    public static String selectSomeThingTwo(String projId){
        AppRoleDao dao = new AppRoleDao();
        String legalApproval = null;
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT EVENT_TYPE FROM EI.TZ_T_FP_THREEMEETING_INFO where PK_ID='"+projId+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> some = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                some.add(rowData);
            }
            for(Map m:some){
                legalApproval = m.get("EVENT_TYPE")+"";
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return legalApproval;
    }

    /**
     * 流程终止，注销,是否需要法务审批
     * @param stopId
     * @return
     */
    public static String selectSomeThingStopLawWorks(String stopId){
        AppRoleDao dao = new AppRoleDao();
        String legalApproval = null;
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT STOP_LAW_WORKS FROM EI.TZ_T_PROJ_STOP where STOP_ID='"+stopId+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> some = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                some.add(rowData);
            }
            for(Map m:some){
                legalApproval = m.get("STOP_LAW_WORKS")+"";
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return legalApproval;
    }
    /**
     * 流程终止，注销,是否重大事项
     * @param stopId
     * @return
     */
    public static String selectSomeThingStopGreat(String stopId){
        AppRoleDao dao = new AppRoleDao();
        String legalApproval = null;
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT STOP_GREAT FROM EI.TZ_T_PROJ_STOP where STOP_ID='"+stopId+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> some = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                some.add(rowData);
            }
            for(Map m:some){
                legalApproval = m.get("STOP_GREAT")+"";
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return legalApproval;
    }

    /**
     * 退出立项,查询是否重大事项
     * @param Id
     * @return
     */
    public static String selectOneProjAppInfoQuit(String Id){
        AppRoleDao dao = new AppRoleDao();
        String legalApproval = null;
        try {
            Database db = new Database(dao.getConnection());
            String sql = "SELECT STOP_GREAT FROM EI.TZ_T_PROJ_APP_INFO_QUIT where QUIT_PROJ_ID='"+Id+"'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String,Object>> some = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1),rs.getObject(1));
                some.add(rowData);
            }
            for(Map m:some){
                legalApproval = m.get("STOP_GREAT")+"";
            }
            db.close(rs);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return legalApproval;
    }




    /**
     * 改变状态
     * @return
     */
    public static void updateStatus(String sql){
        AppRoleDao dao = new AppRoleDao();
        Database db = null;
        ResultSet rs = null;
        try {
            db = new Database(dao.getConnection());
            //--0:草稿；1：审批中；2：审批通过；3：审批不通过；-1：已经终止" +
            //String sql3 = "update TZ_T_COO_CONDITION SET STATUS = '"+1+"' where condition_id='"+1+"'";
            rs = db.getRS(sql);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(db!=null) {
                    db.close(rs);
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
            dao.close();
        }
    }

    /**
     * 遍历查詢結果
     * @param rs
     * @param <E>
     * @return
     * @throws SQLException
     */
    private static <E> List<E> convertList(ResultSet rs) throws SQLException{
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        List<E>  user = new ArrayList<>();
        while (rs.next()) {
            AppUser us = null;
            us = new AppUser();
            int id = rs.getInt("ID");//id
            String name = rs.getString("NAME");//name
            String loginname = rs.getString("LOGINNAME");//用戶名
            String password = rs.getString("PASSWORD");//密碼
            us.setId(id);
            us.setName(name);
            us.setLoginname(loginname);
            us.setPassword(password);
            user.add((E) us);
        }
        return user;
    }


    private static  List<HashMap> convertListMap(ResultSet rs) throws SQLException{
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {//遍历行，next()方法返回值为Boolean，当存在数据行返回true,反之false
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                if(!"END_TIME".equals(md.getColumnName(i))&&!"CREATE_TIME".equals(md.getColumnName(i))){
                    rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
                }
            }
            list.add(rowData);
        }
        return list;
    }

    /**
     * 将resultSet封装成集合
     * @param rs
     * @param clazz
     * @param <T>
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> resultSetToList(ResultSet rs, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        //结果集的元素对象
        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的元素个数
        int colCount = rsmd.getColumnCount();
        //返回结果的列表集合
        List list = new ArrayList();
        //业务对象的属性数组
        Field[] fields = clazz.getDeclaredFields();
        while(rs.next()){//对每一条记录进行操作
            Object obj = clazz.newInstance();//构造业务对象实体
            //将每一个字段取出进行赋值
            for(int i = 1;i<=colCount;i++){
                Object value = rs.getObject(i);
                //寻找该列对应的对象属性
                for(int j=0;j<fields.length;j++){
                    Field f = fields[j];
                    //如果匹配进行赋值
                    if(f.getName().equalsIgnoreCase(rsmd.getColumnName(i).replace("_", ""))){
                        try{
                            if (value != null && "java.math.BigDecimal".equals(value.getClass().getName())) {
                                value = Long.parseLong(value.toString());
                            }
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                           /* System.out.println("value.getClass().getName()--->>"+value.getClass().getName());*/
                            if (value != null && "oracle.sql.TIMESTAMP".equals(value.getClass().getName())) {
                                value = df.parse(value.toString());
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                        break;
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }

    /**
     * 获取当前登陆人-投基金
     * @return
     */
    public static String getUserId(){
        //long userId = new BaseController().getLoginUser().getUserId();//获取登陸人id
        //System.out.println("当前登陆人测试----------->"+userId);
        //发起人APP_USER的id,应该是取当前登陆人
        //return String.valueOf(userId);
        return String.valueOf(109);
    }
    ////项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
    public static String getUserId(String type){
        String userId = null;
        if("1".equals(type)){
            //long userIdS = new BaseController().getLoginUser().getUserId();//获取登陸人id
            //System.out.println("当前登陆人测试----------->"+userId);
            //发起人APP_USER的id,应该是取当前登陆人
            //return String.valueOf(userId);
            userId = "112";//(112)朱鑫文、(113)钱子亮
        }else if("2".equals(type)){
            //long userIdS = new BaseController().getLoginUser().getUserId();//获取登陸人id
            //System.out.println("当前登陆人测试----------->"+userId);
            //发起人APP_USER的id,应该是取当前登陆人
            //return String.valueOf(userId);
            userId = "109";
        }
        return userId;
    }

    /**
     * 生成主键
     * @return
     */
    public static String generateKey(){
        String id = null;
        String id1 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        int id2 = (int) ((Math.random() * 9 + 1) * 10000);
        id = id1 + id2;
        System.out.println("###---->>" + id);
        return id;
    }

    //******************************万元转亿元*********************************************************
    private static final Double MILLION = 1000000.0;
    private static final Double MILLIONS = 10000.0;
    private static final Double BILLION = 100000000.0;
    private static final String MILLION_UNIT = "";
    private static final String BILLION_UNIT = "亿";
    /**
     * 将数字转换成以万为单位或者以亿为单位，因为在前端数字太大显示有问题
     *
     * @author
     * @version 1.00.00
     * @param amount
     * @return
     */
    public String amountConversion(double amount) {
        // 最终返回的结果值
        String result = String.valueOf(amount);
        // 四舍五入后的值
        double value = 0;
        // 转换后的值
        double tempValue = 0;
        // 金额大于1万小于1亿
        if (amount >= MILLIONS && amount < BILLION) {
            tempValue = amount / MILLION;
            //四舍五入保留两位小数
            value = formatNumber(tempValue, 2, true);
            // 如果值刚好是10000万，则要变成1亿
            if (value == MILLION) {
                result = zeroFill(value / MILLION) + BILLION_UNIT;
            } else {
                result = zeroFill(value) + MILLION_UNIT;
            }
        }else if (amount > BILLION) {// 金额大于1亿
            tempValue = amount / BILLION;
            value = formatNumber(tempValue, 4, true);
            result = zeroFill(value);
            //转换成百万
            double dd  = Double.parseDouble(result)*100;
            double dd2 = formatNumber(dd, 2, true);
            result = String.valueOf(dd2);
        } else {
            double d = amount/MILLION;
            BigDecimal dTwo = new BigDecimal(d+"");
            result = String.format("%.6f", dTwo);
        }
        return result;
    }

    /**
     * 对数字进行四舍五入，保留2位小数
     *
     * @author
     * @version 1.00.00
     *
     * @date 2018年1月18日
     * @param number
     *            要四舍五入的数字
     * @param decimal
     *            保留的小数点数
     * @param rounding
     *            是否四舍五入
     * @return
     */
    public static Double formatNumber(double number, int decimal,
                                      boolean rounding) {
        BigDecimal bigDecimal = new BigDecimal(number);

        if (rounding) {
            return bigDecimal.setScale(decimal, RoundingMode.HALF_UP)
                    .doubleValue();
        } else {
            return bigDecimal.setScale(decimal, RoundingMode.DOWN)
                    .doubleValue();
        }
    }

    /**
     * 对四舍五入的数据进行补0显示，即显示.00
     *
     * @author
     * @version 1.00.00
     *
     * @date 2018年1月23日
     * @return
     */
    public static String zeroFill(double number) {
        String value = String.valueOf(number);

        if (value.indexOf(".") < 0) {
            value = value + ".00";
        } else {
            String decimalValue = value.substring(value.indexOf(".") + 1);
            if (decimalValue.length() < 2) {
                value = value + "0";
            }
        }
        return value;
    }

    /**
     * 查询流程图defId
     * @param ：processinstanceId
     * @return
     */
    public static String getDefId(String processinstanceId) {
        AppRoleDao dao = new AppRoleDao();
        String defId = "";
        try {
            //System.out.println("##1--进入查询--查询流程图defId---------------------" + processinstanceId);
            Database db = new Database(dao.getConnection());
            String sql = "SELECT PROCESSDEFINITION_ID FROM BASE.FIXFLOW_RUN_PROCESSINSTANCE WHERE PROCESSINSTANCE_ID='" + processinstanceId + "'";
            ResultSet rs = db.getRS(sql);
            ResultSetMetaData md = rs.getMetaData();
            List<Map<String, Object>> appUsers = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>(1);
                rowData.put(md.getColumnName(1), rs.getObject(1));
                appUsers.add(rowData);
            }
            for (Map m : appUsers) {
                //只取一条数据
                if("".equals(defId)){
                    defId += m.get("PROCESSDEFINITION_ID") + ",";
                }
            }
            if (defId.length() > 0) {
                defId = defId.substring(0, defId.length() - 1);
            }
            db.close(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        //System.out.println("##2--结束查询流程图defId---------------------" + defId);
        return defId;
    }

    /**
     * 测试方法入口
     *
     * @author
     * @version 1.00.00
     *
     * @date 2018年1月18日
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //amountConversion(120);
        //amountConversion(18166.35);
        new UimUtilsUserId().amountConversion(516713.87);
        // amountConversion(129887783.5);
    }




}
