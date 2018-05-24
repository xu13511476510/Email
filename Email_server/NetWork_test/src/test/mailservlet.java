package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

public class mailservlet {
	public mail doGet(HttpServletRequest request, HttpServletResponse response){
		String sender=request.getParameter("sender");
		String receiver=request.getParameter("receiver");
		String date=request.getParameter("date");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String flag=request.getParameter("flag");
		
		mail eva=new mail();
		eva.setSender(sender);
		eva.setReceiver(receiver);
		eva.setData(date);
		eva.setSubject(subject);
		eva.setContext(content);
		eva.setFlag(flag);
		
		try {
			new mailDao().writemail(eva);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eva;	
	}
}
