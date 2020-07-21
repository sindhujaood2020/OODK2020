package com.sd.servlet;

import com.sd.hib.User;

public interface SmartDocDao extends GenericDao {
	 public boolean login(String username, String password);

	 public boolean register(User user);
	 
	 public User getCurrentUser();
}
