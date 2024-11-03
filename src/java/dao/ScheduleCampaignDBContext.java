package dao;

import entity.ScheduleCampaign;
import java.sql.*;

public class ScheduleCampaignDBContext extends DBContext {

    public void insert(ScheduleCampaign sc) {
        try {
            String sql = "INSERT INTO ScheduleCampaign (PlanCampaignID, ScDate, Shift, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, sc.getPlanCampaign().getId());
            ps.setDate(2, sc.getDate());
            ps.setInt(3, sc.getShift());
            ps.setInt(4, sc.getQuantity());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                sc.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
