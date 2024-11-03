package dao;

import entity.assignment.Plan;
import entity.assignment.PlanCampaign;
import entity.assignment.Product;
import java.sql.*;
import java.util.ArrayList;

public class PlanCampaignDBContext extends DBContext {

    public ArrayList<PlanCampaign> list() {
        ArrayList<PlanCampaign> planCampaigns = new ArrayList<>();
        try {
            String sql = "SELECT pc.*, p.ProductName, pl.PlanName FROM PlanCampaign pc "
                    + "INNER JOIN Product p ON pc.ProductID = p.ProductID "
                    + "INNER JOIN Plan pl ON pc.PlanID = pl.PlanID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlanCampaign pc = new PlanCampaign();
                pc.setId(rs.getInt("PlanCampaignID"));
                pc.setQuantity(rs.getInt("Quantity"));
                pc.setCost(rs.getFloat("Cost"));

                Product product = new Product();
                product.setId(rs.getInt("ProductID"));
                product.setName(rs.getString("ProductName"));
                pc.setProduct(product);

                Plan plan = new Plan();
                plan.setId(rs.getInt("PlanID"));
                plan.setName(rs.getString("PlanName"));
                pc.setPlan(plan);

                planCampaigns.add(pc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return planCampaigns;
    }

}
