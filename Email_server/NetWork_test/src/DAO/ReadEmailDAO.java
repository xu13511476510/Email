package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import model.Email;
import model.dbUtil;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ReadEmailDAO {
	private Connection conn=dbUtil.getConnection();
	public void readEmail(String sender,String receiver,String date) throws SQLException
	{
		Statement stmt=(Statement) conn.createStatement();
		String sql = "update mail set flag2='1' where sender='"+sender+"' and receiver='"+receiver+"' and date='"+date+"';";
		stmt.execute(sql);
		
	}

}
