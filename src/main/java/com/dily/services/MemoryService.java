package com.dily.services;

import com.dily.Database;
import com.dily.entities.Media;
import com.dily.entities.Memory;
import com.dily.entities.Tag;
import com.dily.entities.User;
import com.dily.models.FriendModel;
import com.dily.models.LargeMemory;
import com.dily.models.MemoryModel;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 25.05.2017.
 */
public class MemoryService implements IMemoryService {

    @Override
    public List<MemoryModel> findMemoriesInTimeline(int id) throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from memory m join timeline t on m.memoryid=t.memoryid where user_id = " + id + "order by datem asc");

        Memory memory;
        MemoryModel memoryModel;
        List<String> tags;
        List<MemoryModel> memories =  new LinkedList<>();
        List<Integer> ids = new LinkedList<>();
        while (rs.next()) {
            memoryModel = new MemoryModel();
            memoryModel.setMemoryId(rs.getInt(1));
            memoryModel.setTitle(rs.getString(2));
            memoryModel.setDescription(rs.getString(3));
            memoryModel.setMemoryLocation(rs.getString(4));
            memoryModel.setDate(rs.getDate(5));
            memoryModel.setPrivacy(rs.getString(6));
            memoryModel.setMainPicture(rs.getString(7));

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select t.tag_name from tag t join tag_memory tm on tm.tag_id=t.tag_id  where memoryid = " + memoryModel.getMemoryId());
            tags = new LinkedList<>();
            while (rs2.next()) {

                tags.add(rs2.getString(1));
            }
            rs2.close();
            memoryModel.setTags(tags);
            memories.add(memoryModel);
        }
        rs.close();
        return memories;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("delete from memory where memoryid = " + id)) {
            pstmt.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public List<LargeMemory> viewMemoryDetails(int id) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from memory where memoryid = " + id);


        LargeMemory largeMemory;
        List<Tag> tags;
        List<User> tagged;
        List<Media> mediaList;
        List<LargeMemory> memories =  new LinkedList<>();
        Tag tag ;
        Media media;
        User user;

        while (rs.next()) {
            largeMemory = new LargeMemory();
            largeMemory.setMemoryId(rs.getInt(1));
            largeMemory.setTitle(rs.getString(2));
            largeMemory.setDescription(rs.getString(3));
            largeMemory.setMemoryLocation(rs.getString(4));
            largeMemory.setDate(rs.getDate(5));
            largeMemory.setPrivacy(rs.getString(6));
            largeMemory.setMainPicture(rs.getString(7));

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select t.tag_id,t.tag_name from tag t join tag_memory tm on tm.tag_id=t.tag_id  where memoryid = " + largeMemory.getMemoryId());
            tags = new LinkedList<>();
            while (rs2.next()) {
                tag = new Tag();
                tag.setTagId(rs2.getInt(1));
                tag.setTagName(rs2.getString(2));
                tags.add(tag);
            }
            rs2.close();
            largeMemory.setTags(tags);

            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("select mediaid, memoryid, mediatype, mediapath  from media where memoryid = " + largeMemory.getMemoryId());
            mediaList = new LinkedList<>();
            while (rs3.next()) {
                media = new Media();

                media.setMediaId(rs3.getInt(1));
                media.setMemoryId(rs3.getInt(2));
                media.setMediaType(rs3.getString(3));
                media.setMediaPath(rs3.getString(4));
                mediaList.add(media);
            }
            rs3.close();
            largeMemory.setMedia(mediaList);


            Statement stmt4 = con.createStatement();
            ResultSet rs4 = stmt4.executeQuery("select user_id,name, username, password, email, date_of_birth, country, city,profile_picture, gender from user_table where user_id in (select user_id from tagged where memoryid = " + largeMemory.getMemoryId() + ")");
            tagged = new LinkedList<>();
            while (rs4.next()) {
                user = new User();
                user.setUser_id(rs4.getInt(1));
                user.setName(rs4.getString(2));
                user.setUsername(rs4.getString(3));
                user.setPassword(rs4.getString(4));
                user.setEmail(rs4.getString(5));
                user.setDateOfBirth(rs4.getDate(6));
                user.setCountry(rs4.getString(7));
                user.setCity(rs4.getString(8));
                user.setProfilePicture(rs4.getString(9));
                user.setGender(rs4.getString(10));
                tagged.add(user);
            }
            rs3.close();
            largeMemory.setTaggedFriends(tagged);

            memories.add(largeMemory);
        }
        rs.close();
        return memories;
    }


}

