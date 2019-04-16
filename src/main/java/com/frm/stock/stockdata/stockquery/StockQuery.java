package com.frm.stock.stockdata.stockquery;

public class StockQuery {
	public static final String SQL_CREATE = "INSERT INTO STOCK"
			+ "(stockId,stockName,buyerName,companyName,noOfStocks) VALUES (:stockId,:stockName,:buyerName,:companyName,:noOfStocks)";

	public static final String SQL_GET_BY_NAME = "SELECT * FROM STOCK where stockName=:stockName";

	public static final String SQL_GET_BY_ID = "SELECT * FROM STOCK where stockId = :stockId";

	public static final String SQL_GET_ALL = "SELECT * FROM STOCK";

	public static final String SQL_GET_STOCK = "SELECT * FROM STOCK where stockName=:stockName OR buyerName= :buyerName OR companyName=:companyName";

	public static final String SQL_UPDATE = "UPDATE Stock SET stockName = :stockName , buyerName= :buyerName , companyName=:companyName , noOfStocks =:noOfStocks where stockId = :stockId";
}
