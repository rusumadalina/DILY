package com.dily.services;

import com.dily.models.UserModelRegister;
import com.dily.models.UserRegisterFacebookModel;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by rusum on 11.05.2017.
 */
public interface IRegistrationService {
    public boolean findByEmail (String email) throws SQLException;
    public boolean findByUsername (String email) throws SQLException;
    public int findIdByEmail (String email) throws SQLException;
    public int addNewUser(UserModelRegister usermodel) throws SQLException;
    public void addInFacebookTable (int userId, String facebookId) throws SQLException;
    public int addNewFacebookUser (UserRegisterFacebookModel user) throws SQLException, ParseException;
    public int existFacebookUser(String facebookId) throws SQLException;
    public int memoriesGenerated (int id) throws SQLException;
}
