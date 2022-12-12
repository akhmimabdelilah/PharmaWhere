package com.men.pharmawhere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacieDTO {
	private int id;
	private String nom;
	private String adresse;
	private double lat,log;
	private int zone;
}
