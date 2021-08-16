package com.shpresarta.audiosteganography.model;

public class FileData {
    private String filePath;
    private String fileExt;
    private String fileName;
    private byte[] fileBytes;
    private int fileSize;

    public FileData(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public byte[] getFileBytes() {
        return fileBytes;
    }


    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }
    public int getFileSize() {
        return fileSize;
    }

    public void setaudioDuration(int audioDuration) {
        this.fileSize = audioDuration;
    }
}
