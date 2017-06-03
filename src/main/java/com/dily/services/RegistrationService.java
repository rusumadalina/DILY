package com.dily.services;

import com.dily.Database;
import com.dily.models.UserModelRegister;
import com.dily.models.UserRegisterFacebookModel;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public int addNewUser(UserModelRegister usermodel) throws SQLException {

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
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select max(user_id) from user_table");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        id = id + 1;


        try (PreparedStatement pstmt = con.prepareStatement("insert into user_table ( user_id, name,username,password,email,date_of_birth,country,city,profile_picture,gender) values ( ?,?,?,?,?,?,?,?,?,?)")) {

            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3,username);
            pstmt.setString(4,password);
            pstmt.setString(5,email);
            pstmt.setDate(6,date);
            pstmt.setString(7,country);
            pstmt.setString(8,city);
            pstmt.setString(9,"assets/images/default.jpg");
            pstmt.setString(10,gender);

            pstmt.executeUpdate();
            Database.commit();
        }

        return id;
    }

    public int addNewFacebookUser(UserRegisterFacebookModel usermodel) throws SQLException, ParseException {

        String idFacebook = usermodel.getId();
        String name = usermodel.getName();
        String username = usermodel.getEmail();;
        String password = idFacebook;
        String email = usermodel.getEmail();
        String city = usermodel.getCity();
        String country = usermodel.getCountry();
        String dateS = usermodel.getBirth();
        System.out.println(dateS);
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        java.util.Date date = format.parse(dateS);
        java.sql.Date dataSql = new java.sql.Date(date.getTime());;
        String gender = usermodel.getGender();
        String profilePicture = usermodel.getProfilePicture();

        System.out.println(gender);

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select max(user_id) from user_table");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        id = id + 1;


        try (PreparedStatement pstmt = con.prepareStatement("insert into user_table ( user_id, name,username,password,email,date_of_birth,country,city,profile_picture,gender) values ( ?,?,?,?,?,?,?,?,?,?)")) {

            pstmt.setInt(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3,username);
            pstmt.setString(4,password);
            pstmt.setString(5,email);
            pstmt.setDate(6,dataSql);
            pstmt.setString(7,country);
            pstmt.setString(8,city);
            pstmt.setString(9,profilePicture);
            pstmt.setString(10,gender);

            pstmt.executeUpdate();
            Database.commit();
        }

        return id;
    }

    @Override
    public int existFacebookUser(String facebookId) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select user_id from facebook_table where facebook_id ='" + facebookId + "'");

        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        return id;
    }

    @Override
    public int memoriesGenerated(int id) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select memories_created from facebook_table where user_id = "+id);

        int flag = 0;
        while (rs.next()) {
            flag = rs.getInt(1);
        }
        rs.close();
        return flag;
    }

    @Override
    public void addInFacebookTable(int userId, String facebookId) throws SQLException {

        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("insert into facebook_table ( user_id, facebook_id,memories_created ) values (?,?,?)")) {

            pstmt.setInt(1,userId);
            pstmt.setString(2, facebookId);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
            Database.commit();
        }

    }
}
