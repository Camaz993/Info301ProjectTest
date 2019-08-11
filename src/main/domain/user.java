package main.domain;

import java.sql.Date;

import javax.persistence.Column;

public class user {
	private Integer userid;
	private String first_name;
	private String last_name;
	private String role;
	private String username;
	private String passowrd;
	private boolean locked;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	//Constructor 
	public user(Integer userid, String first_name, String last_name, String role, String username, String passowrd,
			boolean locked) {
		super();
		this.userid = userid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
		this.username = username;
		this.passowrd = passowrd;
		this.locked = locked;
	}
	
	@Override
	public String toString() {
		return "user [userid=" + userid + ", first_name=" + first_name + ", last_name=" + last_name + ", role=" + role
				+ ", username=" + username + ", passowrd=" + passowrd + ", locked=" + locked + "]";
	}
	
	
	
	
	
}
