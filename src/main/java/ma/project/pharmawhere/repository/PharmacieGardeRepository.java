package ma.project.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.project.pharmawhere.model.PharmacieGarde;
import ma.project.pharmawhere.model.PharmacieGardePK;

public interface PharmacieGardeRepository extends JpaRepository<PharmacieGarde, PharmacieGardePK> {
	PharmacieGarde findOneByPk(PharmacieGardePK pk);
}
