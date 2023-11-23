package com.poly.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.common.BaseResponse;
import com.poly.constatnt.Constant;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.FavoriteService;
import com.poly.service.ReportService;
import com.poly.utils.XHttp;
import com.poly.utils.XScope;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = {"/api/favorite/like", "/api/favorite/unlike"})
public class FavoriteApi extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5237906382938398411L;
    private transient ObjectMapper objectMapper;
    private transient FavoriteService favoriteService;
    private transient ReportService reportService;

    @Override
    public void init() throws ServletException {
        objectMapper = new ObjectMapper();
        favoriteService = new FavoriteService();
        reportService = new ReportService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User us = XScope.getSession(Constant.USER);

        String uri = req.getRequestURI();
        Video video = XHttp.of(req.getReader()).toEntity(Video.class);
        if (video != null) {
            if (uri.contains("/like")) {
                this.doLike(resp, us, video);
            } else if (uri.contains("/unlike")) {
                this.doUnLike(resp, us, video);
            } else {
                BaseResponse baseResponse = new BaseResponse("Video not found!", false);
                this.objectMapper.writeValue(resp.getOutputStream(), baseResponse);
            }
        }
    }

    public void doLike(HttpServletResponse resp, User us, Video video)
            throws IOException {
        favoriteService.like(us, video);
        BaseResponse baseResponse = new BaseResponse("Success!", true);
        this.objectMapper.writeValue(resp.getOutputStream(), baseResponse);
    }

    public void doUnLike(HttpServletResponse resp, User us, Video video)
            throws IOException {
        favoriteService.unlike(us, video);
        BaseResponse baseResponse = new BaseResponse("Success!", true);
        this.objectMapper.writeValue(resp.getOutputStream(), baseResponse);
    }

}
