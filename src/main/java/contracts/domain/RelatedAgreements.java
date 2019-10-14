/** 
** The relatedagreements domain class includes all of the columns that match the database along with the getter and setter methods, and the to string method
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
@Table(name = RelatedAgreements.TABLE_NAME)
public class RelatedAgreements {
		
		public static final String TABLE_NAME = "RELATED_AGREEMENTS";
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer idrelated_agreements;
		
		@ManyToOne
		@JoinColumn(name = "requestid_related")
		private Contract requestid_related;
		
		@Column(name = "requestid_relatedto")
		private Integer requestid_relatedto; 
		
		public RelatedAgreements(Integer idrelated_agreements, Contract requestid_related, Integer requestid_relatedto) {
			super();
			this.idrelated_agreements = idrelated_agreements;
			this.requestid_related = requestid_related;
			this.requestid_relatedto = requestid_relatedto;
		}
		
		public RelatedAgreements() {
			
		}

		public Integer getIdrelated_agreements() {
			return idrelated_agreements;
		}

		public void setIdrelated_agreements(Integer idrelated_agreements) {
			this.idrelated_agreements = idrelated_agreements;
		}

		public Contract getRequestid_related() {
			return requestid_related;
		}

		public void setRequestid_related(Contract requestid_related) {
			this.requestid_related = requestid_related;
		}

		public Integer getRequestid_relatedto() {
			return requestid_relatedto;
		}

		public void setRequestid_relatedto(Integer requestid_relatedto) {
			this.requestid_relatedto = requestid_relatedto;
		}
		
}

