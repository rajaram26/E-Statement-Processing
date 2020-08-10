package com.raja.respositary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.raja.model.Fileupload;

public interface FileUploadRepositary extends CrudRepository<Fileupload, String> {
	
	@Query(value="SELECT * FROM client_details WHERE date BETWEEN ?1 and ?2 order by date asc",nativeQuery=true)
	List<Fileupload> findbydate(String start,String end);

	@Query(value="SELECT * FROM client_details WHERE accno=?1 order by date asc",nativeQuery=true)
	List<Fileupload> findbyname(String name);
}
