package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;

@Data
public class LocationVO extends BaseVO {

    /** 地区编码 */
    private String locId;

    /** 上级编码 */
    private String parentId;

    /** 地区名称 */
    private String locName;

    /** 地区级别 */
    private String locLevel;

    /** 是否末级(0:否，1：是) */
    private String lastflag;

    /** 顺序编号 */
    private Long orderNo;

    /** 区号 */
    private String zoneId;

    /** 地区类型（个税用，和pt_code关联确定住房租金） */
    private String locType;

}
