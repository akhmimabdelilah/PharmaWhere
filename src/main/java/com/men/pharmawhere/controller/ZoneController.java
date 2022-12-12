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

import com.men.pharmawhere.dto.ZoneDTO;
import com.men.pharmawhere.entity.PharmacieGarde;
import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.entity.Zone;
import com.men.pharmawhere.repository.VilleRepository;
import com.men.pharmawhere.repository.ZoneRepository;

@RestController
@RequestMapping("zone")
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private VilleRepository villeRepository;

	@GetMapping("/")
	public List<Zone> findAll() {
		return zoneRepository.findAll();
	}

	@PostMapping("/")
	public Zone create(@RequestBody Zone zone) {
		return zoneRepository.save(zone);
	}

	@GetMapping("/{id}")
	public Zone findById(@PathVariable(required = true) int id) {
		return zoneRepository.findById(id);
	}

	@PutMapping("/{id}")
	public Zone update(@PathVariable(required = true) int id, @RequestBody ZoneDTO zone) {
		Zone thisZone = zoneRepository.findById(id);
		if (zone.getNom() != null)
			thisZone.setNom(zone.getNom());
		if (zone.getVille() != 0) {
			Ville ville = villeRepository.findById(zone.getVille());
			if (ville != null)
				thisZone.setVille(ville);
		}
		return zoneRepository.save(thisZone);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Zone zone = zoneRepository.findById(id);
		zoneRepository.delete(zone);
	}

}
