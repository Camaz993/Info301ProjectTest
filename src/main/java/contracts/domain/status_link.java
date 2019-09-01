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
@Table(name = status_link.TABLE_NAME)

public class status_link implements Serializable {
	
	public static final String TABLE_NAME = "STATUS_LINK";
	
	public status_link () {
		
	}
	
	@JoinColumn(name = "statusid")
	@OneToOne
	@NotNull
	@Id
	private status statusid;
	
	@JoinColumn (name = "statusid")
	@OneToOne
	@Null
	private status previousstatus;

	public status_link(status statusid, status previousstatus) {
		this.statusid = statusid;
		this.previousstatus = previousstatus;
	}
	
	//Getters and Setters
	
	public status getStatusid() {
		return statusid;
	}

	public void setStatusid(status statusid) {
		this.statusid = statusid;
	}

	public status getPreviousStatus() {
		return previousstatus;
	}

	public void setPreviousStatus(status previousstatus) {
		this.previousstatus = previousstatus;
	}
	
	@Override
	public String toString() {
		return "status_link [statusid=" + statusid + ", previousstatus=" + previousstatus + "]";
	}

}
