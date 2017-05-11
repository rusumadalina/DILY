package com.dily.repositories;

import com.dily.entities.User;
import com.dily.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.sql.SQLException;

/**
 * Created by rusum on 10.05.2017.
 */
public interface IUserRepository extends CrudRepository<User, Integer> ,JpaRepository<User,Integer> {

    public UserModel findByUsernameAndPassword (String username, String password) throws SQLException;
}
