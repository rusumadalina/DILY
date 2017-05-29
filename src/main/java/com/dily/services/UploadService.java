package com.dily.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

/**
 * Created by rusum on 18.05.2017.
 */
public class UploadService implements IUploadService {
    @Override
    public void upload(MultipartHttpServletRequest request, String folder) throws Exception {
        Iterator<String> itrator = request.getFileNames();
        MultipartFile multiFile = request.getFile(itrator.next());
        try {

            String fileName=multiFile.getOriginalFilename();
            String path=request.getServletContext().getRealPath("/");
            path = path.replace("src\\main\\webapp","frontend\\src\\assets\\images");
            byte[] bytes = multiFile.getBytes();
            File directory=    new File(path+ folder);
            directory.mkdirs();

            File file=new File(directory.getAbsolutePath()+System.getProperty("file.separator")+fileName);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error while loading the file");
        }

    }
}
