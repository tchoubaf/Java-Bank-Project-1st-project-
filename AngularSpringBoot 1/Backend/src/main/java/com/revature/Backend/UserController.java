package com.revature.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Backend.model.Roles;
import com.revature.Backend.model.Users;
import com.revature.Backend.service.UserService;

import java.security.Principal;


@CrossOrigin("http://localhost:4200/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin("http://localhost:4200/")
    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody Users users){
        if(userService.findByUsername(users.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        users.setRole(Roles.STUDENT);
        userService.save(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }
    
    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/api/user/login")
    public ResponseEntity<?> auth(Principal principal){
        if(principal==null || principal.getName()==null){
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(
                userService.findByUsername(principal.getName()),
                HttpStatus.OK);
    }
    
    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/api/user/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
