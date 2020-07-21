package com.sd.servlet;

import java.util.List;

import com.sd.hib.Patient;

public interface PatientDao extends GenericDao{
	 public boolean login(String username, String password);

	 public boolean register(Patient user);
	 
	 public Patient getPatientDetails(String patient_id);
	 
	 public List<Patient> findPatient(String firstName, String lastName);
}
