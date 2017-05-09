package com.dily.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by rusum on 01.05.2017.
 */
@Entity
@Table(name = "comment_table")
public class Comment {
    @Id
    private int commentId;
    private int userId;
    private int fromUser;
    private int memoryId;
    private String commentText;
    private java.sql.Date commentDate;

    public Comment() {
    }

    public Comment(int commentId, int userId, int fromUser, int memoryId, String commentText, Date commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.fromUser = fromUser;
        this.memoryId = memoryId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
