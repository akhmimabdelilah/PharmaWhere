package ma.project.pharmawhere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.project.pharmawhere.model.PharmacieGarde;
import ma.project.pharmawhere.model.PharmacieGardePK;


public interface PharmacieGardeRepository extends JpaRepository<PharmacieGarde, PharmacieGardePK> {
	Optional<PharmacieGarde> findById(PharmacieGardePK id);
}
