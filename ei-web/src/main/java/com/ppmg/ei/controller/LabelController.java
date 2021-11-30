package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.core.vo.BaseResponse;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.bean.LabelSearchBean;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.LabelVO;
import easyexcel.exception.ExcelException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@Api(tags={"标签库接口"})
public class LabelController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private LabelService labelService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Reference
	private CommTLabelItemService commTLabelItemService;


	@Reference
	private EntLabelService entLabelService;

	@Resource(name = "codeUtils")
	private CodeUtils codeUtils;

	@Reference
	private AppRoleService appRoleService;

	@Reference
	private ProjInfoService projInfoService;


	@Reference
	private CommTEntLabelItemService commTEntLabelItemService;



	@ApiOperation(value="查询标签库", notes="查询标签库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/label/labelList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundMcInfoList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("STATUS","0");
			PageInfo<LabelModel> rows =labelService.selectListPage(searchCondition);
			List<LabelVO> list = new ArrayList<LabelVO>();
			for(LabelModel model : rows.getList()){
				LabelVO vo = new LabelVO();
				BeanUtils.copyProperties(vo, model);
				CommTLabelItemModel comT=new CommTLabelItemModel();
				comT.setLabelId(model.getLabelId());
				comT.setStatus("0");
				List<CommTLabelItemModel> co=commTLabelItemService.selectList(comT);
				vo.setLabelItemList(co);

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

	@ApiIgnore
	@ApiOperation(value="新增标签", notes="新增合同")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/label/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestParam("labelName")String labelName,String labelId,String status,String parentId){
		try {
			if(StringUtils.isNotEmpty(labelId)){
				//修改标签
				if("1".equals(status)){
					//删除，先判断企业是否选中该标签
					EntLabelModel entLabelModel=new EntLabelModel();
					entLabelModel.setLabelId(labelId);
					List<EntLabelModel> listE=entLabelService.selectList(entLabelModel);
					if(listE!=null && !listE.isEmpty()){
						baseResponse.setMsg("标签被使用，不可删除！");
						return JSONObject.toJSONString(baseResponse);
					}else{
						labelService.deleteBy(labelId);
					}

				}else{
					LabelModel labelModel=	labelService.selectById(labelId);
					if(labelModel!=null && !labelName.equals(labelModel.getLabelName())){
						baseResponse.setMsg("标签名已存在！");
						return JSONObject.toJSONString(baseResponse);
					}
					LabelModel model=new LabelModel();
					String userId=this.getLoginUserId();
					model.setLabelName(labelName);
					model.setUpdateBy(userId);
					model.setUpdateDt(new Date());
					model.setStatus(status);
					model.setLabelId(labelId);
					labelService.update(model);
				}

			}else{
				//新增标签，查询标签是否存在，便签不可重复
				LabelModel modelS=new LabelModel();
				modelS.setLabelName(labelName);
				modelS.setStatus("0");
				List<LabelModel> listL=labelService.selectList(modelS);
				if(listL!=null&&!listL.isEmpty()){
					baseResponse.setMsg("标签名已存在！");
					return JSONObject.toJSONString(baseResponse);
				}
				LabelModel model=new LabelModel();
				String userId=this.getLoginUserId();
				model.setLabelName(labelName);
				model.setCreateBy(userId);
				model.setParentId(parentId);
				model.setCreateDt(new Date());
				model.setUpdateBy(userId);
				model.setUpdateDt(new Date());
				model.setStatus("0");
				String  id=serialnoService.getSequence("BASE.COMM_T_LABEL");
				model.setLabelId(id);
				labelService.insert(model);
			}




		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiOperation(value="查询子标签", notes="查询标签详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/label/labelSonList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String labelSonList(){
		try {
			List<LabelModel> listItem =labelService.selectList("getLabelSonList","");
			List<LabelVO> list = new ArrayList<LabelVO>();
			for(LabelModel model : listItem){
				LabelVO vo = new LabelVO();
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




	//查询标签详情
	@ApiOperation(value="查询标签详情", notes="查询标签详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/label/labelItemList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String labelItemList(){
		try {
			List<LabelModel> listItem =labelService.selectListByItem();
			List<LabelVO> list = new ArrayList<LabelVO>();
			for(LabelModel model : listItem){
				LabelVO vo = new LabelVO();
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

	@ApiIgnore
	@ApiOperation(value="新增合同", notes="新增合同")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/label/saveItem", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveItem(@RequestBody List<LabelSearchBean> labels){
		try {
           if(labels!=null && !labels.isEmpty()){
			   commTLabelItemService.saveItemInfo(labels,this.getLoginUserId());
			   //saveItemInfo(labels,"110");
		   }

		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	public void saveItemInfo(List<LabelSearchBean> labels,String userId) {
		for(LabelSearchBean model:labels){
			//标签下的属性
			//1,2  1,3,4  1,2  3,4
			String [] labName=model.getNames().split(",");
			List<String> list =Arrays.asList(labName);
			List<String> list1 =Arrays.asList(labName);
			List<String>  list3=Arrays.asList(labName);
			CommTLabelItemModel commTLabelItemModel=new CommTLabelItemModel();
			commTLabelItemModel.setLabelId(model.getLabelId());
			//原来属性个数
			List<CommTLabelItemModel> label=commTLabelItemService.selectList(commTLabelItemModel);
			//原来属性个数
			List<String> propertyS=new ArrayList<>();
			List<String> propertyStr=new ArrayList<>();
			for(CommTLabelItemModel mo :label){
				propertyS.add(mo.getProperty());
				propertyStr.add(mo.getProperty());
			}


			propertyS.retainAll(list);
			System.out.print("交集为"+propertyS);
			if(label!=null && !label.isEmpty()){
				//有交集且交集如实例  1,2， 1,2 或者 1.2,3
				if(propertyS!=null&&!propertyS.isEmpty()){
					if(label.size()==propertyS.size()){
						//新数据去除重复的数据
						List<String> listR=listrem(list1,propertyS);
						//list1.removeAll(propertyS);
						if(listR!=null&&!listR.isEmpty()){
							for(String list2:listR){
								CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
								commTLabelItem.setLabelId(model.getLabelId());
								commTLabelItem.setProperty(list2);
								commTLabelItem.setItemId(UUID.randomUUID().toString());
								commTLabelItem.setStatus("0");
								commTLabelItem.setCreateBy(userId);
								commTLabelItem.setCreateDt(new Date());
								commTLabelItem.setUpdateBy(userId);
								commTLabelItem.setUpdateDt(new Date());
								commTLabelItemService.insert(commTLabelItem);

							}
						}
					}
					//有交集且交集如实例  1,3，7  1,4,5
					if(propertyS.size()<label.size()){
						//老数据先取出交集部分，删除多余部分 3，3
						//propertyStr.removeAll(propertyS);
						//propertyStr是原来的数据和propertyS
						List<String> listRe=listrem(propertyStr,propertyS);
						System.out.print("**********listRe****************"+listRe);
						System.out.print("**********listRe.size()****************"+listRe.size());
						for(String o:listRe){
							CommTLabelItemModel commTLabe3=new CommTLabelItemModel();
							commTLabe3.setLabelId(model.getLabelId());
							commTLabe3.setProperty(o);
							if(StringUtils.isNotEmpty(model.getLabelId())&&StringUtils.isNotEmpty(o)){
								commTLabelItemService.delete(commTLabe3);
                             /*   CommTLabelItemModel  b= commTLabelItemService.selectBy(commTLabe3);
                                CommTEntLabelItemModel commTEntLabelItemModel=new CommTEntLabelItemModel();
                                commTEntLabelItemModel.setLabelId(model.getLabelId());
                                if(b!=null){
                                    commTEntLabelItemModel.setItemId(b.getItemId());
                                    commTEntLabelItemService.delete(commTEntLabelItemModel);
                                }*/

							}

						}
						//新数据
						List<String> listRem=listrem(list3,propertyS);
						//list3.removeAll(propertyS);
						System.out.print("list3***************"+list3);
						if(listRem!=null&&!listRem.isEmpty()){
							for(String proI:listRem){
								CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
								commTLabelItem.setLabelId(model.getLabelId());
								commTLabelItem.setProperty(proI);
								commTLabelItem.setItemId(UUID.randomUUID().toString());
								commTLabelItem.setStatus("0");
								commTLabelItem.setCreateBy(userId);
								commTLabelItem.setCreateDt(new Date());
								commTLabelItem.setUpdateBy(userId);
								commTLabelItem.setUpdateDt(new Date());
								commTLabelItemService.insert(commTLabelItem);

							}
						}

					}


				}else{
					if(propertyS==null||propertyS.isEmpty()){
						System.out.print("**********wu****************"+list1);
						for(CommTLabelItemModel comm:label) {
							//没有交集 如 1,2  4,3  先删除 1,2的老数据
							commTLabelItemService.deleteById(comm.getItemId());
                                /*CommTEntLabelItemModel commTEntLabelItemModel=new CommTEntLabelItemModel();
                                commTEntLabelItemModel.setLabelId(comm.getLabelId());
                                commTEntLabelItemModel.setItemId(comm.getItemId());
                                commTEntLabelItemService.delete(commTEntLabelItemModel);*/

						}
					}
					//新增4,3的数据
					for(int i=0;i<labName.length;i++ ){
						CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
						commTLabelItem.setLabelId(model.getLabelId());
						commTLabelItem.setProperty(labName[i]);
						commTLabelItem.setItemId(UUID.randomUUID().toString());
						commTLabelItem.setStatus("0");
						commTLabelItem.setCreateBy(userId);
						commTLabelItem.setCreateDt(new Date());
						commTLabelItem.setUpdateBy(userId);
						commTLabelItem.setUpdateDt(new Date());
						commTLabelItemService.insert(commTLabelItem);
					}
				}

			}else{
				for(int i=0;i<labName.length;i++ ){
					CommTLabelItemModel commTLabelItem=new CommTLabelItemModel();
					commTLabelItem.setLabelId(model.getLabelId());
					commTLabelItem.setProperty(labName[i]);
					commTLabelItem.setItemId(UUID.randomUUID().toString());
					commTLabelItem.setStatus("0");
					commTLabelItem.setCreateBy(userId);
					commTLabelItem.setCreateDt(new Date());
					commTLabelItem.setUpdateBy(userId);
					commTLabelItem.setUpdateDt(new Date());
					commTLabelItemService.insert(commTLabelItem);
				}
			}
		}

	}

	/**
	 * 从listA里删除listB里有的数据
	 * @param listA
	 * @param listB
	 * @return
	 */
	public static List<String> listrem(List<String> listA,List<String> listB){
		System.out.print("************jinruquchu **************");
		HashSet hs1 = new HashSet(listA);
		HashSet hs2 = new HashSet(listB);
		hs1.removeAll(hs2);
		System.out.print("************removeAll end **************");
		List<String> listC = new ArrayList<String>();
		listC.addAll(hs1);
		return listC;
	}


	@ApiOperation(value = "已投基金列表", notes = "获取平台管理基金列表")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/projExport", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse projExport(String keyword, @RequestParam("labelId")String labelId, String labelName, HttpServletResponse response, HttpServletRequest request) {
        try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println("调用导出的开始时间为****************"+df.format(new Date()));// new Date()为获取当前系统时间
            String fileName = labelName+"统计报表";
			// 表单
			Sheet sheet = new Sheet(1,0);
			sheet.setSheetName(labelName);
			// 创建一个表格
			Table table = new Table(1);
			// 动态添加 表头 headList --> 所有表头行集合
			List<List<String>> headList = new ArrayList<List<String>>();
			List<String> headX = new ArrayList<String>();
			headX.add("序号");
			headList.add(headX);
			List<String> headS = new ArrayList<String>();
			headS.add("被投企业");
			headList.add(headS);
			List<String> headS1 = new ArrayList<String>();
			headS1.add("二级基金");
			headList.add(headS1);
			List<String> headS2 = new ArrayList<String>();
			headS2.add("三级基金");
			headList.add(headS2);

			//原来属性个数
			Map<String, Object> param1 = new HashMap<String, Object>();
			param1.put("labelId",labelId);
			if(StringUtils.isNotEmpty(keyword)){
				param1.put("keyWord",keyword.trim());
			}else{
				param1.put("keyWord","");
			}

			//查询标签下面所有的属性
			CommTLabelItemModel commTLabelItemModel=new CommTLabelItemModel();
			commTLabelItemModel.setLabelId(labelId);

			List<CommTLabelItemModel> label=commTLabelItemService.selectList(commTLabelItemModel);
			if(label!=null&&!label.isEmpty()) {
				for (CommTLabelItemModel commTLabelIte : label) {
					if(StringUtils.isNotEmpty(commTLabelIte.getProperty())){
						String pro = codeUtils.getCodeNameByValue("10270", commTLabelIte.getProperty());
						List<String> proList = new ArrayList<String>();
						proList.add(pro);
						headList.add(proList);
					}

				}
				List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
				if(listRow!=null&&listRow.size()>0){
					if(1005002==listRow.get(0).getId()||1005003==listRow.get(0).getId()){
						param1.put("userId", this.getLoginUserId());
					}else{
						param1.put("userId", "");

					}
				}else{
					param1.put("userId", "");

				}
			}
				List<ProjInfoModel> listModel = projInfoService.selectImportInveList(param1);
				List<List<Object>> list = new ArrayList<List<Object>>();
				int i=0;
				for(ProjInfoModel projInfoModel:listModel){
					List<Object> row = new ArrayList<Object>();
					 i=i+1;
					row.add(i);
					row.add(projInfoModel.getEntName());
					row.add(projInfoModel.getTwoFundName());
					row.add(projInfoModel.getThreeFundName());
                    if(projInfoModel.getListcommT()!=null&&!projInfoModel.getListcommT().isEmpty()){
                        List<CommTLabelItemModel> listC=projInfoModel.getListcommT();
                        Collections.sort(listC, new Comparator<CommTLabelItemModel>() {
                            @Override
                            public int compare(CommTLabelItemModel o1, CommTLabelItemModel o2) {
                                //return o1.para - o2.para;  //升序
                                return Integer.parseInt(o1.getProperty()) - Integer.parseInt(o2.getProperty());
                            }
                        });
                        for(CommTLabelItemModel commTLabelItem:listC){
                            row.add(commTLabelItem.getLabelName());
                        }
                    }
				/*	Map<String, Object> param = new HashMap<String, Object>();
					param.put("entId",projInfoModel.getProjObject());
					 param.put("labelId",labelId);
					List<CommTLabelItemModel> listM=commTLabelItemService.selectList("selectImportLabelInfo",param);
					if(listM!=null&&!listM.isEmpty()){
						for (CommTLabelItemModel map : listM) {
							row.add(map.getLabelName());
						}
					}*/

					list.add(row);
				}
				table.setHead(headList);
				OutputStream out = getOutputStream(fileName, response, ExcelTypeEnum.XLSX, request);
				ExcelWriter excelWriter = EasyExcelFactory.getWriter(out);
				excelWriter.write1(list,sheet,table);
				// 记得 释放资源
				excelWriter.finish();
			    out.close();
			} catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
         }
        return dataTablesResponse;
    }

		private static OutputStream getOutputStream(String fileName, HttpServletResponse response, ExcelTypeEnum excelTypeEnum, HttpServletRequest request) throws ExcelException {
			String filePath = fileName + excelTypeEnum.getValue();

			try {
				;
				if(isMSBrowser(request)){
					fileName = new String(filePath.getBytes(), "UTF-8");
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
				}else{
					fileName = new String(filePath.getBytes(), "ISO-8859-1");
					response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
				}

				return response.getOutputStream();
			} catch (IOException var5) {
				throw new ExcelException("创建文件失败！");
			}
		}


	//方法功能描述: 判断是否是IE浏览器
	public static boolean isMSBrowser(HttpServletRequest request) {
		String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
		String userAgent = request.getHeader("User-Agent");
		for (String signal : IEBrowserSignals) {
			if (userAgent.contains(signal)){
				return true;
			}
		}
		return false;
	}


	//















	@ApiOperation(value = "省政府二级标签")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/getLabelAll", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse getLabelAll() {
		try {
			LabelModel label=new LabelModel();
			label.setParentId("0");
			label.setStatus("0");
			List<LabelModel> list=  labelService.selectList(label);
			for(LabelModel model:list){
				//判断是否有子
				LabelModel label1=new LabelModel();
				label1.setParentId(model.getLabelId());
				label1.setStatus("0");
				List<LabelModel> listStr=  labelService.selectList(label1);
				if(listStr!=null && !listStr.isEmpty()){
					model.setListStr(listStr);
				}
			}
			mapResponse.put("list", list);
		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return mapResponse;
	}


}

