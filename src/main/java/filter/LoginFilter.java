package filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/login/*")
public class LoginFilter implements Filter {

    private UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = userService.getUserByNameAndPassword(name, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("role", user.getRole());
            if (user.getRole().equals("admin")) {
                req.getRequestDispatcher("/admin/AllUsers").forward(servletRequest, servletResponse);
            } else if (user.getRole().equals("user")) {
                req.getRequestDispatcher("/user/AccountUser").forward(servletRequest, servletResponse);
            }
        } else {
            req.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
