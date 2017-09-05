package com.poi.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {

    public static void main(String[] args) {
        String fileName="D://1.xls";
        try {
            List list=analyzeExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<Integer> analyzeExcel(String fileName) throws IOException {

        FileInputStream fis=new FileInputStream(new File(fileName));
        List<Integer> list=new ArrayList<Integer>();
        HSSFWorkbook workbook=null;
        if(fileName.endsWith(".xls")){//2003
            workbook=new HSSFWorkbook(fis);
        }else{//2007
          //  workbook=new XSSFWorkbook(fis);
        }

        int sheetCount = workbook.getNumberOfSheets();//获取工作表数量
        for (int s = 0; s < sheetCount; s++) {//遍历工作表
            Sheet sheet = workbook.getSheetAt(s);
            int rowCount = sheet.getPhysicalNumberOfRows();//获取工作表内行数
            for (int r = 1; r < rowCount; r++) {
                Row row = sheet.getRow(r);
                Cell cell=  row.getCell(0);
                System.out.println(getValue(cell));
            }
        }
        return list;
    }
    private static String getValue(Cell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
                return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            DecimalFormat df = new DecimalFormat("#.##");
            String value = df.format(xssfRow.getNumericCellValue());
                return value;
        } else {
                return String.valueOf(xssfRow.getStringCellValue());
        }
    }

}
