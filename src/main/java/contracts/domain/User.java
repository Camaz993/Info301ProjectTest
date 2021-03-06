/** 
** The user domain class includes all of the columns that match the database along with the getter and setter methods, and the to string method
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	@Size(min=4, max=50, message="Username must be between 4 and 50 characters")
	@Column(name="username")
	private String username;
	
	@NotBlank(message="Password cannot be blank")
	@Column(name="password")
	//@Size(min = 8, max= 40, message = "Password must be between 8 and 40 characters")
	//@JSONIgnore
	private String password;
	
	@NotBlank(message="You must enter a matching password")
	@Column(name="passrepeat")
	private String passrepeat;
	
	 @NotNull
	 @Column(name = "locked")
	private boolean locked;
	 
	// Email address regex from http://emailregex.com (works for 99.99% of email
	// addresses - as good as it gets!)
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Email address must be valid.")
	 @Column(name = "email")
	 private String email;
	 
	 @Column(name = "expirydate")
	 private Date expirydate;
	
	//Constructor 
	public User(Integer userid, String firstname, String lastname, String role, String username, String password, String passrepeat,
			boolean locked, String email, Date expirydate) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.username = username;
		this.password = password;
		this.passrepeat = passrepeat;
		this.locked = locked;
		this.email = email;
		this.expirydate = expirydate;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getExpiryDate() {
		return expirydate;
	}
	
	public void setExpiryDate(Date expirydate) {
		this.expirydate = expirydate;
	}
	
	@Override
	public String toString() {
		return username;
	}
	
	
}
