package com.poly.controller.admin;

import com.poly.entity.User;
import com.poly.service.UserService;
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

@WebServlet(urlPatterns = "/admin/users")
public class UserController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6059419205172657974L;
    private transient UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = XForm.getInt("page", 1);
        int pageSize = XForm.getInt("pagesize", 8);
        int totalVideo = userService.getTotalUser();
        int totalPage = XNumber.roundUp((double) totalVideo / pageSize);
        List<User> users = userService.findWithPagination(page, pageSize);
        req.setAttribute("users", users);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("pagesize", pageSize);
        req.getRequestDispatcher("/views/admin/user.jsp").forward(req, resp);
    }


}
