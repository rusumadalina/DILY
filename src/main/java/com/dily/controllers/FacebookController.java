package com.dily.controllers;

import com.dily.models.AddMemoryModel;
import com.dily.models.UserModel;
import com.dily.models.UserModelRegister;
import com.dily.models.UserRegisterFacebookModel;
import com.dily.services.AuthenticationService;
import com.dily.services.NewMemoryService;
import com.dily.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rusum on 02.06.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class FacebookController {

        @RequestMapping(value = "/registerFacebook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
        public ResponseEntity<com.dily.entities.User> registerFacebook(@RequestBody UserRegisterFacebookModel user) throws SQLException, ParseException, InterruptedException {

        RegistrationService registrationService = new RegistrationService();
        AuthenticationService authenticationService = new AuthenticationService();
        com.dily.entities.User user1  ;
        int userId = registrationService.existFacebookUser(user.getId());
        if (userId == 0) {
            int id = registrationService.addNewFacebookUser(user);
            TimeUnit.SECONDS.sleep(2);
            registrationService.addInFacebookTable(id, user.getId());
            user1 = authenticationService.findByUserId(id);

        }else{
            user1 = authenticationService.findByUserId(userId);
        }

        return new ResponseEntity<com.dily.entities.User>(user1, HttpStatus.OK);

    }


    @RequestMapping(value = "/addNewFbMemory/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> facebookMemory(@RequestBody List<AddMemoryModel> addMemoryModel, @PathVariable int id) throws SQLException {

        RegistrationService registrationService = new RegistrationService();
        int flag = registrationService.memoriesGenerated(id);

        if(flag == 0) {
            for (int i = 0; i < addMemoryModel.size(); i++) {
                if (addMemoryModel.get(i).getPrivacy().equals("EVERYONE")) {
                    addMemoryModel.get(i).setPrivacy("public");
                } else {
                    addMemoryModel.get(i).setPrivacy("only friends");
                }
            }
            NewMemoryService newMemoryService = new NewMemoryService();
            for (int i = 0; i < addMemoryModel.size(); i++) {
                int idMem = newMemoryService.addMemory(addMemoryModel.get(i));
                newMemoryService.addInTimeline(idMem, id);
            }
            newMemoryService.setFlagFacebookMemory(id);
        }
        return new ResponseEntity<Integer>(1,HttpStatus.OK);
    }

}

