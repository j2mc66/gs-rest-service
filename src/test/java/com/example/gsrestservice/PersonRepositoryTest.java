package com.example.gsrestservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entities.User;
import com.example.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {UserRepository.class})
@EntityScan("com.example")
@EnableJpaRepositories("com.example") 
public class PersonRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository personRepository;
	
	@Test
	public void whenFindByIdAndLastName() {
		User person = new User();
		person.setFirstName("james");
		person.setLastName("Muñoz");
		
		entityManager.persist(person);
	    entityManager.flush();
	    
	    User found = personRepository.findByIdAndFirstName(person.getId(), person.getFirstName()) ;
	    assertThat(found.getFirstName())
	      .isEqualTo(person.getFirstName());
	}
}
