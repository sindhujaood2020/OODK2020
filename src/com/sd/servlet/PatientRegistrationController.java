package com.sd.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sd.hib.Patient;
import com.sd.util.SDUtils;

public class PatientRegistrationController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public PatientRegistrationController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	 /**
	  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	  */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	 }

	 /**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
		   String page = "userhome.jsp";
	  
		   Patient patient = new Patient();
		   patient.setFirst_name(request.getParameter("firstName"));
		   patient.setLast_name(request.getParameter("lastName"));
		   patient.setGender(request.getParameter("gender").charAt(0));
		   Date dob = null;
			try {
				dob = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("dob"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   patient.setDate_of_birth(dob);
		   patient.setContact_number(request.getParameter("contactNumber"));
		   patient.setAddress(request.getParameter("address"));
		   patient.setCreated_at(new Date());
		   patient.setAge(SDUtils.calculateAge(dob));
		   
		   patient.setPatient_id(Integer.parseInt(SDUtils.generateUniqueKey()));
		   
		   PatientDao patientDao = new PatientDaoImpl();
		   boolean result = patientDao.register(patient);
		   if(result)
		   {
		       request.setAttribute("lastEnrolledPatientId", patient.getPatient_id());
		   }
		   request.setAttribute("pagename","patientenrollmentresult.jsp");
		   request.setAttribute("message", patientDao.getResponseMessage());
		   
		   request.getRequestDispatcher(page).include(request, response);
	 	}

	}