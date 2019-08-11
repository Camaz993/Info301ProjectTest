package main.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class contract_stakeholder {
	
	@NotNull
	@Column(name = "RequestID")
	private Integer requestid;
	
	@NotNull
	@Column(name = "Business_Name")
	private String bussinessname;
	
	public Integer getRequestid() {
		return requestid;
	}
	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}
	
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	
	public contract_stakeholder(Integer requestid, String businessname) {
		super();
		this.requestid = requestid;
		this.bussinessname = businessname;
	}
	
	public String toString() {
		return "contract_stakeholder [requestid=" + requestid + ", businessname=" + businessname + "]";
	}
}
