package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.EiTAttachmentModel;
import com.ppmg.ei.service.EiAttachmentService;
import com.ppmg.ei.vo.MailExchageVO;
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
import com.ppmg.ei.model.MailExchageModel;
import com.ppmg.ei.service.MailExchageService;
@Controller
@Scope("prototype")
@Api(tags={"APP-邮件往来接口"})
public class MailExchageController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private MailExchageService mailExchageService;

	@Reference
	private static EiAttachmentService eiAttachmentService;

	@ApiOperation(value="APP-邮件往来列表", notes="APP-邮件往来列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/mailExchage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String mailExchage(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,@RequestParam("email") String email){

		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addParam("email",email);
			PageInfo<MailExchageModel> rows = mailExchageService.selectListPage(searchCondition);


			List<MailExchageVO> list = new ArrayList<MailExchageVO>();
			for(MailExchageModel model : rows.getList()){
				MailExchageVO vo = new MailExchageVO();
				BeanUtils.copyProperties(vo, model);
				vo.setMailContent("");
				if(vo.getMailFile()!= null && vo.getMailFile()!=  ""){
					EiTAttachmentModel eiTAttachmentModel = new EiTAttachmentModel();
					eiTAttachmentModel.setFieldToken(vo.getMailFile());
					List<EiTAttachmentModel> listEiAtt = eiAttachmentService.selectList(eiTAttachmentModel);
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



	@ApiOperation(value="获取邮件详情", notes="根据url的mailiId来获取邮件详情")
	@ApiImplicitParam(name = "mailId", value = "邮件ID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/mailDetail/{mailId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String mailDetail(@PathVariable(value = "mailId") String mailId){
		try {
			MailExchageModel model = mailExchageService.selectById(mailId);
			MailExchageVO vo = new MailExchageVO();
			BeanUtils.copyProperties(vo, model);

			if(vo.getMailFile()!= null && vo.getMailFile()!=  ""){
				EiTAttachmentModel eiTAttachmentModel = new EiTAttachmentModel();
				eiTAttachmentModel.setFieldToken(vo.getMailFile());
				List<EiTAttachmentModel> listEiAtt = eiAttachmentService.selectList(eiTAttachmentModel);
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
			mapResponse.put("model", vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

}

