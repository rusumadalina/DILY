package com.dily.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rusum on 02.06.2017.
 */

public class DocumentModel {

    String path;

    public DocumentModel(String path) {
        this.path = path;
    }

    public DocumentModel() {
    }

    @XmlElement
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
