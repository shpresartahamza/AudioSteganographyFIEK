package com.shpresarta.audiosteganography.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shpresarta.audiosteganography.model.FileData1;
import com.shpresarta.audiosteganography.utils.FileHelper1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CompareViewModel1 extends ViewModel {
    private MutableLiveData<FileData1> fileData1 = new MutableLiveData<>();


    public void setFileData1(InputStream inputStream, String filePath) {
        fileData1.setValue(FileHelper1.getFileData1(inputStream, filePath));
    }

    public LiveData<FileData1> getFileData1() {
        return fileData1;
    }

    public byte[] getFileSize(byte[] initBytes) {
        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
            for (byte current : initBytes) {
                byteArray.write(current);
            }
            byteArray.flush();
            return byteArray.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
