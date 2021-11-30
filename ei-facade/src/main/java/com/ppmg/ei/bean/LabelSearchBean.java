package com.ppmg.ei.bean;

import com.founder.ssm.core.bean.BaseSearchBean;
import com.ppmg.ei.model.CommTLabelItemModel;

import java.util.List;

public class LabelSearchBean extends BaseSearchBean {

    /** 主键 */
    private String labelId;

    private String names;

   private String entId;

    private List<CommTLabelItemModel> items;

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public List<CommTLabelItemModel> getItems() {
        return items;
    }

    public void setItems(List<CommTLabelItemModel> items) {
        this.items = items;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
