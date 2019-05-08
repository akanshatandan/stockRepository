package com.frm.stock.stockdata.dao;

import java.util.List;

import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;

public interface StockDao {
	public List<Stock> getStockByStockName(String stockName) throws StockException ;

	public Stock getStock(String stockName, String companyName, String buyerName) throws StockException;

	public List<Stock> createStock(Stock stock) throws StockException;

	public Stock updateStock(int stockId, Stock stock) throws StockException;

	Stock getStockById(int stockId);

	List<Stock> getAllStocks();

	public Stock buyedStock(int stockId, int buyedStock) throws StockException;

	public List<Stock> getAllStock();

}