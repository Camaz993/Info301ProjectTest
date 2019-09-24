package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = InNegotiation.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class InNegotiation {
	
	public static final String TABLE_NAME = "IN_NEGOTIATION";
	
	@Id
	@Column(name = "fk_requestid_in_negotiation")
	private Integer requestid;
	
	@Column(name = "comments")
	private String comments;

	public InNegotiation(Integer requestid, String comments) {
		super();
		this.requestid = requestid;
		this.comments = comments;
	}
	
	public InNegotiation() {
		
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
}
