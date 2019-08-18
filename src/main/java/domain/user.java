package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = user.TABLE_NAME)
//@SecondaryTable(name = Audit.TALBE_NAME)
//@SecondaryTable(name = Contract.TABLE_NAME)


public class user {
	
	public static final String TABLE_NAME = "USER";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Integer userid;
	
	@NotNull
	@Column(name="first_name")
	private String first_name;
	
	@NotNull
	@Column(name="last_name")
	private String last_name;
	
	@NotNull
	@Column(name="role")
	private String role;
	
	@NotNull
	@Column(name="username")
	private String username;
	
	@NotNull
	@Column(name="password")
	@Size(min = 8, max= 40, message = "Password must be between 8 and 40 characters")
	//@JSONIgnore
	private String password;
	
	 @NotNull
	 @Column(name = "locked")
	private boolean locked = false;
	
	//Constructor 
	public user(Integer userid, String first_name, String last_name, String role, String username, String password,
			boolean locked) {
		super();
		this.userid = userid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
		this.username = username;
		this.password = password;
		this.locked = locked;
		}
	 
	//Getters and setters
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
		return password;
	}
	public void setPassowrd(String passowrd) {
		this.password = passowrd;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	
	@Override
	public String toString() {
		return "user [userid=" + userid + ", first_name=" + first_name + ", last_name=" + last_name + ", role=" + role
				+ ", username=" + username + ", passowrd=" + password + ", locked=" + locked + "]";
	}
	
	
	
	
	
}
