package main.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = contract.TABLE_NAME)
public class contract {
	
	public static final String TABLE_NAME = "CONTRACT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequestID")
	private Integer requestid;
	
	@ManyToOne
	private Integer userid;
	
	@NotNull
	@Column(name = "Status_ID")
	private Integer statusid;
	
	@NotNull
	@Column(name = "Agreement_Title")
	private String agreement_title;
	
	@NotNull
	@Column(name = "Agreement_Type")
	private String agreement_type;
	
	@NotNull
	@Column(name = "Description")
	private String description;
	
	@NotNull
	@Column(name = "Agreement_Location")
	private String agreement_location;
	
	@NotNull
	@Column(name = "Language")
	private String language;
	
	@NotNull
	@Column(name = "Region")
	private String region;
	
	@NotNull
	@Column(name = "Related_Agreements")
	private String related_agreements;
	
	public contract(Integer requestid, Integer userid, Integer statusid, String agreement_title, String agreement_type,
			String description, String agreement_location, String language, String region, String related_agreements) {
		super();
		this.requestid = requestid;
		this.userid = userid;
		this.statusid = statusid;
		this.agreement_title = agreement_title;
		this.agreement_type = agreement_type;
		this.description = description;
		this.agreement_location = agreement_location;
		this.language = language;
		this.region = region;
		this.related_agreements = related_agreements;
	}
	
	public contract() {
		
	}
	
	public Integer getRequestid() {
		return requestid;
	}
	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getStatusid() {
		return statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public String getAgreement_title() {
		return agreement_title;
	}
	public void setAgreement_title(String agreement_title) {
		this.agreement_title = agreement_title;
	}
	public String getAgreement_type() {
		return agreement_type;
	}
	public void setAgreement_type(String agreement_type) {
		this.agreement_type = agreement_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAgreement_location() {
		return agreement_location;
	}
	public void setAgreement_location(String agreement_location) {
		this.agreement_location = agreement_location;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRelated_agreements() {
		return related_agreements;
	}
	public void setRelated_agreements(String related_agreements) {
		this.related_agreements = related_agreements;
	}

	@Override
	public String toString() {
		return "contract [requestid=" + requestid + ", userid=" + userid + ", statusid=" + statusid
				+ ", agreement_title=" + agreement_title + ", agreement_type=" + agreement_type + ", description="
				+ description + ", agreement_location=" + agreement_location + ", language=" + language + ", region="
				+ region + ", related_agreements=" + related_agreements + "]";
	}
	
	
}
