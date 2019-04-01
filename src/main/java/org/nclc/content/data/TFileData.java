package org.nclc.content.data;

import org.springframework.web.multipart.MultipartFile;


public class TFileData {

    private String fileName;
    private String description;
    private MultipartFile file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
