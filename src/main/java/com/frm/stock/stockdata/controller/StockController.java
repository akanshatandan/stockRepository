package com.frm.stock.stockdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frm.stock.stockdata.constants.StockConstant;
import com.frm.stock.stockdata.error.StockError;
import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@Autowired
	private StockError stockError;

	private Logger logger = LoggerFactory.getLogger(StockController.class);

	public StockController() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Get list of Stocks based on StockName in the System ", response = List.class, tags = "getStockByStockName")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/frm/{stockname}")
	public ResponseEntity<List<Stock>> getStockByStockName(@PathVariable("stockname") String stockName)
			throws StockException {
		logger.debug("Entering into getStockByStockname method");
		long start = System.currentTimeMillis();
		if (stockName.equals("null")) {
			logger.error(StockConstant.NULL_FIELD_MESSAGE);
			throw new StockException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name());
		}
		try {
			List<Stock> stock = stockService.getStockByStockName(stockName);
			long end = System.currentTimeMillis();
			logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
			return new ResponseEntity(stock, HttpStatus.OK);
		} catch (StockException ex) {
			logger.error(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND);
			return new ResponseEntity(stockError.prepareError400(ex), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Get list of Stocks in the System ", response = List.class, tags = "getStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/frm")
	public ResponseEntity<Object> getStock(@RequestParam(name = "stockname") String stockName,
			@RequestParam(name = "companyName") String companyName, @RequestParam(name = "buyerName") String buyerName)
			throws StockException {
		logger.debug("Entering into getStock method");
		long start = System.currentTimeMillis();
		Stock stock = null;
		try {
			stock = stockService.getStock(stockName, companyName, buyerName);
			long end = System.currentTimeMillis();
			logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
		} catch (StockException ex) {
			logger.error(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND);
			return new ResponseEntity(stockError.prepareError400(ex), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(stock, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Get list of Stocks in the System ", response = List.class, tags = "getStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("all/frm")
	public ResponseEntity<Object> getAllStock()
			throws StockException {
		logger.debug("Entering into getStock method");
		long start = System.currentTimeMillis();
		System.out.println("*******************************************************Inside controller******************************************************");
		List<Stock> stock = null;
			stock = stockService.getAllStock();
			long end = System.currentTimeMillis();
			logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
		
		return new ResponseEntity(stock, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "create Stock in the System ", response = List.class, tags = "createStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK") })
	@PostMapping("/frm/createStock")
	public ResponseEntity<List<Stock>> createStock(@RequestBody Stock stock) throws StockException {
		logger.debug("Entering into createStock method");
		long start = System.currentTimeMillis();
		List<Stock> stockList = null;
		try {
			stockList = stockService.createStock(stock);
			long end = System.currentTimeMillis();
			logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
			return new ResponseEntity(stockList, HttpStatus.OK);
		} catch (StockException ex) {
			logger.error(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity(stockError.prepareError400(ex), HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "update Stock in the System ", response = List.class, tags = "updateStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/frm/update/{stockId}")
	public ResponseEntity<Object> updateStock(@PathVariable("stockId") int stockId, @RequestBody Stock stock) throws StockException {
		logger.debug("Entering into updateStock method");
		long start = System.currentTimeMillis();
		Stock updatedStock = null;
		try {
			updatedStock = stockService.updateStock(stockId, stock);
			long end = System.currentTimeMillis();
			logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
			return new ResponseEntity(updatedStock, HttpStatus.OK);
		} catch (StockException ex) {
			logger.error(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity(stockError.prepareError400(ex), HttpStatus.BAD_REQUEST);
		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "buy Stock in the System ", response = List.class, tags = "buyStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/frm/buystock/{stockId}/buystock/{buyedStock}")
	public ResponseEntity<Object> buyStock(@PathVariable("stockId") int stockId,@PathVariable("buyedStock") int buyedStock) throws StockException {
		logger.debug("Entering into updateStock method");
		long start = System.currentTimeMillis();
		Stock updatedStock = null;
		try {
			updatedStock = stockService.buyedStock(stockId,buyedStock);
			long end = System.currentTimeMillis();
			logger.debug("Time Took : " + ((end - start) / 1000 + " sec."));
			return new ResponseEntity(updatedStock, HttpStatus.OK);
		} catch (StockException ex) {
			logger.error(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST);
			return new ResponseEntity(stockError.prepareError400(ex), HttpStatus.BAD_REQUEST);
		}
	}

	
}
