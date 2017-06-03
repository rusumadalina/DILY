package com.dily.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rusum on 03.06.2017.
 */
public class UserRegisterFacebookModel {

    private String id;
    private String name;
    private String email;
    private String city;
    private String country;
    private String birth;
    private String gender;
    private String profilePicture;

    public UserRegisterFacebookModel(String id, String name, String email, String city, String country, String birth, String gender, String profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.country = country;
        this.birth = birth;
        this.gender = gender;
        this.profilePicture = profilePicture;
    }

    public UserRegisterFacebookModel() {
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @XmlElement
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
