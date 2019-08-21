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
@Table(name = audit.TABLE_NAME)
public class audit {
	
	public static final String TABLE_NAME = "AUDIT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AuditID")
	private Integer auditid;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private user userid;
	
	@ManyToOne
	@JoinColumn(name = "requestid")
	private contract requestid;
	
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
	public user getUserid() {
		return userid;
	}
	public void setUserid(user userid) {
		this.userid = userid;
	}
	public contract getRequestedid() {
		return requestid;
	}
	public void setRequestedid(contract requestid) {
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
	public audit(Integer auditid, user userid, contract requestid, Date date, String field_updated,
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
