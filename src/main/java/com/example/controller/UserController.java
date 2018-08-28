package com.example.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entities.User;
import com.example.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	//@RequestMapping(path="/users", method= {RequestMethod.POST})
    public ResponseEntity<Object> create(@RequestBody User user) {
         User userCreated = userService.save(user);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
     			.buildAndExpand(userCreated.getId()).toUri();
         return ResponseEntity.created(location).body(userCreated);
    }
	
	@PutMapping("/{id}")
	//@RequestMapping(path="/users/{id}", method= {RequestMethod.PUT})
    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable long id) {
                  
        Optional<User> userOptional = userService.findById(id);

     	if (!userOptional.isPresent())
     		return ResponseEntity.notFound().build();

     	user.setId(id);
     	
     	userService.save(user);

     	return ResponseEntity.noContent().build();
    }
	
	@DeleteMapping("/{id}")
	//@RequestMapping(path="/users/{id}", method= {RequestMethod.DELETE})
	public ResponseEntity<Object> delete(@PathVariable long id) {
		
		Optional<User> userOptional = userService.findById(id);

     	if (!userOptional.isPresent())
     		return ResponseEntity.notFound().build();
     	
		userService.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
	
	@GetMapping("/{id}")
	//@RequestMapping(path="/users/{id}", method= {RequestMethod.GET})
	public ResponseEntity<Object> findById(@PathVariable long id) {
		Optional<User> user = userService.findById(id);

		if (!user.isPresent())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(user.get());
	}
}
