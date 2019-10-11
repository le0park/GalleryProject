package com.example.galleryproject;

public class Photo {
    String filename;
    String dateTime;
    String latitude;
    String longitude;

    // TODO make Constructor, 필요한 것들 변수 선언및 getset

    public String getFilename() {
        return filename;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
