package com.valid.login.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.valid.login.model.User;
import com.valid.login.repository.UserRepository;
import com.valid.login.util.LoggerUtil;
import com.valid.login.util.Util;

@Configuration
public class LoadInitData {
	@Bean
	CommandLineRunner loadData(UserRepository repo) {
		return args -> {
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User1 {}", repo.save(new User("admin",Util.getHashedPassword("admin"))).getUsername());
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User2 {}", repo.save(new User("user",Util.getHashedPassword("user"))).getUsername());			
		};		
	}
}
