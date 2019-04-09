package com.frm.stock.stockdata.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.frm.stock.stockdata.model.Stock;

@Repository
public class StockDaoImpl implements StockDao{

	public StockDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Stock> getStockByStockName(String stockName) {
		List<Stock> stockList = new ArrayList<>();
	    Stock s1 = new Stock();
	    s1.setStockId(1);
	    s1.setStockName("NSE");
	    s1.setCompanyName("Infy");
	    s1.setBuyerName("Arun");
	    s1.setNoOfStocks(100);
	    stockList.add(s1);
		return stockList;
	}

	@Override
	public List<Stock> getStock(String stockName, String companyName, String buyerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> createStock(List<Stock> stockList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock updateStock(int stockId, Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

}
