package com.ppmg.ei.vo;

import com.founder.ssm.core.vo.BaseVO;
import com.ppmg.ei.model.BussniessResultModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.model.SumProjInfo;

import java.util.List;

public class ResultVO extends BaseVO {

    private List<ProjInfoModel> projinFomodel;

    private List<BussniessResultModel> list;

    private List<SumProjInfo> sumProjInfo;

    public List<SumProjInfo> getSumProjInfo() {
        return sumProjInfo;
    }

    public void setSumProjInfo(List<SumProjInfo> sumProjInfo) {
        this.sumProjInfo = sumProjInfo;
    }

    public List<ProjInfoModel> getProjinFomodel() {
        return projinFomodel;
    }

    public void setProjinFomodel(List<ProjInfoModel> projinFomodel) {
        this.projinFomodel = projinFomodel;
    }

    public List<BussniessResultModel> getList() {
        return list;
    }

    public void setList(List<BussniessResultModel> list) {
        this.list = list;
    }
}
