package com.men.pharmawhere.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PharmacieGardeDTO {
	private int pharmacieId, gardeId;
	private Date dateDebut;
	private Date dateFin;
}
