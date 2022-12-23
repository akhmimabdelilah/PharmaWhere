package ma.project.pharmawhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.project.pharmawhere.model.Garde;
import ma.project.pharmawhere.model.GardeType;
import ma.project.pharmawhere.model.Pharmacie;
public interface GardeRepository extends JpaRepository<Garde, Integer> {
	Garde findById(int id);

	@Query("select p from Pharmacie p, PharmacieGarde pg, Garde g Where pg.pharmacie = p AND pg.garde = g AND g.type = :type")
	List<Pharmacie> getPharmacies(@Param("type") GardeType type);
	
	@Query("select g from Garde g Where g.type = :type")
	Garde getType(@Param("type") GardeType type);
	
}
