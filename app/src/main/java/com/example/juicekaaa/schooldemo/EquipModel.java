package com.example.juicekaaa.schooldemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juicekaaa on 16/12/13.
 */

public class EquipModel {

    public String proximityUuid;
    public int maptype;
    public int mapID;
    public int ibeaconID;
    public int major;
    public int minor;
    public int mapX;
    public int mapY;
    public String title;
    public String detail;


    public List<ResourceModel> m_resource;


    public int txPower;
    public int rssi;


    public int buildPhotoId=R.mipmap.dingweitu;
    public int tudingPosition[];
    public String resname;
    public int imageID;


    public String bluetoothAddress;
    ResourceModel resourceModel;

    //    public String ident;
    String ident;


    public int danweiID;

    public int resID;


    public EquipModel() {

    }


    public EquipModel(String proximityUuid, int maptype, int mapID, int ibeaconID, int major, int minor, int mapX, int mapY, String title, String detail, ResourceModel resourceModel, int txPower, int rssi) {
        this.proximityUuid = proximityUuid;
        this.maptype = maptype;
        this.mapID = mapID;
        this.ibeaconID = ibeaconID;
        this.major = major;
        this.minor = minor;

        this.mapX = mapX;
        this.mapY = mapY;
        this.title = title;
        this.detail = detail;
        this.resourceModel = resourceModel;


        this.txPower = txPower;
        this.rssi = rssi;
        ident = major + "-" + minor;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public List<ResourceModel> getM_resource() {
        return m_resource;
    }

    public void setM_resource(List<ResourceModel> m_resource) {
        this.m_resource = m_resource;
    }


    //    public EquipModel(int ibeaconID, String proximityUuid, int major, int minor, String resource, int mapX, int mapY, String title, String detail, int rssi, int txPower) {
//        this.rssi = rssi;
//        this.txPower = txPower;
//        this.ibeaconID = ibeaconID;
//        this.detail = detail;
//        this.resource = resource;
//        this.mapX = mapX;
//        this.mapY = mapY;
//        this.major = major;
//        this.proximityUuid = proximityUuid;
//        this.minor = minor;
//        this.title = title;
//        ident = major + "-" + minor;
//    }


    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public int[] getTudingPosition() {
        return tudingPosition;
    }

    public void setTudingPosition(int[] tudingPosition) {
        this.tudingPosition = tudingPosition;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }


    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public String getName() {
        return resname;
    }

    public void setName(String name) {
        this.resname = name;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getTxPower() {
        return txPower;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getProximityUuid() {
        return proximityUuid;
    }

    public void setProximityUuid(String proximityUuid) {
        this.proximityUuid = proximityUuid;
    }

    public int getBuildPhotoId() {
        return buildPhotoId;
    }

    public void setBuildPhotoId(int buildPhotoId) {
        this.buildPhotoId = buildPhotoId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getDanweiID() {
        return danweiID;
    }

    public void setDanweiID(int danweiID) {
        this.danweiID = danweiID;
    }

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMaptype() {
        return maptype;
    }

    public void setMaptype(int maptype) {
        this.maptype = maptype;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public int getIbeaconID() {
        return ibeaconID;
    }

    public void setIbeaconID(int ibeaconID) {
        this.ibeaconID = ibeaconID;
    }


    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }


    //    public EquipModel(int buildPhotoId, int[] tudingPosition, String resname, int major, int minor,
//                      String proximityUuid, int txPower, int rssi) {
//        this.buildPhotoId = buildPhotoId;
//        this.tudingPosition = tudingPosition;
//        this.resname = resname;
////        this.imageID = imageID;
//        this.minor = minor;
//        this.major = major;
//        this.proximityUuid = proximityUuid;
//        this.txPower = txPower;
    //        this.rssi = rssi;
////        ident = major + "-" + minor;
//        ident = ibeaconID;
//    }
}