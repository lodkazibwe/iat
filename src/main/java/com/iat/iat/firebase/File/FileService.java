package com.iat.iat.firebase.File;

import com.google.firebase.cloud.StorageClient;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.model.User;
import com.iat.iat.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {
    @Autowired MyUserDetailsService myUserDetailsService;
    @Autowired UserService userService;
    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    public URL uploadImage(MultipartFile myFile) throws IOException {
        logger.info("getting user....");
        User user=myUserDetailsService.currentUser();
        String userName=user.getContact();
        logger.info("converting image....");
        InputStream file =  new BufferedInputStream(myFile.getInputStream());
        logger.info("getting image name....");
        String fileName=generateFileName(myFile);
        String name="iatProfile/"+userName+"/"+ fileName;
        logger.info("uploading image....");
        StorageClient.getInstance().bucket()
                .create(name, file);
        logger.info("updating profile....");
        userService.updateUserImage(name, user.getId());
        return signedUrl();


    }

    private String generateFileName(MultipartFile multiPart) {

        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename())
                .replace(" ", "_");
    }

    public URL signedUrl() {
        logger.info("getting user....");
        User user=myUserDetailsService.currentUser();
       logger.info("getting image....");
        return StorageClient.getInstance().bucket().get(user.getImage()).signUrl(14, TimeUnit.DAYS);

    }


}
