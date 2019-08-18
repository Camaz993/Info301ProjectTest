package src.main.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = contract_stakeholders.TABLE_NAME)
public class contract_stakeholder {
	
	public static final String TABLE_NAME = "CONTRACT_STAKEHOLDERS";
	
	@NotNull
	@Column(name = "RequestID")
	private Integer requestid;
	
	@NotNull
	@Column(name = "Business_Name")
	private String businessname;
	
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
		this.businessname = businessname;
	}
	
	public String toString() {
		return "contract_stakeholder [requestid=" + requestid + ", businessname=" + businessname + "]";
	}
}
