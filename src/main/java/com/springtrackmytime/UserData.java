package com.springtrackmytime;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserData {
	
	public String userName = null;
	@Index
	public String mailId = null;
	public String password = null;
	public long lastEntry;
	public boolean clockin;
	@com.googlecode.objectify.annotation.Id
	Long Id;
	
	
	
	public UserData(String userName, String mailId, String password) {
		this.userName=userName;
	System.out.println(mailId);
			System.out.println(userName);
		this.mailId=mailId;
		this.password=password;
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
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
