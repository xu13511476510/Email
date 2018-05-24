package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import model.Email;
import model.dbUtil;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class LoginDAO {
	private Connection conn=dbUtil.getConnection();
	public boolean login(String uid,String upassword) throws SQLException
	{
		Statement stmt=(Statement) conn.createStatement();
		String sql = "select count(*) as count from user where user='"+uid+"' and password='"+upassword+"';";
		ResultSet rs=stmt.executeQuery(sql);
		List<Email> emList=new ArrayList<Email>();
		while(rs.next())
		{
			String result=rs.getString("count");
			if(result.equals("1"))
			{
				return true;
			}
			
		}
		return false;
	}

}
