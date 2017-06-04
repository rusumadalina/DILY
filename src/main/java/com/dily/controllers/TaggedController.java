package com.dily.controllers;

import com.dily.models.MemoryModel;
import com.dily.services.TaggedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 31.05.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class TaggedController {

    @RequestMapping(value = "/tagged/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MemoryModel>> viewAllMemoriers (@PathVariable int id) throws SQLException {

        TaggedService taggedService = new TaggedService();
        List<MemoryModel> all = taggedService.taggedInMemories(id);

        return new ResponseEntity<List<MemoryModel>>(all, HttpStatus.OK);
    }
}
