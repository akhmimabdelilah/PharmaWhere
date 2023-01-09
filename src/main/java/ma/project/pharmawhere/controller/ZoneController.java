package ma.project.pharmawhere.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.Zone;
import ma.project.pharmawhere.repository.PharmacieRepository;
import ma.project.pharmawhere.repository.ZoneRepository;

@CrossOrigin
@RestController
@RequestMapping("zone")
@CrossOrigin
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private PharmacieRepository pharmacieRepository;

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
	@GetMapping("/{id}/pharmacies")
	public List<Pharmacie> findPharmacie(@PathVariable(required = true) int id) {
		return pharmacieRepository.getPharmaciesByZone(zoneRepository.findById(id));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		Zone zone = zoneRepository.findById(id);
		zoneRepository.delete(zone);
	}
	@GetMapping("/count")
	public long count() {
		return zoneRepository.count();
	}
	@GetMapping("/count/pharmacies")
	public Collection<?> countAll() {
		return zoneRepository.findPharmacies();
	}
}
