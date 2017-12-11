package com.poi.test;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Component
public class ExportExcel<T> {

	Logger logger = Logger.getLogger(ExportExcel.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 导出数据到Excel中
	 * 
	 * @param title
	 *            标题
	 * @param writefile
	 *            文件存放路径
	 * @param headMap
	 *            表头，key为属性名，value为列名称
	 * @param dataList
	 *            数据
	 */
	public void toExcel(String title, String writefile, LinkedHashMap<String, String> headMap, List<T> dataList) {
		FileOutputStream output = null;
		SXSSFWorkbook wb = null;
		try {
			// 输出文件路径
			output = new FileOutputStream(new File(writefile));
			wb = new SXSSFWorkbook(1000);
			if (dataList.size() > 1000000) {
				int size = dataList.size();
				int num = (int) ((size / 1000000));
				for(int i=0;i<=num;i++){
					List<T> tempList = null;
					if(((i*1000000)+1000000)<size){
						tempList = dataList.subList((i*1000000)+1,((i*1000000)+1000000));
						this.toSheet(wb, title, headMap, "sheet"+(i+1), tempList);
					}else{
						tempList = dataList.subList((i*1000000)+1,size);
						this.toSheet(wb, title, headMap, "sheet"+(i+1), tempList);
					}
					tempList = null;
				}
			} else {
				this.toSheet(wb, title, headMap, "sheet1", dataList);
			}
			wb.write(output);
		} catch (Exception e) {
			logger.error("导出错误", e);
		} finally {
			try {
				wb.close();
				output.close();
			} catch (IOException e) {
				logger.error("导出错误", e);
			}
		}
	}

	private Sheet toSheet(SXSSFWorkbook wb, String title, LinkedHashMap<String, String> headMap, String sheetName, List<T> dataList) throws Exception {
		Sheet sheet1 = wb.createSheet(sheetName);
		int excelRow = 0;
		Set<String> headSet = headMap.keySet();
		// 写入标题
		Row titleRow = (Row) sheet1.createRow(excelRow++);
		Cell titlecell = titleRow.createCell(0);
		XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titleStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(50, 186, 129)));
		Font font = wb.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		titleStyle.setFont(font);
		if (headSet.size() > 1) {
			sheet1.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(), titleRow.getRowNum(), headSet.size() - 1));
		}
		titlecell.setCellStyle(titleStyle);
		titlecell.setCellValue(title);

		// 设置表头
		Row headRow = (Row) sheet1.createRow(excelRow++);
		XSSFCellStyle headStyle = (XSSFCellStyle) wb.createCellStyle();
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(181, 181, 181)));
		Font headfont = wb.createFont();
		headfont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		headfont.setFontName("Arial");
		headfont.setFontHeightInPoints((short) 12);
		headStyle.setFont(headfont);
		int i = 0;
		for (String key : headSet) {
			Cell cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headMap.get(key));
			sheet1.setColumnWidth(i, headMap.get(key).length() * 1000);
			i++;
		}
		XSSFCellStyle rowStyle = (XSSFCellStyle) wb.createCellStyle();
		Font rowfont = wb.createFont();
		rowfont.setFontName("Arial");
		rowfont.setFontHeightInPoints((short) 10);

		// 填充数据
		for (Object obj : dataList) {
			Row dataRow = (Row) sheet1.createRow(excelRow++);
			// 反射获取数据
			Class<? extends Object> clazz = obj.getClass();
			int h = 0;
			for (String key : headSet) {
				PropertyDescriptor pd = new PropertyDescriptor(key, clazz);
				Method getMethod = pd.getReadMethod();
				Object o = getMethod.invoke(obj);
				// Excel填充数据
				Cell cell = dataRow.createCell(h);
				
				if (o != null) {
					
					if(o instanceof Date){
						String temp = format.format((Date)o);
						cell.setCellValue(temp);
					}else{
						cell.setCellValue(o.toString());
					}
				}
				cell.setCellStyle(rowStyle);
				h++;
			}
			rowStyle.setFont(rowfont);
			h = 0;
		}
		return sheet1;
	}
}
