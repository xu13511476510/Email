package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ResetpwdDAO;

import com.google.gson.*;

import model.*;

public class ResetpwdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ResetpwdServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 * http://112.74.176.171:8080/NetWork_test/Servlet/ReceiveServlet?uid=123456
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String uid=request.getParameter("uid");
			String upassword=request.getParameter("upassword");
			String newpwd=request.getParameter("newpwd");
			System.out.println("receive uid:"+uid);
			System.out.println("receive upassword:"+upassword);
			System.out.println("receive newpwd:"+newpwd);
			try {
				boolean result=new ResetpwdDAO().resetpwd(uid, upassword,newpwd);
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				if(result)
				{
					out.println("asdfghjkl");
					System.out.println("ÐÞ¸Ä");	
				}
				else
				{
					out.println("123456");
					System.out.println("ÐÞ¸ÄÊ§°Ü");	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
