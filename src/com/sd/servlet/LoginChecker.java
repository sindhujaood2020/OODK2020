package com.sd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginChecker
 */
public class LoginChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("loginpage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("userId");
		String password = request.getParameter("password");

		String msg = "";
		String page="userhome.jsp";
		String centerpage = "defaultcenterpanel.jsp";
		
		if (username.trim().length() >= 0 && username != null && password.trim().length() >= 0 && password != null) 
		{
			SDService sdLoginService = new SDServiceImpl();
			boolean flag = sdLoginService.login(username, password);
			
			if (flag) {
				request.setAttribute("username", username);
				msg =  "Login Success.....";
				request.getSession().setAttribute("user", sdLoginService.getCurrentUser());
			} 
			else {
				msg = "Wrong Username or Password, Try again!!!";
				page = "errorpage.jsp";
			}
		}
		else {
			msg =  "Please enter username and password...";
			page = "loginpage.jsp";
		}

	   request.setAttribute("pagename", centerpage);
	   request.setAttribute("message", msg);
		
	   request.getRequestDispatcher(page).include(request, response);
	}
}
