package com.dily.services;

import com.dily.Database;
import com.dily.models.UserModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rusum on 09.05.2017.
 */
public class AuthenticationService implements IAuthenticationService {
    @Override
    public int findByUsernameAndPassword(String username, String password) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select user_id from user_table where username ='" + username + "' and password = '" + password + "'");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();

        return id;
    }
}
