package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import model.Email;
import model.dbUtil;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SendDAO {
	private Connection conn=dbUtil.getConnection();
	public List<Email> getEmails(String uid) throws SQLException
	{
		Statement stmt=(Statement) conn.createStatement();
		String sql = " select * from mail where flag1='1' and sender='"+uid+"';";
		ResultSet rs=stmt.executeQuery(sql);
		List<Email> emList=new ArrayList<Email>();
		while(rs.next())
		{
			Email em=new Email();
			em.setReceiver(uid);
			em.setBody(rs.getString("context"));
			em.setDate(rs.getString("date"));
			em.setSender(rs.getString("sender"));
			em.setReceiver(rs.getString("receiver"));
			em.setTitle(rs.getString("subject"));
			if(rs.getString("flag2").contains("1"))
			{
				em.setRead(true);
			}
			emList.add(em);
		}
		return emList;
	}

}
