package com.hanshin.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/in")
public class JSP extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/memberdb?serverTimezone=UTC";
		try {
			Class.forName(jdbc_driver).newInstance();
			Connection con = DriverManager.getConnection(jdbc_url, "root", "1234");
			Statement st = con.createStatement();
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			RequestDispatcher rd = null;
			String jspid, jsppwd, jspname, jsptel, jspemail, jspdept, jspgender, jspbirth, jspintroduction;
			
			rs = st.executeQuery("select * from mb;");
			int size = 0;
			while(rs.next())	
				size++;
			String dbid[] = new String[size];
			String dbpwd[] = new String[size];
			String dbname[] = new String[size];
			String dbtel[] = new String[size];
			String dbemail[] = new String[size];
			String dbdept[] = new String[size];
			String dbgender[] = new String[size];
			String dbbirth[] = new String[size];
			String dbintroduction[] = new String[size];
			
			int i = 0, j = 0;
			rs = st.executeQuery("select * from mb;");
			while(rs.next()) {
				dbid[i] = rs.getString("id");
				dbpwd[i] = rs.getString("pwd");
				dbname[i] = rs.getString("name");
				dbtel[i] = rs.getString("tel");
				dbemail[i] = rs.getString("email");
				dbdept[i] = rs.getString("dept");
				dbgender[i] = rs.getString("gender");
				dbbirth[i] = rs.getString("birth");
				dbintroduction[i] = rs.getString("introduction");
				i++;
			}
			
			String logid = req.getParameter("logid");
			String logpwd = req.getParameter("logpwd");
			String logbtn = req.getParameter("logbtn");
			String mbbtn = req.getParameter("mbbtn");
			String listbtn = req.getParameter("listbtn");
			String f = req.getParameter("f");
			String dtbtn = req.getParameter("dtbtn");
			String pcbtn = req.getParameter("pcbtn");
			
			if(logbtn != null && logbtn.equals("로그인")) {
				boolean logcnt = false;
				for(i = 0; i < size; i ++) {
					if((logid.equals(dbid[i]))&&(logpwd.equals(dbpwd[i]))) {
						logcnt = true;
						break;
					}
				}
				if(!logcnt) {
					req.setAttribute("msg", "logf");
					rd = req.getRequestDispatcher("output.jsp");
					rd.forward(req, resp);
				}
				else {
					req.setAttribute("size", size);
					req.setAttribute("id", dbid);
					req.setAttribute("pwd", dbpwd);
					req.setAttribute("name", dbname);
					req.setAttribute("tel", dbtel);
					req.setAttribute("email", dbemail);
					req.setAttribute("dept", dbdept);
					req.setAttribute("gender", dbgender);
					req.setAttribute("birth", dbbirth);
					req.setAttribute("introduction", dbintroduction);		
					rd = req.getRequestDispatcher("list.jsp");
					rd.forward(req, resp);
				}
			}
			else if(logbtn != null && logbtn.equals("가입")) {
				rd = req.getRequestDispatcher("member.jsp");
				rd.forward(req, resp);
			}
			
			if(listbtn != null && listbtn.equals("검색")) {
				String tmpid[] = new String[size];
				String tmppwd[] = new String[size];
				String tmpname[] = new String[size];
				String tmptel[] = new String[size];
				String tmpemail[] = new String[size];
				String tmpdept[] = new String[size];
				String tmpgender[] = new String[size];
				String tmpbirth[] = new String[size];
				String tmpintroduction[] = new String[size];
				
				if(f.equals("default")) {
					req.setAttribute("size", size);
					req.setAttribute("id", dbid);
					req.setAttribute("pwd", dbpwd);
					req.setAttribute("name", dbname);
					req.setAttribute("tel", dbtel);
					req.setAttribute("email", dbemail);
					req.setAttribute("dept", dbdept);
					req.setAttribute("gender", dbgender);
					req.setAttribute("birth", dbbirth);
					req.setAttribute("introduction", dbintroduction);		
					rd = req.getRequestDispatcher("list.jsp");
					rd.forward(req, resp);
				}
				else {	
					for(i = 0, j = 0; i < size; i++) {
						if(f.equals(dbdept[i])) {
							tmpid[j] = dbid[i];
							tmppwd[j] = dbpwd[i];
							tmpname[j] = dbname[i];
							tmptel[j] = dbtel[i];
							tmpemail[j] = dbemail[i];
							tmpdept[j] = dbdept[i];			
							tmpgender[j] = dbgender[i];
							tmpbirth[j] = dbbirth[i];
							tmpintroduction[j] = dbintroduction[i];
							j++;
						}
					}
					req.setAttribute("size", j);
					req.setAttribute("id", tmpid);
					req.setAttribute("pwd", tmppwd);
					req.setAttribute("name", tmpname);
					req.setAttribute("tel", tmptel);
					req.setAttribute("email", tmpemail);
					req.setAttribute("dept", tmpdept);
					req.setAttribute("gender", tmpgender);
					req.setAttribute("birth", tmpbirth);
					req.setAttribute("introduction", tmpintroduction);
					rd = req.getRequestDispatcher("list.jsp");
					rd.forward(req, resp);
				}
			}
			
			if(dtbtn != null && dtbtn.equals("목록")) {
				req.setAttribute("size", size);
				req.setAttribute("id", dbid);
				req.setAttribute("pwd", dbpwd);
				req.setAttribute("name", dbname);
				req.setAttribute("tel", dbtel);
				req.setAttribute("email", dbemail);
				req.setAttribute("dept", dbdept);
				req.setAttribute("gender", dbgender);
				req.setAttribute("birth", dbbirth);
				req.setAttribute("introduction", dbintroduction);
				rd = req.getRequestDispatcher("list.jsp");
				rd.forward(req, resp);
			}
			else if(dtbtn != null && dtbtn.equals("수정")) {
				jspid = req.getParameter("dtid");
				req.setAttribute("id", jspid);
				rd = req.getRequestDispatcher("pwcheck.jsp");
				rd.forward(req, resp);
			}
			
			if(pcbtn != null && pcbtn.equals("수정")) {
				jspid = req.getParameter("dtid");
				jsppwd = req.getParameter("dtpwd");
				boolean pccnt = false;
				for(i = 0; i < size; i++) {
					if(jspid.equals(dbid[i]) && jsppwd.equals(dbpwd[i])) {
						pccnt = true;
						break;
					}
				}
				if(pccnt) {
					req.setAttribute("id", dbid[i]);
					req.setAttribute("name", dbname[i]);
					rd = req.getRequestDispatcher("member.jsp");
					rd.forward(req, resp);
				}
				else if(!pccnt) {
					req.setAttribute("msg", "pcf");
					rd = req.getRequestDispatcher("output.jsp");
					rd.forward(req, resp);
				}
			}

			if(mbbtn != null && mbbtn.equals("전송")) {
				boolean mbcnt = false;
				jspid = req.getParameter("id");
				jsppwd = req.getParameter("pwd");
				jspname = req.getParameter("name");
				jsptel = req.getParameter("tel");
				jspemail = req.getParameter("email");
				jspdept = req.getParameter("dept");
				jspgender = req.getParameter("gender");
				jspbirth = req.getParameter("birth");
				jspintroduction = req.getParameter("introduction");
				String tmpid = req.getParameter("mbid");
				String tmpname = req.getParameter("mbname");
				if(jspid == null && jspname == null) {
					pstmt = con.prepareStatement("update mb set pwd=?,tel=?,email=?,dept=?,gender=?,birth=?,introduction=? where id=? and name=?;");
					pstmt.setString(1, jsppwd);
					pstmt.setString(2, jsptel);
					pstmt.setString(3, jspemail);
					pstmt.setString(4, jspdept);
					pstmt.setString(5, jspgender);
					pstmt.setString(6, jspbirth);
					pstmt.setString(7, jspintroduction);
					pstmt.setString(8, tmpid);
					pstmt.setString(9, tmpname);
					pstmt.executeUpdate();
					
					req.setAttribute("mbid", tmpid);
					req.setAttribute("pwd", jsppwd);
					req.setAttribute("mbname", tmpname);
					req.setAttribute("tel", jsptel);
					req.setAttribute("email", jspemail);
					req.setAttribute("dept", jspdept);
					req.setAttribute("gender", jspgender);
					req.setAttribute("birth", jspbirth);
					req.setAttribute("introduction", jspintroduction);
					rd = req.getRequestDispatcher("detail.jsp");
					rd.forward(req, resp);
				}
				for(i = 0; i < size; i++) {
					if(jspid.equals(dbid[i])) {
						mbcnt = true;
						req.setAttribute("msg", "joinf");
						rd = req.getRequestDispatcher("output.jsp");
						rd.forward(req, resp);
					}
				}
				if(!mbcnt) {
					pstmt = con.prepareStatement("insert into mb values(?,?,?,?,?,?,?,?,?);");
					pstmt.setString(1, jspid);
					pstmt.setString(2, jsppwd);
					pstmt.setString(3, jspname);
					pstmt.setString(4, jsptel);
					pstmt.setString(5, jspemail);
					pstmt.setString(6, jspdept);
					pstmt.setString(7, jspgender);
					pstmt.setString(8, jspbirth);
					pstmt.setString(9, jspintroduction);
					pstmt.executeUpdate();
					req.setAttribute("msg", "joint");
					rd = req.getRequestDispatcher("output.jsp");
					rd.forward(req, resp);
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
