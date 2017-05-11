package com.dily.services;

import com.dily.models.UserModel;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by rusum on 09.05.2017.
 */
@Service
public interface IAuthenticationService {

    public UserModel findByUsernameAndPassword (String username, String password) throws SQLException;
}
