package com.dily.services;

import com.dily.Database;
import com.dily.models.AddMemoryModel;
import org.codehaus.groovy.runtime.StringGroovyMethods;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by rusum on 02.06.2017.
 */
public class EditMemoryService implements IEditMemoryService {
    @Override
    public void editMemory(int memId, AddMemoryModel addMemoryModel) throws SQLException {

        String title = addMemoryModel.getTitle();
        String description = addMemoryModel.getDescription();
        String location = addMemoryModel.getMemoryLocation();
        Date date = addMemoryModel.getDate();
        String privacy = addMemoryModel.getPrivacy();
        String picture = addMemoryModel.getMainPicture();

        Connection con = Database.getConnection();


        try (PreparedStatement pstmt = con.prepareStatement( "UPDATE memory SET  title = ?, description = ?, memorylocation = ?, datem = ?, privacy = ?, mainpicture = ? where memoryid = ?")){


            pstmt.setString(1,title);
            pstmt.setString(2,description);
            pstmt.setString(3,location);
            pstmt.setDate(4,date);
            pstmt.setString(5,privacy);
            pstmt.setString(6,picture);
            pstmt.setInt(7,memId);

            pstmt.executeUpdate();
            Database.commit();

        }
    }

    @Override
    public void deleteMedia(int medId) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("delete from media where mediaid = " + medId)) {
            pstmt.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public void deleteTag(int tagId, int memId) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("delete from tag_memory where tag_id= " + tagId + "and memoryid = "+memId)) {
            pstmt.executeUpdate();
            Database.commit();
        }
    }

    @Override
    public void deleteTagged(int memId, int userId) throws SQLException {

        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("delete from tagged where memoryid= " + memId + "and user_id = "+ userId)) {
            pstmt.executeUpdate();
            Database.commit();
        }
    }
}
