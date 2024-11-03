package controller.employee;

import controller.BaseRBACController;
import dao.DepartmentDBContext;
import dao.EmployeeDBContext;
import entity.Department;
import entity.Employee;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
@WebServlet(name = "EmployeeCreateController", urlPatterns = {"/create-employee"})
public class EmployeeCreateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ArrayList<Department> departments = dbDept.list();
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/view/employee/createEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        boolean gender = req.getParameter("gender").equals("male");
        String address = req.getParameter("address");
        Date dob = Date.valueOf(req.getParameter("dob"));
        int departmentId = Integer.parseInt(req.getParameter("departmentId"));

        Employee e = new Employee();
        e.setName(name);
        e.setGender(gender);
        e.setAddress(address);
        e.setDob(dob);

        Department d = new Department();
        d.setId(departmentId);
        e.setDept(d);

        EmployeeDBContext dbEmp = new EmployeeDBContext();
        dbEmp.insert(e);

        resp.sendRedirect("list");
    }
}
