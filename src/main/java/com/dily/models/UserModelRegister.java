package com.dily.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Created by rusum on 11.05.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class UserModelRegister {

    private String name;
    private String username;
    private String password;
    private String email;
    private java.sql.Date birth;
    private String country;
    private String city;
    private String gender;

    public UserModelRegister() {
    }

    public UserModelRegister(String name, String username, String password, String email, Date birth, String country, String city, String gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birth = birth;
        this.country = country;
        this.city = city;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @XmlElement
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
