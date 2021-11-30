package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.CommTLabelItemModel;
import lombok.Data;

import java.util.List;

@Data
public class LabelVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String labelId;

    /** 父级ID */
    private String parentId;

    /** 标签名称 */
    private String labelName;

    /** 备注 */
    private String remark;

    /** 状态（0正常，1删除） */
    private String status;

    private List<CommTLabelItemModel> labelItemList;
}
