package dao;

import entity.assignment.Product;
import java.sql.*;
import java.util.ArrayList;

public class ProductDBContext extends DBContext {

    public ArrayList<Product> list() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("productID"));
                product.setName(rs.getString("productName"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void insert(Product entity) {
        // Implement insert logic if needed
    }

    public void update(Product entity) {
        // Implement update logic if needed
    }

    public void delete(Product entity) {
        // Implement delete logic if needed
    }

    public Product get(int id) {
        // Implement get logic if needed
        return null;
    }
}
