package com.sd.servlet;

import java.util.Date;
import java.util.List;

import com.sd.hib.Doctor;
import com.sd.hib.Patient;
import com.sd.hib.Slot;
import com.sd.hib.User;

public interface SDService
{
		 public boolean login(String username, String password);

		 public boolean registration(User user);
		 
		 public User getCurrentUser();
		 
		 public boolean registerPatient(Patient patient);
		 
		 public boolean registerSlot(Slot slot);
		 
		 public boolean isDoctorAvailableForADate(String doctorId, Date date, String time);
		 
		 public Slot getSlotDetails(String slot_id);
		 
		 public String getResponseMessage();
		 
		 public List<Doctor> getUniqueListOfDoctors(String specialization);
		 
		 public List<String> getListOfSpecializations();
}
