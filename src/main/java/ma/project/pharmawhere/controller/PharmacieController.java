package ma.project.pharmawhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.project.pharmawhere.dto.PharmacieDTO;
import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.repository.PharmacieRepository;

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

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Pharmacie pharmacie = pharmacieRepository.findById(id);
		pharmacieRepository.delete(pharmacie);
	}
	
	@GetMapping("/count")
	public long count() {
		return pharmacieRepository.count();
	}

}
