package com.sd.hib;

import java.util.Date;

public class Slot 
{
    public int slot_id;
    public int patient_id;
    public int doctor_id;
    public String first_name;
    public String last_name;
	public char gender;
    public Date slot_date;
	public String slot_time;
	public String contact_number;
	public Date created_at;
	public Date updated_date;
    public String slot_status;
    public String comments;
    public int priority;
    
	public int getSlot_id() {
		return slot_id;
	}
	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Date getSlot_date() {
		return slot_date;
	}
	public void setSlot_date(Date slot_date) {
		this.slot_date = slot_date;
	}
	public String getSlot_time() {
		return slot_time;
	}
	public void setSlot_time(String slot_time) {
		this.slot_time = slot_time;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public String getSlot_status() {
		return slot_status;
	}
	public void setSlot_status(String slot_status) {
		this.slot_status = slot_status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
 }
