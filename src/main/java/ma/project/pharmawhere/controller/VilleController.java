package ma.project.pharmawhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.Ville;
import ma.project.pharmawhere.model.Zone;
import ma.project.pharmawhere.repository.PharmacieRepository;
import ma.project.pharmawhere.repository.VilleRepository;


@RestController
@RequestMapping("ville")
@CrossOrigin
public class VilleController {
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private PharmacieRepository pharmacieRepository;
	

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
	
	@GetMapping("/{id}/zones")
	public List<Zone> getZones(@PathVariable(required = true) int id) {
		return villeRepository.getZones(id);
	}
	
	@GetMapping("/{id}/pharmacies")
	public List<Pharmacie> getPharmacies(@PathVariable(required = true) int id) {
		return pharmacieRepository.getPharmaciesByVille(villeRepository.findById(id));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Ville ville = villeRepository.findById(id);
		villeRepository.delete(ville);
	}
	
	

}
