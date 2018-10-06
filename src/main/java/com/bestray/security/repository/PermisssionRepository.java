package com.bestray.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bestray.model.security.Permission;
import com.bestray.model.security.User;

public interface PermisssionRepository extends JpaRepository<Permission,Long>{
	 Permission findByName(String name);
}
