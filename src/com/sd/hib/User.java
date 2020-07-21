package com.sd.hib;

public class User 
{
	private String id;
	private String username;
	private String password;
	private String email;
    public String first_name;
    public String last_name;
    public String dob;
	
	public String getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 @Override
	 public String toString() {
	  return "User [username=" + username + ", password=" + password+ ", email="+ email + ", uniqueId ="+ id+ ", dob ="+ dob+ ", fn ="+ first_name+ ", ln ="+ last_name+ "]";
	 }
}
