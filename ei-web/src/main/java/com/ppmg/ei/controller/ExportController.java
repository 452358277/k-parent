package com.ppmg.ei.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.web.common.CommonStatus;
import com.ppmg.common.controller.BaseController;
import com.ppmg.ei.model.ExportZipListModel;
import com.ppmg.ei.service.PerfManageFundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 描述 [Controller]
 * @author null
 * @date 2019-08-12 15:01
 */
@Controller
@Scope("prototype")
@Api(tags={"文件导出接口"})
public class ExportController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${fileUpload.fileAddr}")
	private String fileAddr;

	@Value("${fileUpload.filePath}")
	private String filePath;

	@Value("${fileUpload.filePathRelative}")
	private String filePathRelative;

	@Reference
	private PerfManageFundService manageFundService;

	@ApiOperation(value = "zip文件导出", notes = "zip文件导出")
	@ApiResponses({
			@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
			@ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
	})
	@GetMapping(value = "/export/zipfile")
	@ResponseBody
	public void export(@RequestParam("pPerId") String pPerId,
					   @RequestParam("fundId") String fundId,
					   HttpServletResponse response) {
		ZipOutputStream zipOut = null;
		try {
			response.reset();
			List<ExportZipListModel> exportZipListModels = manageFundService.exportZip(pPerId, fundId);
			if (CollectionUtils.isNotEmpty(exportZipListModels)){
				String fileName = exportZipListModels.get(0).getFundName()+".zip";
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/x-zip-compressed");
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));

				zipOut = new ZipOutputStream(response.getOutputStream());
				for (ExportZipListModel download : exportZipListModels) {
					if (StringUtils.isNotBlank(download.getFilePath())){
						File sourceFile = new File(filePath+File.separator+download.getFilePath()+File.separator+download.getFileName());
						writeEnrty(zipOut, download.getFileName(), sourceFile);
					}
				}
				zipOut.flush();
			}
		} catch (SystemException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally {
			try {
				//关闭输出流
				if (null != zipOut) {
					zipOut.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void writeEnrty(ZipOutputStream zipOut, String outFile, File sourceFile) throws IOException {
		ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(outFile);
		zipOut.putNextEntry(zipArchiveEntry);
		byte[] bytes = FileUtils.readFileToByteArray(sourceFile);
		zipOut.write(bytes);
		zipOut.closeEntry();
	}

}

