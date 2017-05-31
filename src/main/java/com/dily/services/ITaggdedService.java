package com.dily.services;

import com.dily.models.MemoryModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 31.05.2017.
 */
public interface ITaggdedService {

    public List<MemoryModel> taggedInMemories (int id) throws SQLException;
}
