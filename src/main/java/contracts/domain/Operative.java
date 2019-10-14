/** 
** The operative domain class includes all of the columns that match the database along with the getter and setter methods, and the to string method
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = Operative.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class Operative {
	
	public static final String TABLE_NAME = "OPERATIVE";
	
	@Id
	@Column(name = "requestid")
	private Integer requestid;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Contract contract;
	
	@Column(name = "date_signed")
	private String date_signed;
	
	@Column(name = "date_commenced")
	private String date_commenced;
	
	@Column(name = "date_expire")
	private String date_expire;
	
	@Column(name = "verification")
	private String verification;
	
	public Operative() {}
	
	//Constructor method
	public Operative(Integer requestid, String date_signed, String date_commenced, String date_expire, String verification) {
		super();
		this.requestid = requestid;
		this.date_signed = date_signed;
		this.date_commenced = date_commenced;
		this.date_expire = date_expire;
		this.verification = verification;
	}

	//Getters and setters
	public Integer getRequestId() {
		return requestid;
	}

	public void setRequestId(Integer requestid) {
		this.requestid = requestid;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public String getDate_signed() {
		return date_signed;
	}

	public void setDate_signed(String date_signed) {
		this.date_signed = date_signed;
	}

	public String getDate_commenced() {
		return date_commenced;
	}

	public void setDate_commenced(String date_commenced) {
		this.date_commenced = date_commenced;
	}

	public String getDate_expire() {
		return date_expire;
	}

	public void setDate_expire(String date_expire) {
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
