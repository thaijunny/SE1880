package dao;

import entity.Department;
import entity.assignment.Plan;
import entity.assignment.PlanCampaign;
import java.sql.*;
import java.util.ArrayList;

public class PlanDBContext extends DBContext {

    public void insert(Plan plan) {
        try {
            connection.setAutoCommit(false);

            // Insert into Plan table
            String sql_plan = "INSERT INTO Plan (PlanName, StartDate, EndDate, DepartmentID) VALUES (?, ?, ?, ?)";
            PreparedStatement ps_plan = connection.prepareStatement(sql_plan, Statement.RETURN_GENERATED_KEYS);
            ps_plan.setString(1, plan.getName());
            ps_plan.setDate(2, plan.getStart());
            ps_plan.setDate(3, plan.getEnd());
            ps_plan.setInt(4, plan.getDepartment().getId());
            ps_plan.executeUpdate();

            ResultSet rs_plan = ps_plan.getGeneratedKeys();
            if (rs_plan.next()) {
                plan.setId(rs_plan.getInt(1));
            }

            // Insert into PlanCampaign table
            String sql_campaign = "INSERT INTO PlanCampain (PlanID, ProductID, Quantity, Cost) VALUES (?, ?, ?, ?)";
            PreparedStatement ps_campaign = connection.prepareStatement(sql_campaign);
            for (PlanCampaign pc : plan.getCampaigns()) {
                ps_campaign.setInt(1, plan.getId());
                ps_campaign.setInt(2, pc.getProduct().getId());
                ps_campaign.setInt(3, pc.getQuantity());
                ps_campaign.setFloat(4, pc.getCost());
                ps_campaign.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        try {
            String sql = "SELECT p.*, d.DepartmentName FROM Plan p INNER JOIN Department d ON p.DepartmentID = d.DepartmentID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("PlanID"));
                plan.setName(rs.getString("PlanName"));
                plan.setStart(rs.getDate("StartDate"));
                plan.setEnd(rs.getDate("EndDate"));

                Department dept = new Department();
                dept.setId(rs.getInt("DepartmentID"));
                dept.setName(rs.getString("DepartmentName"));
                plan.setDepartment(dept);

                plans.add(plan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return plans;
    }
}
