package com.hanshin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my2")
public class myexample2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 실행 됨");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		String name = req.getParameter("name");
		String telephone = req.getParameter("tel");
		String email = req.getParameter("email");
		String[] depts = req.getParameterValues("dept");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birth");
		String intro = req.getParameter("introduction");
		
		out.print("<html><head><title>MyExample2</title></head>");
		out.print("<body>");
		out.print("ID : " + id + "<br/>");
		out.print("비밀번호 : " + password + "<br/>");
		out.print("이름 : " + name + "<br/>");
		out.print("전화번호 : " + telephone + "<br/>");
		out.print("이메일 : " + email + "<br/>");
		out.print("학부 : ");
		for (int i = 0; i < depts.length; i++) {
			out.print(depts[i] + " ");
		}
		out.print("<br/>");
		out.print("성별 : " + gender + "<br/>");
		out.print("태어난 계절 : " + birth + "<br/>");
		out.print("소개 : " + intro + "<br/>");
		out.println("</body></html>");
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
