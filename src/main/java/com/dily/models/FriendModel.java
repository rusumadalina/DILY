package com.dily.models;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

/**
 * Created by rusum on 26.05.2017.
 */
public class FriendModel {

    public String profilePicture;
    public String name;
    public String city;
    public String country;
    public Date dateFriends;

    public FriendModel(String profilePicture, String name, String city, String country, Date dateFriends) {
        this.profilePicture = profilePicture;
        this.name = name;
        this.city = city;
        this.country = country;
        this.dateFriends = dateFriends;
    }

    public FriendModel() {
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Date getDateFriends() {
        return dateFriends;
    }

    public void setDateFriends(Date dateFriends) {
        this.dateFriends = dateFriends;
    }

    @XmlElement
    public String getProfilePicture() {
        return profilePicture;
    }


    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
