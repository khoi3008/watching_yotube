package com.poly.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.common.BaseResponse;
import com.poly.constatnt.Constant;
import com.poly.dtos.ShareDto;
import com.poly.entity.User;
import com.poly.service.ShareService;
import com.poly.utils.XHttp;
import com.poly.utils.XScope;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = {"/api/share/user"})
public class ShareApi extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 2130707616229684174L;
    private transient ShareService shareService;

    @Override
    public void init() {
        shareService = new ShareService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ShareDto shareDto = XHttp.of(req.getReader()).toEntity(ShareDto.class);
        User us = XScope.getSession(Constant.USER);
        boolean isSuccess = shareService.share(ShareDto.toShare(shareDto, us));
        String message = isSuccess ? "Shared video!" : "Failed!";
        BaseResponse baseResponse = new BaseResponse(message, isSuccess);
        objectMapper.writeValue(resp.getOutputStream(), baseResponse);

    }

}
