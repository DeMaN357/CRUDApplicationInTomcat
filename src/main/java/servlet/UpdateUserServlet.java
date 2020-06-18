package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/update")
public class UpdateUserServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idToUpdate = Long.valueOf(req.getParameter("idToUpdate"));
        req.setAttribute("user", userService.getUserById(idToUpdate));
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long oldId = Long.valueOf(req.getParameter("oldId"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (userService.updateUser(new User(oldId, name, password,role))) {
            resp.sendRedirect("/admin/AllUsers");
        } else {
            resp.getWriter().write("This name already exist!");
        }
    }
}
