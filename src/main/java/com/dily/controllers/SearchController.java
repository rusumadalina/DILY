package com.dily.controllers;

import com.dily.entities.Memory;
import com.dily.models.FriendModel;
import com.dily.models.MemoryModel;
import com.dily.services.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

/**
 * Created by rusum on 29.05.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class SearchController {

    @RequestMapping(value = "search/tag/{word}", method = RequestMethod.GET)
    public ResponseEntity<List<Memory>> getAllMemories(@PathVariable String word) throws SQLException {
        SearchService searchMemByTag = new SearchService();

        List<Memory> all = searchMemByTag.findByTag(word);
        /*
        for (int i=0; i<all.size(); i++){
            System.out.println(all.get(i).getTitle());
            System.out.println(all.get(i).getDescription());
            System.out.println(all.get(i).getMainPicture());
            System.out.println(all.get(i).getDate());
            System.out.println(all.get(i).getMemoryLocation());
        }
        */
        return new ResponseEntity<List<Memory>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "search/friends/{word}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<FriendModel>> getFriends(@PathVariable String word,@PathVariable int id) throws SQLException {
        SearchService searchService = new SearchService();

        List<FriendModel> all =searchService.findFriends(word,id);
        for (int i=0; i<all.size(); i++){
            System.out.println(all.get(i).getDateFriends());
        }
        return new ResponseEntity<List<FriendModel>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "search/newFriends/{word}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<FriendModel>> getNewFriends(@PathVariable String word,@PathVariable int id) throws SQLException {
        SearchService searchService = new SearchService();

        List<FriendModel> all =searchService.findNewFriends(word,id);
        return new ResponseEntity<List<FriendModel>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "friends/add/{id}/{username}", method = RequestMethod.POST)
    public ResponseEntity<Integer> addNewFriends(@PathVariable int id, @PathVariable String username) throws SQLException {

        byte[] decodedBytes = Base64.getDecoder().decode(username);
        String decodedString = new String(decodedBytes);
        SearchService searchService = new SearchService();
        searchService.addFriendPair(decodedString,id);

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "search/newFriends/view/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<MemoryModel>> viewAllMemories (@PathVariable String username) throws SQLException {
        System.out.println("alooooooooooooooooooooooooo");
        byte[] decodedBytes = Base64.getDecoder().decode(username);
        String decodedString = new String(decodedBytes);
        SearchService searchService = new SearchService();
        List<MemoryModel> all = searchService.findMemoriesInTimeline(decodedString);
        for (int i=0; i<all.size();i++){
            System.out.println(all.get(i).getTitle());
        }
        return new ResponseEntity<List<MemoryModel>>(all, HttpStatus.OK);
    }

}
