package ma.project.pharmawhere.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.multipart.MultipartFile;

import ma.project.pharmawhere.dto.DatesDTO;
import ma.project.pharmawhere.dto.TypeDTO;
import ma.project.pharmawhere.model.GardeType;
import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.PharmacieStatus;
import ma.project.pharmawhere.model.User;
import ma.project.pharmawhere.model.Ville;
import ma.project.pharmawhere.model.Zone;
import ma.project.pharmawhere.repository.PharmacieRepository;
import ma.project.pharmawhere.repository.UserRepository;

@RestController
@RequestMapping("pharmacie")
@CrossOrigin
public class PharmacieController {
	@Autowired
	private PharmacieRepository pharmacieRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public List<Pharmacie> findAll() {
		return pharmacieRepository.findAll();
	}

	@GetMapping("/all")
	public List<Pharmacie> findAllForUser() {
		return pharmacieRepository.getApprovees();
	}

	@PostMapping("/byDates")
	public List<Pharmacie> findByDates(@RequestBody() DatesDTO dates) {
		return pharmacieRepository.getPharmaciesByDate(dates.getDateDebut(), dates.getDateFin());
	}

	@PostMapping("/byGarde")
	public List<Pharmacie> findByGardes(@RequestBody() TypeDTO g) {
		return pharmacieRepository.getPharmaciesByGarde(g.j ? GardeType.J : GardeType.N,
				g.n ? GardeType.N : GardeType.J);
	}

	@PostMapping("/byDatesAndGarde")
	public List<Pharmacie> findByDatesAndGarde(@RequestParam("dateDebut") Date dateDebut,
			@RequestParam("dateFin") Date dateFin, @RequestParam("j") boolean j, @RequestParam("n") boolean n) {
		return pharmacieRepository.getPharmaciesByDateAndGarde(dateDebut, dateFin, j ? GardeType.J : GardeType.N,
				n ? GardeType.N : GardeType.J);
	}

	@PostMapping("/byGardeAndZone")
	public List<Pharmacie> findByGardesAndZone(@RequestParam("zoneId") int zoneId, @RequestParam("j") boolean j,
			@RequestParam("n") boolean n) {
		Zone zone = new Zone();
		zone.setId(zoneId);
		return pharmacieRepository.getPharmaciesByZoneAndGarde(zone, j ? GardeType.J : GardeType.N,
				n ? GardeType.N : GardeType.J);
	}
	
	@PostMapping("/byGardeAndVille")
	public List<Pharmacie> findByGardesAndVille(@RequestParam("villeId") int villeId, @RequestParam("j") boolean j,
			@RequestParam("n") boolean n) {
		Ville ville = new Ville();
		ville.setId(villeId);
		return pharmacieRepository.getPharmaciesByVilleAndGarde(ville, j ? GardeType.J : GardeType.N,
				n ? GardeType.N : GardeType.J);
	}

	@PostMapping("/")
	public Pharmacie create(HttpServletRequest request, @RequestParam("image") MultipartFile image, @RequestParam("nom") String nom,
			@RequestParam("adresse") String adresse, @RequestParam("lat") double lat, @RequestParam("log") double log,
			@RequestParam("zone") int zoneId, @RequestParam("user") String username) {
		
		Pharmacie pharmacie = new Pharmacie();
		pharmacie.setAdresse(adresse);
		pharmacie.setLat(lat);
		pharmacie.setLog(log);
		pharmacie.setNom(nom);
	
		Zone zone = new Zone();
		zone.setId(zoneId);
		User user = userRepository.findUserWithName(username).orElse(null);
		pharmacie.setUser(user);
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

	@PutMapping("/{id}/valid")
	public Pharmacie approve(@PathVariable(required = true) int id) {
		Pharmacie pharmacie = pharmacieRepository.findById(id);
		pharmacie.setStatus(PharmacieStatus.APPROVEE);
		return pharmacieRepository.save(pharmacie);
	}

	@PutMapping("/{id}/invalid")
	public Pharmacie refuse(@PathVariable(required = true) int id) {
		Pharmacie pharmacie = pharmacieRepository.findById(id);
		pharmacie.setStatus(PharmacieStatus.REFUSEE);
		return pharmacieRepository.save(pharmacie);
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
	
	@GetMapping("/count/enattend")
	public long countEnAttend() {
		return pharmacieRepository.getEnAttendCount();
	}
	@PostMapping("/nearby")
	public List<Pharmacie> nearby(@RequestParam("log") double log, @RequestParam("lat") double lat) {
		return pharmacieRepository.nearby(log, lat, 2);
	}

}
