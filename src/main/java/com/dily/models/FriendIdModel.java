package com.dily.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rusum on 01.06.2017.
 */
public class FriendIdModel {
    int friendId;

    public FriendIdModel() {
    }

    public FriendIdModel(int friendId) {
        this.friendId = friendId;
    }

    @XmlElement
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

}
