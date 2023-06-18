package controller;

import model.Role;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "rolecontroller", urlPatterns = {"/role", "/role/add"})
public class RoleController extends HttpServlet {
    RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/role":
                getAllRole(req, resp);
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/role":
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            default:
                break;
        }
    }

    private void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> list = roleService.findAllRole();

        req.setAttribute("listrole", list);
        req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        if(method.equalsIgnoreCase("post")) {
            String name = req.getParameter("name");
            String desc = req.getParameter("desc");

            roleService.insertRole(name, desc);
        }

        req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
    }
}
