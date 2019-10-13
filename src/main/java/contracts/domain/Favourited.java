/** 
** The favourited domain class includes all of the columns that match the database along with the getter and setter methods, and the to string method
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Favourited.TABLE_NAME)
public class Favourited {
	
	public static final String TABLE_NAME = "FAVOURITED";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favouritedid")
	private Integer favouritedid;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "requestid")
	private Contract contract;
	
	public Favourited(Integer favouritedid, User user, Contract contract) {
		super();
		this.favouritedid = favouritedid;
		this.user = user;
		this.contract = contract;
	}
	
	public Favourited() {
		
	}
	
	public Integer getFavouritedid() {
		return favouritedid;
	}
	
	public void setFavouritedid(Integer favouritedid) {
		this.favouritedid = favouritedid;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;	
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	

}
