package com.dily.services;

import com.dily.Database;
import com.dily.entities.Documents;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 02.06.2017.
 */
public class DocumentsService implements IDocumentsService{
    @Override
    public int saveDocument(int userId, String path) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt1 = con.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select max(document_id) from documents");

        int maxId = 0;
        while (rs1.next()) {
            maxId = rs1.getInt(1);
        }
        rs1.close();

        int documentId = maxId + 1;

        try (PreparedStatement pstmt = con.prepareStatement("insert into documents (document_id, user_id,document_path) values (?,?,?)")) {


           pstmt.setInt(1,documentId);
           pstmt.setInt(2,userId);
           pstmt.setString(3,path);
            pstmt.executeUpdate();
            Database.commit();
        }

        return documentId;
    }

    @Override
    public List<Documents> getAllDocuments(int userId) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select document_id, user_id, document_path from documents where user_id = " + userId);

       List<Documents> documents = new LinkedList<>();
       Documents document ;
        while (rs.next()) {
            document = new Documents();
            document.setDocumentId(rs.getInt(1));
            document.setUserId(rs.getInt(2));
            document.setPath(rs.getString(3));
            documents.add(document);
        }
        rs.close();
        return documents;
    }

    @Override
    public void deleteDocument(int userId, int docId) throws SQLException {

            Connection con = Database.getConnection();

            try (PreparedStatement pstmt = con.prepareStatement("delete from documents where user_id =" + userId + "and document_id =" + docId)) {
                pstmt.executeUpdate();
                Database.commit();
            }

    }
}
