package com.bestray.security.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import javax.xml.ws.Response;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bestray.model.security.Authority;
import com.bestray.model.security.User;
import com.bestray.security.JwtAuthenticationRequest;
import com.bestray.security.JwtUser;
import com.bestray.security.JwtUserFactory;
import com.bestray.security.controller.ExceptionHandler_delete;
import com.bestray.security.controller.ExceptionHandler_update;
import com.bestray.security.controller.RegExceptionHandler;
import com.bestray.security.controller.RoleExceptionHandler;
import com.bestray.security.repository.AuthorityRepository;
import com.bestray.security.repository.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
  
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
       
        //System.out.println("permission "+user.getPermission());
            return JwtUserFactory.create(user);
        }
    }
    
  public void saveUser(User authenticationRequest) throws RegExceptionHandler {
	 
	  User userExists =userRepository.findByEmail(authenticationRequest.getEmail());
  	     if (userExists != null) {
			 throw new RegExceptionHandler();
		  }else{
	  authenticationRequest.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
	  authenticationRequest.setEnabled(true);
	  authenticationRequest.setLastPasswordResetDate(new Date());
	  System.out.println("1@@@@@ "+authenticationRequest.getEmail()+" "+authenticationRequest.getPassword()+" "+authenticationRequest.getFirstname()+" "+authenticationRequest.getLastname()+" "+authenticationRequest.getUsername()+" "+authenticationRequest.getId()+" "+authenticationRequest.getEnabled()+" "+authenticationRequest.getLastPasswordResetDate());
	 // User user= userRepository.save(authenticationRequest);
	  userRepository.save(authenticationRequest);
	  System.out.println("2@@@@@");
}
	//  return JwtUserFactory.create(userRepository.save(authenticationRequest));
	  
	  /*if (user == null) {
          throw new UsernameNotFoundException(String.format("No user found with username"));
      } else {
    	  System.out.println("3@@@@@");
          return JwtUserFactory.create(user);
      }*/
	  
      /*Role userRole = roleRepository.findByRole("ADMIN");
      user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));*/

	}
  public void editUser(User user,String name) throws ExceptionHandler_update  {
		 
	  User userExists =userRepository.findByUsername(name);
  	     if (userExists != null) {
  	    	userExists.setFirstname(user.getFirstname());
  	    	userExists.setLastname(user.getLastname());
  	    	userExists.setUsername(user.getUsername());
  	    	userExists.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  	    	userExists.setEmail(user.getEmail());
  	    	userExists.setEnabled(true);
  	    	userExists.setLastPasswordResetDate(new Date());
  	 	    System.out.println("1@@@@@ "+user.getEmail()+" "+user.getPassword()+" "+user.getFirstname()+" "+user.getLastname()+" "+user.getUsername()+" "+user.getId()+" "+user.getEnabled()+" "+user.getLastPasswordResetDate());
  	 	    userRepository.save(userExists);
  	 	    System.out.println("2@@@@@");
		    }else{
			  throw new ExceptionHandler_update(); 
		}
  }
  
  
  
  public void deleteUser(String name) throws ExceptionHandler_delete  {
	  User userExists =userRepository.findByUsername(name);
  	     if (userExists != null) {
  	    	userExists.setEnabled(false);
  	 	    userRepository.save(userExists);
  	 	    System.out.println("2@@@@@");
		    }else{
			  throw new ExceptionHandler_delete(); 
		}
  } 
  
  
  public void saveRole(Authority authority) throws RoleExceptionHandler  {
		 
	  Authority roleExists =authorityRepository.findByName(authority.getName());
  	     if (roleExists != null) {
			 throw new RoleExceptionHandler();
		  }else{
	 // System.out.println("1@@@@@ "+authenticationRequest.getEmail()+" "+authenticationRequest.getPassword()+" "+authenticationRequest.getFirstname()+" "+authenticationRequest.getLastname()+" "+authenticationRequest.getUsername()+" "+authenticationRequest.getId()+" "+authenticationRequest.getEnabled()+" "+authenticationRequest.getLastPasswordResetDate());
	  authorityRepository.save(authority);
	  System.out.println("2@@@@@");
}

	}
  
  
  
    
}
