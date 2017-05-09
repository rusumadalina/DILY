package com.dily.mappers;

import com.dily.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andra on 5/1/2017.
 */
public  final class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setCountry(rs.getString("country"));
        user.setCity(rs.getString("city"));
        user.setProfilePicture(rs.getString("profile_picture"));
        return user;
    }
}