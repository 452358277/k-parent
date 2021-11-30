package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.FundInvePhaseDTO;
import com.ppmg.ei.model.FundInvePhaseModel;
import com.ppmg.ei.service.FundInvePhaseService;
import com.ppmg.ei.vo.FundInvePhaseVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"投资策略-基金阶段接口"})
public class FundInvePhaseController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundInvePhaseService fundInvePhaseService;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@ApiIgnore
	@ApiOperation(value = "基金阶段", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundInvePhase/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundInvePhaseDTO dto) {
		try {
			String userId = this.getLoginUserId();
			FundInvePhaseModel model = new FundInvePhaseModel();
			BeanUtils.copyProperties(dto,model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				fundInvePhaseService.update(model);
			}else{
				String pkId = serialnoService.getSequence("EI.BD_T_FUND_INVE_PHASE");
				model.setPkId(pkId);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				fundInvePhaseService.insert(model);
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
	@GetMapping(value = "/fundInvePhase/detainfo/{fundId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "fundId") String fundId){
		try {
			FundInvePhaseModel modelS =new FundInvePhaseModel();
			modelS.setFundId(fundId);
			FundInvePhaseModel model =null;
			List<FundInvePhaseModel> list=fundInvePhaseService.selectList(modelS);
			if(list!=null&& !list.isEmpty()){
				model=list.get(0);
			}
			FundInvePhaseVO vo=new FundInvePhaseVO();
			BeanUtils.copyProperties( model,vo);
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

