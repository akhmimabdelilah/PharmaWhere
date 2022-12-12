package com.men.pharmawhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.men.pharmawhere.dto.PharmacieDTO;
import com.men.pharmawhere.entity.Garde;
import com.men.pharmawhere.entity.Pharmacie;
import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.entity.Zone;
import com.men.pharmawhere.repository.PharmacieRepository;
import com.men.pharmawhere.repository.ZoneRepository;

@RestController
@RequestMapping("pharmacie")
public class PharmacieController {
	@Autowired
	private PharmacieRepository pharmacieRepository;
	@Autowired
	private ZoneRepository zoneRepository;

	@GetMapping("/")
	public List<Pharmacie> findAll() {
		return pharmacieRepository.findAll();
	}

	@PostMapping("/")
	public Pharmacie create(@RequestBody Pharmacie pharmacie) {
		return pharmacieRepository.save(pharmacie);
	}

	@GetMapping("/{id}")
	public Pharmacie findById(@PathVariable(required = true) int id) {
		return pharmacieRepository.findById(id);
	}

	@PutMapping("/{id}")
	public Pharmacie update(@PathVariable(required = true) int id, @RequestBody PharmacieDTO pharmacie) {
		Pharmacie thisPharmacie = pharmacieRepository.findById(id);

		if (pharmacie.getAdresse() != null)
			thisPharmacie.setAdresse(pharmacie.getAdresse());
		if (pharmacie.getLat() != 0)
			thisPharmacie.setLat(pharmacie.getLat());
		if (pharmacie.getLog() != 0)
			thisPharmacie.setLog(pharmacie.getLog());
		if (pharmacie.getNom() != null)
			thisPharmacie.setNom(pharmacie.getNom());
		if (pharmacie.getZone() != 0) {
			Zone zone = zoneRepository.findById(pharmacie.getZone());
			if (zone != null)
				thisPharmacie.setZone(zone);
		}
		return pharmacieRepository.save(thisPharmacie);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Pharmacie pharmacie = pharmacieRepository.findById(id);
		pharmacieRepository.delete(pharmacie);
	}

}
