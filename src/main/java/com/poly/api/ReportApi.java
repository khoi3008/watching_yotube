package com.poly.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Report;
import com.poly.service.ReportService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(urlPatterns = "/api/admin/report")
public class ReportApi extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 3063964513352567142L;
    private transient ReportService reportService;

    @Override
    public void init() throws ServletException {
        reportService = new ReportService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Report> reports = reportService.statistic();
        objectMapper.writeValue(resp.getOutputStream(), reports);
    }

}
