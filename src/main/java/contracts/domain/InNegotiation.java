package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = InNegotiation.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class InNegotiation {
	
	public static final String TABLE_NAME = "IN_NEGOTIATION";
	
	public InNegotiation() {
		
	}
	
	@Id
	@Column(name = "requestid")
	private Integer requestid;
	
	@Column(name = "comments")
	private String comments;
	
	@OneToOne
    @MapsId
	private Contract contract;

	public InNegotiation(Integer requestid, String comments, Contract contract) {
		super();
		this.requestid = requestid;
		this.comments = comments;
		this.contract = contract;
	}
	
	public Integer getRequestId() {
		return requestid;
	}
	
	public void setRequestId(Integer requestid) {
		this.requestid = requestid;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public void setContract() {
		this.contract = contract;
	}
}
