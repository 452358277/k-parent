package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.MeetingRecordModel;
import com.ppmg.ei.service.MeetingRecordService;
import com.ppmg.ei.vo.MeetingRecordVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"合伙人会议接口"})
public class MeetingRecordController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private MeetingRecordService meetingRecordService;

	@Reference(check = false)
	private SerialnoService serialnoService;

    @Resource(name="codeUtils")
    private CodeUtils codeUtils;


    @ApiOperation(value="APP-会议纪要列表", notes="APP-会议纪要列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/meetingRecordAppList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String meetingRecordAppList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("projectObject") String projectObject){
        try {

            searchCondition.setPageSize(pageSize);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("OBJECT_ID", projectObject);
            searchCondition.addConditionEqualTo("DEL_FLAG", "0");
            PageInfo<MeetingRecordModel> rows = meetingRecordService.selectListPage(searchCondition);
            List<MeetingRecordVO> list = new ArrayList<MeetingRecordVO>();
            for(MeetingRecordModel model : rows.getList()){
                MeetingRecordVO vo = new MeetingRecordVO();
                BeanUtils.copyProperties(vo, model);
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

    @ApiOperation(value="合伙人会议", notes="合伙人会议列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/meetingRecordList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String meetingRecordList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam(value="fundId", required=false) String fundId,@RequestParam(value="mcName", required=false) String mcName,@RequestParam("projectObject") String projectObject){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("OBJECT_ID", projectObject);
			if(StringUtils.isNotEmpty(fundId)){
				searchCondition.addConditionEqualTo("FUND_ID",fundId );
			}
            searchCondition.addConditionEqualTo("DEL_FLAG", "0");
			PageInfo<MeetingRecordModel> rows = meetingRecordService.selectListPage(searchCondition);
			List<MeetingRecordVO> list = new ArrayList<MeetingRecordVO>();
			for(MeetingRecordModel model : rows.getList()){
				MeetingRecordVO vo = new MeetingRecordVO();
				BeanUtils.copyProperties(vo, model);
                if(StringUtils.isNotEmpty(model.getMeetingType())){
                    vo.setMeetingTypeName(codeUtils.getCodeNameByValue("200", model.getMeetingType()));
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

	@ApiOperation(value="合伙人会议新增", notes="合伙人会议新增")
	@ApiImplicitParam(name = "MeetingRecordVO", value = "MeetingRecordVO", required = true, dataType = "MeetingRecordVO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/meetingRecord/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody MeetingRecordVO dto){
		try {
			MeetingRecordModel model=new MeetingRecordModel();
			BeanUtils.copyProperties(model, dto);
			long userId = this.getLoginUser().getUserId();
			//long userId =12342342;
			model.setCreateBy(String.valueOf(userId));
			model.setCreateDt(new Date());
			model.setUpdateBy(String.valueOf(userId));
			model.setUpdateDt(new Date());
			model.setDelFlag("0");
			model.setMeetingId(serialnoService.getSequence("EI.BD_T_MEETING_RECORD"));
			meetingRecordService.insert(model);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="合伙人会议更新", notes="根据url的id来指定更新合伙人会议")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "meetingId", value = "meetingId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "FundMcInfoVO", value = "FundAnalysisReportVO", required = true, dataType = "FundAnalysisReportVO")
	})
	@PostMapping(value = "/meetingRecord/update/{meetingId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("meetingId") String meetingId, @RequestBody MeetingRecordVO dto){
		try {
			MeetingRecordModel model=new MeetingRecordModel();
			BeanUtils.copyProperties(model, dto);
			model.setMeetingId(meetingId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());
			meetingRecordService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "meetingId", value = "meetingId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/meetingRecord/delete/{meetingId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("meetingId") String meetingId){

		try {
			MeetingRecordModel model = new MeetingRecordModel();
			model.setMeetingId(meetingId);
			model.setDelFlag("1");
			meetingRecordService.update(model);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "meetingId", value = "meetingId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/meetingRecord/deleteByIds", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteByIds(@PathVariable("ids") String ids){

		try {
			if(StringUtils.isEmpty(ids)){
				List<String> idsStr = Arrays.asList(ids.split(","));
				for(String meetingId:idsStr){
					MeetingRecordModel model = new MeetingRecordModel();
					model.setMeetingId(meetingId);
					model.setDelFlag("1");
					meetingRecordService.update(model);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="合伙人会议详细信息", notes="根据url的id来获取合伙人会议细信息")
	@ApiImplicitParam(name = "id", value = "出资人ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/meetingRecord/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String promoterInfo(@PathVariable(value = "id") String id){
		try {
			MeetingRecordModel model = meetingRecordService.selectById(id);
			MeetingRecordVO vo = new MeetingRecordVO();
			BeanUtils.copyProperties(vo, model);
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

}

