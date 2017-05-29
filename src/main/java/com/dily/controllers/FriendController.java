package com.dily.controllers;

import com.dily.entities.Memory;
import com.dily.models.FriendModel;
import com.dily.services.FriendService;
import com.dily.services.MemoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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


}
