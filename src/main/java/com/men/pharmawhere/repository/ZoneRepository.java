package com.men.pharmawhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.men.pharmawhere.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Integer>{
	Zone findById(int id);
}
