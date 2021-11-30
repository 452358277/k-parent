package com.ppmg.ei.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.founder.ssm.core.service.impl.BaseServiceImpl;
import com.ppmg.ei.model.CommTEntLabelItemModel;
import com.ppmg.ei.service.CommTEntLabelItemService;

@Component("commTEntLabelItemService")
@Service(interfaceClass = CommTEntLabelItemService.class)
public class CommTEntLabelItemServiceImpl extends BaseServiceImpl<CommTEntLabelItemModel> implements CommTEntLabelItemService {

    public CommTEntLabelItemServiceImpl(){
        this.setNamespace("CommTEntLabelItem");
    }

}