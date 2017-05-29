package com.dily.mappers;

import com.dily.entities.TagsAndMemories;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rusum on 29.05.2017.
 */
public  final class TagsAndMemoryMapper implements RowMapper<TagsAndMemories> {

    public TagsAndMemories mapRow(ResultSet rs, int rowNum) throws SQLException {
        TagsAndMemories tagsAndMemories = new TagsAndMemories();
        tagsAndMemories.setTagName(rs.getString("tag_name"));
        tagsAndMemories.setTitle(rs.getString("title"));
        tagsAndMemories.setDescription(rs.getString("description"));
        tagsAndMemories.setMemoryLocation(rs.getString("memorylocation"));
        tagsAndMemories.setDate(rs.getDate("datem"));
        tagsAndMemories.setMainPicture(rs.getString("mainpicture"));
        return tagsAndMemories;
    }
}