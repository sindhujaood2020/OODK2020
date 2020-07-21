package com.sd.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sd.hib.Doctor;
import com.sd.hib.Patient;
import com.sd.hib.SDHibernateUtility;
import com.sd.hib.Slot;
import com.sd.hib.User;

public class SDServiceImpl implements SDService 
{

	 private SmartDocDao loginDao = new SmartDocDaoImpl();
	 private PatientDao patientDao = new PatientDaoImpl();
	 private SlotDao slotDao = new SlotDaoImpl();
	 private String responseMessage;

	 @Override
	 public boolean login(String username, String password) {
	  return loginDao.login(username, password);
	 }

	 @Override
	 public boolean registration(User user) {
		boolean result = loginDao.register(user);
		this.responseMessage = loginDao.getResponseMessage();
		return result;
	 }
	 
	 @Override
	 public User getCurrentUser() {
	  return loginDao.getCurrentUser();
	 }

	@Override
	public boolean registerPatient(Patient patient) {
		boolean result = patientDao.register(patient);
		this.responseMessage = patientDao.getResponseMessage();
		return result;
	}

	@Override
	public boolean registerSlot(Slot slot) {
		boolean result = slotDao.registerSlot(slot);
		this.responseMessage = slotDao.getResponseMessage();
		return result;
	}

	@Override
	public Slot getSlotDetails(String slot_id) {
		Slot slot = slotDao.getSlotDetails(slot_id);
		this.responseMessage = slotDao.getResponseMessage();
		return slot;
	}
	
	public String getResponseMessage() {
		return this.responseMessage;
	}

	@Override
	public List<Doctor> getUniqueListOfDoctors(String specialization) 
	{
        Transaction transaction = null;
		
		List<Doctor> doctorsList = new ArrayList<Doctor>();
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				doctorsList = session.createQuery("FROM Doctor D WHERE D.specialization=:specialization").setParameter("specialization", specialization).list();
	            transaction.commit();
			} catch (Exception exception) {
				responseMessage = "Exception occred while fetching doctors list: " + exception.getMessage();
			}

		} else {
			responseMessage = "DB server down.....";
		}
		return doctorsList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getListOfSpecializations() 
	{
		Transaction transaction = null;
		
		List<String> specializationsList = new ArrayList<String>();
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) 
		{
			try 
			{
				transaction = session.beginTransaction();
				specializationsList = (List<String>)session.createNativeQuery("SELECT distinct(D.specialization) FROM Doctor D").getResultList();
	            transaction.commit();
			} catch (Exception exception) {
				responseMessage = "Exception occred while fetching doctors list: " + exception.getMessage();
			}

		} else {
			responseMessage = "DB server down.....";
		}
		return specializationsList;
	}
	
	public boolean isDoctorAvailableForADate(String doctorId, Date date,String time) {
		boolean isDoctorAvailable = false;
		isDoctorAvailable = slotDao.isSlotAvailable(doctorId, date, time);
		return isDoctorAvailable;
	}
}


