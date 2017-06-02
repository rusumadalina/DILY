package com.dily.controllers;

import com.dily.entities.Documents;
import com.dily.models.AddMemoryModel;
import com.dily.models.DocumentModel;
import com.dily.services.DocumentsService;
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
 * Created by rusum on 02.06.2017.
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api")
public class AddDocumentController {

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "multipart/form-data")
    public ResponseEntity<Integer> upload(MultipartHttpServletRequest request) throws Exception {
        UploadService uploadService = new UploadService();
        uploadService.uploadDocument(request, "");
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/addDocuments/save/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<Integer> addMemory(@PathVariable int id, @RequestBody List<DocumentModel> documentsModel) throws SQLException, InterruptedException {

        DocumentsService documentsService = new DocumentsService();
        for (int i=0; i<documentsModel.size();i++){
            documentsService.saveDocument(id,documentsModel.get(i).getPath());
        }
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }


    @RequestMapping(value = "/addDocuments/getDocuments/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Documents>> getAllDocuments(@PathVariable int id) throws SQLException {

        DocumentsService documentsService = new DocumentsService();
        List<Documents> all = documentsService.getAllDocuments(id);
        return new ResponseEntity<List<Documents>>(all, HttpStatus.OK);
    }

    //pi/addDocuments/deleteDocuments/' + userId+'/'+id,

    @RequestMapping(value = "/addDocuments/deleteDocuments/{userId}/{docId}", method = RequestMethod.GET)
    public ResponseEntity<Integer> delete(@PathVariable int userId, @PathVariable int docId) throws SQLException {
        DocumentsService documentsService = new DocumentsService();
        documentsService.deleteDocument(userId,docId);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

}
