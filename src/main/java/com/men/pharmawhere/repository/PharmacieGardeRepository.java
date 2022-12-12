package com.men.pharmawhere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.PharmacieGarde;
import com.men.pharmawhere.entity.PharmacieGardePK;

public interface PharmacieGardeRepository extends JpaRepository<PharmacieGarde, PharmacieGardePK> {
	Optional<PharmacieGarde> findById(PharmacieGardePK id);
}
