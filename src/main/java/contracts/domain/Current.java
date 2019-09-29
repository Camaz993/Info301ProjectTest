package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = Current.TABLE_NAME)
public class Current {
	
	public static final String TABLE_NAME = "current_css";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurrent_css")
	private Integer idcurrent_css;
	
	@Column(name = "background")
	private String background;
	
	@Column(name = "button")
	private String button;
	
	@Column(name = "links")
	private String links;
	
	public Current(Integer idcurrent_css, String background, String button, String links) {
		super();
		this.idcurrent_css = idcurrent_css;
		this.background = background;
		this.button = button;
		this.links = links;
	}
	
	public Current() {
		
	}
	
	public Integer getIdcurrent_css() {
		return idcurrent_css;
	}
	
	public void setIdcurrent_css(Integer idcurrent_css) {
		this.idcurrent_css = idcurrent_css;
	}
	
	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
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
		return "current [idcurrent_css=" + idcurrent_css + ", background=" + background + "]";
	}
	
}
