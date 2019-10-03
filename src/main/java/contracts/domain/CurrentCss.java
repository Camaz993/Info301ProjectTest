package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = CurrentCss.TABLE_NAME)
//@SecondaryTable (name = Contract.TABLE_NAME)
public class CurrentCss {
	
	public static final String TABLE_NAME = "CURRENT_CSS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurrent_css")
	private Integer id;
	
	@Column(name = "background")
	private String background;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="button")
	private String button;
	
	@Column(name="links")
	private String links;
	
	public CurrentCss() {}

	public CurrentCss(Integer id, String background, String logo, String button, String links) {
		super();
		this.id = id;
		this.background = background;
		this.logo = logo;
		this.button = button;
		this.links = links;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "CurrentCss [id=" + id + ", background=" + background + ", logo=" + logo + ", button=" + button
				+ ", links=" + links + "]";
	}
	
	
}
