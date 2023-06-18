package repository;

import config.MysqlConfig;
import model.Job;
import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<Task> findAllTask() {
        List<Task> taskList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = " SELECT * FROM tasks t join users u ON t.user_id = u.id " +
                    "join jobs j ON t.job_id = j.id " +
                    "join status s ON t.status_id = s.id";

            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("t.id"));
                task.setName(resultSet.getString("t.name"));
                task.setStart(resultSet.getDate("t.start_date"));
                task.setEnd(resultSet.getDate("t.end_date"));
                task.setJob(resultSet.getString("j.name"));
                task.setUser(resultSet.getString("u.fullname"));
                task.setStatus(resultSet.getString("s.name"));

                taskList.add(task);
            }

        } catch (Exception e) {
            System.out.println("Error FindAllTask:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindAllTask " + e.getMessage());
                }
            }
        }

        return taskList;
    }

    public List<Task> FindTaskByUserId(int id) {
        List<Task> taskList = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "SELECT * FROM tasks t join users u ON t.user_id = u.id join jobs j ON t.job_id = j.id join status s ON t.status_id = s.id where t.user_id = ?";

            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("t.id"));
                task.setName(resultSet.getString("t.name"));
                task.setStart(resultSet.getDate("t.start_date"));
                task.setEnd(resultSet.getDate("t.end_date"));
                task.setUser(resultSet.getString("u.fullname"));
                task.setStatus(resultSet.getString("s.name"));
                task.setJob(resultSet.getString("j.name"));
                taskList.add(task);
            }

        } catch (Exception e) {
            System.out.println("Error FindAllTask:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindAllTask " + e.getMessage());
                }
            }
        }

        return taskList;
    }
}
