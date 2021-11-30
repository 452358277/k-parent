package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.CommTLabelItemModel;
import lombok.Data;

import java.util.List;

@Data
public class LabelDTO extends BaseDTO {

    /** 主键 */
    private String labelId;

    private String names;
}
