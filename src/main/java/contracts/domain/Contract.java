package contracts.domain;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	
	@Column(name = "businessname")
	private String businessname;

	@Column(name = "clientname")
	private String clientname;
	
	@Column(name = "Address", length = 128)
	@Size(min = 5, max = 128, message = "Address must contain between 10 and 128 characters.")
	private String address;
	
	@Column(name = "Phone", length = 11)
	@Size(min = 5, max = 11, message = "Phone number length must be between 9 and 11 characters. Be sure to include the area code but not country code.")
	@Pattern(regexp = "([0-9]){9,11}", message = "Phone number must be numerical")
	private String phone;
	
	@Column(name = "Email", length = 254)
	@Size(min = 3, max = 254, message = "Email address must contain between 3 and 254 characters.")
	// Email address regex from http://emailregex.com (works for 99.99% of email
	// addresses - as good as it gets!)
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Email address must be valid.")
	private String email;

	@Column(name = "Fax")
	private String fax;
	
	@NotBlank(message="Language cannot be empty")
	@Column(name = "Language")
	private String language;
	
	@NotBlank(message="Region cannot be empty")
	@Column(name = "Region")
	private String region;
	
	@NotBlank(message="Related agreements cannot be null")
	@Column(name = "Related_Agreements")
	private String related_agreements;
	
	@Column(name = "archived")
	private String archived;
	
	@Column(name = "favourited")
	private String favourited;
	
	@Column(name = "date_updated")
	private Date date_updated;
	
	public Contract(Integer requestid, User user, List<Status> statusid, String agreement_title, String agreement_type,
			String description, String agreement_location, String businessname, String clientname, String address, String phone, String email, String fax,
		 String language, String region, String related_agreements, String archived,
			String favourited, Date date_updated) {
		super();
		this.requestid = requestid;
		this.user = user;
		this.statusid = statusid;
		this.agreement_title = agreement_title;
		this.agreement_type = agreement_type;
		this.description = description;
		this.agreement_location = agreement_location;
		this.businessname = businessname;
		this.clientname = clientname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
		this.language = language;
		this.region = region;
		this.related_agreements = related_agreements;
		this.archived = archived;
		this.favourited = favourited;
		this.date_updated = date_updated;
	}
	
	public Contract() {
		
	} 

	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
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
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	
	public String getArchived() {
		return archived;
	}
	
	public void setArchived(String archived) {
		this.archived = archived;
	}

	public String getFavourited() {
		return favourited;
	}
	
	public void setFavourited(String favourited) {
		this.favourited = favourited;
	}
	
	

	
	@Override
	public String toString() {
		return "contract [requestid=" + requestid + ", userid=" + user + ", statusid=" + statusid
				+ ", agreement_title=" + agreement_title + ", agreement_type=" + agreement_type + ", description="
				+ description + ", agreement_location=" + agreement_location + ", businessname=" + businessname +", clientname=" + clientname
				+", address=" + address +", phone=" + phone +", email=" + email +", fax=" + fax +", language=" + language + ", region="
				+ region + ", related_agreements=" + related_agreements + ", archived=" + archived + ", favourited=" + favourited + "]";
	}
}
