package ma.project.pharmawhere.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(" select u from User u " + " where u.username = ?1")
	Optional<User> findUserWithName(String username);

	@Query("select p from User u, Pharmacie p " + " where p.user = u and u.username = ?1 ")
	List<Pharmacie> findPharmaciesWithUserName(String username);
}