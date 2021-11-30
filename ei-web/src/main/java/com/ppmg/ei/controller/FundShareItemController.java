package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.core.util.StringUtils;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.github.pagehelper.PageInfo;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.FundShareItemDTO;
import com.ppmg.ei.model.FundInfoModel;
import com.ppmg.ei.model.FundShareInfoModel;
import com.ppmg.ei.model.FundShareItemModel;
import com.ppmg.ei.service.FundInfoService;
import com.ppmg.ei.service.FundShareInfoService;
import com.ppmg.ei.service.FundShareItemService;
import com.ppmg.ei.vo.FundShareItemVO;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@Api(tags={"基金幕资明细信息接口"})
public class FundShareItemController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundShareItemService fundShareItemService;

    @Reference(check = false)
    private SerialnoService serialnoService;

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    @Reference
    private FundInfoService fundInfoService;

    @Reference
    private FundShareInfoService fundShareInfoService;

    @ApiOperation(value="基金幕资明细列表", notes="基金幕资明细列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "length", value = "每页大小", required = true, dataType = "int"),
		@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int"),
        @ApiImplicitParam(name = "fundId", value = "基金ID", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
	@GetMapping(value = "/fundShareItemList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String fundShareItemList(@RequestParam("length") int length, @RequestParam("currPage") int currPage, @RequestParam("fundId") String fundId){
		try {

			searchCondition.setPageSize(length);
			searchCondition.setCurrPage(currPage);
            searchCondition.addConditionEqualTo("T.STATUS", "0");
            if (StringUtils.isNotEmpty(fundId)) {
                searchCondition.addParam("FUND_ID", fundId);
                PageInfo<FundShareItemModel> rows = fundShareItemService.selectListPage(searchCondition);
                List<FundShareItemVO> list = new ArrayList<FundShareItemVO>();
                for(FundShareItemModel model : rows.getList()){
                    FundShareItemVO vo = new FundShareItemVO();
                    if(StringUtils.isNotEmpty(String.valueOf(model.getCloseRound()))){
                        String codeName = codeUtils.getCodeNameByValue("1004", String.valueOf(model.getCloseRound()));
                        vo.setCloseRoundName(StringUtils.isEmpty(codeName) ? "" : codeName);
                    }
                    BeanUtils.copyProperties(vo, model);
                    FundShareItemModel paraModel = new FundShareItemModel();
                    paraModel.setFundId(model.getFundId());
                    paraModel.setCloseRound(model.getCloseRound());
                    paraModel.setStatus("0");
                    List<FundShareItemModel> fundShareItemList = fundShareItemService.selectList(paraModel);
                    if(fundShareItemList != null && fundShareItemList.size() >0){
                   /*     for(FundShareItemModel fundShar:fundShareItemList){
                         //更新累计缴款额totalFinancial
                          //查询当前的期数的认缴额
                            FundShareItemModel para = new FundShareItemModel();
                            para.setFundId(model.getFundId());
                            para.setItemId(fundShar.getItemId());
                            para.setInvestorName(fundShar.getInvestorName());
                            Double sum=fundShareItemService.selectByCloseRound(para);
                            if(sum!=null){
                                fundShar.setTotalFinancial(sum);
                            }else{
                                fundShar.setTotalFinancial(0.0);
                            }

                        }*/
                        vo.setFundShareItemList(fundShareItemList);
                    }
                    list.add(vo);
                }
                dataTablesResponse.setData(list, rows);
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

    @ApiOperation(value="保存基金幕资明细信息", notes="保存基金幕资明细信息")
    @ApiImplicitParam(name = "FundShareItemDTO", value = "保存基金幕资信息实体FundShareItemDTO", required = true, dataType = "FundShareItemDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/saveFundShareItem", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveFundShareItem(@RequestBody List<FundShareItemDTO> dtoList){

        try {
            for(FundShareItemDTO dto : dtoList){
                FundShareItemModel model = new FundShareItemModel();
                BeanUtils.copyProperties(model, dto);
                String userId = this.getLoginUserId();
                // String userId = "1005000";
                if(StringUtils.isNotEmpty(dto.getItemId())){
                    model.setUpdateBy(userId);
                    model.setUpdateDt(new Date());
                    //修改实缴额
                    //查询之前的实缴额
                    FundShareItemModel fundShareItem=fundShareItemService.selectById(dto.getItemId());
                    //
                /*   if(fundShareItem!=null){
                       if(dto.getPaymentAmount()!=null){
                           if(fundShareItem.getTotalFinancial()!=null){
                               if(fundShareItem.getPaymentAmount()!=null){
                                   model.setTotalFinancial(fundShareItem.getTotalFinancial()-fundShareItem.getPaymentAmount()+dto.getPaymentAmount());
                               }else{
                                   model.setTotalFinancial(fundShareItem.getTotalFinancial()+dto.getPaymentAmount());
                               }

                           }else{
                               model.setTotalFinancial(dto.getPaymentAmount());
                           }

                       }else {
                           if(fundShareItem.getTotalFinancial()!=null){
                               if(fundShareItem.getPaymentAmount()!=null){
                                   model.setTotalFinancial(fundShareItem.getTotalFinancial()-fundShareItem.getPaymentAmount());
                               }

                           }

                       }
                   }*/
                    fundShareItemService.update(model);
                    FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
                    if(StringUtils.isNotEmpty(dto.getPkId())){
                        fundShareInfoModel.setPkId(dto.getPkId());
                        fundShareInfoModel.setRaiseAmount(model.getTotalFinancial());
                        fundShareInfoService.update(fundShareInfoModel);
                    }
                }else{
                    model.setIsDelete("0");
                    String itemId = serialnoService.getSequence("EI.RZ_T_FUND_SHARE_ITEM");
                    model.setItemId(itemId);
                    model.setStatus("0");
                    model.setCreateBy(userId);
                    model.setCreateDt(new Date());
                    model.setUpdateBy(userId);
                    model.setUpdateDt(new Date());
                    //查询当前期数
                    //查询当前的期数的认缴额
                    FundShareItemModel para = new FundShareItemModel();
                    para.setPkId(dto.getPkId());
                    Double sum=fundShareItemService.selectByFundCloseRound(para);

                    if(sum!=null){
                        if(dto.getPaymentAmount()!=null){
                            model.setTotalFinancial(sum+dto.getPaymentAmount());
                        }else{
                            model.setTotalFinancial(sum);
                        }
                    }else{
                        model.setTotalFinancial(dto.getPaymentAmount());
                    }
                    fundShareItemService.insert(model);
                    FundShareInfoModel fundShareInfoModel=new FundShareInfoModel();
                    if(StringUtils.isNotEmpty(dto.getPkId())){
                        fundShareInfoModel.setPkId(dto.getPkId());
                        fundShareInfoModel.setRaiseAmount(model.getTotalFinancial());
                        fundShareInfoService.update(fundShareInfoModel);
                    }
                }
            }
            if(dtoList!=null&&!dtoList.isEmpty()){
                String fundId= dtoList.get(0).getFundId();
                Double sumPay=fundShareItemService.selectBySumPay(fundId);
                FundInfoModel f=new FundInfoModel();
                f.setFundId(fundId);
                f.setRaiseAmount(sumPay);
                fundInfoService.update(f);

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


    @ApiOperation(value="保存基金幕资明细信息", notes="保存基金幕资明细信息")
    @ApiImplicitParam(name = "FundShareItemDTO", value = "保存基金幕资信息实体FundShareItemDTO", required = true, dataType = "FundShareItemDTO")
    @ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @RequestMapping(value = "/delateFundShareItem", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String delateFundShareItem(@RequestBody List<FundShareItemModel> dtoList){

        try {
            fundShareItemService.updateByIds(dtoList,this.getLoginUserId());

        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
        } catch (Exception e) {
            baseResponse.error();
            logger.error(e.getMessage(), e);
        }
        return JSONObject.toJSONString(baseResponse);
    }
    /*
	@ApiOperation(value="获取基金幕资明细详细信息", notes="根据url的id来获取基金幕资明细详细信息")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/fundShareItemInfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "id") String id){
		try {
			FundShareItemModel model = fundShareItemService.selectById(id);
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
*/

}

