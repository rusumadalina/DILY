package com.dily.controllers;


import com.dily.Database;
import com.dily.entities.User;
import com.dily.models.UserModel;


import com.dily.services.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Andra on 5/1/2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class UserController {

    @RequestMapping(value="/login" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<UserModel> findByUsernameAndPassword( @RequestBody UserModel userModel) throws SQLException {


        String username =userModel.getUsername();
        String password =userModel.getPassword();
        AuthenticationServiceImpl authentication = new AuthenticationServiceImpl();

        UserModel user;
        user = authentication.findByUsernameAndPassword(username,password);

        if (user.getUsername() != null && user.getPassword()!= null) {
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }

    }
}
