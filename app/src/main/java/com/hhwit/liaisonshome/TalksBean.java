package com.hhwit.liaisonshome;

import java.util.Date;

/**
 * Created by Hongwen Huang on 2018/10/11
 * Email: hhwit@126.com
 */

public class TalksBean {

    private String identity;
    private String description;
    private int type;
    private int who;
    private String content;
    private Date date;

    TalksBean() {
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
