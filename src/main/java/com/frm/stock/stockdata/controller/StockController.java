package com.frm.stock.stockdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	public StockController() {
		super();
	}

	@ApiOperation(value = "Get list of Stocks based on StockName in the System ", response = List.class, tags = "getStockByStockName")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/frm/{stockname}")
	public List<Stock> getStockByStockName(@PathVariable("stockname") String stockName) {

		return null;

	}

	@ApiOperation(value = "Get list of Stocks in the System ", response = List.class, tags = "getStock")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/frm")
	public List<Stock> getStock(@RequestParam(name = "stockname") String stockName,
			@RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "buyerName") String buyerName) {

		return null;

	}

	@ApiOperation(value = "create Stock in the System ", response = List.class, tags = "createStock")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK") })
	@PostMapping("/frm/createStock")
	public List<Stock> createStock(@RequestBody List<Stock> stock) {

		return null;

	}

	@ApiOperation(value = "update Stock in the System ", response = List.class, tags = "updateStock")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/frm/update/{stockId}")
	public Stock updateStock(@PathVariable("stockId") int stockId, @RequestBody Stock stock) {

		return null;

	}

}
