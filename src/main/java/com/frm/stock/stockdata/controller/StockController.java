package com.frm.stock.stockdata.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	private Logger logger = LoggerFactory.getLogger(StockController.class);

	@Value("${message400}")
	private String errorMessage400;

	public StockController() {
		super();
	}

	@ApiOperation(value = "Get list of Stocks based on StockName in the System ", response = List.class, tags = "getStockByStockName")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/frm/{stockname}")
	public Stock getStockByStockName(@PathVariable("stockname") String stockName) {
		logger.debug("Entering into getStockByStockname method");
		if (null == stockName) {
			logger.error("Throw exception");
			//throw new StockException(HttpStatus.BAD_REQUEST, errorMessage400);
		}
		try {
		return stockService.getStockByStockName(stockName);
		}catch(StockException ex) {
			logger.error("not found",HttpStatus.NOT_FOUND);
			
		}
		return null;
	}

	@ApiOperation(value = "Get list of Stocks in the System ", response = List.class, tags = "getStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/frm")
	public List<Stock> getStock(@RequestParam(name = "stockname") String stockName,
			@RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "buyerName") String buyerName) throws StockException {
		logger.debug("Entering into getStock method");
		if (null == stockName) {
			logger.error("Throw exception");
			throw new StockException(HttpStatus.BAD_REQUEST, errorMessage400);
		}
		return stockService.getStock(stockName,companyName,buyerName);
	}

	@ApiOperation(value = "create Stock in the System ", response = List.class, tags = "createStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK") })
	@PostMapping("/frm/createStock")
	public List<Stock> createStock(@RequestBody Stock stock) {
        
		return stockService.createStock(stock);

	}

	@ApiOperation(value = "update Stock in the System ", response = List.class, tags = "updateStock")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/frm/update/{stockId}")
	public Stock updateStock(@PathVariable("stockId") int stockId, @RequestBody Stock stock) {

		return stockService.updateStock(stockId, stock);

	}

}
