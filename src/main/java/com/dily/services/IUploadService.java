package com.dily.services;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by rusum on 18.05.2017.
 */
public interface IUploadService {
    public void upload ( MultipartHttpServletRequest request, String folder) throws Exception;
}
