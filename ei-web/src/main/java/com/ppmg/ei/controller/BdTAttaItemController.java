package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ppmg.ei.model.FpAttachmentModel;
import com.ppmg.ei.model.ProjInfoModel;
import com.ppmg.ei.service.FpAttachmentService;
import com.ppmg.ei.service.ProjInfoService;
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
import com.founder.ssm.core.vo.BaseVO;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.model.BdTAttaItemModel;
import com.ppmg.ei.model.BdTFundAttaModel;
import com.ppmg.ei.service.BdTAttaItemService;
import com.ppmg.ei.service.BdTFundAttaService;
import com.ppmg.ei.vo.BdTAttaItemVO;
import com.ppmg.ei.vo.BdTFundAttaVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Scope("prototype")
@Api(tags = {"附件分类接口"})
public class BdTAttaItemController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private BdTAttaItemService bdTAttaItemService;

    @Reference
    private BdTFundAttaService bdTFundAttaService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    @Reference
    private ProjInfoService projInfoService;

    @Reference
    private FpAttachmentService fpAttachmentService;

    @ApiOperation(value = "金财文件库左侧分类列表", notes = "金财文件库左侧分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projOrFundId", value = "项目或基金ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "itemType", value = "附件类型", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/attaItemList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(@RequestParam("projOrFundId") String projOrFundId, @RequestParam("itemType") String itemType) {
        try {
            //itemType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金
            //ITEM_TYPE=1关联项目表的项目id（PROJ_TYPE=1的项目：项目投管），
            //ITEM_TYPE=3关联项目表的项目id（PROJ_TYPE=2的项目：基金投管），
            //ITEM_TYPE=2关联基金表的基金id，暂时没有
            Map<String, String> map = new HashMap<String, String>();
            map.put("itemType", itemType);
            map.put("projOrFundId", projOrFundId);

            List<BdTAttaItemModel> rows = bdTAttaItemService.getAttaItemList(map);
            List<BdTAttaItemVO> list = new ArrayList<BdTAttaItemVO>();
			
			/*for(BdTAttaItemModel model : rows){
				BdTAttaItemVO vo = new BdTAttaItemVO();
				BeanUtils.copyProperties(vo, model);
				list.add(vo);
			}*/

            if (rows.size() > 0) {
                BdTAttaItemVO vo = new BdTAttaItemVO();
                BeanUtils.copyProperties(vo, rows.get(0));
                list.add(vo);
            }

            dataTablesResponse.setData(list);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiIgnore
    @ApiOperation(value = "获取附件分类详细信息", notes = "根据url的id来获取附件分类详细信息")
    @ApiImplicitParam(name = "id", value = "附件分类ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/attaItemInfo/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@PathVariable(value = "id") String id) {
        try {
            BdTAttaItemModel model = bdTAttaItemService.selectById(id);
            BaseVO vo = new BaseVO();
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

    @ApiOperation(value = "新增附件分类信息", notes = "根据BdTAttaItemModel对象创建附件分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemName", value = "附件分类名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父键ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "itemType", value = "附件类型", required = true, dataType = "String"),
    })
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/attaItemAdd", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam("itemName") String itemName, @RequestParam("fundId") String fundId,
                      @RequestParam("parentId") String parentId, @RequestParam("itemType") String itemType) {

        try {
            BdTAttaItemModel model = new BdTAttaItemModel();
            String userId = this.getLoginUserId();
            model.setItemId(bdTAttaItemService.getAttaItemId());
            model.setItemName(itemName);
            model.setProjectOrFundId(fundId);
            model.setParentId(parentId);
            model.setItemType(itemType);
            model.setDescription(itemName);
            model.setEditable("0");
            model.setStatus("0");
            model.setCreateBy(userId);
            model.setCreateDt(new Date());
            model.setUpdateBy(userId);
            model.setUpdateDt(new Date());
            bdTAttaItemService.insert(model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }

    //	@ApiIgnore
    @ApiOperation(value = "删除附件分类信息", notes = "根据url的id来删除附件分类信息")
    @ApiImplicitParam(name = "id", value = "附件分类ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @DeleteMapping(value = "/attaItemDelete/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable(value = "id") String id) {

        try {
            BdTAttaItemModel model = new BdTAttaItemModel();
            model.setItemId(id);
            model.setStatus("1");
            bdTAttaItemService.update(model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(baseResponse);

    }

    @ApiOperation(value = "更新附件分类信息", notes = "根据url的id来指定更新附件分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "附件分类ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "itemName", value = "附件分类名称", required = true, dataType = "String")
    })
    @PutMapping(value = "/attaItemUpdate/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@PathVariable("id") String id, @RequestParam("itemName") String itemName) {

        try {
            BdTAttaItemModel model = new BdTAttaItemModel();
            model.setItemId(id);
            model.setItemName(itemName);
            bdTAttaItemService.update(model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }

        return JSONObject.toJSONString(baseResponse);

    }


    @ApiOperation(value = "附件列表", notes = "附件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "nodeId", value = "节点ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "itemType", value = "附件类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "关键字", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fileList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fileList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("nodeId") String nodeId,
                           @RequestParam("fundId") String fundId, @RequestParam("itemType") String itemType, String keyWord) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionNotEqualTo("STATUS", "1");
            searchCondition.addConditionEqualTo("FUND_ID", fundId);
            searchCondition.addConditionEqualTo("NODE_ID", nodeId);
            searchCondition.addParam("itemType", itemType);
            if (keyWord == null) {
                searchCondition.addParam("KEYWORD", keyWord);
            } else {
                keyWord = keyWord.trim();
                searchCondition.addParam("KEYWORD", "'%" + keyWord + "%'");
            }
            String userId = this.getLoginUserId();
            searchCondition.addParam("USER_ID", "'" + userId + "'");
            PageInfo<BdTFundAttaModel> rows = bdTFundAttaService.selectListPage(searchCondition);
            List<BdTFundAttaVO> list = new ArrayList<BdTFundAttaVO>();
            for (BdTFundAttaModel model : rows.getList()) {
                BdTFundAttaVO vo = new BdTFundAttaVO();
                if ("0".equals(model.getEditable())) {
                    if (!userId.equals(model.getCreateBy())) {
                        model.setEditable("1");
                    }
                }
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

    //================================20200403============新增=========================================

    @ApiOperation(value = "金财文件库右侧附件列表", notes = "南通文件库右侧附件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "nodeId", value = "节点ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "itemType", value = "附件类型（itemType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keyWord", value = "关键字", required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/fileListNT", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fileListNT(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("nodeId") String nodeId,
                             @RequestParam("fundId") String fundId, @RequestParam("itemType") String itemType
            , @RequestParam("itemName") String itemName, String keyWord) {
        try {

            searchCondition.setPageSize(length);
            searchCondition.setCurrPage(currPage);
            searchCondition.addConditionNotEqualTo("STATUS", "1");
            searchCondition.addConditionEqualTo("FUND_ID", fundId);
            if (StringUtils.isNotEmpty(nodeId)) {
                searchCondition.addConditionEqualTo("NODE_ID", nodeId);
            }
            searchCondition.addParam("itemType", itemType);
            searchCondition.addParam("itemName", itemName);
            if (keyWord == null) {
                searchCondition.addParam("KEYWORD", keyWord);
            } else {
                keyWord = keyWord.trim();
                searchCondition.addParam("KEYWORD", "'%" + keyWord + "%'");
            }
            String userId = this.getLoginUserId();
            //String userId = "100";
            //searchCondition.addParam("USER_ID", "'"+userId+"'");
            PageInfo<BdTFundAttaModel> rows = bdTFundAttaService.selectListPageNT(searchCondition);
            List<BdTFundAttaVO> list = new ArrayList<>();
            for (BdTFundAttaModel model : rows.getList()) {
                BdTFundAttaVO vo = new BdTFundAttaVO();
                //是否可编辑(1：不可编辑；0可编辑)
                if ("0".equals(model.getEditable())) {
                    if (!userId.equals(model.getCreateBy())) {
                        model.setEditable("1");
                    }
                }
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


    @ApiOperation(value = "金财文件库EI_T_ATTACHMENT表初始化数据", notes = "金财文件库EI_T_ATTACHMENT表初始化数据")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/addEiTAttachment", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addEiTAttachment(@RequestParam("projId") String projectOrFundId, @RequestParam("projType") String projType) {
        try {
            if (projectOrFundId != null) {
                String userId = this.getLoginUserId();
                if (userId == null) {
                    userId = "1200119390";
                }
                //项目类型（1：直投企业，2：投基金，3:子基金项目,4spv,5.三级基金投四级基金，6.四级基金投项目）
                //ProjInfoModel pf_Serch = projInfoService.selectById(projId);
                //projType:11:基金平台母基金；12：基金平台代管基金；13：省政府平台母基金
                //String projType = pf_Serch.getProjType();
                bdTAttaItemService.addEiTAttachment(projectOrFundId, projType, userId);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "删除金财文件库EI_T_ATTACHMENT表初始化数据", notes = "删除南通文件库EI_T_ATTACHMENT表初始化数据")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/deleteEiTAttachment", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteEiTAttachment(@RequestParam("projId") String projIdOrFundId) {
        try {
            if (projIdOrFundId != null) {
                bdTAttaItemService.delectEiTAttachment(projIdOrFundId);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "金财文件库新增", notes = "金财文件库新增")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/addBdTFundAttaDelect", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addBdTFundAttaDelect(@RequestBody BdTFundAttaModel bdTFundAttaModel) {
        try {
            if (bdTFundAttaModel != null) {
                String userId = this.getLoginUserId();
                bdTFundAttaModel.setUpdateBy(userId);
                bdTFundAttaService.addBdTFundAttaModel(bdTFundAttaModel);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    @ApiOperation(value = "金财文件库删除", notes = "金财文件库删除")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/delectBdTFundAtta", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delectBdTFundAtta(@RequestParam("keys") String attaId) {
        try {
            if (attaId != null) {
                bdTFundAttaService.deleteById(attaId);
            }
        } catch (SystemException e) {
            logger.error(e.getMessage());
            dataTablesResponse.error(e.getMessage());
        } catch (Exception e) {
            dataTablesResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(dataTablesResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
    }

}

