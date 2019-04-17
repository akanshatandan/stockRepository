package com.frm.stock.stockdata.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.service.ExcelService;
import com.frm.stock.stockdata.utils.ExcelWorkBook;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelWorkBook<Stock> excelWorkBook;

	@Autowired
	private ExcelService excelService;

	private Logger logger = LoggerFactory.getLogger(ExcelController.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Create Excel in the System ", response = List.class, tags = "createExcel")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/create/{fileName}")
	public ResponseEntity<Object> createExcel(@PathVariable("fileName") String fileName, @RequestBody List<Stock> stock)
			throws StockException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		logger.debug("Entering into createExcel method");
		long start = System.currentTimeMillis();
		// Stock currStock = null;
		excelWorkBook.createExcel(fileName, stock);
		// currStock = excelService.createExcel(stock);
		long end = System.currentTimeMillis();
		logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
		return new ResponseEntity(stock, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Write Data from Db to Excel", response = List.class, tags = "writeRecordDbToExcel")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/createExcel")
	public ResponseEntity<List<Stock>> writeRecordDbToExcel()
			throws StockException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		logger.debug("Entering into createExcel method");
		long start = System.currentTimeMillis();
		List<Stock> stock = excelService.getStocks();
		excelWorkBook.createExcel("Stock", stock);
		long end = System.currentTimeMillis();
		logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
		return new ResponseEntity(stock, HttpStatus.OK);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "read Data from Excel to Db", response = List.class, tags = "readRecordExcelToDB")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/readExcel")
	public ResponseEntity<List<Stock>> readRecordExcelToDB()
			throws StockException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		logger.debug("Entering into createExcel method");
		long start = System.currentTimeMillis();
		List<Stock> list = (List<Stock>) excelWorkBook.readRecordFromExcel();
		long end = System.currentTimeMillis();
		logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
		return new ResponseEntity(list, HttpStatus.OK);
	}

}
