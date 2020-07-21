package com.sd.servlet;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.sd.hib.Patient;
import com.sd.hib.SDHibernateUtility;
import com.sd.hib.User;

public class PatientDaoImpl implements PatientDao {
	
	private Patient patient;
	private String responseMessage;

	@Override
	public boolean login(String username, String password) {
		
		return false;
	}

	@Override
	public boolean register(Patient patient) 
	{
		boolean result = false;
		
		setResponseMessage("Registration unsuccessful, try again.....");
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try {
				if (patient != null) {
					int patientId = (Integer) session.save(patient);
					session.beginTransaction().commit();
					setResponseMessage("Patient entry for " + patient.getFirst_name()+" "+patient.getLast_name() +"["+patientId+"] created successfully. Proceed for slot booking...");
					result = true;
				}
			} catch (Exception exception) {
				setResponseMessage("Exception occred while inserting patient data: " + exception.getMessage());
				exception.printStackTrace();
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return result;
	}

	@Override
	public String getResponseMessage() {
		return this.responseMessage;
	}

	@Override
	public void setResponseMessage(String message) {
		this.responseMessage = message;
	}

	@Override
	public Patient getPatientDetails(String patient_id) 
	{
		Transaction transaction = null;
		Patient patient = null;
		Session session = SDHibernateUtility.getSession();
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				// get an user object
	            patient = (Patient) session.createQuery("FROM Patient P WHERE P.patient_id = :patient_id").setParameter("patient_id", patient_id)
	                .uniqueResult();
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while reading patient data: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return patient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> findPatient(String firstName, String lastName) 
	{
		Transaction transaction = null;
		
		List<Patient> patientsList = new ArrayList<Patient>();
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				
				Criteria cr = session.createCriteria(Patient.class);
				
				Criterion  cr1 = Restrictions.like("first_name", firstName+"%");
				Criterion  cr2 = Restrictions.like("last_name", lastName+"%");
				
				
				LogicalExpression andExp = Restrictions.and(cr1, cr2);
				cr.add( andExp );

				patientsList = cr.list();
				setResponseMessage("fetched "+patientsList.size()+" records for search criteria["+firstName+","+lastName+"]");
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while fetching patients list: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return patientsList;
	}
}
