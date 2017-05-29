package com.dily.controllers;

import com.dily.entities.Memory;
import com.dily.models.MemoryModel;
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
    public ResponseEntity<List<MemoryModel>> getAllMemories(@PathVariable int id) throws SQLException {
        MemoryService memoryService = new MemoryService();

        List<MemoryModel> all =  memoryService.findMemoriesInTimeline(id);
        return new ResponseEntity<List<MemoryModel>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/memories/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> delete(@PathVariable int id) throws SQLException {
        MemoryService memoryService = new MemoryService();
        memoryService.delete(id);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

}
