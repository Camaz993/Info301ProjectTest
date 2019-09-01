package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = status.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class status {
	
	public static final String TABLE_NAME = "STATUS";
	
	public status() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statusid")
	private Integer statusid;
	
	@NotNull
	@Column(name = "active")
	private String active;
	
	//Constructor
	public status(Integer statusid, String active) {
		super();
		this.statusid = statusid;
		this.active = active;
	}

	//Getters and setters
	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "status [statusid=" + statusid + ", active=" + active + "]";
	}
	
	
	
}
