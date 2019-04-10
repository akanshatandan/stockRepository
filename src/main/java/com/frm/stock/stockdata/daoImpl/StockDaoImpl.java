package com.frm.stock.stockdata.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.frm.stock.stockdata.dao.StockDao;
import com.frm.stock.stockdata.model.Stock;

@Repository
public class StockDaoImpl implements StockDao{
	
	private Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);
 
	@PersistenceContext
    public EntityManager em;
	
	public StockDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Stock> getStockByStockName(String stockName) {
		logger.debug("Entering into method getStockByStockName");
		@SuppressWarnings("unchecked")
		List<Stock> stockList = em.createNamedQuery("findAllStockByName")
		            .setParameter("stockName", "Smith")
		            .getResultList();
	   /* Stock s1 = new Stock();
	    s1.setStockId(1);
	    s1.setStockName("NSE");
	    s1.setCompanyName("Infy");
	    s1.setBuyerName("Arun");
	    s1.setNoOfStocks(100);
	    stockList.add(s1);*/
		return stockList;
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
