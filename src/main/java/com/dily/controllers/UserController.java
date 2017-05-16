package com.dily.controllers;


import com.dily.Database;
import com.dily.entities.User;
import com.dily.models.UserModel;


import com.dily.models.UserModelRegister;
import com.dily.services.AuthenticationService;
import com.dily.services.RegistrationService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by Andra on 5/1/2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class UserController {

    @RequestMapping(value="/login" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<User> login(@RequestBody UserModel userModel) throws SQLException {


        String username =userModel.getUsername();
        String password =userModel.getPassword();
        AuthenticationService authentication = new AuthenticationService();

        User user;
        user = authentication.findByUsernameAndPassword(username,password);


        //JSONObject jsonId = new JSONObject();

        //jsonId.put("id",id);

        if (user != null ) {
            System.out.println("sllslslslslssl");
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            System.out.println("maimutica");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/register" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> signup(@RequestBody UserModelRegister usermodel) throws SQLException {

        RegistrationService registrationService = new RegistrationService();
        JSONObject jsonMessage = new JSONObject();

        String email = usermodel.getEmail();
        String username = usermodel.getUsername();

        if (registrationService.findByEmail(email) == true ) {
            //jsonMessage.put("reason","email");
            return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
        }

        if(registrationService.findByUsername(username) == true ){
            //jsonMessage.put("reason","username");
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
        }

        registrationService.addNewUser(usermodel);
        int id = registrationService.findIdByEmail(email);
        //jsonMessage.put("id_nou",id);
        return new ResponseEntity<Integer>(id, HttpStatus.OK);

    }

    @RequestMapping(value="/settings" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public void settings(@RequestBody User user) throws SQLException {

        RegistrationService registrationService = new RegistrationService();
        JSONObject jsonMessage = new JSONObject();

        int id = user.getUser_id();
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        Date dateOfBirth = user.getDateOfBirth();
        String country = user.getCountry();
        String city = user.getCity();
        String profilePicture = user.getProfilePicture();
        String gender = user.getGender();


        System.out.println(id);
        System.out.println(name);
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(dateOfBirth);
        System.out.println(country);
        System.out.println(city);
        System.out.println(profilePicture);
        System.out.println(gender);

    }
}
