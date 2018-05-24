package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






import model.Email;
import model.dbUtil;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class RegisterDAO {
	private Connection conn=dbUtil.getConnection();
	private String result1=new String();
	private String result2=new String();
	public boolean login(String uid,String upassword) throws SQLException
	{
		try{
			String sql2 = "insert into user(user,PASSWORD) values (?,?);";
			PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql2);
			ptmt.setString(1,uid);
			ptmt.setString(2,upassword);
			ptmt.execute();
			return true;
		}
		catch(SQLException e){
			return false;
		}
		
	}

}
