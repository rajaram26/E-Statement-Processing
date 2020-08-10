package com.raja.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.raja.model.Fileupload;

public interface FileService {
	
	@Query(value="SELECT * FROM client_details ORDER BY trans_id")
	List<Fileupload> findAll();
	
	List<Fileupload> findByDate(String name,String name1);
}
