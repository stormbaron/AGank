package com.example.stormbaron.agank.model.entity;

import java.util.List;

/**
 * Created by stormbaron on 17-6-26.ng
 * 干货信息
 */

public class GankNewsEtity {

    /**
     * _id : 5941f5f3421aa92c7be61c16
     * createdAt : 2017-06-15T10:50:27.317Z
     * desc : 仿Nice首页图片列表9图样式，并实现拖拽效果
     * images : ["http://img.gank.io/4f54c011-e293-436a-ada1-dc03669ffb10"]
     * publishedAt : 2017-06-15T13:55:57.947Z
     * source : web
     * type : Android
     * url : http://www.jianshu.com/p/0ea96b952170
     * used : true
     * who : 兔子吃过窝边草
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
