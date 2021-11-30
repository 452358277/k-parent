package com.ppmg.ei.easyexcel;

import com.founder.ssm.core.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "导入业务管理员请求对象", parent = BaseDTO.class)
@Data
public class FundManagerImportRequest extends BaseDTO {

    @ApiModelProperty(hidden = true)
    private List<FundManagerModel> dateList;



}
