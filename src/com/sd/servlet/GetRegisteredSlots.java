package com.sd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.sd.hib.Doctor;
import com.sd.hib.Slot;

/**
 * Servlet implementation class GetDoctors
 */
public class GetRegisteredSlots extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try 
		{
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter out = response.getWriter();
            
            SlotDao dao = new SlotDaoImpl();
            
            List<Slot> slotList = dao.getAllRegisteredSlots();
            
            request.setAttribute("SlotList",slotList);
            
            request.getRequestDispatcher("").include(request, response);
        } 
		catch (Exception io) {
            io.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
	}
}
