package com.dily.models;

import com.dily.entities.Media;
import com.dily.entities.Tag;
import com.dily.entities.User;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;
import java.util.List;

/**
 * Created by rusum on 31.05.2017.
 */
public class LargeMemory {

    private int memoryId;
    private String title;
    private String description;
    private String memoryLocation;
    private java.sql.Date date;
    private String privacy;
    private String mainPicture;
    private List<Tag> tags;
    private List<Media> media;
    private List<User> taggedFriends;

    public LargeMemory() {
    }

    public LargeMemory(int memoryId, String title, String description, String memoryLocation, Date date, String privacy, String mainPicture, List<Tag> tags, List<Media> media, List<User> taggedFriends) {
        this.memoryId = memoryId;
        this.title = title;
        this.description = description;
        this.memoryLocation = memoryLocation;
        this.date = date;
        this.privacy = privacy;
        this.mainPicture = mainPicture;
        this.tags = tags;
        this.media = media;
        this.taggedFriends = taggedFriends;
    }

    @XmlElement
    public int getMemoryId() {
        return memoryId;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public String getMemoryLocation() {
        return memoryLocation;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    @XmlElement
    public String getPrivacy() {
        return privacy;
    }

    @XmlElement
    public String getMainPicture() {
        return mainPicture;
    }

    @XmlElement
    public List<Tag> getTags() {
        return tags;
    }

    @XmlElement
    public List<Media> getMedia() {
        return media;
    }

    @XmlElement
    public List<User> getTaggedFriends() {
        return taggedFriends;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMemoryLocation(String memoryLocation) {
        this.memoryLocation = memoryLocation;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public void setTaggedFriends(List<User> taggedFriends) {
        this.taggedFriends = taggedFriends;
    }
}
