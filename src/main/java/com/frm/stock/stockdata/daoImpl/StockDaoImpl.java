package com.frm.stock.stockdata.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.frm.stock.stockdata.constants.StockConstant;
import com.frm.stock.stockdata.dao.StockDao;
import com.frm.stock.stockdata.dao.mapper.StockMapper;
import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.stockquery.StockQuery;

@Repository
public class StockDaoImpl implements StockDao {

	private Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public StockDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	@Override
	public Stock getStockByStockName(String stockName) throws StockException {
		logger.debug("Entering into method getStockByStockName");

		Stock stock = null;
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource(StockConstant.STOCKNAME, stockName);
			stock = (Stock) namedJdbcTemplate.queryForObject(StockQuery.SQL_GET_BY_NAME, namedParameters,
					new StockMapper());
			logger.debug("The data is  {}", stock.toString());
		} catch (DataAccessException ex) {
			logger.error("The exception is   {}", ex.getCause());
			throw new StockException("no record found");
		}
		return stock;
	}

	@Override
	public Stock getStock(String stockName, String companyName, String buyerName) throws StockException {
		logger.debug("Entering into method getStock");
		Stock stock = null;
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource(StockConstant.STOCKNAME, stockName)
					.addValue(StockConstant.COMPANYNAME, companyName).addValue(StockConstant.BUYERNAME, buyerName);
			stock = (Stock) namedJdbcTemplate.queryForObject(StockQuery.SQL_GET_STOCK, namedParameters,
					new StockMapper());
			logger.debug("The stock data is {}", stock);
		} catch (DataAccessException ex) {
			logger.error("The exception is   {}", ex.getCause());
			throw new StockException(ex.getCause(), StockConstant.NO_RECORD_FOUND, HttpStatus.NOT_FOUND);
		}
		return stock;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Stock> createStock(Stock stock) throws StockException {
		logger.debug("Entering into method createStock");
		Map namedParameters = new HashMap();
		try {
			namedParameters.put(StockConstant.STOCKID, stock.getStockId());
			namedParameters.put(StockConstant.STOCKNAME, stock.getStockName());
			namedParameters.put(StockConstant.BUYERNAME, stock.getBuyerName());
			namedParameters.put(StockConstant.COMPANYNAME, stock.getCompanyName());
			namedParameters.put(StockConstant.NOOFSTOCKS, stock.getNoOfStocks());
			namedJdbcTemplate.update(StockQuery.SQL_CREATE, namedParameters);
			logger.debug("Data is created into database whose Id is {}", stock.getStockId());
		} catch (DataAccessException se) {
			logger.error("The exception is   {}", se.getCause());
			throw new StockException(se.getCause(), StockConstant.NOT_CREATED, HttpStatus.NOT_FOUND);
		}
		List<Stock> stocks = getStocks();
		return stocks;
	}

	public List<Stock> getStocks() {
		logger.debug("Entering into method getStocks");
		List<Stock> stocks = namedJdbcTemplate.query(StockQuery.SQL_GET_ALL, new StockMapper());
		return stocks;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Stock updateStock(int stockId, Stock stock) throws StockException {
		logger.debug("Entering into method updateStock");
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue(StockConstant.STOCKNAME, stock.getStockName())
					.addValue(StockConstant.BUYERNAME, stock.getBuyerName())
					.addValue(StockConstant.COMPANYNAME, stock.getCompanyName())
					.addValue(StockConstant.NOOFSTOCKS, stock.getNoOfStocks()).addValue(StockConstant.STOCKID, stock.getStockId());

			namedJdbcTemplate.update(StockQuery.SQL_UPDATE, namedParameters);
			logger.debug("Stock is updated successfully");
		} catch (DataAccessException se) {
			logger.error("The exception is   {}", se.getCause());
			throw new StockException(se.getCause(), StockConstant.NOT_CREATED, HttpStatus.NOT_FOUND);
		}
		return getStockById(stockId);
	}

	@Override
	public Stock getStockById(int stockId) {
		logger.debug("Entering into method getStockByStockName");
		SqlParameterSource namedParameters = new MapSqlParameterSource(StockConstant.STOCKID, stockId);
		Stock stock = namedJdbcTemplate.queryForObject(StockQuery.SQL_GET_BY_ID, namedParameters, new StockMapper());
		logger.debug("Get the record for stock whose id is {}", stock.getStockId());
		return stock;
	}

	@Override
	public List<Stock> getAllStocks() {
		logger.debug("Entering into method getAllStocks");
		List<Stock> stocks = namedJdbcTemplate.query(StockQuery.SQL_GET_ALL, new StockMapper());
		return stocks;
	}

}
