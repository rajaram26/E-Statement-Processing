package com.raja.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raja.model.User;
import com.raja.respositary.UserRepositary;

@Service
@Transactional
public class UserService {
	
	@Autowired
	public final UserRepositary userRepositary;
	
	public UserService(UserRepositary userRepositary) {
		this.userRepositary=userRepositary;
	}
	
	public void saveMyUser(User user) {
		userRepositary.save(user);
	}
	
	public User findByUsernameAndPassword(String username,String password) {
		return userRepositary.findByUsernameAndPassword(username,password);
	}
		
//	public User findByUsername(String s) {
//		return userRepositary.findByUsername(s);
//	}
}
