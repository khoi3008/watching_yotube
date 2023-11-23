package com.poly.service;

import java.util.List;

import com.poly.dao.impl.ReportDAO;
import com.poly.entity.Report;

public class ReportService {
	ReportDAO reportDAO = new ReportDAO();
	
	public List<Report> statistic(){
		return reportDAO.statistic();
	}
}
