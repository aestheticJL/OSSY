package com.mmt.ossy.demo_4;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class CURD {

    private static String filePath = "C:\\Work\\OSSY\\";

    private static String sheetName = "Sheet0";

    public static void create(String fileName) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("学生学号");
        row.createCell(1).setCellValue("学生班级");
        row.createCell(2).setCellValue("学生姓名");
        row.createCell(3).setCellValue("学生成绩");

        FileOutputStream outputStream = new FileOutputStream(new File(fileName + ".xlsx"));
        workbook.write(outputStream);
        workbook.close();
    }

    public static void insert(String fileName, int studentId, String className, String studentName, int score) throws Exception {
        FileInputStream fis = new FileInputStream(filePath + fileName + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);

        int rowNum = sheet.getLastRowNum();

        XSSFRow row = sheet.createRow(rowNum + 1);

        row.createCell(0).setCellValue(studentId);
        row.createCell(1).setCellValue(className);
        row.createCell(2).setCellValue(studentName);
        row.createCell(3).setCellValue(score);
        FileOutputStream fileOut = new FileOutputStream(filePath + fileName + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }

    public static void read(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath + fileName + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        for (Row row : sheet) {
            if (row.getCell(0).toString().equals("")) {
                return;
            }
            int end = row.getLastCellNum();
            System.out.println();
            for (int i = 0; i < end; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    System.out.print("null" + "\t");
                    continue;
                }
                Object obj;
                if (cell.getCellTypeEnum() == STRING) {
                    obj = cell.getStringCellValue();
                } else {
                    obj = cell.getNumericCellValue();
                }
                System.out.print(obj + "\t");
            }
        }
        wb.close();
    }

    public static void select(String fileName, int studentId) throws Exception {
        FileInputStream fis = new FileInputStream(filePath + fileName + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        for (Row row : sheet) {
            if (row.getCell(0).toString().equals("")) {
                return;
            }
            Cell cell = row.getCell(0);
            Object obj;
            if (cell.getCellTypeEnum() == STRING) {
                obj = cell.getStringCellValue();
            } else {
                obj = (int) cell.getNumericCellValue();
            }
            if (obj.equals(studentId)) {
                System.out.print("学号为" + studentId + "的学生成绩为" + row.getCell(3).getNumericCellValue());
                return;
            }
        }
        wb.close();
    }

    public static void update(String fileName, int studentId, int score) throws Exception {
        FileInputStream fis = new FileInputStream(filePath + fileName + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        for (Row row : sheet) {
            if (row.getCell(0).toString().equals("")) {
                return;
            }
            Cell cell = row.getCell(0);
            Object obj;
            if (cell.getCellTypeEnum() == STRING) {
                obj = cell.getStringCellValue();
            } else {
                obj = (int) cell.getNumericCellValue();
            }
            if (obj.equals(studentId)) {
                String className = row.getCell(1).getStringCellValue();
                String studentName = row.getCell(2).getStringCellValue();
                XSSFRow sheetRow = sheet.createRow(row.getRowNum());
                sheetRow.createCell(0).setCellValue(studentId);
                sheetRow.createCell(1).setCellValue(className);
                sheetRow.createCell(2).setCellValue(studentName);
                sheetRow.createCell(3).setCellValue(score);
                break;
            }
        }
        FileOutputStream fileOut = new FileOutputStream(filePath + fileName + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }

    public static void copy(String fileName, String copyFileName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath + fileName + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);

        FileInputStream copyFis = new FileInputStream(filePath + copyFileName + ".xlsx");
        XSSFWorkbook copyWb = new XSSFWorkbook(copyFis);
        XSSFSheet copySheet = copyWb.getSheet(sheetName);

        for (Row row : sheet) {
            //如果当前行没有数据，跳出循环
            if (row.getCell(0).toString().equals("")) {
                return;
            }
            int end = row.getLastCellNum();
            int copyEnd = copySheet.getLastRowNum() + 1;
            XSSFRow copySheetRow = copySheet.createRow(copyEnd);
            for (int i = 0; i < end; i++) {
                Cell cell = row.getCell(i);
                Object obj;
                if (cell.getCellTypeEnum() == STRING) {
                    obj = cell.getStringCellValue();
                    copySheetRow.createCell(i).setCellValue((String)obj);
                } else {
                    obj = cell.getNumericCellValue();
                    copySheetRow.createCell(i).setCellValue((double)obj);
                }
            }
        }
        FileOutputStream fileOut = new FileOutputStream(filePath + copyFileName + ".xlsx");
        copyWb.write(fileOut);
        fileOut.close();
        copyWb.close();
        wb.close();
    }
}