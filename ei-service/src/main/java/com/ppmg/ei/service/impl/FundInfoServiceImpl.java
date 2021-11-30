package com.ppmg.ei.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("fundInfoService")
@Service(interfaceClass = FundInfoService.class,timeout = 200000)
public class FundInfoServiceImpl extends BaseServiceImpl<FundInfoModel> implements FundInfoService {

	@Resource(name="fundInveDescService")
	private FundInveDescService fundInveDescService;

	@Resource(name="fundShareInfoService")
	private FundShareInfoService fundShareInfoService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource(name="bankInfoService")
	private BankInfoService bankInfoService;

	@Resource(name="fundMcInfoService")
	private FundMcInfoService fundMcInfoService;

	@Resource(name="fundUserRelateService")
	private FundUserRelateService fundUserRelateService;

	@Resource(name="userService")
	private UserService userService;

	@Resource(name="entBaseInfoService")
	private EntBaseInfoService entBaseInfoService;

	@Resource
	private FundInfoService fundInfoService;

	@Resource
	private ProjInfoService projInfoService;

	@Resource
	private  ProjAppInfoService projAppInfoService;

	@Resource
	private BdTFitRegulationSelfService bdTFitRegulationSelfService;

	@Resource
	private BdTAttaItemService bdTAttaItemService;


	@Resource
	private FundRecordInfoService fundRecordInfoService;


	@Resource
	private FundMemberService fundMemberService;

	@Resource
	private FundViewService fundViewService;

	@Resource
	private EnteInfoService enteInfoService;



	public FundInfoServiceImpl(){
		this.setNamespace("FundInfo");
	}

	@Override
	public FundInfoModel getFundDetailInfoByFundId(String fundId) {
		return (FundInfoModel)this.selectOne("getFundDetailInfoByFundId",fundId);
	}

	@Override
	public FundInfoModel getFundInvestInfoByFundId(String fundId) {
		return (FundInfoModel)this.selectOne("getFundInvestInfoByFundId",fundId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateFundInfo(FundInfoModel model, String userId,String password)throws  Exception {
		FundInveDescModel fundInveDescModel = model.getFundInveDescModel();
		String fundId = model.getFundId();
		model.setRegName(model.getFundName());
		if (StringUtils.isNotEmpty(model.getFundId())) {
			//判断原来的基金管理人是金财公司，且没有修改
		    FundInfoModel fundIf=fundInfoService.selectById(model.getFundId());
			if(fundIf!=null){
						ProjInfoModel projInfoModel=new ProjInfoModel();
						projInfoModel.setProjObject(model.getFundId());
						projInfoModel.setProjType("2");
						List<ProjInfoModel> proStr= projInfoService.selectList(projInfoModel);
						if(proStr!=null&& !proStr.isEmpty()){
							String proId1=proStr.get(0).getProjId();
							projInfoModel.setUpdateDt(new Date());
							projInfoModel.setGroupId(1L);
							projInfoModel.setUpdateBy(userId);
							//projInfoModel.setProjStatus("1");
							projInfoModel.setInveId("0");
							projInfoModel.setInveName("江苏省政府投资基金（有限合伙）");
							projInfoModel.setProjId(proId1);
							//projInfoService.update(projInfoModel);
							//插入立项表
							ProjAppInfoModel projAppInfoModel=new ProjAppInfoModel();
							projAppInfoModel.setProjId(proId1);
							projAppInfoModel.setUpdateDt(new Date());
							projAppInfoModel.setUpdateBy(userId);
							projAppInfoModel.setInveCurrentAmount(model.getInveCurrentAmount());
							projAppInfoModel.setInveRaiseAmount(model.getInveRaiseAmount());
							//projAppInfoService.update(projAppInfoModel);
							//合规表
							BdTFitRegulationSelfModel bdTFitRegulationSelfModel=new BdTFitRegulationSelfModel();
							bdTFitRegulationSelfModel.setProjId(proId1);
							bdTFitRegulationSelfModel.setFundId(projInfoModel.getInveId());
							bdTFitRegulationSelfModel.setStatus("0");
							bdTFitRegulationSelfModel.setUpdateDt(new Date());
							bdTFitRegulationSelfModel.setUpdateBy(userId);
							//bdTFitRegulationSelfService.update(bdTFitRegulationSelfModel);
					}


			}

		    //更新基金信息
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			this.update(model);
			//更新基金投资信息
			if(fundInveDescModel != null){
				fundInveDescModel.setUpdateBy(userId);
				fundInveDescModel.setUpdateDt(new Date());
				fundInveDescModel.setFundId(fundId);
				FundInveDescModel  fundInveDesc=fundInveDescService.selectById(fundId);
				if(fundInveDesc==null){
					fundInveDescModel.setCreateBy(userId);
					fundInveDescModel.setCreateDt(new Date());
					fundInveDescService.insert(fundInveDescModel);
				}else {
					fundInveDescService.update(fundInveDescModel);
				}
			}

			//更新托管行信息
            if (StringUtils.isNotEmpty(model.getCustodianBank())) {
                String newUserId = "";
				BankInfoModel bankInfoModel = new BankInfoModel();
                if (StringUtils.isNotEmpty(model.getCustodianBankId())) {
					bankInfoModel.setBankId(model.getCustodianBankId());
				}else{
                    bankInfoModel.setBankName(model.getCustodianBank());
                }
                //现在选中的基金托管行
				List<BankInfoModel> bankInfoModelList = bankInfoService.selectList(bankInfoModel);
				if(bankInfoModelList != null && bankInfoModelList.size() > 0){
					//获取托管银行的用户id
					newUserId = bankInfoModelList.get(0).getUserId();
				}/*else{
					UserModel user=new UserModel();
					newUserId = serialnoService.getSequence("BASE.APP_USER");
					//newUserId = serialnoService.getSequence(SerialNoConstants.APP_USER);
					user.setId(Long.valueOf(newUserId));
					//todo如果是多个应该遍历
					//插入银行用户数据
					user.setLoginname(model.getCustodianBank());
					user.setUsertype(2L);
					user.setPassword(password);
					user.setName(model.getCustodianBank());
					user.setDescription(model.getCustodianBank());

					UserModel user1=new UserModel();
					user1.setLoginname(model.getCustodianBank());
					UserModel user2=userService.selectBy(user1);
					if(user2==null){
						userService.insert(user);
					}

					String newBankId = serialnoService.getSequence("EI.BD_T_BANK_INFO");
					bankInfoModel.setBankId(newBankId);
					bankInfoModel.setCreateBy(userId);
					bankInfoModel.setUserId(newUserId);
					bankInfoModel.setCreateDt(new Date());
					bankInfoModel.setUpdateBy(userId);
					bankInfoModel.setCreateBy(userId);
					bankInfoModel.setUpdateDt(new Date());
					bankInfoService.insert(bankInfoModel);

				}*/
				FundUserRelateModel fundUserRelateModel = new FundUserRelateModel();
				fundUserRelateModel.setFundId(model.getFundId());
				FundUserRelateModel fundUserRelateModel3 = new FundUserRelateModel();
				fundUserRelateModel3.setFundId(model.getFundId());
				fundUserRelateModel3.setUserId(newUserId);
				//查询在权限表中是否存在
				List<FundUserRelateModel> fundUserRelateModelList = fundUserRelateService.selectList(fundUserRelateModel3);
				if(fundUserRelateModelList==null){
					fundUserRelateModel.setUserId(newUserId);
					fundUserRelateModel.setCreateBy(userId);
					fundUserRelateModel.setCreateDt(new Date());
					fundUserRelateModel.setUpdateBy(userId);
					fundUserRelateModel.setUpdateDt(new Date());
					fundUserRelateModel.setUserRoles("2");
					fundUserRelateModel.setAccounts(model.getCustodianBankNum());
					fundUserRelateService.insert(fundUserRelateModel);
				}
				//查询原来的托管行
				if(StringUtils.isNotEmpty(model.getOldCustodianBank())){
					BankInfoModel base=new BankInfoModel();
				    base.setBankName(model.getOldCustodianBank());
				    List<BankInfoModel> baskInfo=bankInfoService.selectList(base);
                    if(baskInfo != null &&baskInfo.size()>0){
                     String oldUserId = baskInfo.get(0).getUserId();
                    //基金已有托管行，并且与此次银行不相同，则删除关联关系，重新插入关联数据
                    if(StringUtils.isNotEmpty(oldUserId) && !oldUserId.equals(newUserId)){
						FundUserRelateModel fundUserRelateModel1 = new FundUserRelateModel();
						fundUserRelateModel1.setUserId(oldUserId);
						fundUserRelateModel1.setFundId(model.getFundId());
						fundUserRelateModel.setUserRoles("2");
                    	fundUserRelateService.delete(fundUserRelateModel1);
						fundUserRelateModel.setUserId(newUserId);
						fundUserRelateModel.setCreateBy(userId);
						fundUserRelateModel.setCreateDt(new Date());
						fundUserRelateModel.setUpdateBy(userId);
						fundUserRelateModel.setUpdateDt(new Date());
						fundUserRelateModel.setUserRoles("2");
						fundUserRelateModel.setAccounts(model.getCustodianBankNum());
						//fundUserRelateModel.setFundId(baskInfo.get(0).getUserId());
						fundUserRelateService.insert(fundUserRelateModel);
                    }else{
						fundUserRelateModel.setFundId(model.getFundId());
						fundUserRelateModel.setUserId(newUserId);
						fundUserRelateModel.setCreateBy(userId);
						fundUserRelateModel.setCreateDt(new Date());
						fundUserRelateModel.setUpdateBy(userId);
						fundUserRelateModel.setUpdateDt(new Date());
						fundUserRelateModel.setUserRoles("2");
						fundUserRelateModel.setAccounts(model.getCustodianBankNum());
						//fundUserRelateModel.setFundId(baskInfo.get(0).getUserId());
						fundUserRelateService.update(fundUserRelateModel);
					}
                 }
				}else{
					fundUserRelateModel.setUserId(newUserId);
					fundUserRelateModel.setCreateBy(userId);
					fundUserRelateModel.setCreateDt(new Date());
                    fundUserRelateModel.setUpdateBy(userId);
                    fundUserRelateModel.setUpdateDt(new Date());
					fundUserRelateModel.setUserRoles("2");
					fundUserRelateModel.setAccounts(model.getCustodianBankNum());
					fundUserRelateService.insert(fundUserRelateModel);
                }
            }

		} else {
            //插入基金信息
			model.setIsDelete("0");
			fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
			model.setFundId(fundId);
			model.setCreateBy(userId);
			model.setCreateDt(new Date());
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			model.setRegisterStatus("1");
			this.insert(model);
			//判断如果管理人是金财公司的
			/*if("10008".equals(model.getMcId())){*/
				//插入到项目表
				String projId = serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
				ProjInfoModel projInfoModel=new ProjInfoModel();
				projInfoModel.setProjId(projId);
				projInfoModel.setCreateBy(userId);
				projInfoModel.setCreateDt(new Date());
				projInfoModel.setUpdateDt(new Date());
				projInfoModel.setUpdateBy(userId);
				projInfoModel.setProjType("2");
				projInfoModel.setProjName(model.getFundName());
				projInfoModel.setProjNo(model.getFundCode());
				projInfoModel.setProjStatus("1");
				projInfoModel.setInveId("0");
				projInfoModel.setStatus("1");
				projInfoModel.setGroupId(1L);
				projInfoModel.setProjObject(model.getFundId());
				projInfoModel.setProjObjectName(model.getFundName());
				projInfoService.insert(projInfoModel);
				//插入立项表
				ProjAppInfoModel projAppInfoModel=new ProjAppInfoModel();
				projAppInfoModel.setProjId(projId);
				projAppInfoModel.setCreateBy(userId);
				projAppInfoModel.setCreateDt(new Date());
				projAppInfoModel.setUpdateDt(new Date());
				projAppInfoModel.setUpdateBy(userId);
				projAppInfoService.insert(projAppInfoModel);
				//合规表
				BdTFitRegulationSelfModel bdTFitRegulationSelfModel=new BdTFitRegulationSelfModel();
				bdTFitRegulationSelfModel.setProjId(projId);
				bdTFitRegulationSelfModel.setFundId(projInfoModel.getInveId());
				bdTFitRegulationSelfModel.setStatus("0");
				bdTFitRegulationSelfModel.setCreateBy(userId);
				bdTFitRegulationSelfModel.setCreateDt(new Date());
				bdTFitRegulationSelfModel.setUpdateDt(new Date());
				bdTFitRegulationSelfModel.setUpdateBy(userId);
				bdTFitRegulationSelfService.insert(bdTFitRegulationSelfModel);

			/*}*/


            //插入基金投资信息
			if(fundInveDescModel != null){
				fundInveDescModel.setFundId(fundId);
				fundInveDescModel.setCreateBy(userId);
				fundInveDescModel.setCreateDt(new Date());
				fundInveDescModel.setUpdateBy(userId);
				fundInveDescModel.setUpdateDt(new Date());
				fundInveDescService.insert(fundInveDescModel);
			}
            //插入基金管理人关联信息
			if (StringUtils.isNotEmpty(model.getMcId())) {
				String[] mcIds = model.getMcId().split(",");
				if(mcIds.length>0){
					FundMcInfoModel fund=fundMcInfoService.selectById(mcIds[0]);
					FundUserRelateModel fundUserRelateModel = new FundUserRelateModel();
					fundUserRelateModel.setFundId(model.getFundId());
					fundUserRelateModel.setUserId(fund.getUserId());
					fundUserRelateService.delete(fundUserRelateModel);
				}

                for (int i = 0; i < mcIds.length; i++) {
					FundMcInfoModel fund=fundMcInfoService.selectById(mcIds[i]);
					FundUserRelateModel mcRelateModel = new FundUserRelateModel();
                    mcRelateModel.setFundId(model.getFundId());
                    if(StringUtils.isNotEmpty(fund.getUserId())){
						mcRelateModel.setUserId(fund.getUserId());
					}
                    mcRelateModel.setCreateBy(userId);
                    mcRelateModel.setCreateDt(new Date());
                    mcRelateModel.setUpdateBy(userId);
                    mcRelateModel.setUpdateDt(new Date());
					mcRelateModel.setUserRoles("1");
					mcRelateModel.setAccounts(model.getCustodianBankNum());
					fundUserRelateService.insert(mcRelateModel);
                }
			}
		}
		List<FundShareInfoModel> shareInfoList = model.getShareInfoList();
		if (shareInfoList != null && shareInfoList.size() > 0) {
			for (FundShareInfoModel shareInfoModel : shareInfoList) {
				if (StringUtils.isNotEmpty(shareInfoModel.getPkId())) {
				    if("1".equals(shareInfoModel.getIsDelete())){
                        fundShareInfoService.deleteById(shareInfoModel.getPkId());
                    }
					shareInfoModel.setUpdateBy(userId);
					shareInfoModel.setUpdateDt(new Date());
					if(StringUtils.isNotEmpty(shareInfoModel.getInveType())){
						if(!"3".equals(shareInfoModel.getInveType())){
							shareInfoModel.setIsFinancingPlatform("0");
						}

					}
					fundShareInfoService.update(shareInfoModel);
				} else {
					if(StringUtils.isNotEmpty(shareInfoModel.getInvestorId())){
						if(!"1".equals(shareInfoModel.getIsDelete())){
							shareInfoModel.setIsDelete("0");
							String pkId = serialnoService.getSequence("EI.RZ_T_FUND_SHARE_INFO");
							shareInfoModel.setPkId(pkId);
							shareInfoModel.setFundId(fundId);
							shareInfoModel.setCreateBy(userId);
							shareInfoModel.setCreateDt(new Date());
							shareInfoModel.setUpdateBy(userId);
							shareInfoModel.setUpdateDt(new Date());
							shareInfoModel.setStatus("0");
							fundShareInfoService.insert(shareInfoModel);
						}

					}

				}
			}
		}
	}

	@Override
	public <E> PageInfo<E> selectgovernmentFundListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectgovernmentFundListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> fundinveInfoList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("fundinveInfoList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> selectListProjPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectListProjPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

    @Override
    public <E> PageInfo<E> selectFundOrSpvListByPage(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectFundOrSpvListByPage", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }


	@Override
	public Map<String,Object> selectFundIdList(String fundId) {
		return (Map<String,Object>)this.selectOne("selectFundIdList",fundId);
	}

	@Override
	public Map<String, Object> selectSumByFundId(String fundId) {
		return (Map<String,Object>)this.selectOne("selectSumByFundId",fundId);
	}

	@Override
	public FundInfoModel selectByName(String fundName) {
		return (FundInfoModel)this.selectOne("selectByName",fundName);
	}
    @Override
    public List<FundInfoModel> selectListByFundCode(String fundCode) {
        return this.selectList("selectListByFundCode",fundCode);
    }

	@Override
	public <E> PageInfo<E> fundComprehensiveListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("fundComprehensiveListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public List<FundInfoModel> selectByNameAndFundSrc(String fundName) {
		return  this.selectList("selectByNameAndFundSrc",fundName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertFundInfoProj(FundInfoModel fundInfo, ProjInfoModel projInfo, String userId,FundInveDescModel fundInveDescModel,ProjAppInfoModel projAppInfoModel,FundShareInfoModel fundShareInfoModel) throws  Exception{
		//判断是否注册
		if(StringUtils.isNotEmpty(fundInfo.getRegisterStatus())){
			if("0".equals(fundInfo.getRegisterStatus())){
				//未注册，插入企业表
				String enterpriseId="";
				EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
				if(StringUtils.isNotEmpty(fundInfo.getFundName())){
				entBaseInfoModel1.setName(fundInfo.getFundName());
				EntBaseInfoModel ent = entBaseInfoService.selectBy(entBaseInfoModel1);
				if(ent==null){
					enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
					EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
					entBaseInfoModel2.setEnterpriseId(enterpriseId);
					entBaseInfoModel2.setName(fundInfo.getFundName());
					entBaseInfoModel2.setEnName(fundInfo.getFundName());
					entBaseInfoModel2.setDeleteFlag("0");
					entBaseInfoModel2.setStatus("未注册");
					entBaseInfoModel2.setCreateBy(userId);
					entBaseInfoModel2.setCreateDt(new Date());
					entBaseInfoModel2.setUpdateBy(userId);
					entBaseInfoModel2.setUpdateDt(new Date());
					entBaseInfoService.insert(entBaseInfoModel2);
					fundInfo.setEnteId(enterpriseId);
				}else{
					enterpriseId=ent.getEnterpriseId();
					fundInfo.setEnteId(enterpriseId);
				}
				}
			}
		}else{
			fundInfo.setEnteId(fundInfo.getEnteId());
		}
		FundInfoModel fundInfoModel=new FundInfoModel();
		fundInfoModel.setRegName(fundInfo.getFundName());
		List<FundInfoModel> list=fundInfoService.selectList(fundInfoModel);
		if(list!=null&&list.size()>0) {
			projInfo.setProjObject(list.get(0).getFundId());
			fundInveDescModel.setFundId(list.get(0).getFundId());
			fundShareInfoModel.setFundId(list.get(0).getFundId());
		}else {
			String fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
			fundInfo.setFundId(fundId);
			fundInfo.setFundName(fundInfo.getRegName());
			fundInfo.setCreateBy(userId);
			fundInfo.setCreateDt(new Date());
			fundInfo.setUpdateDt(new Date());
			fundInfo.setUpdateBy(userId);
			fundInfo.setStatus("0");
			//fundInfo.setFundSrc("2");
			//fundInfo.setFundCode(projInfoModel.getProjNo());
			projInfo.setProjObject(fundId);
			Integer sum = fundInfoService.insert(fundInfo);
			fundInveDescModel.setFundId(fundId);
			fundInveDescModel.setCreateBy(userId);
			fundInveDescModel.setCreateDt(new Date());
			fundInveDescModel.setUpdateDt(new Date());
			fundInveDescModel.setUpdateBy(userId);
			fundInveDescService.insert(fundInveDescModel);
			fundShareInfoModel.setFundId(fundId);
		}

		String pkId = serialnoService.getSequence("EI.RZ_T_FUND_SHARE_INFO");
		fundShareInfoModel.setPkId(pkId);
		fundShareInfoModel.setStatus("0");
		fundShareInfoModel.setCreateBy(userId);
		fundShareInfoModel.setCreateDt(new Date());
		fundShareInfoModel.setUpdateDt(new Date());
		fundShareInfoModel.setUpdateBy(userId);
		fundShareInfoModel.setInvestorId(projInfo.getInveId());
		fundShareInfoService.insert(fundShareInfoModel);

		projInfo.setProjNo(fundInfo.getFundCode());
		projInfo.setCreateBy(userId);
		projInfo.setCreateDt(new Date());
		projInfo.setUpdateDt(new Date());
		projInfo.setUpdateBy(userId);
		projInfo.setStatus("0");
		projInfo.setProjType("2");
		projInfo.setProjStatus("1");
		String projId = serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
		projInfo.setProjId(projId);
		projInfoService.insert(projInfo);
		projAppInfoModel.setProjId(projId);
		projAppInfoModel.setCreateBy(userId);
		projAppInfoModel.setCreateDt(new Date());
		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoModel.setUpdateBy(userId);
		projAppInfoService.insert(projAppInfoModel);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateFundInfoProj(FundInfoModel fundInfo, ProjInfoModel projInfo, String userId,FundInveDescModel fundInveDescModel,ProjAppInfoModel projAppInfoModel) throws  Exception{

		fundInfo.setUpdateDt(new Date());
		fundInfo.setUpdateBy(userId);

		projInfo.setUpdateDt(new Date());
		projInfo.setUpdateBy(userId);
		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoModel.setUpdateBy(userId);
		if(StringUtils.isNotEmpty(projAppInfoModel.getProjId())){
			projInfoService.update(projInfo);
			projAppInfoService.update(projAppInfoModel);
		}
		fundInveDescModel.setUpdateDt(new Date());
		fundInveDescModel.setUpdateBy(userId);
		if(StringUtils.isNotEmpty(fundInveDescModel.getFundId())){

			//fundInfoService.update("updateForId",fundInfo);
			fundInfoService.update(fundInfo);
			fundInveDescService.update(fundInveDescModel);
		}

	}


	@Override
	public void insertFundInfo(FundInfoModel fundInfoModel) {

	}

	@Override
	public void updateFundInfo(FundInfoModel fundInfoModel) {
		this.update("updateFundInfoMapper",fundInfoModel);
	}

	@Override
	public <E> PageInfo<E> selectFofListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFofListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectFofListPageAll(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFofListPageAll", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<FundInfoModel> selectByList(FundInfoModel model) {
		return this.selectList("selectByList",model);
	}

	@Override
	public List<FundInfoModel> selectNewCode(String code,Integer cou) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", "%"+code+"%");
		param.put("fundCodeSign", String.valueOf(cou));
		return this.selectList("selectNewCode",param);
	}

	@Override
	public List<FundInfoModel> selectNewCodeName(String code,Integer cou) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", "%"+code+"%");
		param.put("fundCodeSign", String.valueOf(cou));
		return this.selectList("selectNewCodeName",param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertByInfo(FundInfoModel model,ProjInfoModel projInfoModel,FundInveDescModel fundInveDescModel,ProjAppInfoModel projAppInfoModel,EntBaseInfoModel ent,String typeStr) {
		String projIdAdd = null;
		//判断是存在基金
		if(!"1".equals(typeStr)){
		if(StringUtils.isNotEmpty(model.getRegisterStatus())){
			if("0".equals(model.getRegisterStatus())){
				//未注册，插入企业表
				String enterpriseId="";
				EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
				if(StringUtils.isNotEmpty(model.getFundName())){
					entBaseInfoModel1.setName(model.getFundName());
					entBaseInfoModel1.setIsDelete("0");
					List<EntBaseInfoModel> entList = entBaseInfoService.selectList(entBaseInfoModel1);
					if(entList!=null && entList.size()>0){
						enterpriseId=entList.get(0).getEnterpriseId();
						model.setEnteId(enterpriseId);
					}else{
						enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
						EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
						entBaseInfoModel2.setEnterpriseId(enterpriseId);
						entBaseInfoModel2.setName(model.getFundName());
						entBaseInfoModel2.setEnName(model.getFundName());
						entBaseInfoModel2.setDeleteFlag("0");
						entBaseInfoModel2.setStatus("未注册");
						entBaseInfoModel2.setCreateBy(model.getCreateBy());
						entBaseInfoModel2.setCreateDt(new Date());
						entBaseInfoModel2.setUpdateBy(model.getUpdateBy());
						entBaseInfoModel2.setUpdateDt(new Date());
						entBaseInfoModel2.setStartDate(ent.getStartDate());
						entBaseInfoModel2.setAddressDetails(ent.getAddressDetails());
						entBaseInfoModel2.setCreditCode(ent.getCreditCode());
						entBaseInfoService.insert(entBaseInfoModel2);
						model.setEnteId(enterpriseId);
					}
				}
			}
		}else{
			model.setEnteId(model.getEnteId());
		}
		if("0".equals(projInfoModel.getInveId())){
			model.setFundSrc("1");
		}
		fundInfoService.insert(model);
		fundInveDescModel.setFundId(model.getFundId());
		fundInveDescModel.setCreateBy(model.getUpdateBy());
		fundInveDescModel.setCreateDt(new Date());
		fundInveDescModel.setUpdateDt(new Date());
		fundInveDescModel.setUpdateBy(model.getUpdateBy());
		fundInveDescService.insert(fundInveDescModel);
		}
		if("4".equals(model.getOldSubPlatProp())){
			//生成项目编号
			if (StringUtils.isNotEmpty(model.getFundCode())) {
				//查询项目的编号
			 List<ProjInfoModel> projInfoList1=projInfoService.selectList("selectByProjNno",model.getFundCode());
			   if(projInfoList1!=null&&projInfoList1.size()>0){
				   String projNo1 = projInfoList1.get(0).getProjNo().substring(projInfoList1.get(0).getProjNo().length()-1);
				   if (Integer.parseInt(projNo1) <= 8) {
					   String projNo = model.getFundCode()+"000" + String.valueOf(Integer.parseInt(projNo1) + 1);
					   projInfoModel.setProjNo(projNo);
				   } else if(Integer.parseInt(projNo1) <= 98){
					   String projNo = model.getFundCode()+"00" + String.valueOf(Integer.parseInt(projNo1) + 1);
					   projInfoModel.setProjNo(projNo);
				   }else if(Integer.parseInt(projNo1) <= 998){
					   String projNo =model.getFundCode()+ "0" +String.valueOf(Integer.parseInt(projNo1) + 1);
					   projInfoModel.setProjNo(projNo);
				   }else{
					   String projNo =model.getFundCode()+String.valueOf(Integer.parseInt(projNo1) + 1);
					   projInfoModel.setProjNo(model.getFundCode()+projNo1);
				   }
			   }else{
				   projInfoModel.setProjNo(model.getFundCode()+"0001");
			   }

			}
			String projId = serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
			projIdAdd = projId;
			projInfoModel.setProjId(projId);
			projInfoModel.setProjType("2");
			projInfoModel.setProjObject(model.getFundId());
			projInfoModel.setCreateBy(model.getUpdateBy());
			projInfoModel.setCreateDt(new Date());
			projInfoModel.setUpdateDt(new Date());
			projInfoModel.setUpdateBy(model.getUpdateBy());
			projInfoModel.setProjNo(model.getFundCode());
			projInfoModel.setProjStatus("1");
			projInfoService.insert(projInfoModel);
			//判断出资主体是不是 省政府基金
			   if("0".equals(projInfoModel.getInveId())){
			   	//是省政府基金
					FundMcInfoModel fund=fundMcInfoService.selectById(model.getMcId());
					FundUserRelateModel mcRelateModel = new FundUserRelateModel();
					mcRelateModel.setFundId(model.getFundId());
					if(fund!=null){
						if(StringUtils.isNotEmpty(fund.getUserId())){
							mcRelateModel.setUserId(fund.getUserId());
							//判断是否需要新增
							List<FundUserRelateModel> listFundR=fundUserRelateService.selectList(mcRelateModel);
							if(listFundR==null||listFundR.isEmpty()){
								mcRelateModel.setCreateBy(model.getUpdateBy());
								mcRelateModel.setCreateDt(new Date());
								mcRelateModel.setUpdateBy(model.getUpdateBy());
								mcRelateModel.setUpdateDt(new Date());
								mcRelateModel.setUserRoles("1");
								fundUserRelateService.insert(mcRelateModel);
							}

						}
					}
			}

			projAppInfoModel.setProjId(projId);
			projAppInfoModel.setCreateBy(model.getUpdateBy());
			projAppInfoModel.setCreateDt(new Date());
			projAppInfoModel.setUpdateDt(new Date());
			projAppInfoModel.setUpdateBy(model.getUpdateBy());
			projAppInfoService.insert(projAppInfoModel);
		}
		//新增文件库，在表BD_T_ATTA_ITEM插入数据
		//projType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金
		//ITEM_TYPE=1关联项目表的项目id（PROJ_TYPE=1的项目：项目投管），
		//ITEM_TYPE=3关联项目表的项目id（PROJ_TYPE=2的项目：基金投管），
		//母基金
		try{
			String projectOrFundId = model.getFundId();
			if("2".equals(model.getOldSubPlatProp())){
				bdTAttaItemService.addEiTAttachment(projectOrFundId, "10", model.getUpdateBy());
				System.out.println("---------------新增文件库-母基金" + projectOrFundId);
			}else if("3".equals(model.getOldSubPlatProp())){
				//代管基金
				bdTAttaItemService.addEiTAttachment(projectOrFundId, "12", model.getUpdateBy());
				System.out.println("---------------新增文件库-代管" + projectOrFundId);
			}else if("4".equals(model.getOldSubPlatProp())){
				//投基金
				bdTAttaItemService.addEiTAttachment(projIdAdd, "2", model.getUpdateBy());
				System.out.println("---------------新增文件库-投基金" + projIdAdd);
			}
		}catch (Exception e){
			throw new RuntimeException("新增文件库失败");
			//System.out.println("新增文件库失败");
		}

	}

	@Override
	public void insertByFundOrInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, EntBaseInfoModel ent, String typeStr,List<AdminModel> listAdmin,String typeUpdate) {
		//判断是存在基金
        String projIdAdd = null;
		if(!"1".equals(typeStr)){
			if(StringUtils.isNotEmpty(model.getRegisterStatus())){
				if("0".equals(model.getRegisterStatus())){
					//未注册，插入企业表
					String enterpriseId="";
					EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
					if(StringUtils.isNotEmpty(model.getFundName())){
						entBaseInfoModel1.setName(model.getFundName());
						entBaseInfoModel1.setIsDelete("0");
						List<EntBaseInfoModel> entList = entBaseInfoService.selectList(entBaseInfoModel1);
						if(entList!=null && entList.size()>0){
							enterpriseId=entList.get(0).getEnterpriseId();
							model.setEnteId(enterpriseId);
						}else{
							enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
							EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
							entBaseInfoModel2.setEnterpriseId(enterpriseId);
							entBaseInfoModel2.setName(model.getFundName());
							entBaseInfoModel2.setEnName(model.getFundName());
							entBaseInfoModel2.setDeleteFlag("0");
							entBaseInfoModel2.setStatus("未注册");
							entBaseInfoModel2.setCreateBy(model.getCreateBy());
							entBaseInfoModel2.setCreateDt(new Date());
							entBaseInfoModel2.setUpdateBy(model.getUpdateBy());
							entBaseInfoModel2.setUpdateDt(new Date());
							entBaseInfoModel2.setStartDate(ent.getStartDate());
							entBaseInfoModel2.setAddressDetails(ent.getAddressDetails());
							entBaseInfoModel2.setCreditCode(ent.getCreditCode());
							entBaseInfoService.insert(entBaseInfoModel2);
							model.setEnteId(enterpriseId);
						}
					}
				}
			}else{
				model.setEnteId(model.getEnteId());
			}

			fundInfoService.insert(model);
			fundInveDescModel.setFundId(model.getFundId());
			fundInveDescModel.setCreateBy(model.getUpdateBy());
			fundInveDescModel.setCreateDt(new Date());
			fundInveDescModel.setUpdateDt(new Date());
			fundInveDescModel.setUpdateBy(model.getUpdateBy());
			fundInveDescService.insert(fundInveDescModel);
		}

		if("2".equals(model.getOldSubPlatProp())||"4".equals(model.getOldSubPlatProp())||"5".equals(model.getOldSubPlatProp())||"0".equals(projInfoModel.getInveId())){
			//生成项目编号
			String projId = serialnoService.getSequence("EI.TZ_T_PROJ_INFO");
            projIdAdd=projId;
			projInfoModel.setProjId(projId);
			projInfoModel.setProjType("2");
			projInfoModel.setProjObject(model.getFundId());
			projInfoModel.setProjObjectName(model.getRegName());
			projInfoModel.setCreateBy(model.getUpdateBy());
			projInfoModel.setCreateDt(new Date());
			projInfoModel.setUpdateDt(new Date());
			projInfoModel.setUpdateBy(model.getUpdateBy());
			projInfoModel.setProjNo(model.getFundCode());
			projInfoModel.setProjStatus("1");
			projInfoService.insert(projInfoModel);
			//判断如果出资主体是省政府则需要把原来的基金groupId 修改为省政府的
			if("0".equals(projInfoModel.getInveId())){
				FundInfoModel fundModel=new FundInfoModel();
				fundModel.setFundId(model.getFundId());
				fundModel.setGroupId(1L);
				fundInfoService.update(model);
			}
            if("update".equals(typeUpdate)){
				EnteInfoModel ente=new EnteInfoModel();
				ente.setEnteId(model.getEnteId());
				ente.setEnterpriseId(projInfoModel.getOldEnterpriseId());
				ente.setUpdateDt(new Date());
				ente.setUpdateBy(model.getUpdateBy());
				enteInfoService.update(ente);
			}else{
				EnteInfoModel ente=new EnteInfoModel();
				ente.setEnteId(model.getEnteId());
				ente.setEnterpriseId(projInfoModel.getOldEnterpriseId());
				ente.setCreateBy(model.getUpdateBy());
				ente.setCreateDt(new Date());
				ente.setUpdateDt(new Date());
				ente.setUpdateBy(model.getUpdateBy());
				enteInfoService.insert(ente);
			}

            if("0".equals(projInfoModel.getInveId())){
                //是省政府基金
                FundMcInfoModel fund=fundMcInfoService.selectById(model.getMcId());
                FundUserRelateModel mcRelateModel = new FundUserRelateModel();
                mcRelateModel.setFundId(model.getFundId());
                if(fund!=null){
                    if(StringUtils.isNotEmpty(fund.getUserId())){
                        mcRelateModel.setUserId(fund.getUserId());
                        //判断是否需要新增
                        List<FundUserRelateModel> listFundR=fundUserRelateService.selectList(mcRelateModel);
                        if(listFundR==null||listFundR.isEmpty()){
                            mcRelateModel.setCreateBy(model.getUpdateBy());
                            mcRelateModel.setCreateDt(new Date());
                            mcRelateModel.setUpdateBy(model.getUpdateBy());
                            mcRelateModel.setUpdateDt(new Date());
                            mcRelateModel.setUserRoles("1");
                            fundUserRelateService.insert(mcRelateModel);
                        }

                    }
                }
            }


			//判断出资主体是不是 省政府基金
			projAppInfoModel.setProjId(projId);
			projAppInfoModel.setCreateBy(model.getUpdateBy());
			projAppInfoModel.setCreateDt(new Date());
			projAppInfoModel.setUpdateDt(new Date());
			projAppInfoModel.setUpdateBy(model.getUpdateBy());
			projAppInfoService.insert(projAppInfoModel);
            /*if(listAdmin!=null && !listAdmin.isEmpty()){
				if(StringUtils.isNotEmpty(model.getFundId())){
					fundViewService.update("deleteByFundId",model.getFundId());
				}

            	for(AdminModel adminModel:listAdmin){
					FundViewModel fundViewModel=new FundViewModel();
					fundViewModel.setPkId(UUID.randomUUID().toString());
					fundViewModel.setFundId(model.getFundId());
					fundViewModel.setAdminId(adminModel.getAdminId());
					fundViewModel.setAdminName(adminModel.getAdminName());
					fundViewModel.setCreateBy(model.getUpdateBy());
					fundViewModel.setCreateDt(new Date());
					fundViewModel.setUpdateDt(new Date());
					fundViewModel.setUpdateBy(model.getUpdateBy());
					fundViewService.insert(fundViewModel);
				}

			}*/

		}

        //新增文件库，在表BD_T_ATTA_ITEM插入数据
        //projType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金
        //ITEM_TYPE=1关联项目表的项目id（PROJ_TYPE=1的项目：项目投管），
        //ITEM_TYPE=3关联项目表的项目id（PROJ_TYPE=2的项目：基金投管），
        //母基金
 /*       try{
            String projectOrFundId = model.getFundId();
			if("1".equals(model.getOldSubPlatProp())){
				bdTAttaItemService.addEiTAttachment(projectOrFundId, "11", model.getUpdateBy());
				System.out.println("---------------新增文件库-母基金" + projectOrFundId);
			}
            if("2".equals(model.getOldSubPlatProp())){
                bdTAttaItemService.addEiTAttachment(projectOrFundId, "10", model.getUpdateBy());
                System.out.println("---------------新增文件库-母基金" + projectOrFundId);
            }else if("3".equals(model.getOldSubPlatProp())){
                //代管基金
                bdTAttaItemService.addEiTAttachment(projectOrFundId, "12", model.getUpdateBy());
                System.out.println("---------------新增文件库-代管" + projectOrFundId);
            }
        }catch (Exception e){
            throw new RuntimeException("新增文件库失败");

        }*/
	}

	@Override
	public void updateByFundOrInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel, ProjAppInfoModel projAppInfoModel, EntBaseInfoModel ent, String typeStr,List<AdminModel> listAdmin) {
		//判断是否注册
		//判断出资主体原来不是 改为是省政府基金的
		fundInfoService.update(model);
		fundInveDescModel.setFundId(model.getFundId());
		fundInveDescModel.setUpdateDt(new Date());
		fundInveDescModel.setUpdateBy(model.getUpdateBy());
		FundInveDescModel fundInveDescModel1=fundInveDescService.selectById(model.getFundId());
		if(fundInveDescModel1!=null){
			fundInveDescService.update(fundInveDescModel);
		}else{
			fundInveDescService.insert(fundInveDescModel);
		}

		projInfoModel.setUpdateDt(new Date());
		projInfoModel.setUpdateBy(model.getUpdateBy());
		projInfoService.update(projInfoModel);
		projAppInfoModel.setUpdateDt(new Date());
		projAppInfoModel.setUpdateBy(model.getUpdateBy());
		projAppInfoService.update(projAppInfoModel);

	}

	@Override
	public void updateByInfo(FundInfoModel model, ProjInfoModel projInfoModel, FundInveDescModel fundInveDescModel,ProjAppInfoModel projAppInfoModel,EntBaseInfoModel entB,String typeStr) {
		//判断是否注册
		FundInfoModel  fundInfom=fundInfoService.selectById(model.getFundId());
		if(StringUtils.isNotEmpty(model.getRegisterStatus())){
			if("0".equals(model.getRegisterStatus())){
				//未注册，插入企业表
				String enterpriseId="";
				EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
				if(StringUtils.isNotEmpty(model.getFundName())){
					//查询基金对应的企业id
					//FundInfoModel  fundInfom=fundInfoService.selectById(model.getFundId());
					if(fundInfom!=null){
						if(StringUtils.isNotEmpty(fundInfom.getEnteId())){
							entB.setEnterpriseId(fundInfom.getEnteId());
							entBaseInfoService.update(entB);
							model.setEnteId(fundInfom.getEnteId());
						}else{
							entBaseInfoModel1.setName(model.getFundName());
							EntBaseInfoModel ent = entBaseInfoService.selectBy(entBaseInfoModel1);
							if(ent==null){
								enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
								EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
								entBaseInfoModel2.setEnterpriseId(enterpriseId);
								entBaseInfoModel2.setName(model.getFundName());
								entBaseInfoModel2.setEnName(model.getFundName());
								entBaseInfoModel2.setDeleteFlag("0");
								entBaseInfoModel2.setStatus("未注册");
								entBaseInfoModel2.setCreateBy(model.getCreateBy());
								entBaseInfoModel2.setCreateDt(new Date());
								entBaseInfoModel2.setUpdateBy(model.getUpdateBy());
								entBaseInfoModel2.setUpdateDt(new Date());
								entBaseInfoService.insert(entBaseInfoModel2);
								model.setEnteId(enterpriseId);
							}else{
								enterpriseId=ent.getEnterpriseId();
								entB.setEnterpriseId(enterpriseId);
								entBaseInfoService.update(entB);
								model.setEnteId(enterpriseId);
							}
						}
					}

				}
			}
		}else{
			model.setEnteId(model.getEnteId());
		}
		//判断出资主体原来不是 改为是省政府基金的
		if(projInfoModel!=null &&Strings.isNotBlank(projInfoModel.getProjId())){
			ProjInfoModel proj=projInfoService.selectById(projInfoModel.getProjId());
        if(proj!=null){
			if(!"0".equals(proj.getInveId()) && "0".equals(projInfoModel.getInveId())){
			 //是省政府基金
				FundMcInfoModel fund=fundMcInfoService.selectById(model.getMcId());
				FundUserRelateModel mcRelateModel = new FundUserRelateModel();
				mcRelateModel.setFundId(model.getFundId());
				if(fund!=null){
					if(StringUtils.isNotEmpty(fund.getUserId())){
						mcRelateModel.setUserId(fund.getUserId());
						mcRelateModel.setCreateBy(model.getUpdateBy());
						mcRelateModel.setCreateDt(new Date());
						mcRelateModel.setUpdateBy(model.getUpdateBy());
						mcRelateModel.setUpdateDt(new Date());
						mcRelateModel.setUserRoles("1");
						mcRelateModel.setAccounts(fundInfom.getCustodianBankaccount());
						fundUserRelateService.insert(mcRelateModel);
					}
				}
			model.setFundSrc("1");
		}
		//原来出资主体是省政府，现在也是
		if("0".equals(projInfoModel.getInveId())&& "0".equals(proj.getInveId())){
			//基金管理人判断是否修改
			if(fundInfom!=null){
				FundMcInfoModel fundMcN=fundMcInfoService.selectById(fundInfom.getMcId());
				if(StringUtils.isNotEmpty(model.getMcId())){
					if(!fundInfom.getMcId().equals(model.getMcId())){
						if(fundMcN!=null){
							FundUserRelateModel mcRel = new FundUserRelateModel();
							mcRel.setFundId(model.getFundId());
							//删除原来的基金管理人的权限
							if(StringUtils.isNotEmpty(fundMcN.getUserId())){
								mcRel.setUserId(fundMcN.getUserId());
								fundUserRelateService.delete(mcRel);
							}
						}
						FundUserRelateModel mcRelateModel = new FundUserRelateModel();
						mcRelateModel.setFundId(model.getFundId());
						if(fundMcN!=null){
							if(StringUtils.isNotEmpty(fundMcN.getUserId())){
								mcRelateModel.setUserId(fundMcN.getUserId());
								mcRelateModel.setCreateBy(model.getUpdateBy());
								mcRelateModel.setCreateDt(new Date());
								mcRelateModel.setUpdateBy(model.getUpdateBy());
								mcRelateModel.setUpdateDt(new Date());
								mcRelateModel.setUserRoles("1");
								//注意用户没有填写托管银行的信息
								mcRelateModel.setAccounts(fundInfom.getCustodianBankaccount());
								fundUserRelateService.insert(mcRelateModel);
							}
						}
					}

				}
			}


		}
		//  原来是 现在不是
		if(!"0".equals(projInfoModel.getInveId())&& "0".equals(proj.getInveId())){
			//基金管理人判断是否修改
			if(StringUtils.isNotEmpty(model.getMcId())){
				FundMcInfoModel fundMcNa=fundMcInfoService.selectById(fundInfom.getMcId());
					if(fundMcNa!=null){
						FundUserRelateModel mcRel = new FundUserRelateModel();
						mcRel.setFundId(model.getFundId());
						//删除原来的基金管理人的权限
						if(StringUtils.isNotEmpty(fundMcNa.getUserId())){
							mcRel.setUserId(fundMcNa.getUserId());
							fundUserRelateService.delete(mcRel);
						}

					}
			}

		}

		}
		}
		fundInfoService.update(model);
		fundInveDescModel.setFundId(model.getFundId());
		fundInveDescModel.setUpdateDt(new Date());
		fundInveDescModel.setUpdateBy(model.getUpdateBy());
		FundInveDescModel fundInveDescModel1=fundInveDescService.selectById(model.getFundId());
		if(fundInveDescModel1!=null){
			fundInveDescService.update(fundInveDescModel);
		}else{
			fundInveDescService.insert(fundInveDescModel);
		}
        /*if(1L==model.getGroupId()){*/
			projInfoModel.setUpdateDt(new Date());
			projInfoModel.setUpdateBy(model.getUpdateBy());
			projInfoService.update(projInfoModel);
			projAppInfoModel.setUpdateDt(new Date());
			projAppInfoModel.setUpdateBy(model.getUpdateBy());
			projAppInfoService.update(projAppInfoModel);
		/*}
		if("4".equals(model.getOldSubPlatProp())){
			projInfoModel.setUpdateDt(new Date());
			projInfoModel.setUpdateBy(model.getUpdateBy());
			projInfoService.update(projInfoModel);
			projAppInfoModel.setUpdateDt(new Date());
			projAppInfoModel.setUpdateBy(model.getUpdateBy());
			projAppInfoService.update(projAppInfoModel);
		}*/
	}

	@Override
	public Integer selectByProjNum(String fundId) {
		return (Integer)this.selectOne("selectByProjNum",fundId);
	}
	@Override
	public <E> PageInfo<E> getInfoListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getInfoListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> selectInveListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectInveListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> getJcFundList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getJcFundList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

    @Override
    public <E> PageInfo<E> selectJcInveListPage(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectJcInveListPage", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

	@Override
	public Double getSumFund() {
		return null;
	}

	@Override
	public <E> PageInfo<E> fundInfoInveList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("fundInfoInveList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public Integer getSumProj(String fundId) {
		return (Integer)this.selectOne("getSumProjNum",fundId);
	}

	@Override
	public void savaFundth(FundInfoModel fundInfoModel, FundInveDescModel inveDes, FundRecordInfoModel record, FundMemberModel fundMum) {
		if(StringUtils.isEmpty(fundInfoModel.getFundId())){
			//判断是否注册
			if("0".equals(fundInfoModel.getRegisterStatus())){
				//未注册，插入企业表
				String enterpriseId="";
				EntBaseInfoModel entBaseInfoModel1 = new EntBaseInfoModel();
				if(StringUtils.isNotEmpty(fundInfoModel.getRegName())){
					//查询基金对应的企业id
					//FundInfoModel  fundInfom=fundInfoService.selectById(model.getFundId());
							entBaseInfoModel1.setName(fundInfoModel.getRegName());
					        entBaseInfoModel1.setDeleteFlag("0");
							EntBaseInfoModel ent = entBaseInfoService.selectBy(entBaseInfoModel1);
							if(ent==null){
								enterpriseId = serialnoService.getSequence("MASTER.T_ENT_BASE_INFO");
								EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();
								entBaseInfoModel2.setEnterpriseId(enterpriseId);
								entBaseInfoModel2.setName(fundInfoModel.getFundName());
								entBaseInfoModel2.setEnName(fundInfoModel.getFundName());
								entBaseInfoModel2.setDeleteFlag("0");
								entBaseInfoModel2.setStatus("未注册");
								entBaseInfoModel2.setCreateBy(fundInfoModel.getUpdateBy());
								entBaseInfoModel2.setCreateDt(new Date());
								entBaseInfoModel2.setUpdateBy(fundInfoModel.getUpdateBy());
								entBaseInfoModel2.setUpdateDt(new Date());
								entBaseInfoService.insert(entBaseInfoModel2);
								fundInfoModel.setEnteId(enterpriseId);
							}else{
								enterpriseId=ent.getEnterpriseId();
								fundInfoModel.setEnteId(enterpriseId);
							}



				}
			}
			String fundId = serialnoService.getSequence("EI.BD_T_FUND_INFO");
			fundInfoModel.setCreateDt(new Date());
			fundInfoModel.setCreateBy(fundInfoModel.getUpdateBy());
			fundInfoModel.setFundId(fundId);
			fundInfoModel.setStatus("0");
			fundInfoService.insert(fundInfoModel);

			inveDes.setCreateDt(new Date());
			inveDes.setCreateBy(fundInfoModel.getUpdateBy());
			inveDes.setUpdateBy(fundInfoModel.getUpdateBy());
			inveDes.setUpdateDt(new Date());
			inveDes.setFundId(fundId);
			fundInveDescService.insert(inveDes);
			fundMum.setPkId(UUID.randomUUID().toString());
			fundMum.setCreateDt(new Date());
			fundMum.setCreateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateDt(new Date());
			fundMum.setFundId(fundId);
			fundMemberService.insert(fundMum);
			record.setCreateDt(new Date());
			record.setCreateBy(fundInfoModel.getUpdateBy());
			record.setUpdateBy(fundInfoModel.getUpdateBy());
			record.setUpdateDt(new Date());
			record.setFundId(fundId);
			String pkId = UUID.randomUUID().toString();
			record.setPkId(pkId);
			fundRecordInfoService.insert(record);
		}else{
			fundInfoModel.setCreateDt(new Date());
			fundInfoModel.setCreateBy(fundInfoModel.getUpdateBy());
			fundInfoService.update(fundInfoModel);

			inveDes.setUpdateBy(fundInfoModel.getUpdateBy());
			inveDes.setUpdateDt(new Date());
			fundInveDescService.update(inveDes);


			fundMum.setUpdateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateDt(new Date());
			fundMum.setFundId(fundInfoModel.getFundId());
			if(StringUtils.isNotEmpty(fundMum.getPkId())){
				fundMemberService.update(fundMum);
			}else{
				fundMum.setPkId(UUID.randomUUID().toString());
				fundMum.setCreateDt(new Date());
				fundMum.setCreateBy(fundInfoModel.getUpdateBy());
				fundMemberService.insert(fundMum);
			}


			record.setUpdateBy(fundInfoModel.getUpdateBy());
			record.setUpdateDt(new Date());
			//查询是否存在存在则修改
			FundRecordInfoModel fundRecordInfoModel=new FundRecordInfoModel();
			fundRecordInfoModel.setFundId(fundInfoModel.getFundId());
			List<FundRecordInfoModel> listFundR=fundRecordInfoService.selectList(fundRecordInfoModel);
			if(listFundR!=null&& !listFundR.isEmpty()){
				record.setPkId(listFundR.get(0).getPkId());
				fundRecordInfoService.update(record);
			}else{
				String pkId4 = UUID.randomUUID().toString();
				record.setPkId(pkId4);
				record.setCreateBy(fundInfoModel.getUpdateBy());
				record.setCreateDt(new Date());
				fundRecordInfoService.insert(record);
			}

		}



	}

	@Override
	public <E> PageInfo<E> selectCgListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectCgListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> getFofListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getFofListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public <E> PageInfo<E> getEnteInveList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("getEnteInveList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}
	@Override
	public <E> PageInfo<E> selectFundExportList(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("selectFundExportList", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}



    @Override
	public void savaFundF(FundInfoModel fundInfoModel, FundInveDescModel inveDes, FundMemberModel fundMum) throws Exception {
		fundInfoModel.setCreateDt(new Date());
		fundInfoModel.setCreateBy(fundInfoModel.getUpdateBy());
		fundInfoService.update(fundInfoModel);

		inveDes.setUpdateBy(fundInfoModel.getUpdateBy());
		inveDes.setUpdateDt(new Date());
		fundInveDescService.update(inveDes);
		if(StringUtils.isNotEmpty(fundMum.getPkId())){
			fundMum.setCreateDt(new Date());
			fundMum.setCreateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateDt(new Date());
			fundMemberService.update(fundMum);
		}else{
			fundMum.setCreateDt(new Date());
			fundMum.setCreateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateBy(fundInfoModel.getUpdateBy());
			fundMum.setUpdateDt(new Date());
			fundMum.setPkId(UUID.randomUUID().toString());
			fundMemberService.insert(fundMum);
		}

	}

	@Override
	public List<FundInfoExportModel> getExportInfoList(SearchCondition var1) {
		return this.selectList("getExportInfoList",var1);
	}


	@Override
	public <E> PageInfo<E> projInfoExportListPage(SearchCondition var1) {
		PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
		List<E> list = this.selectList("projInfoExportListPage", var1);
		PageInfo<E> page = new PageInfo(list);
		return page;
	}

	@Override
	public List<ProjInfoExportModel> projInfoExportList(SearchCondition var1) {
		return this.selectList("projInfoExportList",var1);
	}

	@Override
	public List<ProjInfoAllExportModel> projExportList(SearchCondition var1) {
		return this.selectList("projExportList",var1);
	}

	@Override
	public List<LedgeMagExportModel> projLedgeExportList(SearchCondition var1) {
		return this.selectList("projLedgeExportList",var1);
	}

	@Override
	public List<LedgeMagExportModel> ledgeExportList(SearchCondition var1) {
		return this.selectList("ledgeExportList",var1);
	}

	@Override
	public int getCountLedgeMage(SearchCondition var1) {
		return (int)this.selectOne("getCountLedgeMage",var1);
	}

	@Override
	public int projInfoExportCount(SearchCondition var1) {
		return (int)this.selectOne("projInfoExportCount",var1);
	}

	@Override
	public List<FundInfoModel> selectAllFundList(SearchCondition var1) {
		return this.selectList("selectAllFundList",var1);
	}

	@Override
	public List<DataQuarterModel> selectFundInfoList(String keyWord) {
		return this.selectList("selectFundInfoList",keyWord);
	}

	@Override
	public List<DataQuarterModel> getSendFundList(String keyWord) {
		return this.selectList("getSendFundList",keyWord);
	}

	@Override
	public List<DataQuarterModel> selectSendAdminList(String keyWord) {
		return this.selectList("selectSendAdminList",keyWord);
	}
}