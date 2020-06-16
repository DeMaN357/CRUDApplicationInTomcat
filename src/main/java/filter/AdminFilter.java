package filter;

import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        RequestDispatcher requestDispatcher;

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null) {
            requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(servletRequest, servletResponse);
        }else if (role.equals("admin")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else if (role.equals("user")){
            requestDispatcher = req.getRequestDispatcher("/user/AccountUser");
            requestDispatcher.forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
