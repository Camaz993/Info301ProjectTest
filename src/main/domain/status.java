package main.domain;

public class status {
	private Integer statusid;
	private Integer previousstatus;
	private String active;
	
	public status(Integer statusid, Integer previousstatus, String active) {
		super();
		this.statusid = statusid;
		this.previousstatus = previousstatus;
		this.active = active;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public Integer getPreviousstatus() {
		return previousstatus;
	}

	public void setPreviousstatus(Integer previousstatus) {
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
