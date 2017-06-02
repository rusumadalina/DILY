package com.dily.models;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

/**
 * Created by rusum on 01.06.2017.
 */
public class AddMemoryModel {

    private String title;
    private String description;
    private String memoryLocation;
    private java.sql.Date date;
    private String privacy;
    private String mainPicture;

    public AddMemoryModel() {
    }

    public AddMemoryModel(String title, String description, String memoryLocation, Date date, String privacy, String mainPicture) {
        this.title = title;
        this.description = description;
        this.memoryLocation = memoryLocation;
        this.date = date;
        this.privacy = privacy;
        this.mainPicture = mainPicture;
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
    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    @XmlElement
    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }
}
