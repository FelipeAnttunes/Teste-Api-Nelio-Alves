package com.example.backend.testApi.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.testApi.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		//criando o corpo de resposta para solicitação web
		//utilizando a base da minha entidade para criar um usuario
		User u = new User(1L, "Felipe", "felipe@gmail.com", "99999999", "123456");
		return ResponseEntity.ok().body(u);
	}

}
