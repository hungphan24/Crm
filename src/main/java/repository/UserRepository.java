package repository;

import config.MysqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<UserModel> findByEmailAndPassword(String email, String password, Integer[] id) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "select * from users u where u.email = ? and u.password = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                id[0] = (resultSet.getInt("id"));

                userModelList.add(userModel);
            }


        } catch (Exception e) {
            System.out.println("Error FindByEmailAndPassWord:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindByEmailAndPassWord " + e.getMessage());
                }
            }
        }

        return userModelList;
    }

    public List<UserModel> findAll() {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "select * from users u";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }


        } catch (Exception e) {
            System.out.println("Error FindAll:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindAll " + e.getMessage());
                }
            }
        }

        return userModelList;
    }

    public boolean insertUser(String fullname, String email, String password, int roleID) {
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "insert into users(email, password, fullname, role_id) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setInt(4,roleID);

            isSucess =  (statement.executeUpdate() > 0);

        } catch (Exception e) {
            System.out.println("Error insertUser:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối insertUser " + e.getMessage());
                }
            }
        }

        return isSucess;
    }

    public boolean deleteById(int id) {
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "delete from users u where u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            isSucess =  (statement.executeUpdate() > 0);

        } catch (Exception e) {
            System.out.println("Error deleteById:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối deleteById " + e.getMessage());
                }
            }
        }

        return isSucess;
    }

    public UserModel FindById(int id) {
        Connection connection = null;
        UserModel userModel = new UserModel();
        try {
            String sql = "select * from users u where u.id = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

            }


        } catch (Exception e) {
            System.out.println("Error FindById:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindById " + e.getMessage());
                }
            }
        }
        return userModel;
    }

}
