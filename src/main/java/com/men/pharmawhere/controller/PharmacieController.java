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

import com.men.pharmawhere.entity.Garde;
import com.men.pharmawhere.entity.Pharmacie;
import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.repository.PharmacieRepository;

@RestController
@RequestMapping("pharmacie")
public class PharmacieController {
	@Autowired
	private PharmacieRepository pharmacieRepository;
	
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
	public Pharmacie update(@PathVariable(required = true) int id) {
		Pharmacie pharmacie = pharmacieRepository.findById(id);
		return pharmacieRepository.save(pharmacie);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Pharmacie pharmacie = pharmacieRepository.findById(id);
		pharmacieRepository.delete(pharmacie);
	}
	
	
	
}
