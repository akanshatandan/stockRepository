package com.frm.stock.stockdata.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.frm.stock.stockdata.constants.StockConstant;
import com.frm.stock.stockdata.exception.StockException;

@Component
public class StockError {

	@Value("${errorMessage404}")
	private String errorMessage404;
	
	@Value("${errorMessage400}")
	private String errorMessage400;

	public Map<String, Object> prepareError404(StockException ex) {
		Map<String, Object> map = new HashMap<>();
		map.put(StockConstant.ERROR_MESSAGE, errorMessage404);
		map.put(StockConstant.ERROR_CODE, HttpStatus.NOT_FOUND.value());
		map.put(StockConstant.ERROR_NAME, HttpStatus.NOT_FOUND.name());
		map.put(StockConstant.CAUSE, ex.getCause());
		return map;
	}
	
	public Map<String, Object> prepareError400(StockException ex) {
		Map<String, Object> map = new HashMap<>();
		map.put(StockConstant.ERROR_MESSAGE, errorMessage400);
		map.put(StockConstant.ERROR_CODE, HttpStatus.BAD_REQUEST.value());
		map.put(StockConstant.ERROR_NAME, HttpStatus.BAD_REQUEST.name());
		map.put(StockConstant.CAUSE, ex.getCause());
		return map;
	}
}
