package com.ppmg.ei.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.service.CommTCodeService;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("entBaseInfoSelectUtil")
public class entBaseInfoSelectUtil {

    @Reference(check = false)
    private static CommTCodeService commTCodeService;

}
