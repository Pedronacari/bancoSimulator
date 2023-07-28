package com.banco.banco.controllers;

import com.banco.banco.dto.PayLoadDTO;
import com.banco.banco.models.Users;
import com.banco.banco.sevices.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;
    @Autowired
    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = userServices.getAll();
        if (usersList == null) {
            return new ResponseEntity(usersList, HttpStatus.NOT_FOUND);
        } else if (usersList.isEmpty()) {
            return new ResponseEntity<>(usersList, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(usersList, HttpStatus.OK);
    }

    @PostMapping
    public void addUser(@RequestBody Users users){
        userServices.addNewUser(users);
    }

    @PostMapping("/transaction")
    public void transaction(@RequestBody PayLoadDTO payLoadDTO){
        userServices.transferService(payLoadDTO);
    }


}
