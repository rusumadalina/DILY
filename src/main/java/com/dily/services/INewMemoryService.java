package com.dily.services;

import com.dily.models.AddMemoryModel;

import java.sql.SQLException;

/**
 * Created by rusum on 01.06.2017.
 */
public interface INewMemoryService {
    public int addMemory ( AddMemoryModel addMemoryModel) throws SQLException;
    public void addInTimeline (int memId, int userId) throws SQLException;
    public void tagFriend (int memId, int userId) throws SQLException;
    public int existTag (String tag) throws SQLException;
    public void addTag (int memId, int tagId) throws SQLException;
    public int addNewTag (String tag) throws SQLException;
    public void addMedia (String path, int memId) throws SQLException;
    public void setFlagFacebookMemory (int id) throws SQLException;
}
