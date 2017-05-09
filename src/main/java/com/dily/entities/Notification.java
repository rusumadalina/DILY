package com.dily.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by rusum on 01.05.2017.
 */
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    private int notificationId;
    private int userId;
    private int fromUser;
    private java.sql.Date dateN;
    private String notificationText;

    public Notification() {
    }

    public Notification(int notificationId, int userId, int fromUser, Date dateN, String notificationText) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.fromUser = fromUser;
        this.dateN = dateN;
        this.notificationText = notificationText;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public Date getDateN() {
        return dateN;
    }

    public void setDateN(Date dateN) {
        this.dateN = dateN;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
