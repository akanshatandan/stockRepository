package com.frm.stock.stockdata.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frm.stock.stockdata.model.Stock;

public class StockMapper implements RowMapper<Stock> {

	@Override
	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("inside mapper class...");
		Stock stock = new Stock();
		stock.setStockId(rs.getInt("stockId"));
		stock.setStockName(rs.getString("stockName"));
		stock.setBuyerName(rs.getString("buyerName"));
		stock.setCompanyName(rs.getString("companyName"));
		stock.setNoOfStocks(rs.getInt("noOfStocks"));

		return stock;
	}

}
