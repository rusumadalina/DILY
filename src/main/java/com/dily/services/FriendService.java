package com.dily.services;

import com.dily.Database;
import com.dily.models.FriendModel;

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
}
