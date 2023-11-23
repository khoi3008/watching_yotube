package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.IReportDAO;
import com.poly.entity.Report;
import com.poly.utils.JpaUtils;

public class ReportDAO implements IReportDAO {
	@Override
	public List<Report> statistic() {
		String jpql = "Select new Report(o.video.title, count(o), max(o.likeDate), min(o.likeDate))"
				+ "From Favorite o Group By o.video.title";
		return JpaUtils.excuteQuery(jpql, null, JpaUtils.JPQL_QUERY);

	}
}
