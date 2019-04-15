package com.frm.stock.stockdata.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.frm.stock.stockdata.dao.StockDao;
import com.frm.stock.stockdata.daoImpl.StockDaoImpl;
import com.frm.stock.stockdata.exception.StockException;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.service.StockService;

@Service
@CacheConfig(cacheNames = "stocks")
public class StockServiceImpl implements StockService {

	private Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);

	@Autowired
	private StockDao stockDao;

	public StockServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Cacheable(value = "StockServiceCache", key = "#stockName")
	@Override
	public Stock getStockByStockName(String stockName) throws StockException {
		logger.debug("Entering into method getStockByStockName");
		return stockDao.getStockByStockName(stockName);
	}

	@Cacheable(value = "StockServiceCache", key = "#companyName")
	@Override
	public Stock getStock(String stockName, String companyName, String buyerName) throws StockException {
		logger.debug("Entering into method getStock");
		return stockDao.getStock(stockName, companyName, buyerName);
	}

	@Cacheable(value = "StockServiceCache", key = "#stock")
	@Override
	public List<Stock> createStock(Stock stock) throws StockException {
		logger.debug("Entering into method createStock");
		return stockDao.createStock(stock);
	}

	@CacheEvict(value = "StockServiceCache", key = "#stockId")
	@Override
	public Stock updateStock(int stockId, Stock stock) throws StockException {
		logger.debug("Entering into method updateStock");
		return stockDao.updateStock(stockId, stock);
	}

}
