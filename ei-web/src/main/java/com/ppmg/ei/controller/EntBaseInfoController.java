package com.ppmg.ei.controller;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.bean.entBaseInfoSearchBean;
import com.ppmg.ei.model.CommTGicsModel;
import com.ppmg.ei.model.EntLogoModel;
import com.ppmg.ei.service.CommTCodeService;
import com.ppmg.ei.service.CommTGicsService;
import com.ppmg.ei.service.EntBaseInfoService;
import com.ppmg.ei.service.EntLogoService;
import com.ppmg.ei.vo.EntBaseInfoListVO;
import com.ppmg.ei.vo.EntLogoVO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.EntBaseInfoModel;

@Controller
@Scope("prototype")
@RequestMapping("/entBaseInfo")
@Api(tags={"企业信息接口"})
public class EntBaseInfoController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(retries=-1)
    private EntBaseInfoService entBaseInfoService;
    @Reference
    private CommTGicsService commTGicsService;

    @Reference(check = false)
    private static CommTCodeService commTCodeService;

    @Reference
    private EntLogoService entLogoService;

    @ApiOperation(value="企业信息列表", notes="企业信息列表")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(@RequestBody entBaseInfoSearchBean searchBean){
        try {

            String apiKey = "050bf2f3396444a9b6e770e209482825";//SystemConfig.getInstance().getString("apiKey");
            String Endinfo = URLDecoder.decode(searchBean.getKeyword(),"UTF-8");
            String PostUrl = "http://i.yjapi.com/ECIV4/Search?key="+apiKey+"&dtype=json&keyWord=${params}";

            String url = PostUrl.replace("${params}",Endinfo);

            String responseString = null;
            responseString =loadJSON(url);
            logger.info(responseString);
            String aa = responseString.substring(responseString.indexOf("\"Result\":")+9,responseString.lastIndexOf("}"));
            JSONArray jsonArray = JSONArray.parseArray(aa);
            List<Map<String,Object>> lisMaptJson = (List)jsonArray;
            if(lisMaptJson!=null){
                for(int i=0;i<lisMaptJson.size();i++){

                    String creditCode = String.valueOf(lisMaptJson.get(i).get("CreditCode"));
                    String keyNo = String.valueOf(lisMaptJson.get(i).get("KeyNo"));
                    String No = String.valueOf(lisMaptJson.get(i).get("No"));
                    EntBaseInfoModel entBaseInfoModel = new EntBaseInfoModel();
                    if(!creditCode.isEmpty()){
                        entBaseInfoModel.setCreditCode(creditCode);
                        entBaseInfoModel.setDeleteFlag("0");
                    }else{
                        entBaseInfoModel.setKeyNo(keyNo);
                        entBaseInfoModel.setDeleteFlag("0");
                    }

                    entBaseInfoModel = entBaseInfoService.selectBy(entBaseInfoModel);
                    if(entBaseInfoModel==null){
                        String apiKey2 = "050bf2f3396444a9b6e770e209482825";//SystemConfig.getInstance().getString("apiKey");
                        if(creditCode.isEmpty()&&No.isEmpty()){
                            continue;
                        }
                        if(!creditCode.isEmpty()){
                            No = creditCode;
                        }
                        String Endinfo2 = URLDecoder.decode(No,"UTF-8");
                        String PostUrl2 = "http://i.yjapi.com/ECIV4/GetDetailsByName?key="+apiKey2+"&keyWord=${params}";
                        String url2 = PostUrl2.replace("${params}",Endinfo2);
                        String responseString2 = null;
                        responseString2 =loadJSON(url2);
                        logger.info(responseString2);
                        if(responseString2.indexOf("查询无结果")==-1){//{"Status":"201","Message":"查询无结果","Result":null}
                            String bb = "{"+responseString2.substring(responseString2.indexOf("\"KeyNo\":"),responseString2.lastIndexOf("}"));
                            //JSONArray jsonArray2 = JSONArray.parseArray(bb);
                            Map maps = (Map) JSON.parse(bb);
                            Map<String,Object> maptJson = (Map)maps;

                            String TermStartJ = null;
                            if((!responseString2.substring(responseString2.indexOf("TermStart")+11,responseString2.indexOf("TermStart")+15).equals("null"))&&(!responseString2.substring(responseString2.indexOf("TermStart")+11,responseString2.indexOf("TermStart")+13).equals("\"\""))){
                                TermStartJ = responseString2.substring(responseString2.indexOf("TermStart")+12,responseString2.indexOf("TermStart")+22);//经营期限start
                            }

                            String TeamEndJ = null ;
                            if((!responseString2.substring(responseString2.indexOf("TeamEnd")+9,responseString2.indexOf("TeamEnd")+13).equals("null"))&&(!responseString2.substring(responseString2.indexOf("TeamEnd")+9,responseString2.indexOf("TeamEnd")+11).equals("\"\""))){
                                TeamEndJ = responseString2.substring(responseString2.indexOf("TeamEnd")+10,responseString2.indexOf("TeamEnd")+20);//经营期限end
                            }

                            String CheckDateJ = null ;
                            if((!responseString2.substring(responseString2.indexOf("CheckDate")+11,responseString2.indexOf("CheckDate")+15).equals("null"))&&(!responseString2.substring(responseString2.indexOf("CheckDate")+11,responseString2.indexOf("CheckDate")+13).equals("\"\""))){
                                CheckDateJ = responseString2.substring(responseString2.indexOf("CheckDate")+12,responseString2.indexOf("CheckDate")+22);//核准时间
                            }


                            //String enterpriseId = SequenceUtil.getSequence("T_ENT_BASE_INFO");
                            String enterpriseId = getSeqNextValString("EI.SEQ_ENTERPRISE_ID");
                            EntBaseInfoModel entBaseInfoModel2 = new EntBaseInfoModel();


                            String status = String.valueOf(maptJson.get("Status"));
                            if(status.indexOf("存续") >-1){
                                status = "存续";
                            }else if(status.indexOf("开业") >-1){
                                status = "开业";
                            }else if(status.indexOf("在业") >-1){
                                status = "在业";
                            }else if(status.indexOf("注销") >-1){
                                status = "注销";
                            }else if(status.indexOf("迁入") >-1){
                                status = "迁入";
                            }else if(status.indexOf("吊销") >-1){
                                status = "吊销";
                            }else if(status.indexOf("迁出") >-1){
                                status = "迁出";
                            }else if(status.indexOf("停业") >-1){
                                status = "停业";
                            }else if(status.indexOf("清算") >-1){
                                status = "清算";
                            }else if(status.indexOf("未注册") >-1){
                                status = "未注册";
                            }


                            String RegistCapi = String.valueOf(maptJson.get("RegistCapi"));
                            String RegistAmount = "";
                            String RegistCapiBZ = "1";  //注册资本币种
                            if(RegistCapi!=null && RegistCapi !="null"){
                                String RegistCapiArr[] = RegistCapi.split("万");//注册资本  // 1000万元人民币
                                RegistAmount = RegistCapiArr[0];
                                if(RegistCapiArr.length>1){
                                    if(RegistCapiArr[1].indexOf("人民币")>-1){
                                        RegistCapiBZ = "1";
                                    }else if(RegistCapiArr[1].indexOf("美元")>-1){
                                        RegistCapiBZ = "2";
                                    }else if(RegistCapiArr[1].indexOf("欧元")>-1){
                                        RegistCapiBZ = "3";
                                    }
                                }
                                if((!RegistAmount.equals(""))&&(RegistAmount!=null)){
                                    entBaseInfoModel2.setRegistAmount(Double.parseDouble(RegistAmount)*10000);
                                }else{
                                    entBaseInfoModel2.setRegistAmount(0.00);
                                }

                            }else{
                                RegistCapi = "" ;
                            }


                            if(TermStartJ!=null&&TermStartJ!=""){
                                DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
                                Date TermStart = df3.parse(TermStartJ);
                                entBaseInfoModel2.setTermStart(TermStart);
                            }
                            if(TeamEndJ!=null&&TeamEndJ!=""){
                                DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd");
                                Date TeamEnd = df4.parse(TeamEndJ);
                                entBaseInfoModel2.setTermEnd(TeamEnd);
                            }
                            if(CheckDateJ!=null&&CheckDateJ!=""){
                                DateFormat df5 = new SimpleDateFormat("yyyy-MM-dd");
                                Date CheckDate = df5.parse(CheckDateJ);
                                entBaseInfoModel2.setCheckDate(CheckDate);
                            }

                            String address = String.valueOf(maptJson.get("Address"));
                            String province = String.valueOf(maptJson.get("Province"));//省简称

                            String province_arr[] = {"BJ","TJ","HB","SX","NMG","LN","JL","HLJ","SH","JS","ZJ","AH","FJ","JX","SD","HEN","HUB","HUN","GD","GX","HAIN","CQ","SC","GZ","YN","XZ","SAX","GS","QH","NX","XJ"};
                            String province_str = "";//省编号
                            for(int j=0;j<province_arr.length;j++){
                                if(province_arr[j].equals(province) ){
                                    province_str = String.valueOf(j);
                                    break;
                                }
                            }
                            String city_str = getCityAndArea(address,province_str,"2");//市编号
                            String area_str = getCityAndArea(address,city_str,"3");//区编号

                            if(String.valueOf(maptJson.get("StartDate"))!=""&&String.valueOf(maptJson.get("StartDate"))!=null){
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                Date startDate = df.parse(String.valueOf(maptJson.get("StartDate")));
                                entBaseInfoModel2.setStartDate(startDate);
                            }


                            if(maptJson.get("EndDate")!=null&&(!String.valueOf(maptJson.get("EndDate")).equals(""))){
                                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                                Date endDate = df2.parse(String.valueOf(maptJson.get("EndDate")));
                                entBaseInfoModel2.setEndDate(endDate);
                            }


                            String econKind = String.valueOf(maptJson.get("EconKind"));
                            if(econKind.equals("中外合作非法人企业")){
                                econKind = "1";
                            }else if(econKind.equals("合伙企业")){
                                econKind = "2";
                            }else if(econKind.equals("股份有限公司")){
                                econKind = "3";
                            }else if(econKind.equals("有限责任公司")){
                                econKind = "4";
                            }else {
                                econKind = "" ;
                            }




                            /*塞数据Start*/
                            entBaseInfoModel2.setEnterpriseId(enterpriseId);//企业ID，主键
                            entBaseInfoModel2.setKeyNo(String.valueOf(maptJson.get("KeyNo")));
                            entBaseInfoModel2.setStatus(status);//登记状态
                            entBaseInfoModel2.setCreditCode(creditCode);

                            entBaseInfoModel2.setIsAbroad("0");

                            entBaseInfoModel2.setAddressDetails(address);
                            entBaseInfoModel2.setPrivince(province);
                            entBaseInfoModel2.setAddressProvince(province_str);
                            entBaseInfoModel2.setAddressCity(city_str);
                            entBaseInfoModel2.setAddressArea(area_str);
                            entBaseInfoModel2.setName(String.valueOf(maptJson.get("Name")));//企业全称
                            entBaseInfoModel2.setBelongOrg(String.valueOf(maptJson.get("BelongOrg")));
                            entBaseInfoModel2.setOperName(String.valueOf(maptJson.get("OperName")));
                            entBaseInfoModel2.setEconKind(econKind);

                            entBaseInfoModel2.setRegistCapi(RegistCapi);

                            entBaseInfoModel2.setCurrency(RegistCapiBZ);

                            entBaseInfoModel2.setScope(String.valueOf(maptJson.get("Scope")));
                            entBaseInfoModel2.setDeleteFlag("0");



                            entBaseInfoModel2.setCreateBy(this.getLoginUserId());
                            entBaseInfoModel2.setCreateDt(new Date());
                            entBaseInfoModel2.setUpdateBy(this.getLoginUserId());
                            entBaseInfoModel2.setUpdateDt(new Date());

                            /*塞数据End*/

                            entBaseInfoService.insert(entBaseInfoModel2);
                        }

                    }
                }
            }


            //正式开始查询企业库列表
            searchCondition.setSearchBean(searchBean);
            PageInfo<EntBaseInfoModel> rows = entBaseInfoService.selectListPage(searchCondition);
            List<EntBaseInfoListVO> list = new ArrayList<EntBaseInfoListVO>();
            for(EntBaseInfoModel model : rows.getList()){
                EntBaseInfoListVO vo = new EntBaseInfoListVO();
                BeanUtils.copyProperties(vo, model);
                String qkIndustry = model.getQkIndustry();
                CommTGicsModel commTGicsModel = commTGicsService.selectById(qkIndustry);
                if(commTGicsModel!=null){
                    String qkIndustryName = commTGicsModel.getGicsName();
                    vo.setQkIndustryNam(qkIndustryName);
                }

                list.add(vo);
            }





            dataTablesResponse.setData(list, rows);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(),"utf-8"));//防止乱码
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    //通过详细地址查找市、区
    public String getCityAndArea(String address,String province_str,String locLevel) throws Exception{
        address = URLDecoder.decode(address, "utf-8");

        String selectSql = "select LOC_ID from base.comm_t_location where instr('"+address+"',loc_name)>0 "
                + "and parent_id = '"+province_str+"' and loc_level = '"+locLevel+"'";

        List<Map<String,Object>> LM = entBaseInfoService.selectSql2Map(selectSql);
        String locId = "";
        if(LM.size()>0){
            locId = String.valueOf(LM.get(0).get("LOC_ID"));
        }
        return locId;

    }

    @ApiOperation(value="获取企业基本信息", notes="根据url的平台Id来获取项目基本信息")
    @ApiImplicitParam(name = "platId", value = "平台ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/entInfoDetail/{platId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String entInfoDetail(@PathVariable(value = "platId") String platId){
        try {
            EntBaseInfoModel ent = entBaseInfoService.getEntBaseInfoByPlatId(platId);
            EntBaseInfoListVO vo = new EntBaseInfoListVO();
            BeanUtils.copyProperties(vo, ent);
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value="获取企业基本信息", notes="根据url的企业Id来获取企业基本信息")
    @ApiImplicitParam(name = "entId", value = "企业ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/entInfoByEntId/{entId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String entInfoByEntId(@PathVariable(value = "entId") String entId){
        try {
            EntBaseInfoModel ent = entBaseInfoService.selectById(entId);
            EntBaseInfoListVO vo = new EntBaseInfoListVO();
            BeanUtils.copyProperties(vo, ent);
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value="获取企业LOGO图标", notes="获取企业LOGO图标")
    @ApiImplicitParam(name = "entId", value = "企业ID", required = true, dataType = "String")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/entLogo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String entLogo(@RequestParam(value = "entId") String entId){

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("ENT_ID",entId);
        EntLogoModel param = new EntLogoModel();
        param.setEntId(entId);
        EntLogoModel model = entLogoService.getLogoByEntId(params);
        try {
            EntLogoVO vo = new EntLogoVO();
            BeanUtils.copyProperties(vo, model);
            mapResponse.put("model", vo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        /*byte[] buffer = model.getIconBlob();
        File file = new File("d:/temp/cccc.png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try {
            bos.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }


    /**
     * 根据seq的ID生成下一个序列
     * @param sequenceName
     * @return
     * @throws Exception
     */
    public static String getSeqNextValString(String sequenceName) throws Exception{
        String nextval="-1";
        //Map<String,Object> pramsMap = new HashMap<String,Object>();
        //pramsMap.put("sequencename","sequenceName");
        nextval = String.valueOf(commTCodeService.selectOne("getSeqNextVal",sequenceName));
        //commTCodeService.selectSql2Map()
        return nextval;
    }

}


