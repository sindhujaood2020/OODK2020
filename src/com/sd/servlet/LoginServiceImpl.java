package com.sd.servlet;

import com.sd.hib.User;

public class LoginServiceImpl implements LoginService {

	 private SmartDocDao loginDao = new SmartDocDaoImpl();

	 @Override
	 public boolean login(String username, String password) {
	  return loginDao.login(username, password);
	 }

	 @Override
	 public boolean registration(User user) {
	  return loginDao.register(user);
	 }

}