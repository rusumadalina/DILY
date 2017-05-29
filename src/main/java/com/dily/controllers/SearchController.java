package com.dily.controllers;

import com.dily.entities.Memory;
import com.dily.services.SearchMemByTag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
        SearchMemByTag searchMemByTag = new SearchMemByTag();

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


}
