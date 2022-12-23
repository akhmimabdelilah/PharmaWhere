package ma.project.pharmawhere.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.project.pharmawhere.model.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Integer>{
	Zone findById(int id);
	@Query("select z.nom as nom, count(*) as nbr from Zone z, Pharmacie p where p.zone = z group by z.nom")
    Collection<?> findPharmacies();  
}
