package controller;

import model.Job;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "joncontroller", urlPatterns = {"/job","/job/add"})
public class JobController extends HttpServlet {
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/job":
                getAllJob(req, resp);
                break;
            case "/job/add":
                try {
                    insertJob(req, resp);
                } catch (ParseException e) {

                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/job":
                break;
            case "/job/add":
                try {
                    insertJob(req, resp);
                } catch (ParseException e) {

                }
                break;
            default:
                break;
        }
    }

    private void getAllJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Job> jobList = jobService.findAllJob();

        req.setAttribute("listjob", jobList);
        req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
    }

    private void insertJob(HttpServletRequest req, HttpServletResponse resp) throws ParseException, ServletException, IOException {
        String method = req.getMethod();

        if(method.equalsIgnoreCase("post")) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String name = req.getParameter("name");
            String _start = req.getParameter("start");
            Date start = formatter.parse(_start);
            String _end = req.getParameter("end");
            Date end = formatter.parse(_end);

            jobService.insertJob(name, start, end);
        }

        req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);

    }
}
