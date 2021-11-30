package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.BdTAttaItemModel;
import com.ppmg.ei.model.CommTCodeModel;
import com.ppmg.ei.model.GroupModel;
import com.ppmg.ei.model.PlatformInfoModel;
import com.ppmg.ei.service.BdTAttaItemService;
import com.ppmg.ei.service.CommTCodeService;
import com.ppmg.ei.service.GroupService;
import com.ppmg.ei.service.PlatformInfoService;
import com.ppmg.ei.util.otherUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Component("platformInfoService")
@Service(interfaceClass = PlatformInfoService.class)
public class PlatformInfoServiceImpl extends BaseServiceImpl<PlatformInfoModel> implements PlatformInfoService {

	public PlatformInfoServiceImpl(){
		this.setNamespace("PlatformInfo");
	}

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	public PlatformInfoModel getPlatformInfoByPlatId(String pkId) {
		PlatformInfoModel model = this.selectById(pkId);
		Map<String, String> param = new HashMap<String, String>();
		param.put("PK_ID", pkId);
		if(model != null){
			param.put("PLAT_CODE", model.getPlatCode());
		}
		return (PlatformInfoModel)this.selectOne("getPlatformInfoByPlatId", param);
	}
	@Resource(name="platformInfoService")
	private PlatformInfoService platformInfoService;

	@Resource(name="groupService")
	private GroupService groupService;

	@Resource(name="commTCodeService")
	private CommTCodeService commTCodeService;

	@Resource(name="bdTAttaItemService")
	private BdTAttaItemService bdTAttaItemService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertCodeAndOrgAndRole(PlatformInfoModel model,String loginUserId) throws Exception {

		/**
		 * 1.插入主表 PT_T_PLATFORM_INFO
		 */
		String pkId = serialnoService.getSequence("EI.PT_T_PLATFORM_INFO");
		model.setPkId(pkId);
		String entId = model.getEntId();
		model.setPlatCode(entId);
		model.setStepDate(model.getStepDate());
		model.setDelFlag("0");
		platformInfoService.insert(model);

		/**
		 * 2.插入组织表 APP_GROUP
		 */
	/*	String orgId = 	String.valueOf(groupService.selectOne("getOrgId",""));
		GroupModel appGroupModel = new GroupModel();
		String id = serialnoService.getSequence("BASE.APP_GROUP ");
		appGroupModel.setId(Long.parseLong(id));
		String platShortName = model.getPlatShortName();
		appGroupModel.setName(platShortName);
		appGroupModel.setGuid(java.util.UUID.randomUUID().toString());
		appGroupModel.setCreateddate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		appGroupModel.setFatherid(Long.parseLong("1000"));
		appGroupModel.setGrouptype(Long.parseLong("0"));
		appGroupModel.setGrouplevel(Long.parseLong("2"));
		appGroupModel.setSortorder(Long.parseLong(orgId));
		appGroupModel.setOrgcode(orgId);
		appGroupModel.setOrgid(orgId);
		appGroupModel.setOutFlag("0");
		appGroupModel.setOrgcode(orgId);
		appGroupModel.setSuporgid("1000000");
		appGroupModel.setUnitType("0");
		groupService.insert(appGroupModel);*/

		/**
		 * 3.插入码值表 COMM_T_CODE-266
		 */
	/*	CommTCodeModel commTCodeModel = new CommTCodeModel();
		String codeId = otherUtil.getSeqNextValString("BASE.SEQ_CODE_ID");
		commTCodeModel.setCodeId(codeId);
		commTCodeModel.setParentId("266");
		String codeName = entId;
		commTCodeModel.setCodeName(codeName);
		commTCodeModel.setCodeValue(codeName);
		commTCodeModel.setCodeDesc(platShortName);
		commTCodeModel.setOrderNo(Long.parseLong(codeName));
		commTCodeModel.setRwFlag("1");
		commTCodeModel.setStatus("0");
		commTCodeModel.setCreateBy(loginUserId);
		commTCodeModel.setCreateDt(new Date());
		commTCodeModel.setUpdateBy(loginUserId);
		commTCodeModel.setUpdateDt(new Date());
		commTCodeService.insert(commTCodeModel);*/

		/**
		 * 3.插入码值表 COMM_T_CODE-279
		 */
	/*	CommTCodeModel commTCodeModel2 = new CommTCodeModel();
		String codeId279 = otherUtil.getSeqNextValString("BASE.SEQ_CODE_ID");  //SequenceUtil.getSequence("BASE.SEQ_CODE_ID");
		commTCodeModel2.setCodeId(codeId279);
		commTCodeModel2.setParentId("279");
		commTCodeModel2.setCodeName(platShortName);
		commTCodeModel2.setCodeValue(codeName);
		commTCodeModel2.setCodeDesc(platShortName);
		commTCodeModel2.setOrderNo(Long.parseLong(codeName));
		commTCodeModel2.setRwFlag("0");
		commTCodeModel2.setStatus("0");
		commTCodeModel2.setCreateBy(loginUserId);
		commTCodeModel2.setCreateDt(new Date());
		commTCodeModel2.setUpdateBy(loginUserId);
		commTCodeModel2.setUpdateDt(new Date());
		commTCodeService.insert(commTCodeModel2);*/


		/**
		 * 4.插入一些基础角色
		 * 4-1. 添加所需角色
		 * 	a) base.app_role
		 * 	b) base.app_applicationrole
		 * 4-2. 新增平台数据权限
		 * 	a) base.app_permission
		 * 	b) base.app_applicationpermission
		 * 	c) app_rolepermission
		 */

		/**
		 * 5.插入附件分类表 BD_T_ATTA_ITEM
		 */
		String ItemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
		String[] itemIds = {ItemId,"","",""};
		String[] itemNames = {"附件分类","经营计划","合同签署","其他"};
		String[] parentIds = {"0",ItemId,ItemId,ItemId};
		List<BdTAttaItemModel> itemList = new ArrayList<BdTAttaItemModel>();
		for(int i=0; i<itemIds.length; i++){
			String itemId = itemIds[i];
			if("".equals(itemId)){
				itemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
			}
			BdTAttaItemModel itemModel = new BdTAttaItemModel();
			itemModel.setItemId(itemId);
			itemModel.setItemName(itemNames[i]);
			itemModel.setDescription(itemNames[i]);
			itemModel.setParentId(parentIds[i]);
			itemModel.setItemType("4");
			itemModel.setProjectOrFundId(model.getPkId());
			itemModel.setStatus("0");
			itemModel.setEditable("1");
			itemModel.setCreateBy(loginUserId);
			itemModel.setCreateDt(new Date());
			itemModel.setUpdateBy(loginUserId);
			itemModel.setUpdateDt(new Date());
			itemList.add(itemModel);
		}
		bdTAttaItemService.insertBatch(itemList);
	}



}