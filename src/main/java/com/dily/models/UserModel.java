package com.dily.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

/**
 * Created by Andra on 5/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class UserModel {
    public String username;
    public String password;

    public UserModel() {
    }

    //    public static UserModel valueOf(String json){
//
//        try {
//            UserModel obj = new ObjectMapper().readValue(json, UserModel.class);
//            return obj;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    return null;
//    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

