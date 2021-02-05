package com.java.jdbc.repository;

import com.java.jdbc.model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IStudentRepository {

    int save(Student student, Connection connection) throws SQLException;

    int update(String fullName, String uuid, Connection connection);

    int update(String fullName, Integer id, Connection connection) throws SQLException;

    Student selectById(String uuid, Connection connection);

    Student selectById(Integer id, Connection connection) throws SQLException;

    List<Student> selectAll(Connection connection) throws SQLException;

    int delete(Integer id, Connection connection) throws SQLException;
}
