package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.EntBaseInfoService;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.vo.FundInfoVO;
import com.ppmg.ei.vo.HomeResultVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@Api(tags={"首页统计信息"})
public class HomeController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(retries = -1)
    private EntBaseInfoService entBaseInfoService;

    @Reference
    private FundInfoService fundInfoService;


    //根据
    @ApiOperation(value = "获取首页统计", notes = "首页母基金统计")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/getFunpermission", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getFunpermission(@RequestParam("fatherId")String fatherId) {
        try {
            String userId=this.getLoginUserId();
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userId",userId);
            param.put("fatherId",fatherId);

            List<Map<String,Object>> listmAP=fundInfoService.selectList("getFunpermission",param);
            mapResponse.put("listmAP", listmAP);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }



    @ApiOperation(value = "首页母基金统计", notes = "首页母基金统计")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/countFofDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String countFofDetail() {
        try {
            String fundId="0";
            FundInfoModel model = fundInfoService.selectById(fundId);
            HomeResultVO vo = new HomeResultVO();
            DecimalFormat df = new DecimalFormat("#.00");
            if (model != null && StringUtils.isNotEmpty(model.getEnteId())) {
                EntBaseInfoModel entBaseInfo = entBaseInfoService.selectById(model.getEnteId());
                if(entBaseInfo!=null){
                    if(entBaseInfo.getRecCapAmount()!=null){
                        String recCapAmount=String.valueOf(df.format(entBaseInfo.getRecCapAmount()/100000000));
                        vo.setRecCap(recCapAmount);
                    }else{
                        //实缴资本
                        vo.setRecCap("0.00");
                    }

                }
            }
            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setGroupingUsed(false);
            //查询母基金投资金额，基金数
            Map<String, Object> map = fundInfoService.selectFundIdList(fundId);
            if (map != null) {
                String countGs = String.valueOf(map.get("COUNTGS")==null?"0":map.get("COUNTGS").toString());
                vo.setCountGs(countGs);
                System.out.print("*************AMOUNT****************"+map.get("AMOUNT"));
                //已投金额
                Double AMOUNT = Double.parseDouble(map.get("AMOUNT")==null?"0":map.get("AMOUNT").toString());
                vo.setAmount(nf.format(AMOUNT/10000));
                //基金认缴总规模
                System.out.print("*************SUMCURRENTAMOUNT****************"+map.get("SUMCURRENTAMOUNT"));
                Double sumCurrentAmount = Double.parseDouble(map.get("SUMCURRENTAMOUNT")==null?"0":map.get("SUMCURRENTAMOUNT").toString());
                vo.setSumCurrentAmount(nf.format(sumCurrentAmount));
            }
            //查询二级基金投三级基金的数量
            Map<String, Object> mapFund=(Map<String,Object>)fundInfoService.selectOne("getsumfund","");
            if(mapFund!=null){
                String sumfun = String.valueOf(mapFund.get("SUMFUN")==null?"0":mapFund.get("SUMFUN").toString());
                vo.setSumFund(sumfun);
                //二级基金投三级基金总规模
                Double sumTwoCurrentAmount = Double.parseDouble(mapFund.get("SUMTWOCURRENTAMOUNT")==null?"0":mapFund.get("SUMTWOCURRENTAMOUNT").toString());
                vo.setSumTwoCurrentAmount(nf.format(sumTwoCurrentAmount/10000));

            }

            //二级基金投项目数
            String sumproj="0";
            //省政府 二级基金投资金额
            String sumTwoPay="0";
            String qtAmount="0";
            Map<String, Object> sumProj=(Map<String,Object>)fundInfoService.selectOne("getSumProj","");
            if(sumProj!=null){
                 sumproj =String.valueOf(sumProj.get("SUMPROJ")==null?"0":sumProj.get("SUMPROJ"));
                vo.setSumProj(sumproj);
                Double  sumTwoPayS =Double.parseDouble(sumProj.get("SUMTWOPAY")==null?"0":sumProj.get("SUMTWOPAY").toString());
                sumTwoPay=nf.format(sumTwoPayS/10000);
                vo.setSumTwoPay(sumTwoPay);
                //带动其他社会资本 二级基金直投项目带动社会出资金额
                Double  qtamountS =Double.parseDouble(sumProj.get("QTAMOUNT")==null?"0":sumProj.get("QTAMOUNT").toString());
                qtAmount=nf.format(qtamountS/10000);
            }
            //三级基金投的项目和三级基金投资金额
            Map<String, Object> sumThreeProj=(Map<String,Object>)fundInfoService.selectOne("getThreeSumProj","");
            //三级基金投资项目数
            String threeCount="0";
            //三级基金出资金额
            String threeSumpay="0";
            String qtATreemount="0";
            if(sumThreeProj!=null){
                 threeCount =String.valueOf(sumThreeProj.get("THREECOUNT")==null?"0":sumThreeProj.get("THREECOUNT"));
                vo.setThreeCount(threeCount);
                Double threeSumpayS =Double.parseDouble(sumThreeProj.get("THREESUMPAY")==null?"0":sumThreeProj.get("THREESUMPAY").toString());
                threeSumpay=nf.format(threeSumpayS/10000);
                //vo.setThreeCount(threeSumpay);
                //三级基金直投项目带动社会出资金额
                Double qtATreemountS =Double.parseDouble(sumThreeProj.get("QTAMOUNT")==null?"0":sumThreeProj.get("QTAMOUNT").toString());
                qtATreemount=nf.format(qtATreemountS/10000);
            }
            //三级基金和特殊基金带动其他社会出资
            String tsQtAmount="0";
            Map<String, Object> sumQtThreeProj=(Map<String,Object>)fundInfoService.selectOne("getSumQtThreeProj","");
            if(sumQtThreeProj!=null){
                Double tsqtAmount =Double.parseDouble(sumQtThreeProj.get("TSQTAMOUNT")==null?"0":sumQtThreeProj.get("TSQTAMOUNT").toString());
                tsQtAmount=nf.format(tsqtAmount/10000);
            }
            //带动社会出资总额
            String sumQtAm= nf.format(Double.valueOf(qtAmount)+Double.valueOf(qtATreemount)+Double.valueOf(tsQtAmount));
            vo.setSumQtAm(sumQtAm);

            //投资项目数 合计：二级基金直投项目数+三级基金直投项目数
            Integer inveProjCountS=Integer.parseInt(sumproj)+Integer.parseInt(threeCount);
           if(inveProjCountS!=null){
             String  inveProjCount= String.valueOf(inveProjCountS);
               //投资项目数
             vo.setInveProjCount(inveProjCount);
           }
           //投资项目总金额
            Double payProjCount=Double.valueOf(sumTwoPay)+Double.valueOf(threeSumpay);
            String sumCountM= nf.format(payProjCount);
            vo.setSumCountM(sumCountM);


            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }


    @ApiOperation(value = "首页基金平台统计", notes = "首页基金平台统计")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/countFundDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String countFundDetail() {
        try {
            HomeResultVO vo = new HomeResultVO();
            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setGroupingUsed(false);
            //母基金规模
            Map<String,Object> cuMap=(Map<String,Object>)fundInfoService.selectOne("getSumCurrAmount",null);
            if(cuMap!=null){
                Double sumCurrAmount = Double.parseDouble(cuMap.get("SUMCURRAMOUNT")==null?"0":cuMap.get("SUMCURRAMOUNT").toString());
                vo.setSumCurrAmount(nf.format(sumCurrAmount/10000));
            }
            //投资金额-母基金管理：已投金额合计
            Map<String,Object> sumPayMap=(Map<String,Object>)fundInfoService.selectOne("getSumPay",null);
              if(sumPayMap!=null){
                  Double sumpay = Double.parseDouble(sumPayMap.get("SUMPAY")==null?"0":sumPayMap.get("SUMPAY").toString());
                  vo.setSumPay(nf.format(sumpay/10000));
              }
            //母基金管理：已投子基金数合计
            Map<String,Object> countFundMap=(Map<String,Object>)fundInfoService.selectOne("getCountFund",null);
            if(countFundMap!=null){
                String countFund = countFundMap.get("COUNTFUND")==null?"0":countFundMap.get("COUNTFUND").toString();
                vo.setCountFund(countFund);
            }


            Map<String,Object> mapS=(Map<String,Object>)fundInfoService.selectOne("getCountS",null);
            if(mapS!=null){
                //立项
                String countApp = String.valueOf(mapS.get("COUNTAPP")==null?"0":mapS.get("COUNTAPP"));
                //过会
                String countPolicy = String.valueOf(mapS.get("COUNTPOLICY")==null?"0":mapS.get("COUNTPOLICY").toString());
                //退出
                String countOut = String.valueOf(mapS.get("COUNTOUT")==null?"0":mapS.get("COUNTOUT"));
                vo.setCountApp(countApp);
                vo.setCountPolicy(countPolicy);
                vo.setCountOut(countOut);
            }
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }




    @ApiOperation(value = "首页直投平台统计", notes = "首页直投平台统计")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/countProjDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String countProjDetail() {
        try {
            HomeResultVO vo = new HomeResultVO();
            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setGroupingUsed(false);
            Map<String,Object> mapS=(Map<String,Object>)fundInfoService.selectOne("getCountProj",null);
             if(mapS!=null){
                 Double sumRmbActualPayAmount = Double.parseDouble(mapS.get("SUMRMBACTUALPAYAMOUNT")==null?"0":mapS.get("SUMRMBACTUALPAYAMOUNT").toString());
                 String countProj =  String.valueOf(mapS.get("COUNTPROJ")==null?"0":mapS.get("COUNTPROJ").toString());
                 vo.setSumRmbActualPayAmount(nf.format(sumRmbActualPayAmount/10000));
                 vo.setCountProj(countProj);
                }
            Map<String,Object> mapOther=(Map<String,Object>)fundInfoService.selectOne("countOtherProj",null);
            if(mapOther!=null){
                String countCb = String.valueOf(mapOther.get("COUNTCB")==null?0:mapOther.get("COUNTCB"));
                String countLx = String.valueOf(mapOther.get("COUNTLX")==null?0:mapOther.get("COUNTLX"));
                String countJc = String.valueOf(mapOther.get("COUNTJC")==null?0:mapOther.get("COUNTJC"));
                String countTc = String.valueOf(mapOther.get("COUNTTC")==null?0:mapOther.get("COUNTTC"));
               //Double.valueOf(countCb)+Double.valueOf(countCb)+
                vo.setCountCb(countCb);
                vo.setCountLx(countLx);
                vo.setCountJc(countJc);
                vo.setCountTc(countTc);
            }
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }



    @ApiOperation(value = "首页金财合盈统计", notes = "首页金财合盈统计")
    @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/countHyDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String countHyDetail() {
        try {
            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            nf.setMaximumFractionDigits(2);
            HomeResultVO vo = new HomeResultVO();
            Map<String,Object> mapS=(Map<String,Object>)fundInfoService.selectOne("countHyProj",null);
            if(mapS!=null){
                String hyProj = String.valueOf(mapS.get("HYPROJ")==null?0:mapS.get("HYPROJ"));
                vo.setHyProj(hyProj);
            }
            Map<String,Object> mapPay=(Map<String,Object>)fundInfoService.selectOne("countHyProjPay",null);
            if(mapPay!=null){
                Double countPayHy = Double.valueOf(mapPay.get("COUNTPAYHY")==null?"0":mapPay.get("COUNTPAYHY").toString());
                vo.setCountPayHy(nf.format(countPayHy/10000));
            }
            Map<String,Object> mapOther=(Map<String,Object>)fundInfoService.selectOne("countOtherHyProj",null);
            if(mapOther!=null){
                String countHyCb = String.valueOf(mapOther.get("COUNTHYCB")==null?0:mapOther.get("COUNTHYCB"));
                String countHyLx = String.valueOf(mapOther.get("COUNTHYLX")==null?0:mapOther.get("COUNTHYLX"));
                String countHyTc = String.valueOf(mapOther.get("COUNTHYTC")==null?0:mapOther.get("COUNTHYTC"));
                vo.setCountHyCb(countHyCb);
                //过会
                vo.setCountHyLx(countHyLx);
                vo.setCountHyTc(countHyTc);
            }
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }


}
