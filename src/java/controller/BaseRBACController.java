package controller;

import dao.UserDBContext;
import entity.Feature;
import entity.Role;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Base controller to enforce role-based access control.
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    private boolean isAuthorized(HttpServletRequest req, User user) {
        String currentUrl = req.getServletPath();
        UserDBContext db = new UserDBContext();
        ArrayList<Role> roles = db.getRoles(user.getUsername());
        user.setRoles(roles);

        for (Role role : roles) {
            for (Feature feature : role.getFeatures()) {
                if (feature.getUrl().equals(currentUrl)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        if (isAuthorized(req, user)) {
            doAuthorizedGet(req, resp, user);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this feature.");
        }
    }

    protected abstract void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        if (isAuthorized(req, user)) {
            doAuthorizedPost(req, resp, user);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this feature.");
        }
    }

    protected abstract void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;
}
