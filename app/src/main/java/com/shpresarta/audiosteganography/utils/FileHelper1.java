package com.shpresarta.audiosteganography.utils;

import android.net.Uri;

import androidx.fragment.app.FragmentActivity;

import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.shpresarta.audiosteganography.model.FileData1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper1 implements PickiTCallbacks {
    private static FileData1 fileData1;
    private PickiT pick;
    private String filePath;

    public FileHelper1(FragmentActivity activity) {
        pick = new PickiT(activity, this, activity);
    }

    public static FileData1 getFileData1(InputStream inputStream, String filePath) {
        fileData1 = new FileData1(filePath);
        fileData1.setFileBytes1(readByteFile1(inputStream));
        setFileInfo1();
        System.out.println(fileData1.getFileName1());
        return fileData1;
    }

    public static byte[] readByteFile1(InputStream inputStream) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1000000];
            int bytesRead;
            if (inputStream != null) {
                while ((bytesRead = inputStream.read(bytes)) != -1) {
                    bos.write(bytes, 0, bytesRead);
                }
            }
            return bos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    static void setFileInfo1() {
        String ext = "";
        String name = "";
        boolean check = false;
        char tmp;
        if (fileData1.getFilePath1() != null) {
            for (int i = fileData1.getFilePath1().length(); i > 0; i--) {
                tmp = fileData1.getFilePath1().charAt(i - 1);
                if (tmp == '/') i = 0;
                else {
                    if (tmp == '.') check = true;
                    else {
                        if (!check) ext = String.format("%s%s", tmp, ext);
                        else name = String.format("%s%s", tmp, name);
                    }
                }
            }
        }
        fileData1.setFileExt1(ext);
        fileData1.setFileName1(name);
    }

    public void setPick1(Uri uri, int apiLevel) {
        pick.getPath(uri, apiLevel);
    }

    public String getFilePath1() {
        return filePath;
    }

    private void setFilePath1(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void PickiTonUriReturned() {

    }

    @Override
    public void PickiTonStartListener() {

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {

    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        setFilePath1(path);
    }
}
