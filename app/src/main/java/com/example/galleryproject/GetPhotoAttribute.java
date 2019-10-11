package com.example.galleryproject;

import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GetPhotoAttribute {
    private File file;
    private File[] listFile;
    private ArrayList<String> list;

    public ArrayList<String> getListOfFile(){
        list = new ArrayList<String>();

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            Toast.makeText(getContext(), "Error! No SDCARD Found!", Toast.LENGTH_LONG).show();
        } else {
            // Locate the image folder in your SD Card
            file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "DCIM/Camera");
            // Create a new folder if no folder named SDImageTutorial exist
            file.mkdirs();
        }

        if (file.isDirectory()) {
            listFile = file.listFiles();
            Log.e("LENGTH", String.valueOf(listFile.length));
            for (int i = 0; i < listFile.length; i++) {
                if(listFile[i].getAbsolutePath().toLowerCase().endsWith(".jpg"))
                    list.add(0,listFile[i].getAbsolutePath());
//                else
//                    Log.e("예외파일", listFile[i].getAbsolutePath());
            }
        }
        return list;
    }

    public Photo createPhoto(int position){
        Photo photo = new Photo();
        String filename = list.get(position);

        try {
            ExifInterface exif = new ExifInterface(filename);
            photo.setFilename(filename);
            photo.setDateTime(exif.getAttribute(ExifInterface.TAG_DATETIME));
            photo.setLatitude(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
            photo.setLongitude(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
            //위와같은식으로 . 하면 겁나많음
            //exif.getAttribute(ExifInterface)
        } catch (IOException e) {
            e.printStackTrace();
        }

        return photo;
    }

}
