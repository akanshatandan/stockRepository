DROP TABLE IF EXISTS stock;

Create Table stock
(
StockId        BIGINT NOT NULL AUTO_INCREMENT primary key,
StockName      VARCHAR(512),
BuyerName      VARCHAR(512), 
CompanyName    VARCHAR(200),
NoOfStocks     BIGINT NOT NULL,
RemainingStocks  BIGINT NOT NULL,
BuyedStocks      BIGINT NOT NULL             
);
