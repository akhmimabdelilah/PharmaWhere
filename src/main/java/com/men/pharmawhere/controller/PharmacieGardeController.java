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

import com.men.pharmawhere.entity.Pharmacie;
import com.men.pharmawhere.entity.PharmacieGarde;
import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.repository.PharmacieGardeRepository;

@RestController
@RequestMapping("pharmaciegarde")
public class PharmacieGardeController {
	@Autowired
	private PharmacieGardeRepository pharmacieGardeRepository;
	
	@GetMapping("/")
	public List<PharmacieGarde> findAll() {
		return pharmacieGardeRepository.findAll();
	}
	@PostMapping("/")
	public PharmacieGarde create(@RequestBody PharmacieGarde pharmacieGarde) {
		return pharmacieGardeRepository.save(pharmacieGarde);
	}
	
}
