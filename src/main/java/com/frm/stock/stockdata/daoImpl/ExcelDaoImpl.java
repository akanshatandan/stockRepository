package com.frm.stock.stockdata.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.frm.stock.stockdata.dao.ExcelDao;
import com.frm.stock.stockdata.dao.mapper.StockMapper;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.stockquery.StockQuery;

@Repository
public class ExcelDaoImpl implements ExcelDao{

	@Autowired	
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public List<Stock> getStocks() {
		List<Stock> stocks = namedJdbcTemplate.query(StockQuery.SQL_GET_ALL, new StockMapper());
		return stocks;
	}

}
