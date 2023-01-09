package ma.project.pharmawhere.repository;

import java.util.Collection;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.project.pharmawhere.model.Ville;
import ma.project.pharmawhere.model.Zone;

public interface VilleRepository extends JpaRepository<Ville, Integer>{
	Ville findById(int id);
	@Query("SELECT z FROM Zone z, Ville v WHERE z.ville = v AND v.id = :id")
	List<Zone> getZones(@Param("id") int id);
	@Query("select v.nom as nom, count(*) as nbr from Ville v, Zone z, Pharmacie p where p.zone = z and z.ville = v group by v.nom")
    Collection<?> findPharmacies();  
}
