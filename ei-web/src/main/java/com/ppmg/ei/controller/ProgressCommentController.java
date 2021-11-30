package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.Util.SystemConfig;
import com.ppmg.ei.dto.ProgressCommentDTO;
import com.ppmg.ei.model.FmTFileModel;
import com.ppmg.ei.model.ProgressCommentModel;
import com.ppmg.ei.model.SysMessageModel;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.ProgressCommentVO;
import com.ppmg.ei.vo.SysMessageVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/progressComment")
@Api(tags={"进展评论接口"})
public class ProgressCommentController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProgressCommentService progressCommentService;

	@Reference(check = false)
	private static CommTCodeService commTCodeService;

	@Reference
	private AppUserService appUserService;

	@Reference
	private FmTFileService fmTFileService;

	@Reference
	private SysMessageService sysMessageService;


	@ApiOperation(value="评论列表", notes="评论列表")
	@ApiImplicitParams({

	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("progressId") String progressId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROGRESS_ID", progressId);

			PageInfo<ProgressCommentModel> rows = progressCommentService.selectListPage(searchCondition);
			List<ProgressCommentVO> list = new ArrayList<ProgressCommentVO>();
			for(ProgressCommentModel model : rows.getList()){
				ProgressCommentVO progressCommentVO = new ProgressCommentVO();
				BeanUtils.copyProperties(progressCommentVO, model);


				/*long stateTimeLong2 = progressCommentVO.getCommentDt().getTime();
				long endTimeLong2 = (new Date()).getTime();
				// 结束时间-开始时间 = 天数
				long day2 = (endTimeLong2-stateTimeLong2)/(24*60*60*1000);
				String isEditDelete2 = "0" ;
				if((day2>30)||(!((this.getLoginUserId()).equals(progressCommentVO.getCreateBy())))){
					isEditDelete2 = "1";
				}
				progressCommentVO.setIsEditDelete(isEditDelete2);*/


				FmTFileModel fmTFileModelC= new FmTFileModel();
				fmTFileModelC.setDataPk(progressCommentVO.getCreateBy());
				FmTFileModel fmTFileModelC2 = fmTFileService.selectBy(fmTFileModelC);
				//String faceImageUrlC = "http://58.210.96.191:9081/imgserver/oa/faceImage/";
				SystemConfig systemConfigC = new SystemConfig();
				String faceImageUrlC = systemConfigC.getString("faceImageUrl");
				String filePathC = "";
				if(fmTFileModelC2!=null){
					filePathC = fmTFileModelC2.getFilePath();
				}

				if(!"".equals(faceImageUrlC) && filePathC!=null && !"null".equals(filePathC) && !"".equals(filePathC)){
					filePathC = faceImageUrlC + filePathC;//拼成完整的图片路径
				}else{
					//没找到配置文件，或者数据库中图片路径为空，就显示默认图片
					String sexyC = String.valueOf(appUserService.selectById(progressCommentVO.getCreateBy()).getSexy());
					//app_user里的性别说明，10008男，10009女
					if("10009".equals(sexyC)){
						filePathC = "/oaweb/style/member2.jpg";//女性默认头像
					}else{
						filePathC = "/oaweb/style/member.jpg";//男性默认头像
					}
				}

				progressCommentVO.setFilePath(filePathC);
				progressCommentVO.setCreateName(appUserService.selectById(model.getCreateBy()).getName());
				progressCommentVO.setThatLoginUserId(this.getLoginUserId());
				list.add(progressCommentVO);

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



	@ApiOperation(value="新增评论", notes="根据ProgressCommentDTO对象创建新评论")
	@ApiImplicitParam(name = "ProgressCommentDTO", value = "工作进展实体progressCommentDTO", required = true, dataType = "ProgressCommentDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody ProgressCommentDTO dto){

		try {

			ProgressCommentModel model = new ProgressCommentModel();
			BeanUtils.copyProperties(model, dto);

			String commentId = getSeqNextValString("EI.SEQ_PROGRESS_COMMENT");//SequenceUtil.getSequence("PROGRESS_COMMENT");
			model.setCommentId(commentId);
			model.setCommentDt(new Date());

			model.setCreateBy(this.getLoginUserId());
			model.setCreateDt(new Date());
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());

			String allUserIds = dto.getAllUserIds();
			String allUserNames = dto.getAllUserNames();
			//String projectObjectName = dto.getProjectObjectName();
			//String inveName = dto.getInveName();

			String dealMark = dto.getDealMark();
			String dist = dto.getDist();
			String PROJ_STATUS = dto.getProjStatus();

			progressCommentService.insertCommentAndMessage(model,allUserIds,allUserNames,this.getLoginUserId(),this.getLoginUser().getName(),dealMark,dist,PROJ_STATUS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="获取关于进展评论的消息列表", notes="获取关于进展评论的消息列表")
	@ApiImplicitParams({
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/messagelist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String messagelist(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage ){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("STATUS", "0");
			searchCondition.addConditionLike("MSG_TITLE","%被投企业为%出资主体为%进展内容为%");

			PageInfo<SysMessageModel> rows = sysMessageService.selectListPage(searchCondition);
			List<SysMessageVO> list = new ArrayList<SysMessageVO>();
			for(SysMessageModel model : rows.getList()){
				SysMessageVO sysMessageVO = new SysMessageVO();
				BeanUtils.copyProperties(sysMessageVO, model);

				FmTFileModel fmTFileModelC= new FmTFileModel();
				fmTFileModelC.setDataPk(sysMessageVO.getReceiveUserId());
				FmTFileModel fmTFileModelC2 = fmTFileService.selectBy(fmTFileModelC);
				//String faceImageUrlC = "http://58.210.96.191:9081/imgserver/oa/faceImage/";
				SystemConfig systemConfigC = new SystemConfig();
				String faceImageUrlC = systemConfigC.getString("faceImageUrl");
				String filePathC = "";
				if(fmTFileModelC2!=null){
					filePathC = fmTFileModelC2.getFilePath();
				}

				if(!"".equals(faceImageUrlC) && filePathC!=null && !"null".equals(filePathC) && !"".equals(filePathC)){
					filePathC = faceImageUrlC + filePathC;//拼成完整的图片路径
				}else{
					//没找到配置文件，或者数据库中图片路径为空，就显示默认图片
					String sexyC = String.valueOf(appUserService.selectById(sysMessageVO.getReceiveUserId()).getSexy());
					//app_user里的性别说明，10008男，10009女
					if("10009".equals(sexyC)){
						filePathC = "/oaweb/style/member2.jpg";//女性默认头像
					}else{
						filePathC = "/oaweb/style/member.jpg";//男性默认头像
					}
				}

				sysMessageVO.setFilePath(filePathC);



				list.add(sysMessageVO);

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



	@ApiOperation(value="将未读消息更新为已读", notes="将未读消息更新为已读")
	@ApiImplicitParams({
	})
	@GetMapping(value = "/updateMmessageStatus", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateMmessageStatus(@RequestParam("msgIds") String msgIds){

		try {
			String sql = "UPDATE BASE.YH_SYS_MESSAGE T SET T.STATUS='1' WHERE T.MSG_ID IN ("+msgIds+")";
			sysMessageService.updateSql(sql);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}





	/**
	 * 根据seq的ID生成下一个序列
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	public static String getSeqNextValString(String sequenceName) throws Exception{
		String nextval="-1";

		//select ${value}.nextval SEQVALUE from dual
		nextval = String.valueOf(commTCodeService.selectOne("getSeqNextVal",sequenceName));

		return nextval;
	}


}

