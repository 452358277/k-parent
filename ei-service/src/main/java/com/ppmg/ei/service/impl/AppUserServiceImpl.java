package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.founder.ssm.core.vo.BaseResponse;
import com.founder.ssm.foundation.enumeration.YesOrNoEnmu;
import com.founder.ssm.foundation.service.SerialnoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.common.SerialNoConstants;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.AppUserroleModel;
import com.ppmg.ei.model.BankInfoModel;
import com.ppmg.ei.model.FundMcInfoModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.AppUserroleService;
import com.ppmg.ei.service.BankInfoService;
import com.ppmg.ei.service.FundMcInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 [Service.impl]
 *
 * @author yuyongjun
 * @date 2019-06-24 16:17
 */
@Component("appUserService")
@Service(interfaceClass = AppUserService.class, retries = -1)
public class AppUserServiceImpl extends BaseServiceImpl<AppUserModel> implements AppUserService {

    public AppUserServiceImpl() {
        this.setNamespace("AppUser");
    }

    @Autowired
    private AppUserroleService appUserroleService;

    @Autowired
    private FundMcInfoService fundMcInfoService;

    @Autowired
    private BankInfoService bankInfoService;

    @Reference(check = false)
    private SerialnoService serialnoService;

    @Override
    public String getUserIdByRoleId(String roleId) {
        AppUserModel param = new AppUserModel();
        param.setGuid(roleId);
        ;
        return (String) this.selectOne("getUserIdByRoleId", param);
    }

    @Override
    public String getUserNameById(String userId) {
//		AppUserModel param = new AppUserModel();
//		param.setId(Long.parseLong(userId));
        Map<String, String> param = new HashMap<String, String>();
        param.put("ID", userId);
        return (String) this.selectOne("getUserNameById", param);
    }

    @Override
    public List<AppUserModel> getUserListById(String userId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("ID", userId);
        return this.selectList("getUserListById", param);
    }

    @Override
    public List<AppUserModel> getUserListByRoleId(String roleId) {
        return this.selectList("getUserListByRoleId", roleId);
    }

    /**
     * 根据登录名查询用户
     *
     * @param loginName
     * @return
     */
    @Override
    public AppUserModel selectByLoginName(String loginName) {
        return (AppUserModel) this.selectOne("selectByLoginName",loginName);
    }

    /**
     * 应用编号
     */
    @Value("${uim.appId}")
    private Long appId;

    /**
     * 基金处角色编号
     */
    @Value("${uim.outRoles.fundMA}")
    private Long fundMA;

    /**
     * 基金管理人角色编号
     */
    @Value("${uim.outRoles.fundMC}")
    private Long fundMC;

    /**
     * 托管银行角色编号
     */
    @Value("${uim.outRoles.bank}")
    private Long bank;

    /**
     * 新增外部用户
     *
     * @param model
     * @param roleId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public BaseResponse inserOutUser(AppUserModel model, Long roleId) {
        BaseResponse baseResponse = new BaseResponse();
        AppUserModel appUserModel = this.selectByLoginName(model.getLoginname());
        if(appUserModel!=null){
            baseResponse.error("用户已存在");
            return baseResponse;
        }
        if(roleId.compareTo(fundMC)!=0 && roleId.compareTo(fundMA)!=0 && roleId.compareTo(bank)!=0){
            baseResponse.error("您没有新增用户的权限");
            return baseResponse;
        }

        String userId = serialnoService.getSequence(SerialNoConstants.APP_USER);
        model.setId(Long.parseLong(userId));
        model.setUsertype(2L);
        model.setDisabled("0");
        this.insert(model);
        AppUserroleModel appUserroleModel = new AppUserroleModel();
        appUserroleModel.setAppid(appId);
        appUserroleModel.setUserid(Long.parseLong(userId));
        appUserroleModel.setRoleid(roleId);
        appUserroleService.insert(appUserroleModel);

        //基金管理人
        if(roleId.compareTo(fundMC)==0){

            FundMcInfoModel parentFundMcInfoModel = fundMcInfoService.selectByUserId(model.getCreateBy());
            FundMcInfoModel fundMcInfoModel = new FundMcInfoModel();
            BeanUtils.copyProperties(model, fundMcInfoModel);

            String mcId = serialnoService.getSequence(SerialNoConstants.BD_T_FUND_MC_INFO);
            fundMcInfoModel.setMcId(mcId);
            fundMcInfoModel.setMcName(model.getLoginname());
            fundMcInfoModel.setUserId(userId);
            if(parentFundMcInfoModel!=null){
                fundMcInfoModel.setParentId(parentFundMcInfoModel.getMcId());
            }

            fundMcInfoService.insert(fundMcInfoModel);
        }

        //托管银行
        if(roleId.compareTo(bank)==0){
            BankInfoModel parentBankInfoModel = bankInfoService.selectByUserId(model.getCreateBy());
            BankInfoModel bankInfoModel = new BankInfoModel();
            BeanUtils.copyProperties(model, bankInfoModel);
            String bankId = serialnoService.getSequence(SerialNoConstants.BD_T_BANK_INFO);
            bankInfoModel.setBankId(bankId);
            bankInfoModel.setBankName(model.getLoginname());
            bankInfoModel.setUserId(userId);
            if(parentBankInfoModel!=null){
                bankInfoModel.setParentId(parentBankInfoModel.getBankId());
            }
            bankInfoModel.setStatus(YesOrNoEnmu.NO.value);
            bankInfoService.insert(bankInfoModel);
        }

        return baseResponse;
    }

    /**
     * 逻辑删除用户信息
     *
     * @param model
     * @return
     */
    @Override
    public Integer deleteLogic(AppUserModel model) {
        return this.update("deleteLogic", model);
    }

    /**
     * 修改密码
     *
     * @param model
     * @param oldPassword
     * @return
     */
    @Override
    public BaseResponse updatePassword(AppUserModel model, String oldPassword) {

        BaseResponse baseResponse = new BaseResponse();
        AppUserModel appUserModel = this.selectById(model.getId()+"");
        if(appUserModel==null){
            baseResponse.error("用户不存在");
            return baseResponse;
        }
        if(StringUtils.isNotEmpty(oldPassword)){
            if(oldPassword.contains("\r\n")){
                oldPassword=oldPassword.replaceAll("\r\n","");
            }else if(oldPassword.contains("\n")){
                oldPassword=oldPassword.replaceAll("\n","");
            }
            String  password=appUserModel.getPassword();
            if(appUserModel.getPassword().contains("\r\n")){
                password=appUserModel.getPassword().replaceAll("\r\n","");
            }else if(appUserModel.getPassword().contains("\n")){
                password=appUserModel.getPassword().replaceAll("\n","");
            }
            if(!oldPassword.equals(password)){
                baseResponse.error("旧密码不正确");
                return baseResponse;
            }
        }

        if(model.getPassword().equals(appUserModel.getPassword())){
            baseResponse.error("新密码不能与旧密码相同");
            return baseResponse;
        }

        this.update(model);
        return baseResponse;
    }

    @Override
    public <E> PageInfo<E> selectUserListPage(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectUserListPage", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override
    public <E> PageInfo<E> selectUserAllListPage(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectUserAllListPage", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }
}