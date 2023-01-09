package ma.project.pharmawhere.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacieGarde {
	@EmbeddedId
	private PharmacieGardePK pk;

	@Temporal(TemporalType.DATE)
	@Column( insertable = false, updatable = false)
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "pharmacieId", insertable = false, updatable = false)
	private Pharmacie pharmacie;

	@ManyToOne
	@JoinColumn(name = "gardeId", insertable = false, updatable = false)
	private Garde garde;
}
