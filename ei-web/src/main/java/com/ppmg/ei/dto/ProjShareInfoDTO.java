package com.ppmg.ei.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.ProjShareInfoModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ProjShareInfoDTO extends BaseDTO {

    /** 记录日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date recordDate;

    private String osId;

    private String projId;

    private String fundId;

    private String fundName;

    private String enteId;

    private String enteName;

    private List<ProjShareInfoModel> listVo;

    //1.是股权投资
    private String type;

}
