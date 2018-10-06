package com.bestray.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bestray.model.security.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Long>{
	 Authority findByName(String name);

}
