package com.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FormsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);


        displayHeadersAndParams(req);

        PrintWriter writer = resp.getWriter();
        writer.write("get method");
    }


    // http://localhost:8080/app/forms?x=20&y=200&x=230&username=john&password=doe
    // why to use body instead query parameters
    // https://stackoverflow.com/questions/25385559/rest-api-best-practices-args-in-query-string-vs-in-request-body

    // add this header during form submission: application/x-www-form-urlencoded
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);

        displayHeadersAndParams(req);
        String u1 = req.getParameter("username");
        String p1 = req.getParameter("password");

        PrintWriter writer = resp.getWriter();
        if (u1.equals("john") && p1.equals("doe")) {
            writer.write("login success");
        } else {
            writer.write("login failed");
        }


    }

    private void displayHeadersAndParams(HttpServletRequest req) {
        String value = req.getParameter("x");
        getServletContext().log("value in servlet is: " + value);

        value = req.getHeader("x-authorization"); // mostly used to exchange some metadata for authentication.

        getServletContext().log("header value: " + value);
    }

}
