package com.example.juicekaaa.schooldemo.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 16/5/19.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASENAME = "ibeacon.db";
    private static final int DATABASEVERSION = 1;
    //    private static final String TABLENAME_USER = "user";
    private static final String TABLENAME_DANWEI = "danwei";
    private static final String TABLENAME_IBEACON = "ibeacon";
    private static final String TABLENAME_MAP = "map";

    private static final String TABLENAME_IBEACONTOMAP = "ibeacontomap";
    private static final String TABLENAME_XYRESOURCE = "XYresource";
    private static final String TABLENAME_EQUIPMENT = "equipment";
    private static final String TABLENAME_LOCATION_RESOURCE="location_resource";


    public MyDBHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

//        String sql1 = "create table " + TABLENAME_USER + "(" + "user_name varchar(14) primary key," + "user_password varchar(14) not null)";
//        db.execSQL(sql1);

//create table ()
        String SQL_CREATE_EQUIPMENT = "create table " + TABLENAME_EQUIPMENT + "(_id int PRIMARY KEY,equipment_name  VARCHAR(14)  ," +
                "equipment_uuid  VARCHAR(14)  NOT NULL,equipment_major  VARCHAR(14)  NOT NULL," +
                "equipment_minor  VARCHAR(14)  NOT NULL,equipment_x   VARCHAR(14)  NOT NULL," +
                "equipment_y   VARCHAR(14)  NOT NULL)";

        db.execSQL(SQL_CREATE_EQUIPMENT);


        String SQL_CREATE_DANWEI = "CREATE TABLE " + TABLENAME_DANWEI + "(danweiID int(11) PRIMARY KEY," +
                "UUID varchar(38) NOT NULL,gpsX Double NOT NULL,gpsY Double NOT NULL," +
                "title varchar(50) NOT NULL,detail varchar(250) NOT NULL,downloaded int)";

        db.execSQL(SQL_CREATE_DANWEI);

        String SQL_CREATE_IBEACON = "CREATE TABLE " + TABLENAME_IBEACON + " (ibeaconID int(11) not null PRIMARY KEY," +
                "danweiID int(11),mapID int(11) not null,gotomapID int,major int(11) NOT NULL,minor int(11) NOT NULL,mapX int(11) not null,mapY int(11) not null," +
                "title varchar(50) NOT NULL,detail varchar(250) NOT NULL)";
        db.execSQL(SQL_CREATE_IBEACON);

        String SQL_CREATE_MAP = "CREATE TABLE " + TABLENAME_MAP + " (mapID int PRIMARY KEY,danweiID int(11),maptype int NOT NULL," +
                "mapversion int NOT NULL,width int NOT NULL,height int NOT NULL,maptitle varchar(50) NOT NULL, " +
                "maplocation varchar(250) NOT NULL)";

        db.execSQL(SQL_CREATE_MAP);

        String SQL_CREATE_LOCATION_RESOURCE="create table "+TABLENAME_LOCATION_RESOURCE+"(resID int PRIMARY KEY,danweiID int(11),ibeaconID int(11)," +
                "resourcetype int not null,resource varchar(250),downloaded int)";
        db.execSQL(SQL_CREATE_LOCATION_RESOURCE);

//        String SQL_CREATE_IBEACONTOMAP = "CREATE TABLE " + TABLENAME_IBEACONTOMAP + " (ID int(11) primary key,ibeaconID int(11) NOT NULL," +
//                "mapID int(11) NOT NULL,mapX int(11) NOT NULL,mapY int(11) NOT NULL,foreign key (ibeaconID) references ibeacon(ibeaconID)," +
//                "foreign key (mapID) references map(mapID))";
//
//        db.execSQL(SQL_CREATE_IBEACONTOMAP);
//
//
//        String SQL_CREATE_XYRESOURCE = "CREATE TABLE " + TABLENAME_XYRESOURCE + " (ID int NOT NULL primary key,danweiID int(11),ibeaconID int(11)," +
//                "resourcetype int NOT NULL,resourcetitle varchar(50) NOT NULL,resource varchar(250) NOT NULL," +
//                "foreign key (danweiID) references danwei(danweiID),foreign key (ibeaconID) references ibeacon(ibeaconID))";
//
//        db.execSQL(SQL_CREATE_XYRESOURCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql3 = "drop table if exists" + TABLENAME_USER;
//        db.execSQL(sql3);


        String SQL_DROP_EQUIPMENT = "drop table if exists" + TABLENAME_EQUIPMENT;
        db.execSQL(SQL_DROP_EQUIPMENT);


        String SQL_DROP_DANWEI = "drop table if exists" + TABLENAME_DANWEI;
        db.execSQL(SQL_DROP_DANWEI);

        String SQL_DROP_IBEACON = "drop table if exists" + TABLENAME_IBEACON;
        db.execSQL(SQL_DROP_IBEACON);

        String SQL_DROP_MAP = "drop table if exists" + TABLENAME_MAP;
        db.execSQL(SQL_DROP_MAP);

        String SQL_LOCATION_RESOURCE = "drop table if exists" + TABLENAME_LOCATION_RESOURCE;
        db.execSQL(SQL_LOCATION_RESOURCE);

//        String SQL_DROP_IBEACONTOMAP = "drop table if exists" + TABLENAME_IBEACONTOMAP;
//        db.execSQL(SQL_DROP_IBEACONTOMAP);
//
//        String SQL_DROP_XYRESOURCE = "drop table if exists" + TABLENAME_XYRESOURCE;
//        db.execSQL(SQL_DROP_XYRESOURCE);


        this.onCreate(db);

    }
}
