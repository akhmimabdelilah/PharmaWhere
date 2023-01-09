package ma.project.pharmawhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.PharmacieGarde;
import ma.project.pharmawhere.model.PharmacieGardePK;

public interface PharmacieGardeRepository extends JpaRepository<PharmacieGarde, PharmacieGardePK> {
	PharmacieGarde findOneByPk(PharmacieGardePK pk);
	@Query("select pg from User u, Pharmacie p, PharmacieGarde pg " + " where pg.pharmacie=p and p.user = u and u.username = ?1 ")
	List<PharmacieGarde> user(String username);
}
