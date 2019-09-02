package contracts.domain;

import java.util.Date;

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
	@Column(name = "AuditID")
	private Integer auditid;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User userid;
	
	@ManyToOne
	@JoinColumn(name = "requestid")
	private Contract requestid;
	
	@NotNull
	@Column(name = "Date")
	private Date date;
	
	@NotNull
	@Column(name = "Field_Updated")
	private String field_updated;
	
	@NotNull
	@Column(name = "Field_Before")
	private String field_before;
	
	@NotNull
	@Column(name = "Field_After")
	private String field_after;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public Audit(Integer auditid, User userid, Contract requestid, Date date, String field_updated,
			String field_before, String field_after) {
		super();
		this.auditid = auditid;
		this.userid = userid;
		this.requestid = requestid;
		this.date = date;
		this.field_updated = field_updated;
		this.field_before = field_before;
		this.field_after = field_after;
	}
	@Override
	public String toString() {
		return "audit [auditid=" + auditid + ", userid=" + userid + ", requestedid=" + requestid + ", date=" + date
				+ ", field_updated=" + field_updated + ", field_before=" + field_before + ", field_after=" + field_after
				+ "]";
	}
	
}
