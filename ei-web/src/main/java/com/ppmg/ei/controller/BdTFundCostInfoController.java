package com.ppmg.ei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.BdTFundCostInfoDTO;
import com.ppmg.ei.dto.BdTFundLockInfoDTO;
import com.ppmg.ei.model.BdTFundLockInfoModel;
import com.ppmg.ei.vo.BdTFundCostInfoVO;
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
import com.ppmg.ei.model.BdTFundCostInfoModel;
import com.ppmg.ei.service.BdTFundCostInfoService;
@Controller
@Scope("prototype")
@Api(tags={"?????????????????????"})
public class BdTFundCostInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private BdTFundCostInfoService bdTFundCostInfoService;

	@Resource(name = "codeUtils")
	private CodeUtils codeUtils;

	@ApiOperation(value="?????????????????????", notes="?????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "????????????", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "????????????", required = true, dataType = "int")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "????????????"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
    })
	@GetMapping(value = "/costList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String costList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,String fundId){
		try {

			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("FUND_ID",fundId);
			searchCondition.addConditionEqualTo("STATUS","0");
			PageInfo<BdTFundCostInfoModel> rows = bdTFundCostInfoService.selectListPage(searchCondition);
			List<BdTFundCostInfoVO> list = new ArrayList<BdTFundCostInfoVO>();
			for(BdTFundCostInfoModel model : rows.getList()){
				BdTFundCostInfoVO vo = new BdTFundCostInfoVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getQuarter())){
					String codeName = codeUtils.getCodeNameByValue("2233", model.getQuarter());
					vo.setQuarterName(codeName);
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

	@ApiOperation(value="?????????????????????????????????", notes="?????????????????????????????????")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "????????????"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
   })
	@GetMapping(value = "/costInfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String costInfo(@PathVariable(value = "id") String id){
		try {
			BdTFundCostInfoModel model = bdTFundCostInfoService.selectById(id);
			BdTFundCostInfoVO vo = new BdTFundCostInfoVO();
			BeanUtils.copyProperties(vo, model);
			if(StringUtils.isNotEmpty(model.getQuarter())){
				String codeName = codeUtils.getCodeNameByValue("2233", model.getQuarter());
				vo.setQuarterName(codeName);
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


	@ApiOperation(value="?????????????????????", notes="??????????????????????????????")
	@ApiImplicitParam(name = "FundShareItemDTO", value = "??????????????????????????????FundShareItemDTO", required = true, dataType = "FundShareItemDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@PostMapping(value = "/saveCostInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveCostInfo(@RequestBody BdTFundCostInfoDTO dto){

		try {
			if(StringUtils.isEmpty(dto.getYear())){
				baseResponse.setMsg("???????????????");
				return JSONObject.toJSONString(baseResponse);
			}
				BdTFundCostInfoModel model = new BdTFundCostInfoModel();
				BeanUtils.copyProperties(model, dto);
				model.setUpdateBy(this.getLoginUserId());
				model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getId())){
				//??????
				BdTFundCostInfoModel bd=bdTFundCostInfoService.selectById(dto.getId());
				if(bd!=null&&bd.getYear().equals(dto.getYear())){
				    //?????????????????????????????????
					if(StringUtils.isNotEmpty(bd.getQuarter())&&bd.getQuarter().equals(dto.getQuarter())){
                        bdTFundCostInfoService.update("updatePkId",model);
                    }else if(StringUtils.isNotEmpty(bd.getQuarter())&&StringUtils.isNotEmpty(dto.getQuarter())&& !bd.getQuarter().equals(dto.getQuarter())){
                            //????????????????????????????????????
                            BdTFundCostInfoModel modelS = new BdTFundCostInfoModel();
                            modelS.setFundId(dto.getFundId());
                            modelS.setYear(dto.getYear());
                            modelS.setQuarter(dto.getQuarter());
                            modelS.setStatus("0");
                            List<BdTFundCostInfoModel> list = bdTFundCostInfoService.selectList(modelS);
                            if (list != null && !list.isEmpty()) {
                                if(StringUtils.isNotEmpty(dto.getQuarter())){
                                    baseResponse.setMsg("??????????????????");
                                    return JSONObject.toJSONString(baseResponse);
                                }else{
                                    baseResponse.setMsg("??????????????????");
                                    return JSONObject.toJSONString(baseResponse);
                                }
                            }
                            bdTFundCostInfoService.update("updatePkId",model);

                    }
					//???????????????????????????????????????
					if((StringUtils.isNotEmpty(bd.getQuarter())&&StringUtils.isEmpty(dto.getQuarter()))){
                      //?????????????????????????????????????????????
						BdTFundCostInfoModel modelSt = new BdTFundCostInfoModel();
						modelSt.setFundId(dto.getFundId());
						modelSt.setYear(dto.getYear());
						List<BdTFundCostInfoModel> list1 =bdTFundCostInfoService.selectList("getYearList",modelSt);
					    if(list1!=null&&list1.size()>1){
							baseResponse.setMsg(dto.getYear()+"?????????");
							return JSONObject.toJSONString(baseResponse);
						}
						bdTFundCostInfoService.update("updatePkId",model);
					}
					//???????????????????????????????????????
					if(StringUtils.isEmpty(bd.getQuarter())&&StringUtils.isEmpty(dto.getQuarter())){
                        bdTFundCostInfoService.update("updatePkId",model);
                    }
					//?????????????????? ???????????????
					if(StringUtils.isEmpty(bd.getQuarter())&&StringUtils.isNotEmpty(dto.getQuarter())){
                        //????????????????????????????????????
                        BdTFundCostInfoModel modelS = new BdTFundCostInfoModel();
                        modelS.setFundId(dto.getFundId());
                        modelS.setYear(dto.getYear());
                        modelS.setQuarter(dto.getQuarter());
                        modelS.setStatus("0");
                        List<BdTFundCostInfoModel> list = bdTFundCostInfoService.selectList(modelS);
                        if (list != null && !list.isEmpty()) {
                            if(StringUtils.isNotEmpty(dto.getQuarter())){
                                baseResponse.setMsg("??????????????????");
                                return JSONObject.toJSONString(baseResponse);
                            }else{
                                baseResponse.setMsg("??????????????????");
                                return JSONObject.toJSONString(baseResponse);
                            }
                        }
                        bdTFundCostInfoService.update("updatePkId",model);
                    }

				}else{
					//????????????????????????????????????
					BdTFundCostInfoModel modelS = new BdTFundCostInfoModel();
					modelS.setFundId(dto.getFundId());
					modelS.setYear(dto.getYear());
					modelS.setQuarter(dto.getQuarter());
					modelS.setStatus("0");
					List<BdTFundCostInfoModel> list = bdTFundCostInfoService.selectList(modelS);
					if (list != null && !list.isEmpty()) {
						if(StringUtils.isNotEmpty(dto.getQuarter())){
							baseResponse.setMsg("??????????????????");
							return JSONObject.toJSONString(baseResponse);
						}else{
							baseResponse.setMsg("??????????????????");
							return JSONObject.toJSONString(baseResponse);
						}
					}
					bdTFundCostInfoService.update("updatePkId",model);
				}

			}else {
				//????????????????????????????????????
				BdTFundCostInfoModel modelS = new BdTFundCostInfoModel();
				modelS.setFundId(dto.getFundId());
				modelS.setYear(dto.getYear());
				modelS.setQuarter(dto.getQuarter());
				modelS.setStatus("0");
				List<BdTFundCostInfoModel> list = bdTFundCostInfoService.selectList(modelS);
				if (list != null && !list.isEmpty()) {
					if(StringUtils.isNotEmpty(dto.getQuarter())){
						baseResponse.setMsg("??????????????????");
						return JSONObject.toJSONString(baseResponse);
					}else{
						baseResponse.setMsg("??????????????????");
						return JSONObject.toJSONString(baseResponse);
					}

				}
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				model.setStatus("0");
				model.setId(UUID.randomUUID().toString());
				bdTFundCostInfoService.insert(model);
			}

		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}

	@ApiOperation(value="?????????????????????", notes="??????????????????????????????")
	@ApiImplicitParam(name = "FundShareItemDTO", value = "??????????????????????????????FundShareItemDTO", required = true, dataType = "FundShareItemDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "????????????"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "?????????????????????"),
	})
	@GetMapping(value = "/delCostInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delCostInfo(@RequestParam("ids")String ids){
		try {
			if(StringUtils.isNotEmpty(ids)){
				String[] var1=ids.split(",");
				bdTFundCostInfoService.update("updateBatch",var1);
			}


		} catch (SystemException e) {
			logger.error(e.getMessage());
			baseResponse.error(e.getMessage());
		} catch (Exception e) {
			baseResponse.error();
			logger.error(e.getMessage(), e);
		}
		return JSONObject.toJSONString(baseResponse);
	}

}

