package com.men.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.Garde;
import com.men.pharmawhere.entity.Zone;

public interface GardeRepository extends JpaRepository<Garde, Integer> {
	Garde findById(int id);
}
