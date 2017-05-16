package com.dily.services;

import com.dily.Database;
import com.dily.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rusum on 09.05.2017.
 */
public class AuthenticationService implements IAuthenticationService {
    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from user_table where username ='" + username + "' and password = '" + password + "'");

        User user = new User();

        while (rs.next()) {
            user.setUser_id(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setDateOfBirth(rs.getDate(6));
            user.setCountry(rs.getString(7));
            user.setCity(rs.getString(8));
            user.setProfilePicture(rs.getString(9));
            user.setGender(rs.getString(10));

        }
        rs.close();
        return user;
    }
}
