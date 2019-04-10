package com.frm.stock.stockdata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="STOCK")
@NamedQuery(
	    name="findAllStockByName",
	    query="SELECT OBJECT(stc) FROM Stock stc WHERE stc.stockName = :stockName"
	)
public class Stock implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8562729756462832552L;

	@Column(name="STOCKID")
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "stock_id")
    @SequenceGenerator(name = "stock_id", sequenceName = "STOCKID")
	private int stockId;

	@Column(name="COMPANYNAME")
	private String companyName;

	@Column(name="BUYERNAME")
	private String buyerName;

	@Column(name="NOOFSTOCKS")
	private int noOfStocks;

	@Column(name="STOCKNAME")
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

}
