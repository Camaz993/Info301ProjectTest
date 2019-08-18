package contracts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = expired.TABLE_NAME)
public class expired {
	
	public static final String TABLE_NAME = "EXPIRED";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequestID")
	private Integer requestid;
	
	@NotNull
	@Column(name = "Ending_Reason")
	private String ending_reason;
	
	@NotNull
	@Column(name = "Expiry_Date")
	private Date expiry_date;
	
	@NotNull
	@Column(name = "Termination_Date")
	private Date termination_date;
	
	@NotNull
	@Column(name = "Termination_Notice_Date")
	private String termination_notice_date;
	
	@NotNull
	@Column(name = "Binding_Terms_Active")
	private String binding_terms_active;
	
	public expired(Integer requestid, String ending_reason, Date expiry_date, Date termination_date,
			String termination_notice_date, String binding_terms_active) {
		super();
		this.requestid = requestid;
		this.ending_reason = ending_reason;
		this.expiry_date = expiry_date;
		this.termination_date = termination_date;
		this.termination_notice_date = termination_notice_date;
		this.binding_terms_active = binding_terms_active;
	}

	public Integer getRequestid() {
		return requestid;
	}

	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}

	public String getEnding_reason() {
		return ending_reason;
	}

	public void setEnding_reason(String ending_reason) {
		this.ending_reason = ending_reason;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Date getTermination_date() {
		return termination_date;
	}

	public void setTermination_date(Date termination_date) {
		this.termination_date = termination_date;
	}

	public String getTermination_notice_date() {
		return termination_notice_date;
	}

	public void setTermination_notice_date(String termination_notice_date) {
		this.termination_notice_date = termination_notice_date;
	}

	public String getBinding_terms_active() {
		return binding_terms_active;
	}

	public void setBinding_terms_active(String binding_terms_active) {
		this.binding_terms_active = binding_terms_active;
	}

	@Override
	public String toString() {
		return "expired [requestid=" + requestid + ", ending_reason=" + ending_reason + ", expiry_date=" + expiry_date
				+ ", termination_date=" + termination_date + ", termination_notice_date=" + termination_notice_date
				+ ", binding_terms_active=" + binding_terms_active + "]";
	}
	
	
}
