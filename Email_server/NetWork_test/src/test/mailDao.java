package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;
import com.mysql.jdbc.PreparedStatement;

public class mailDao {
	private Connection conn=javautil.getConnection();
	private String sql;
	public void writemail(mail ma) throws SQLException{
		Statement stmt=(Statement)conn.createStatement();
		sql="insert into mail values(?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql);
		ptmt.setString(1,ma.getSender());
		ptmt.setString(2,ma.getReceiver());
		ptmt.setString(3,ma.getData());
		ptmt.setString(4,ma.getSubject());
		ptmt.setString(5,ma.getContext());
		ptmt.setString(6,ma.getFlag());
		ptmt.setString(7,"1");
		ptmt.setString(8,"0");
		ptmt.execute();
	}
	
}
