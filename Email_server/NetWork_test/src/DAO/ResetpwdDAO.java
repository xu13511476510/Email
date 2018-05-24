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

public class ResetpwdDAO {
	private Connection conn=dbUtil.getConnection();
	private String result1=new String();
	private String result2=new String();
	public boolean resetpwd(String uid,String upassword,String newpwd) throws SQLException
	{
		try{
			Statement stmt=(Statement) conn.createStatement();
			String sql = "select count(*) as count from user where user='"+uid+"' and password='"+upassword+"';";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				String result=rs.getString("count");
				if(result.equals("1"))
				{
					System.out.println("进入修改密码环节");
					String sql2 = "update user set password=? where user=?";
					PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql2);
					ptmt.setString(1,newpwd);
					ptmt.setString(2,uid);
					ptmt.execute();
					return true;
				}
			}
			return false;
		}
		catch(SQLException e){
			return false;
		}
		
	}

}
