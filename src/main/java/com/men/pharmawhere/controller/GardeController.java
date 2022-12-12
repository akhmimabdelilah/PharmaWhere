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
import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.repository.GardeRepository;

@RestController
@RequestMapping("garde")
public class GardeController {
	@Autowired
	private GardeRepository gardeRepository;
	@GetMapping("/")
	public List<Garde> findAll() {
		return gardeRepository.findAll();
	}
	@PostMapping("/")
	public Garde create(@RequestBody Garde garde) {
		return gardeRepository.save(garde);
	}
	
	@GetMapping("/{id}")
	public Garde findById(@PathVariable(required = true) int id) {
		return gardeRepository.findById(id);
	}
	@PutMapping("/{id}")
	public Garde update(@PathVariable(required = true) int id) {
		Garde garde = gardeRepository.findById(id);
		return gardeRepository.save(garde);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Garde garde = gardeRepository.findById(id);
		gardeRepository.delete(garde);
	}
	
}
