package com.dily.controllers;


import com.dily.Database;
import com.dily.entities.User;
import com.dily.models.Picture;
import com.dily.models.UserModel;


import com.dily.models.UserModelRegister;
import com.dily.services.*;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by Andra on 5/1/2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<User> login(@RequestBody UserModel userModel) throws SQLException {


        String username = userModel.getUsername();
        String password = userModel.getPassword();
        AuthenticationService authentication = new AuthenticationService();

        User user;
        user = authentication.findByUsernameAndPassword(username, password);

        if (user.getUsername() != null && user.getName() != null && user.getEmail() != null && user.getDateOfBirth() != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> signup(@RequestBody UserModelRegister usermodel) throws SQLException {

        RegistrationService registrationService = new RegistrationService();


        String email = usermodel.getEmail();
        String username = usermodel.getUsername();

        if (registrationService.findByEmail(email) == true) {
            return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
        }

        if (registrationService.findByUsername(username) == true) {
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
        }

        registrationService.addNewUser(usermodel);
        int id = registrationService.findIdByEmail(email);
        return new ResponseEntity<Integer>(id, HttpStatus.OK);

    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> settings(@RequestBody User user) throws SQLException {

        SettingsService settingsService = new SettingsService();
        settingsService.update(user);

        return new ResponseEntity<Integer>(1, HttpStatus.OK);


    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "multipart/form-data")
    public ResponseEntity<Integer> upload( MultipartHttpServletRequest request) throws Exception {

        UploadService uploadService = new UploadService();
        uploadService.upload(request, "/profilePictures");

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "user/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> delete(@PathVariable int id) throws SQLException {

        SettingsService settingsService = new SettingsService();
        settingsService.delete(id);

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
}