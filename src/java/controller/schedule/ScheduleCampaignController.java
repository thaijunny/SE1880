package controller.schedule;

import controller.BaseRBACController;
import dao.PlanCampaignDBContext;
import dao.ScheduleCampaignDBContext;
import entity.ScheduleCampaign;
import entity.User;
import entity.assignment.PlanCampaign;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


@WebServlet(name = "ScheduleCampaignController", urlPatterns = {"/ScheduleCampaignController"})
public class ScheduleCampaignController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        PlanCampaignDBContext dbPlanCampaign = new PlanCampaignDBContext();
        ArrayList<PlanCampaign> planCampaigns = dbPlanCampaign.list();
        req.setAttribute("planCampaigns", planCampaigns);
        req.getRequestDispatcher("/view/schedule/createSchedule.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        int planCampaignId = Integer.parseInt(req.getParameter("planCampaignId"));
        Date date = Date.valueOf(req.getParameter("date"));
        int shift = Integer.parseInt(req.getParameter("shift"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        ScheduleCampaign sc = new ScheduleCampaign();
        PlanCampaign pc = new PlanCampaign();
        pc.setId(planCampaignId);
        sc.setPlanCampaign(pc);
        sc.setDate(date);
        sc.setShift(shift);
        sc.setQuantity(quantity);

        ScheduleCampaignDBContext dbScheduleCampaign = new ScheduleCampaignDBContext();
        dbScheduleCampaign.insert(sc);

        resp.sendRedirect("list");
    }
}
