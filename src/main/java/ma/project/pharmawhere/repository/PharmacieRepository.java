package ma.project.pharmawhere.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.Ville;
import ma.project.pharmawhere.model.Zone;


public interface PharmacieRepository extends JpaRepository<Pharmacie, Integer> {
	Pharmacie findById(int id);

	@Query("select p from Pharmacie p, PharmacieGarde pg Where pg.pharmacie = p AND pg.dateDebut = :date1 AND pg.dateFin = :date2 and p.status = 1")
	List<Pharmacie> getPharmaciesByDate(@Param("date1")Date date1,@Param("date2") Date date2);

	@Query("select p from Pharmacie p, Zone z Where p.zone = z AND z = :zone AND p.status = 1")
	List<Pharmacie> getPharmaciesByZone(@Param("zone") Zone zone);

	@Query("select p from Pharmacie p, Zone z, Ville v Where p.zone = z AND z.ville = v AND v = :ville AND p.status = APPROVEE")
	List<Pharmacie> getPharmaciesByVille(@Param("ville") Ville ville);
	
	@Query("select p from Pharmacie p Where p.status = 1")
	List<Pharmacie> getApprovees();
	
	@Query("select p from Pharmacie p Where p.status = 0")
	List<Pharmacie> getEnAttend();
	
	@Query("select p from Pharmacie p Where (p.log - :log) < - :threshold AND (p.log - :log) < :threshold AND (p.lat - :lat) < :threshold AND (p.lat - :lat) < - :threshold AND p.status = 1")
	List<Pharmacie> nearby(@Param("log")double log,@Param("lat") double lat,@Param("threshold") double threshold);
}
