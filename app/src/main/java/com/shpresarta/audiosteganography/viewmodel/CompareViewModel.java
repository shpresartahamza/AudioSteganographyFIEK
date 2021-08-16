package com.shpresarta.audiosteganography.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shpresarta.audiosteganography.model.FileData;
import com.shpresarta.audiosteganography.utils.FileHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CompareViewModel extends ViewModel {
    private MutableLiveData<FileData> fileData = new MutableLiveData<>();

    public void setFileData(InputStream inputStream, String filePath) {
        fileData.setValue(FileHelper.getFileData(inputStream, filePath));
    }

    public LiveData<FileData> getFileData() {
        return fileData;
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
