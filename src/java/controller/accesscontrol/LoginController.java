package controller.accesscontrol;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        UserDBContext db = new UserDBContext();
        User account = db.get(user, pass);

        if (account != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "Invalid username or password.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
