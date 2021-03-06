package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LajiDAO;

import com.google.gson.*;

import model.*;

public class LajiServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LajiServlet() {
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
			System.out.println("receive uid:"+uid);
			try {
				List<Email> EmailList=new LajiDAO().getEmails(uid);
				//EmailList el=new EmailList();
				//el.setResult(1);
				//el.setEmailList(EmailList);
				Gson gson=new Gson();
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				System.out.println(gson.toJson(EmailList));
				out.println(gson.toJson(EmailList));
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
