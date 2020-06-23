package com.hanshin.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my3")
public class myforward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num1 = 3;
        int num2 = 5;
        req.setAttribute("num1",num1);
        req.setAttribute("num2",num2);
        RequestDispatcher rd = req.getRequestDispatcher("myjspexample5.jsp");
        rd.forward(req, resp);
    }

}