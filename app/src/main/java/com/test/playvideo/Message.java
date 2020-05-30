package com.test.playvideo;

import java.io.Serializable;

public class Message implements Serializable {
    private String id;//ID
    private String feedurl;//url 地址
    private String nickname;//用户名
    private String description;// 视频描述(标题)
    private int likecount;// 喜爱值(点赞数) |
    private String avactar;//

    public Message(String feedurl, String avactar) {
        this.feedurl = feedurl;
        this.avactar = avactar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedurl() {
        return feedurl;
    }

    public void setFeedurl(String feedurl) {
        this.feedurl = feedurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public String getAvactar() {
        return avactar;
    }

    public void setAvactar(String avactar) {
        this.avactar = avactar;
    }
}