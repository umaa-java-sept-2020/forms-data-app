package com.java.jdbc;

import com.java.jdbc.model.Student;
import com.java.jdbc.repository.IStudentRepository;
import com.java.jdbc.repository.StudentRepositoryImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Hello world!
 * Connection Pooling in JDBC/Database.
 * Scrollable ResultSet.
 * Pagination With JDBC.
 * What is transaction. ACID properties.
 * ISOLATION & PROPAGATION levels of transaction.
 * SQL Injection.
 * <p>
 * How to access auto generated primary key in JDBC? must try.
 *
 * Statement vs PreparedStatement
 * How JDBC works.
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        //   saveStudent();

        //     updateStudentFullName();

      //  selectStudentById();

        selectAllStudents();

       // deleteById();

    }


    private static void saveStudentCommitRollback() throws Exception {
        Connection connection = getConnection(); // 3
        Student student = new Student();
        student.setFullName("Abhishek");
        student.setAge(23);
        student.setUuid(UUID.randomUUID().toString());

        IStudentRepository studentRepository = new StudentRepositoryImpl();
        int rows = 0;
        try {
            rows = studentRepository.save(student, connection);
            //  courseRepository.save(course, connection);
            connection.commit(); // making changes to db: insert, update, delete
        } catch (SQLException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        } finally {
            connection.close(); // this is mandatory for all operations with database
        }
        System.out.println(rows);

    }

    private static void saveStudent() throws Exception {
        Connection connection = getConnection(); // 3
        Student student = new Student();
        student.setFullName("Abhishek");
        student.setAge(23);
        student.setUuid(UUID.randomUUID().toString());

        IStudentRepository repository = new StudentRepositoryImpl();
        int rows = repository.save(student, connection);

        System.out.println(rows);

        connection.commit(); // making changes to db: insert, update, delete
        connection.close(); // this is mandatory for all operations with database
    }

    private static void updateStudentFullName() throws Exception {
        Integer id = 4;
        String fullName = "Saroj";
        IStudentRepository studentRepository = new StudentRepositoryImpl();
        Connection connection = getConnection();
        int rows = studentRepository.update(fullName, id, connection);
        System.out.println(rows);

        connection.commit();
        connection.close();

    }

    private static void selectStudentById() throws Exception {
        Connection connection = getConnection();
        Integer id = 4;
        IStudentRepository studentRepository = new StudentRepositoryImpl();
        Student student = studentRepository.selectById(id, connection);

        System.out.println(student);
        connection.close();
    }


    private static void selectAllStudents() throws Exception {
        Connection connection = getConnection();
        IStudentRepository studentRepository = new StudentRepositoryImpl();
        List<Student> students = studentRepository.selectAll(connection);

        System.out.println(students);
        connection.close();
    }

    private static void deleteById() throws Exception{
        Connection connection = getConnection();

        Integer id = 4;

        IStudentRepository studentRepository = new StudentRepositoryImpl();
        int rows = studentRepository.delete(id, connection);
        System.out.println(rows);

        connection.commit();
        connection.close();
    }

    /*** JDBC Connection **/
    private static Properties loadProperties() throws IOException {
        String dbConfigFilePath = "db-config.properties";
        InputStream is = App.class.getClassLoader().getResourceAsStream(dbConfigFilePath);
        Properties properties = new Properties();
        properties.load(is);
        return properties;
    }

    private static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = loadProperties();
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String dbUrl = properties.getProperty("db.url");
        String driverClass = properties.getProperty("db.driver-class");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        connection.setAutoCommit(false); // why ? it will help to not to commit partial transaction.
        return connection;
    }
}
