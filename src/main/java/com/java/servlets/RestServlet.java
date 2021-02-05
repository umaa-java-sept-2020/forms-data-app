package com.java.servlets;

import com.java.jdbc.model.Student;
import com.java.jdbc.service.StudentDataService;
import com.java.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class RestServlet extends HttpServlet {

    private StudentDataService studentDataService;

    public RestServlet() {
        this.studentDataService = new StudentDataService();
    }

    // http://localhost:8080/app/rest?id=6
    // http://localhost:8080/app/rest
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        String idStr = req.getParameter("id");
        Object object = null;
        try {
            if (idStr == null)
                object = studentDataService.selectAllStudents();
            else {
                Integer id = Integer.valueOf(idStr);
                object = studentDataService.selectStudentById(id);
            }
            resp.setHeader("Content-Type", "application/json");
            String json = JsonUtils.toJson(object);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // create Student
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  super.doPost(req, resp);

        InputStream is = req.getInputStream();
        Student student = JsonUtils.toObject(is, Student.class);
        try {
            student = studentDataService.saveStudent(student);
            resp.setHeader("Content-Type", "application/json");
            String json = JsonUtils.toJson(student);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPut(req, resp);
        String idStr = req.getParameter("id"); // put must have idenitifier to update which record
        Integer id = Integer.valueOf(idStr);
        InputStream is = req.getInputStream();
        Student student = JsonUtils.toObject(is, Student.class);
        student.setId(id);
        try {
            studentDataService.updateStudentFullName(student);

            Map<String,Object> response= new HashMap<>();
            response.put("status",Boolean.TRUE);
            resp.setHeader("Content-Type", "application/json");
            String json = JsonUtils.toJson(response);
            PrintWriter writer = resp.getWriter();
            writer.write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doDelete(req, resp);
        String idStr = req.getParameter("id");
        Integer id = Integer.valueOf(idStr);
        try {
            studentDataService.deleteById(id);

            Map<String,Object> response= new HashMap<>();
            response.put("status",Boolean.TRUE);
            resp.setHeader("Content-Type", "application/json");
            String json = JsonUtils.toJson(response);
            PrintWriter writer = resp.getWriter();
            writer.write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
