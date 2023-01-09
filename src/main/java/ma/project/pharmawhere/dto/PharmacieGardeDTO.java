package ma.project.pharmawhere.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.project.pharmawhere.model.Garde;
import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.PharmacieGardePK;

@Data
@NoArgsConstructor
public class PharmacieGardeDTO {
	private PharmacieGardePK pk;
	private Date dateDebut;
	private Date dateFin;
	private Pharmacie pharmacie;
	private Garde garde;
}
