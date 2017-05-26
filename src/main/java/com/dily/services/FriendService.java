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
        ResultSet rs = stmt.executeQuery("select u.profile_picture, u.name, u.city, u.country, f.datefriends from user_table u join relationship f on u.user_id = f.USER2 where f.user1= " + id);

        FriendModel friend ;
        List<FriendModel> friends = new LinkedList<>();

        while (rs.next()) {
            friend = new FriendModel();
            friend.setProfilePicture(rs.getString(1));
            friend.setName(rs.getString(2));
            friend.setCity(rs.getString(3));
            friend.setCountry(rs.getString(4));
            friend.setDateFriends(rs.getDate(5));
            friends.add(friend);
        }
        rs.close();
        return friends;
    }
}
