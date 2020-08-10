package com.raja.respositary;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.raja.model.User;

public interface UserRepositary extends CrudRepository<User,Integer> {
	
	public User findByUsernameAndPassword(String username,String password);
	
	Optional <User> findByUsername(String username);
	
	
}
