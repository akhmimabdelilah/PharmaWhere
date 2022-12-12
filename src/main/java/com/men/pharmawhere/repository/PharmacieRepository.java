package com.men.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.Pharmacie;
import com.men.pharmawhere.entity.Zone;

public interface PharmacieRepository extends JpaRepository<Pharmacie, Integer>{
	Pharmacie findById(int id);
}