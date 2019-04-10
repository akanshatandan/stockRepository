package com.frm.stock.stockdata.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frm.stock.stockdata.dao.StockDao;
import com.frm.stock.stockdata.daoImpl.StockDaoImpl;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.service.StockService;

@Service
public class StockServiceImpl implements StockService{

	private Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);

	@Autowired
	private StockDao stockDao;
	
	public StockServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Stock> getStockByStockName(String stockName) {
		logger.debug("Entering into method getStockByStockName");
		return stockDao.getStockByStockName(stockName);
	}

	@Override
	public List<Stock> getStock(String stockName, String companyName, String buyerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> createStock(List<Stock> stockList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock updateStock(int stockId, Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

}
