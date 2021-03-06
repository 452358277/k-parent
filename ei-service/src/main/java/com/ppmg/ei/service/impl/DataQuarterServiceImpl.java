package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.DataQuarterItemModel;
import com.ppmg.ei.model.DataQuarterModel;
import com.ppmg.ei.service.DataQuarterItemService;
import com.ppmg.ei.service.DataQuarterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("dataQuarterService")
@Service(interfaceClass = DataQuarterService.class,timeout = 500000,retries = 0)
public class DataQuarterServiceImpl extends BaseServiceImpl<DataQuarterModel> implements DataQuarterService {

	public DataQuarterServiceImpl(){
		this.setNamespace("DataQuarter");
	}

	@Resource
	private DataQuarterService dataQuarterService;

	@Resource
	private DataQuarterItemService dataQuarterItemService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertList(List<DataQuarterModel> list,String quarter,int year,String clostT)throws Exception {
		//String lastDay=getLastDayOfMonth();
		List<DataQuarterModel> listModel=new ArrayList();
		//List<DataQuarterItemModel> listModelItem=new ArrayList();
		if(list!=null && !list.isEmpty()){
			for(DataQuarterModel model:list){
				String taskId= UUID.randomUUID().toString();
				DataQuarterModel dataquartermodel=new DataQuarterModel();
				dataquartermodel.setQuarter(quarter);
				dataquartermodel.setYear(String.valueOf(year));
				dataquartermodel.setUserType("5");
				dataquartermodel.setTaskId(taskId);
				dataquartermodel.setCreateBy("init");
				dataquartermodel.setCreateDt(new Date());
				dataquartermodel.setUserBizKey("userId");
				if(StringUtils.isEmpty(model.getUserBizValue())){
					dataquartermodel.setUserBizValue("init");
				}else{
					dataquartermodel.setUserBizValue(model.getUserBizValue());
				}

				dataquartermodel.setUpdateBy("init");
				dataquartermodel.setUpdateDt(new Date());
				dataquartermodel.setEndDate(clostT);
				dataquartermodel.setStatus("0");
				if(model.getListItem()!=null&& !model.getListItem().isEmpty()){
					for(DataQuarterItemModel dataQuarterItemModel:model.getListItem()){
						String itemId=UUID.randomUUID().toString();
						dataQuarterItemModel.setTaskId(taskId);
						dataQuarterItemModel.setItemId(itemId);
						dataQuarterItemModel.setCreateBy("init");
						dataQuarterItemModel.setCreateDt(new Date());
						dataQuarterItemModel.setUpdateBy(model.getUserBizValue());
						dataQuarterItemModel.setUpdateDt(new Date());
						dataQuarterItemModel.setStatus("0");
						if("1".equals(dataQuarterItemModel.getDataType())){
							dataQuarterItemModel.setTaskTitle("???????????????"+"???"+dataQuarterItemModel.getTaskTitle());
						}else if("2".equals(dataQuarterItemModel.getDataType())){
							dataQuarterItemModel.setTaskTitle("????????????"+"???"+dataQuarterItemModel.getTaskTitle());
						}else if("3".equals(dataQuarterItemModel.getDataType())){
							dataQuarterItemModel.setTaskTitle("???????????????"+"???"+dataQuarterItemModel.getTaskTitle());
						}
						dataQuarterItemService.insert(dataQuarterItemModel);

					}

				}
				String	taskName=String.valueOf(year)+"???"+quarter+"??????"+"????????????"+model.getListItem().size()+"???";
				dataquartermodel.setTaskName(taskName);
				listModel.add(dataquartermodel);
				//dataQuarterService.insert(dataquartermodel);
			}
			dataQuarterService.insertBatch(listModel);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void sendMsgMcInfo(List<DataQuarterModel> list, String closeDate,String nowYear,String quarter,String dateType)throws Exception {

		List<DataQuarterModel> listModel=new ArrayList();
		List<DataQuarterItemModel> listModelItem=new ArrayList();
		if(list!=null && !list.isEmpty()){
			for(DataQuarterModel model:list){
				String taskId= UUID.randomUUID().toString();
				if(StringUtils.isEmpty(model.getUserBizValue())){
					model.setUserBizValue("init");
				}
				DataQuarterModel dataquartermodel=new DataQuarterModel();
				dataquartermodel.setQuarter(quarter);
				dataquartermodel.setYear(String.valueOf(nowYear));
				dataquartermodel.setUserType("2");
				dataquartermodel.setTaskId(taskId);
				dataquartermodel.setCreateBy("init");
				dataquartermodel.setCreateDt(new Date());
				dataquartermodel.setUserBizKey("userId");
				dataquartermodel.setUserBizValue(model.getUserBizValue());
				dataquartermodel.setUpdateBy("init");
				dataquartermodel.setUpdateDt(new Date());
				dataquartermodel.setEndDate(closeDate);
				dataquartermodel.setStatus("0");
				listModel.add(dataquartermodel);
				if(model.getListItem()!=null&& !model.getListItem().isEmpty()){
					for(DataQuarterItemModel dataQuarterItemModel:model.getListItem()){
						String itemId=UUID.randomUUID().toString();
						dataQuarterItemModel.setTaskId(taskId);
						dataQuarterItemModel.setItemId(itemId);
						dataQuarterItemModel.setCreateBy("init");
						dataQuarterItemModel.setCreateDt(new Date());
						dataQuarterItemModel.setUpdateBy(model.getUserBizValue());
						dataQuarterItemModel.setUpdateDt(new Date());
						dataQuarterItemModel.setStatus("0");
						dataQuarterItemModel.setDataType(dateType);
						if("8".equals(dateType)){
							dataQuarterItemModel.setTaskTitle(nowYear+"??????"+dataQuarterItemModel.getTaskTitle()+"?????????????????????????????????");
						}else if("9".equals(dateType)){
							dataQuarterItemModel.setTaskTitle(nowYear+"?????????"+quarter+"??????"+dataQuarterItemModel.getTaskTitle()+"?????????????????????????????????");
						}else if("10".equals(dateType)){
							dataQuarterItemModel.setTaskTitle(nowYear+"?????????"+quarter+"??????"+dataQuarterItemModel.getTaskTitle()+"???????????????????????????");
						}

						dataQuarterItemService.insert(dataQuarterItemModel);
						//listModelItem.add(dataQuarterItemModel);
					}
					String	taskName="";
					if("8".equals(dateType)){
							taskName=String.valueOf(nowYear)+"???"+"????????????"+model.getListItem().size()+"???";
					}else if("9".equals(dateType)){
							taskName=String.valueOf(nowYear)+"?????????"+quarter+"??????"+"????????????"+model.getListItem().size()+"???"+"?????????????????????????????????";
					}else if("10".equals(dateType)){
							taskName=String.valueOf(nowYear)+"?????????"+quarter+"??????"+"????????????"+model.getListItem().size()+"???"+"???????????????????????????";
					}

					dataquartermodel.setTaskName(taskName);
				}

				dataQuarterService.insert(dataquartermodel);
				//dataQuarterService.insertBatch(listModel);
				//dataQuarterItemService.insertBatch(listModelItem);
			}

		}

	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void sendMsgBankInfo(List<DataQuarterModel> list, String closeDate,String nowYear,String quarter,String dateType) throws Exception{

		List<DataQuarterModel> listModel=new ArrayList();
		List<DataQuarterItemModel> listModelItem=new ArrayList();
		if(list!=null && !list.isEmpty()){
			for(DataQuarterModel model:list){
				String taskId= UUID.randomUUID().toString();
				if(StringUtils.isEmpty(model.getUserBizValue())){
					model.setUserBizValue("init");
				}
				DataQuarterModel dataquartermodel=new DataQuarterModel();
				dataquartermodel.setQuarter(quarter);
				dataquartermodel.setYear(String.valueOf(nowYear));
				dataquartermodel.setUserType("3");
				dataquartermodel.setTaskId(taskId);
				dataquartermodel.setCreateBy("init");
				dataquartermodel.setCreateDt(new Date());
				dataquartermodel.setUserBizKey("userId");
				dataquartermodel.setUserBizValue(model.getUserBizValue());
				dataquartermodel.setUpdateBy("init");
				dataquartermodel.setUpdateDt(new Date());
				dataquartermodel.setEndDate(closeDate);
				dataquartermodel.setStatus("0");
				listModel.add(dataquartermodel);
				if(model.getListItem()!=null&& !model.getListItem().isEmpty()){
					for(DataQuarterItemModel dataQuarterItemModel:model.getListItem()){
						String itemId=UUID.randomUUID().toString();
						dataQuarterItemModel.setTaskId(taskId);
						dataQuarterItemModel.setItemId(itemId);
						dataQuarterItemModel.setCreateBy("init");
						dataQuarterItemModel.setCreateDt(new Date());
						dataQuarterItemModel.setUpdateBy(model.getUserBizValue());
						dataQuarterItemModel.setUpdateDt(new Date());
						dataQuarterItemModel.setStatus("0");
						dataQuarterItemModel.setDataType(dateType);
						dataQuarterItemModel.setTaskTitle(nowYear+"??????"+dataQuarterItemModel.getTaskTitle()+"??????????????????????????????");
						dataQuarterItemService.insert(dataQuarterItemModel);
						//listModelItem.add(dataQuarterItemModel);
					}
					String	taskName=String.valueOf(nowYear)+"???"+"????????????"+model.getListItem().size()+"???";
					dataquartermodel.setTaskName(taskName);
				}

				dataQuarterService.insert(dataquartermodel);
				//dataQuarterService.insertBatch(listModel);
				//dataQuarterItemService.insertBatch(listModelItem);
			}

		}

	}
	public static String getLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		// ????????????
		calendar.set(Calendar.MONTH, month - 1);
		// ????????????????????????
		int lastDay=0;
		//2????????????????????????
		if(month==2) {
			lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
		}else {
			lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		// ????????????????????????????????????
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		// ???????????????
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastD = sdf.format(calendar.getTime())+" 23:59:59";
		return lastD;
	}

}