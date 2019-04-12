package com.frm.stock.stockdata.dao;

import java.util.List;

import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;

public interface StockDao {
	public Stock getStockByStockName(String stockName) throws StockException ;

	public List<Stock> getStock(String stockName, String companyName, String buyerName);

	public List<Stock> createStock(Stock stock);

	public Stock updateStock(int stockId, Stock stock);

	Stock getStockById(int stockId);

	List<Stock> getAllStocks();

}