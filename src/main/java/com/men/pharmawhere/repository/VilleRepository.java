package com.men.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.Ville;
import com.men.pharmawhere.entity.Zone;

public interface VilleRepository extends JpaRepository<Ville, Integer>{
	Ville findById(int id);
}
