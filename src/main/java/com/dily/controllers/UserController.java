package com.dily.controllers;


import com.dily.entities.User;
import com.dily.models.UserModel;


import com.dily.models.UserModelRegiter;
import com.dily.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;


/**
 * Created by Andra on 5/1/2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class UserController {

    @RequestMapping(value="/login" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<UserModel> login( @RequestBody UserModel userModel) throws SQLException {


        String username =userModel.getUsername();
        String password =userModel.getPassword();
        AuthenticationService authentication = new AuthenticationService();

        UserModel user;
        user = authentication.findByUsernameAndPassword(username,password);

        if (user.getUsername() != null && user.getPassword()!= null) {
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/register" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public void signup( @RequestBody UserModelRegiter user) throws SQLException {


        String name = user.getName();
        String username =user.getUsername();
        String password =user.getPassword();
        String email = user.getEmail();
        String city = user.getCity();
        String country = user.getCountry();
        Date date = user.getDateOfBirth();
        String gender = user.getGender();

        System.out.println(name);
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(city);
        System.out.println(country);
        System.out.println(date);
        System.out.println(gender);


    }
}
