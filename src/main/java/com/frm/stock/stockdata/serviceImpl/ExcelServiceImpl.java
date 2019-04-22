package com.frm.stock.stockdata.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frm.stock.stockdata.dao.ExcelDao;
import com.frm.stock.stockdata.model.Stock;
import com.frm.stock.stockdata.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService{

	@Autowired
	private ExcelDao excelDao;
	
	@Override
	public List<Stock> getStocks() {
		return excelDao.getStocks();
	}

	@Override
	public void insertIntoDB(List<Stock> list) {
		excelDao.insertIntoDB(list);	
	}

}
