package ma.project.pharmawhere.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pharmacie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String adresse;
	private double lat, log;

	@Enumerated(EnumType.ORDINAL)
	private PharmacieStatus status = PharmacieStatus.ENATTEND;
	@ManyToOne
	private Zone zone;

	@OneToMany(mappedBy = "pharmacie", fetch = FetchType.EAGER)
	private List<PharmacieGarde> gardes;

	@Lob
	private String image;

	@ManyToOne()
	@JsonIgnore
	private User user;
}
