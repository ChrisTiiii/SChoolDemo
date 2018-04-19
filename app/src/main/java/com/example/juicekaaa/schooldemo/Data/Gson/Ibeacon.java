package com.example.juicekaaa.schooldemo.Data.Gson;

/**
 * Created by Juicekaaa on 17/3/17.
 */

public class Ibeacon {
    private int ibeaconID;
    private int danweiID;
    private int major;
    private int minor;
    private int mapX;
    private int mapY;
    private String title;
    private String detai;

    public int getIbeaconID() {
        return ibeaconID;
    }

    public void setIbeaconID(int ibeaconID) {
        this.ibeaconID = ibeaconID;
    }

    public int getDanweiID() {
        return danweiID;
    }

    public void setDanweiID(int danweiID) {
        this.danweiID = danweiID;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetai() {
        return detai;
    }

    public void setDetai(String detai) {
        this.detai = detai;
    }
}
