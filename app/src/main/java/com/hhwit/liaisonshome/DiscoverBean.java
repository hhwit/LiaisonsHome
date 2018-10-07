package com.hhwit.liaisonshome;

/**
 * Created by Hongwen Huang on 2018/9/26
 * Email: hhwit@126.com
 */

public class DiscoverBean {

    private String identity;
    private String description;

    DiscoverBean(String identity, String description) {
        this.identity = identity;
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String i) {
        identity = i;
    }

}
