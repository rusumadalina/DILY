package com.dily.services;

import com.dily.Database;
import com.dily.entities.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by rusum on 16.05.2017.
 */
public class SettingsService implements ISettingsService {

    @Override
    public void update(User user) throws SQLException {
        int id = user.getUser_id();
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        Date dateOfBirth = user.getDateOfBirth();
        String country = user.getCountry();
        String city = user.getCity();
        String profilePicture = user.getProfilePicture();
        System.out.println(profilePicture
        );
        String gender = user.getGender();

        Connection con = Database.getConnection();

        String path = profilePicture;

        try (PreparedStatement pstmt = con.prepareStatement( "UPDATE user_table SET  name = ?, username = ?, password = ?, email = ?, date_of_birth = ?, country = ?, city = ?, profile_picture = ?, gender = ? where user_id = ?")){


            pstmt.setString(1, name);
            pstmt.setString(2,username);
            pstmt.setString(3,password);
            pstmt.setString(4,email);
            pstmt.setDate(5,dateOfBirth);
            pstmt.setString(6,country);
            pstmt.setString(7,city);
            pstmt.setString(8,path);
            pstmt.setString(9,gender);
            pstmt.setInt(10,id);

            pstmt.executeUpdate();
            Database.commit();

        }
    }

}
