package com.ppmg.ei.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.AppUserroleModel;
import com.ppmg.ei.model.FundMcInfoModel;
import com.ppmg.ei.model.UserModel;
import com.ppmg.ei.service.AppUserroleService;
import com.ppmg.ei.service.FundMcInfoService;
import com.ppmg.ei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基金管理人 [Service.impl]
 *
 * @author yuyongjun
 * @date 2019-06-25 09:21
 */
@Component("fundMcInfoService")
@Service(interfaceClass = FundMcInfoService.class)
public class FundMcInfoServiceImpl extends BaseServiceImpl<FundMcInfoModel> implements FundMcInfoService {

    public FundMcInfoServiceImpl() {
        this.setNamespace("FundMcInfo");
    }

    @Resource
    private UserService userService;

    @Resource
    private FundMcInfoService fundMcInfoService;

    @Resource
    private AppUserroleService appUserroleService;


    @Value("${uim.outRoles.fundMC}")
    private Long fundMC;

    @Value("${uim.appId}")
    private Long appId;

    @Override
    public List<FundMcInfoModel> selectByFundId() {
        return this.selectList("selectByFundId", "");
    }

    @Override
    public FundMcInfoModel selectByUserId(String userId) {
        return (FundMcInfoModel) this.selectOne("selectByUserId", userId);
    }


    @Override
    public <E> PageInfo<E> selectListPage1(SearchCondition var1) {
        PageHelper.startPage(var1.getCurrPage(), var1.getPageSize());
        List<E> list = this.selectList("selectListPage1", var1);
        PageInfo<E> page = new PageInfo(list);
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void insertById(UserModel user, FundMcInfoModel model) throws Exception {
        user.setDisabled("0");
        user.setIsDelete("0");
        user.setCreateBy(String.valueOf(user.getId()));
        userService.insert(user);
        fundMcInfoService.insert(model);
        AppUserroleModel appUserroleModel = new AppUserroleModel();
        appUserroleModel.setAppid(appId);
        appUserroleModel.setUserid(user.getId());
        appUserroleModel.setRoleid(fundMC);
        appUserroleService.insert(appUserroleModel);
    }
}