package controller;

import config.MysqlConfig;
import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController",urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie cookie = new Cookie("username", "nguyenvana");
//        resp.addCookie(cookie);

//        Cookie[] cookies = req.getCookies();
//        for(Cookie item : cookies) {
//            if(item.getName().equals("username")) {
//                System.out.println(item.getValue());
//            }
//        }

//        HttpSession session = req.getSession();
//        session.setAttribute("password","123456");
//
//        System.out.println("session: " + session.getAttribute("password"));

//        Cookie[] cookies = req.getCookies();
//        String userName = "";
//        String password = "";
//
//        for(Cookie item : cookies) {
//            if(item.getName().equals("username")) {
//                userName = item.getValue();
//            }
//            if(item.getName().equals("password")) {
//                password = item.getValue();
//            }
//        }
//
//        req.setAttribute("username",userName);
//        req.setAttribute("password", password);

        System.out.println("doget login");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        Integer[] id = new Integer[1];
        id[0] = -2;

        boolean isSuccess = loginService.checkLogin(email, password, id);

        if(isSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute("email",email);

            String strId = "" + id[0];
            //System.out.println("abc " + strId);
            session.setAttribute("strId", strId);
            String contextPath = req.getContextPath();
            //resp.sendRedirect("");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("login fail!!");
            printWriter.close();
        }

    }
}
