package contracts.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Audit.TABLE_NAME)
public class Audit {
	
	public static final String TABLE_NAME = "AUDIT";
	
	public Audit () {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auditid")
	private Integer auditid;
	
	@ManyToOne
	@JoinColumn(name = "fk_userid_audit")
	private User userid;
	
	@ManyToOne
	@JoinColumn(name = "fk_requestid_audit")
	private Contract requestid;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	@Column(name = "field_updated")
	private String field_updated;
	
	@Column(name = "field_before")
	private String field_before;
	
	@Column(name = "field_after")
	private String field_after;
	
	@Column(name = "add_contract")
	private String add_contract;
	
	@Column(name = "archived_contract")
	private String archived_contract;
	
	public String getAdd_contract() {
		return add_contract;
	}
	public void setAdd_contract(String add_contract) {
		this.add_contract = add_contract;
	}
	public Integer getAuditid() {
		return auditid;
	}
	public void setAuditid(Integer auditid) {
		this.auditid = auditid;
	}
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	public Contract getRequestedid() {
		return requestid;
	}
	public void setRequestedid(Contract requestid) {
		this.requestid = requestid;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getField_updated() {
		return field_updated;
	}
	public void setField_updated(String field_updated) {
		this.field_updated = field_updated;
	}
	public String getField_before() {
		return field_before;
	}
	public void setField_before(String field_before) {
		this.field_before = field_before;
	}
	public String getField_after() {
		return field_after;
	}
	public void setField_after(String field_after) {
		this.field_after = field_after;
	}
	public Audit(Integer auditid, User userid, Contract requestid, LocalDateTime date, String field_updated,
			String field_before, String field_after, String add_contract, String archived_contract) {
		super();
		this.auditid = auditid;
		this.userid = userid;
		this.requestid = requestid;
		this.date = date;
		this.field_updated = field_updated;
		this.field_before = field_before;
		this.field_after = field_after;
		this.add_contract = add_contract;
		this.archived_contract = archived_contract;
	}
	@Override
	public String toString() {
		return "audit [auditid=" + auditid + ", userid=" + userid + ", requestedid=" + requestid + ", date=" + date
				+ ", field_updated=" + field_updated + ", field_before=" + field_before + ", field_after=" + field_after
				+ "]";
	}
	public String getArchived_contract() {
		return archived_contract;
	}
	public void setArchived_contract(String archive_contract) {
		this.archived_contract = archive_contract;
	}
	
}
