package com.dily.services;

import com.dily.Database;
import com.dily.models.FriendModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        ResultSet rs = stmt.executeQuery("(select datefriends, name, profile_picture, city, country from (select r.user2, datefriends from user_table u join relationship r on u.user_id = r.user1 where user_id = " + id + ") join user_table t on user_id=user2) UNION (select datefriends, name, profile_picture, city, country from (select r.user1, datefriends from user_table u join relationship r on u.user_id = r.user2 where user_id = " + id + ") join user_table t on user_id=user1)");

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
}
