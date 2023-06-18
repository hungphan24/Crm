package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile"})
public class UserFiler implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ids = "";
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        ids = (String) session.getAttribute("strId");
        System.out.println(ids);
        if(ids == null || ids.equals("")) {
            //System.out.println("fitlerrrr null");
            request.getRequestDispatcher("login.jsp").forward(request,response);

        } else {
            //List<UserModel> list = userService.getAllUser();

            request.setAttribute("ids", ids);
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
