package com.poly.controller.web;

import com.poly.constatnt.Constant;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.FavoriteService;
import com.poly.service.VideoService;
import com.poly.utils.XForm;
import com.poly.utils.XNumber;
import com.poly.utils.XScope;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/favorite")
public class FavoriteController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6130741550333467712L;
    private transient VideoService videoService;
    private transient FavoriteService favoriteService;

    @Override
    public void init() throws ServletException {
        videoService = new VideoService();
        favoriteService = new FavoriteService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User us = XScope.getSession(Constant.USER);
        int page = XForm.getInt("page", 1);
        int pageSize = XForm.getInt("pagesize", 8);
        int totalVideo = favoriteService.getTotalFavoriteVideo(us.getId());
        int totalPage = XNumber.roundUp((double) totalVideo / pageSize);
        List<Video> videos = videoService.findFavoriteVideoByUser(us.getId(), page, pageSize);
        req.setAttribute("videos", videos);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("pagesize", pageSize);
        req.getRequestDispatcher("/views/web/favorite.jsp").forward(req, resp);
    }

}
