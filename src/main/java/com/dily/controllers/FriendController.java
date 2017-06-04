package com.dily.controllers;

import com.dily.entities.Memory;
import com.dily.models.FriendModel;
import com.dily.models.MemoryModel;
import com.dily.services.FriendService;
import com.dily.services.MemoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

/**
 * Created by rusum on 26.05.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class FriendController {
    @RequestMapping(value = "/friends/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<FriendModel>> getAllEarthquakes(@PathVariable int id) throws SQLException {

        FriendService friendService = new FriendService();
        List<FriendModel> all =  friendService.getAllFriends(id);

        return new ResponseEntity<List<FriendModel>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/friends/delete/{userId}/{friendId}", method = RequestMethod.GET)
    public ResponseEntity<Integer> delete(@PathVariable int userId, @PathVariable int friendId) throws SQLException {

        FriendService friendService = new FriendService();
        friendService.deletePair(userId,friendId);

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/friends/deleteSearch/{username}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> deleteSearch(@PathVariable String username, @PathVariable int id) throws SQLException {

        byte[] decodedBytes = Base64.getDecoder().decode(username);
        String decodedString = new String(decodedBytes);

        FriendService friendService = new FriendService();

        int id2 = friendService.findUserByUsername(decodedString);
        friendService.deletePair(id,id2);

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }


    @RequestMapping(value = "/friends/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MemoryModel>> viewAllMemoriers (@PathVariable int id) throws SQLException {

        FriendService friendService = new FriendService();

        List<MemoryModel> all =  friendService.findMemoriesInTimeline(id);
        return new ResponseEntity<List<MemoryModel>>(all, HttpStatus.OK);
    }
}
