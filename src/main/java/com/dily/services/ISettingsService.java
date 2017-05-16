package com.dily.services;

import com.dily.entities.User;

import java.sql.SQLException;

/**
 * Created by rusum on 16.05.2017.
 */
public interface ISettingsService {
    public void update (User user) throws SQLException;
}
