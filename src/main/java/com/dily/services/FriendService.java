package com.dily.services;

import com.dily.Database;
import com.dily.entities.Memory;
import com.dily.models.FriendModel;
import com.dily.models.MemoryModel;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 26.05.2017.
 */
public class FriendService implements IFriendService {


    @Override
    public List<FriendModel> getAllFriends(int id) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("(select user2, datefriends, name, profile_picture, city, country from (select r.user2, datefriends from user_table u join relationship r on u.user_id = r.user1 where user_id = " + id + ") join user_table t on user_id=user2) UNION (select user1, datefriends, name, profile_picture, city, country from (select r.user1, datefriends from user_table u join relationship r on u.user_id = r.user2 where user_id = " + id + ") join user_table t on user_id=user1)");

        FriendModel friend ;
        List<FriendModel> friends = new LinkedList<>();

        while (rs.next()) {
            friend = new FriendModel();
            friend.setFriendId(rs.getInt(1));
            friend.setDateFriends(rs.getDate(2));
            friend.setName(rs.getString(3));
            friend.setProfilePicture(rs.getString(4));
            friend.setCity(rs.getString(5));
            friend.setCountry(rs.getString(6));

            friends.add(friend);
        }
        rs.close();
        return friends;
    }

    @Override
    public void deletePair(int userId, int friendId) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("delete from relationship where (user1 = " + userId + "and user2 = " + friendId + ") or (user1 = " + friendId + "and user2 = " + userId + ")")) {
            pstmt.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public int findUserByUsername(String username) throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        int id = 0;
        ResultSet rs = stmt.executeQuery("select user_id from user_table where username = '" + username + "'");

        while (rs.next()) {
           id = rs.getInt(1);
        }
        rs.close();
       return id;
    }

    public List<MemoryModel> findMemoriesInTimeline(int id) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from memory m join timeline t on m.memoryid=t.memoryid where user_id = " + id +" and (privacy = 'public' or privacy = 'only friends') order by datem asc");

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

}
