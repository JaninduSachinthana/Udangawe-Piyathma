package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.UserService;

//for map
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ComplainAPI
 */
@WebServlet("/UserAPI")
public class UserServiceAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService comObj = new UserService();
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	
	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServiceAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = comObj.insertUser( 
				request.getParameter("Username"),
				request.getParameter("Userpwd"),
				request.getParameter("Userphn"),
				request.getParameter("Useremail"),
				request.getParameter("Useradrs"),
				request.getParameter("Useracc"),
				request.getParameter("Date"));

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		String output = comObj.updateUser( 
				paras.get("Username").toString(), 
				paras.get("Userpwd").toString(),
				paras.get("Userphn").toString(),
				paras.get("Useremail").toString(), 
				paras.get("Useradrs").toString(),
				paras.get("Useracc").toString(),
				paras.get("Date").toString(),
				paras.get("hidcomIDSave").toString()); 
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = comObj.deleteUser(paras.get("comId").toString());
		//String output = comObj.deleteComplain("31");
		response.getWriter().write(output); 
		
	}

}
