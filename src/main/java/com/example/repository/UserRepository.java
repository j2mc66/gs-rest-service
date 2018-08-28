package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.entities.User;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByLastName(@Param("name") String name);
	
	List<User> findByFirstName(@Param("name") String name);
	
	User findByIdAndFirstName(@Param("id") Long id, @Param("name") String name);
	
	User findByUsername(@Param("username") String username);

}
