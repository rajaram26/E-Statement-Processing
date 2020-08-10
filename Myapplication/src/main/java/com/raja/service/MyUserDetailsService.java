package com.raja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raja.model.LoginUser;
import com.raja.respositary.LoginRepositary;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepositary loginrepositary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      	Optional<LoginUser> loginuser =loginrepositary.findByEmail(username);
      	
      	loginuser.orElseThrow(()-> new UsernameNotFoundException("Not Found: " + username));
    	
        return loginuser.map(MyUserDetails::new).get();
    }
    
}

