package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*@WebFilter("/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println("я в фильтре");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/AllUser");
        requestDispatcher.forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}*/
