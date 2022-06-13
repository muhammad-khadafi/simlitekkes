package com.pusilkom.base.helper;

/**
 * Created by muhammad.khadafi on 23/03/2020.
 */
public class FileUploadHelper {

    private String fileName;
    private String filePath;

    public FileUploadHelper(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
