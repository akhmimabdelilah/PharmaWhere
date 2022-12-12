package com.men.pharmawhere.entity;

import java.util.Date;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacieGardePK {
	private int pharmacieId, gardeId;
	private Date dateDebut;
}
