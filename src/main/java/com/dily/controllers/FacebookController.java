package com.dily.controllers;

import com.dily.models.UserModel;
import com.dily.models.UserModelRegister;
import com.dily.models.UserRegisterFacebookModel;
import com.dily.services.AuthenticationService;
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
import java.util.concurrent.TimeUnit;

/**
 * Created by rusum on 02.06.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class FacebookController {

//    @RequestMapping(value = "/facebook/signup/{id}/{token}", method = RequestMethod.GET)
//    public ResponseEntity<Integer> deleteSearch(@PathVariable String id, @PathVariable String token) throws SQLException {
//        //System.out.println(username+" "+id);
//        byte[] decodedBytes = Base64.getDecoder().decode(id);
//        String decodedId = new String(decodedBytes);
//
//        byte[] decodedBytes2 = Base64.getDecoder().decode(token);
//        String decodedToken = new String(decodedBytes2);
//
//        //Facebook facebook = new FacebookTemplate(decodedToken);
//        //User profile = facebook.userOperations().getUserProfile();
//
//        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory("826899354145289","0abb1788e82aa6040aa11502cf30714b");
//        AccessGrant accessGrant = new AccessGrant(decodedToken);
//        Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
//        Facebook facebook = connection.getApi();
//        String [] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", "website", "work"};
//        User userProfile = facebook.fetchObject("me", User.class, fields);
//
//        //String facebookId = userProfile.getId();
//        System.out.println(userProfile.getLastName());
//        System.out.println(userProfile.getFirstName());
//        System.out.println(userProfile.getEmail());
//        System.out.println(userProfile.getBirthday());
//        System.out.println(userProfile.getHometown());
//        System.out.println(userProfile.getGender());
//
//
//
//
//
//        String facebookId = userProfile.getId();
//        String name;
//        if(userProfile.getLastName().equals(null) &&  userProfile.getFirstName().equals(null)){
//            name = "undefined";
//        }else{
//            name = userProfile.getLastName() + " " + userProfile.getFirstName();
//        }
//        String username =  (userProfile.getLastName() + "." + userProfile.getFirstName()).toLowerCase() + facebookId;
//        String password = facebookId;
//        String email;
//        if (userProfile.getEmail()==null){
//            email = "undefined";
//        }else {
//            email = userProfile.getEmail();
//        }
//        Date birthday;
//        if(userProfile.getBirthday()==null){
//            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            //LocalDateTime now = LocalDateTime.now();
//            birthday = new java.sql.Date(new java.util.Date().getTime());
//        }else {
//            birthday  = Date.valueOf(userProfile.getBirthday());
//        }
//
//        String country = "undefined";
//        String city ;
//        if (String.valueOf(userProfile.getHometown())==null){
//            city = "undedfined";
//        }else {
//            city = String.valueOf(userProfile.getHometown());
//        }
//        String gender;
//        if( userProfile.getGender()==null){
//            gender = "undefined";
//        }else {
//            gender = userProfile.getGender();
//        }
//
//        UserModelRegister userModelRegister = new UserModelRegister();
//        userModelRegister.setName(name);
//        userModelRegister.setUsername(username);
//        userModelRegister.setPassword(password);
//        userModelRegister.setEmail(email);
//        userModelRegister.setBirth(birthday);
//        userModelRegister.setCountry(country);
//        userModelRegister.setCity(city);
//        userModelRegister.setGender(gender);
//
//        RegistrationService registrationService = new RegistrationService();
//        int idNou = registrationService.addNewUser(userModelRegister);
//
//
//        registrationService.addInFacebookTable(idNou,facebookId);
//
//        return new ResponseEntity<Integer>(1, HttpStatus.OK);
//    }

    @RequestMapping(value = "/registerFacebook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<com.dily.entities.User> registerFacebook(@RequestBody UserRegisterFacebookModel user) throws SQLException, ParseException, InterruptedException {

//        System.out.println(user.getId());
//        System.out.println(user.getName());
//        System.out.println(user.getEmail());
//        System.out.println(user.getBirth());
//        System.out.println(user.getCity());
//        System.out.println(user.getCountry());
//        System.out.println(user.getGender());
//        System.out.println(user.getProfilePicture());


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


}

