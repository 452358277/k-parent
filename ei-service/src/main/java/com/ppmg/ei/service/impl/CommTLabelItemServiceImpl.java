package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.bean.LabelSearchBean;
import com.ppmg.ei.model.CommTEntLabelItemModel;
import com.ppmg.ei.model.CommTLabelItemModel;
import com.ppmg.ei.model.EntLabelModel;
import com.ppmg.ei.service.CommTEntLabelItemService;
import com.ppmg.ei.service.CommTLabelItemService;
import com.ppmg.ei.service.EntLabelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("commTLabelItemService")
@Service(interfaceClass = CommTLabelItemService.class)
public class CommTLabelItemServiceImpl extends BaseServiceImpl<CommTLabelItemModel> implements CommTLabelItemService {

	@Resource
	private CommTLabelItemService commTLabelItemService;

    @Resource
    private CommTEntLabelItemService commTEntLabelItemService;

    @Resource
    private EntLabelService entLabelService;



	public CommTLabelItemServiceImpl(){
		this.setNamespace("CommTLabelItem");
	}



	@Override
	public void saveItemInfo(List<LabelSearchBean> labels,String userId) {
		for(LabelSearchBean model:labels){
			//标签下的属性
            //1,2  1,3,4  1,2  3,4
            String [] labName=model.getNames().split(",");
            List<String> list =Arrays.asList(labName);
            List<String> list1 =Arrays.asList(labName);
            List<String>  list3=Arrays.asList(labName);
			CommTLabelItemModel commTLabelItemModel=new CommTLabelItemModel();
			commTLabelItemModel.setLabelId(model.getLabelId());
			//原来属性个数
			List<CommTLabelItemModel> label=commTLabelItemService.selectList(commTLabelItemModel);
            //原来属性个数
            List<String> propertyS=new ArrayList<>();
            List<String> propertyStr=new ArrayList<>();
            for(CommTLabelItemModel mo :label){
                propertyS.add(mo.getProperty());
                propertyStr.add(mo.getProperty());
            }

            propertyS.retainAll(list);
            System.out.print("交集为"+propertyS);
            if(label!=null && !label.isEmpty()){
                //有交集且交集如实例  1,2， 1,2 或者 1.2,3
                if(propertyS!=null&&!propertyS.isEmpty()){
                    if(label.size()==propertyS.size()){
                        //新数据去除重复的数据
                        List<String> listR=listrem(list1,propertyS);
                        //list1.removeAll(propertyS);
                        if(listR!=null&&!listR.isEmpty()){
                            for(String list2:listR){
                                if(StringUtils.isNotEmpty(list2)){
                                    CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
                                    commTLabelItem.setLabelId(model.getLabelId());
                                    commTLabelItem.setProperty(list2);
                                    commTLabelItem.setItemId(UUID.randomUUID().toString());
                                    commTLabelItem.setStatus("0");
                                    commTLabelItem.setCreateBy(userId);
                                    commTLabelItem.setCreateDt(new Date());
                                    commTLabelItem.setUpdateBy(userId);
                                    commTLabelItem.setUpdateDt(new Date());
                                    commTLabelItemService.insert(commTLabelItem);
                                }


                            }
                        }
                    }
                    //有交集且交集如实例  1,3，7  1,4,5
                    if(propertyS.size()<label.size()){
                     //老数据先取出交集部分，删除多余部分 3，3
                        //propertyStr.removeAll(propertyS);
                        //propertyStr是原来的数据和propertyS
                        List<String> listRe=listrem(propertyStr,propertyS);
                        System.out.print("**********listRe****************"+listRe);
                        System.out.print("**********listRe.size()****************"+listRe.size());
                        for(String o:listRe){
                           	CommTLabelItemModel commTLabe3=new CommTLabelItemModel();
                            commTLabe3.setLabelId(model.getLabelId());
                            commTLabe3.setProperty(o);
                            if(StringUtils.isNotEmpty(model.getLabelId())&&StringUtils.isNotEmpty(o)){
                                commTLabelItemService.delete(commTLabe3);
                             /*   CommTLabelItemModel  b= commTLabelItemService.selectBy(commTLabe3);
                                CommTEntLabelItemModel commTEntLabelItemModel=new CommTEntLabelItemModel();
                                commTEntLabelItemModel.setLabelId(model.getLabelId());
                                if(b!=null){
                                    commTEntLabelItemModel.setItemId(b.getItemId());
                                    commTEntLabelItemService.delete(commTEntLabelItemModel);
                                }*/

                            }

                        }
                        //新数据
                        List<String> listRem=listrem(list3,propertyS);
                        //list3.removeAll(propertyS);
                        System.out.print("list3***************"+list3);
                        if(listRem!=null&&!listRem.isEmpty()){
                            for(String proI:listRem){
                                if(StringUtils.isNotEmpty(proI)){
                                    CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
                                    commTLabelItem.setLabelId(model.getLabelId());
                                    commTLabelItem.setProperty(proI);
                                    commTLabelItem.setItemId(UUID.randomUUID().toString());
                                    commTLabelItem.setStatus("0");
                                    commTLabelItem.setCreateBy(userId);
                                    commTLabelItem.setCreateDt(new Date());
                                    commTLabelItem.setUpdateBy(userId);
                                    commTLabelItem.setUpdateDt(new Date());
                                    commTLabelItemService.insert(commTLabelItem);
                                }


                            }
                        }

                    }


                }else{
                    if(propertyS==null||propertyS.isEmpty()){
                        System.out.print("**********wu****************"+list1);
                        for(CommTLabelItemModel comm:label) {
                            //没有交集 如 1,2  4,3  先删除 1,2的老数据
                                commTLabelItemService.deleteById(comm.getItemId());
                                /*CommTEntLabelItemModel commTEntLabelItemModel=new CommTEntLabelItemModel();
                                commTEntLabelItemModel.setLabelId(comm.getLabelId());
                                commTEntLabelItemModel.setItemId(comm.getItemId());
                                commTEntLabelItemService.delete(commTEntLabelItemModel);*/

                         }
                    }
                    //新增4,3的数据
                    for(int i=0;i<labName.length;i++ ){
                        if(StringUtils.isNotEmpty(labName[i])){
                            CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
                            commTLabelItem.setLabelId(model.getLabelId());
                            commTLabelItem.setProperty(labName[i]);
                            commTLabelItem.setItemId(UUID.randomUUID().toString());
                            commTLabelItem.setStatus("0");
                            commTLabelItem.setCreateBy(userId);
                            commTLabelItem.setCreateDt(new Date());
                            commTLabelItem.setUpdateBy(userId);
                            commTLabelItem.setUpdateDt(new Date());
                            commTLabelItemService.insert(commTLabelItem);
                        }

                    }
                }

            }else{
                for(int i=0;i<labName.length;i++ ){
                    if(StringUtils.isNotEmpty(labName[i])){
                        CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
                        commTLabelItem.setLabelId(model.getLabelId());
                        commTLabelItem.setProperty(labName[i]);
                        commTLabelItem.setItemId(UUID.randomUUID().toString());
                        commTLabelItem.setStatus("0");
                        commTLabelItem.setCreateBy(userId);
                        commTLabelItem.setCreateDt(new Date());
                        commTLabelItem.setUpdateBy(userId);
                        commTLabelItem.setUpdateDt(new Date());
                        commTLabelItemService.insert(commTLabelItem);
                    }

                }
            }
		}

	}
    /**
     * 从listA里删除listB里有的数据
     * @param listA
     * @param listB
     * @return
     */
    public static List<String> listrem(List<String> listA,List<String> listB){
        System.out.print("************jinruquchu **************");
        HashSet hs1 = new HashSet(listA);
        HashSet hs2 = new HashSet(listB);
        hs1.removeAll(hs2);
        System.out.print("************removeAll end **************");
        List<String> listC = new ArrayList<String>();
        listC.addAll(hs1);
        return listC;
    }





    @Override
    public  List<Map<String,Object>> selectByLabelInfo( Map<String, Object> param) {
        return this.selectList("selectByLabelInfo",param);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void saveEntItem(List<LabelSearchBean> labels, String userId) throws Exception{
        if(labels!=null&&!labels.isEmpty()){
            for(LabelSearchBean labelSearchBean:labels){
                //查询标签是否存在
                EntLabelModel entLabelModel=new EntLabelModel();
                entLabelModel.setLabelId(labelSearchBean.getLabelId());
                entLabelModel.setEntId(labelSearchBean.getEntId());
                EntLabelModel ent=entLabelService.selectBy(entLabelModel);
                if(ent==null){
                    EntLabelModel entLabelM=new EntLabelModel();
                    entLabelM.setLabelId(labelSearchBean.getLabelId());
                    entLabelM.setEntId(labelSearchBean.getEntId());
                    entLabelM.setCreateBy(userId);
                    entLabelM.setCreateDt(new Date());
                    entLabelM.setUpdateDt(new Date());
                    entLabelM.setUpdateBy(userId);
                    entLabelService.insert(entLabelM);
                }


                if(labelSearchBean.getItems()!=null&&!labelSearchBean.getItems().isEmpty()){
                    List<CommTLabelItemModel> list=labelSearchBean.getItems();
                    //删除原来企业填写的属性
                    CommTEntLabelItemModel commTEntLabel=new CommTEntLabelItemModel();
                    commTEntLabel.setEntId(labelSearchBean.getEntId());
                    commTEntLabel.setLabelId(labelSearchBean.getLabelId());
                    commTEntLabelItemService.delete(commTEntLabel);
                    for(CommTLabelItemModel commTLabelItemModel:list){
                       CommTEntLabelItemModel commTEntLabelItemModel=new CommTEntLabelItemModel();
                        commTEntLabelItemModel.setEntId(labelSearchBean.getEntId());
                        commTEntLabelItemModel.setLabelId(commTLabelItemModel.getLabelId());
                        commTEntLabelItemModel.setItemId(commTLabelItemModel.getItemId());
                        commTEntLabelItemModel.setLabelName(commTLabelItemModel.getLabelName());
                        commTEntLabelItemModel.setCreateBy(userId);
                        commTEntLabelItemModel.setCreateDt(new Date());
                        commTEntLabelItemModel.setUpdateBy(userId);
                        commTEntLabelItemModel.setUpdateDt(new Date());
                        if(StringUtils.isNotEmpty(commTLabelItemModel.getLabelName())){
                            commTEntLabelItemService.insert(commTEntLabelItemModel);
                        }

                    }
                }

            }
        }
    }

    @Override
    public void delEntItem(List<LabelSearchBean> labels, String userId) throws Exception {
        if(labels!=null&&!labels.isEmpty()){
            for(LabelSearchBean labelSearchBean:labels){
                //先删除企业对应的标签
                if(StringUtils.isNotEmpty(labelSearchBean.getEntId())&&StringUtils.isNotEmpty(labelSearchBean.getLabelId())){
                    EntLabelModel entLabelModel=new EntLabelModel();
                    entLabelModel.setLabelId(labelSearchBean.getLabelId());
                    entLabelModel.setEntId(labelSearchBean.getEntId());
                   entLabelService.delete("deleteByEneIdOrLabelId",entLabelModel);
                }
                //再删除对应的
                if(labelSearchBean.getItems()!=null&&!labelSearchBean.getItems().isEmpty()){
                    List<CommTLabelItemModel> list=labelSearchBean.getItems();
                    //删除原来企业填写的属性
                    CommTEntLabelItemModel commTEntLabel=new CommTEntLabelItemModel();
                    commTEntLabel.setEntId(labelSearchBean.getEntId());
                    commTEntLabel.setLabelId(labelSearchBean.getLabelId());
                    if(StringUtils.isNotEmpty(labelSearchBean.getEntId())&&StringUtils.isNotEmpty(labelSearchBean.getLabelId())){
                        commTEntLabelItemService.delete(commTEntLabel);
                    }

                }
            }
        }
    }
}