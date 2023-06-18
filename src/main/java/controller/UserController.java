package controller;

import model.Role;
import model.Task;
import model.UserModel;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "usercontroller", urlPatterns = {"/user", "/user/add", "/user/delete","/user/details", "/profile"})
public class UserController extends HttpServlet {

    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();  // lay duong dan nguoi dung goi tren browser

        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            case "/user/details":
                DetailsUser(req, resp);
                break;
            case "/profile":
                ProfileUser(req, resp);
                break;
            default:

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();  // lay duong dan nguoi dung goi tren browser

        switch (path) {
            case "/user":

                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "user/details":
                break;
            default:

        }
    }

    private void ProfileUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getAttribute("ids"));
        UserModel userModel = userService.FindById(id);
        request.setAttribute("user", userModel);

        List<Task> taskList = userService.FindTaskByUserId(id);

        request.setAttribute("tasklist", taskList);

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private void DetailsUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserModel userModel = userService.FindById(id);
        request.setAttribute("user", userModel);

        List<Task> taskList = userService.FindTaskByUserId(id);

        request.setAttribute("tasklist", taskList);

        request.getRequestDispatcher("/user-details.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteById(id);

    }
    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUser();

        req.setAttribute("listUser", list);
        req.getRequestDispatcher("user-table.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        List<Role> roleList = roleService.findAllRole();

        if(method.equalsIgnoreCase("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleID = Integer.parseInt(req.getParameter("role"));
            //System.out.println(password);
            userService.insertUser(email,password,fullname, roleID);
        }

        req.setAttribute("listRole", roleList);
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }
}
