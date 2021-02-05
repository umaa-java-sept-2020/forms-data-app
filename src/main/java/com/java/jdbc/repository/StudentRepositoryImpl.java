package com.java.jdbc.repository;

import com.java.jdbc.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// no complex logic inside repostory layer
public class StudentRepositoryImpl implements IStudentRepository {

    private static String INSERT_STUDENT = "INSERT INTO TBL_STUDENT(UUID, FULL_NAME, AGE) VALUES(?,?,?)";
    private static String UPDATE_STUDENT_BY_ID = "UPDATE TBL_STUDENT SET FULL_NAME=? WHERE ID = ?";
    private static String SELECT_BY_ID = "SELECT * FROM TBL_STUDENT WHERE ID=?";
    private static String DELETE_BY_ID = "DELETE FROM TBL_STUDENT WHERE ID=?";
    private static String SELECT_ALL = "SELECT * FROM TBL_STUDENT";

    @Override
    public int save(Student student, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        preparedStatement.setString(1, student.getUuid());
        preparedStatement.setString(2, student.getFullName());
        preparedStatement.setInt(3, student.getAge());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(String fullName, String uuid, Connection connection) {
        return 0;
    }

    @Override
    public int update(String fullName, Integer id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_BY_ID);
        preparedStatement.setString(1, fullName);
        preparedStatement.setInt(2, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public Student selectById(String uuid, Connection connection) {
        return null;
    }

    @Override
    public Student selectById(Integer id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            StudentRowMapper studentRowMapper = new StudentRowMapper();
            Student student = studentRowMapper.toObject(resultSet);
            return student;
        }
        return null;
    }

    @Override
    public List<Student> selectAll(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Student> list = new ArrayList<>();
        while (resultSet.next()) {
            StudentRowMapper studentRowMapper = new StudentRowMapper();
            Student student = studentRowMapper.toObject(resultSet);
            list.add(student);
        }
        return list;
    }

    @Override
    public int delete(Integer id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }
}
