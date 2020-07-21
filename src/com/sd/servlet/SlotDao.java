package com.sd.servlet;

import java.util.Date;
import java.util.List;

import com.sd.hib.Doctor;
import com.sd.hib.Patient;
import com.sd.hib.Slot;

public interface SlotDao extends GenericDao {

	 public boolean registerSlot(Slot slot);
	 
	 public List<Slot> getRegisteredSlots(String doctorId, Date date, String time);
	 
	 public List<Slot> getRegisteredSlots(String patientId, Date date);
	 
	 public List<Slot> getAllRegisteredSlots();
	 
	 public boolean isSlotAvailable(String doctorId, Date date, String time);
	 
	 public boolean canceSlot(String slot_id);
	 
	 public boolean updateSlot(Slot slot);
	 
	 public Slot getSlotDetails(String slot_id);
}
