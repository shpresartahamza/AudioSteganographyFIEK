package com.shpresarta.audiosteganography.model;

public class FileData1 {
    private String filePath1;
    private String fileExt1;
    private String fileName1;
    private byte[] fileBytes1;
    String audioDuration1;

    public FileData1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public String getFileExt1() {
        return fileExt1;
    }

    public void setFileExt1(String fileExt1) {
        this.fileExt1 = fileExt1;
    }

    public String getFileName1() {
        return fileName1;
    }


    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }
    public byte[] getFileBytes1() {
        return fileBytes1;
    }


    public void setFileBytes1(byte[] fileBytes1) {
        this.fileBytes1 = fileBytes1;
    }
    public String getaudioDuration1() {
        return audioDuration1;
    }

    public void setaudioDuration1(String audioDuration) {
        this.audioDuration1 = audioDuration;
    }
}
