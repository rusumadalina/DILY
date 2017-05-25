package com.dily.controllers;

import com.dily.entities.Memory;
import com.dily.repositories.IMemoryRepository;
import com.dily.services.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 25.05.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class MemoryController {

    @RequestMapping(value = "/memories/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Memory>> getAllEarthquakes(@PathVariable int id) throws SQLException {
        MemoryService memoryService = new MemoryService();

        List<Memory> all =  memoryService.findMemoriesInTimeline(id);

        /*

        for (int i=0; i< all.size(); i++)
        {
            System.out.println(all.get(i).getTitle());
        }*/

        return new ResponseEntity<List<Memory>>(all, HttpStatus.OK);
    }
}
