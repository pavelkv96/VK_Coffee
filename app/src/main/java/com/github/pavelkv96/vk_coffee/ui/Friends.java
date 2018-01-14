package com.github.pavelkv96.vk_coffee.ui;

public class Friends {
    private String mFirstName;
    private String mLastName;

    public Friends(String pFirstName, String pLastName) {
        this.mFirstName = pFirstName;
        this.mLastName = pLastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String pFirstName) {
        this.mFirstName = pFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String pLastName) {
        this.mLastName = pLastName;
    }
}
