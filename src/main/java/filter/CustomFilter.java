package filter;


import model.UserModel;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/login"})
public class CustomFilter implements Filter {
    private UserService userService = new UserService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       // Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("kich hoat fitlerrrr");
        String email = "";
        String a = "";
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        email = (String) session.getAttribute("email");
        if(email == null || email.equals("")) {
            System.out.println("fitlerrrr null");
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            //List<UserModel> list = userService.getAllUser();

            //request.setAttribute("listUser", list);
            request.getRequestDispatcher( "index.jsp").forward(request,response);
        }

    }

    @Override
    public void destroy() {
       // Filter.super.destroy();
    }
}
