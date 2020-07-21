package com.sd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sd.hib.Slot;

/**
 * Servlet implementation class NavigationController
 */
public class NavigationController extends HttpServlet {
 private static final long serialVersionUID = 1L;


 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 {
	 String centerpage="userhome.jsp";
	
	 String actionParam = request.getParameter("action");
	 
	 switch(actionParam) {
	  case "addPatient":
		  centerpage = "patientenrollment.jsp";
	    break;
	  case "addSlot":
		  centerpage = "slotenrollment.jsp";
	    break;
	  case "alterSlot":
		  centerpage = "alterslot.jsp";
	    break;
	  case "getSlots":
		  centerpage = "viewslots.jsp";
          SlotDao dao = new SlotDaoImpl();
          List<Slot> slotList = dao.getAllRegisteredSlots();
          request.setAttribute("SlotList",slotList);
	    break;
	  default:
		  centerpage = "defaultcenterpanel.jsp";
	}
	 
	request.setAttribute("pagename", centerpage);
	
    request.getRequestDispatcher("userhome.jsp").include(request, response);
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 }
}
