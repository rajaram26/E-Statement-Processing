package com.raja.respositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.raja.model.LoginUser;

public interface LoginRepositary extends CrudRepository<LoginUser, Integer> {
	
	Optional <LoginUser> findByEmail(String email);
	
	@Query(value="SELECT * FROM login_user WHERE username=?1",nativeQuery = true)
	LoginUser findByEmailid(String email);

	@Modifying
	@Query(value="UPDATE login_user SET password=?2 WHERE email=?1",nativeQuery = true)
	void updatepassword(String email, String password);
	
}
