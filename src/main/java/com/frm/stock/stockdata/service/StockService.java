package com.frm.stock.stockdata.service;

import java.util.List;

import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;

public interface StockService {

	public Stock getStockByStockName(String stockName) throws StockException;

	public List<Stock> getStock(String stockName, String companyName, String buyerName);

	public List<Stock> createStock(Stock stockList);

	public Stock updateStock(int stockId, Stock stock);

}
