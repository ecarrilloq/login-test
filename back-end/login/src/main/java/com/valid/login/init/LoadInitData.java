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
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User {}", repo.save(new User("admin",Util.getHashedPassword("admin"))).getUsername());
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User {}", repo.save(new User("user",Util.getHashedPassword("user"))).getUsername());
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User {}", repo.save(new User("user1",Util.getHashedPassword("user1"))).getUsername());
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User {}", repo.save(new User("user2",Util.getHashedPassword("user2"))).getUsername());
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User {}", repo.save(new User("user3",Util.getHashedPassword("user3"))).getUsername());
			LoggerUtil.getLogger(LoadInitData.class).info("Adding User {}", repo.save(new User("user4",Util.getHashedPassword("user4"))).getUsername());
		};		
	}
}
