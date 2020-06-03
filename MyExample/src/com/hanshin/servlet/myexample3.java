package com.hanshin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/my3")
public class myexample3 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost 실행 됨");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String v_ = req.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = req.getParameter("operator");
		
		//ServletContext application = req.getServletContext();
		//HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		int result = 0;
		
		if(op.equals("=")) {
			//int prev = (int) application.getAttribute("value");
			//String prev_op = (String) application.getAttribute("operator");
			
			//int prev = (int) session.getAttribute("value");
			//String prev_op = (String) session.getAttribute("operator");
			
			
			int prev = 0;
			String prev_op = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					prev = Integer.parseInt(c.getValue());
				}
				if(c.getName().equals("operator")){
					prev_op = c.getValue();
				}
			}
			
			if(prev_op.equals("+")) {
				result = prev + v;
			} else if(prev_op.equals("-")) {
				result = prev - v;
			}
			
			resp.getWriter().printf("result is %d\n", result);
		}
		else {
			//application.setAttribute("value", v);
			//application.setAttribute("operator", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("operator", op);
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie operatorCookie = new Cookie("operator", op);
			valueCookie.setMaxAge(60*60*2);
			
			resp.addCookie(valueCookie);
			resp.addCookie(operatorCookie);
			
			resp.sendRedirect("Calculator2.html");
		}
	}

}
