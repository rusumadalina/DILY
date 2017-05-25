package com.dily.services;

import com.dily.entities.Memory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 25.05.2017.
 */
public interface IMemoryService {
    List<Memory> findMemoriesInTimeline (int Id) throws SQLException;
}
