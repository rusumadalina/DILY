package com.dily.services;

import com.dily.entities.Memory;
import com.dily.models.FriendModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 29.05.2017.
 */
public interface ISearchService {

    public List<Memory> findByTag (String tag) throws SQLException;
    public List<FriendModel> findFriends (String word, int id) throws SQLException;
    public List<FriendModel> findNewFriends (String word, int id) throws SQLException;
}
