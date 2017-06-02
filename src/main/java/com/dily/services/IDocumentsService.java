package com.dily.services;

import com.dily.entities.Documents;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 02.06.2017.
 */
public interface IDocumentsService {

    public int saveDocument (int userId, String path) throws SQLException;
    public List<Documents> getAllDocuments (int userId) throws SQLException;
    public void deleteDocument (int userId, int docId) throws SQLException;
}
