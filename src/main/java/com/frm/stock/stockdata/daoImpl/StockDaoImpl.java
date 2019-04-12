package com.frm.stock.stockdata.daoImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.frm.stock.stockdata.dao.StockDao;
import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.utils.StockMapper;

@Repository
public class StockDaoImpl implements StockDao {

	private Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public StockDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Stock getStockByStockName(String stockName) throws StockException {
		logger.debug("Entering into method getStockByStockName");
		String SQL_GET_ALL = "SELECT * FROM STOCK where stockName=?";
		Stock stock = null;
		try {
			stock = jdbcTemplate.queryForObject(SQL_GET_ALL, new Object[] { stockName }, new StockMapper());
		} catch (DataAccessException ex) {
			throw new StockException(ex.getCause(),"no record found",HttpStatus.NOT_FOUND);
		}
		return stock;
	}

	@Override
	public List<Stock> getStock(String stockName, String companyName, String buyerName) {
		String SQL_GET_ALL = "SELECT * FROM STOCK where stockName =? and companyName= ? and buyerName=?";
		List<Stock> stockList = jdbcTemplate.query(SQL_GET_ALL, new StockMapper());
		return stockList;
	}

	@Override
	public List<Stock> createStock(Stock stock) {
		String SQL_GET_ALL = "INSERT INTO STOCK"
				+ "(stockId,stockName,buyerName,companyName,NoOfStocks) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(SQL_GET_ALL, new Object[] { stock.getStockId(), stock.getStockName(), stock.getBuyerName(),
				stock.getCompanyName(), stock.getNoOfStocks() });
		List<Stock> stocks = getStocks();
		return stocks;
	}

	public List<Stock> getStocks() {
		logger.debug("Entering into method getStocks");
		String SQL_GET_ALL = "SELECT * FROM STOCK";
		List<Stock> stocks = jdbcTemplate.query(SQL_GET_ALL, new StockMapper());
		return stocks;
	}

	@Override
	public Stock updateStock(int stockId, Stock stock) {
		Stock currstock = getStockById(stockId);
		currstock.setStockId(stock.getStockId());
		currstock.setStockName(stock.getStockName());
		currstock.setBuyerName(stock.getBuyerName());
		currstock.setCompanyName(stock.getCompanyName());
		currstock.setNoOfStocks(stock.getNoOfStocks());
		String SQL = "update Stock set stockName = ? and buyerName= ? and companyName=? and noofstocks =? where stockId = ?";
		// jdbcTemplate.update(SQL, currstock, stockId);
		jdbcTemplate.update(SQL, new Object[] { currstock.getStockId(), currstock.getStockName(),
				currstock.getBuyerName(), currstock.getCompanyName(), currstock.getNoOfStocks() });
		return getStockById(currstock.getStockId());
	}

	@Override
	public Stock getStockById(int stockId) {
		logger.debug("Entering into method getStockByStockName");
		String SQL_GET_ALL = "SELECT * FROM STOCK where stockId=?";
		Stock stock = jdbcTemplate.queryForObject(SQL_GET_ALL, new Object[] { stockId }, new StockMapper());
		return stock;
	}

	@Override
	public List<Stock> getAllStocks() {
		logger.debug("Entering into method getStocks");
		String SQL_GET_ALL = "SELECT * FROM STOCK";
		List<Stock> stocks = jdbcTemplate.query(SQL_GET_ALL, new StockMapper());
		return stocks;
	}

}
