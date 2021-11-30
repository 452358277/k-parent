package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

import java.util.Date;

@Data
public class NjFundInfoVO extends BaseVO {

    private static final long serialVersionUID = 1L;
    /** 主键 */
    private String fundId;

    /** 基金代码 */
    private String fundCode;

    /** 基金名称 */
    private String fundName;

    /** 基金注册名称 */
    private String regName;

    /** 管理公司编号 */
    private String mcId;

    /** 管理公司 */
    private String mcName;

    /** 普通合伙人 */
    private String gp;

    /** 联系人 */
    private String contact;

    /** 联系人电话 */
    private String phoneNo;

    /** 联系人Email */
    private String contactEmail;

    /** 拟募资规模 */
    private Double planAmount;

    /** 拟募资币种 */
    private String planCurr;

    /** 基金规模 */
    private Double raiseAmount;

    /** 币种 */
    private String raiseCurrency;

    /** 组织形式 */
    private String fundStruct;

    /** 平台性质 */
    private String platProp;

    /** 基金性质（1：直投基金，2：母基金） */
    private String subPlatProp;

    /** 成立日期 */
    private Date setupDt;

    /** 拟最终关闭时间 */
    private Date closeDt;

    /** 注册地 */
    private String regAdd;

    /** 组织机构代码 */
    private String orgnCode;

    /** 法定代表人 */
    private String legalPerson;

    /** 托管银行 */
    private String custodianBank;

    /** 注册资本金 */
    private Double regCapital;

    /** 注册资本金币种 */
    private String regCurrency;

    /** 资本金账户 */
    private String capitalAccount;

    /** 资本回收账户 */
    private String returnAccount;

    /** 基金状态(1：已登记，2：申请中，3：已立项，4：投资期，5：回收期，6：延展期，7：已关闭，8：已删除，9：已废弃) */
    private String fundSts;

    /** 附件 */
    private String descFile;

    /** 基金来源（1：融资设立，2：投资子基金，3：其他） */
    private String fundSrc;

    /** 基金类型 */
    private String fundGenre;

    /** 托管银行账户 */
    private String custodianBankaccount;

    /** 资本金银行 */
    private String capitalBank;

    /** 资本回收银行 */
    private String returnBank;

    /** 合伙人名单 */
    private String partners;

    /** 基金所属公司ID */
    private Long groupId;

    /** 普通合伙人ID */
    private String gpId;

    /** 折算为人民币 */
    private Double rmbRaiseAmount;

    /** 是否引导基金 */
    private String isLead;

    /** 设立依据 */
    private String basis;

    /** 批准设立部门 */
    private String appDep;

    /** 投资限制 */
    private String inveLimit;

    /** 管理部门 */
    private String manDep;

    /** 上级基金ID */
    private String parentId;

    /** 空：内网录入，guest：外网录入 */
    private String dataSrc;

    /** 基金编号类型 */
    private String fundCodeType;

    /** 注册日期 */
    private Date regDt;

    /** 统一社会信用代码/注册号 */
    private String regCode;

    /** 投资方式（1.股权投资，2.债权投资，3.其他投资） */
    private String inveType;

    /** 基金设立时间 */
    private Date regDate;

    /** 是否建立退出机制 */
    private String isExit;

    /** 省级财政出资 */
    private Double financeAmount;

    /** 是否符合征集条件 */
    private String isFit;

    /** 出资人代表名称 */
    private String repName;

    /** 出资人代表ID */
    private String repId;

    /** 子基金首投的母基金（子基金才会用到） */
    private String motherFundId;

    /** 子基金首投的母基金名称 */
    private String motherFundName;

    /** null */
    private String fundCodeSign;


    private String fundStsName;

}
