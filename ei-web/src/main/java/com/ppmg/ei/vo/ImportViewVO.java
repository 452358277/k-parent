package com.ppmg.ei.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.founder.ssm.core.vo.BaseVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ImportViewVO extends BaseVO {

    /** PK-导入ID */
    private String importId;

    /** 导入名称 */
    private String importName;

    /** 导入时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date impotTime;

    /** 导入季度 */
    private String importQuarter;

    /** 导入附件，附件token */
    private String descFile;

    /** 创建时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;

    /** 附件存入名 */
    private String fileName;

    /** sessionKey */
    private String sessionkey;




}
