/*package ro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import basic.AppUser;
import basic.AppUser.Role;
import basic.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

	 private final AppUserRepository appUserRepository;

	//@Autowired
	public AppUserService(AppUserRepository appUserRepository) {
		 this.appUserRepository = appUserRepository;
	}

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 AppUser appUser = appUserRepository.findByUsername(username)
				    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	        // Creăm un UserDetails pentru a-l returna la Spring Security
	        return User.builder()
	                .username(appUser.getUsername())
	                .password(appUser.getPassword()) 
	                .roles(appUser.getRole().name()) 
	                .build();
	    }
	 public AppUser findByUsername(String username) {
	        return appUserRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	    }
	 private  PasswordEncoder passwordEncoder;
	    public AppUser registerUser(String username, String password, String roles) {
	        // Check if user already exists
	        if (appUserRepository.findByUsername(username) != null) {
	            throw new IllegalArgumentException("Username already exists!");
	        }

	        // Encode the password
	        String encodedPassword = passwordEncoder.encode(password);

	        // Create the new user\
	        var role=Role.valueOf(roles);
	        AppUser newUser = new AppUser(username, encodedPassword, role);

	        // Save the user
	        return appUserRepository.save(newUser);
	    }
}
*/