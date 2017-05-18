package com.dily.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rusum on 18.05.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Picture {
    MultipartFile profilePicture;

    public Picture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Picture() {
    }

    @XmlElement
    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }


}
