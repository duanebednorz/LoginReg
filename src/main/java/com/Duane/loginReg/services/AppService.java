package com.Duane.loginReg.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.Duane.loginReg.models.User;
import com.Duane.loginReg.repositories.UserRepository;

@Service
public class AppService {
	
	 	private final UserRepository userRepo;
	    
	    public AppService(UserRepository userRepo) {
	        this.userRepo = userRepo;
	    }
	    
	    public User registerUser(User user) {
	        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashed); /* set user object password */
			return userRepo.save(user); /* creates new user object */
	    }
	    
	    public User findByEmail(String email) {
	        return userRepo.findByEmail(email);
	    }
	    
	    public User findUserById(Long id) {  // find user by id 
//	    	Optional<User> u = userRepo.findById(id);
//	    	if(u.isPresent()) {
//	            return u.get();
//	    	} else {
//	    	    return null;
//	    	}
	    	return userRepo.findById(id).orElse(null);
	    }
	    
	   
	    public boolean authenticateUser(String email, String password) {  // authenticate user
	        User user = userRepo.findByEmail(email); // first find the user by email
	        if(user == null) { // if we can't find it by email, return false
	            return false;
	        } else {
	            if(BCrypt.checkpw(password, user.getPassword())) { 
	                return true; // if the passwords match, return true, else, return false
	            } else {
	                return false;
	            }
	        }
	    }
};
