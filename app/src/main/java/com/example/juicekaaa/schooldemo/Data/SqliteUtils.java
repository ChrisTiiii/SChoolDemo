package com.example.juicekaaa.schooldemo.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.juicekaaa.schooldemo.EquipModel;
import com.example.juicekaaa.schooldemo.ResourceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juicekaaa on 17/3/19.
 */

public class SqliteUtils {


    private final SQLiteDatabase db;

    public SqliteUtils(Context context) {
        MyDBHelper helper = new MyDBHelper(context);
        db = helper.getWritableDatabase();
    }


    public void insert() {
        String sql = "insert into danwei values (222,'AB8190D5-D11E-4941-ACC4-42F30510B408',112.1,121.1,'sadha','asdsa',1)";
        String sql1 = "insert into ibeacon values (21,222,13,12211,24,101,113,121,'A楼:文化创意学院','asd')";
        String sql2 = "insert into map values (13,222,1,1,122,122,'ads','adasd')";

        String sql3 = "insert into location_resource values (3,222,21,1,'/danwei/pic/A.jpg',1)";
        String sql4 = "insert into location_resource values (4,222,21,0,'这是A楼报告厅',1)";
        String sql5 = "insert into location_resource values (5,222,21,2,'/danwei/mp3/A.mp3',1)";
        String sql6 = "insert into location_resource values (6,222,21,3,'/danwei/video/A.mp4',1)";


        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);

        String sql7 = "insert into danwei values (111,'AB8190D5-D11E-4941-ACC4-42F30510B408',222.1,444.1,'sadha','asdsa',1)";
        String sql8 = "insert into ibeacon values (22,111,12,2323232,21,101,222,444,'J楼:物联网与软件技术学院','asd')";
        String sql9 = "insert into map values (12,111,1,1,122,122,'aasdsd','adasd')";

        String sql10 = "insert into location_resource values (7,111,22,1,'/danwei/pic/B.jpg',1)";
        String sql11 = "insert into location_resource values (8,111,22,0,'这是J楼',1)";
        String sql12 = "insert into location_resource values (9,111,22,3,'/danwei/video/B.mp4',1)";
        String sql13 = "insert into location_resource values (10,111,22,2,'/danwei/mp3/B.mp3',1)";

        db.execSQL(sql7);
        db.execSQL(sql8);
        db.execSQL(sql9);
        db.execSQL(sql10);
        db.execSQL(sql11);
        db.execSQL(sql12);
        db.execSQL(sql13);
    }

    public void Search(List<EquipModel> equipLists) {
//        String sql = "select danwei.UUID,ibeacon.ibeaconID,ibeacon.major,ibeacon.minor,ibeacon.mapX,ibeacon.mapY,ibeacon.title,ibeacon.detail,location_resource.resID,location_resource.ibeaconID,location_resource.resource " +
//                "from danwei,ibeacon,location_resource where danwei.danweiID=ibeacon.danweiID and danwei.danweiID=location_resource.danweiID";

//        String sql_danwei_map = "select danwei.UUID,map.maptype,map.mapID from danwei,map where danwei.danweiID=map.danweiID";
//
//        String sql_map_ibeacon = "select ibeacon.ibeaconID,ibeacon.major,ibeacon,minor,ibeacon.mapX,ibeacon.mapY,ibeacon.title,ibeacon.detail from ibeacon,map where ibeacon.mapID=map.mapID";
//
//        String sql_ibeacon_location_resource = "select location_resource.resourcetype,location_resource.resource from location_resource where location_resource.ibeaconID=ibeacon.ibeaconID";
        String sql = "select danwei.UUID,map.maptype,map.mapID,ibeacon.ibeaconID,ibeacon.major,ibeacon.minor,ibeacon.mapX,ibeacon.mapY," +
                "ibeacon.title,ibeacon.detail from danwei,ibeacon,map where danwei.danweiID=map.danweiID and ibeacon.mapID=map.mapID";

        Cursor cursor = db.rawQuery(sql, null);
        int index = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

//            index = cursor.getColumnIndex("danweiID");
//            String danweiID = cursor.getString(index);

            index = cursor.getColumnIndex("UUID");
            String UUID = cursor.getString(index);

            index = cursor.getColumnIndex("maptype");
            String maptype = cursor.getString(index);

            index = cursor.getColumnIndex("mapID");
            String mapID = cursor.getString(index);


            index = cursor.getColumnIndex("ibeaconID");
            String ibeaconID = cursor.getString(index);


            index = cursor.getColumnIndex("major");
            String major = cursor.getString(index);

            index = cursor.getColumnIndex("minor");
            String minor = cursor.getString(index);

            index = cursor.getColumnIndex("mapX");
            String mapX = cursor.getString(index);

            index = cursor.getColumnIndex("mapY");
            String mapY = cursor.getString(index);

            index = cursor.getColumnIndex("title");
            String title = cursor.getString(index);

            index = cursor.getColumnIndex("detail");
            String detail = cursor.getString(index);


            EquipModel equipModel = new EquipModel();

//            equipModel.setDanweiID(Integer.valueOf(danweiID));
            equipModel.setProximityUuid(UUID);
            equipModel.setMaptype(Integer.valueOf(maptype));
            equipModel.setMapID(Integer.valueOf(mapID));
            equipModel.setIbeaconID(Integer.valueOf(ibeaconID));
            equipModel.setMajor(Integer.valueOf(major));
            equipModel.setMinor(Integer.valueOf(minor));
            equipModel.setMapX(Integer.valueOf(mapX));
            equipModel.setMapY(Integer.valueOf(mapY));
            equipModel.setTitle(title);
            equipModel.setDetail(detail);
            equipModel.setIdent(major + "-" + minor);
            equipModel.setM_resource(search_resource(Integer.valueOf(ibeaconID), ++index));

            equipLists.add(equipModel);


        }


    }


    public List<ResourceModel> search_resource(int IbeaconID, int index) {
        String sql_resource = "select resourcetype,resource from location_resource where ibeaconID=" + IbeaconID;
        Cursor cursor = db.rawQuery(sql_resource, null);
        List<ResourceModel> resourceModelList = new ArrayList<ResourceModel>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            index = cursor.getColumnIndex("resourcetype");
            String resourcetype = cursor.getString(index);

            index = cursor.getColumnIndex("resource");
            String resourse = cursor.getString(index);

            ResourceModel resourceModel = new ResourceModel();
            resourceModel.setResourcetype(Integer.valueOf(resourcetype));
            resourceModel.setResource(resourse);
            resourceModelList.add(resourceModel);
        }
        return resourceModelList;

//        Cursor cursor = db.rawQuery(sql, null);
//        int index = 0;
//        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            index = cursor.getColumnIndex("danweiID");
//            String danweiID = cursor.getString(index);

//            index = cursor.getColumnIndex("UUID");
//            String UUID = cursor.getString(index);
//
//            index = cursor.getColumnIndex("major");
//            String major = cursor.getString(index);
//
//            index = cursor.getColumnIndex("minor");
//            String minor = cursor.getString(index);
//
//            index = cursor.getColumnIndex("title");
//            String title = cursor.getString(index);
//
//            index = cursor.getColumnIndex("detail");
//            String detail = cursor.getString(index);
//
//            index = cursor.getColumnIndex("mapX");
//            String mapX = cursor.getString(index);
//
//            index = cursor.getColumnIndex("mapY");
//            String mapY = cursor.getString(index);
//
////            index = cursor.getColumnIndex("maptype");
////            String maptype = cursor.getString(index);
//
//            index = cursor.getColumnIndex("resID");
//            String resID = cursor.getString(index);
//
//            index = cursor.getColumnIndex("ibeaconID");
//            String ibeaconID = cursor.getString(index);
//
//            index = cursor.getColumnIndex("resource");
//            String resource = cursor.getString(index);
//
//
//            EquipModel equipModel = new EquipModel();
////            equipModel.setDanweiID(Integer.valueOf(danweiID));
//            equipModel.setProximityUuid(UUID);
//            equipModel.setMajor(Integer.valueOf(major));
//            equipModel.setMinor(Integer.valueOf(minor));
//            equipModel.setIdent(major+"-"+minor);
//            equipModel.setResname(title);
//            equipModel.setDetail(detail);
//            equipModel.setMapX(Integer.valueOf(mapX));
//            equipModel.setMapY(Integer.valueOf(mapY));
////            equipModel.setMaptype(Integer.valueOf(maptype));
//            equipModel.setResID(Integer.valueOf(resID));
//            equipModel.setIbeaconID(Integer.valueOf(ibeaconID));
//            equipModel.setResource(resource);
//            equipLists.add(equipModel);
//
//
//
//        }

    }

    public List<String> searchPathfromDB() {
        String sql = "select resource from location_resource where resourcetype=1||resourcetype=2||resourcetype==3";
        Cursor cursor = db.rawQuery(sql, null);
        List<String> list = new ArrayList<String>();
        int index = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            index = cursor.getColumnIndex("resource");
            String resource = cursor.getString(index);
            list.add(resource);

        }
        return list;

    }

}
