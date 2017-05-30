package com.dily.models;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

/**
 * Created by rusum on 26.05.2017.
 */
public class FriendModel {

    public int friendId;
    public String profilePicture;
    public String name;
    public String username;
    public String city;
    public String country;
    public Date dateFriends;

    public FriendModel(int friendId, String profilePicture, String name, String username, String city, String country, Date dateFriends) {
        this.friendId = friendId;
        this.profilePicture = profilePicture;
        this.name = name;
        this.username = username;
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

    @XmlElement
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
