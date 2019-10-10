package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Id;

@Entity
@Table(name = StatusLink.TABLE_NAME)

public class StatusLink{
	
	public static final String TABLE_NAME = "STATUS_LINK";
	
	public StatusLink () {
		
	}
	
	@Id
	@Column(name = "requestid")
	private Integer requestid;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Contract contract;
	
	@NotNull
	@Column(name="currentstatus")
	private String currentstatus;

	public StatusLink(Integer requestid, String currentstatus) {
		this.requestid = requestid;
		this.currentstatus = currentstatus;
	}
	
	//Getters and Setters
	@Override
	public String toString() {
		return "status_link [requestid=" + requestid + ", currentstatus=" + currentstatus + "]";
	}

	public Integer getRequestid() {
		return requestid;
	}

	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}

	public String getCurrentstatus() {
		return currentstatus;
	}

	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}

}
