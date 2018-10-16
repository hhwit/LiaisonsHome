package com.hhwit.liaisonshome;

import java.util.Date;

/**
 * Created by Hongwen Huang on 2018/10/16
 * Email: hhwit@126.com
 */

public class ChatBean {
    public static final int TYPE_LEFT = 1;
    public static final int TYPE_MIDDLE = 2;
    public static final int TYPE_RIGHT = 3;

    private String words;
    private int type;

    ChatBean(String words) {
        this.words = words;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
