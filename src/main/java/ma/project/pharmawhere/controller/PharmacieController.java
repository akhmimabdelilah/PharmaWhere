package ma.project.pharmawhere.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.project.pharmawhere.dto.PharmacieDTO;
import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.Zone;
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
	public Pharmacie create(@RequestParam("image") MultipartFile image,
			@RequestParam("nom") String nom,
			@RequestParam("adresse") String adresse,
			@RequestParam("lat") double lat,
			@RequestParam("log") double log,
			@RequestParam("zone") int zoneId) {
		Pharmacie pharmacie = new Pharmacie();
		pharmacie.setAdresse(adresse);
		pharmacie.setLat(lat);
		pharmacie.setLog(log);
		pharmacie.setNom(nom);
		Zone zone = new Zone();
		zone.setId(zoneId);
		pharmacie.setZone(zone);
		try {
			pharmacie.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
