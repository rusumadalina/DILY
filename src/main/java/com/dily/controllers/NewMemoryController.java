package com.dily.controllers;

import com.dily.models.*;
import com.dily.services.FriendService;
import com.dily.services.NewMemoryService;
import com.dily.services.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rusum on 31.05.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class NewMemoryController {

    @RequestMapping(value = "/newMemory/friends/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<FriendModel>> getAllEarthquakes(@PathVariable int id) throws SQLException {
        FriendService friendService = new FriendService();

        List<FriendModel> all =  friendService.getAllFriends(id);

        return new ResponseEntity<List<FriendModel>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "newMemory/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "multipart/form-data")
    public ResponseEntity<Integer> upload(MultipartHttpServletRequest request) throws Exception {

        UploadService uploadService = new UploadService();
        uploadService.upload(request, "/mainPictures");

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "newMemory/uploadPictures", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "multipart/form-data")
    public ResponseEntity<Integer> uploadMore(MultipartHttpServletRequest request) throws Exception {

        UploadService uploadService = new UploadService();
        uploadService.upload(request, "/memory");

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMemory/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> addMemory(@PathVariable int id, @RequestBody AddMemoryModel addMemoryModel) throws SQLException, InterruptedException {

        int memId = 0;
        NewMemoryService newMemoryService = new NewMemoryService();
        memId = newMemoryService.addMemory(addMemoryModel);
        TimeUnit.SECONDS.sleep(2);
        newMemoryService.addInTimeline(memId,id);
        return new ResponseEntity<Integer>(memId, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMemory/tagged/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> addFriendsToMemory(@PathVariable int id, @RequestBody List<FriendIdModel> friendIdModels) throws SQLException {


        NewMemoryService newMemoryService = new NewMemoryService();
        for (int i=0; i < friendIdModels.size(); i++){
            newMemoryService.tagFriend(id, friendIdModels.get(i).getFriendId());
        }
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMemory/tags/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> addTagsToMemory(@PathVariable int id, @RequestBody List<TagModel> tagModels) throws SQLException {

        NewMemoryService newMemoryService = new NewMemoryService();

        for (int i=0; i<tagModels.size();i++){
            String tag = tagModels.get(i).getTagName();
            if (tag!=null){
                int tagId = newMemoryService.existTag(tag);
                if (tagId != 0){
                    newMemoryService.addTag(id,tagId);
                }else{
                    int newTagId = newMemoryService.addNewTag(tag);
                    newMemoryService.addTag(id,newTagId);
                }
            }
        }
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMemory/media/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> addMediaToMemory(@PathVariable int id, @RequestBody List<MediaModel> mediaModels) throws SQLException {

        NewMemoryService newMemoryService = new NewMemoryService();
        for (int i=0; i< mediaModels.size(); i++){

            newMemoryService.addMedia(mediaModels.get(i).getPicture(),id);

        }
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
}
