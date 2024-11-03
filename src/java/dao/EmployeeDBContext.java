package dao;

import entity.Department;
import entity.Employee;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDBContext extends DBContext {

    public void insert(Employee e) {
        try {
            String sql = "INSERT INTO Employee (EmployeeName, gender, address, dob, DepartmentID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getName());
            ps.setBoolean(2, e.isGender());
            ps.setString(3, e.getAddress());
            ps.setDate(4, e.getDob());
            ps.setInt(5, e.getDept().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(Employee e) {
        try {
            String sql = "UPDATE Employee SET EmployeeName=?, gender=?, address=?, dob=?, DepartmentID=? WHERE EmployeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, e.getName());
            ps.setBoolean(2, e.isGender());
            ps.setString(3, e.getAddress());
            ps.setDate(4, e.getDob());
            ps.setInt(5, e.getDept().getId());
            ps.setInt(6, e.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Employee e) {
        try {
            String sql = "DELETE FROM Employee WHERE EmployeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Employee get(int id) {
        try {
            String sql = "SELECT e.*, d.DepartmentName FROM Employee e INNER JOIN Department d ON e.DepartmentID = d.DepartmentID WHERE EmployeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("EmployeeID"));
                e.setName(rs.getString("EmployeeName"));
                e.setGender(rs.getBoolean("gender"));
                e.setAddress(rs.getString("address"));
                e.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                e.setDept(d);

                return e;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Employee> list() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT e.*, d.DepartmentName FROM Employee e INNER JOIN Department d ON e.DepartmentID = d.DepartmentID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("EmployeeID"));
                e.setName(rs.getString("EmployeeName"));
                e.setGender(rs.getBoolean("gender"));
                e.setAddress(rs.getString("address"));
                e.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                e.setDept(d);

                employees.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employees;
    }
}
