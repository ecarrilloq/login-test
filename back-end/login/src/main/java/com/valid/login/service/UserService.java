package com.valid.login.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.valid.login.model.User;
import com.valid.login.repository.UserRepository;
import com.valid.login.util.Util;

@Service
public class UserService {

	private final UserRepository userRep;

	public UserService(UserRepository userRep) {
		this.userRep=userRep;
	}
	
	public List<User> getAllUsers(){
		return userRep.findAll();
	}
	
	public String login(String username, String password) {	
		User user = userRep.findByUsername(username);
		if (user!=null && user.getHashedPassword()!=null && user.getHashedPassword().equals(Util.getHashedPassword(password))) {
			return Util.getToken(username);
		}else {
			return null;
		}
		
	}

}
