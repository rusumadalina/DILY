package com.dily.services;

import com.dily.entities.Memory;
import com.dily.models.LargeMemory;
import com.dily.models.MemoryModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 25.05.2017.
 */
public interface IMemoryService {
    List<MemoryModel> findMemoriesInTimeline (int Id) throws SQLException;
    void delete (int id ) throws SQLException;
    List<LargeMemory> viewMemoryDetails (int id) throws SQLException;
}
