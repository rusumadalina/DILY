package com.dily.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rusum on 01.06.2017.
 */
public class MediaModel {

    String picture;

    public MediaModel(String picture) {
        this.picture = picture;
    }

    public MediaModel() {
    }

    @XmlElement
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
