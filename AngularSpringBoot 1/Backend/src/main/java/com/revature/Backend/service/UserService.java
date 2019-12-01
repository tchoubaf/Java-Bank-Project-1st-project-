package com.revature.Backend.service;

import java.util.List;

import com.revature.Backend.model.Users;

public interface UserService {
    Users save(Users users);

    Users findByUsername(String username);


    List<Users> findAllUsers();
}
