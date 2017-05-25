package com.dily.services;

import com.dily.Database;
import com.dily.entities.Memory;
import com.dily.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        Memory memory = new Memory();
        List<Memory> memories =  new LinkedList<>();
        List<Integer> ids = new LinkedList<>();
        System.out.println(id);
        /*
        while (rs.next()) {
            ids.add(rs.getInt(1));

        }
        for( int i=0; i<ids.size(); i++){
            System.out.println(ids.get(i));
        }
        */
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
        /*
        for (int i=0; i<memories.size();i++){
            System.out.println(memories.get(i).getMemoryId());
            System.out.println(memories.get(i).getTitle());
        }*/
        return memories;
    }
}

