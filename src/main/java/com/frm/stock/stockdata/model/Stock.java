package com.frm.stock.stockdata.model;

import java.io.Serializable;

import com.frm.stock.stockdata.utils.WorkBookInterface;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8562729756462832552L;

	@WorkBookInterface
	private int stockId;

	@WorkBookInterface
	private String companyName;

	@WorkBookInterface
	private String buyerName;

	@WorkBookInterface
	private int noOfStocks;

	@WorkBookInterface
	private int buyedStocks;

	@WorkBookInterface
	private int remainingStocks;

	@WorkBookInterface
	private String stockName;

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String companyName, String buyerName, int noOfStocks, String stockName) {
		super();
		this.companyName = companyName;
		this.buyerName = buyerName;
		this.noOfStocks = noOfStocks;
		this.stockName = stockName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public int getNoOfStocks() {
		return noOfStocks;
	}

	public void setNoOfStocks(int noOfStocks) {
		this.noOfStocks = noOfStocks;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return "Stock [companyName=" + companyName + ", buyerName=" + buyerName + ", NoOfStocks=" + noOfStocks
				+ ", stockName=" + stockName + "]";
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public int getBuyedStocks() {
		return buyedStocks;
	}

	public void setBuyedStocks(int buyedStocks) {
		this.buyedStocks = buyedStocks;
	}

	public int getRemainingStocks() {
		return remainingStocks;
	}

	public void setRemainingStocks(int remainingStocks) {
		this.remainingStocks = remainingStocks;
	}

}
