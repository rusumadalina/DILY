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
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public UserModel findByUsernameAndPassword(String username, String password) throws SQLException {

        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from user_table where username ='" + username + "' and password = '" + password + "'");

        UserModel userModel = new UserModel();
        while (rs.next()) {
            userModel.setUsername(rs.getString(3));
            userModel.setPassword(rs.getString(4));
        }

        rs.close();

        return userModel;
    }
}
