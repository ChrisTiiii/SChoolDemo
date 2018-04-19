package com.example.juicekaaa.schooldemo.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juicekaaa.schooldemo.Data.FileUtils;
import com.example.juicekaaa.schooldemo.Data.MyDBHelper;
import com.example.juicekaaa.schooldemo.Data.SqliteUtils;
import com.example.juicekaaa.schooldemo.MainActivity;
import com.example.juicekaaa.schooldemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by apple on 16/5/19.
 */
public class FragmentMe extends Fragment {
    //    private static final String urlPath = "http://192.168.1.100:8080/project02/task2-1.jsp";
    private static final String urlPath_danwei = "http://192.168.1.102:8080/keyuantong/admin/download.jsp?method=danwei";
    private static final String urlPath_iBeacon = "http://192.168.1.102:8080/keyuantong/admin/download.jsp?method=device&danweiID=1001";
    private static final String urlPath_map = "http://192.168.1.102:8080/keyuantong/admin/download.jsp?method=map&danweiID=1001";
    private static final String urlPath_location_resource = "http://192.168.1.102:8080/keyuantong/admin/download.jsp?method=resource&danweiID=1001";


    private static final String url_dowload = "http://www.shidoe.com/shidoe/";

    private static final String TABLENAME_DANWEI = "danwei";
    private static final String TABLENAME_IBEACON = "ibeacon";
    private static final String TABLENAME_MAP = "map";
    private static final String TABLENAME_LOCATION_RESOURCE = "location_resource";

    private static final String FILEURL = "url";
    private SQLiteDatabase db = null;


    private static final String DANWEI = "danwei";
    private static final String MAP = "map";
    private static final String RESOURCE = "resource";
    private static final String IBEACON = "iBeacon";


    MyAdapter myAdapter;
    ListView mlistview = null;
    List<InitData> m_listdata;
    Button btn_text;
    TextView tv_text;
    ListView succList;

    public SqliteUtils sqliteUtils;

    private View view;
    private String resource[] = new String[]{"无锡科技职业学院", "无锡太湖学院", "无锡职业学院"};


//    private Button btn_denglu, btn_zhuce;
//    private EditText et_Name, et_PassWord;

    private Handler m_handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MyDBHelper myDBHelper = new MyDBHelper(getActivity());
            db = myDBHelper.getWritableDatabase();
            switch (msg.what) {
                case 199:
                    Log.e("bbb", DANWEI);
                    String JSON_danwei = (String) msg.obj;
                    parseJsonDanWei(JSON_danwei);
                    break;
                case 200:
                    Log.e("bbb", IBEACON);
                    String JSON_iBeacon = (String) msg.obj;
                    parseJsonIbeacon(JSON_iBeacon);
                    break;
                case 201:
                    Log.e("bbb", MAP);
                    String JSON_Map = (String) msg.obj;
                    parseJsonMap(JSON_Map);
                    break;
                case 202:
                    Log.e("bbb", RESOURCE);
                    String JSON_location_resource = (String) msg.obj;
                    parseJsonLocation_Resource(JSON_location_resource);
                    break;
            }

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.frag_me, container, false);
            m_listdata = getData();

            init();

            myAdapter = new MyAdapter();
            mlistview.setAdapter(myAdapter);


            btn_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String sql = "select danweiID from danwei";

//                            getJson(urlPath_danwei);


                        }

                    }).start();


                }
            });


//            m_handler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    switch (msg.what) {
//                        case 199:
//
//                            String JSON = (String) msg.obj;
////                            parseJson(JSON);
//                            parseJsonDanWei(JSON);
//
//
//                            break;
//                    }
//
//                }
//            };
            return view;
        }


//        btn_zhuce = (Button) view.findViewById(R.id.me_btn_zhuce);
//        btn_denglu = (Button) view.findViewById(R.id.me_btn_denglu);
//        et_Name = (EditText) view.findViewById(R.id.me_et_Name);
//        et_PassWord = (EditText) view.findViewById(R.id.me_et_PassWord);
//        btn_denglu.setOnClickListener(this);
//        btn_zhuce.setOnClickListener(this);
        return view;
    }


    private void init() {
        mlistview = (ListView) view.findViewById(R.id.frag_me_list);
        btn_text = (Button) view.findViewById(R.id.btn_text);
        tv_text = (TextView) view.findViewById(R.id.txt_text);
        succList = (ListView) view.findViewById(R.id.succList);
    }

    //数据设置
    public List<InitData> getData() {
        List<InitData> list = new ArrayList<InitData>();
        for (int i = 0; i < resource.length; i++) {
            InitData initData = new InitData(resource[i]);
            list.add(initData);
        }

        return list;

    }

    public void downloadDialog(int position, final ViewHolder holder) {

        new AlertDialog.Builder(getActivity()).setTitle("确定要下载" + resource[position] + "资源包么？").setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                            getJson(urlPath_danwei);
                        getJson(urlPath_danwei);
                        getJson(urlPath_iBeacon);
                        getJson(urlPath_map);
                        getJson(urlPath_location_resource);

                    }

                }).start();


                Toast.makeText(getActivity(), "正在下载", Toast.LENGTH_SHORT).show();


//                downloadData();
                getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    holder.btn_download.setVisibility(View.INVISIBLE);
                                                    holder.progressBar.setVisibility(View.VISIBLE);
                                                }
                                            }
                );


                // 启动线程来执行任务
                new Thread() {
                    //该程序模拟天成长度为100的数组
                    final int[] data = new int[100];

                    int hasData = 0;

                    int progressStatus = 0;

                    public void run() {
                        while (progressStatus < 100) {
                            // 获取耗时的完成百分比
                            progressStatus = doWork();
                            holder.progressBar.setProgress(progressStatus);

                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity main = (MainActivity) getActivity();
                                main.sqliteUtils.insert();
                                main.sqliteUtils.Search(main.equipLists);
                                holder.progressBar.setVisibility(View.INVISIBLE);
                                holder._done.setVisibility(View.VISIBLE);

//                                FileUtils.downLoad(url_dowload + "Edition/upfile/04.png", "/pic", "A. png");//下载完成后就可以在手机目录里查看到了


                            }
                        });


                    }

                    //模拟一个耗时的操作
                    private int doWork() {
                        data[hasData++] = (int) (Math.random() * 100);
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return hasData;
                    }
                }.start();


                writePreferences();
            }


        }).show();


    }

    class InitData extends FragmentMe {
        private String mSchool;

        public InitData(String school) {
            this.mSchool = school;
        }

        public String getmSchool() {
            return mSchool;
        }
    }

    public class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return m_listdata.size();
        }

        @Override
        public Object getItem(int position) {
            return m_listdata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            final ViewHolder viewholder;
//            MyListener myListener = null;
            if (view == null) {

                viewholder = new ViewHolder();

                view = getActivity().getLayoutInflater().inflate(R.layout.frag_me_list_item, null);
                viewholder._school = (TextView) view.findViewById(R.id.me_list_text);
                viewholder.btn_download = (Button) view.findViewById(R.id.me_list_btn);
                viewholder.progressBar = (ProgressBar) view.findViewById(R.id.pb_progressbar);
                viewholder._done = (TextView) view.findViewById(R.id.me_list_done);

                view.setTag(viewholder);

            } else {
                viewholder = (ViewHolder) view.getTag();

            }


            //数据的绑定：
//            viewholder.school.setText((String) m_listdata.get(position).get("school"));
            readPreferences(viewholder);
            final InitData initData = (InitData) getItem(position);
            viewholder._school.setText(initData.getmSchool());

            viewholder.btn_download.setTag(position);

            //给button添加点击事件，添加button之后listview将失去焦点，需要的直接把Button的焦点去掉
//            viewholder.btn_download.setOnClickListener(myListener);
            viewholder.btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downloadDialog(position, viewholder);
                }
            });
            return view;
        }
    }


    public final class ViewHolder {
        public TextView _school, _done;
        public Button btn_download;
        public ProgressBar progressBar;
    }


    private void getJson(String _url) {
        String wholeUrl = _url;
        Log.e("TEST_JSON", wholeUrl);
        try {
            URL object = new URL(wholeUrl);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            //给服务器发送JSON
           /*
            JSONObject data = new JSONObject();

            data.put("tablename", "device");
            data.put("msg", "hello");
            data.put("SQL","select * from device" );
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "utf-8");
            wr.write(data.toString());
            wr.flush();
            Log.d("TEST_JSON","给服务器发送的JSON字符串：" + data.toString());
*/

            // 显示 POST 请求返回的内容
            final StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                Log.e("TEST_JSON", "服务器返回的字符串：" + sb.toString());

//                Message msg = new Message();
//                msg.obj = sb.toString();
//                m_handler.sendMessage(msg);

                if (_url.equals(urlPath_danwei)) {
//                        Log.e("aaa","danwei");

                    Message mes = new Message();
                    mes.what = 199;
                    mes.obj = sb.toString();
                    m_handler.sendMessage(mes);

                } else if (_url.equals(urlPath_iBeacon)) {
//                        Log.e("aaa","ibeacon");
                    Message mes1 = new Message();
                    mes1.what = 200;
                    mes1.obj = sb.toString();
                    m_handler.sendMessage(mes1);

                } else if (_url.equals(urlPath_map)) {
//                        Log.e("aaa","map");
                    Message mes2 = new Message();
                    mes2.what = 201;
                    mes2.obj = sb.toString();
                    m_handler.sendMessage(mes2);
                } else if (_url.equals(urlPath_location_resource)) {
//                        Log.e("aaa","resource");
                    Message msg3 = new Message();
                    msg3.what = 202;
                    msg3.obj = sb.toString();
                    m_handler.sendMessage(msg3);

                }


            } else {
                //System.out.println(con.getResponseMessage());
                Log.e("TEST_JSON", "服务器返回内容失败");//http协议连接失败
            }
        } catch (Exception e) {
            Log.e("TEST_JSON", "发生异常:" + e.getMessage());
        }
    }


//        String _check = "select user_name,user_password from user where user_name='" + et_Name.getText() + "' and user_password='" + et_PassWord.getText() + "'";
//
//        switch (v.getId()) {
//            case R.id.me_btn_zhuce:
//                if (et_Name.getText().toString().trim().length() != 0
//                        && et_PassWord.getText().toString().trim().length() != 0) {
//                    try {
//                        //insert into user (username,passwor)values('zs','asd123');
//                        String sql = "insert into user(user_name,user_password) values('" + et_Name.getText() + "','" + et_PassWord.getText() + "')";
//                        db.execSQL(sql);
//                        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_LONG).show();
//                        et_Name.setText("");
//                        et_PassWord.setText("");
//
//                    } catch (Exception e) {
//                        Toast.makeText(getActivity(), "错误提示：" + e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//
//                } else
//                    Toast.makeText(getActivity(), "请输入账号和密码", Toast.LENGTH_LONG).show();
//
//                break;
//
//            case R.id.me_btn_denglu:
//                Cursor c = db.rawQuery(_check, null);
//                if (c.moveToNext() == false) {
//                    Toast.makeText(getActivity(), "登陆失败，请注册！", Toast.LENGTH_LONG).show();
//                    et_Name.setText("");
//                    et_PassWord.setText("");
//                    return;
//                } else
//                    Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_LONG).show();
//                et_Name.setText("");
//                et_PassWord.setText("");
//
//                break;
//        }


    // 普通Json数据解析
    private void parseJson(String strResult) {
        try {
            JSONObject jsonObj = new JSONObject(strResult).getJSONObject("person");
            int id = jsonObj.getInt("id");
            String name = jsonObj.getString("name");
            String sex = jsonObj.getString("sex");
            tv_text.setText("ID号" + id + ", 姓名：" + name + ",性别：" + sex);
        } catch (JSONException e) {
            System.out.println("Json parse error");
            e.printStackTrace();
        }
    }

    //解析多个数据的Json
    private void parseJsonDanWei(String strResult) {
        try {
            JSONArray jsonObjs = new JSONArray(strResult);
            String s = "";

            for (int i = 0; i < jsonObjs.length(); i++) {
                JSONObject jsonObj = (JSONObject) jsonObjs.get(i);
                int danweiID = jsonObj.getInt("danweiID");
                String UUID = jsonObj.getString("UUID");
                double gpsX = jsonObj.getDouble("gpsX");
                double gpsY = jsonObj.getDouble("gpsY");
                String title = jsonObj.getString("title");
                String detail = jsonObj.getString("detail");
                insertDanWeiData(TABLENAME_DANWEI, danweiID, UUID, gpsX, gpsY, title, detail, 1);
                s += "danweiID号" + danweiID + ", UUID：" + UUID + ",gpsX：" + gpsX + ",gpsY:" + gpsY + ",title:" + title + ",detail:" + detail + "\n";
                Log.e("sss", s);
            }
//            writePreferences();
//            tv_text1.setText(s);
        } catch (JSONException e) {
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
    }


    private void parseJsonIbeacon(String strResult) {
        try {
            JSONArray jsonObjs = new JSONArray(strResult);
            String s = "";
            for (int i = 0; i < jsonObjs.length(); i++) {
                JSONObject jsonObj = (JSONObject) jsonObjs.get(i);
                int ibeaconID = jsonObj.getInt("ibeaconID");
                int danweiID = jsonObj.getInt("danweiID");
                int mapID = jsonObj.getInt("mapID");
                int gotomapID = jsonObj.getInt("gotomapID");
                int major = jsonObj.getInt("major");
                int minor = jsonObj.getInt("minor");
                int mapX = jsonObj.getInt("mapX");
                int mapY = jsonObj.getInt("mapY");
                String title = jsonObj.getString("title");
                String detail = jsonObj.getString("detail");
                insertIbeaconData(TABLENAME_IBEACON, ibeaconID, danweiID, mapID, gotomapID, major, minor, mapX, mapY, title, detail);
                s += "ibeaconID：" + ibeaconID + "danweiID:" + danweiID + "mapID:" + mapID + "gotomapID" + gotomapID + "major" + major + "minor" + minor + "mapX" + mapX + "mapY" + mapY + "title" + title + "detail" + detail;
                Log.e("sss", s);

            }
        } catch (JSONException e) {
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
    }

    private void parseJsonMap(String strResult) {
        try {
            JSONArray jsonObjs = new JSONArray(strResult);
            String s = "";
            for (int i = 0; i < jsonObjs.length(); i++) {
                JSONObject jsonObj = (JSONObject) jsonObjs.get(i);
                int mapID = jsonObj.getInt("mapID");
                int danweiID = jsonObj.getInt("danweiID");
                int maptype = jsonObj.getInt("maptype");
                int mapversion = jsonObj.getInt("mapversion");
                int width = jsonObj.getInt("width");
                int height = jsonObj.getInt("height");
                String maptitle = jsonObj.getString("maptitle");
                String maplocation = jsonObj.getString("maplocation");
                insertMapData(TABLENAME_MAP, mapID, danweiID, maptype, mapversion, width, height, maptitle, maplocation);
                s += "mapID" + mapID + "danweiID" + danweiID + "maptype" + maptype;
                Log.e("sss", s);

            }
        } catch (JSONException e) {
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
    }


    private void parseJsonLocation_Resource(String strResult) {
        try {
            JSONArray jsonObjs = new JSONArray(strResult);
            String s = "";
            for (int i = 0; i < jsonObjs.length(); i++) {
                JSONObject jsonObj = (JSONObject) jsonObjs.get(i);
                int resID = jsonObj.getInt("resID");
                int danweiID = jsonObj.getInt("danweiID");
                int ibeaconID = jsonObj.getInt("ibeaconID");
                int resourcetype = jsonObj.getInt("resourcetype");
                String resource = jsonObj.getString("resource");
                insertLocation_Resource_Data(TABLENAME_LOCATION_RESOURCE, resID, danweiID, ibeaconID, resourcetype, resource, 1);
                s += "resID:" + resID + "danweiID" + danweiID + "ibeaconID" + ibeaconID;
                Log.e("sss", s);
            }
        } catch (JSONException e) {
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
    }

    private void insertLocation_Resource_Data(String tablenameLocationResource, int resID, int danweiID, int ibeaconID, int resourcetype, String resource, int downloaded) {
        String sql_insert_Location_Resource = "insert into " + tablenameLocationResource + " values(" + resID + "," + danweiID + "," + ibeaconID + "," + resourcetype + "," +
                "'" + resource + "'," + downloaded + ")";
        db.execSQL(sql_insert_Location_Resource);
    }

    private void insertMapData(String tablenameMap, int mapID, int danweiID, int maptype, int mapversion, int width, int height, String maptitle, String maplocation) {
        String sql_insert_Map = "insert into " + tablenameMap + " values(" + mapID + "," + danweiID + "," + maptype + "," + mapversion + "," +
                "" + width + "," + height + ",'" + maptitle + "','" + maplocation + "')";
        db.execSQL(sql_insert_Map);

    }

    private void insertIbeaconData(String tablenameIbeacon, int ibeaconID, int danweiID, int mapID, int gotomapID, int major, int minor, int mapX, int mapY, String title, String detail) {
        String sql_insert_Ibeacon = "insert into " + tablenameIbeacon + " values(" + ibeaconID + "," + danweiID + "," + mapID + "," + gotomapID + "," + major + "," + minor + "," +
                "" + mapX + "," + mapY + ",'" + title + "','" + detail + "')";
        db.execSQL(sql_insert_Ibeacon);
    }


    public void insertDanWeiData(String tablenameDanWei, int danweiID, String UUID, Double gpsX, Double gpsY, String title, String detail, int downloaded) {
        String sql_insert_danwei = "insert into " + tablenameDanWei + " values(" + danweiID + ",'" + UUID + "'," + gpsX + "," + gpsY + ",'" + title + "','" + detail + "'," + downloaded + ")";
        db.execSQL(sql_insert_danwei);
    }


    private void writePreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILEURL, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url", urlPath_danwei);
        editor.commit();
    }

    private void readPreferences(ViewHolder viewholder) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILEURL, Activity.MODE_PRIVATE);
        String url = sharedPreferences.getString("url", null);
        if (urlPath_danwei.equals(url)) {
            viewholder.btn_download.setVisibility(View.GONE);
            viewholder._done.setVisibility(View.VISIBLE);

        }

    }

    public void downloadData() {
        for (int i = 0; i < sqliteUtils.searchPathfromDB().size(); i++) {
            String path = sqliteUtils.searchPathfromDB().get(i);
            int j = path.lastIndexOf("/");
            String second_path = path.substring(path.lastIndexOf("/") - 1, path.lastIndexOf("/"));
            String FileName = path.substring(j + 1);
            FileUtils.downLoad(url_dowload + path, "/" + second_path, FileName);
        }
    }

}

