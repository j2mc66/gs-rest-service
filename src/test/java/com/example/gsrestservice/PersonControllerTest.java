package com.example.gsrestservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootConfiguration
@ComponentScan({"com.example"})
public class PersonControllerTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void createPeson() {
		
		ResponseEntity<User> responseEntity =
	            restTemplate.postForEntity("/persona", new User("James", "Muñoz", "james@hotmail.com"), User.class);
		User person = responseEntity.getBody();
	
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("James", person.getFirstName());
	}
}
