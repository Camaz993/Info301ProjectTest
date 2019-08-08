package main.domain;

public class external_stakeholders {
	private String businessname;
	private String address;
	private String phone;
	private String email;
	private String fax;
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
