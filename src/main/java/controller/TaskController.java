package controller;

import model.Task;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "taskcontroller", urlPatterns = "/task")
public class TaskController extends HttpServlet {
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> taskList = taskService.findAllTask();

        req.setAttribute("tasklist", taskList);
        req.getRequestDispatcher("task.jsp").forward(req, resp);
    }
}
