package com.ppmg.ei.dto;

import com.founder.ssm.core.dto.BaseDTO;
import lombok.Data;

@Data
public class BankInfoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 银行编号 */
    private String bankId;

    /** 银行名称 */
    private String bankName;

    /** 用户ID */
    private String userId;

    /** 父级ID */
    private String parentId;

    /** 注册企业ID */
    private String enteId;

    /** 状态 */
    private String status;
    /** 联系人 */
    private String contacts;

    /** 联系人手机好*/
    private String phone;
}
