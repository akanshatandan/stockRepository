package com.frm.stock.stockdata.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frm.stock.stockdata.model.Stock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StockH2TestCases {

	@Autowired
	private MockMvc mvc;

	public StockH2TestCases() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	WebApplicationContext wac;

	@Test
	public void testGetStock() throws Exception {
		mvc.perform(get("/stock/all/frm").accept("application/json").contentType("application/json"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	@Test
	public void testCreateStock() throws Exception {
		Stock stock = createDummyStock();

		mvc.perform(MockMvcRequestBuilders.post("/stock/frm/createStock").content(asJsonString(stock))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].companyName", containsString(stock.getCompanyName())));
	}

	@Test
	public void getStock() throws Exception {

		mvc.perform(get("/stock/all/frm").accept("application/json").contentType("application/json"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	@Test(expected = NestedServletException.class)
	public void getStockforNull() throws Exception {
		String stockName = "null";
		// exception.expect(StockException.class);
		mvc.perform(get("/stock/frm/{stockname}", stockName).accept("application/json").contentType("application/json"))
				.andDo(MockMvcResultHandlers.print());// .andExpect(status().isBadRequest());
	}

	@Test
	public void getStockByStockName() throws Exception {
		String stockName = "DSE";
		mvc.perform(get("/stock/frm/{stockname}", stockName).accept("application/json").contentType("application/json"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].stockName", containsString(stockName)));
		;
	}


	@Test
	public void updateStock() throws Exception {
		Stock stock = createDummyStock();
		stock.setStockName("DPE");
		mvc.perform(put("/stock/frm/update/{stockId}", stock.getStockId()).content(asJsonString(stock))
				.accept("application/json").contentType("application/json")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.stockName", containsString(stock.getStockName())));
	}

	@Test
	public void negativeTestUpdateStock() throws Exception {
		Stock stock = getStockData();
		stock.setStockName("DPE");
		mvc.perform(put("/stock/frm/update/{stockId}", stock.getStockId()).content(asJsonString(stock))
				.accept("application/json").contentType("application/json")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.stockName", containsString(stock.getStockName())));
	}

	@Test
	public void buyStockforNegativeScenerio() throws Exception {
		int stockId = 99;
		int buyedStock = 50;
		mvc.perform(put("/stock/frm/buystock/{stockId}/buystock/{buyedStock}", stockId, buyedStock)
				.accept("application/json").contentType("application/json")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void buyStock() throws Exception {
		int stockId = 179;
		int buyedStock = 1;
		String stockName = "NSE";
		mvc.perform(put("/stock/frm/buystock/{stockId}/buystock/{buyedStock}", stockId, buyedStock)
				.accept("application/json").contentType("application/json")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.stockName", containsString(stockName)));
	}

	@Test
	public void getStockTestForPositive() throws Exception {

		mvc.perform(get("/stock/frm").param("stockname", "DSE").param("buyerName", "Akhil").param("companyName", "TCS")
				.accept("application/json").contentType("application/json")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void getStockTest() throws Exception {

		mvc.perform(get("/stock/frm").param("stockname", "NSE").param("buyerName", "Arun").param("companyName", "Infy")
				.accept("application/json").contentType("application/json")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isNotFound());
	}

	private Stock getStockData() {
		Stock stock = new Stock();
		stock.setStockId(232);
		stock.setStockName("NSE");
		stock.setBuyedStocks(0);
		stock.setBuyerName("deepak");
		stock.setNoOfStocks(100);
		stock.setRemainingStocks(100);
		stock.setCompanyName("deloitte");
		return stock;
	}

	private Stock createDummyStock() {
		Stock stock = new Stock();
		stock.setStockId(198);
		stock.setStockName("NSE");
		stock.setBuyedStocks(0);
		stock.setBuyerName("deepak");
		stock.setNoOfStocks(100);
		stock.setRemainingStocks(100);
		stock.setCompanyName("deloitte");
		return stock;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
