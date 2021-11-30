package com.ppmg.ei.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.foundation.service.SerialnoService;
import com.ppmg.ei.model.CommTCodeModel;
import com.ppmg.ei.model.HandleRecordModel;
import com.ppmg.ei.model.PlatformInfoModel;
import com.ppmg.ei.service.CommTCodeService;
import com.ppmg.ei.service.HandleRecordService;
import com.ppmg.ei.service.PlatformInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommonModel;
import com.ppmg.ei.service.CommonService;

import javax.annotation.Resource;
import java.util.Date;

@Component("commonService")
@Service(interfaceClass = CommonService.class)
public class CommonServiceImpl extends BaseServiceImpl<CommonModel> implements CommonService {

	public CommonServiceImpl(){
		this.setNamespace("Common");
	}

	@Resource(name="platformInfoService")
	private PlatformInfoService platformInfoService;

	@Resource(name="commTCodeService")
	private CommTCodeService commTCodeService;

	@Resource(name="handleRecordService")
	private HandleRecordService handleRecordService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Override
	public void updateTableDataInfo(CommonModel model) {
		if("1".equals(model.getType())){
			this.update("updateTableDataInfo", model);
			if(!model.getContentOld().equals(model.getFieldValue())){
				HandleRecordModel handleRecordModel=new HandleRecordModel();
				String pkId = serialnoService.getSequence("EI.SEQ_EI_MY_HANDLE_RECORD");
				handleRecordModel.setPkId(pkId);
				handleRecordModel.setProjectOrFundId(model.getKeyValue());
				handleRecordModel.setContent(model.getFieldValue());
				handleRecordModel.setContentOld(model.getContentOld());
				handleRecordModel.setColumResource("关键人士");
				handleRecordModel.setColumName("KEY_PERSON_LOCK");
				handleRecordModel.setUpdateBy("1");
				handleRecordModel.setUpdateDt(new Date());
				handleRecordService.insert(handleRecordModel);
			}

		}else{
			this.update("updateTableDataInfo", model);
		}


		if("PT_T_PLATFORM_INFO".equals(model.getTableName()) && "PLAT_SHORT_NAME".equals(model.getFieldName())){
			PlatformInfoModel plat = platformInfoService.selectById(model.getKeyValue());
			if(plat != null){
				String code = plat.getPlatCode();
				CommTCodeModel comm = new CommTCodeModel();
				comm.setParentId("266");
				comm.setCodeName(code);
				comm.setCodeDesc(model.getFieldValue());
				comm.setUpdateBy(model.getUpdateId());
				comm.setUpdateDt(new Date());
				commTCodeService.updateByParentId266(comm);

				CommTCodeModel comm2 = new CommTCodeModel();
				comm2.setParentId("279");
				if("1".equals(code)){
					comm2.setCodeValue("4");
				}else if("3".equals(code)){
					comm2.setCodeValue("1");
				}else if("4".equals(code) || "6".equals(code) || "7".equals(code) || "8".equals(code) ||"9".equals(code) ||"10".equals(code) ||"12".equals(code)){
					comm2.setCodeValue("");
				}else if("5".equals(code)){
					comm2.setCodeValue("3");
				}else if("11".equals(code)){
					comm2.setCodeValue("5");
				}else if("13".equals(code)){
					comm2.setCodeValue("12");
				}else if("14".equals(code)){
					comm2.setCodeValue("13");
				}else if("15".equals(code)){
					comm2.setCodeValue("17");
				}else if("16".equals(code)){
					comm2.setCodeValue("9");
				}else if("18".equals(code)){
					comm2.setCodeValue("15");
				}else if("19".equals(code)){
					comm2.setCodeValue("7");
				}else if("41777".equals(code)){
					comm2.setCodeValue("6");
				}else if("40598".equals(code)){
					comm2.setCodeValue("14");
				}else{
					comm2.setCodeValue(code);
				}
				comm2.setCodeName(model.getFieldValue());
				comm2.setCodeDesc(model.getFieldValue());
				comm2.setUpdateBy(model.getUpdateId());
				comm2.setUpdateDt(new Date());
				commTCodeService.updateByParentId279(comm2);
			}
		}

	}

	@Override
	public void deleteTableDataById(CommonModel model) {
		this.update("deleteTableDataById", model);
		
	}


}