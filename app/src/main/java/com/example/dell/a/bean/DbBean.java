package com.example.dell.a.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by DELL on 2019/9/20.
 */
@Entity
public class DbBean {
    //@Id(autoincrement = true)
    //private long id;
    private String url;
    private String title;
    private String desc;
    @Generated(hash = 1215373901)
    public DbBean(String url, String title, String desc) {
        this.url = url;
        this.title = title;
        this.desc = desc;
    }
    @Generated(hash = 1953169116)
    public DbBean() {
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /*@Override
    public String toString() {
        return "DbBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Generated(hash = 1097034574)
    public DbBean(long id, String url, String title, String desc) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.desc = desc;
    }
    @Generated(hash = 1953169116)
    public DbBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }*/
}
