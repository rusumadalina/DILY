package com.dily.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rusum on 01.06.2017.
 */
public class TagModel {

    String tagName;

    public TagModel(String tagName) {
        this.tagName = tagName;
    }

    public TagModel() {
    }

    @XmlElement
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }



}
