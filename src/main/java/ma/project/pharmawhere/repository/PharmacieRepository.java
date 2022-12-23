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

	@Query("select p from Pharmacie p, PharmacieGarde pg Where pg.pharmacie = p AND pg.dateDebut = :date1 AND pg.dateFin = :date2")
	List<Pharmacie> getPharmaciesByDate(Date date1, Date date2);

	@Query("select p from Pharmacie p, Zone z Where p.zone = z AND z = :zone")
	List<Pharmacie> getPharmaciesByZone(@Param("zone") Zone zone);

	@Query("select p from Pharmacie p, Zone z, Ville v Where p.zone = z AND z.ville = v AND v = :ville")
	List<Pharmacie> getPharmaciesByVille(Ville ville);
	
	@Query("select p from Pharmacie p Where (p.log - :log) + (p.lat - :lat) < :threshold")
	List<Pharmacie> getPharmaciesByZone(double log, double lat, double threshold);
	
}
