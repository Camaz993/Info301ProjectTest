package contracts.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import javax.persistence.JoinColumn;

@Entity
@Table(name = Contract.TABLE_NAME)
public class Contract {
	
	public static final String TABLE_NAME = "CONTRACT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "requestid")
	private Integer requestid;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@OneToMany
	@JoinColumn(name = "statusid")
	private List<Status> statusid = new ArrayList<Status>();
	
	@NotBlank(message="Agreement title cannot be empty")
	@Column(name = "Agreement_Title")
	private String agreement_title;
	
	@NotBlank(message="Agreement type cannot be empty")
	@Column(name = "Agreement_Type")
	private String agreement_type;
	
	@NotBlank(message="Description cannot be empty")
	@Column(name = "Description")
	private String description;
	
	@NotBlank(message="Agreement location cannot be empty")
	@Column(name = "Agreement_Location")
	private String agreement_location;
	
	@NotBlank(message="Language cannot be empty")
	@Column(name = "Language")
	private String language;
	
	@NotBlank(message="Region cannot be empty")
	@Column(name = "Region")
	private String region;
	
	@NotBlank(message="Related agreements cannot be null")
	@Column(name = "Related_Agreements")
	private String related_agreements;
	
	public Contract(Integer requestid, User user, List<Status> statusid, String agreement_title, String agreement_type,
			String description, String agreement_location, String language, String region, String related_agreements) {
		super();
		this.requestid = requestid;
		this.user = user;
		this.statusid = statusid;
		this.agreement_title = agreement_title;
		this.agreement_type = agreement_type;
		this.description = description;
		this.agreement_location = agreement_location;
		this.language = language;
		this.region = region;
		this.related_agreements = related_agreements;
	}
	
	public Contract() {
		
	}
	
	public Integer getRequestid() {
		return requestid;
	}
	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}
	public User getUser() {
		return user;
	}

	public void setUserid(User user) {
		this.user = user;
	}
	public List<Status> getStatusList() {
		return statusid;
	}
	public void setStatusid(List<Status> statusid) {
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
		return "contract [requestid=" + requestid + ", userid=" + user + ", statusid=" + statusid
				+ ", agreement_title=" + agreement_title + ", agreement_type=" + agreement_type + ", description="
				+ description + ", agreement_location=" + agreement_location + ", language=" + language + ", region="
				+ region + ", related_agreements=" + related_agreements + "]";
	}
	
	
}
