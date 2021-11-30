package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.ProjContractFileModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ProjContractInfoFileDTO extends BaseDTO {

    private List<ProjContractFileModel> list;

    private String projName;

    private String projId;

    private String inveId;

    private String inveName;

    private  Boolean isStartFlow;

    private String id;

    private String remark;

    private String majorTerm;

    private String contractFile;


    private String groupId;


    private String status;

    private Double signAmount;

    private String signAmountCurr;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date signDt;

    private String finalFile;
}
