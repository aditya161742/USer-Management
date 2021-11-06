package com.usermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.models.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long>{
	
	
}
