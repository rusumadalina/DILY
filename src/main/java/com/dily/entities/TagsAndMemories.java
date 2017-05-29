package com.dily.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

/**
 * Created by rusum on 29.05.2017.
 */
@Entity
@Table(name = "tag_and_memory")
public class TagsAndMemories {

    private String tagName;
    private String memoryId;
    private String title;
    private String description;
    private String memoryLocation;
    private java.sql.Date date;
    private String privacy;
    private String mainPicture;

    public TagsAndMemories() {
    }


    public TagsAndMemories(String tagName, String memoryId, String title, String description, String memoryLocation, Date date, String privacy, String mainPicture) {
        this.tagName = tagName;
        this.memoryId = memoryId;
        this.title = title;
        this.description = description;
        this.memoryLocation = memoryLocation;
        this.date = date;
        this.privacy = privacy;
        this.mainPicture = mainPicture;
    }

    @XmlElement
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getMemoryLocation() {
        return memoryLocation;
    }

    public void setMemoryLocation(String memoryLocation) {
        this.memoryLocation = memoryLocation;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    @XmlElement
    public String getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(String memoryId) {
        this.memoryId = memoryId;
    }

    @XmlElement
    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
