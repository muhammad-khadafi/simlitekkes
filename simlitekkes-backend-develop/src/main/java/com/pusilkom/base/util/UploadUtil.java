package com.pusilkom.base.util;

import com.github.slugify.Slugify;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.model.mapper.FileMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by muhammad.khadafi on 17/10/2019.
 */
@Component
public class UploadUtil {

    @Autowired
    FileMapper fileMapper;

    public Map<String, String> uploadFile(String dirUploadFile, MultipartFile... listMultipartFile) throws IOException {
        Map<String, String> response = new HashMap<String, String>();
        int i = 0;
        for (MultipartFile multipartFile : listMultipartFile) {
            if (multipartFile == null) continue;

            String newFileName = this.generateNewFileName(multipartFile.getOriginalFilename());

            File fileDest = new File(dirUploadFile, newFileName);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), fileDest);
            response.put(multipartFile.getName() + i, newFileName);
            i++;

            com.pusilkom.base.model.File fileDoc = new com.pusilkom.base.model.File();
            fileDoc.setFileName(newFileName);
            fileDoc.setFilePath(fileDest.getPath());
            fileMapper.insert(fileDoc);
        }

        return response;
    }

    public FileUploadHelper uploadFileCommonService(String dirUploadFile, MultipartFile multipartFile) throws IOException {
        if (multipartFile == null)
            return null;

        String newFileName = this.generateNewFileName(multipartFile.getOriginalFilename());

        File fileDest = new File(dirUploadFile, newFileName);
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), fileDest);

        FileUploadHelper fileUploadHelper = new FileUploadHelper(newFileName, fileDest.getPath());

        return fileUploadHelper;
    }

    public FileUploadHelper uploadFileCommonServiceUpdate(String dirUploadFile, MultipartFile multipartFile, String nameWithoutTimeStamp) throws IOException {
        if (multipartFile == null)
            return null;

        String newFileName = this.generateNewFileName(nameWithoutTimeStamp);

        File fileDest = new File(dirUploadFile, newFileName);
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), fileDest);

        FileUploadHelper fileUploadHelper = new FileUploadHelper(newFileName, fileDest.getPath());

        return fileUploadHelper;
    }

    public String generateNewFileName(String originalFileName) {
        String fileName = FilenameUtils.removeExtension(originalFileName);
        String extension = FilenameUtils.getExtension(originalFileName);


        Slugify slugify = new Slugify();
//        String newFileName = new DateTime().toString("yyyyMMddHHmmss") + "-" + randomShortUUIDGenerator() + "-" + slugify.slugify(fileName) + "." + extension;

        if (fileName.length() > 30) {
            fileName = fileName.substring(0,30);
        }

        String newFileName = fileName + "-"+ new DateTime().toString("yyyyMMddHHmmss") + "." + extension;

        return newFileName;

    }

    public String randomShortUUIDGenerator() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

}
