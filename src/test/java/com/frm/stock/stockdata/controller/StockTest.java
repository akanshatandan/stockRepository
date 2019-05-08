/*package com.frm.stock.stockdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.service.StockService;

public class StockTest {

	@InjectMocks
	private StockController stockControllera;

	@Mock
	private StockService service;

	public StockTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testStockGetMethod() throws StockException {
		List<Stock> stockList = new ArrayList<>();
		Mockito.when(service.getStockByStockName("sdds")).thenReturn(stockList);
		ResponseEntity<List<Stock>> asas = stockControllera.getStockByStockName("szdsd");
		
	}
}
*/