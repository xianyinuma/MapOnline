package com.sota.message;

import com.sota.entity.Tag;

/**
 * Created by wenjin on 2017/5/8.
 */
public class ImageMessage {
    private int imageID;
    private String title;
    private String description;
    private String base64Coding;
    private Tag[] tags;
    private double longitude;
    private double latitude;

    public ImageMessage() {

    }

    public ImageMessage(String title, String description, String base64Coding, Tag[] tags, double longitude, double latitude) {
        this.title = title;
        this.description = description;
        this.base64Coding = base64Coding;
        this.tags = tags;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBase64Coding() {
        return base64Coding;
    }

    public void setBase64Coding(String base64Coding) {
        this.base64Coding = base64Coding;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
