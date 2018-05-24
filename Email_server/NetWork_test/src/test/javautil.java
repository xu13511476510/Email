package test;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class javautil {
	private static final String url="jdbc:mysql://127.0.0.1:3306/youxiang?characterEncoding=utf8&useSSL=true";
	private static final String user="root";
	private static final String password="admin";
	private static Connection conn=null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=(Connection)DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
}
