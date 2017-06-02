package com.dily.services;

import com.dily.Database;
import com.dily.models.AddMemoryModel;
import com.dily.models.FriendModel;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 01.06.2017.
 */
public class NewMemoryService implements INewMemoryService {
    @Override
    public int addMemory(AddMemoryModel addMemoryModel) throws SQLException {

        String title = addMemoryModel.getTitle();
        String description = addMemoryModel.getDescription();
        String memoryLocation = addMemoryModel.getMemoryLocation();
        Date date = addMemoryModel.getDate();
        String privacy = addMemoryModel.getPrivacy();
        String mainPicture = addMemoryModel.getMainPicture();

        Connection con = Database.getConnection();

        Statement stmt1 = con.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select max(memoryid) from memory");

        int maxId = 0;
        while (rs1.next()) {
            maxId = rs1.getInt(1);
        }
        rs1.close();

        int memoryId = maxId + 1;

        try (PreparedStatement pstmt = con.prepareStatement("insert into memory (memoryid, title, description, memoryLocation, datem, privacy, mainPicture) values (?,?,?,?,?,?,?)")) {


            pstmt.setInt(1,memoryId);
            pstmt.setString(2,title);
            pstmt.setString(3,description);
            pstmt.setString(4,memoryLocation);
            pstmt.setDate(5,date);
            pstmt.setString(6,privacy);
            pstmt.setString(7,mainPicture);
            pstmt.executeUpdate();
            Database.commit();
        }


    return memoryId;
    }

    @Override
    public void addInTimeline(int memId, int userId) throws SQLException {

        Connection con = Database.getConnection();
        try (PreparedStatement pstmt2 = con.prepareStatement("insert into timeline (memoryid, user_id) values (?,?)")) {


            pstmt2.setInt(1,memId);
            pstmt2.setInt(2,userId);

            pstmt2.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public void tagFriend(int memId, int userId) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt2 = con.prepareStatement("insert into tagged (memoryid, user_id) values (?,?)")) {


            pstmt2.setInt(1,memId);
            pstmt2.setInt(2,userId);

            pstmt2.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public int existTag(String tag) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt1 = con.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select tag_id from tag where tag_name = '" + tag + "'");

        int tagId = 0;
        while (rs1.next()) {
            tagId = rs1.getInt(1);
        }
        rs1.close();

        return tagId;
    }

    @Override
    public void addTag(int memId, int tagId) throws SQLException {

        Connection con = Database.getConnection();
        try (PreparedStatement pstmt2 = con.prepareStatement("insert into tag_memory (tag_id, memoryid) values (?,?)")) {


            pstmt2.setInt(1, tagId);
            pstmt2.setInt(2,memId);

            pstmt2.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public int addNewTag(String tag) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt1 = con.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select max(tag_id) from tag");

        int maxId = 0;
        while (rs1.next()) {
            maxId = rs1.getInt(1);
        }
        rs1.close();

        int tagId = maxId + 1;

        try (PreparedStatement pstmt = con.prepareStatement("insert into tag (tag_name, tag_id) values (?,?)")) {


            pstmt.setString(1,tag);
            pstmt.setInt(2,tagId);
            pstmt.executeUpdate();
            Database.commit();
        }
        return tagId;
    }

    @Override
    public void addMedia(String path, int memId) throws SQLException {

        Connection con = Database.getConnection();
        try (PreparedStatement pstmt2 = con.prepareStatement("insert into media (mediatype,memoryid, mediapath) values (?,?,?)")) {


            pstmt2.setString(1,"image");
            pstmt2.setInt(2,memId);
            pstmt2.setString(3,path);

            pstmt2.executeUpdate();
            Database.commit();
        }

    }

}
