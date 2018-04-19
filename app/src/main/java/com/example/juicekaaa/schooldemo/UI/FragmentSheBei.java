package com.example.juicekaaa.schooldemo.UI;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juicekaaa.schooldemo.EquipModel;
import com.example.juicekaaa.schooldemo.MainActivity;
import com.example.juicekaaa.schooldemo.R;

import static com.example.juicekaaa.schooldemo.R.id.list;


/**
 * Created by Juicekaaa on 16/10/14.
 */
public class FragmentSheBei extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private MyAdapter mAdapter;//适配器
    private ListView mlistView = null;
    private MainActivity mainActivity;

    Handler mHander;
    Runnable runnable;
    MyAdapter.Zujian zujian;

    public FragmentSheBei() {

    }

//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            switch (msg.what) {
//
//
//                case 11:
////                    new Thread(new Runnable() {
////
////                        @Override
////                        public void run() {
////                            Looper.prepare();
////                            try {
////                                Thread.sleep(1000);
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    }).start();
//
//                    Bundle bundle = msg.getData();
//                    juli = bundle.getString("txt");
//                    mAdapter.updateItem(0);
////                    Looper.loop();
//
//
//                    break;
//
//                case 15:
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            Looper.prepare();
////                            try {
////                                Thread.sleep(1000);
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    }).start();
//
//                    Bundle bundle1 = msg.getData();
//                    juli = bundle1.getString("txt");
//                    mAdapter.updateItem(4);
////                    Looper.loop();
//
//
//                    break;
//
//
//                default:
//                    break;
//            }
//
//
//            super.handleMessage(msg);
//        }
//    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;

//        final MyAdapter myAdapter=new MyAdapter();

//        mHander = new Handler();
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//              if (myAdapter.getItemId(MainActivity.currentposition)!=-1) {
//
//                }
//            }
//        };
//        mHander.post(runnable);
    }


    //UI初始化
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if (view == null) {//优化View减少View的创建次数

            view = inflater.inflate(R.layout.frag_shebei, container, false);
            //mDataList = getData();
            mlistView = (ListView) view.findViewById(list);
            mAdapter = new MyAdapter();
            mlistView.setAdapter(mAdapter);

            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip_container);
            return view;
        }


        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, 1000);
            }
        });


    }


//    //数据设置
//    public List<ListData> getData() {
//        Log.d(TAG, "Data设置成功");
//        List<ListData> list = new ArrayList<ListData>();
//        for (int i = 0; i < resName.length; i++) {
//            ListData data = new ListData(resName[i], location, resImages[i]);
//            list.add(data);
//
//        }
//
//        return list;
//    }

//    class ListData extends FragmentSheBei {
//        private String mTitle, mContent;
//        private int mIcon;
//
//        public ListData(String title, String content, int icon) {
//            this.mTitle = title;
//            this.mContent = content;
//            this.mIcon = icon;
//        }
//
//        public String getmTitle() {
//            return mTitle;
//        }
//
//        public String getmContent() {
//            return mContent;
//        }
//
//        public int getmIcon() {
//            return mIcon;
//        }
//
//    }

    public class MyAdapter extends BaseAdapter {

        /**
         * 组件集合，listview_item.xml中的控件
         */
        public final class Zujian {
            public ImageView imageView;
            public TextView tv_title, tv_content;

        }

        @Override
        public int getCount() {
            return mainActivity.equipLists.size();
        }

        //获得某一位置的数据
        @Override
        public EquipModel getItem(int position) {
            return mainActivity.equipLists.get(position);
        }

        //获得唯一标识
        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            Zujian zujian = null;
            if (view == null) {
                zujian = new Zujian();
                //获得组件，实例化组件
                view = getActivity().getLayoutInflater().inflate(R.layout.listview_item, null);
                zujian.imageView = (ImageView) view.findViewById(R.id.Item_img_res);
                zujian.tv_title = (TextView) view.findViewById(R.id.Item_tv_Title);
                zujian.tv_content = (TextView) view.findViewById(R.id.Item_tv_content);
                view.setTag(zujian);


            } else {
                zujian = (Zujian) view.getTag();
            }


            //数据绑定
            //final ListData listData = (ListData) getItem(position);
            EquipModel item = getItem(position);
            zujian.tv_title.setText(item.getTitle());
            zujian.tv_content.setText("当前距离您:" + mainActivity.calculateAccuracy(item.txPower, Double.parseDouble(item.rssi + "")) + "m");
//            zujian.imageView.setImageResource(item.getImageID());

            if (position == MainActivity.currentposition) {
//            zujian.imageView.setImageResource(mainActivity.equipLists.get(MainActivity.currentposition).buildPhotoId);
                zujian.imageView.setImageResource(item.buildPhotoId);
                update();
            } else
                zujian.imageView.setImageResource(R.mipmap.write);
//            mHander.postDelayed(runnable, 5000);

            return view;
        }


        //getChildAt(position)方法获取到当前的position项，获取的时候需做一个位置计算
//        public void updateItem(int position) {
//            //得到第一个可显示控件的位置
//            int firstvisible = mlistView.getFirstVisiblePosition();
//            int lastvisibale = mlistView.getLastVisiblePosition();
//            if (position >= firstvisible && position <= lastvisibale) {
//                //得到需要更新的item的View
//                view = mlistView.getChildAt(position - firstvisible);
//                if (view == null)
//                    return;
//                //从view中取得组件
//                Zujian zujian = (Zujian) view.getTag();
//                //然后用zujian去更新需要更新的view
//                ListData item = mDataList.get(position);
//
//                zujian.tv_title = (TextView) view.findViewById(R.id.Item_tv_Title);
//                zujian.tv_content = (TextView) view.findViewById(R.id.Item_tv_content);
//                zujian.imageView = (ImageView) view.findViewById(R.id.Item_img_res);
//
//                update();
//
//            }
//        }
    }

    public void update() {
        if (mainActivity.equipLists.size() != 0) {
            mAdapter.notifyDataSetChanged();
            mlistView.invalidate();
        }

    }


}

