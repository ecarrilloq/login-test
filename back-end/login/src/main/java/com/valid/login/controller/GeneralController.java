package com.valid.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
	
	@GetMapping("/status")
	ResponseEntity<String> getStatus(){
		return new ResponseEntity<>("The server is Up and Running", HttpStatus.OK);
	}
	
}
