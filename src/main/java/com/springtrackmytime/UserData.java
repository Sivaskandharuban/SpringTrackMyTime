package com.springtrackmytime;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserData implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public String userName = null;
	@Index
	public String mailId = null;
	public String password = null;
	public long lastEntry;
	public boolean clockin;
	@com.googlecode.objectify.annotation.Id
	Long id;
	
	
	
	public UserData(String userName, String mailId, String password) {
		this.userName=userName;
	System.out.println(mailId);
			System.out.println(userName);
		this.mailId=mailId;
		this.password=password;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getLastEntry() {
		return lastEntry;
	}

	public void setLastEntry(long lastEntry) {
		this.lastEntry = lastEntry;
	}

	public boolean isClockin() {
		return clockin;
	}

	public void setClockin(boolean clockin) {
		this.clockin = clockin;
	}
	
	
		
	public UserData() {
		
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
