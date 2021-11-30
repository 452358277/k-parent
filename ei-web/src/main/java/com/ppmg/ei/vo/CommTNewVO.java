package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class CommTNewVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    /** 行业编码 */
    private String id;

    /** 上级编码 */
    private String parentId;

    /** 行业名称 */
    private String newName;

    /** 行业级别 */
    private String newLevel;

    /** 状态(0：正常，1：禁用) */
    private String status;

    /** 顺序编号 */
    private Long orderNo;

}
