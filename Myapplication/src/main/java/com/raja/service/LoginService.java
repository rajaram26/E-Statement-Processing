package com.raja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raja.model.LoginUser;
import com.raja.respositary.LoginRepositary;

@Service
public class LoginService {
	
	@Autowired
	LoginRepositary loginrepositary;
	
	
	public void saveMyUser(LoginUser loginuser) {
		loginrepositary.save(loginuser);
	}


	public void saveMyUser(String userid,String username, String password, String role, boolean active, String email) {
		
		LoginUser loginuser = new LoginUser(Integer.parseInt(userid),username,password,role,active,email);
		loginrepositary.save(loginuser);
	}
	
	public List<LoginUser> findAll(){
		return (List<LoginUser>) loginrepositary.findAll();
	}


	public void delete(String id) {
		loginrepositary.deleteById(Integer.parseInt(id));
	}


	public LoginUser findByEmailid(String email) {
		LoginUser loginuser = loginrepositary.findByEmailid(email);
		return loginuser;
	}


	public void changepassword(String email, String password) {
		loginrepositary.updatepassword(email,password);
	}

}
