package com.revature.Backend.service;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

import com.revature.Backend.config.repository.UserRepository;
import com.revature.Backend.model.Users;

import java.util.List;

	
	@Service
	@Transactional
	public class UserServiceImpl implements UserService {

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Override
	    public Users save(Users users){
	        users.setPassword(passwordEncoder.encode(users.getPassword()));
	        userRepository.save(users);
	        return users;
	    }

	    @Override
	    public Users findByUsername(String username){
	        return userRepository.findByUsername(username).orElse(null);
	    }

	    

	    @Override
	    public List<Users> findAllUsers(){
	        return this.userRepository.findAll();
	    }

		
	}


