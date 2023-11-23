package com.poly.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.common.BaseResponse;
import com.poly.entity.Video;
import com.poly.service.VideoService;
import com.poly.utils.XForm;
import com.poly.utils.XHttp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(urlPatterns = {"/api/videos", "/api/admin/videos", "/api/justwatch"})
public class VideoApi extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 65514938066301075L;

    private transient VideoService videoService;

    @Override
    public void init() throws ServletException {
        super.init();
        videoService = new VideoService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/admin/videos")) {
            String method = req.getMethod();
            if (method.equalsIgnoreCase("POST")) {
                this.doCreateVideo(req, resp);
            } else if (method.equalsIgnoreCase("PUT")) {
                this.doUpdateVideo(req, resp);
            } else if (method.equalsIgnoreCase("DELETE")) {
                this.doDeleteVideo(resp);
            }
        } else if (uri.contains("/videos")) {
            this.doGetVideos(resp);
        } else if (uri.contains("/justwatch")) {
            this.doGetJustWatchVideo(req, resp);
        }
    }

    private void doGetJustWatchVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] ids = XHttp.of(req.getReader()).toEntity(String[].class);
        List<Video> videos = videoService.getJustWatchVideo(ids);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), videos);
    }

    protected void doGetVideos(HttpServletResponse resp) throws IOException {
        String vid = XForm.getString("id", null);
        String listAll = XForm.getString("list", "");
        ObjectMapper objectMapper = new ObjectMapper();
        if (vid != null) {
            Video video = videoService.findById(vid);
            objectMapper.writeValue(resp.getOutputStream(), video);
        } else if (listAll.equals("1")) {
            List<Video> videos = videoService.findAll();
            objectMapper.writeValue(resp.getOutputStream(), videos);
        } else {
            int page = XForm.getInt("page", 1);
            int pageSize = XForm.getInt("pagesize", 10);
            List<Video> list = videoService.findWithPagination(page, pageSize);
            objectMapper.writeValue(resp.getOutputStream(), list);
        }

    }


    protected void doDeleteVideo(HttpServletResponse resp)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String id = XForm.getString("id", null);
        if (id != null) {
            videoService.delete(id);
        }
        BaseResponse baseResponse = new BaseResponse(id == null ? "Delete failed" : "Delete Success", id == null);
        objectMapper.writeValue(resp.getOutputStream(), baseResponse);
    }

    protected void doUpdateVideo(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Video video = XHttp.of(req.getReader()).toEntity(Video.class);
        Video respVideo = videoService.update(video);
        BaseResponse baseResponse = new BaseResponse(respVideo != null ? "Update Success" : "Update Failed",
                respVideo != null);
        objectMapper.writeValue(resp.getOutputStream(), baseResponse);
    }

    protected void doCreateVideo(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Video video = XHttp.of(req.getReader()).toEntity(Video.class);
        Video respVideo = videoService.insert(video);
        BaseResponse baseResponse = new BaseResponse(respVideo != null ? "Success" : "VideoId is exist",
                respVideo != null);
        objectMapper.writeValue(resp.getOutputStream(), baseResponse);
    }

}
