package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.CommTLabelItemModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

public class projItemVO extends BaseVO {

    /** 主键 */
    private String projId;

    /** 子基金简称 */
    private String projObjectName;

    /** 项目来源 */
    private String projSrc;

    /** 来源说明 */
    private String srcDesc;

    /** 项目经理 */
    private String mangerName;

    /** 项目成员 */
    private String memberName;

    /** 分管合伙人 */
    @ApiModelProperty(value="分管合伙人",name="chargePartner",example="金玉玲")
    private String chargePartner;

    /** 保密等级 */
    @ApiModelProperty(value="保密等级",name="secrecyLevel",example="机密")
    private String secrecyLevel;

    /** 项目概况 */
    @ApiModelProperty(value="项目概况",name="projOverview",example="")
    private String projOverview;

    /** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
    private String projStatus;

    /** 项目状态（2：储备项目，3：立项中，4：已立项，5:已决策，6：已签订，7：已删除，8：已废弃，9：已中止，11：已出资，12：已决议退出，13：已部分退出，14：已退出,15:决策中） */
    private String projStatusName;

    /** 被投对象 */
    private String projObject;

    /** 质量评估评级结果 */
    private Long scoreResult;

    /** 项目所属公司ID */
    private Long groupId;


    /** 项目编号 */
    private String projNo;

    /** 项目名称 */
    private String projName;

    /** 投资经理对项目评价 */
    private String inveEvaluation;


    /** 出资主体编号 */
    private String inveId;

    /** 出资主体名称 */
    private String inveName;

    private List<Map<String,Object>> listMap;

    private String twoFundName;

    private String threeFundName;

    private String entName;


    private   List<CommTLabelItemModel> co;

    private Boolean tag;


    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public List<CommTLabelItemModel> getCo() {
        return co;
    }

    public void setCo(List<CommTLabelItemModel> co) {
        this.co = co;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getTwoFundName() {
        return twoFundName;
    }

    public void setTwoFundName(String twoFundName) {
        this.twoFundName = twoFundName;
    }

    public String getThreeFundName() {
        return threeFundName;
    }

    public void setThreeFundName(String threeFundName) {
        this.threeFundName = threeFundName;
    }

    public List<Map<String, Object>> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map<String, Object>> listMap) {
        this.listMap = listMap;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjObjectName() {
        return projObjectName;
    }

    public void setProjObjectName(String projObjectName) {
        this.projObjectName = projObjectName;
    }

    public String getProjSrc() {
        return projSrc;
    }

    public void setProjSrc(String projSrc) {
        this.projSrc = projSrc;
    }

    public String getSrcDesc() {
        return srcDesc;
    }

    public void setSrcDesc(String srcDesc) {
        this.srcDesc = srcDesc;
    }

    public String getMangerName() {
        return mangerName;
    }

    public void setMangerName(String mangerName) {
        this.mangerName = mangerName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getChargePartner() {
        return chargePartner;
    }

    public void setChargePartner(String chargePartner) {
        this.chargePartner = chargePartner;
    }

    public String getSecrecyLevel() {
        return secrecyLevel;
    }

    public void setSecrecyLevel(String secrecyLevel) {
        this.secrecyLevel = secrecyLevel;
    }

    public String getProjOverview() {
        return projOverview;
    }

    public void setProjOverview(String projOverview) {
        this.projOverview = projOverview;
    }

    public String getProjStatus() {
        return projStatus;
    }

    public void setProjStatus(String projStatus) {
        this.projStatus = projStatus;
    }

    public String getProjStatusName() {
        return projStatusName;
    }

    public void setProjStatusName(String projStatusName) {
        this.projStatusName = projStatusName;
    }

    public String getProjObject() {
        return projObject;
    }

    public void setProjObject(String projObject) {
        this.projObject = projObject;
    }

    public Long getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(Long scoreResult) {
        this.scoreResult = scoreResult;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getInveEvaluation() {
        return inveEvaluation;
    }

    public void setInveEvaluation(String inveEvaluation) {
        this.inveEvaluation = inveEvaluation;
    }

    public String getInveId() {
        return inveId;
    }

    public void setInveId(String inveId) {
        this.inveId = inveId;
    }

    public String getInveName() {
        return inveName;
    }

    public void setInveName(String inveName) {
        this.inveName = inveName;
    }
}
