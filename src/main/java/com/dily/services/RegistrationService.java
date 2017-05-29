package com.dily.services;

import com.dily.Database;
import com.dily.models.UserModel;
import com.dily.models.UserModelRegister;

import java.sql.*;

/**
 * Created by rusum on 11.05.2017.
 */
public class RegistrationService implements IRegistrationService {
    @Override
    public boolean findByEmail(String email) throws SQLException {

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select user_id from user_table where email ='" + email + "'");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        if(id == 0){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean findByUsername(String username) throws SQLException {

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select user_id from user_table where username ='" + username + "'");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        if(id == 0){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public int findIdByEmail(String email) throws SQLException {

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select user_id from user_table where email ='" + email + "'");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        return id;
    }

    @Override
    public void addNewUser(UserModelRegister usermodel) throws SQLException {

        String name = usermodel.getName();
        String username =usermodel.getUsername();
        String password =usermodel.getPassword();
        String email = usermodel.getEmail();
        String city = usermodel.getCity();
        String country = usermodel.getCountry();
        Date date = usermodel.getBirth();
        String gender = usermodel.getGender();

        System.out.println(gender);

        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("insert into user_table ( name,username,password,email,date_of_birth,country,city,profile_picture,gender) values ( ?,?,?,?,?,?,?,?,?)")) {

            pstmt.setString(1, name);
            pstmt.setString(2,username);
            pstmt.setString(3,password);
            pstmt.setString(4,email);
            pstmt.setDate(5,date);
            pstmt.setString(6,country);
            pstmt.setString(7,city);
            pstmt.setString(8,"assets/images/default.jpg");
            pstmt.setString(9,gender);

            pstmt.executeUpdate();
            Database.commit();
        }
    }
}
