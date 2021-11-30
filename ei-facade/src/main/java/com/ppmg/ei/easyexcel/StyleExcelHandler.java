package com.ppmg.ei.easyexcel;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.ss.usermodel.*;

public class StyleExcelHandler implements WriteHandler {

    @Override
    public void sheet(int i, Sheet sheet) {
    }

    @Override
    public void row(int i, Row row) {
    }

    @Override
    public void cell(int i, Cell cell) {
        //设置单元格样式， 从第二行开始设置格式，第一行是表头
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle cellStyle = createStyle(workbook);
        // 表头不操作 行序号是从0开始的
        //cell.getRowIndex():当前第几行
        //i:当前第几列
        //System.out.println("当前第--->>" + cell.getRowIndex()+"行;第"+i+"列------cell-->>"+cell);
        //标题
        if (cell.getRowIndex() < 2) {
            //这个终于可以设置颜色了  https://blog.csdn.net/z1074907546/article/details/50544178 这个链接有颜色说明。
            //setFillPattern是设置单元格填充样式，SOLID_FOREGROUND纯色使用前景颜色填充，接着设置前景颜色
            // (setFillForegroundColor)就可以给单元格着色了。setFillForegroundColor()方法的参数是一个short类型，
            // easyExcel使用索引来代表颜色，默认已经有一些颜色了。可以自定义颜色，可以自定义颜色，可以自定义颜色。
            // 设置前景填充样式
           // cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //设置背景色（GREY_50_PERCENT：灰色）
            //cellStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            //cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());

            Font font = workbook.createFont();
            font.setFontName("宋体");
            // 字体颜色
            font.setColor(IndexedColors.BLACK.getIndex());
            // 设置字体大小
            font.setFontHeightInPoints((short) 14);
            cellStyle.setFont(font);
        }
        //内容
        if (cell.getRowIndex() >= 2) {
            //第四列，注册资本加千分符
            if (i >= 4) {
                DataFormat dataFormat = workbook.createDataFormat();
                // 设置千位分隔符
                cellStyle.setDataFormat(dataFormat.getFormat("#,##0"));
            }
            /*if (i == 7 || i == 6) {
                String stringCellValue = cell.getStringCellValue();
                cell.setCellValue(new BigDecimal(stringCellValue.replaceAll("%", "")).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_UP).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
                // 设置百分比
                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
            }*/
        }
        // 没有不操作
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 实际中如果直接获取原单元格的样式进行修改, 最后发现是改了整行的样式, 因此这里是新建一个样* 式
     */
    private CellStyle createStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        // 下边框
       // cellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
       // cellStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
       // cellStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        //cellStyle.setBorderRight(BorderStyle.THIN);
        // 水平对齐方式
       // cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
       // cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
}

