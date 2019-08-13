package main.domain;
import java.util.Date;

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
@Table(name = in_negotation.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class in_negotiation {
	
	@Id
	@Column(name = "requestid")
	private Integer requestid;
	
	@Column(name = "comments")
	private String comments;
	
	@OneToOne
    @MapsId
	private Contract contract;
	
}

public operative(Integer requestid, String comments, Contract contract) {
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