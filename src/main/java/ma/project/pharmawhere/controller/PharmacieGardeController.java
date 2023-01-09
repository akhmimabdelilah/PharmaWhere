package ma.project.pharmawhere.controller;

import java.util.ArrayList;
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

import ma.project.pharmawhere.dto.PharmacieGardeDTO;
import ma.project.pharmawhere.model.PharmacieGarde;
import ma.project.pharmawhere.model.PharmacieGardePK;
import ma.project.pharmawhere.repository.PharmacieGardeRepository;

@CrossOrigin
@RestController
@RequestMapping("pharmaciegarde")
public class PharmacieGardeController {
	@Autowired
	private PharmacieGardeRepository pharmacieGardeRepository;

	@GetMapping("/")
	public List<PharmacieGardeDTO> findAll() {
		List<PharmacieGardeDTO> ll = new ArrayList<>();
		for(PharmacieGarde pg : pharmacieGardeRepository.findAll()) {
			PharmacieGardeDTO pgdto= new PharmacieGardeDTO();
			pgdto.setDateDebut(pg.getDateDebut());
			pgdto.setDateFin(pg.getDateFin());
			pgdto.setGarde(pg.getGarde());
			pgdto.setPharmacie(pg.getPharmacie());
			pgdto.setPk(pg.getPk());
			ll.add(pgdto);
		}
		return ll;
	}

	@PostMapping("/")
	public PharmacieGarde create(@RequestBody PharmacieGarde pharmacieGarde) {
		
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
