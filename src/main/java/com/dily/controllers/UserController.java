package com.dily.controllers;


import com.dily.Database;
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
    public ResponseEntity<Integer> login(@RequestBody UserModel userModel) throws SQLException {


        String username =userModel.getUsername();
        String password =userModel.getPassword();
        AuthenticationService authentication = new AuthenticationService();

        int id;
        id = authentication.findByUsernameAndPassword(username,password);


        //JSONObject jsonId = new JSONObject();

        //jsonId.put("id",id);

        if (id != 0) {
            return new ResponseEntity<Integer>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
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
}
