package com.ppmg.ei.utils;

/**
 * FIXFLOW_RUN_TASKINSTANCE表
 */
public class ProcessUtil {
    /*任务实例编号*/
    private String taskinstanceId;
    /*流程定义编号*/
    private String processdefinitionId;
    /*任务主键id*/
    private String bizkKey;
    /*操作表单*/
    private String formUri;
    /*浏览表单*/
    private String formUriView;

    public String getTaskinstanceId() {
        return taskinstanceId;
    }

    public void setTaskinstanceId(String taskinstanceId) {
        this.taskinstanceId = taskinstanceId;
    }

    public String getProcessdefinitionId() {
        return processdefinitionId;
    }

    public void setProcessdefinitionId(String processdefinitionId) {
        this.processdefinitionId = processdefinitionId;
    }

    public String getBizkKey() {
        return bizkKey;
    }

    public void setBizkKey(String bizkKey) {
        this.bizkKey = bizkKey;
    }

    public String getFormUri() {
        return formUri;
    }

    public void setFormUri(String formUri) {
        this.formUri = formUri;
    }

    public String getFormUriView() {
        return formUriView;
    }

    public void setFormUriView(String formUriView) {
        this.formUriView = formUriView;
    }
}
