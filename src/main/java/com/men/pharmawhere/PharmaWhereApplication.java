package com.men.pharmawhere;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.men.pharmawhere.entity.Pharmacie;
import com.men.pharmawhere.repository.PharmacieRepository;

@SpringBootApplication
public class PharmaWhereApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmaWhereApplication.class, args);
	}
void CommandLineRunner(PharmacieRepository pharmacieRepository){
	Pharmacie p = new Pharmacie();
	pharmacieRepository.save(p);
}
}
