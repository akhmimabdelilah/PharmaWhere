package ma.project.pharmawhere.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacieGardePK implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pharmacieId, gardeId;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
}
