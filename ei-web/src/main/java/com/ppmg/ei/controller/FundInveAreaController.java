package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.dto.FundInveAreaDTO;
import com.ppmg.ei.model.FundInveAreaModel;
import com.ppmg.ei.service.FundInveAreaService;
import com.ppmg.ei.vo.FundInveAreaVO;
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
@Api(tags={"投资策略-地域接口"})
public class FundInveAreaController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private FundInveAreaService fundInveAreaService;

	@Reference(check = false)
	private SerialnoService serialnoService;


	@ApiIgnore
	@ApiOperation(value = "基金阶段", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjContractFileDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/fundArea/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody FundInveAreaDTO dto) {
		try {
			String userId = this.getLoginUserId();
			FundInveAreaModel model = new FundInveAreaModel();
			BeanUtils.copyProperties(dto,model);
			model.setUpdateBy(userId);
			model.setUpdateDt(new Date());
			if(StringUtils.isNotEmpty(dto.getPkId())){
				fundInveAreaService.update(model);
			}else{
				String pkId = serialnoService.getSequence("EI.BD_T_FUND_INVE_AREA");
				model.setPkId(pkId);
				model.setCreateBy(userId);
				model.setCreateDt(new Date());
				fundInveAreaService.insert(model);
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
	@GetMapping(value = "/fundArea/detainfo/{fundId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "fundId") String fundId){
		try {
			FundInveAreaModel modelS =new FundInveAreaModel();
			modelS.setFundId(fundId);
			FundInveAreaModel model =null;
			List<FundInveAreaModel> list=fundInveAreaService.selectList(modelS);
			if(list!=null&& !list.isEmpty()){
				model=list.get(0);
			}
			FundInveAreaVO vo=new FundInveAreaVO();
			BeanUtils.copyProperties(model,vo);
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

