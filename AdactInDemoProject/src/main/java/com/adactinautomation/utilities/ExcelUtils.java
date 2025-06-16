package com.adactinautomation.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static XSSFWorkbook workbook;
    static String sheetName = ConfigReader.getProperty("TestDataSheet");
    private static final Logger log = LoggerHelper.getLogger(ExcelUtils.class);

    public ExcelUtils() {
        File filepath = new File(System.getProperty("user.dir") + "/src/test/resources/Testdata/AdactIn_TestData.xlsx");
        try {
            workbook = new XSSFWorkbook(filepath);
            log.info("Excel file loaded successfully from: " + filepath.getAbsolutePath());
        } catch (Exception e) {
            log.error("Unable to read the Excel file. " + e.getMessage());
            throw new RuntimeException("Unable to read the Excel file. " + e.getMessage());
        }
    }

    public static String getStringData(int row, int column) {
        String data = workbook.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
        log.debug("Fetched String data from sheet: " + sheetName + ", Row: " + row + ", Column: " + column + " => " + data);
        return data;
    }

    public double getNumericData(int row, int column) {
        double num = workbook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
        log.debug("Fetched Numeric data from sheet: " + sheetName + ", Row: " + row + ", Column: " + column + " => " + num);
        return num;
    }

    public static String getDataByColumnName(int rowIndex, String columnName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow headerRow = sheet.getRow(0);
        int columnCount = headerRow.getLastCellNum();
        for (int col = 0; col < columnCount; col++) {
            String header = headerRow.getCell(col).getStringCellValue();
            if (header.equalsIgnoreCase(columnName)) {
                XSSFCell cell = sheet.getRow(rowIndex).getCell(col);
                String data = cell.toString();
                log.debug("Data fetched for Column: " + columnName + " at Row: " + rowIndex + " => " + data);
                return data;
            }
        }
        log.error("Column name " + columnName + " not found in sheet " + sheetName);
        throw new RuntimeException("Column name " + columnName + " not found in sheet " + sheetName);
    }

    public static List<Integer> getAllRowIndicesByTestCaseID(String testCaseId) {
        List<Integer> rowIndices = new ArrayList<>();
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() + 1;
        for (int i = 0; i < rowCount; i++) {
            String cellValue = getStringData(i, 0);
            if (cellValue.equalsIgnoreCase(testCaseId)) {
                rowIndices.add(i);
                log.debug("Matched Test Case ID: " + testCaseId + " at Row: " + i);
            }
        }
        if (rowIndices.isEmpty()) {
            log.error("No rows found for Test Case ID " + testCaseId + " in sheet " + sheetName);
            throw new RuntimeException("No rows found for Test Case ID " + testCaseId + " in sheet " + sheetName);
        }
        return rowIndices;
    }
}
