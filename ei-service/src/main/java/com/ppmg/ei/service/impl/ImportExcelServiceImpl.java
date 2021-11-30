package com.ppmg.ei.service.impl;

import com.ppmg.ei.service.ImportExcelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

public class ImportExcelServiceImpl implements ImportExcelService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public String readExcelFile(InputStream in, String fileName) {
     return "";
    }
}
