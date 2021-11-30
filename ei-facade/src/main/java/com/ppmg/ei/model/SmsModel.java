package com.ppmg.ei.model;

import com.founder.ssm.core.model.BaseModel;
import lombok.Data;

/**
 * 描述[短信模板]
 *
 * @author : William Jiang
 * @date : 2021-4-9 16:47
 */
@Data
public class SmsModel extends BaseModel {

    private String code;

    private String label;

    private String name;
}
