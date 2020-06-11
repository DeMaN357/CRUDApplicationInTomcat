package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/update")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", new User(Long.valueOf(req.getParameter("idToUpdate")), req.getParameter("name"), req.getParameter("password")));
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String oldName = req.getParameter("oldName");
        String oldPassword = req.getParameter("oldPassword");
        userService.updateUser(new User(oldName, oldPassword), new User(name, password));
        resp.sendRedirect(getServletContext().getContextPath() + "/AllUsers");

    }
}
