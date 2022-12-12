package com.men.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.Garde;

public interface GardeRepository extends JpaRepository<Garde, Integer> {
	Garde findById(int id);
}
