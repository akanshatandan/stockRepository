package com.frm.stock.stockdata.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frm.stock.stockdata.model.Stock;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//@WebAppConfiguration
//@WebMvcTest(StockController.class)

//@WebMvcTest(StockController.class)
public class StockIntegrationTest {

	private static final String GET_STOCK_DTO_LIST_JSON_TO_RETURN = null;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext wac;

	/*@Autowired
	StockController stockController;*/

	public StockIntegrationTest() {
		super();
	}

	@Before
	public void setup() throws Exception {
		//this.mockMvc = MockMvcBuilders.standaloneSetup(this.stockController).build();// Standalone context
	}

	/*
	 * @Before public void before() { this.mockMvc =
	 * MockMvcBuilders.webAppContextSetup(this.wac).build(); }
	 */

	@Test
	public void testCreateStock() throws Exception {
		Stock stock = createDummyStock();

		mockMvc.perform(MockMvcRequestBuilders.post("/stock/frm/createStock").content(asJsonString(stock))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()) .andExpect(content().json(GET_STOCK_DTO_LIST_JSON_TO_RETURN));
				//.andExpect(MockMvcResultMatchers.jsonPath("$.stockId").exists());
		// .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\
		//.andExpect(header().string("location", containsString("http://localhost/")))
	}

	@Test
	public void getStock() throws Exception {

		mockMvc.perform(get("/stock/all/frm").accept("application/json").contentType("application/json"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
		// .andExpect(jsonPath("$[0].name", is("bob")));

	}

	private Stock createDummyStock() {
		Stock stock = new Stock();
		stock.setStockId(99);
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
