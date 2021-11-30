package com.ppmg.ei.service.impl;

import com.founder.ssm.core.common.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ProjMemberModel;
import com.ppmg.ei.model.YhStaffListPhModel;
import com.ppmg.ei.service.ProjMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.FpThreemeetingInfoModel;
import com.ppmg.ei.service.FpThreemeetingInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component("fpThreemeetingInfoService")
@Service(interfaceClass = FpThreemeetingInfoService.class)
public class FpThreemeetingInfoServiceImpl extends BaseServiceImpl<FpThreemeetingInfoModel> implements FpThreemeetingInfoService {

	public FpThreemeetingInfoServiceImpl(){
		this.setNamespace("FpThreemeetingInfo");
	}
	@Resource
	private ProjMemberService projMemberService;
	@Override



	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addFpThreemeetingInfo(FpThreemeetingInfoModel fpThreemeetingInfoModel) {
        System.out.println("-----33--111------->");
		//会议人员
		List<ProjMemberModel> list = fpThreemeetingInfoModel.getProjMemberModel();
		System.out.println("-----33--222--->"+list);
		if (list != null && list.size() > 0) {
			String projId = fpThreemeetingInfoModel.getPkId();//关联主键
			//保存
			for (ProjMemberModel pm : list) {
				String Pk = UUID.randomUUID().toString();
				pm.setPkId(Pk);
				pm.setProjId(projId);
				pm.setCreateBy(fpThreemeetingInfoModel.getCreateBy());//创建人
				pm.setUpdateBy(fpThreemeetingInfoModel.getUpdateBy());//更新人
				pm.setCreateDt(new Date());
				pm.setUpdateDt(new Date());
				projMemberService.insert(pm);
			}
		}
		//投后事项保存
		List<FpThreemeetingInfoModel> listOne = new ArrayList<FpThreemeetingInfoModel>();
		System.out.println("===--44-------------->"+fpThreemeetingInfoModel);
		listOne.add(fpThreemeetingInfoModel);
        System.out.println("----55---------------------->"+listOne);
		this.insert("addFpThreemeetingInfoMapper",listOne);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFpThreemeetingInfo(FpThreemeetingInfoModel fpThreemeetingInfoModel) {
		//更新
		fpThreemeetingInfoModel.setUpdateDt(new Date());//更新时间
		this.update("updateFpThreemeetingInfoMapper",fpThreemeetingInfoModel);
		//会议人员
		List<ProjMemberModel> list = fpThreemeetingInfoModel.getProjMemberModel();
		if (list != null && list.size() > 0) {
			//先刪除
			String projId = fpThreemeetingInfoModel.getPkId();
			projMemberService.deletcProjId(projId);
			//在保存
			for (ProjMemberModel pm : list) {
				String Pk = UUID.randomUUID().toString();
				pm.setPkId(Pk);
				pm.setProjId(projId);
				pm.setCreateBy(fpThreemeetingInfoModel.getCreateBy());//创建人
				pm.setUpdateBy(fpThreemeetingInfoModel.getUpdateBy());//更新人
				pm.setCreateDt(new Date());
				pm.setUpdateDt(new Date());
				projMemberService.insert(pm);
			}
		}
	}

	@Override
	public <E> PageInfo<E> fundFpThreemeetingInfoListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<FpThreemeetingInfoModel> list = this.selectList("fundFpThreemeetingInfoListPageMapper", var1);
		//查詢人員
		String projId = null;
		for (FpThreemeetingInfoModel lt : list) {
			projId = lt.getPkId();
			List<ProjMemberModel> m_list = projMemberService.selectList(projId);
			if (m_list != null && m_list.size() > 0) {
				lt.setProjMemberModel(m_list);
			}
		}
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<YhStaffListPhModel> selectNumbers(String projId) {
		return this.selectList("selectNumbers",projId);
	}

	@Override
	public <E> PageInfo<E> fundFpThreemeetingInfoListPageNumber(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectNumbers", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public int count(SearchCondition var1) {
		return this.selectCount("selectCountMapper",var1);
	}
}