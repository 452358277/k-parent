package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.foundation.service.SerialnoService;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.CodeUtils;
import com.ppmg.ei.dto.ProjShareInfoDTO;
import com.ppmg.ei.model.ProjShareInfoModel;
import com.ppmg.ei.model.TzTProjOwnershipInfoModel;
import com.ppmg.ei.service.ProjShareInfoService;
import com.ppmg.ei.service.TzTProjOwnershipInfoService;
import com.ppmg.ei.vo.ProjShareInfoVO;
import com.ppmg.ei.vo.TzTProjOwnershipInfoVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@Scope("prototype")
@Api(tags={"一期股权结构接口"})
@RequestMapping("/projShar")
public class ProjShareInfoController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjShareInfoService projShareInfoService;

	@Reference
	private TzTProjOwnershipInfoService tzTProjOwnershipInfoService;

	@Resource(name = "codeUtils")
	private CodeUtils codeUtils;

	@Reference(check = false)
	private SerialnoService serialnoService;

	@ApiOperation(value="股权结构明细", notes="详情")
	@ApiImplicitParam(name = "osId", value = "osId", required = true, dataType = "String", paramType = "path")
	@ApiResponses({ @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/projShareInfo/detainfo/{osId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String detainfo(@PathVariable(value = "osId") String osId){
		try {
			TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel=tzTProjOwnershipInfoService.selectById(osId);
			TzTProjOwnershipInfoVO voS = new TzTProjOwnershipInfoVO();
			BeanUtils.copyProperties(tzTProjOwnershipInfoModel, voS);

			List<ProjShareInfoVO> listVo=new ArrayList<>();
			ProjShareInfoModel modelS =new ProjShareInfoModel();
			modelS.setOsId(osId);
			modelS.setStatus("0");
			List<ProjShareInfoModel> list=projShareInfoService.selectListShare(modelS);
			for(ProjShareInfoModel projShareInfoModel:list){
				ProjShareInfoVO vo=new ProjShareInfoVO();
				BeanUtils.copyProperties(projShareInfoModel,vo);
				if (StringUtils.isNotEmpty(projShareInfoModel.getInvestorPro())) {
					String investmentAttributesName = codeUtils.getCodeNameByValue("9015", projShareInfoModel.getInvestorPro());
					vo.setInvestorProName(investmentAttributesName);
				}
				listVo.add(vo);
			}
			voS.setListVo(listVo);
			dataResponse.setData(voS);
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
	@ApiOperation(value = "股权结构新增", notes = "基金阶段")
	@ApiImplicitParam(name = "ProjShareInfoDTO", value = "ProjContractFileDTO", required = true, dataType = "ProjContractFileDTO")
	@ApiResponses({@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@PostMapping(value = "/projShareInfo/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody ProjShareInfoDTO dto) {
		try {
			String userId = this.getLoginUserId();
			if(StringUtils.isNotEmpty(dto.getOsId())){
				TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel= new TzTProjOwnershipInfoModel();
				tzTProjOwnershipInfoModel.setOsId(dto.getOsId());
				tzTProjOwnershipInfoModel.setRecordDate(dto.getRecordDate());
				tzTProjOwnershipInfoModel.setUpdateBy(userId);
				tzTProjOwnershipInfoModel.setUpdateDt(new Date());

				//T投项目
				if("1".equals(dto.getType())){
                    tzTProjOwnershipInfoModel.setEnteId(dto.getEnteId());
                }else{
                    tzTProjOwnershipInfoModel.setFundId(dto.getFundId());
                    tzTProjOwnershipInfoModel.setFundName(dto.getFundName());
                }

				projShareInfoService.updateInfo(dto.getListVo(),tzTProjOwnershipInfoModel);
			}else{
				String osId = serialnoService.getSequence("EI.TZ_T_PROJ_OWNERSHIP_INFO");
				TzTProjOwnershipInfoModel tzTProjOwnershipInfoModel= new TzTProjOwnershipInfoModel();
				tzTProjOwnershipInfoModel.setRecordDate(dto.getRecordDate());
				tzTProjOwnershipInfoModel.setUpdateBy(userId);
				tzTProjOwnershipInfoModel.setUpdateDt(new Date());
				tzTProjOwnershipInfoModel.setCreateBy(userId);
				tzTProjOwnershipInfoModel.setCreateDt(new Date());
				tzTProjOwnershipInfoModel.setOsId(osId);
				tzTProjOwnershipInfoModel.setStatus("0");
				tzTProjOwnershipInfoModel.setProjId(dto.getProjId());
                if("1".equals(dto.getType())){
                    tzTProjOwnershipInfoModel.setEnteId(dto.getEnteId());
					tzTProjOwnershipInfoModel.setEnteName(dto.getEnteName());
                }else{
                    tzTProjOwnershipInfoModel.setFundId(dto.getFundId());
                    tzTProjOwnershipInfoModel.setFundName(dto.getFundName());
                }

				projShareInfoService.saveInfo(dto.getListVo(),tzTProjOwnershipInfoModel);
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			baseResponse.error();
		}
		return JSONObject.toJSONString(baseResponse);
	}


}

