package com.ppmg.ei.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.bean.AppUserSearchBean;
import com.ppmg.ei.dto.BdTFundLockInfoDTO;
import com.ppmg.ei.model.AppUserModel;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.vo.BdTFundLockInfoVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.BdTFundLockInfoModel;
import com.ppmg.ei.service.BdTFundLockInfoService;
@Controller
@Scope("prototype")
@Api(tags={"关键人事锁定"})
@RequestMapping("/lock")
public class BdTFundLockInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private BdTFundLockInfoService bdTFundLockInfoService;

	@Reference
	private FundInfoService fundInfoService;

	@ApiOperation(value = "关键人事锁定", notes = "关键人事锁定")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getLockList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getLockList(@RequestParam("fundId")String fundId,@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage) {
		try {
			searchCondition.addConditionEqualTo("FUND_ID",fundId);
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			PageInfo<BdTFundLockInfoModel> rows = bdTFundLockInfoService.selectListPage(searchCondition);
			List<BdTFundLockInfoVO> list = new ArrayList<>();
			for (BdTFundLockInfoModel model : rows.getList()) {
				BdTFundLockInfoVO vo = new BdTFundLockInfoVO();
				BeanUtils.copyProperties( model,vo);
				SimpleDateFormat fomat=new SimpleDateFormat("yyyy-MM-dd");
				vo.setUpdateD(fomat.format(model.getUpdateDt()));
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

	@ApiOperation(value="保存基金幕资明细信息", notes="保存基金幕资明细信息")
	@ApiImplicitParam(name = "FundShareItemDTO", value = "保存基金幕资信息实体FundShareItemDTO", required = true, dataType = "FundShareItemDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/saveLockInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveLockInfo(@RequestBody BdTFundLockInfoDTO dto){
		try {
			BdTFundLockInfoModel model = new BdTFundLockInfoModel();
			BeanUtils.copyProperties(dto,model);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotEmpty(model.getRemark())){
				model.setChangeDt(sdf.parse(model.getRemark()));
			}
			bdTFundLockInfoService.saveInfo(model,this.getLoginUserId());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="详情", notes="保存基金幕资明细信息")
	@ApiImplicitParam(name = "FundShareItemDTO", value = "保存基金幕资信息实体FundShareItemDTO", required = true, dataType = "FundShareItemDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/lockInfoDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String lockInfoDetail(@RequestParam("fundId")String fundId){

		try {
			BdTFundLockInfoVO vo = new BdTFundLockInfoVO();
			BdTFundLockInfoModel model = new BdTFundLockInfoModel();
			model.setFundId(fundId);
			List<BdTFundLockInfoModel> list=bdTFundLockInfoService.selectList(model);
			SimpleDateFormat fomat=new SimpleDateFormat("yyyy-MM-dd");
			if(list!=null&&!list.isEmpty()){
				BeanUtils.copyProperties(list.get(0),vo);
				if(list.get(0).getUpdateDt()!=null){
					vo.setUpdateD(fomat.format(list.get(0).getUpdateDt()));
				}

				dataResponse.setData(vo);
			}else{
			   FundInfoModel fund=fundInfoService.selectById(fundId);
				if(fund!=null&&StringUtils.isNotEmpty(fund.getKeyPersonLock())){
					vo.setFundId(fundId);
					vo.setLockName(fund.getKeyPersonLock());
					if(fund.getUpdateDt()!=null){
						vo.setUpdateD(fomat.format(fund.getUpdateDt()));
						vo.setRemark(fomat.format(fund.getUpdateDt()));
					}
					dataResponse.setData(vo);
				}else{
					vo.setUpdateD(fomat.format(new Date()));
					dataResponse.setData(vo);
				}
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}

}

