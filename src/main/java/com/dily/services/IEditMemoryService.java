package com.dily.services;

import com.dily.models.AddMemoryModel;

import java.sql.SQLException;

/**
 * Created by rusum on 02.06.2017.
 */
public interface IEditMemoryService {
    public void editMemory (int memId, AddMemoryModel addMemoryModel) throws SQLException;
    public void deleteMedia (int medId) throws SQLException;
    public void deleteTag (int tagId, int memId) throws SQLException;
    public void deleteTagged (int memId, int userId) throws SQLException;
}
