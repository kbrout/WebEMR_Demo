package com.bestray.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bestray.model.security.User;
import com.bestray.security.JwtAuthenticationRequest;
import com.bestray.security.JwtUser;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
   // void saveall(JwtAuthenticationRequest authenticationRequest);
   // User save(User authenticationRequest);
   // User findById(Long id);
    User findByEmail(String email);
}
