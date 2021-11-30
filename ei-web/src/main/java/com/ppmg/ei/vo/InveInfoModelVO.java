package com.ppmg.ei.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.InveResumeModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InveInfoModelVO extends BaseVO {
    /** 投资人ID */
    private String investorId;

    /** 投资人名称 */
    private String investorName;

    /** 投资人类型(1:个人，2:机构，3：政府) */
    private String investorType;

    private String investorTypeName;

    /** 机构属性（1:国企，2:民企，3：外企） */
    private String subType;

    private String subTypeName;

    /** 性别 */
    private String gender;

    /** 国籍 */
    private String nationality;

    /** 所属公司 */
    private String company;

    /** 职位 */
    private String position;

    /** 证件号码/组织机构代码 */
    private String idNo;

    /** 注册名称 */
    private String regName;

    /** 注册地点 */
    private String regAdd;

    /** 注册日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date regDate;

    /** 注册资本 */
    private Double regCapital;

    /** 注册资本币种 */
    private String regCurrency;

    /** 办公地址 */
    private String officeAdd;

    /** 办公电话 */
    private String officeTel;

    /** 联系人 */
    private String contact;

    /** 联系电话 */
    private String phoneNo;

    /** 邮箱 */
    private String email;

    /** 投资阶段 */
    private String invePhase;

    /** 所在区域 */
    private String area;

    /** 关注行业 */
    private String watchIndustry;

    /** 客户经理 */
    private String custMag;

    /** 开户行 */
    private String accountBank;

    /** 账号 */
    private String accountNo;

    /** 投资人状态（0：正常，1:删除，2：其他） */
    private String status;

    /** 所属公司ID */
    private Long groupId;

    /** 企业ID */
    private String enteId;

    private InveResumeModel inveResumeModel;

    private String enteNature;

    private String investmentAttributes;
}
