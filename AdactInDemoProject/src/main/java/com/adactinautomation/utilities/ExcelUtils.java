package com.adactinautomation.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static XSSFWorkbook workbook;
	static String sheetName=ConfigReader.getProperty("TestDataSheet");

	public ExcelUtils() {
		File filepath = new File(System.getProperty("user.dir") + "/src/test/resources/Testdata/AdactIn_TestData.xlsx");
		try {
			workbook = new XSSFWorkbook(filepath);
		} catch (Exception e) {
			throw new RuntimeException("Unable to read the Excel file. " + e.getMessage());
		}
	}

	public static String getStringData(int row, int column) {
		return workbook.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}

	public double getNumericData(int row, int column) {
		return workbook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}

	public static String getDataByColumnName(int rowIndex, String columnName) {
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow headerRow = sheet.getRow(0);
		int columnCount = headerRow.getLastCellNum();
		for (int col = 0; col < columnCount; col++) {
			String header = headerRow.getCell(col).getStringCellValue();
			if (header.equalsIgnoreCase(columnName)) {
				XSSFCell cell = sheet.getRow(rowIndex).getCell(col);
				return cell.toString();
			}
		}
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
			}
		}
		if (rowIndices.isEmpty()) {
			throw new RuntimeException("No rows found for Test Case ID " + testCaseId + " in sheet " + sheetName);
		}
		return rowIndices;
	}
}
