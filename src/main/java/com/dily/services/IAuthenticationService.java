package com.dily.services;

import com.dily.entities.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by rusum on 09.05.2017.
 */
@Service
public interface IAuthenticationService {

    public User findByUsernameAndPassword (String username, String password) throws SQLException;
    public User findByUserId (int userId) throws SQLException;
}
