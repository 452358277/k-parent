package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.common.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.bean.ProjInfoSearchBean;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.FundInveDescModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.FundInveDescService;
import com.ppmg.ei.service.ProjInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.BdTFitRegulationSelfModel;
import com.ppmg.ei.service.BdTFitRegulationSelfService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("bdTFitRegulationSelfService")
@Service(interfaceClass = BdTFitRegulationSelfService.class)
public class BdTFitRegulationSelfServiceImpl extends BaseServiceImpl<BdTFitRegulationSelfModel> implements BdTFitRegulationSelfService {

	public BdTFitRegulationSelfServiceImpl(){
		this.setNamespace("BdTFitRegulationSelf");
	}

	@Resource
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@Resource
	private ProjInfoService projInfoService;

	@Resource
	private FundInfoService fundInfoService;

	@Resource
	private FundInveDescService fundInveDescService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void operation(FundInfoModel fundInfoModel, ProjInfoModel projInfoModel, BdTFitRegulationSelfModel  bdTFitRegulationSelfModel, FundInveDescModel fundInveDescModel,String userId) throws Exception {
		//1.修改基金概况
		//FundInfoModel fundInfoModel1=new FundInfoModel();
		fundInfoModel.setUpdateBy(userId);
		fundInfoModel.setUpdateDt(new Date());
		//fundInfoService.update(fundInfoModel);

		fundInveDescModel.setUpdateBy(userId);
		fundInveDescModel.setUpdateDt(new Date());
		fundInveDescService.update(fundInveDescModel);
		//2.修改项目
		projInfoModel.setUpdateBy(userId);
		projInfoModel.setUpdateDt(new Date());
		//projInfoModel1.setIsSpv(projInfoModel.getIsSpv());
		projInfoService.update(projInfoModel);
		//3.修改自查信息
		BdTFitRegulationSelfModel bd=new BdTFitRegulationSelfModel();
		bd.setProjId(projInfoModel.getProjId());
		bd.setCurTmoney(bdTFitRegulationSelfModel.getCurTmoney());
		bd.setAllTmoney(bdTFitRegulationSelfModel.getAllTmoney());
		bd.setCurTmoneyPer(bdTFitRegulationSelfModel.getCurTmoneyPer());
		bd.setAllTmoneyPer(bdTFitRegulationSelfModel.getAllTmoneyPer());
		bd.setFunToPro(bdTFitRegulationSelfModel.getFunToPro());
		bd.setIsSholder(bdTFitRegulationSelfModel.getIsSholder());
		bd.setIndustry(bdTFitRegulationSelfModel.getIndustry());
		bd.setIsMfield(bdTFitRegulationSelfModel.getIsMfield());
		bd.setNmainTmoney(bdTFitRegulationSelfModel.getNmainTmoney());
		bd.setNmainTmoneyPer(bdTFitRegulationSelfModel.getNmainTmoneyPer());
		bd.setIsJs(bdTFitRegulationSelfModel.getIsJs());
		bd.setIsTwelve(bdTFitRegulationSelfModel.getIsTwelve());
		bd.setTmoneyOne(bdTFitRegulationSelfModel.getTmoneyOne());
		bd.setTmoneyOnePer(bdTFitRegulationSelfModel.getTmoneyOnePer());
		bd.setTmoneyTtf(bdTFitRegulationSelfModel.getTmoneyTtf());
		bd.setTmoneyTtfPer(bdTFitRegulationSelfModel.getTmoneyTtfPer());
		bd.setOutjsTmoney(bdTFitRegulationSelfModel.getOutjsTmoney());
		bd.setOutjsTmoneyPer(bdTFitRegulationSelfModel.getOutjsTmoneyPer());
		bd.setIsCorr(bdTFitRegulationSelfModel.getIsCorr());
		bd.setCorrExplain(bdTFitRegulationSelfModel.getCorrExplain());
		bd.setIsAllow(bdTFitRegulationSelfModel.getIsAllow());
		bd.setWhichClass(bdTFitRegulationSelfModel.getWhichClass());
		bd.setIsNlist(bdTFitRegulationSelfModel.getIsNlist());
		bd.setIsThirteen(bdTFitRegulationSelfModel.getIsThirteen());
		bd.setAnyNlist(bdTFitRegulationSelfModel.getAnyNlist());
		bd.setExplain(bdTFitRegulationSelfModel.getExplain());
		bd.setSelfConclusion(bdTFitRegulationSelfModel.getSelfConclusion());
		bd.setUpdateBy(userId);
		bd.setCreateDt(new Date());
		bd.setUpdateDt(new Date());
		bd.setRegulationFile(bdTFitRegulationSelfModel.getRegulationFile());
		bd.setSecTmoneyPer(bdTFitRegulationSelfModel.getSecTmoneyPer());
		bd.setThrTmoneyPer(bdTFitRegulationSelfModel.getThrTmoneyPer());
		bd.setConfirmLetterFile(bdTFitRegulationSelfModel.getConfirmLetterFile());
        if(StringUtils.isEmpty(projInfoModel.getProjId())){
			bd.setCreateBy(userId);
            bdTFitRegulationSelfService.insert(bd);
         }else{
            BdTFitRegulationSelfModel  bdTFitRegulationSelfModel1=bdTFitRegulationSelfService.selectById(projInfoModel.getProjId());
               //如果存在则修改
            if(bdTFitRegulationSelfModel1!=null){
                bdTFitRegulationSelfService.update(bd);
            }else{
                bdTFitRegulationSelfService.insert(bd);
            }
        }

	}

	@Override
	public Map<String, Object> selectSum(String id) {

		return (Map<String, Object>)this.selectOne("selectSum",id);
	}

	@Override
	public List<BdTFitRegulationSelfModel> selectListBybdTFit(Map<String, Object> map) {
		return this.selectList("selectListBybdTFit",map);
	}

	@Override
	public <E> PageInfo<E> selectRegulationPage(SearchCondition var1) {
			PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
			List<E> list = this.selectList("selectRegulationPage", var1);
			PageInfo<E> page = new PageInfo(list);
			return page;
		}
}