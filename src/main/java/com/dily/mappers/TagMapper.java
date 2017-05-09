package com.dily.mappers;

import com.dily.entities.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andra on 5/1/2017.
 */
public final class TagMapper  implements RowMapper<Tag> {
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag=new Tag();
        tag.setTagId(rs.getInt("tag_id"));
        tag.setTagName(rs.getString("tag_name"));
        return tag;
    }
}
