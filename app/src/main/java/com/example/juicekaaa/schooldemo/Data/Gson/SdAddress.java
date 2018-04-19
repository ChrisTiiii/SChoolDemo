package com.example.juicekaaa.schooldemo.Data.Gson;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Juicekaaa on 17/3/21.
 */

public class SdAddress {


    public void sdsave(String filename, String content) throws Exception {
        //判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/danwei/", filename);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(content.getBytes());//向文件中写入数据，将字符串转换成字节
            fileOutputStream.flush();//将所有剩余的数据写入文件
            fileOutputStream.close();
        }
    }

    public String sdread(String filename) throws Exception {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileInputStream fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/danwei/" + filename);
            byte[] input = new byte[fileInputStream.available()];//返回是实文件的大小
            while (fileInputStream.read(input) != -1) {
            }
            fileInputStream.close();
            return new String(input);


        } else
            return "";


    }

//    public void getSDPath() {
//        File sdDir = null;
//        File sdDir1 = null;
//        File sdDir2 = null;
//        boolean sdCardExist = Environment.getExternalStorageState()
//                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
//        if (sdCardExist) {
//            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
//            sdDir1 = Environment.getDataDirectory();
//            sdDir2 = Environment.getRootDirectory();
//        }
//        System.out.println("getExternalStorageDirectory(): " + sdDir.toString());
//        System.out.println("getDataDirectory(): " + sdDir1.toString());
//        System.out.println("getRootDirectory(): " + sdDir2.toString());
//    }
//
//
////判断一个路径下的文件（文件夹）是否存在
//
//    public class IsExist {
//        public  void main(String[] args) {
//            isExist("e://12");
//        }
//
//        /**
//         * @param path 文件夹路径
//         */
//        public  void isExist(String path) {
//            File file = new File(path);
////判断文件夹是否存在,如果不存在则创建文件夹
//            if (!file.exists()) {
//                file.mkdir();
//            }
//        }
//    }


}
