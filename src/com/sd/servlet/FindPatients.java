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
import com.sd.hib.Patient;

/**
 * Servlet implementation class FindPatients
 */
public class FindPatients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try 
		{
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            
            System.out.println(firstName+"-"+lastName);
            PrintWriter out = response.getWriter();
            
            PatientDao patientDao = new PatientDaoImpl();
            
            List<Patient> patientsList = patientDao.findPatient(firstName, lastName);
            System.out.println(patientDao.getResponseMessage());
            System.out.println(patientsList.size());
            
            JSONArray jsonArray = new JSONArray(patientsList);
            System.out.println(jsonArray);
            out.println(jsonArray);
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
