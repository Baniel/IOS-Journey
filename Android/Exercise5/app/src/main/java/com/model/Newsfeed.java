package com.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yandongzhang on 16/4/16.
 */
public class Newsfeed{

    private String title;
    private String imgUrl;
    private String link;


    public Newsfeed(String title, String imgUrl, String link) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.link = link;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
