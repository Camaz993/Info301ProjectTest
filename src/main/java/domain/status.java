package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = status.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class status {
	
	public static final String TABLE_NAME = "STATUS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statusid")
	//@OnetoOne(mappedBy = previousstatus)
	private Integer statusid;
	
	//Foreign key of statusid (self-referencing integrity)
	@OneToOne
	private status previousstatus;
	
	@NotNull
	@Column(name = "active")
	private String active;
	
	//Constructor
	public status(Integer statusid, status previousstatus, String active) {
		super();
		this.statusid = statusid;
		this.previousstatus = previousstatus;
		this.active = active;
	}

	//Getters and setters
	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public status getPreviousstatus() {
		return previousstatus;
	}

	public void setPreviousstatus(status previousstatus) {
		this.previousstatus = previousstatus;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "status [statusid=" + statusid + ", previousstatus=" + previousstatus + ", active=" + active + "]";
	}
	
	
	
}
