package repository;

import config.MysqlConfig;
import model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobRepository {
    public List<Job> findAllJob() {
        List<Job> jobList = new ArrayList<>();

        Connection connection = null;
        try {
            String sql = "SELECT * from jobs";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getInt("id"));
                job.setName(resultSet.getString("name"));
                job.setStart(resultSet.getDate("start_date"));
                job.setEnd(resultSet.getDate("end_date"));

                jobList.add(job);
            }


        } catch (Exception e) {
            System.out.println("Error FindAllJob:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối FindAllJob " + e.getMessage());
                }
            }
        }

        return jobList;
    }

    public boolean insertJob(String name, Date start, Date end) {
        boolean isSuccess = false;
        Connection connection = null;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "insert into jobs(name, start_date, end_date) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            java.sql.Date start_date = new java.sql.Date(start.getTime());
            statement.setDate(2, start_date);
            java.sql.Date end_date = new java.sql.Date(end.getTime());
            statement.setDate(3, end_date);

            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error insertJob:  " + e.getMessage());
        } finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối insertJob " + e.getMessage());
                }
            }
        }

        return isSuccess;

    }
}
