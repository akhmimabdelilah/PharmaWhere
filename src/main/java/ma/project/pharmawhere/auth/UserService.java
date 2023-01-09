package ma.project.pharmawhere.auth;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.project.pharmawhere.model.Role;
import ma.project.pharmawhere.model.User;
import ma.project.pharmawhere.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = repository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
    
    
    public User saveUser(String username, String password, String email){
    	User user = new User();
        user.setUsername(username);
        user.setRole(Role.PHARMACIEN);
        
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        return repository.save(user);
    }
}
