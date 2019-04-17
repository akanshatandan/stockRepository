package com.frm.stock.stockdata.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelWorkBook<T> {

	private Workbook workbook;

	private CreationHelper createHelper = null;

	private List<String> fieldsName = new ArrayList<>();

	public ExcelWorkBook() {
		super();
		workbook = new XSSFWorkbook();
		// TODO Auto-generated constructor stub
	}

	public <T> void createExcel(String fileName, List<T> data) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		createHelper = workbook.getCreationHelper();
		Sheet sheet = getWorkbook().createSheet(fileName);
		setupFieldsForClass(data.get(0).getClass());

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		writeDataToExcel(sheet, headerFont, data);
	}

	private Boolean setupFieldsForClass(Class<? extends Object> class1) {
		Field[] fields = class1.getDeclaredFields();
		for (int i = 1; i < fields.length; i++) {
			fieldsName.add(fields[i].getName());
		}
		return true;
	}

	private <T> void writeDataToExcel(Sheet sheet, Font headerFont, List<T> data)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		int rowCount = 0;
		int columnCount = 0;
		CellStyle headerCellStyle = getWorkbook().createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row row = sheet.createRow(rowCount++);
		// Create cells
		for (String field : fieldsName) {
			Cell cell = row.createCell(columnCount++);
			cell.setCellValue(field);
			cell.setCellStyle(headerCellStyle);
		}
		CellStyle dateCellStyle = getWorkbook().createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		Class<? extends Object> classz = data.get(0).getClass();
		for (T t : data) {
			row = sheet.createRow(rowCount++);
			columnCount = 0;
			for (String field : fieldsName) {
				Cell cel = row.createCell(columnCount);
				Method method = classz.getMethod("get" + captalize(field));
				Object value = method.invoke(t, (Object[]) null);
				if (value != null) {
					if (value instanceof String) {
						cel.setCellValue((String) value);
					} else if (value instanceof Long) {
						cel.setCellValue((Long) value);
					} else if (value instanceof Integer) {
						cel.setCellValue((Integer) value);
					} else if (value instanceof Double) {
						cel.setCellValue((Double) value);
					}
				}
				columnCount++;
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < fieldsName.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(new File("poi-generated-file.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getWorkbook().write(fileOut);
		fileOut.close();

		// Closing the workbook
		getWorkbook().close();
	}

	public <T> Object readRecordFromExcel() throws IOException {
		List<Object> fields = new ArrayList<>();
		FileInputStream fileInput = new FileInputStream(new File("poi-generated-file.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 0; i < rowCount + 1; i++) {

			Row row = sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {
				// int k = row.getCell(j)..getCellType();

				CellType type = row.getCell(j).getCellTypeEnum();
				// Print Excel data in console
				if (CellType.NUMERIC.equals(type)) {
					fields.add(row.getCell(j).getNumericCellValue());

				} else {
					fields.add(row.getCell(j).getStringCellValue());
				}

			}

		}
		return fields;
	}

	private String captalize(String field) {
		return field.substring(0, 1).toUpperCase() + field.substring(1);
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

}
