package com.hair.User_Service.controller;

import com.hair.User_Service.Exception.UserException;
import com.hair.User_Service.Repository.UserRepository;
import com.hair.User_Service.model.User;
import com.hair.User_Service.servive.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {


    final private UserService userService;



    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUser(){
        List<User> users= userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody  @Valid User user){
          User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId,
                                           @RequestBody User user
            ) throws  Exception{
        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws Exception{
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<String>  deleteUserById(@PathVariable("userId") Long userId)throws Exception{
         userService.deleteUser(userId);
        return new ResponseEntity<>("User is Deleted Successfully!!",HttpStatus.OK);
    }
}
