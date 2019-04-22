package com.frm.stock.stockdata.dao;

import java.util.List;

import com.frm.stock.stockdata.model.Stock;

public interface ExcelDao {

	List<Stock> getStocks();

	void insertIntoDB(List<Stock> list);

}
