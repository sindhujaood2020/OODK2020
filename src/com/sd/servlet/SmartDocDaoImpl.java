package com.sd.servlet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sd.hib.SDHibernateUtility;
import com.sd.hib.User;

public class SmartDocDaoImpl implements SmartDocDao {
	
	private User user;
	private String responseMessage;
	
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public boolean login(String username, String password) {
		Transaction transaction = null;
		Session session = SDHibernateUtility.getSession();
		if (session != null) {
			try 
			{
				transaction = session.beginTransaction();
				// get an user object
	            User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
	                .uniqueResult();
				
				if (password.equals(user.getPassword())) {
					this.user = user;
		            transaction.commit();
					return true;
				}

			} catch (Exception exception) {
				System.out.println("Exception occred while reading user data: " + exception.getMessage());
				setResponseMessage(exception.getMessage());
				return false;
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return false;
	}

	@Override
	public boolean register(User user) 
	{
		boolean result = false;
		
		setResponseMessage("Registration unsuccessful, try again.....");
		
		Session session = SDHibernateUtility.getSession();
		
		if (session != null) {
			try {
				if (user != null) {
					String username = (String) session.save(user);
					session.beginTransaction().commit();
					setResponseMessage("User " + user.getUsername() + " created successfully...");
					result = true;
				}
			} catch (Exception exception) {
				setResponseMessage("Exception occred while reading user data: " + exception.getMessage());
			}

		} else {
			setResponseMessage("DB server down.....");
		}
		return result;
	}
	
	@Override
	public User getCurrentUser()
	{
		return user;
	}

	@Override
	public String getResponseMessage() {
		return this.responseMessage;
	}

}
