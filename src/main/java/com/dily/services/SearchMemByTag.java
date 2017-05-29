package com.dily.services;

import com.dily.Database;
import com.dily.entities.Memory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rusum on 29.05.2017.
 */
public class SearchMemByTag implements ISearchMemByTag {
    @Override
    public List<Memory> findByTag(String tag) throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from tag_and_memory where tag_name = '" + tag + "'");
        Memory memory;
        List<Memory> memories =  new LinkedList<>();
        while (rs.next()) {
            memory = new Memory();
            memory.setMemoryId(rs.getInt(2));
            memory.setTitle(rs.getString(3));
            memory.setDescription(rs.getString(4));
            memory.setMemoryLocation(rs.getString(5));
            memory.setDate(rs.getDate(6));
            memory.setPrivacy(rs.getString(7));
            memory.setMainPicture(rs.getString(8));
            memories.add(memory);
        }
        rs.close();

        return memories;
    }
    }

