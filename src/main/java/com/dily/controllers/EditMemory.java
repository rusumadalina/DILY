package com.dily.controllers;

import com.dily.models.AddMemoryModel;
import com.dily.models.FriendIdModel;
import com.dily.models.FriendModel;
import com.dily.services.EditMemoryService;
import com.dily.services.FriendService;
import com.dily.services.NewMemoryService;
import com.dily.services.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

/**
 * Created by rusum on 02.06.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class EditMemory {

    @RequestMapping(value = "editMemory/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "multipart/form-data")
    public ResponseEntity<Integer> upload(MultipartHttpServletRequest request) throws Exception {
        UploadService uploadService = new UploadService();
        uploadService.upload(request, "/mainPictures");
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> addFriendsToMemory(@PathVariable int id, @RequestBody AddMemoryModel addMemoryModel) throws SQLException {

//        System.out.println(id);
//        System.out.println(addMemoryModel.getTitle());
//        System.out.println(addMemoryModel.getDescription());
//        System.out.println(addMemoryModel.getMemoryLocation());
//        System.out.println(addMemoryModel.getDate());
//        System.out.println(addMemoryModel.getPrivacy());
//        System.out.println(addMemoryModel.getMainPicture());

        EditMemoryService editMemoryService = new EditMemoryService();
        editMemoryService.editMemory(id,addMemoryModel);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/editMemory/deleteMedia/{id}", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteMedia(@PathVariable int id) throws SQLException {

        System.out.println(id);
        EditMemoryService editMemoryService = new EditMemoryService();
        editMemoryService.deleteMedia(id);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/editMemory/deleteTags/{idTag}/{idMem}", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteTag(@PathVariable int idTag, @PathVariable int idMem) throws SQLException {



        EditMemoryService editMemoryService = new EditMemoryService();
        editMemoryService.deleteTag(idTag,idMem);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }


    @RequestMapping(value = "/editMemory/deleteTagged/{username}/{idMem}", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteTagged(@PathVariable String username, @PathVariable int idMem) throws SQLException {

        byte[] decodedBytes = Base64.getDecoder().decode(username);
        String decodedString = new String(decodedBytes);

        FriendService friendService = new FriendService();
        int idUser = friendService.findUserByUsername(decodedString);

        EditMemoryService editMemoryService = new EditMemoryService();
        editMemoryService.deleteTagged(idMem,idUser);

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

}
