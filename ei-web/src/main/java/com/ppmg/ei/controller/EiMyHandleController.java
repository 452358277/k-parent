package com.ppmg.ei.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.CommTCodeModel;
import com.ppmg.ei.model.EiPushFundModel;
import com.ppmg.ei.service.AppUserService;
import com.ppmg.ei.service.CommTCodeService;
import com.ppmg.ei.service.EiPushFundService;
import com.ppmg.ei.vo.EiMyHandleVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.EiMyHandleModel;
import com.ppmg.ei.service.EiMyHandleService;
@Controller
@Scope("prototype")
@Api(tags={"季度填报接口"})
public class EiMyHandleController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private EiMyHandleService eiMyHandleService;

	@Reference(check = false)
	private CommTCodeService commTCodeService;

	@Reference(check = false)
	private EiPushFundService eiPushFundService;

	@Reference(check = false)
	private AppUserService appUserService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="季度填报投企业", notes="季度填报投企业")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "groupId", value = "平台ID", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sendMonth", value = "当前季度", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/quarterReportQY", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String quarterReportQY( @RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("groupId") String groupId,  @RequestParam("sendMonth") String sendMonth){
		try {


			CommTCodeModel commTCodeModel = new CommTCodeModel();
			commTCodeModel.setCodeName(groupId);
			commTCodeModel.setParentId("266");
			commTCodeModel = commTCodeService.selectBy(commTCodeModel);
			String groupName_sql = commTCodeModel.getCodeDesc();

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addParam("groupId",groupId);
			searchCondition.addParam("groupName_sql",groupName_sql);
			PageInfo<EiMyHandleModel> rows = eiMyHandleService.selectListPage(searchCondition);
			List<EiMyHandleVO> list = new ArrayList<EiMyHandleVO>();
			for(EiMyHandleModel model : rows.getList()){

				String groupName = model.getGroupName();
				String sql1 = "SELECT COUNT(1) FROM EI.EI_MY_HANDLE T WHERE T.GROUP_NAME ='"+groupName+"' AND T.SEND_MONTH='"+sendMonth+"' AND T.DESC_PART2='1' ";
				int model0_1 = eiMyHandleService.selectCountSql(sql1);  //selectCountBy(eiMyHandleModel);

				String sql2 = "SELECT COUNT(1) FROM EI.EI_MY_HANDLE T WHERE T.GROUP_NAME ='"+groupName+"' AND T.SEND_MONTH='"+sendMonth+"' AND T.DESC_PART2='1' AND T.IS_FINISH='1' ";

				int model1 = eiMyHandleService.selectCountSql(sql2);
				int model0 = model0_1-model1;

				EiMyHandleVO vo = new EiMyHandleVO();

				vo.setGroupName(groupName);//平台名称
				vo.setIsNotFinishNum(String.valueOf(model0));//未完成
				String isNotFinishRate = "";
				String isFinishRate = "";
				if(model0_1==0){
					isNotFinishRate =  "0.0%";
					isFinishRate = "100.0%";
				}else{
					isNotFinishRate =  String.valueOf(new BigDecimal(((float)model0/model0_1)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%";//df.format(String.valueOf(((float)model0/model0_1)*100))+"%";//未完成率
					isFinishRate = String.valueOf(new BigDecimal(((float)model1/model0_1)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%";//df.format(String.valueOf(((float)model1/model0_1)*100))+"%"; //已完成率
				}

				vo.setIsNotFinishRate(isNotFinishRate);
				vo.setIsFinishNum(String.valueOf(model1));//已完成

				vo.setIsFinishRate(isFinishRate);
				vo.setTotal(String.valueOf(model0_1));//总计：未完成+已完成
				vo.setSendMonth(sendMonth);
				list.add(vo);

			}

			dataTablesResponse.setData(list, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="季度填报人事财务", notes="季度填报人事财务")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "groupId", value = "平台ID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "sendMonth", value = "当前季度", required = true, dataType = "String"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/quarterReportRS", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String quarterReportRS( @RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("groupId") String groupId,  @RequestParam("sendMonth") String sendMonth){
		try {


			CommTCodeModel commTCodeModel = new CommTCodeModel();
			commTCodeModel.setCodeName(groupId);
			commTCodeModel.setParentId("266");
			commTCodeModel = commTCodeService.selectBy(commTCodeModel);
			String groupName_sql = commTCodeModel.getCodeDesc();

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addParam("groupId",groupId);
			searchCondition.addParam("groupName_sql",groupName_sql);
			PageInfo<EiMyHandleModel> rows = eiMyHandleService.selectListPage(searchCondition);
			List<EiMyHandleVO> list = new ArrayList<EiMyHandleVO>();
			for(EiMyHandleModel model : rows.getList()){

				String groupName = model.getGroupName();
				String sql1 = "SELECT COUNT(1) FROM EI.EI_MY_HANDLE T WHERE T.GROUP_NAME ='"+groupName+"' AND T.SEND_MONTH='"+sendMonth+"' AND T.DESC_PART2='2' ";
				int model0_1 = eiMyHandleService.selectCountSql(sql1);  //selectCountBy(eiMyHandleModel);

				String sql2 = "SELECT COUNT(1) FROM EI.EI_MY_HANDLE T WHERE T.GROUP_NAME ='"+groupName+"' AND T.SEND_MONTH='"+sendMonth+"' AND T.DESC_PART2='2' AND T.IS_FINISH='1' ";

				int model1 = eiMyHandleService.selectCountSql(sql2);
				int model0 = model0_1-model1;

				EiMyHandleVO vo = new EiMyHandleVO();

				vo.setGroupName(groupName);//平台名称
				vo.setIsNotFinishNum(String.valueOf(model0));//未完成
				String isNotFinishRate = "";
				String isFinishRate = "";
				if(model0_1==0){
					isNotFinishRate =  "0.0%";
					isFinishRate = "100.0%";
				}else{
					isNotFinishRate =  String.valueOf(new BigDecimal(((float)model0/model0_1)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%";//df.format(String.valueOf(((float)model0/model0_1)*100))+"%";//未完成率
					isFinishRate = String.valueOf(new BigDecimal(((float)model1/model0_1)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%";//df.format(String.valueOf(((float)model1/model0_1)*100))+"%"; //已完成率
				}

				vo.setIsNotFinishRate(isNotFinishRate);
				vo.setIsFinishNum(String.valueOf(model1));//已完成

				vo.setIsFinishRate(isFinishRate);
				vo.setTotal(String.valueOf(model0_1));//总计：未完成+已完成
				vo.setSendMonth(sendMonth);
				list.add(vo);

			}

			dataTablesResponse.setData(list, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="季度填报基金", notes="季度填报基金")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "groupId", value = "平台ID", required = true, dataType = "String"),
			@ApiImplicitParam(name = "sendMonth", value = "当前季度", required = true, dataType = "String"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/quarterReportJJ", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String quarterReportJJ( @RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("groupId") String groupId,  @RequestParam("sendMonth") String sendMonth){
		try {


			CommTCodeModel commTCodeModel = new CommTCodeModel();
			commTCodeModel.setCodeName(groupId);
			commTCodeModel.setParentId("266");
			commTCodeModel = commTCodeService.selectBy(commTCodeModel);
			String groupName_sql = commTCodeModel.getCodeDesc();

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addParam("groupId",groupId);
			searchCondition.addParam("groupName_sql",groupName_sql);
			PageInfo<EiPushFundModel> rows = eiPushFundService.selectListPage(searchCondition);
			List<EiMyHandleVO> list = new ArrayList<EiMyHandleVO>();
			for(EiPushFundModel model : rows.getList()){

				String groupName = model.getGroupName();
				String sql1 = "SELECT COUNT(1) FROM EI.EI_PUSH_FUND t where T.GROUP_NAME ='"+groupName+"' and T.SEND_MONTH='"+sendMonth+"' ";
				int model0_1 = eiMyHandleService.selectCountSql(sql1);  //selectCountBy(eiMyHandleModel);

				String sql2 = "SELECT COUNT(1) FROM EI.EI_PUSH_FUND t where T.GROUP_NAME ='"+groupName+"' and T.SEND_MONTH='"+sendMonth+"' AND T.IS_FINISH='1' ";

				int model1 = eiMyHandleService.selectCountSql(sql2);
				int model0 = model0_1-model1;

				EiMyHandleVO vo = new EiMyHandleVO();

				vo.setGroupName(groupName);//平台名称
				vo.setIsNotFinishNum(String.valueOf(model0));//未完成
				String isNotFinishRate = "";
				String isFinishRate = "";
				if(model0_1==0){
					isNotFinishRate =  "0.0%";
					isFinishRate = "100.0%";
				}else{
					isNotFinishRate =  String.valueOf(new BigDecimal(((float)model0/model0_1)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%";//df.format(String.valueOf(((float)model0/model0_1)*100))+"%";//未完成率
					isFinishRate = String.valueOf(new BigDecimal(((float)model1/model0_1)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())+"%";//df.format(String.valueOf(((float)model1/model0_1)*100))+"%"; //已完成率
				}

				vo.setIsNotFinishRate(isNotFinishRate);
				vo.setIsFinishNum(String.valueOf(model1));//已完成

				vo.setIsFinishRate(isFinishRate);
				vo.setTotal(String.valueOf(model0_1));//总计：未完成+已完成
				vo.setSendMonth(sendMonth);
				list.add(vo);

			}

			dataTablesResponse.setData(list, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="季度填报所有年份", notes="季度填报所有年份")
	@ApiImplicitParams({
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getYear", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getYear(){
		try {

			String sql1 = "select distinct(substr(T.SEND_MONTH, 0, 4)) as SEND_MONTH from EI.EI_MY_HANDLE t order by substr(T.SEND_MONTH, 0, 4) ";
			List<Object> yearList1 = eiMyHandleService.selectSql2Obj(sql1);  //selectCountBy(eiMyHandleModel);
			List<Object> yearList = yearList1.subList(yearList1.size()-5, yearList1.size());
			mapResponse.put("model", yearList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="获得登录人所有信息", notes="获得登录人所有信息")
	@ApiImplicitParams({
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getLoginInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getLoginInfo(){
		try {

			String loginId =this.getLoginUserId();
			AppUserModel appUserModel = appUserService.selectById(this.getLoginUserId());

			mapResponse.put("appUserModel", appUserModel);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}


}

