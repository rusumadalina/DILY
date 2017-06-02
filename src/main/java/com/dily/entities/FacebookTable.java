package com.dily.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rusum on 02.06.2017.
 */
@Entity
@Table(name = "facebook_table")
public class FacebookTable {
    int userId;
    String facebookId;

    public FacebookTable(int userId, String facebookId) {
        this.userId = userId;
        this.facebookId = facebookId;
    }

    public FacebookTable() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
