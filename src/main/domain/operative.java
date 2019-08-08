package main.domain;
import java.util.Date;

public class operative {
	//private Integer requestid;
	private Date date_signed;
	private Date date_commenced;
	private Date date_expire;
	private String verification;
	
	public operative(Integer requestid, Date date_signed, Date date_commenced, Date date_expire, String verification) {
		super();
		//this.requestid = requestid;
		this.date_signed = date_signed;
		this.date_commenced = date_commenced;
		this.date_expire = date_expire;
		this.verification = verification;
	}

	public Date getDate_signed() {
		return date_signed;
	}

	public void setDate_signed(Date date_signed) {
		this.date_signed = date_signed;
	}

	public Date getDate_commenced() {
		return date_commenced;
	}

	public void setDate_commenced(Date date_commenced) {
		this.date_commenced = date_commenced;
	}

	public Date getDate_expire() {
		return date_expire;
	}

	public void setDate_expire(Date date_expire) {
		this.date_expire = date_expire;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	@Override
	public String toString() {
		return "operative [date_signed=" + date_signed + ", date_commenced=" + date_commenced + ", date_expire="
				+ date_expire + ", verification=" + verification + "]";
	}
	
	
}
