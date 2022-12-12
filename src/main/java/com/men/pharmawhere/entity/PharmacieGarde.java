package com.men.pharmawhere.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	@JoinColumn(name = "pharmacieId", insertable = false, updatable = false)
	private Pharmacie pharmacie;

	@ManyToOne
	@JoinColumn(name = "gardeId", insertable = false, updatable = false)
	private Garde garde;
}
