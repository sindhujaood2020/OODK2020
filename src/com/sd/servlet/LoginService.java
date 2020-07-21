package com.sd.servlet;

import com.sd.hib.User;

public interface LoginService {
	
	 public boolean login(String username, String password);

	 public boolean registration(User user);
	 
}