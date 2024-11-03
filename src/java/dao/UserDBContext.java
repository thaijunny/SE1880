package dao;

import entity.Role;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDBContext extends DBContext {

    public User get(String username, String password) {
        try {
            String sql = "SELECT * FROM [User] WHERE [UserName]=? AND [password]=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Role> getRoles(String username) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.* FROM [Role] r INNER JOIN UserRole ur ON r.RoleID = ur.RoleID WHERE ur.UserName = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("RoleID"));
                role.setName(rs.getString("RoleName"));
                roles.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}
