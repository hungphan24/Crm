package repository;

import config.MysqlConfig;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<Role> findAllRole() {
        Connection connection = null;
        List<Role> list = new ArrayList<>();
        try {
            String sql = "select * from roles";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Role role = new Role();

                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));

                list.add(role);
            }
        } catch (Exception e) {
            System.out.println("Error FindAllRole:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindAllRole " + e.getMessage());
                }
            }
        }
        return list;
    }

    public boolean insertRole(String name, String desc) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "insert into roles(name,description) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2, desc);

            isSuccess =   statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error insertRole:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối insertRole " + e.getMessage());
                }
            }
        }

        return isSuccess;
    }

}
