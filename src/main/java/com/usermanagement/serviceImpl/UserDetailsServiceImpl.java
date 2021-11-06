package com.usermanagement.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.models.Users;
import com.usermanagement.repositories.UserDetailsRepository;
import com.usermanagement.services.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Override
	public Set<Users> findAll() {
		Set<Users> users = new HashSet<>();
		userDetailsRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public Users findById(Long id) {
		return userDetailsRepository.findById(id).orElse(null);
	}

	@Override
	public Users save(Users object) {
		return userDetailsRepository.save(object);
	}

	@Override
	public void delete(Users object) {
		userDetailsRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		userDetailsRepository.deleteById(id);
	}

}
