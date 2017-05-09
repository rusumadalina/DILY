package com.dily.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Andra on 5/1/2017.
 */
@Entity
@Table(name = "media")
public class Media {
    @Id
    private int mediaId;
    private int memoryId;
    private String mediaType;
    private String mediaPath;

    public Media(int mediaId, int memoryId, String mediaType, String mediaPath) {
        this.mediaId = mediaId;
        this.memoryId = memoryId;
        this.mediaType = mediaType;
        this.mediaPath = mediaPath;
    }
    public Media(){

    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
