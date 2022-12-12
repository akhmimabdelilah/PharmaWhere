package com.men.pharmawhere.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.men.pharmawhere.dto.PharmacieGardeDTO;
import com.men.pharmawhere.entity.Garde;
import com.men.pharmawhere.entity.Pharmacie;
import com.men.pharmawhere.entity.PharmacieGarde;
import com.men.pharmawhere.entity.PharmacieGardePK;
import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.repository.GardeRepository;
import com.men.pharmawhere.repository.PharmacieGardeRepository;
import com.men.pharmawhere.repository.PharmacieRepository;

@RestController
@RequestMapping("pharmaciegarde")
public class PharmacieGardeController {
	@Autowired
	private PharmacieGardeRepository pharmacieGardeRepository;
	@Autowired
	private PharmacieRepository pharmacieRepository;
	@Autowired
	private GardeRepository gardeRepository;

	@GetMapping("/")
	public List<PharmacieGarde> findAll() {
		return pharmacieGardeRepository.findAll();
	}

	@PostMapping("/")
	public PharmacieGarde create(@RequestBody PharmacieGardeDTO pharmacieGarde) {
		PharmacieGardePK pk = new PharmacieGardePK(pharmacieGarde.getPharmacieId(), pharmacieGarde.getGardeId(),
				pharmacieGarde.getDateDebut());
		Pharmacie pharmacie = pharmacieRepository.findById(pharmacieGarde.getPharmacieId());
		Garde garde = gardeRepository.findById(pharmacieGarde.getGardeId());
		PharmacieGarde pGarde = new PharmacieGarde(pk, pharmacieGarde.getDateDebut(), pharmacieGarde.getDateFin(),
				pharmacie, garde);
		return pharmacieGardeRepository.save(pGarde);
	}

	@PostMapping("/one")
	public PharmacieGarde findId(@RequestBody PharmacieGardePK pharmacieGarde) {
		Optional<PharmacieGarde> garde = pharmacieGardeRepository.findById(pharmacieGarde);
		return garde.get();
	}

}
