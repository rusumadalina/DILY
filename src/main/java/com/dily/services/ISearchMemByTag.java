package com.dily.services;

import com.dily.entities.Memory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 29.05.2017.
 */
public interface ISearchMemByTag {

    public List<Memory> findByTag (String tag) throws SQLException;
}
