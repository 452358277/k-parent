package com.ppmg.ei.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.founder.ssm.core.bean.BaseSearchBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExpertSearchBean extends BaseSearchBean {
    /** 姓名 */
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
