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
import com.ppmg.ei.bean.CashFlowSearchBean;
import com.ppmg.ei.dto.LedgerMagFundDTO;
import com.ppmg.ei.dto.LedgerMagInfoDTO;
import com.ppmg.ei.model.*;
import com.ppmg.ei.service.*;
import com.ppmg.ei.vo.LedgerMagVO;
import com.ppmg.ei.vo.LedgerVO;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"控股投资现金流接口"})
@RequestMapping("/ledgerMag")
public class LedgerMagController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private LedgerMagService ledgerMagService;

	@Reference
	private CommTCodeService commTCodeService;

	@Reference
	private AppUserService appUserService;

	@Reference
	private BankProductService bankProductService;

	@Reference
	private BankInfoService bankInfoService;

	@Reference
	private AppRoleService appRoleService;

	@Reference
	private BankRelateService bankRelateService;

	@Reference
	private FundUserRelateService fundUserRelateService;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@Resource(name="codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private FundAccountService fundAccountService;


	@Reference(check = false)
	private ProjInfoService projInfoService;

	@ApiOperation(value="现金流列表", notes="现金流列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/ledgerMagAppList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ledgerMagAppList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("groupId") String groupId, @RequestParam("fundId") String fundId, String type){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addParam("groupId",groupId);
			searchCondition.addConditionEqualTo("COMPANY_NUMBER",fundId);
			if("1".equals(type)){
				searchCondition.addParam("NAME", "1");
				searchCondition.addParam("FUNDID", fundId);
			}
			PageInfo<LedgerMagModel> rows = ledgerMagService.selectListPage(searchCondition);
			List<LedgerMagVO> list = new ArrayList<LedgerMagVO>();
			for(LedgerMagModel model : rows.getList()){
				LedgerMagVO vo = new LedgerMagVO();
				BeanUtils.copyProperties(vo, model);
				String investPaymentType = "";
				if(model.getInvestPaymentType()!=""&&model.getInvestPaymentType()!=null){
					investPaymentType = codeUtils.getCodeNameByValue("61000", model.getInvestPaymentType());//投资支付类型（1投资人，2基金，3项目，4平台）,61000
				}
				String financeType = "";
				String recBillTypeNum = "";
				if(StringUtils.isNotEmpty(model.getFinanceType())){
					vo.setFinanceTypeName(codeUtils.getCodeNameByValue("1003", model.getFinanceType()));
					if("1".equals(model.getFinanceType())){
						if(StringUtils.isNotEmpty(model.getRecBillTypeNum())){
							vo.setRecBillTypeName(codeUtils.getCodeNameByValue("10031", model.getRecBillTypeNum()));
						}
					}else if("2".equals(model.getFinanceType())){
						if(StringUtils.isNotEmpty(model.getRecBillTypeNum())){
							vo.setRecBillTypeName(codeUtils.getCodeNameByValue("10032", model.getRecBillTypeNum()));
						}
					}
				}
				vo.setInvestPaymentType(investPaymentType);
				vo.setFinanceType(financeType);
				vo.setRecBillTypeNum(recBillTypeNum);
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

    @ApiOperation(value="查询现金流记录列表", notes="查询现金流记录列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/ledgerList", produces = "application/json;charset=UTF-8" )
    @ResponseBody
	public String ledgerList(@RequestBody CashFlowSearchBean searchBean){
		try {
			searchCondition.addParam("groupId",searchBean.getGroupId());
			if(StringUtils.isNotEmpty(searchBean.getStartTime())){
				searchBean.setStartTime(searchBean.getStartTime()+"00:00:00");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndTime())){
				searchBean.setEndTime(searchBean.getEndTime()+"23:59:59");
			}
			if(StringUtils.isNotEmpty(searchBean.getFinanceType())){
				List<String> financeType = Arrays.asList(searchBean.getFinanceType().split(","));
				searchCondition.addConditionIn("FINANCE_TYPE", financeType);
			}
            if(StringUtils.isNotEmpty(searchBean.getIds())){
                List<String> fundIds = Arrays.asList(searchBean.getIds().split(","));
                searchBean.setFundIds(fundIds);
            }
            //searchCondition.addParam("USER_ID", "1005002");
			//searchCondition.addParam("USER_ID", this.getLoginUserId());
			//查询用户的角色
			List<AppRoleModel> listRow=appRoleService.selectByUserId(this.getLoginUserId());
			if(listRow!=null&&listRow.size()>0){
				for(AppRoleModel appRoleModel:listRow){
					if(appRoleModel.getId()==1005003){
						searchCondition.addParam("USER_ID", this.getLoginUserId());
						searchCondition.addParam("NAME", "0");
					}else if(appRoleModel.getId()==1005002){
						searchCondition.addParam("USER_ID", this.getLoginUserId());
						searchCondition.addParam("NAME", "2");
					}else{
					    //除了管理人或者银行角色其他的用户角色//type=1 代表基金详情里面的现金流
					    if("1".equals(searchBean.getType())){
                            searchCondition.addParam("NAME", "1");
                            searchCondition.addParam("FUNDID", searchBean.getFundId());
                        }else{
							searchCondition.addParam("NAME", "1");
						}
					}
				}
			}
			searchCondition.setSearchBean(searchBean);
			PageInfo<LedgerMagModel> rows = ledgerMagService.selectcashFlowPage(searchCondition);
			List<LedgerMagVO> list = new ArrayList<LedgerMagVO>();
			for(LedgerMagModel model : rows.getList()){
				LedgerMagVO vo = new LedgerMagVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getFinanceType())){
					vo.setFinanceTypeName(codeUtils.getCodeNameByValue("1003", model.getFinanceType()));
					if("1".equals(model.getFinanceType())){
						if(StringUtils.isNotEmpty(model.getRecBillTypeNum())){
							vo.setRecBillTypeName(codeUtils.getCodeNameByValue("10031", model.getRecBillTypeNum()));
						}
					}else if("2".equals(model.getFinanceType())){
						if(StringUtils.isNotEmpty(model.getRecBillTypeNum())){
							vo.setRecBillTypeName(codeUtils.getCodeNameByValue("10032", model.getRecBillTypeNum()));
						}
					}
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

	@ApiIgnore
	@ApiOperation(value="新增现金流记录", notes="根据LedgerMagInfoDTO对象创建金流记录")
	@ApiImplicitParam(name = "LedgerMagInfoDTO", value = "LedgerMagInfoDTO", required = true, dataType = "LedgerMagInfoDTO")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(@RequestBody LedgerMagInfoDTO dto){
		try {
			String userId=this.getLoginUserId();
			LedgerMagModel model = new LedgerMagModel();
			BeanUtils.copyProperties(model,dto);

			FundUserRelateModel fundUserRelateModel=new FundUserRelateModel();
			fundUserRelateModel.setFundId(dto.getCompanyNumber());
			fundUserRelateModel.setUserId(userId);
			//fundUserRelateModel.setUserId(this.getLoginUserId());
			FundUserRelateModel fundUserRelate=fundUserRelateService.selectBy(fundUserRelateModel);
			if(StringUtils.isNotEmpty(dto.getCompanyNumber())){
				if("0".equals(dto.getCompanyNumber())){
					if(fundUserRelate!=null&&StringUtils.isNotEmpty(fundUserRelate.getAccounts())){
						model.setAccounts(fundUserRelate.getAccounts());
					}
					//查询银行的账户
					/*BankInfoModel bankInfoModel=new BankInfoModel();
					bankInfoModel.setUserId(this.getLoginUserId());
					BankInfoModel bankDetail=bankInfoService.selectBy(bankInfoModel);
					if(bankDetail!=null){
						BankRelateModel bankRelateModel=new BankRelateModel();
						bankRelateModel.setFundId(dto.getCompanyNumber());
						bankRelateModel.setBankId(bankDetail.getBankId());
						BankRelateModel bankRelateDetail=bankRelateService.selectBy(bankRelateModel);
					  if(bankRelateDetail!=null){
						  model.setAccounts(bankRelateDetail.getAccounts());
					  }
					}*/
				}

			}
			BankProductModel bankProduct=new BankProductModel();
			BeanUtils.copyProperties(bankProduct,dto);
			bankProduct.setFundId(dto.getCompanyNumber());
			LedgerMagModel ledgerMagModel=new LedgerMagModel();

			if(fundUserRelate!=null&&StringUtils.isNotEmpty(fundUserRelate.getAccounts())){
				ledgerMagModel.setAccounts(fundUserRelate.getAccounts());
				bankProduct.setAccounts(fundUserRelate.getAccounts());
			}

			ledgerMagModel.setCompanyNumber(dto.getCompanyNumber());
			ledgerMagModel.setCreateBy(userId);
			ledgerMagModel.setStatus("0");
			//ledgerMagModel.setCreateBy(this.getLoginUserId());
			ledgerMagModel.setStatus("0");
			List<LedgerMagModel> list=ledgerMagService.selectList(ledgerMagModel);
			if(StringUtils.isNotEmpty(model.getCurrencyNum())){
				model.setCurrencyName(codeUtils.getCodeNameByValue("103", model.getCurrencyNum()));
			}
			//判断是收入还是支出
            if("1".equals(model.getFinanceType())){
              //出资
				if(model.getAccountBalance()==null||"".equals(model.getAccountBalance())||"0".equals(model.getAccountBalance())){
					baseResponse.setMsg("余额不足");
					return	JSONObject.toJSONString(baseResponse);
				}
                	//查询最新的余额
					if(list!=null&&list.size()>0){
						LedgerMagModel model1=list.get(0);
						if(dto.getAmt()>model1.getAccountBalance()){
							baseResponse.setMsg("余额不足");
							return	JSONObject.toJSONString(baseResponse);
						}else{
							model.setAccountBalance(model1.getAccountBalance()-dto.getAmt());
						}
					}else{
						if(model.getAccountBalance()<model.getAmt()){
							baseResponse.setMsg("余额不足");
							return	JSONObject.toJSONString(baseResponse);
						}
					}

                   String result=ledgerMagService.insertLedgerMag(model,userId,bankProduct);
					if("-1".equals(result)){
						baseResponse.setMsg("对不起，该理财产品编号已存在");
						return	JSONObject.toJSONString(baseResponse);
					}

            }
            if("2".equals(model.getFinanceType())){
            	//赎回的总金额不能大于投资的金额
                //入资
				if(dto.getAccountBalance()==null){
					model.setAccountBalance(Double.valueOf(0));
				}
                model.setAccountBalance(model.getAccountBalance()+model.getAmt());
                ledgerMagService.insertLedgerMag(model,userId,bankProduct);
            }


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}


	@ApiIgnore
	@ApiOperation(value="更新现金流记录", notes="根据url的id来指定现金流记录信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "现金流记录主键ledgerId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "LedgerMagInfoDTO", value = "LedgerMagInfoDTO", required = true, dataType = "LedgerMagInfoDTO")
	})
	@PostMapping(value = "/updateLedge/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateLedge(@PathVariable("id") String id, @RequestBody LedgerMagInfoDTO dto){

		try {
			//查询这条记录原来是收入还是支出的，余额
			LedgerMagModel leg=ledgerMagService.selectById(id);
			if(leg!=null){
				LedgerMagModel ledgerMagModel=new LedgerMagModel();
				BeanUtils.copyProperties(ledgerMagModel,dto);
				BankProductModel bankProduct=new BankProductModel();
				BeanUtils.copyProperties(bankProduct,dto);
				ledgerMagModel.setLedgerId(id);
				bankProduct.setProductId(leg.getProductId());
				String userId=this.getLoginUserId();
				//String userId="20199";
				ledgerMagService.updateByName(ledgerMagModel,userId,bankProduct,leg.getProductId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}

	@ApiIgnore
	@ApiOperation(value="更新现金流记录", notes="根据url的id来指定现金流记录信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "现金流记录主键ledgerId", required = true, dataType = "String",paramType = "path"),
			@ApiImplicitParam(name = "LedgerMagInfoDTO", value = "LedgerMagInfoDTO", required = true, dataType = "LedgerMagInfoDTO")
	})
	@PostMapping(value = "/update/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable("id") String id, @RequestBody LedgerMagInfoDTO dto){

		try {
		    //查询这条记录原来是收入还是支出的，余额
            LedgerMagModel leg=ledgerMagService.selectById(id);
            if(leg!=null){
            	//
				LedgerMagModel ledgerMagModel=new LedgerMagModel();
				ledgerMagModel.setCompanyNumber(leg.getCompanyNumber());
				ledgerMagModel.setCreateBy(this.getLoginUserId());
				List<LedgerMagModel> list=ledgerMagService.selectList(ledgerMagModel);
				double old1=0;
				if(list!=null&&list.size()>0){
					//最新的余额
					 old1= list.get(0).getAccountBalance();
				}

                LedgerMagModel model = new LedgerMagModel();
                BeanUtils.copyProperties(model, dto);
				// double oldAccountBalance=leg.getAccountBalance();
				double oldAccountBalance=old1;
				leg.setAccountBalance(oldAccountBalance);
                String oldFinanceType= leg.getFinanceType();
				if(leg.getAccountBalance()==null){
					leg.setAccountBalance(Double.valueOf(0));
				}
				if(leg.getAmt()==null){
					leg.setAmt(Double.valueOf(0));
				}
                if(oldFinanceType.equals(model.getFinanceType())){
                    if("1".equals(model.getFinanceType())){
                    //出资
                        //原来的余额
                       double old=leg.getAmt()+leg.getAccountBalance();
                       if(old==0){
						   baseResponse.setMsg("余额不足");
						   return JSONObject.toJSONString(baseResponse);
					   }
                        model.setAccountBalance(old-model.getAmt());
                    }else{
                        //入资
                        //原来的余额
                        double old=leg.getAccountBalance()-leg.getAmt();
                        model.setAccountBalance(old+model.getAmt());
                    }

                }else{
                    if("1".equals(model.getFinanceType())){
                        //原来是入资，现在改为 出资
                        // 原来的余额
                        double old=leg.getAccountBalance()-leg.getAmt();
						if(old==0){
							baseResponse.setMsg("余额不足");
							return JSONObject.toJSONString(baseResponse);
						}
						if(old<model.getAmt()){
							baseResponse.setMsg("余额不足");
							return JSONObject.toJSONString(baseResponse);
						}
                        model.setAccountBalance(old-model.getAmt());
                    }else{
                        //原来是出资，现在改为 入资
                        double old=leg.getAmt()+leg.getAccountBalance();
                        model.setAccountBalance(old+model.getAmt());
                    }
                }
                model.setLedgerId(id);
                model.setUpdateDt(new Date());
                model.setUpdateBy(this.getLoginUserId());
                ledgerMagService.update(model);

            }


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}

		return JSONObject.toJSONString(baseResponse);

	}
	@ApiOperation(value="查询现金流详情", notes="查询现金流详情")
	@ApiImplicitParam(name = "id", value = "XXXID", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
   })
	@GetMapping(value = "/detainfo/{id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detail(@PathVariable(value = "id") String id){
		try {

			LedgerMagModel model = ledgerMagService.selectById(id);
			LedgerMagModel ledgerMagModel=new LedgerMagModel();

			/*ledgerMagModel.setCompanyNumber(model.getCompanyNumber());
			ledgerMagModel.setCreateBy("20199");
			List<LedgerMagModel> list=ledgerMagService.selectList(ledgerMagModel);*/
			LedgerMagVO vo = new LedgerMagVO();
		/*	if(list!=null&&list.size()>0){
				LedgerMagModel model1 =list.get(0);
				vo.setAccountBalance(model1.getAccountBalance());
			}*/
			BeanUtils.copyProperties(vo, model);
			if(StringUtils.isNotEmpty(model.getProductId())){
				BankProductModel bank=bankProductService.selectById(model.getProductId());
				if(bank!=null){
					vo.setBankProductModel(bank);
				}

			}
			if(model!=null&&StringUtils.isNotEmpty(model.getProjId())){
				ProjInfoModel projM=projInfoService.selectById(model.getProjId());
				vo.setProjName(projM.getProjName());

			}
			vo.setProjId(model.getProjId());
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

	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ledgerId", value = "ledgerId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/delete/{ledgerId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("ledgerId") String ledgerId){

		try {
            LedgerMagModel leg=ledgerMagService.selectById(ledgerId);
            //根据基金和创建人
            LedgerMagModel model1 =new LedgerMagModel();
            model1.setCreateBy(leg.getCreateBy());
            model1.setCompanyNumber(leg.getCurrencyNum());
            List<LedgerMagModel> list= ledgerMagService.selectList(model1);
            LedgerMagModel model =new LedgerMagModel();
            if(list.size()>1){
                LedgerMagModel ledgerMagModel=list.get(1);
                if(leg!=null){
                    model.setLedgerId(ledgerMagModel.getLedgerId());
                    if("1".equals(leg.getFinanceType())){
                        //出资
                        // leg.getAccountBalance()
                        model.setAccountBalance(ledgerMagModel.getAccountBalance()+leg.getAmt());

                    }else{
                        model.setAccountBalance(ledgerMagModel.getAccountBalance()-leg.getAmt());
                    }
                    ledgerMagService.update(model);
            }
              LedgerMagModel model2 =new LedgerMagModel();
                model2.setLedgerId(ledgerId);
                model2.setStatus("1");
                ledgerMagService.update(model2);
            }else{
				LedgerMagModel model2 =new LedgerMagModel();
				model2.setLedgerId(ledgerId);
				model2.setStatus("1");
				ledgerMagService.update(model2);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


    @ApiIgnore
    @ApiOperation(value="查询余额", notes="查询余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fundId", value = "fundId", required = true, dataType = "String",paramType = "path"),
    })
    @GetMapping(value = "/getLedgerMag/{fundId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getLedgerMag(@PathVariable("fundId") String fundId){
        try {
			LedgerMagModel model =new LedgerMagModel();
			model.setCompanyNumber(fundId);
			FundUserRelateModel fundUserRelateModel=new FundUserRelateModel();
			fundUserRelateModel.setFundId(fundId);
			fundUserRelateModel.setUserId(this.getLoginUserId());
			FundUserRelateModel fundUserRelate=fundUserRelateService.selectBy(fundUserRelateModel);
           if(fundUserRelate!=null&&StringUtils.isNotEmpty(fundUserRelate.getAccounts())){
			   model.setAccounts(fundUserRelate.getAccounts());
		   }
            model.setStatus("0");
            //String userId=this.getLoginUserId();
            //model.setCreateBy(userId);
           List<LedgerMagModel> list= ledgerMagService.selectList(model);
            double accountBalance;
           if(list!=null&&list.size()>0){
               //获取第一个值
              accountBalance= list.get(0).getAccountBalance();
			   if(list.get(0).getBizDate()!=null){
				   SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
				   mapResponse.put("bizTime", sim.format(list.get(0).getBizDate()));

			   }

           }else{
               accountBalance=0;
           }
            mapResponse.put("accountBalance", accountBalance);


        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error();
        }
        return JSONObject.toJSONString(mapResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

    }
    //查询产品编号



	@ApiIgnore
	@ApiOperation(value="根据银行账户查询产品赎回的余额", notes="根据银行账户查询产品赎回的余额")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productId", value = "productId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/productId/{productId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String product(@PathVariable("productId") String productId){
		try {
			//根据登录人查询是哪个银行
			String userId=this.getLoginUserId();
			//String userId="1005003";


			/*BankInfoModel bankInfoModel=bankInfoService.selectByUserId(userId);*/

				//根据bankId 和 productCode 查询产品的id
			/*	BankProductModel bankProduct=new BankProductModel();
				bankProduct.setProductCode(productCode);
				bankProduct.setBankId(bankInfoModel.getBankId());
				BankProductModel bankProductModel=bankProductService.selectBy(bankProduct);*/
			  BankProductModel bankProductModel=bankProductService.selectById(productId);
                if(bankProductModel!=null){
                	//购买的金额
					double buyAmount=bankProductModel.getBuyAmount();
					//赎回金额
					LedgerMagModel model =new LedgerMagModel();
					model.setProductId(bankProductModel.getProductId());
					model.setStatus("0");
					model.setAccounts(bankProductModel.getAccounts());
					//model.setCreateBy(userId);
					model.setFinanceType("2");
					//查询所有的出资
					List<LedgerMagModel> list=ledgerMagService.selectList(model);
					//赎回总金额
					double sumAmount=0;
					if(list!=null&&list.size()>0){
						for(LedgerMagModel ledgerMagModel:list){
							sumAmount=sumAmount+ledgerMagModel.getAmt();
						}
					}
				    double amount=buyAmount-sumAmount;
					if(buyAmount<sumAmount){
						baseResponse.setMsg("赎回余额不足");
						return JSONObject.toJSONString(baseResponse);
					}else{
						DecimalFormat df   = new DecimalFormat("######0.00");
						baseResponse.setMsg(String.valueOf(df.format(amount)));
					}
				}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);

	}





	@ApiOperation(value="查询母基金账户对应现金流记录列表", notes="查询母基金账户对应现金流记录列表")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "bankRelate/accountLedgerList", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String accountLedgerList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage, @RequestParam("groupId") String groupId, @RequestParam("fundId") String fundId,@RequestParam("bankId") String bankId){
		try {
			searchCondition.addParam("groupId",groupId);
			searchCondition.addParam("BANK_ID",bankId);
			searchCondition.addParam("COMPANY_NUMBER",fundId);
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			PageInfo<LedgerMagModel> rows = ledgerMagService.selectLedgerPage(searchCondition);
			List<LedgerMagVO> list = new ArrayList<LedgerMagVO>();
			for(LedgerMagModel model : rows.getList()){
				LedgerMagVO vo = new LedgerMagVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getCustomerType())){
					vo.setCustomerTypeName(codeUtils.getCodeNameByValue("1009", model.getCustomerType()));
				}
				vo.setCreateDt(model.getCreateDt());
				//转换人
				AppUserModel appUserModel=appUserService.selectById(model.getCreateBy());
				if(appUserModel!=null){
					vo.setCreateBy(appUserModel.getName());
				}
				if(StringUtils.isNotEmpty(model.getFinanceType())){
					vo.setFinanceTypeName(codeUtils.getCodeNameByValue("1003", model.getFinanceType()));
					if("1".equals(model.getFinanceType())){
						if(StringUtils.isNotEmpty(model.getRecBillTypeNum())){
							vo.setRecBillTypeName(codeUtils.getCodeNameByValue("10031", model.getRecBillTypeNum()));
						}
					}else if("2".equals(model.getFinanceType())){
						if(StringUtils.isNotEmpty(model.getRecBillTypeNum())){
							vo.setRecBillTypeName(codeUtils.getCodeNameByValue("10032", model.getRecBillTypeNum()));
						}
					}
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

	//#####################################一期台账###################################################

	@ApiOperation(value="一期台账", notes="现金流列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
			@ApiImplicitParam(name = "currPage", value = "当前页码", required = true, dataType = "int")
	})
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/ledgerFundList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ledgerFundList(@RequestParam("pageSize") int pageSize, @RequestParam("currPage") int currPage,  @RequestParam("projId") String projId){
		try {
			searchCondition.setPageSize(pageSize);
			searchCondition.setCurrPage(currPage);
			searchCondition.addConditionEqualTo("PROJ_ID",projId);
			PageInfo<LedgerMagModel> rows = ledgerMagService.selectListFundPage(searchCondition);
			List<LedgerMagVO> list = new ArrayList<LedgerMagVO>();
			for(LedgerMagModel model : rows.getList()){
				LedgerMagVO vo = new LedgerMagVO();
				BeanUtils.copyProperties(vo, model);
				if(StringUtils.isNotEmpty(model.getLedgerType())){
					String ledgerTypeName = codeUtils.getCodeNameByValue("242",model.getLedgerType());
					vo.setLedgerTypeName(ledgerTypeName);
				}
				if(StringUtils.isNotEmpty(model.getOccurCurr())){
					String occurCurrName = codeUtils.getCodeNameByValue("103",model.getOccurCurr());
					vo.setOccurCurrName(occurCurrName);
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

	@ApiIgnore
	@ApiOperation(value = "股权结构新增", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/ledgerMagFund/saveMage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveMage(@RequestBody LedgerMagFundDTO dto) {
		try {
			LedgerMagModel model=new LedgerMagModel();
			BeanUtils.copyProperties(model,dto);
			model.setStatus("0");
			if(StringUtils.isEmpty(dto.getLedgerId())){
				model.setCreateBy(this.getLoginUserId());
				model.setCreateDt(new Date());
				model.setUpdateBy(this.getLoginUserId());
				model.setUpdateDt(new Date());
				String ledgerId = serialnoService.getSequence("EI.XJL_T_LEDGER_MAG");
				model.setLedgerId(ledgerId);
				//如果type=1 是  母基金中-投基金中-台账管理
				if("1".equals(dto.getType())){
					ledgerMagService.insertInfo(model,dto.getIntendedAmount(),dto.getIntendedCurrency());
				}else{
					ledgerMagService.insert(model);
				}

			}else{
				model.setUpdateBy(this.getLoginUserId());
				model.setUpdateDt(new Date());
				if("1".equals(dto.getType())){
					ledgerMagService.updateInfo(model,dto.getIntendedAmount(),dto.getIntendedCurrency());
				}else{
					ledgerMagService.update(model);
				}


			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "appId", value = "appId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/ledgeM/detainfo/{ledgerId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "ledgerId") String ledgerId){
		try {
			LedgerMagModel model=ledgerMagService.selectById(ledgerId);

			LedgerVO vo=new LedgerVO();
			BeanUtils.copyProperties(vo,model);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			if(model.getCreateDt()!=null){
				String registerDt= format.format(model.getCreateDt());
				vo.setRegisterDt(registerDt);
			}
			if(StringUtils.isNotEmpty(model.getCreateBy())){
				if("init".equals(model.getCreateBy())){
					vo.setRegisterUser("初始化");
				}else{
					AppUserModel account=appUserService.selectById(model.getCreateBy());
					if(account!=null){
						vo.setRegisterUser(account.getName());
					}
				}

			}
			if(StringUtils.isNotEmpty(model.getFofInveCurr())){
				String fofInveCurr = codeUtils.getCodeNameByValue("103",model.getFofInveCurr());
				vo.setFofInveCurrName(fofInveCurr);
			}
			//基金类型
			if(StringUtils.isNotEmpty(model.getLedgerType())){
				String ledgerTypeName = codeUtils.getCodeNameByValue("242",model.getLedgerType());
				vo.setLedgerTypeName(ledgerTypeName);
			}

			if(StringUtils.isNotEmpty(model.getOccurCurr())){
				String occurCurrName = codeUtils.getCodeNameByValue("103",model.getOccurCurr());
				vo.setOccurCurrName(occurCurrName);
			}
			if(StringUtils.isNotEmpty(model.getExitType())){
				String exitTypeName = codeUtils.getCodeNameByValue("21717",model.getExitType());
				vo.setExitTypeName(exitTypeName);
			}
			if(StringUtils.isNotEmpty(model.getPayWay())){
				String payWayName = codeUtils.getCodeNameByValue("247",model.getPayWay());
				vo.setPayWayName(payWayName);
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

	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "consId", value = "consId", required = true, dataType = "String",paramType = "path"),
	})
	@GetMapping(value = "/deleteById/{ledgerId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteById(@PathVariable("ledgerId") String ledgerId){
		try {
			String[] ids = ledgerId.split(",");
			for(int i = 0; i < ids.length; i++){
				if(StringUtils.isNotEmpty(ids[i])){
					ledgerMagService.deleteById(ids[i]);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);

	}


}

