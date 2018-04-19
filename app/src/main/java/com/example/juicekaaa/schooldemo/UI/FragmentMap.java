package com.example.juicekaaa.schooldemo.UI;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.juicekaaa.schooldemo.MainActivity;
import com.example.juicekaaa.schooldemo.R;
import com.example.juicekaaa.schooldemo.ResourceModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Juicekaaa on 16/10/14.
 */
public class FragmentMap extends Fragment {
    private View view;// 需要返回的布局
    private ImageView Image_down, tuding;
    private MainActivity mainActivity;
    Runnable runable;
    Handler handler;
    public Button map_mp3, map_video, map_photo, map_text;
//    public TextView map_mp3, map_video, map_photo, map_text;

    private TextView tv_text;
    private VideoView videoView;
    private MediaController mediaController;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_map, container, false);

        init();


        return view;

    }

    private void init() {
        tuding = (ImageView) view.findViewById(R.id.tuding);
        Image_down = (ImageView) view.findViewById(R.id.default_down);
        videoView = (VideoView) view.findViewById(R.id.map_video);
        tv_text = (TextView) view.findViewById(R.id.map_tv);

//        map_mp3= (TextView) view.findViewById(R.id.map_btn_mp3);
//        map_photo = (TextView) view.findViewById(R.id.map_btn_photo);
//        map_text = (TextView) view.findViewById(R.id.map_btn_text);
//        map_video = (TextView) view.findViewById(R.id.map_btn_video);

        map_mp3 = (Button) view.findViewById(R.id.map_btn_mp3);
        map_photo = (Button) view.findViewById(R.id.map_btn_photo);
        map_text = (Button) view.findViewById(R.id.map_btn_text);
        map_video = (Button) view.findViewById(R.id.map_btn_video);
    }

//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0x101) {
//                tuding.setX(100);
//                tuding.setY(200);
//                tuding.setVisibility(View.VISIBLE);
//            }
//
//            super.handleMessage(msg);
//        }
//    };
// Thread th= new Thread(new Runnable() {
//
//        @Override
//        public void run() {
//
//            int index = 0;
//            while (!Thread.currentThread().isInterrupted()) {
//                Message m = mHandler.obtainMessage();
//                m.what = 0x101;
//                mHandler.sendMessageDelayed(m, 2000);
//            }
//
//        }
//
//    });


    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
//        mainActivity.setmHandler(mHandler);

        handler = new Handler();
        runable = new Runnable() {
            @Override
            public void run() {
                refresh();


                map_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hide();
                        tv_text.setVisibility(View.VISIBLE);
                        String resource = isexsitresource(0);
                        if (resource != null) {
                            tv_text.setText(resource);
                        } else
                            Toast.makeText(getActivity(), "该位置没有相关文字信息", Toast.LENGTH_SHORT).show();


                    }
                });

                map_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hide();
                        Image_down.setVisibility(View.VISIBLE);
                        Image_down.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.school_map2));
//                        Image_down.setBackgroundResource(R.mipmap.school_map2);
//                        loadImage("/pic");
                        String resource = isexsitresource(1);
                        if (resource != null) {
                            String path = Environment.getExternalStorageDirectory().toString() + resource;
                            Image_down.setImageBitmap(BitmapFactory.decodeFile(path));
                        } else {
                            Toast.makeText(getActivity(), "该位置没有相关图片信息", Toast.LENGTH_SHORT).show();
                        }
                        Image_down.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getActivity(), "没有下一张", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });


                map_mp3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final MediaPlayer mediaPlayer = new MediaPlayer();
                        String resource = isexsitresource(2);
                        if (resource != null) {
                            try {
                                resource = Environment.getExternalStorageDirectory() + resource;
                                if (ContextCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {

                                    ActivityCompat.requestPermissions(getActivity(),
                                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            1);
                                } else {
                                    mediaPlayer.reset();
                                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    mediaPlayer.setDataSource(resource);
                                    mediaPlayer.prepareAsync();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                            }

                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mediaPlayer.start();
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "该位置没有相关音频信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


//                map_mp3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final MediaPlayer mediaPlayer = new MediaPlayer();
//                        String resource = isexsitresource(2);
//
//                        if (resource != null) {
//                            try {
//
//                                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                                        != PackageManager.PERMISSION_GRANTED) {
//
//                                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//
//                                } else {
//                                    mediaPlayer.reset();
//                                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                                    mediaPlayer.setDataSource(resource);
//                                    mediaPlayer.prepareAsync();
//
//                                }
//
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//
//                                Toast.makeText(getActivity(), "音频有错", Toast.LENGTH_SHORT).show();
//                            }
//
//                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                                @Override
//                                public void onPrepared(MediaPlayer mp) {
//                                    mediaPlayer.start();
//                                }
//                            });
//                        } else
//                            Toast.makeText(getActivity(), "该位置没有相关音频信息", Toast.LENGTH_SHORT).show();
//
//                    }
//                });

                map_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hide();
                        videoView.setVisibility(View.VISIBLE);
                        String resource = isexsitresource(3);

                        if (resource != null) {
                            File video = new File(resource);
                            mediaController = new MediaController(getActivity());
                            String path = Environment.getExternalStorageDirectory().toString() + video.getAbsolutePath();

                            videoView.setVideoPath(path);

                            //设置videoView与mController建立关联
                            videoView.setMediaController(mediaController);
                            mediaController.setMediaPlayer(videoView);
                            videoView.requestFocus();


                        } else
                            Toast.makeText(getActivity(), "该位置没有相关视频", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        };


        handler.post(runable);


    }

    public String isexsitresource(int type) {
        if (mainActivity.equipLists.isEmpty() == false) {
            List<ResourceModel> m_resource = mainActivity.equipLists.get(MainActivity.currentposition).getM_resource();
            for (int i = 0; i < m_resource.size(); i++) {
                if (m_resource.get(i).getResourcetype() == type) {
                    return m_resource.get(i).getResource();
                }

            }
        }
        return null;
    }


    public void hide() {
        tv_text.setVisibility(View.INVISIBLE);
        Image_down.setVisibility(View.INVISIBLE);
        videoView.setVisibility(View.INVISIBLE);
    }

    public void refresh() {
        if (MainActivity.currentposition != -1) {
            tuding.setX(mainActivity.equipLists.get(MainActivity.currentposition).mapX);
            tuding.setY(mainActivity.equipLists.get(MainActivity.currentposition).mapY);
//            int imageid = mainActivity.equipLists.get(MainActivity.currentposition).buildPhotoId;
//            Image_down.setImageResource(imageid);

//            if (tuding.getX() != mainActivity.equipLists.get(MainActivity.currentposition).mapX&&tuding.getY() != mainActivity.equipLists.get(MainActivity.currentposition).mapY) {
//                map_photo.performClick();
//            }
        }

        mainActivity.fragment_SheBei.update();
        handler.postDelayed(runable, 5000);

    }


    //    private void loadImage(String util) {
//        String path = Environment.getExternalStorageDirectory().toString() + "/danwei" + util;
//        try {
//
//            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(new File(path, "A.png")));
//            Image_down.setImageBitmap(bmp);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        //获取 / 最后一次出现的位置，然后位置+1截取剩余的就是文件名了
//        String s = "http://www.shidoe.com/shidoe/Edition/upfile/04.png";
//        int i = s.lastIndexOf("/");
//        String FileName = s.substring(i + 1);
//    }

}
