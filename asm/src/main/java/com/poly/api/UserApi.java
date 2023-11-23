package com.poly.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.common.BaseResponse;
import com.poly.constatnt.Constant;
import com.poly.dtos.FavoriteUserDto;
import com.poly.dtos.ShareUserDto;
import com.poly.entity.User;
import com.poly.service.UserService;
import com.poly.utils.XForm;
import com.poly.utils.XHttp;
import com.poly.utils.XScope;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(urlPatterns = {"/api/admin/favorite-user", "/api/admin/share-user", "/api/admin/user", "/api/update-user",
        "/api/admin/delete-user", "/api/user-data"})
public class UserApi extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 4875178134474428513L;
    private transient ObjectMapper objectMapper;
    private transient UserService userService;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("favorite-user")) {
            this.doGetFavoriteUser(resp);
        } else if (uri.contains("share-user")) {
            this.doGetShareUser(resp);
        } else if (uri.contains("update-user")) {
            this.doUpdateUser(req, resp);
        } else if (uri.contains("delete-user")) {
            this.doDeleteUser(resp);
        } else if (uri.contains("admin/user")) {
            this.doGetUser(resp);
        } else if (uri.contains("user-data")) {
            this.doGetUserData(resp);
        }
    }

    private void doGetUserData(HttpServletResponse resp) throws IOException {
        User us = XScope.getSession(Constant.USER);
        this.objectMapper.writeValue(resp.getOutputStream(), us);
    }

    private void doDeleteUser(HttpServletResponse resp)
            throws IOException {
        String id = XForm.getString("id", null);
        if (id != null) {
            boolean isSuccess = userService.delete(id);
            BaseResponse baseResponse;
            if (isSuccess) {
                baseResponse = new BaseResponse("Success!", true);
            } else {
                baseResponse = new BaseResponse("Delete Failed!", false);
            }
            this.objectMapper.writeValue(resp.getOutputStream(), baseResponse);
        } else {
            BaseResponse baseResponse = new BaseResponse("User not found!", false);
            this.objectMapper.writeValue(resp.getOutputStream(), baseResponse);
        }
    }

    private void doUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User us = XHttp.of(req.getReader()).toEntity(User.class);
        User loginUser = XScope.getSession("user");
        String message = userService.updateUser(us, loginUser);
        boolean isSuccess = message.length() == 0;
        BaseResponse baseResponse = new BaseResponse(isSuccess ? "Update success!" : message, message.length() == 0);
        this.objectMapper.writeValue(resp.getOutputStream(), baseResponse);
    }

    private void doGetUser(HttpServletResponse resp)
            throws IOException {
        String userId = XForm.getString("id", null);
        if (userId != null) {
            User us = userService.findById(userId);
            this.objectMapper.writeValue(resp.getOutputStream(), us);
        } else {
            this.objectMapper.writeValue(resp.getOutputStream(), null);
        }

    }

    private void doGetShareUser(HttpServletResponse resp) throws IOException {
        String videoId = XForm.getString("id", null);
        if (videoId != null) {
            List<ShareUserDto> data = userService.findAllShareUser(videoId);
            this.objectMapper.writeValue(resp.getOutputStream(), data);
        }

    }

    private void doGetFavoriteUser(HttpServletResponse resp)
            throws IOException {
        String videoId = XForm.getString("id", null);
        if (videoId != null) {
            List<FavoriteUserDto> data = userService.findAllFavoriteUser(videoId);
            this.objectMapper.writeValue(resp.getOutputStream(), data);
        }
    }

}
