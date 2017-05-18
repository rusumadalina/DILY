package com.dily.controllers;


import com.dily.Database;
import com.dily.entities.User;
import com.dily.models.Picture;
import com.dily.models.UserModel;


import com.dily.models.UserModelRegister;
import com.dily.services.AuthenticationService;
import com.dily.services.RegistrationService;
import com.dily.services.SettingsService;
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


        //JSONObject jsonId = new JSONObject();

        //jsonId.put("id",id);

        if (user.getUsername() != null && user.getName() != null && user.getEmail() != null && user.getDateOfBirth() != null) {

            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> signup(@RequestBody UserModelRegister usermodel) throws SQLException {

        RegistrationService registrationService = new RegistrationService();
        JSONObject jsonMessage = new JSONObject();

        String email = usermodel.getEmail();
        String username = usermodel.getUsername();

        if (registrationService.findByEmail(email) == true) {
            //jsonMessage.put("reason","email");
            return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
        }

        if (registrationService.findByUsername(username) == true) {
            //jsonMessage.put("reason","username");
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
        }

        registrationService.addNewUser(usermodel);
        int id = registrationService.findIdByEmail(email);
        //jsonMessage.put("id_nou",id);
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

        System.out.println("maimuutica");
        System.out.println(request.getContentLength());

        Iterator<String> itrator = request.getFileNames();
        MultipartFile multiFile = request.getFile(itrator.next());
        try {
            // just to show that we have actually received the file
            System.out.println("File Length:" + multiFile.getBytes().length);
            System.out.println("File Type:" + multiFile.getContentType());
            String fileName=multiFile.getOriginalFilename();
            System.out.println("File Name:" +fileName);
            String path=request.getServletContext().getRealPath("/");

            //making directories for our required path.
            byte[] bytes = multiFile.getBytes();
            File directory=    new File(path+ "/uploads");
            directory.mkdirs();
            // saving the file
            File file=new File(directory.getAbsolutePath()+System.getProperty("file.separator")+fileName);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error while loading the file");
        }



//        System.out.println(file);
//
//        if (!file.isEmpty()) {
//            try {
//                String uploadsDir = "/uploads/";
//                String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/uploads");
//
//                if (!new File(realPathtoUploads).exists()) {
//                    new File(realPathtoUploads).mkdir();
//                }
//
//                String orgName = file.getOriginalFilename();
//                String filePath = realPathtoUploads + orgName;
//                File dest = new File(filePath);
//                file.transferTo(dest);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
//                // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists())
//                    dir.mkdirs();
//
//                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath()
//                        + File.separator + name);
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
//
//                System.out.println("You successfully uploaded file=" + name);
//                return new ResponseEntity<Integer>(1, HttpStatus.OK);
//            } catch (Exception e) {
//                System.out.println("You failed to upload " + name + " => " + e.getMessage());
//                return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
//            }
//        } else {
//            System.out.println( "You failed to upload " + name
//                    + " because the file was empty.");
//            return new ResponseEntity<Integer>(2, HttpStatus.NOT_FOUND);
//        }

                //   }


       // }
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
}