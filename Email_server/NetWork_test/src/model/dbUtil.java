package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.*;
 

public class dbUtil {

	private static final String URL="jdbc:mysql://127.0.0.1:3306/youxiang?characterEncoding=utf8&useSSL=true";
	private static final String USER="root";
    private static final String PASSWORD="admin";
    private static Connection conn=null;
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=(Connection)DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection()
	{
		return conn;
	}
}
