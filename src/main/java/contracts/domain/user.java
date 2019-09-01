package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = user.TABLE_NAME)
//@SecondaryTable(name = Audit.TALBE_NAME)
//@SecondaryTable(name = Contract.TABLE_NAME)


public class user {
	
	public static final String TABLE_NAME = "USER";
	
	public user () {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Integer userid;
	
	@NotNull
	@Column(name="firstname")
	private String firstname;
	
	@NotNull
	@Column(name="lastname")
	private String lastname;
	
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
	public user(Integer userid, String firstname, String lastname, String role, String username, String password,
			boolean locked) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
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
		return firstname;
	}
	public void setFirst_name(String firstname) {
		this.firstname = firstname;
	}
	public String getLast_name() {
		return lastname;
	}
	public void setLast_name(String lastname) {
		this.lastname = lastname;
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
		return "user [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", role=" + role
				+ ", username=" + username + ", passowrd=" + password + ", locked=" + locked + "]";
	}
	
	
}
