package com.dily.services;

import com.dily.Database;
import com.dily.entities.Memory;
import com.dily.models.FriendModel;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 29.05.2017.
 */
public class SearchService implements ISearchService {
    @Override
    public List<Memory> findByTag(String tag) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from tag_and_memory where tag_name = '" + tag + "'");
        Memory memory;
        List<Memory> memories =  new LinkedList<>();
        while (rs.next()) {
            memory = new Memory();
            memory.setMemoryId(rs.getInt(2));
            memory.setTitle(rs.getString(3));
            memory.setDescription(rs.getString(4));
            memory.setMemoryLocation(rs.getString(5));
            memory.setDate(rs.getDate(6));
            memory.setPrivacy(rs.getString(7));
            memory.setMainPicture(rs.getString(8));
            memories.add(memory);
        }
        rs.close();

        return memories;
    }


    @Override
    public List<FriendModel> findFriends(String word, int id) throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select datefriends, name, profile_picture, city, country from (select datefriends, name, profile_picture, city, country from (select r.user2, datefriends from user_table u join relationship r on u.user_id = r.user1 where user_id = " + id + " ) join user_table t on user_id=user2 UNION select datefriends, name, profile_picture, city, country from (select r.user1, datefriends from user_table u join relationship r on u.user_id = r.user2 where user_id =" + id + ") join user_table t on user_id=user1) where name like " + "'%" + word + "%'");

        FriendModel friend ;
        List<FriendModel> friends = new LinkedList<>();

        while (rs.next()) {
            friend = new FriendModel();
            friend.setDateFriends(rs.getDate(1));
            friend.setName(rs.getString(2));
            friend.setProfilePicture(rs.getString(3));
            friend.setCity(rs.getString(4));
            friend.setCountry(rs.getString(5));

            friends.add(friend);
        }
        rs.close();
        return friends;
    }

    @Override
    public List<FriendModel> findNewFriends(String word, int id) throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select username, datefriends, name, profile_picture, city, country from ((select username,datefriends, name, profile_picture, city, country from (select username,datefriends, name, profile_picture, city, country from (select r.user2, datefriends from user_table u join relationship r on u.user_id = r.user1) join user_table t on user_id=user2 UNION select username, datefriends, name, profile_picture, city, country from (select r.user1, datefriends from user_table u join relationship r on u.user_id = r.user2) join user_table t on user_id=user1)) MINUS (select username, datefriends, name, profile_picture, city, country from (select username, datefriends, name, profile_picture, city, country from (select r.user2, datefriends from user_table u join relationship r on u.user_id = r.user1 where user_id = " + id + " ) join user_table t on user_id=user2 UNION select username, datefriends, name, profile_picture, city, country from (select r.user1, datefriends from user_table u join relationship r on u.user_id = r.user2 where user_id = " + id + ") join user_table t on user_id=user1))) where name like '%" + word + "%'");

        FriendModel friend ;
        List<FriendModel> friends = new LinkedList<>();

        while (rs.next()) {
            friend = new FriendModel();
            friend.setUsername(rs.getString(1));
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
    public void addFriendPair(String username, int id) throws SQLException {


        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select user_id from user_table where username ='" + username + "'");

        int friendId = 0;

        while (rs.next()) {
            friendId = rs.getInt(1);
        }
        rs.close();


        try (PreparedStatement pstmt = con.prepareStatement("insert into relationship ( user1,user2,datefriends) values ( ?,?,SYSDATE)")) {

            pstmt.setInt(1, id);
            pstmt.setInt(2,friendId);
            pstmt.executeUpdate();
            Database.commit();
        }
    }
}

