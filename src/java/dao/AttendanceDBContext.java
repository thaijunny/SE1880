package dao;

import entity.Attendance;
import entity.ScheduleEmployee;
import java.sql.*;
import java.util.ArrayList;

public class AttendanceDBContext extends DBContext {

    public void insert(Attendance attendance) {
        try {
            String sql = "INSERT INTO Attendance (ScheduleEmployeeID, AttendanceDate, Present) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, attendance.getScheduleEmployee().getId());
            ps.setDate(2, attendance.getAttendanceDate());
            ps.setBoolean(3, attendance.isPresent());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                attendance.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Additional methods like list(), get(), etc., can be added if needed
}
