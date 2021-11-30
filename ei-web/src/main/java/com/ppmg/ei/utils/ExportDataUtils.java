package com.ppmg.ei.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

/**
 * @author 25956
 * @date 2018/12/26
 */

public class ExportDataUtils {

    public static Workbook exportData(List<String[]> data, String[] title, String fileName) {
        return baseExportData(data,title,fileName,null,null);
    }

    public static Workbook baseExportData(List<String[]> data, String[] title, String fileName,
            String workBookName, String SheetName) {

        {
            Workbook workbook = null;
            try {
                //文件检查
                checkFile(fileName);
                //获取工作簿对象 workbook
                workbook = getWorkbook(workBookName != null && workBookName != "" ? workBookName:fileName);
                //创建sheet对象
                Sheet sheet = workbook.createSheet(SheetName != null && SheetName != "" ? SheetName:fileName);
                sheet.setDefaultColumnWidth(15);
                //设置cell元素居中
                CellStyle cellStyle = workbook.createCellStyle();
                //cellStyle.setAlignment(HorizontalAlignment.CENTER);
                //科学计数法
                CellStyle cellStyle1 = workbook.createCellStyle();
                DataFormat dataFormat = workbook.createDataFormat();
                cellStyle1.setDataFormat(dataFormat.getFormat("@"));
                //添加表头第0行
                Row row = sheet.createRow(0);
                for (int titleNum = 0; titleNum < title.length; titleNum++) {
                    Cell cell = row.createCell(titleNum);
                    cell.setCellValue(title[titleNum]);
                    cell.setCellStyle(cellStyle);
                    cell.setCellStyle(cellStyle1);
                }
                //添加数据内容
                for (int rowNum = 0; rowNum < data.size(); rowNum++) {
                    String[] rowData = data.get(rowNum);
                    row = sheet.createRow(rowNum + 1);
                    for (int cellNum = 0; cellNum < rowData.length; cellNum++) {
                        Cell cell = row.createCell(cellNum);
                        cell.setCellStyle(cellStyle1);
                        cell.setCellValue(rowData[cellNum]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return workbook;
        }
    }

    public static Workbook getWorkbook(String fileName) {
        Workbook workbook = null;
        if (fileName.endsWith(".xls")) {
            //2003
            workbook = new HSSFWorkbook();
        }
        if (fileName.endsWith(".xlsx")) {
            //2007
            workbook = new XSSFWorkbook();
        }
            return workbook;
        }

        public static void checkFile (String fileName) throws Exception {
            if (StringUtils.isBlank(fileName)) {
                throw new NullPointerException("文件名不能为空!");
            }
            if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
                throw new IOException("不是Excel文件");
            }
        }
    }
