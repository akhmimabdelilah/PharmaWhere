package ma.project.pharmawhere.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ma.project.pharmawhere.model.PharmacieGarde;
import ma.project.pharmawhere.model.PharmacieGardePK;
import ma.project.pharmawhere.repository.PharmacieGardeRepository;

@CrossOrigin
@RestController
@RequestMapping("pharmaciegarde")
@CrossOrigin
public class PharmacieGardeController {
	@Autowired
	private PharmacieGardeRepository pharmacieGardeRepository;

	@GetMapping("/")
	public List<PharmacieGarde> findAll() {
		return pharmacieGardeRepository.findAll();
	}

	@PostMapping("/")
	public PharmacieGarde create(@RequestBody PharmacieGarde pharmacieGarde) {
		/*PharmacieGardePK pk = new PharmacieGardePK(pharmacieGarde.getPharmacieId(), pharmacieGarde.getGardeId(),
				pharmacieGarde.getDateDebut());
		Pharmacie pharmacie = pharmacieRepository.findById(pharmacieGarde.getPharmacieId());
		Garde garde = gardeRepository.findById(pharmacieGarde.getGardeId());
		//System.out.println(pk);
		PharmacieGarde pGarde = new PharmacieGarde(pk, null, pharmacieGarde.getDateFin(),
				pharmacie, garde);*/
	
		return pharmacieGardeRepository.save(pharmacieGarde);
	}

	@PostMapping("/one")
	public PharmacieGarde findId(@RequestBody PharmacieGardePK pharmacieGarde) {
		PharmacieGarde garde = pharmacieGardeRepository.findOneByPk(pharmacieGarde);
		return garde;
	}
	@DeleteMapping("/one")
	public void delete(@RequestBody PharmacieGardePK pharmacieGarde) {
		pharmacieGardeRepository.deleteById(pharmacieGarde);
	}
	
	@GetMapping("/count")
	public long count() {
		return pharmacieGardeRepository.count();
	}

	

}
