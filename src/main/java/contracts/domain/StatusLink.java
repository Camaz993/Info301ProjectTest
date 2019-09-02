package contracts.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Id;

@Entity
@Table(name = StatusLink.TABLE_NAME)

public class StatusLink implements Serializable {
	
	public static final String TABLE_NAME = "STATUS_LINK";
	
	public StatusLink () {
		
	}
	
	@JoinColumn(name = "statusid")
	@OneToOne
	@NotNull
	@Id
	private Status statusid;
	
	@JoinColumn (name = "statusid")
	@OneToOne
	@Null
	private Status previousstatus;

	public StatusLink(Status statusid, Status previousstatus) {
		this.statusid = statusid;
		this.previousstatus = previousstatus;
	}
	
	//Getters and Setters
	
	public Status getStatusid() {
		return statusid;
	}

	public void setStatusid(Status statusid) {
		this.statusid = statusid;
	}

	public Status getPreviousStatus() {
		return previousstatus;
	}

	public void setPreviousStatus(Status previousstatus) {
		this.previousstatus = previousstatus;
	}
	
	@Override
	public String toString() {
		return "status_link [statusid=" + statusid + ", previousstatus=" + previousstatus + "]";
	}

}
