package com.ppmg.ei.service;

import java.io.InputStream;

public interface ImportExcelService {

    String readExcelFile( InputStream in ,String fileName);

}
