package com.dily.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rusum on 02.06.2017.
 */
@Entity
@Table(name = "documents")
public class Documents {

    int documentId;
    int userId;
    String path;

    public Documents(int documentId, int userId, String path) {
        this.documentId = documentId;
        this.userId = userId;
        this.path = path;
    }

    public Documents() {
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
