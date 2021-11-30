package com.ppmg.ei.easyexcel;

import com.founder.ssm.core.dto.BaseDTO;
import com.ppmg.ei.model.DatNewFunctionExcel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yuyongjun
 * @date 2019/5/30 17:30
 */
@ApiModel(value = "导入业务管理员请求对象", parent = BaseDTO.class)
@Data
public class ZtBusinessManagerImportRequest extends BaseDTO {

    @ApiModelProperty(hidden = true)
    private List<DatNewFunctionExcel> dateList;

    /**
     * 角色编码
     */
    private String roleCode;
}
