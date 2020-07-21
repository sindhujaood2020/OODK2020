package com.sd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sd.hib.Slot;
import com.sd.util.SDUtils;

public class SlotRegistrationController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public SlotRegistrationController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	 /**
	  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	  */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
		 PrintWriter out = response.getWriter();
		  if(request.getParameter("Action").equals("GetAvailability"))
		  {
			   Date slot_date = null;
				try {
					slot_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("date"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			  SDService slotService = new SDServiceImpl();
			  boolean slotAvailable = slotService.isDoctorAvailableForADate(request.getParameter("doctorId"), slot_date, request.getParameter("time"));
			  out.println(slotAvailable);
		  }
	 }

	 /**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
	  String msg = "";
	  String page = "userhome.jsp";
	  
	  Slot slot = new Slot();
	  slot.setDoctor_id(Integer.parseInt(request.getParameter("doctor_id")));
	  slot.setPatient_id(Integer.parseInt(request.getParameter("patient_id")));
	  slot.setCreated_at(new Date());
	   Date slot_date = null;
		try {
			slot_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("slot_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   slot.setContact_number(request.getParameter("patient_id"));
	   slot.setPriority(1);
	   slot.setComments(request.getParameter("comments"));
	   slot.setSlot_date(slot_date);
	   slot.setSlot_time(request.getParameter("slot_time"));
	   slot.setSlot_status("New");
	   
	   slot.setSlot_id(Integer.parseInt(SDUtils.generateUniqueKey()));

	   SDService slotService = new SDServiceImpl();
	   boolean success = slotService.registerSlot(slot);
	  
	   request.setAttribute("pagename","slotenrollmentresult.jsp");
	   request.setAttribute("message", slotService.getResponseMessage());
	   request.getRequestDispatcher(page).include(request, response);


	 }
	}