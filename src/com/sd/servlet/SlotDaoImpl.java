package com.sd.servlet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sd.hib.SDHibernateUtility;
import com.sd.hib.Slot;

public class SlotDaoImpl implements SlotDao 
{
	private String responseMessage;

	@Override
	public boolean registerSlot(Slot slot) 
	{
		boolean result = false;
		
		setResponseMessage("Slot registration unsuccessful, try again.....");
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try {
				if (slot != null) {
					int slot_id = (Integer) session.save(slot);
					session.beginTransaction().commit();
					setResponseMessage("Slot "+slot_id+" booked successfully");
					result = true;
				}
			} catch (Exception exception) {
				setResponseMessage("Exception occred while booking the slot: " + exception.getMessage());
				exception.printStackTrace();
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Slot> getRegisteredSlots(String doctorId, Date date, String time)
	{
		Transaction transaction = null;
		
		List<Slot> slotsList = new ArrayList<Slot>();
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				
				String queryString = "";
				
				if(doctorId!=null) {
					queryString = "FROM Slot S WHERE S.doctor_id = "+doctorId;
				}
				if(date!=null)
				{
					queryString = queryString + " AND S.slot_date="+date+")";
				}
				
				slotsList = (List<Slot>) session.createQuery(queryString);
				setResponseMessage("fetched "+slotsList.size()+" records for search criteria["+doctorId+","+date+"]");
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while fetching patients list: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return slotsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Slot> getRegisteredSlots(String patientId, Date date) 
	{
		Transaction transaction = null;
		
		List<Slot> slotsList = new ArrayList<Slot>();
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				
				String queryString = "";
				
				if(patientId!=null) {
					queryString = "FROM Slot S WHERE S.patient_id = "+patientId;
				}
				if(date!=null)
				{
					queryString = queryString + " AND S.slot_date="+date+")";
				}
				
				slotsList = (List<Slot>) session.createQuery(queryString);
				
				setResponseMessage("fetched "+slotsList.size()+" records for search criteria["+patientId+","+date+"]");
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while fetching slots list for patient: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return slotsList;
	}

	@Override
	public boolean isSlotAvailable(String doctorId, Date date, String time) 
	{
		Transaction transaction = null;
		boolean isSlotAvailable = true;
		Session session = SDHibernateUtility.getSession();
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				Timestamp sqlTimestamp = new Timestamp(date.getTime());
				// get an user object
				List<Slot> resultSlots = session.createQuery("FROM Slot S WHERE S.doctor_id = "+doctorId+" and S.slot_date = '"+sqlTimestamp+"' and S.slot_time='"+time+"'").getResultList();
				System.out.println(resultSlots.size());
	            isSlotAvailable = (resultSlots.size()<=0);
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while checking slot availability: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return isSlotAvailable;
	}

	@Override
	public boolean canceSlot(String slot_id) 
	{
		boolean result = false;
		
		setResponseMessage("Slot cancellation unsuccessful, try again.....");
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try {
				if (slot_id != null) {
					Slot slot = (Slot) session.get(Slot.class, slot_id);
					session.delete(slot);
					session.beginTransaction().commit();
					setResponseMessage("Slot "+slot_id+" cancelled successfully");
					result = true;
				}
			} catch (Exception exception) {
				setResponseMessage("Exception occred while booking the slot: " + exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return result;
	}

	@Override
	public boolean updateSlot(Slot slot) 
	{
		boolean result = false;
		
		setResponseMessage("Slot updation unsuccessful, try again.....");
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try {
				if (slot != null) {
					String slot_id = (String) session.save(slot);
					session.beginTransaction().commit();
					setResponseMessage("Slot "+slot_id+" updated successfully");
					result = true;
				}
			} catch (Exception exception) {
				setResponseMessage("Exception occred while updating the slot: " + exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return result;
	}

	@Override
	public Slot getSlotDetails(String slot_id) {
		Transaction transaction = null;
		Slot slot = null;
		Session session = SDHibernateUtility.getSession();
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				// get an user object
	            slot = (Slot) session.createQuery("FROM Slot S WHERE S.slot_id = :slot_id").setParameter("slot_id", slot_id)
	                .uniqueResult();
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while reading Slot data: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return slot;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Slot> getAllRegisteredSlots() 
	{
		Transaction transaction = null;
		
		List<Slot> slotsList = new ArrayList<Slot>();
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				
				String queryString = "FROM Slot S WHERE S.slot_status = 'New' ";

				slotsList = (List<Slot>) session.createQuery(queryString).getResultList();
				
				setResponseMessage("fetched "+slotsList.size()+" records for search criteria[Status New]");
	            transaction.commit();
			} catch (Exception exception) {
				System.out.println("Exception occred while fetching slots list for patient: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return slotsList;
	}

	@Override
	public String getResponseMessage() {
		return this.responseMessage;
	}

	@Override
	public void setResponseMessage(String message) {
		this.responseMessage = message;
	}
}
