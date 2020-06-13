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
        Long idToUpdate = Long.valueOf(req.getParameter("idToUpdate"));
        req.setAttribute("user", userService.getUserById(idToUpdate));
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long oldId = Long.valueOf(req.getParameter("oldId"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (userService.updateUser(new User(oldId, name, password))) {
            resp.sendRedirect(getServletContext().getContextPath() + "/AllUsers");
        } else {
            resp.getWriter().write("This name already exist!");
        }

    }
}
