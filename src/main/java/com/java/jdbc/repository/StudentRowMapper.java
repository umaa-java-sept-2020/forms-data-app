package com.java.jdbc.repository;

import com.java.jdbc.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper {

    public Student toObject(ResultSet resultSet) throws SQLException
    {
         Student student = new Student();
         String fullName = resultSet.getString("FULL_NAME");
         String uuid = resultSet.getString("UUID");
         Integer id = resultSet.getInt("ID");
         Integer age = resultSet.getInt("AGE");

         student.setUuid(uuid);
         student.setFullName(fullName);
         student.setAge(age);
         student.setId(id);
         return student;
    }
}
