package ma.project.pharmawhere.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.project.pharmawhere.auth.UserService;
import ma.project.pharmawhere.model.Pharmacie;
import ma.project.pharmawhere.model.PharmacieGarde;
import ma.project.pharmawhere.model.PharmacieGardePK;
import ma.project.pharmawhere.model.User;
import ma.project.pharmawhere.repository.PharmacieGardeRepository;
import ma.project.pharmawhere.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@PostMapping("/")
	public User create(@RequestBody User user) {
		return userService.saveUser(user.getUsername(), user.getPassword(), user.getEmail());
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable(required = true) int id) {
		return userRepository.findById(id).get();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/count")
	public long count() {
		return userRepository.count();
	}

	

}
