package com.dily.services;

import com.dily.models.FriendModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rusum on 26.05.2017.
 */
public interface IFriendService {
    public List<FriendModel> getAllFriends (int id) throws SQLException;
}