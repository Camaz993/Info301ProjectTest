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
	
	@Column(name = "colour")
	private String colour;
	
	public CurrentCss() {}

	public CurrentCss(Integer id, String colour) {
		super();
		this.id = id;
		this.colour = colour;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "CurrentCss [id=" + id;
	}
	
	
}
