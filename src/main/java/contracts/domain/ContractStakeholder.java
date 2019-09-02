package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Entity
@Table(name = ContractStakeholder.TABLE_NAME)
public class ContractStakeholder {
	
	public static final String TABLE_NAME = "CONTRACT_STAKEHOLDERS";
	
	public ContractStakeholder () {
		
	}
	
	@Id
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
	
	public ContractStakeholder(Integer requestid, String businessname) {
		super();
		this.requestid = requestid;
		this.businessname = businessname;
	}
	
	public String toString() {
		return "contract_stakeholder [requestid=" + requestid + ", businessname=" + businessname + "]";
	}
}
