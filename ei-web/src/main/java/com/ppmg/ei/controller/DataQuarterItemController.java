package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.DataQuarterItemModel;
import com.ppmg.ei.model.DataQuarterModel;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.service.DataQuarterItemService;
import com.ppmg.ei.service.DataQuarterService;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.ProjInfoService;
import com.ppmg.ei.vo.DataQuarterItemVO;
import com.ppmg.ei.vo.SomeInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@Api(tags={"季度列表接口"})
@RequestMapping("/dataQuarter")
public class DataQuarterItemController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private DataQuarterItemService dataQuarterItemService;
	@Reference
	private DataQuarterService dataQuarterService;

	@Reference
	private ProjInfoService projInfoService;

	@Reference
	private FundInfoService fundInfoService;


	@ApiOperation(value="直投平台季度任务列表", notes="银行列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String taskTitle){
		try {
			Calendar cal = Calendar.getInstance();
			int yearStr = cal.get(Calendar.YEAR);
			//判断是否新发了任务
			Date today=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


            Date now=new Date();
			String quarter=getQuarter();
			DataQuarterModel dataQuarter=new DataQuarterModel();
			String 	sendT="";
			if("3".equals(quarter)){
			     	sendT=String.valueOf(yearStr)+"-09-25";
			}else if("4".equals(quarter)){
				sendT=String.valueOf(yearStr)+"-12-25";
			}else if("2".equals(quarter)){
				sendT=String.valueOf(yearStr)+"-6-25";
			}else if("1".equals(quarter)){
				sendT=String.valueOf(yearStr)+"-3-25";
				yearStr=yearStr-1;
			}
			   //发送日期
				Date dateD=sdf.parse(sendT);
				//发送日期为3-25,6-25,9-25,12-25
				boolean flag=dateD.getTime()<=today.getTime();
				if(flag==true){
					//现在时间时间大于发送时间
					dataQuarter.setQuarter(quarter);
				}else{
					String a=String.valueOf(Integer.parseInt(quarter)-1);
					dataQuarter.setQuarter(a);
				}
				dataQuarter.setYear(String.valueOf(yearStr));


			dataQuarter.setUserBizValue(this.getLoginUserId());
			List<DataQuarterModel> lsitQ=dataQuarterService.selectList(dataQuarter);
			if(lsitQ!=null&& !lsitQ.isEmpty()){
				searchCondition.setPageSize(pageSize);
				searchCondition.setCurrPage(currPage);
				String taskId=lsitQ.get(0).getTaskId();
				String endDate=lsitQ.get(0).getEndDate();
				searchCondition.addConditionEqualTo("task_id",taskId);
				if(StringUtils.isNotEmpty(taskTitle)){
					searchCondition.addConditionLike("TASK_TITLE","%"+taskTitle+"%");
				}
				searchCondition.addConditionEqualTo("status","0");
				PageInfo<DataQuarterItemModel> rows = dataQuarterItemService.selectListPage(searchCondition);
				List<DataQuarterItemVO> list = new ArrayList<DataQuarterItemVO>();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				for(DataQuarterItemModel model : rows.getList()){
					DataQuarterItemVO vo = new DataQuarterItemVO();
					BeanUtils.copyProperties(model,vo);
					if(endDate!=null){
						vo.setEndDate(format.parse(endDate));
					}

					list.add(vo);
				}
				dataTablesResponse.setData(list, rows);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="获取出资人详细信息", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/updateForStatus", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateForStatus(@RequestParam("itemId")String itemId,@RequestParam("status")String status){
		try {
			DataQuarterItemModel model = new DataQuarterItemModel();
			model.setItemId(itemId);
			model.setStatus(status);
			dataQuarterItemService.update(model);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="根据项目id获取其他参数", notes="根据url的id来获取XXX详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getProjInfo/{projId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String enteAuditInfo(@PathVariable(value = "projId") String projId){
		try {
			ProjInfoModel model=projInfoService.selectById(projId);
			SomeInfoVO vo = new SomeInfoVO();
			BeanUtils.copyProperties(model, vo);
			if(model!=null){
				if("2".equals(model.getProjType())&&StringUtils.isNotEmpty(model.getProjObject())){
						FundInfoModel fundM= fundInfoService.selectById(model.getProjObject());
						if(fundM!=null){
							vo.setFundId(fundM.getFundId());
							vo.setEnteId(fundM.getEnteId());
							}
				}else if("1".equals(model.getProjType())){
					vo.setEnteId(model.getProjObject());
					vo.setOldEnterpriseId(model.getOldEnterpriseId());
				}

			}

			dataResponse.setData(vo);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="省政府平台-基金管理人填报-列表", notes="省政府基金-管理人填报-列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getMcList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMcList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String taskTitle,String beginTime,String endTime){
		try {
			String quarter=getQuarter();
			//定时任务是次年1月5日1.5号，如果时间小于一月5号显示上一年的上一年，如果大于1.5号则显示当年度的
			//季度，则查询当前季度的
			Calendar cal = Calendar.getInstance();
			int yearStr = cal.get(Calendar.YEAR);
			int yearNow=0;
			//定时任务的时间
			String sendT=String.valueOf(yearStr)+"-01-05";
			Date today=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dateD=sdf.parse(sendT);
			boolean flag=  dateD.getTime()<=today.getTime();
			if(flag=true){
				//如何大于一月5号则显示上一年的
				yearNow=yearStr-1;
			}else{
				//如果但前年不大于1月5号，则显示上上年的
				yearNow=yearStr-2;
			}
			//季度定时任务每季度的最后一个月的15日生成任务，因为只有重新发了新任务，老任务才会消失
			//当前季度
			    String timeS="";
				if("2".equals(quarter)){
					//发送定时任务时间，
					 timeS= String.valueOf(yearStr)+"-06-15";
				}else if("3".equals(quarter)){
					timeS= String.valueOf(yearStr)+"-09-15";
				}else if("4".equals(quarter)){
					timeS= String.valueOf(yearStr)+"-12-15";
				}else if("1".equals(quarter)){
					timeS= String.valueOf(yearStr)+"-03-15";
				}
			   Date dateS=sdf.parse(timeS);
				String quarterLast15="";
			    String quarterLast15Year="";
			    boolean flag1=  dateS.getTime()<=today.getTime();
			    //发送时间和当前时间比较，如果大于则取当前年的当前季度的值
		       String sendTime="";
				if(flag1==true){
					quarterLast15=quarter;
					quarterLast15Year=String.valueOf(yearStr);
					sendTime=timeS;
				}else{
					//如果是第一季度，则查询上一年的第四季度
					if("1".equals(quarter)){
						quarterLast15="4";
						quarterLast15Year=String.valueOf(yearStr-1);
						sendTime=String.valueOf(yearStr-1)+"-12-15";
					}else{
						quarterLast15=String.valueOf(Integer.valueOf(quarter)-1);
						quarterLast15Year=String.valueOf(yearStr);
						sendTime=timeS;
					}
				}
				//下季度第一个月第一天
               //如果当前是第二季度
                if("1".equals(quarter)){
                	String sendTimeS=String.valueOf(yearStr-1);
                	//则查询当前季度的上一个季度
                     //则查询但前年当前季度的上一季度的值 则第一季度的一月1号的值
					searchCondition.addParam("quarterStr","4");
					searchCondition.addParam("yearN",sendTimeS);
					searchCondition.addParam("sendT",sendTimeS+"-10-01");
				}else if("2".equals(quarter)) {
					//则查询当前季度的上一个季度
					//则查询但前年当前季度的上一季度的值 则第一季度的一月1号的值
					searchCondition.addParam("quarterStr","1");
					searchCondition.addParam("yearN",yearStr);
					searchCondition.addParam("sendT",yearStr+"-01-01");
				}else if("3".equals(quarter)) {
					//则查询当前季度的上一个季度
					//则查询但前年当前季度的上一季度的值 则第一季度的一月1号的值
					searchCondition.addParam("quarterStr","2");
					searchCondition.addParam("sendT",yearStr+"-04-01");
					searchCondition.addParam("yearN",yearStr);
				}else if("4".equals(quarter)) {
					//则查询当前季度的上一个季度
					//则查询但前年当前季度的上一季度的值 则第一季度的一月1号的值
					searchCondition.addParam("quarterStr","3");
					searchCondition.addParam("sendT",yearStr+"-07-01");
					searchCondition.addParam("yearN",yearStr);
				}

				searchCondition.setPageSize(pageSize);
				searchCondition.setCurrPage(currPage);
				if(StringUtils.isNotEmpty(taskTitle)){
					searchCondition.addConditionLike("aa.TASK_TITLE","%"+taskTitle+"%");
				}
				if(StringUtils.isNotEmpty(beginTime)){
					searchCondition.addParam("beginTime",beginTime);
				}
				if(StringUtils.isNotEmpty(endTime)){
					searchCondition.addParam("endTime",endTime);
				}
			    searchCondition.addParam("yearNow",yearNow);
			    searchCondition.addParam("yearStr",yearStr);

			    searchCondition.addParam("sendTime ",sendTime);
			    System.out.print("**********************sendTime*********************************************************"+sendTime);
			    searchCondition.addParam("quarterLast15",quarterLast15);
			    searchCondition.addParam("quarterLast15Year",quarterLast15Year);

			    searchCondition.addParam("userId",this.getLoginUserId());
			    searchCondition.addParam("quarter",quarter);
				searchCondition.addConditionEqualTo("aa.status","0");
				PageInfo<DataQuarterItemModel> rows = dataQuarterItemService.selectListMcPage(searchCondition);
				List<DataQuarterItemVO> list = new ArrayList<DataQuarterItemVO>();
				for(DataQuarterItemModel model : rows.getList()){
					DataQuarterItemVO vo = new DataQuarterItemVO();
					BeanUtils.copyProperties(model,vo);
					if(model.getCreateDt()!=null){
                        vo.setCreateDtStr(sdf.format(model.getCreateDt()));
                    }
					if(StringUtils.isNotEmpty(model.getCloseDate())){
						vo.setEndDate(sdf.parse(model.getCloseDate()));
					}

					list.add(vo);
				}
				dataTablesResponse.setData(list, rows);


		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}
























	public String getQuarter(){
		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;
		String quarter = "1";
		if (m >= 1 && m <= 3) {
			quarter = "1";
		}
		if (m >= 4 && m <= 6) {
			quarter = "2";
		}
		if (m >= 7 && m <= 9) {
			quarter = "3";
		}
		if (m >= 10 && m <= 12) {
			quarter = "4";
		}
		return quarter;
	}
}

