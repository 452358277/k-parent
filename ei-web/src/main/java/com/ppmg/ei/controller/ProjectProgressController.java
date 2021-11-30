package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.CommonSerialNoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.Util.SystemConfig;
import com.ppmg.ei.bean.ProjectProgressSearchBean;
import com.ppmg.ei.dto.ProjectProgressDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.AppUserIdNameVO;
import com.ppmg.ei.vo.ProgressCommentVO;
import com.ppmg.ei.vo.ProjInfoVO;
import com.ppmg.ei.vo.ProjectProgressVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URLDecoder;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("/projectProgress")
@Api(tags={"工作进展接口"})
public class ProjectProgressController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(check = false)
	private ProjInfoService projInfoService;

	@Reference(check = false)
	private ProjectProgressService projectProgressService;

	@Reference(check = false)
	private ProgressCommentService progressCommentService;

	@Reference(check = false)
	private FpAttachmentService fpAttachmentService;


	@Reference(check = false)
	private AppUserService appUserService;

	@Reference(check = false)
	private static CommTCodeService commTCodeService;

	@Reference(check = false)
    private FmTFileService fmTFileService;

	@ApiOperation(value="工作进展列表", notes="工作进展列表")
	@ApiImplicitParams({

	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestBody ProjectProgressSearchBean searchBean){
		try {
			//searchBean.setPageSize(10000);
			//searchBean.setCurrPage(1);
			searchCondition.setSearchBean(searchBean);
			/*最高学历查询start*/
			String createBy = searchBean.getCreateBy();
			if(createBy!=""&&createBy!=null){
				List<String> createList = Arrays.asList(searchBean.getCreateBy().split(","));
				searchCondition.addConditionIn("T1.CREATE_BY", createList);
			}

			if(searchBean.getProgressId()!=""&&searchBean.getProgressId()!=null){
				searchCondition.addConditionEqualTo("T1.PROGRESS_ID",searchBean.getProgressId());
			}

			if(searchBean.getProgressResource()!=""&&searchBean.getProgressResource()!=null){
				searchCondition.addConditionEqualTo("T1.PROGRESS_RESOURCE",searchBean.getProgressResource());
			}

			PageInfo<ProjectProgressModel> rows = projectProgressService.selectListPage(searchCondition);
			List<ProjectProgressVO> list = new ArrayList<ProjectProgressVO>();
			for(ProjectProgressModel model : rows.getList()){
				ProjectProgressVO vo = new ProjectProgressVO();

				/*long stateTimeLong = model.getCreateDt().getTime();
				long endTimeLong = (new Date()).getTime();
				// 结束时间-开始时间 = 天数
				long day = (endTimeLong-stateTimeLong)/(24*60*60*1000);
				String isEditDelete = "0" ;*/

				BeanUtils.copyProperties(vo, model);
				/*if((day>30)||(!((this.getLoginUserId()).equals(vo.getCreateBy())))||(vo.getProgressResource().equals("1"))){
					isEditDelete = "1";
				}
				vo.setIsEditDelete(isEditDelete);*/

				vo.setThatLoginUserId(this.getLoginUserId());


				SearchCondition searchCondition2 = new SearchCondition();
				String progressId = model.getProgressId();

				searchCondition2.addConditionEqualTo("PROGRESS_ID", progressId);
				searchCondition2.setPageSize(2);
				searchCondition2.setCurrPage(1);
				PageInfo<ProgressCommentModel> rows2 = progressCommentService.selectListPage(searchCondition2);
				List<ProgressCommentVO> list2 = new ArrayList<ProgressCommentVO>();


				for(ProgressCommentModel model2 : rows2.getList()){
					ProgressCommentVO progressCommentVO = new ProgressCommentVO();
					BeanUtils.copyProperties(progressCommentVO, model2);

					/*long stateTimeLong2 = model2.getCreateDt().getTime();
					long endTimeLong2 = (new Date()).getTime();
					// 结束时间-开始时间 = 天数
					long day2 = (endTimeLong2-stateTimeLong2)/(24*60*60*1000);
					String isEditDelete2 = "0" ;
					if((day2>30)||(!((this.getLoginUserId()).equals(model2.getCreateBy())))){
						isEditDelete2 = "1";
					}
					progressCommentVO.setIsEditDelete(isEditDelete2);*/


					FmTFileModel fmTFileModelC= new FmTFileModel();
					fmTFileModelC.setDataPk(progressCommentVO.getCreateBy());
					FmTFileModel fmTFileModelC2 = fmTFileService.selectBy(fmTFileModelC);
                    //String faceImageUrlC = "http://58.210.96.191:9081/imgserver/oa/faceImage/";//正式环境头像地址
					//String faceImageUrlC = "http://172.30.70.166:11808//oa/faceImage/";//UAT环境头像地址
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
					progressCommentVO.setCreateName(appUserService.selectById(model2.getCreateBy()).getName());
					//ProgressCommentList.get(i).setCreateBy(appUserService.selectById(ProgressCommentList.get(i).getCreateBy()).getName());
					progressCommentVO.setThatLoginUserId(this.getLoginUserId());
					list2.add(progressCommentVO);
				}
				vo.setProgressCommentList(list2);

                FmTFileModel fmTFileModel= new FmTFileModel();
                fmTFileModel.setDataPk(model.getCreateBy());
                FmTFileModel fmTFileModel2 = fmTFileService.selectBy(fmTFileModel);
                //String faceImageUrl = "http://58.210.96.191:9081/imgserver/oa/faceImage/";//正式环境头像地址
				//String faceImageUrl = "http://172.30.70.166:11808//oa/faceImage/";//UAT环境头像地址
				SystemConfig systemConfig = new SystemConfig();
				String faceImageUrl = systemConfig.getString("faceImageUrl");
				String filePath = "";
                if(fmTFileModel2!=null){
					filePath = fmTFileModel2.getFilePath();
				}

                if(!"".equals(faceImageUrl) && filePath!=null && !"null".equals(filePath) && !"".equals(filePath)){
                    filePath = faceImageUrl + filePath;//拼成完整的图片路径
                }else{
                    //没找到配置文件，或者数据库中图片路径为空，就显示默认图片
                    String sexy = String.valueOf(appUserService.selectById(model.getCreateBy()).getSexy());
                    //app_user里的性别说明，10008男，10009女
                    if("10009".equals(sexy)){
                        filePath = "/oaweb/style/member2.jpg";//女性默认头像
                    }else{
                        filePath = "/oaweb/style/member.jpg";//男性默认头像
                    }
                }

                vo.setFilePath(filePath);
				vo.setCreateName(appUserService.selectById(model.getCreateBy()).getName());

				String sqlComment = "select count(1) from PROGRESS_COMMENT where PROGRESS_ID="+progressId+" and STATUS!='9' ";
				int cnt = progressCommentService.selectCountSql(sqlComment);
				vo.setCommentCount(cnt);


				if(vo.getProgressFile()!= null && vo.getProgressFile()!=  ""){
					FpAttachmentModel fpAttachmentModel = new FpAttachmentModel();
					fpAttachmentModel.setFieldToken(vo.getProgressFile());
					List<FpAttachmentModel> listEiAtt = fpAttachmentService.selectList(fpAttachmentModel);
					for(int i=0;i<listEiAtt.size();i++){
						String fileSizeS = "" ;
						Long fileSizeL = Long.parseLong(listEiAtt.get(i).getFileSize());
						if(fileSizeL<1024){
							fileSizeS = String.valueOf(fileSizeL)+"B";
						}else if((fileSizeL/1024)<1024){
							fileSizeS = String.valueOf(fileSizeL/1024)+"KB";
						}else if((fileSizeL/1024/1024)<1024){
							fileSizeS = String.valueOf(fileSizeL/1024/1024)+"M";
						}
						listEiAtt.get(i).setFileSize(fileSizeS);
					}
					vo.setFileLists(listEiAtt);
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


	@ApiOperation(value="新增工作进展", notes="根据ProjectProgressDTO对象创建平台")
	@ApiImplicitParam(name = "ResumeManagementDTO", value = "工作进展实体ProjectProgressDTO", required = true, dataType = "ProjectProgressDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody ProjectProgressDTO dto){

		try {

			ProjectProgressModel model = new ProjectProgressModel();
			BeanUtils.copyProperties(model, dto);

			String projectObjectName = "";
			if(dto.getProjectObjectName()!=null&&dto.getProjectObjectName()!=""){
				projectObjectName = URLDecoder.decode(dto.getProjectObjectName(),"UTF-8");
			}

			String inveName = "";
			if(dto.getInveName()!=null&&dto.getInveName()!=""){
				inveName = URLDecoder.decode(dto.getInveName(),"UTF-8");
			}

			model.setProjectObjectName(projectObjectName);
			model.setInveName(inveName);

			String ProgressId = getSeqNextValString("EI.SEQ_PROJECT_PROGRESS");

			model.setProgressId(ProgressId);
			model.setProgressDate(new Date());

			model.setCreateBy(this.getLoginUserId());
			model.setCreateDt(new Date());
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());

			String allUserIds = dto.getAllUserIds();
			String allUserNames = dto.getAllUserNames();
			String dealMark = dto.getDealMark();
			String dist = dto.getDist();
			String projStatus = dto.getProjStatus();
			projectProgressService.insertProessAndMessage(model,allUserIds,allUserNames,this.getLoginUserId(),this.getLoginUser().getName(),dealMark,dist,projStatus);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiIgnore
	@ApiOperation(value="更新工作进展", notes="根据url的id来指定更新工作进展")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ProgressId", value = "工作进展主键ProgressId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "ResumeManagementDTO", value = "平台信息实体PlatformInfoDTO", required = true, dataType = "PlatformInfoDTO")
	})
	@PostMapping(value = "/update/{ProgressId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("ProgressId") String ProgressId, @RequestBody ProjectProgressDTO dto){

		try {

			ProjectProgressModel model = new ProjectProgressModel();
			BeanUtils.copyProperties(model, dto);
			model.setProgressId(ProgressId);
			model.setUpdateBy(this.getLoginUserId());
			model.setUpdateDt(new Date());


			//projectProgressService.update(model);
			String allUserIds = dto.getAllUserIds();
			String allUserNames = dto.getAllUserNames();
			String dealMark = dto.getDealMark();
			String dist = dto.getDist();
			String projStatus = dto.getProjStatus();
			projectProgressService.updateProessAndMessage(model,allUserIds,allUserNames,this.getLoginUserId(),this.getLoginUser().getName(),dealMark,dist,projStatus);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="获得所有的用户ID+NAME", notes="获得所有的用户ID+NAME")
	@ApiImplicitParams({
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getAllUser", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllUser(@RequestParam(value = "groupId") String groupId){
		try {
			/*AppUserModel appUserModel = new AppUserModel();
			appUserModel.setDisabled("0");
			List<AppUserModel> AppUserList = new ArrayList<AppUserModel>();
			AppUserList = appUserService.selectList(appUserModel);
			List<AppUserIdNameVO> AppUserIdNameList = new ArrayList<AppUserIdNameVO>();
			for(int i=0;i<AppUserList.size();i++){
				AppUserIdNameVO vo = new AppUserIdNameVO();
				Long id = AppUserList.get(i).getId();
				String name = AppUserList.get(i).getName();
                String loginname = AppUserList.get(i).getLoginname();
				vo.setId(id);
				vo.setName(name);
				vo.setLoginname(loginname);
				AppUserIdNameList.add(vo);
			}*/

			List<Map<String,Object>> AppUserIdNameList = appUserService.selectList("getIdNameLoginnameByGroupId",groupId);
			List<AppUserIdNameVO> AppUserIdNameVOList = new ArrayList<AppUserIdNameVO>();
			for(int i=0;i<AppUserIdNameList.size();i++){
				AppUserIdNameVO vo = new AppUserIdNameVO();
				vo.setId(Integer.parseInt(String.valueOf(AppUserIdNameList.get(i).get("ID"))));
				vo.setName(String.valueOf(AppUserIdNameList.get(i).get("NAME")));
				vo.setLoginname(String.valueOf(AppUserIdNameList.get(i).get("LOGINNAME")));
				AppUserIdNameVOList.add(vo);
			}

			//AppUserIdNameVO vo = new AppUserIdNameVO();
			//BeanUtils.copyProperties(vo, dto);


			mapResponse.put("AppUserIdNameList", AppUserIdNameVOList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}



	@ApiOperation(value="通过进展获取评论数", notes="通过进展获取评论数")
	@ApiImplicitParams({
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/commentCount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String commentCount(@RequestParam(value = "progressId") String progressId){
		try {
			String sqlComment = "select count(1) from PROGRESS_COMMENT where PROGRESS_ID="+progressId+" and STATUS!='9' ";
			int cnt = progressCommentService.selectCountSql(sqlComment);
			mapResponse.put("commentCount", cnt);

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value="APP-通过三个ID判断dealmark数据", notes="根据url的项目Id来获取项目决策信息")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getDealmarkByThreeId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDealmarkByThreeId( @RequestParam("projectObject") String projectObject,@RequestParam("inveId") String inveId,@RequestParam("groupId") String groupId){
		try {

			String getdealmarksql = "SELECT COUNT(1) FROM TZ_T_PROJ_MEMBER A1 " +
					" left join tz_t_proj_info A2 ON a2.proj_id=a1.proj_id " +
					" WHERE (A2.PROJ_OBJECT='"+projectObject+"' AND A2.INVE_ID='"+inveId+"' " +
					" AND A2.GROUP_ID='"+groupId+"' AND A1.MEMBER_ID='"+this.getLoginUserId()+"' AND A1.IS_PM!='2' " +
					" AND A2.PROJ_TYPE='1' AND A2.PROJ_STATUS!='7') OR (A2.charge_partner like '%"+this.getLoginUserId()+"%') ";

			int cnt = projectProgressService.selectCountSql(getdealmarksql);//通过orgId获取所属平台
			String dealMark = "view";
			if(cnt>0){
				dealMark = "update";
			}
			mapResponse.put("dealMark", dealMark);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}




	@ApiOperation(value="APP-项目进展新增-项目选择", notes="项目进展新增-项目选择")
	@ApiImplicitParams({

	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常")
	})
	@GetMapping(value = "/selectProject", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectProject(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String keyword){

		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_TYPE", "1");
			searchCondition.addConditionNotEqualTo("PROJ_STATUS", "7");
			searchCondition.addParam("keyword",keyword);

			//PageInfo<ProjInfoModel> rows = projInfoService.selectListPage(searchCondition);
			PageInfo<ProjInfoModel> rows = projInfoService.selectListPage("projInfogetAPPInfoXMall",searchCondition);

			List<ProjInfoVO> list = new ArrayList<ProjInfoVO>();
			for(ProjInfoModel model : rows.getList()){
				ProjInfoVO vo = new ProjInfoVO();
                BeanUtils.copyProperties(vo, model);
				list.add(vo);
			}


			dataTablesResponse.setData(list);
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

