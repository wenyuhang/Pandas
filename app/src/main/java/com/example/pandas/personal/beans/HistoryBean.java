package com.example.pandas.personal.beans;

/**
 * Created by li on 2017/7/20.
 */

public class HistoryBean {

    boolean flg;
    boolean flgCheck;
    String image;
    String timer;
    String title;
    String data;
    String url;

    public HistoryBean() {
    }

    public boolean isFlgCheck() {
        return flgCheck;
    }

    public void setFlgCheck(boolean flgCheck) {
        this.flgCheck = flgCheck;
    }

    public HistoryBean(boolean flg, String image, String timer, String title, String data, String url, boolean flgCheck) {
        this.flg = flg;
        this.image = image;
        this.timer = timer;
        this.title = title;
        this.data = data;
        this.url = url;
        this.flgCheck=flgCheck;

    }

    public boolean isFlg() {
        return flg;
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
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
