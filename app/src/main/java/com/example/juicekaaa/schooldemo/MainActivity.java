package com.example.juicekaaa.schooldemo;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.juicekaaa.schooldemo.Data.SqliteUtils;
import com.example.juicekaaa.schooldemo.UI.FragmentAdd;
import com.example.juicekaaa.schooldemo.UI.FragmentMap;
import com.example.juicekaaa.schooldemo.UI.FragmentMe;
import com.example.juicekaaa.schooldemo.UI.FragmentSheBei;

import java.util.ArrayList;
import java.util.List;

//import com.example.juicekaaa.schooldemo.Data.SysParamter;

public class MainActivity extends Activity implements View.OnClickListener {


    //布局管理器
    private android.app.FragmentManager fManager;
    public FragmentSheBei fragment_SheBei;
    public FragmentMap fragment_Map;
    private FragmentAdd fragment_Add;
    private FragmentMe fragment_Me;
    public TextView tab_menu_Map, tab_menu_SheBei, tab_menu_Add, tab_menu_Me;
    private BluetoothAdapter mBluetoothAdapter;
    private String TAG = "TAG";
    //    public Handler mHandler = null;
//    SysParamter sysParamter;
    public static int currentposition = -1;
    final static String WXSTC_INFORMATION = "AB8190D5-D11E-4941-ACC4-42F30510B408";


//    SdAddress sdAddress;


//    public static String stc;
//    public static int m;
//    private View view;
//    private List<Map<String, Object>> m_listdata;
//    private SimpleAdapter m_adapter;//适配器
//    private Button btn;

    public List<EquipModel> equipLists = new ArrayList<EquipModel>();
    public List<ResourceModel> resourceModels = new ArrayList<ResourceModel>();
    public SqliteUtils sqliteUtils;
//    double recentdistance = 0;

//    public void setmHandler(Handler mHandler) {
//        this.mHandler = mHandler;
//    }public void setmHandler(Handler mHandler) {
//        this.mHandler = mHandler;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sdAddress = new SdAddress();
//        try {
//            sdAddress.sdsave("pic", "www.dsd");
//            sdAddress.sdsave("video", "dsa");
//            sdAddress.sdsave("mp3", "dss");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        sysParamter = new SysParamter();
        sqliteUtils = new SqliteUtils(MainActivity.this);

        initshebei();


        //布局管理器
        fManager = getFragmentManager();


        //初始化组件
        initViews();

        tab_menu_SheBei.performClick();


        //获取蓝牙实例
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        //判断蓝牙可用
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBluetooth = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 1);
        }
        mBluetoothAdapter.startLeScan(mLeScanCallback);
        Log.d(TAG, "调用蓝牙");

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (fragment_SheBei != null) {
                                    fragment_SheBei.update();
                                }


                            }
                        });
//                        Message msg = mHandler.obtainMessage();
//                        switch (m) {
//                            case 11:
//                                msg.what = 11;
//                                Bundle bundle = new Bundle();
//                                bundle.putString("txt", stc);
//                                msg.setData(bundle);
//
//                                break;
//                            case 15:
//                                msg.what = 15;
//                                Bundle bundle1 = new Bundle();
//                                bundle1.putString("txt", stc);
//                                msg.setData(bundle1);
//
//                                break;
//                        }
//
//                        mHandler.sendMessageDelayed(msg, 1000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }
            }

        }).start();
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi,
                             final byte[] scanRecord) {
            int startByte = 2;
            boolean patternFound = false;
            // 寻找ibeacon
            while (startByte <= 5) {
                if (((int) scanRecord[startByte + 2] & 0xff) == 0x02 && // Identifies
                        // an
                        // iBeacon
                        ((int) scanRecord[startByte + 3] & 0xff) == 0x15) { // Identifies
                    // correct
                    // data
                    // length
                    patternFound = true;
                    break;
                }
                startByte++;
            }
            // 如果找到了的话
            if (patternFound) {
                // 转换为16进制
                byte[] uuidBytes = new byte[16];
                System.arraycopy(scanRecord, startByte + 4, uuidBytes, 0, 16);
                String hexString = bytesToHex(uuidBytes);

                // ibeacon的UUID值
                String uuid = hexString.substring(0, 8) + "-"
                        + hexString.substring(8, 12) + "-"
                        + hexString.substring(12, 16) + "-"
                        + hexString.substring(16, 20) + "-"
                        + hexString.substring(20, 32);

                // ibeacon的Major值
                int major = (scanRecord[startByte + 20] & 0xff) * 0x100
                        + (scanRecord[startByte + 21] & 0xff);

                // ibeacon的Minor值
                int minor = (scanRecord[startByte + 22] & 0xff) * 0x100
                        + (scanRecord[startByte + 23] & 0xff);

                String ibeaconName = device.getName();
                String mac = device.getAddress();
                int txPower = (scanRecord[startByte + 24]);

//
//                Log.d("BLE", bytesToHex(scanRecord));
//                Log.d("BLE", "Name：" + ibeaconName + "\nMac：" + mac
//                        + " \nUUID：" + uuid + "\nMajor：" + major + "\nMinor："
//                        + minor + "\nTxPower：" + txPower + "\nrssi：" + rssi);
//                Log.d("BLE", "distance：" + calculateAccuracy(txPower, rssi));

                //判断当前设备的具体信息
                if (uuid.equals(WXSTC_INFORMATION)) {
                    int i = isexsit(major, minor);
                    if (i != -1) {
                        equipLists.get(i).major = major;
                        equipLists.get(i).minor = minor;
                        equipLists.get(i).txPower = txPower;
                        equipLists.get(i).rssi = rssi;
                    } else {
//                        EquipModel equipList = new EquipModel(sysParamter.buildImage.get(i + 11 + ""), sysParamter.buildTuding.get(i + 11 + ""), sysParamter.majorBulid.get(major + ""),
//                                 major, minor, uuid, txPower, rssi);

                        // equipLists.add(equipList);
                    }
                    currentposition = rank();

                }
//                m = major;
//
//                if (uuid.equals("AB8190D5-D11E-4941-ACC4-42F30510B408")) {
//                    switch (m) {
//                        case 11:
//                            stc = Double.toString(calculateAccuracy(txPower, rssi));
//                            break;
//                        case 15:
//                            stc = Double.toString(calculateAccuracy(txPower, rssi));
//                            break;
//                    }
//                }

            }


        }
    };

    //得到最近的一次距离的设备位置
    public int rank() {

        int position = 0;
        double min = distance(0);
        for (int i = 0; i < equipLists.size(); i++) {
            if (distance(i) < min) {
                if (distance(i) != 0) {
                    min = distance(i);
                    position = i;
                }

            }
        }
        return position;
    }

    public double distance(int i) {
        return calculateAccuracy(equipLists.get(i).txPower, equipLists.get(i).rssi);
    }

    //major minor 是否存在于equipLists数组里
    public int isexsit(int major, int minor) {
        //存在数组里
        for (int i = 0; i < equipLists.size(); i++) {
            if ((major + "-" + minor).equals(equipLists.get(i).ident)) {
                return i;
            }
        }

        return -1;
    }

    //转化成16进制数
    static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    //计算距离的运算函数
    public static double calculateAccuracy(int txPower, double rssi) {
        if (rssi == 0) {
            return 0; // if we cannot determine accuracy, return -1.
        }

        double ratio = rssi * 1.0 / txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        } else {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
            return accuracy;
        }
    }

    //初始化组件
    private void initViews() {


        tab_menu_SheBei = (TextView) findViewById(R.id.tab_menu_SheBei);
        tab_menu_Map = (TextView) findViewById(R.id.tab_menu_Map);
        tab_menu_Add = (TextView) findViewById(R.id.tab_menu_Add);
        tab_menu_Me = (TextView) findViewById(R.id.tab_menu_Me);


        tab_menu_SheBei.setOnClickListener(this);
        tab_menu_Map.setOnClickListener(this);
        tab_menu_Add.setOnClickListener(this);
        tab_menu_Me.setOnClickListener(this);


    }

    //初始化选择
    private void setSelect() {
        tab_menu_SheBei.setSelected(false);
        tab_menu_Map.setSelected(false);
        tab_menu_Add.setSelected(false);
        tab_menu_Me.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragment_SheBei != null) {
            fragmentTransaction.hide(fragment_SheBei);
        }
        if (fragment_Map != null) {
            fragmentTransaction.hide(fragment_Map);
        }
        if (fragment_Add != null) {
            fragmentTransaction.hide(fragment_Add);
        }
        if (fragment_Me != null) {
            fragmentTransaction.hide(fragment_Me);
        }


    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);

        switch (view.getId()) {
            case R.id.tab_menu_SheBei:
                setSelect();
                tab_menu_SheBei.setSelected(true);
                if (fragment_SheBei == null) {
                    fragment_SheBei = new FragmentSheBei();
                    fragmentTransaction.add(R.id.ly_content, fragment_SheBei);
                } else
                    fragmentTransaction.show(fragment_SheBei);
                break;
            case R.id.tab_menu_Map:
                setSelect();
                tab_menu_Map.setSelected(true);
                if (fragment_Map == null) {
                    fragment_Map = new FragmentMap();
                    fragmentTransaction.add(R.id.ly_content, fragment_Map);
                } else

                    fragmentTransaction.show(fragment_Map);

                if(fragment_Map.isAdded()){
                    fragment_Map.map_photo.performClick();
                }

                break;
            case R.id.tab_menu_Me:
                setSelect();
                tab_menu_Me.setSelected(true);
                if (fragment_Me == null) {
                    fragment_Me = new FragmentMe();
                    fragmentTransaction.add(R.id.ly_content, fragment_Me);
                } else
                    fragmentTransaction.show(fragment_Me);
                break;
            case R.id.tab_menu_Add:
                setSelect();
                tab_menu_Add.setSelected(true);
                if (fragment_Add == null) {
                    fragment_Add = new FragmentAdd();
                    fragmentTransaction.add(R.id.ly_content, fragment_Add);
                } else
                    fragmentTransaction.show(fragment_Add);
                break;
        }
        fragmentTransaction.commit();

    }

    //初始化所有设备信息
    public void initshebei() {
        equipLists.clear();
        sqliteUtils.Search(equipLists);


    }


//    public void initdiatance() {
//
////        equipLists.clear();
////        for (int i = 0; i < sysParamter.resName.length; i++) {
////            EquipModel equipList = new EquipModel(sysParamter.buildImage.get(i + 11 + ""), sysParamter.buildTuding.get(i + 11 + ""), sysParamter.majorBulid.get(i + 11 + ""),
////                    sysParamter.majorImage.get(i + 11 + ""), i + 11, 101, "0", 0, 0);
////            equipLists.add(equipList);
////        }
//
//
//    }

}
