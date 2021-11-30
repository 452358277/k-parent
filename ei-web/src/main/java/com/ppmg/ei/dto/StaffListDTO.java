package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.StaffListModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class StaffListDTO extends BaseDTO {

    private  List<StaffListModel> list;
    /** 人员委派安排ID */
    private String assignId;

    private Boolean isStartFlow;

    private String status;

    private String projId;

    private String flowType;

    private String groupId;

}
