package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BdTAttaItemModel;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.service.BdTAttaItemService;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.ProjInfoService;
import com.ppmg.ei.util.otherUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("bdTAttaItemService")
@Service(interfaceClass = BdTAttaItemService.class)
public class BdTAttaItemServiceImpl extends BaseServiceImpl<BdTAttaItemModel> implements BdTAttaItemService {
    @Resource(name = "bdTAttaItemService")
    private BdTAttaItemService bdTAttaItemService;
    @Resource(name = "projInfoService")
    private ProjInfoService projInfoService;

    @Resource(name = "fundInfoService")
    private FundInfoService fundInfoService;

    public BdTAttaItemServiceImpl() {
        this.setNamespace("BdTAttaItem");
    }

    @Override
    public String getAttaItemId() {
        return (String) this.selectOne("getAttaItemId", "");
    }

    @Override
    public List<BdTAttaItemModel> getAttaItemList(Map<String, String> map) {
        List<BdTAttaItemModel> list = this.selectList("getAttaItemListByFundId", map);
        if (list.size() > 0) {
            for (BdTAttaItemModel m : list) {
                if ("0".equals(m.getIsLeaf())) {
                    List<BdTAttaItemModel> child = new ArrayList<BdTAttaItemModel>();
                    for (BdTAttaItemModel m1 : list) {
                        if (m1.getParentId().equals(m.getItemId())) {
                            child.add(m1);
                        }
                    }
                    m.setChildrenList(child);
                }
            }
        }
        return list;
    }

    /**
     * 插入附件分类表 BD_T_ATTA_ITEM
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void addEiTAttachment(String projId, String projType, String userId) throws Exception {
        if (!"init".equals(projType)) {
            List<BdTAttaItemModel> list = bdTAttaItemService.selectList(projId);
            if (list.size() > 0) {
                System.out.println("-------------------数据库已经有该数据--------------------");
                return;
            }
        }
        //projType项目类型（1：投项目，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
        String ItemId = null;
        if (!"init".equals(projType)) {
            ItemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
        }
        String[] itemIds = null;
        String[] itemNames = null;
        String ItemType = null;
        //itemType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金
        if ("10".equals(projType)) {
            //基金：母基金管理
            ItemType = "10";
            itemIds = new String[]{ItemId, "", "", "", "", "", "", ""};
            itemNames = new String[]{"附件分类", "基本信息", "历史沿革及增值服务", "定期报告", "合伙协议及章程", "基金管理办法", "股东会/董事会/合伙人会议", "其他相关资料"};
        }
        else if ("11".equals(projType)) {
            //基金：母基金管理
            ItemType = "11";
            itemIds = new String[]{ItemId, "", "", "", "", "", "", ""};
            itemNames = new String[]{"附件分类", "基本信息", "历史沿革及增值服务", "定期报告", "合伙协议及章程", "基金管理办法", "股东会/董事会/合伙人会议", "其他相关资料"};
        } else if ("12".equals(projType)) {
            //基金:代管基金管理
            ItemType = "12";
            itemIds = new String[]{ItemId, "", "", "", "", "", "", ""};
            itemNames = new String[]{"附件分类", "基本信息", "历史沿革及增值服务", "定期报告", "合伙协议及章程", "基金管理办法", "股东会/董事会/合伙人会议", "其他相关资料"};
        } else if ("13".equals(projType)) {
            //省政府基金，母基金
            ItemType = "13";
            itemIds = new String[]{ItemId, "", "", "", "", ""};
            itemNames = new String[]{"附件分类", "合伙协议", "管理报告", "合伙人会议相关材料（会议通知、议案、决议、记录)", "投决会（会议通知、议案、决议、表决票）", "其他相关资料"};
        } else if ("1".equals(projType)) {
            //项目
            ItemType = "1";
            itemIds = new String[]{ItemId, "", "", "", "", "", ""};
            itemNames = new String[]{"附件分类", "项目立项", "项目决策", "合同签署", "项目出资", "投后管理", "项目退出"};
        } else if ("2".equals(projType)) {
            //基金
            ItemType = "3";
            itemIds = new String[]{ItemId, "", "", "", "", ""};
            itemNames = new String[]{"附件分类", "基金立项", "投资决策", "合同签署", "基金出资", "基金退出"};
        } else if ("init".equals(projType)) {
            ProjInfoModel pf = new ProjInfoModel();
            //查询所有基金
            pf.setGroupId(2L);
            List<ProjInfoModel> listProjInfo = projInfoService.selectList(pf);
            FundInfoModel ff = new FundInfoModel();
            ff.setGroupId(2L);
            List<FundInfoModel> listFundInfo = fundInfoService.selectList(ff);
            //批量初始化，基金：母基金，代管基金；
            if (listFundInfo.size() > 0) {
                for (FundInfoModel ffModel : listFundInfo) {
                    ItemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
                    String fundIdF = ffModel.getFundId();
                    List<BdTAttaItemModel> listYesOrNo = bdTAttaItemService.selectList(fundIdF);
                    if (listYesOrNo.size() > 0) {
                        System.out.println("-------------------数据库已经有该数据--------------------");
                        continue;
                    }
                    String status = ffModel.getStatus();
                    Long groupId = ffModel.getGroupId();
                    String oldSubPlatProp = ffModel.getOldSubPlatProp();
                    //2母基金，3代管基金,4参股子基金，5.专项基金
                    if (groupId != null && status != null && groupId == 2 && status != "2") {
                        if ("2".equals(oldSubPlatProp)) {
                            //基金：母基金管理
                            ItemType = "11";
                            itemIds = new String[]{ItemId, "", "", "", "", "", "", ""};
                            itemNames = new String[]{"附件分类", "基本信息", "历史沿革及增值服务", "定期报告", "合伙协议及章程", "基金管理办法", "股东会/董事会/合伙人会议", "其他相关资料"};
                        } else if ("3".equals(oldSubPlatProp)) {
                            //基金:代管基金管理
                            ItemType = "12";
                            itemIds = new String[]{ItemId, "", "", "", "", "", "", ""};
                            itemNames = new String[]{"附件分类", "基本信息", "历史沿革及增值服务", "定期报告", "合伙协议及章程", "基金管理办法", "股东会/董事会/合伙人会议", "其他相关资料"};
                        } else {
                            itemIds = null;
                        }
                    }
                    //执行插入
                    String[] parentIds = {"0", ItemId, ItemId, ItemId, ItemId, ItemId, ItemId, ItemId};
                    List<BdTAttaItemModel> itemList = new ArrayList<>();
                    if (itemIds != null) {
                        for (int i = 0; i < itemIds.length; i++) {
                            String itemId = itemIds[i];
                            if ("".equals(itemId)) {
                                itemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
                            }
                            BdTAttaItemModel itemModel = new BdTAttaItemModel();
                            itemModel.setItemId(itemId);
                            itemModel.setItemName(itemNames[i]);
                            itemModel.setDescription(itemNames[i]);
                            itemModel.setParentId(parentIds[i]);
                            itemModel.setItemType(ItemType);
                            itemModel.setProjectOrFundId(fundIdF);
                            itemModel.setStatus("0");
                            itemModel.setEditable("1");
                            itemModel.setCreateBy(userId);
                            itemModel.setCreateDt(new Date());
                            itemModel.setUpdateBy("admin");
                            itemModel.setUpdateDt(new Date());
                            itemList.add(itemModel);
                        }
                        bdTAttaItemService.insertBatch(itemList);
                    }
                }
            }
            //批量初始化，投基金，投投项目
            if (listProjInfo.size() > 0) {
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                for (ProjInfoModel pfModel : listProjInfo) {
                    ItemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
                    String projIdS = pfModel.getProjId();
                    List<BdTAttaItemModel> listYesOrNo = bdTAttaItemService.selectList(projIdS);
                    if (listYesOrNo.size() > 0) {
                        System.out.println("-------------------数据库已经有该数据--------------------");
                        continue;
                    }
                    String status = pfModel.getStatus();
                    Long groupId = pfModel.getGroupId();
                    String projTypeSerch = pfModel.getProjType();
                    if (groupId != null && status != null && groupId == 2 && status != "2") {
                        if ("2".equals(projTypeSerch)) {
                            //投基金
                            ItemType = "3";
                            itemIds = new String[]{ItemId, "", "", "", "", ""};
                            itemNames = new String[]{"附件分类", "基金立项", "投资决策", "合同签署", "基金出资", "基金退出"};
                        } else if ("1".equals(projTypeSerch)) {
                            //投项目
                            ItemType = "1";
                            itemIds = new String[]{ItemId, "", "", "", "", "", ""};
                            itemNames = new String[]{"附件分类", "项目立项", "项目决策", "合同签署", "项目出资", "投后管理", "项目退出"};
                        } else {
                            itemIds = null;
                        }
                    }
                    //执行插入
                    String[] parentIds = {"0", ItemId, ItemId, ItemId, ItemId, ItemId, ItemId, ItemId};
                    List<BdTAttaItemModel> itemList = new ArrayList<>();
                    if (itemIds != null) {
                        for (int i = 0; i < itemIds.length; i++) {
                            String itemId = itemIds[i];
                            if ("".equals(itemId)) {
                                itemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
                            }
                            BdTAttaItemModel itemModel = new BdTAttaItemModel();
                            itemModel.setItemId(itemId);
                            itemModel.setItemName(itemNames[i]);
                            itemModel.setDescription(itemNames[i]);
                            itemModel.setParentId(parentIds[i]);
                            itemModel.setItemType(ItemType);
                            itemModel.setProjectOrFundId(projIdS);
                            itemModel.setStatus("0");
                            itemModel.setEditable("1");
                            itemModel.setCreateBy(userId);
                            itemModel.setCreateDt(new Date());
                            itemModel.setUpdateBy("admin");
                            itemModel.setUpdateDt(new Date());
                            itemList.add(itemModel);
                        }
                        bdTAttaItemService.insertBatch(itemList);
                    }
                }
            }
        }
        //单个插入
        if (!"init".equals(projType)) {
            String[] parentIds = {"0", ItemId, ItemId, ItemId, ItemId, ItemId, ItemId, ItemId};
            List<BdTAttaItemModel> itemList = new ArrayList<>();
            if (itemIds != null) {
                for (int i = 0; i < itemIds.length; i++) {
                    String itemId = itemIds[i];
                    if ("".equals(itemId)) {
                        itemId = otherUtil.getSeqNextValString("EI.SEQ_BD_T_ATTA_ITEM");
                    }
                    BdTAttaItemModel itemModel = new BdTAttaItemModel();
                    itemModel.setItemId(itemId);
                    itemModel.setItemName(itemNames[i]);
                    itemModel.setDescription(itemNames[i]);
                    itemModel.setParentId(parentIds[i]);
                    itemModel.setItemType(ItemType);
                    itemModel.setProjectOrFundId(projId);
                    itemModel.setStatus("0");
                    itemModel.setEditable("1");
                    itemModel.setCreateBy(userId);
                    itemModel.setCreateDt(new Date());
                    itemModel.setUpdateBy("admin");
                    itemModel.setUpdateDt(new Date());
                    itemList.add(itemModel);
                }
                bdTAttaItemService.insertBatch(itemList);
            }
        }
    }

    //删除EI_T_ATTACHMENT表初始化数据
    @Override
    public void delectEiTAttachment(String projId) {
        this.delete("delectEiTAttachment", projId);
    }

    //查询EI_T_ATTACHMENT表初始化数据
    @Override
    public List<BdTAttaItemModel> selectList(String projId) {
        return this.selectList("selectListAttaItem", projId);
    }


}