package com.example.pandas.personal.beans;

/**
 * Created by li on 2017/7/20.
 */

public class HistoryBean {

    String image;
    String timer;
    String title;
    String data;
    String url;

    public HistoryBean(String image, String timer, String title, String data, String url) {
        this.image = image;
        this.timer = timer;
        this.title = title;
        this.data = data;
        this.url = url;
    }

    public HistoryBean() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
