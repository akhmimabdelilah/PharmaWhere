package com.men.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer>{
	Ville findById(int id);
}
