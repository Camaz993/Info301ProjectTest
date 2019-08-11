package main.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = external_stakeholders.TABLE_NAME)
public class external_stakeholders {
	
	public static final String TABLE_NAME = "EXTERNAL_STAKEHOLDERS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Business_Name")
	private String businessname;
	
	@Column(name = "ADDRESS", length = 128)
	@Size(min = 10, max = 128, message = "Address must contain between 10 and 128 characters.")
	private String address;
	
	@Column(name = "PHONE", length = 11)
	@Size(min = 9, max = 11, message = "Phone number length must be between 9 and 11 characters. Be sure to include the area code but not country code.")
	@Pattern(regexp = "([0-9]){9,11}", message = "Phone number must be numerical")
	private String phone;
	
	@NotNull
	@Column(name = "EMAIL", length = 254)
	@Size(min = 3, max = 254, message = "Email address must contain between 3 and 254 characters.")
	// Email address regex from http://emailregex.com (works for 99.99% of email
	// addresses - as good as it gets!)
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Email address must be valid.")
	private String email;
	
	@NotNull
	@Column(name = "Fax")
	private String fax;
	
	@NotNull
	@Column(name = "Client_Name")
	private String client_name;
	
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public external_stakeholders(String businessname, String address, String phone, String email, String fax,
			String client_name) {
		super();
		this.businessname = businessname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
		this.client_name = client_name;
	}
	@Override
	public String toString() {
		return "external_stakeholders [businessname=" + businessname + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", fax=" + fax + ", client_name=" + client_name + "]";
	}
	
	
}
