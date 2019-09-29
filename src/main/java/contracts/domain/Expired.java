package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Expired.TABLE_NAME)
public class Expired {
	
	public static final String TABLE_NAME = "EXPIRED";

	@Id
	@Column(name = "requestid")
	private Integer requestid;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Contract contract;
	
	@NotNull
	@Column(name = "ending_reason")
	private String ending_reason;
	
	@NotNull
	@Column(name = "expiry_date")
	private String expiry_date;
	
	@NotNull
	@Column(name = "termination_date")
	private String termination_date;
	
	@NotNull
	@Column(name = "termination_notice_date")
	private String termination_notice_date;
	
	@NotNull
	@Column(name = "binding_terms_active")
	private String binding_terms_active;
	
	
	public Expired() {}
	
	public Expired(Integer requestid, String ending_reason, String expiry_date, String termination_date,
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

	public void setRequestId(Integer requestid) {
		this.requestid = requestid;
	}

	public String getEnding_reason() {
		return ending_reason;
	}

	public void setEnding_reason(String ending_reason) {
		this.ending_reason = ending_reason;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getTermination_date() {
		return termination_date;
	}

	public void setTermination_date(String termination_date) {
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
	
	public Contract getContract() {
		return contract;
	}
	
	public void setContract(Contract contract) {
		this.contract=contract;
	}

	@Override
	public String toString() {
		return "expired [requestid=" + requestid + ", ending_reason=" + ending_reason + ", expiry_date=" + expiry_date
				+ ", termination_date=" + termination_date + ", termination_notice_date=" + termination_notice_date
				+ ", binding_terms_active=" + binding_terms_active + "]";
	}
	
	
}
