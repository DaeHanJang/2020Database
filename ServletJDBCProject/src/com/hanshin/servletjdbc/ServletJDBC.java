package com.hanshin.servletjdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/in")
public class ServletJDBC extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/memberdb?serverTimezone=UTC";
		try {
			Class.forName(jdbc_driver).newInstance();
			Connection con = DriverManager.getConnection(jdbc_url,"root","1234");
			Statement st = con.createStatement();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");
			String email = req.getParameter("email");
			String depts = req.getParameter("dept");
			String gender = req.getParameter("gender");
			String birth = req.getParameter("birth");
			String intro = req.getParameter("introduction");
			String btn = req.getParameter("btn");
			
			out.print("<html><head><title>memberDB</title></head>");
			out.print("<body>");
			
			if(btn.equals("전송")) {
				boolean cnt = false;
				String id_tmp = null, pwd_tmp = null, name_tmp = null;
				rs = st.executeQuery("select * from information;");
				while(rs.next()) {
					id_tmp = rs.getString("id");
					pwd_tmp = rs.getString("password");
					name_tmp = rs.getString("name");
					if((id.equals(id_tmp)) && (name.equals(name_tmp))) {
						cnt = true;
						if(!(pwd.equals(pwd_tmp))) {
							out.print("비밀번호가 다릅니다!");
							break;
						}
						else {
							pstmt = con.prepareStatement("update information set phone=?,email=?,dept=?,gender=?,birth=?,introduction=? where id=? and name=?;");
							pstmt.setString(1, tel);
							pstmt.setString(2, email);
							pstmt.setString(3, depts);
							pstmt.setString(4, gender);
							pstmt.setString(5, birth);
							pstmt.setString(6, intro);
							pstmt.setString(7, id_tmp);
							pstmt.setString(8, name_tmp);
							pstmt.executeUpdate();
							out.print("업데이트 성공! <br/>");
							out.print("id : " + id + "<br/>");
							out.print("이름 : " + name + "<br/>");
							out.print("전화번호 : " + tel + "<br/>");
							out.print("이메일 : " + email + "<br/>");
							out.print("전공 : " + depts + "<br/>");
							out.print("성별 : " + gender + "<br/>");
							out.print("태어난 날 : " + birth + "<br/>");
							out.print("소개 : " + intro + "<br/>");
							break;
						}
					}
				}
				if(!cnt) {
					pstmt = con.prepareStatement("insert into information values(?,?,?,?,?,?,?,?,?);");
					pstmt.setString(1, id);
					pstmt.setString(2, pwd);
					pstmt.setString(3, name);
					pstmt.setString(4, tel);
					pstmt.setString(5, email);
					pstmt.setString(6, depts);
					pstmt.setString(7, gender);
					pstmt.setString(8, birth);
					pstmt.setString(9, intro);
					pstmt.executeUpdate();
					out.print("저장 성공! <br/>");
					out.print("id : " + id + "<br/>");
					out.print("이름 : " + name + "<br/>");
					out.print("전화번호 : " + tel + "<br/>");
					out.print("이메일 : " + email + "<br/>");
					out.print("전공 : " + depts + "<br/>");
					out.print("성별 : " + gender + "<br/>");
					out.print("태어난 날 : " + birth + "<br/>");
					out.print("소개 : " + intro + "<br/>");
				}
			}
			else if(btn.equals("DB보기")){
				rs = st.executeQuery("select id,name,phone,email,dept,gender,birth,introduction from information;");
				while(rs.next()) {
					id = rs.getString("id");
					name = rs.getString("name");
					tel = rs.getString("phone");
					email = rs.getString("email");
					depts = rs.getString("dept");
					gender = rs.getString("gender");			
					birth = rs.getString("birth");
					intro = rs.getString("introduction");
					out.print("id : " + id + "<br/>");
					out.print("이름 : " + name + "<br/>");
					out.print("전화번호 : " + tel + "<br/>");
					out.print("이메일 : " + email + "<br/>");
					out.print("전공 : " + depts + "<br/>");
					out.print("성별 : " + gender + "<br/>");
					out.print("태어난 날 : " + birth + "<br/>");
					out.print("소개 : " + intro + "<br/>");
					out.print("<hr/>");
				}
			}
			else if(btn.equals("DB삭제")) {
				pstmt = con.prepareStatement("delete from information;");
				pstmt.executeUpdate();
				out.print("테이블이 초기화되었습니다.");
			}
			else {
				out.print("오류");
			}
			
			out.print("</body></html>");
			out.close();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
