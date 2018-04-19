package com.example.juicekaaa.schooldemo.Data.Gson;

/**
 * Created by Juicekaaa on 17/3/17.
 */

public class DanWei {
    private int danweiID;
    private String UUID;
    private double gpsX;
    private double gpsY;
    private String title;

    public DanWei(int danweiID, String UUID, double gpsX, double gpsY, String title, String detai) {
        this.danweiID = danweiID;
        this.UUID = UUID;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.title = title;
        this.detai = detai;
    }

    private String detai;

    public int getDanweiID() {
        return danweiID;
    }

    public void setDanweiID(int danweiID) {
        this.danweiID = danweiID;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public double getGpsX() {
        return gpsX;
    }

    public void setGpsX(double gpsX) {
        this.gpsX = gpsX;
    }

    public double getGpsY() {
        return gpsY;
    }

    public void setGpsY(double gpsY) {
        this.gpsY = gpsY;
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
