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

import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.entity.Zone;
import com.men.pharmawhere.repository.VilleRepository;

@RestController
@RequestMapping("ville")
public class VilleController {
	@Autowired
	private VilleRepository villeRepository;

	@GetMapping("/")
	public List<Ville> findAll() {
		return villeRepository.findAll();
	}

	@PostMapping("/")
	public Ville create(@RequestBody Ville ville) {
		return villeRepository.save(ville);
	}

	@GetMapping("/{id}")
	public Ville findById(@PathVariable(required = true) int id) {
		return villeRepository.findById(id);
	}

	@PutMapping("/{id}")
	public Ville update(@PathVariable(required = true) int id, @RequestBody Ville ville) {
		Ville thisVille = villeRepository.findById(id);
		if (ville.getNom() != null)
			thisVille.setNom(ville.getNom());
		return villeRepository.save(thisVille);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Ville ville = villeRepository.findById(id);
		villeRepository.delete(ville);
	}
	
	

}
