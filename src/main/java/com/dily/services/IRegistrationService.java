package com.dily.services;

import com.dily.models.UserModelRegister;

import java.sql.SQLException;

/**
 * Created by rusum on 11.05.2017.
 */
public interface IRegistrationService {
    public boolean findByEmail (String email) throws SQLException;
    public boolean findByUsername (String email) throws SQLException;
    public int findIdByEmail (String email) throws SQLException;
    public int addNewUser(UserModelRegister usermodel) throws SQLException;
    public void addInFacebookTable (int userId, String facebookId) throws SQLException;
}
