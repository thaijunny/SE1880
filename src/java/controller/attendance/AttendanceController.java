package controller.attendance;

import controller.BaseRBACController;
import dao.ScheduleEmployeeDBContext;
import dao.AttendanceDBContext;
import entity.Attendance;
import entity.ScheduleEmployee;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class AttendanceController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account)
            throws ServletException, IOException {
        ScheduleEmployeeDBContext dbScheduleEmployee = new ScheduleEmployeeDBContext();
        ArrayList<ScheduleEmployee> scheduleEmployees = dbScheduleEmployee.list();
        req.setAttribute("scheduleEmployees", scheduleEmployees);
        req.getRequestDispatcher("/view/attendance/attendanceForm.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account)
            throws ServletException, IOException {
        int scheduleEmployeeId = Integer.parseInt(req.getParameter("scheduleEmployeeId"));
        Date attendanceDate = Date.valueOf(req.getParameter("attendanceDate"));
        boolean present = Boolean.parseBoolean(req.getParameter("present"));

        ScheduleEmployee se = new ScheduleEmployee();
        se.setId(scheduleEmployeeId);

        Attendance attendance = new Attendance();
        attendance.setScheduleEmployee(se);
        attendance.setAttendanceDate(attendanceDate);
        attendance.setPresent(present);

        AttendanceDBContext dbAttendance = new AttendanceDBContext();
        dbAttendance.insert(attendance);

        resp.sendRedirect("list"); // Redirect to attendance list or confirmation page
    }
}
