import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbc {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i, id;
		String name, tel, email, address;
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC";
		try {
			Class.forName(jdbc_driver).newInstance();
			Connection con = DriverManager.getConnection(jdbc_url, "root", "1234");
			Statement st = con.createStatement();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "CREATE TABLE addressbook (id INT PRIMARY KEY, name VARCHAR(45), tel VARCHAR(45), email VARCHAR(60), address VARCHAR(60));";
			st.executeUpdate(sql);
			
			for(i = 0; i < 5; i++) {
				System.out.print("id : ");
				id = sc.nextInt();
				sc.nextLine();
				System.out.print("name : ");
				name = sc.nextLine();
				System.out.print("tel : ");
				tel = sc.nextLine();
				System.out.print("email : ");
				email = sc.nextLine();
				System.out.print("address : ");
				address = sc.nextLine();
				pstmt = con.prepareStatement("insert into addressbook values(?,?,?,?,?);");
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, tel);
				pstmt.setString(4, email);
				pstmt.setString(5, address);
				pstmt.executeUpdate();
			}
			
			rs = st.executeQuery("select email from addressbook;");
			for(i = 0; i < 5; i++) {
				if(rs.next()) {
					email = rs.getString("email");
					String[] temp = email.split("@");
					temp[0] = temp[0] + "@naver.com";
					pstmt = con.prepareStatement("update addressbook set email=?;");
					pstmt.setString(1, temp[0]);
					pstmt.executeUpdate();
				System.out.printf("%s\n", temp[0]);
				}
			}				
			
			rs = st.executeQuery("select count(id) as num from addressbook;");
			if(rs.next()) {
				int row = rs.getInt("num");
				pstmt = con.prepareStatement("delete from addressbook where id = ?;");
				pstmt.setInt(1,  row);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement("delete from addressbook where id = ?;");
				pstmt.setInt(1,  row-1);
				pstmt.executeUpdate();
			}
			
			rs = st.executeQuery("select * from addressbook;");
			for(i = 0; i < 5; i++) {
				if(rs.next()) {
					id = rs.getInt("id");
					name = rs.getString("name");
					tel = rs.getString("tel");
					email = rs.getString("email");
					address = rs.getString("address");
					System.out.printf("id : %d, name : %s, tel : %s, email : %s, address : %s\n", id, name, tel, email, address);
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
