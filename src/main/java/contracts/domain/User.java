package contracts.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
//@SecondaryTable(name = Audit.TALBE_NAME)
//@SecondaryTable(name = Contract.TABLE_NAME)


public class User {
	
	public static final String TABLE_NAME = "USER";
	
	public User () {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Integer userid;
	
	@NotBlank(message="Name cannot be blank")
	@Column(name="firstname")
	private String firstname;
	
	@NotBlank(message="Name cannot be blank")
	@Column(name="lastname")
	private String lastname;
	
	@NotBlank(message="Role cannot be blank")
	@Column(name="role")
	private String role;
	
	@NotBlank(message="Username cannot be blank")
	@Column(name="username")
	private String username;
	
	@NotBlank(message="Password cannot be blank")
	@Column(name="password")
	@Size(min = 8, max= 40, message = "Password must be between 8 and 40 characters")
	//@JSONIgnore
	private String password;
	
	@NotBlank(message="You must enter a matching password")
	@Column(name="passrepeat")
	private String passrepeat;
	
	
	 @NotNull
	 @Column(name = "locked")
	private boolean locked = false;
	
	//Constructor 
	public User(Integer userid, String firstname, String lastname, String role, String username, String password, String passrepeat,
			boolean locked) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.username = username;
		this.password = password;
		this.passrepeat = passrepeat;
		this.locked = locked;
		}
	 
	//Getters and setters
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassrepeat() {
		return passrepeat;
	}
	public void setPassrepeat(String passrepeat) {
		this.passrepeat = passrepeat;
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
