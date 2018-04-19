package com.example.juicekaaa.schooldemo.Data.Gson;

/**
 * Created by Juicekaaa on 17/3/17.
 */

public class Location_Resource {
    private int resID;
    private int danweiID;
    private int ibeaconID;
    private int resourcetype;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(int resourcetype) {
        this.resourcetype = resourcetype;
    }

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

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    private String resource;
}
