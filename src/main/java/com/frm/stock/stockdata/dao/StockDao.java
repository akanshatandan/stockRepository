package com.frm.stock.stockdata.dao;

import java.util.List;

import com.frm.stock.stockdata.model.Stock;

public interface StockDao {
	public List<Stock> getStockByStockName(String stockName);

	public List<Stock> getStock(String stockName, String companyName, String buyerName);

	public List<Stock> createStock(List<Stock> stockList);

	public Stock updateStock(int stockId, Stock stock);
}
