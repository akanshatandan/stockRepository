package com.frm.stock.stockdata.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.frm.stock.stockdata.constants.StockConstant;
import com.frm.stock.stockdata.dao.ExcelDao;
import com.frm.stock.stockdata.dao.mapper.StockMapper;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.stockquery.StockQuery;

@Repository
public class ExcelDaoImpl implements ExcelDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<Stock> getStocks() {
		List<Stock> stocks = namedJdbcTemplate.query(StockQuery.SQL_GET_ALL, new StockMapper());
		return stocks;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insertIntoDB(List<Stock> list) {
		Map namedParameters = new HashMap();
		for (Stock stock : list) {
			namedParameters.put(StockConstant.STOCKID, stock.getStockId());
			namedParameters.put(StockConstant.STOCKNAME, stock.getStockName());
			namedParameters.put(StockConstant.BUYERNAME, stock.getBuyerName());
			namedParameters.put(StockConstant.COMPANYNAME, stock.getCompanyName());
			namedParameters.put(StockConstant.NOOFSTOCKS, stock.getNoOfStocks());
			namedParameters.put(StockConstant.REMAINING_STOCK, stock.getNoOfStocks());
			namedParameters.put(StockConstant.BUYED_STOCK, stock.getNoOfStocks() - stock.getRemainingStocks());
			namedJdbcTemplate.update(StockQuery.SQL_CREATE, namedParameters);
		}
	}

}
