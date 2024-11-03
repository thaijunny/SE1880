package dao;

import entity.ScheduleEmployee;
import entity.Employee;
import java.sql.*;
import java.util.ArrayList;

public class ScheduleEmployeeDBContext extends DBContext {

    public ArrayList<ScheduleEmployee> list() {
        ArrayList<ScheduleEmployee> scheduleEmployees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ScheduleEmployee";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScheduleEmployee se = new ScheduleEmployee();
                se.setId(rs.getInt("ScheduleEmployeeID"));
                se.setScheduleDate(rs.getDate("ScheduleDate"));
                se.setShift(rs.getInt("Shift"));
                
                Employee emp = new Employee();
                emp.setId(rs.getInt("EmployeeID"));
                se.setEmployee(emp);

                scheduleEmployees.add(se);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scheduleEmployees;
    }

    // Add additional methods if needed
}
