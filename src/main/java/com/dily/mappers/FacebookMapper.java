package com.dily.mappers;

import com.dily.entities.FacebookTable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rusum on 02.06.2017.
 */
public final class FacebookMapper implements RowMapper<FacebookTable> {
    public FacebookTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        FacebookTable facebookTable = new FacebookTable();
        facebookTable.setUserId(rs.getInt("user_id"));
        facebookTable.setFacebookId(rs.getString("facebook_id"));
        return facebookTable;
    }
}