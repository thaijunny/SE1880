package controller.productionplan;

import controller.BaseRBACController;
import dao.DepartmentDBContext;
import dao.PlanDBContext;
import dao.ProductDBContext;
import entity.Department;
import entity.User;
import entity.assignment.Plan;
import entity.assignment.PlanCampaign;
import entity.assignment.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


@WebServlet(name = "ProductionPlanCreateController", urlPatterns = {"/create-plan"})
public class ProductionPlanCreateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();
        ArrayList<Department> departments = dbDept.list();
        ArrayList<Product> products = dbProduct.list();

        req.setAttribute("departments", departments);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/view/productionplan/createPlan.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        int departmentId = Integer.parseInt(req.getParameter("departmentId"));

        Plan plan = new Plan();
        plan.setName(name);
        plan.setStart(startDate);
        plan.setEnd(endDate);

        Department dept = new Department();
        dept.setId(departmentId);
        plan.setDepartment(dept);

        String[] productIds = req.getParameterValues("productId");
        if (productIds != null) {
            for (String pid : productIds) {
                int productId = Integer.parseInt(pid);
                int quantity = Integer.parseInt(req.getParameter("quantity" + pid));
                float estimate = Float.parseFloat(req.getParameter("estimate" + pid));

                PlanCampaign pc = new PlanCampaign();
                pc.setPlan(plan);

                Product product = new Product();
                product.setId(productId);
                pc.setProduct(product);

                pc.setQuantity(quantity);
                pc.setCost(estimate);

                plan.getCampaigns().add(pc);
            }
        }

        PlanDBContext dbPlan = new PlanDBContext();
        dbPlan.insert(plan);

        resp.sendRedirect("list");
    }
}
