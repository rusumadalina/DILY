package com.dily.services;

import com.dily.Database;
import com.dily.entities.Memory;
import com.dily.entities.User;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 25.05.2017.
 */
public class MemoryService implements IMemoryService {

    @Override
    public List<Memory> findMemoriesInTimeline(int id) throws SQLException {
        Connection con = Database.getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from memory m join timeline t on m.memoryid=t.memoryid where user_id = " + id);

        Memory memory;
        List<Memory> memories =  new LinkedList<>();
        List<Integer> ids = new LinkedList<>();
        while (rs.next()) {
            memory = new Memory();
            memory.setMemoryId(rs.getInt(1));
            memory.setTitle(rs.getString(2));
            memory.setDescription(rs.getString(3));
            memory.setMemoryLocation(rs.getString(4));
            memory.setDate(rs.getDate(5));
            memory.setPrivacy(rs.getString(6));
            memory.setMainPicture(rs.getString(7));
            memories.add(memory);
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

