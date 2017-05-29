package com.dily.services;

import com.dily.Database;
import com.dily.entities.Memory;
import com.dily.entities.User;
import com.dily.models.MemoryModel;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 25.05.2017.
 */
public class MemoryService implements IMemoryService {

    @Override
    public List<MemoryModel> findMemoriesInTimeline(int id) throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from memory m join timeline t on m.memoryid=t.memoryid where user_id = " + id);

        Memory memory;
        MemoryModel memoryModel;
        List<String> tags;
        List<MemoryModel> memories =  new LinkedList<>();
        List<Integer> ids = new LinkedList<>();
        while (rs.next()) {
            memoryModel = new MemoryModel();
            memoryModel.setMemoryId(rs.getInt(1));
            memoryModel.setTitle(rs.getString(2));
            memoryModel.setDescription(rs.getString(3));
            memoryModel.setMemoryLocation(rs.getString(4));
            memoryModel.setDate(rs.getDate(5));
            memoryModel.setPrivacy(rs.getString(6));
            memoryModel.setMainPicture(rs.getString(7));

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select t.tag_name from tag t join tag_memory tm on tm.tag_id=t.tag_id  where memoryid = " + memoryModel.getMemoryId());
            tags = new LinkedList<>();
            while (rs2.next()) {

                tags.add(rs2.getString(1));
            }
            rs2.close();
            memoryModel.setTags(tags);
            memories.add(memoryModel);
        }
        rs.close();
        return memories;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("delete from memory where memoryid = " + id)) {
            pstmt.executeUpdate();
            Database.commit();
        }
    }
}

