/**
 * 
 */
package com.ppmg.ei.utils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.founder.uim.dao.AppRoleDao;
import org.apache.log4j.Logger;

import com.founder.core.util.Database;
import com.founder.core.util.StringUtils;

/**
 * 
 * 
 * @author keyi Jan 20, 2014
 */
public class MyMessageUtil {
	static Logger logger = Logger.getLogger(MyMessageUtil.class);

	/**
	 * 新增我的消息
	 *
	 * @return Map<ID,NAME>
	 * @throws Exception
	 */
	public static void addMyMessage(MyMessageBean myMessageBean)
			throws Exception {
		AppRoleDao dao = new AppRoleDao();
		com.founder.uim.database.Database db = null;
		//Database db = new Database("YHKG_DS_BASE");
		db = new com.founder.uim.database.Database(dao.getConnection());
		String sql = "INSERT INTO BASE.YH_SYS_MESSAGE (MSG_ID, "
				+ "                                 MSG_CONTEXT, "
				+ "                                 MSG_URL, "
				+ "                                 RECEIVE_USER_ID, "
				+ "                                 RECEIVE_USER_NAME, "
				+ "                                 MSG_TYPE, "
				+ "                                 CREATE_DT, "
				+ "                                 CREATE_BY, "
				+ "                                 UPDATE_DT, "
				+ "                                 UPDATE_BY, "
				+ "                                 STATUS, "
				+ "                                 MSG_FROM, "
				+ "                                 PARENT_ID, "
				+ "                                 ATTA_FILE, " +

				"                                 MSG_TITLE) "
				+ "     VALUES (SEQ_YH_SYS_MESSAGE.NEXTVAL, "
				+ "             '"
				+ myMessageBean.getMSG_CONTEXT()
				+ "', "
				+ "             '"
				+ myMessageBean.getMSG_URL()
				+ "', "
				+ "             '"
				+ myMessageBean.getRECEIVE_USER_ID()
				+ "', "
				+ "             '"
				+ ""
				+ "', "
				+ "             '"
				+ myMessageBean.getMSG_TYPE()
				+ "', "
				+ "             SYSDATE, "
				+ "             '"
				+ myMessageBean.getCREATE_BY()
				+ "', "
				+ "             SYSDATE, "
				+ "             '"
				+ myMessageBean.getCREATE_BY()
				+ "', "
				+ "             '0', "
				+ "             '"
				+ myMessageBean.getMSG_FROM()
				+ "', "
				+ "             '"
				+ myMessageBean.getPARENT_ID()
				+ "', "
				+ "             '"
				+ myMessageBean.getATTA_FILE()
				+ "', "
				+ "             '" + myMessageBean.getMSG_TITLE() + "') ";
		try {
			db.execSqlUpdate(sql);
			db.commit();

		} catch (Throwable t) {
			t.printStackTrace();

		} finally {
			if (db != null) {
				db.cleanup();
				db = null;
			}
		}

	}

	/**
	 * 流程最后推送推送信息
	 * 
	 * @param pk
	 * @param startUser
	 * @throws Exception
	 */
	public static void sendMessage(String db, String pk, String startUser,
			String pkCol, String tableName, String instCol,
			String workflowNodeName) throws Exception {

		/*
		 * 1.查询流程ID
		 */
		String PROCESSINSTANCE_ID = "";
		String selectSql1 = "SELECT  " + instCol + "  FROM " + tableName
				+ " WHERE  " + pkCol + " = '" + pk + "' ";
		List<Map<String, Object>> list1 = MyMessageUtil.getRsResultList(db,
				selectSql1);
		if (list1 != null && !list1.isEmpty()) {
			Map<String, Object> map = list1.get(0);
			PROCESSINSTANCE_ID = (String) map.get("PROCESS_INST_ID");
		}
		/*
		 * 2.查询流程信息
		 */
		String formUriView = "";
		String title = "";
		String sqlUrlview = "SELECT T1.* "
				+ "  FROM FIXFLOW_RUN_TASKINSTANCE T1 " + " WHERE     1 = 1 "
				+ "       AND T1.PROCESSINSTANCE_ID = '" + PROCESSINSTANCE_ID
				+ "' " + "       AND T1.NODE_ID = '" + workflowNodeName + "' ";
		List<Map<String, Object>> list2 = MyMessageUtil.getRsResultList(
				"YHKG_DS_BASE", sqlUrlview);
		if (list2 != null && !list2.isEmpty()) {

			Map<String, Object> map = list2.get(0);
			title = "完成审批" + "【" + (String) map.get("DESCRIPTION") + "】";
			String FORMURIVIEW = String.valueOf(map.get("FORMURIVIEW"));
			FORMURIVIEW = URLEncoder.encode(FORMURIVIEW, "UTF-8");
			String TASKINSTANCE_ID = String.valueOf(map.get("TASKINSTANCE_ID"));
			String BIZKEY = String.valueOf(map.get("BIZKEY"));
			String PROCESSDEFINITION_ID = String.valueOf(map
					.get("PROCESSDEFINITION_ID"));
			formUriView = "/sysweb" + "/editor/editor.jsp?" + "dealMark=view"
					+ "&showToolBar=false"
					+ "&xmlFileName=workflowEditorConfig" + "&groupId=2"
					+ "&taskId=" + TASKINSTANCE_ID + "&instId="
					+ PROCESSINSTANCE_ID + "&defId=" + PROCESSDEFINITION_ID
					+ "&bizKey=" + BIZKEY + "&formUri=" + FORMURIVIEW
					+ "&formUriView=" + FORMURIVIEW;
		}
		/*
		 * 3.消息推送
		 */
		if (StringUtils.isNotEmpty(startUser)) {
			String userId = startUser;
			MyMessageBean myMessageBean = new MyMessageBean();
			myMessageBean.setMSG_TITLE(title);
			myMessageBean.setMSG_CONTEXT(title);
			myMessageBean.setRECEIVE_USER_ID(userId);
			myMessageBean.setCREATE_BY(startUser);
			myMessageBean.setMSG_URL(formUriView);
			myMessageBean.setMSG_TYPE("0");
			myMessageBean.setMSG_FROM("");
			myMessageBean.setPARENT_ID("");
			myMessageBean.setATTA_FILE("");

			MyMessageUtil.addMyMessage(myMessageBean);
		}

	}

	/**
	 * 根据JNDI查询
	 * 
	 * @param JNDI
	 * @param sql
	 * @return
	 */
	public static List<Map<String, Object>> getRsResultList(String JNDI,
			String sql) {
		List<Map<String, Object>> returnList = null;
		Database db = null;
		try {
			db = new Database(JNDI);
			returnList = db.getResultList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.cleanup();
		}
		return returnList;
	}
}
