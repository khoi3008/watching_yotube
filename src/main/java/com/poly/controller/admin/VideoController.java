package com.poly.controller.admin;

import com.poly.entity.Video;
import com.poly.service.VideoService;
import com.poly.utils.XForm;
import com.poly.utils.XNumber;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/admin/videos")
public class VideoController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 3968414433292121855L;
    private transient VideoService videoService;

    @Override
    public void init() throws ServletException {
        videoService = new VideoService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = XForm.getInt("page", 1);
        int pageSize = XForm.getInt("pagesize", 8);
        int totalVideo = videoService.getTotalVideo();
        int totalPage = XNumber.roundUp((double) totalVideo / pageSize);
        List<Video> videos = videoService.findWithPagination(page, pageSize);
        req.setAttribute("videos", videos);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("pagesize", pageSize);
        req.getRequestDispatcher("/views/admin/videos.jsp").forward(req, resp);
    }

}
