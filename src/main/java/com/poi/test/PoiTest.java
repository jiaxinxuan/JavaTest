package com.poi.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {

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

    private static void saveEmails(String path) throws Exception {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(path)));
        int sheetCount = workbook.getNumberOfSheets();
        for (int s = 0; s < sheetCount; s++) {
            Sheet sheet = workbook.getSheetAt(s);
            int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
            for (int r = 1; r < rowCount; r++) {
                Row row = sheet.getRow(r);
                Cell cell = row.getCell(0);
                String cellValue = cell.getStringCellValue();
                // 输出邮件地址
                System.out.println(cellValue);
            }
        }
    }
    @Test
    public  void testete() {
        try{
            batchInsert();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public static void batchInsert() throws Exception {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/project?useServerPrepStmts=false&rewriteBatchedStatements=true",
                "root", "root");

        connection.setAutoCommit(false);
        PreparedStatement cmd = connection.prepareStatement("INSERT INTO user (email) VALUES (?)");
        Workbook workbook = WorkbookFactory.create(new FileInputStream(new File("C://123.xlsx")));
        int sheetCount = workbook.getNumberOfSheets();
        for (int s = 0; s < sheetCount; s++) {
            Sheet sheet = workbook.getSheetAt(s);
            int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
            for (int r = 1; r < rowCount; r++) {
                Row row = sheet.getRow(r);
                Cell cell = row.getCell(0);
                String cellValue = cell.getStringCellValue();
                cmd.setString(1, cellValue);
                cmd.addBatch();
                if(r%1000==0){
                    cmd.executeBatch();
                }
            }
        }
        cmd.executeBatch();
        connection.commit();

        cmd.close();
        connection.close();

        long end = System.currentTimeMillis();
        System.out.println("批量插入需要时间:"+(end - start)); //批量插入需要时间:24675
    }

}
