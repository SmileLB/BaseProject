package com.example.administrator.baseproject.request;

/**
 * Created by LiBing
 * on 2017/11/22 0022
 * describe:
 */

public class Requstdate {
    private String chars;
    private String key;

    public Requstdate(String chars, String key) {
        this.chars = chars;
        this.key = key;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
