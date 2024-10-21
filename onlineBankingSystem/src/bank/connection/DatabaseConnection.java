package bank.connection;

import java.sql.*;

public class DatabaseConnection {

	public static Connection provideConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/bankingsystem";
		
		try {
			conn = DriverManager.getConnection(url,"root","1234");
		} catch (Exception e) {

			System.out.println("Exception occured in Database");
		}
		
		
		return conn;
	}
}
