package com.valid.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valid.login.model.User;
import com.valid.login.service.UserService;
import com.valid.login.util.Util;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/users")
	ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token){
		if(Util.validateToken(token)) {
			return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/login")
	ResponseEntity<String>  login(@RequestParam("username") String username, @RequestParam("password") String password){
		String token =service.login(username, password);
		if(token!=null && !token.isEmpty()) {
			return new ResponseEntity<>(token, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	
	
}
