package com.ppmg.ei.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.core.util.UimUtils;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.uim.xdk.AppGroup;
import com.ppmg.common.bean.ProcessBean;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.ExpertSearchBean;
import com.ppmg.ei.dto.ExpertDTO;
import com.ppmg.ei.model.ExpertLabelModel;
import com.ppmg.ei.model.ExpertListModel;
import com.ppmg.ei.service.ExpertLabelService;
import com.ppmg.ei.utils.ExportDataUtils;
import com.ppmg.ei.vo.ExpertLabelVO;
import com.ppmg.ei.vo.ExpertVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.core.common.SearchCondition;
import com.founder.ssm.core.action.BaseAction;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;

import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.ppmg.ei.model.ExpertModel;
import com.ppmg.ei.service.ExpertService;
/** 
 * 描述 [Controller] 
 * @author null
 * @date 2019-08-12 15:01 
 */ 
@RestController
@Scope("prototype")
@RequestMapping("/expert")
@Api(tags={"专家库接口"})
public class ExpertController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ExpertService expertService;

	@Reference
	private ExpertLabelService expertLabelService;

	@Reference
	private SerialnoService serialnoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;


	@ApiIgnore
	@GetMapping(value = "/toList")
	public String toList() {
		return "expert/list";
	}

	@ApiOperation(value = "专家库列表", notes = "专家库列表")
	@ApiParam(name = "searchBean", value = "专家库列表查询对象,传入json格式", required = false)
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(@RequestBody ExpertSearchBean searchBean) {
		try {
			PageInfo<ExpertListModel> rows = expertService.selectExpertByPage(searchCondition,searchBean);
			List<ExpertVO> list = new ArrayList<ExpertVO>();
			for (ExpertListModel expertListModel : rows.getList()) {
				ExpertVO vo = new ExpertVO();
				BeanUtils.copyProperties(expertListModel, vo);
				if(expertListModel.getSex() != null && StringUtils.isNotBlank(expertListModel.getSex())){
					vo.setSexName(codeUtils.getCodeNameByValue("2603", expertListModel.getSex()) == null ? "" : codeUtils.getCodeNameByValue("2603", expertListModel.getSex()));
				}
				//专家领域
				List<ExpertLabelModel> labelModels = expertListModel.getLabelModels();
				List<ExpertLabelVO> labelVOS = new ArrayList<>();
				String label = "";
				String labelName = "";
				if(labelModels != null && labelModels.size() > 0){
					for (ExpertLabelModel lModel:labelModels) {
						ExpertLabelVO labelVO = new ExpertLabelVO();
						BeanUtils.copyProperties(lModel,labelVO);
						labelVOS.add(labelVO);
						label = label + lModel.getLabel() + ",";
						String codeName = codeUtils.getCodeNameByValue("1028", lModel.getLabel()) == null ? "" : codeUtils.getCodeNameByValue("1028", lModel.getLabel());
						labelName = labelName + codeName +",";
					}
				}
				if(!"".equals(label)){
					label = label.substring(0,label.length()-1);
				}
				if(!"".equals(labelName)){
					labelName = labelName.substring(0,labelName.length()-1);
				}
				vo.setLabelVOS(labelVOS);
				vo.setLabelName(labelName);
				vo.setLabel(label);
				list.add(vo);
			}
			dataTablesResponse.setData(list,rows);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataTablesResponse.error(e.getMessage());
		} catch (Exception e) {
			dataTablesResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value = "获取专家库详细信息", notes = "根据url的id来获取专家库详细信息")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/expertInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String todetail(String id) {
		try {
			ExpertModel expertModel = new ExpertModel();
			expertModel.setExpertId(id);
			List<ExpertListModel> modelList = expertService.selectList(expertModel);
			ExpertListModel model = new ExpertListModel();
			if(modelList!=null&&modelList.size()>0){
				model = modelList.get(0);
			}
			ExpertVO vo = new ExpertVO();
			BeanUtils.copyProperties(model, vo);
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

	@ApiOperation(value = "删除It流程为保存（更新当前状态为0：删除）", notes = "根据url的id来删除专家库信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "申请", required = true, dataType = "String"),
	})
	@GetMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) {
		try {
			String[] idsArray = ids.split(",");
			expertService.deleteByIds(idsArray, String.valueOf(this.getLoginUser().getUserId()));
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value = "获取专家库信息进行编辑", notes = "根据url的id来获取专家库详细信息")
	@ApiImplicitParam(name = "expertId", value = "专家库ID", dataType = "String")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/toEdit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String toEdit(String dealMark, String expertId,String pkId ,ProcessBean processBean, String steps) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ExpertVO expertVO = new ExpertVO();

			if ("add".equals(dealMark)) {
				expertId = serialnoService.getSequence("BD_T_FUND_EXPERT");
				pkId = serialnoService.getSequence("BD_T_FUND_EXPERT_LABEL");
				expertVO.setExpertId(expertId);
				expertVO.setPkId(pkId);
				dataResponse.setData(expertVO);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			dataResponse.error(e.getMessage());
		} catch (Exception e) {
			dataResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(dataResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value = "新增专家库", notes = "新增专家库")
	@ApiImplicitParam(name = "dto", value = "审核实体ExpertDTO", dataType = "ExpertDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody ExpertDTO dto) {
		try {
			long userId = this.getLoginUser().getUserId();
			ExpertModel expertModel = new ExpertModel();
			BeanUtils.copyProperties(dto,expertModel);
			expertModel.setCreateBy(String.valueOf(userId));
			expertModel.setUpdateBy(String.valueOf(userId));
			expertService.insertModel(expertModel,dto.getLabel());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}


	@ApiOperation(value="更新专家库信息", notes="更新专家库信息")
	@ApiImplicitParam(name = "dto", value = "更新专家库信息", dataType = "ExpertDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@RequestBody ExpertDTO dto) {
		try {
			ExpertModel expertModel = new ExpertModel();
			BeanUtils.copyProperties(dto,expertModel);
			long userId =this.getLoginUser().getUserId();
			expertModel.setUpdateDt(new Date());
			expertModel.setUpdateBy(String.valueOf(userId));
			expertService.updateExpert(expertModel,dto.getLabel());
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteMapNullValue);
	}

	@ApiOperation(value = "导出专家库信息", notes = "根据查询信息导出专家库信息")
	@GetMapping(value = "/export")
	@ResponseBody
	public void export(ExpertSearchBean searchBean, HttpServletResponse response) {
		try {
			searchCondition.setSearchBean(searchBean);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//PageInfo<ExpertListModel> rows = expertService.selectExpertByPage(searchCondition,searchBean);
			List<ExpertListModel> models = expertService.exportList(searchCondition,searchBean);
			String[] title = new String[]{"姓名", "毕业院校", "工作单位", "职务", "邮箱", "手机"};
			String fileName = "专家库_" + System.currentTimeMillis()+".xls";
			List<String[]> dataList = new ArrayList<>();
			if(models != null && models.size() > 0){
				for (ExpertListModel exModel : models) {
					String[] data = new String[title.length];
					data[0] = exModel.getExpertName();
					data[1] = exModel.getUniversity();
					data[2] = exModel.getCompany();
					data[3] = exModel.getPosition();
					data[4] = exModel.getEmail();
					data[5] = exModel.getMobilePhone();
					dataList.add(data);
				}
			}

			Workbook wb = ExportDataUtils.exportData(dataList, title, fileName);
			if (wb != null) {
				OutputStream out = null;
				try {
					out = response.getOutputStream();
					// 告诉浏览器用什么软件可以打开此文件
					//response.setHeader("content-Type", "application/vnd.ms-excel");
					response.setContentType("application/vnd.ms-excel");
					response.setCharacterEncoding("UTF-8");
					// 下载文件的默认名称
					response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
					wb.write(out);
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (out != null) {
							out.close();
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}

