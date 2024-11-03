package dao;

import entity.Department;
import java.sql.*;
import java.util.ArrayList;

public class DepartmentDBContext extends DBContext {

    public ArrayList<Department> list() {
        ArrayList<Department> departments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Department";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                departments.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }
}
