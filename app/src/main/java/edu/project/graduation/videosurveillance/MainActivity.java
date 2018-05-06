package edu.project.graduation.videosurveillance;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.project.graduation.videosurveillance.server.LoginActivity;

public class MainActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private boolean isPlay;
    private Thread mt;

    ViewPager viewPager;
    MyNewAdapter adapter;
    List<View> views;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        Intent in = new Intent();
        in.setClass(MainActivity.this, LoginActivity.class);
        startActivity(in);

//        viewPager = (ViewPager)findViewById(R.id.viewpager);
//        views = new ArrayList<View>();
//        LayoutInflater inflater  = LayoutInflater.from(getApplicationContext());
//
//        View view1 = inflater.inflate(R.layout.hello_tab1,null);
//        View view2 = inflater.inflate(R.layout.hello_tab2,null);
//        View view3 = inflater.inflate(R.layout.hello_tab3,null);
//        views.add(view1);
//        views.add(view2);
//        views.add(view3);
//
//        adapter = new MyNewAdapter(views);
//        viewPager.setAdapter(adapter);

        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceView.getHolder().setFixedSize(768, 432);  //设置分辨率

        /*下面设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前*/
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mediaPlayer = new MediaPlayer();
        surfaceHolder =  surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);
        mt = new Thread();

        btn1 = (Button)findViewById(R.id.button2);
        btn2 = (Button)findViewById(R.id.button3);
        btn3 = (Button)findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                playStreaming(1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                playStreaming(2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent in2 = new Intent();
                in2.setClass(MainActivity.this, SelctActivity.class);
                startActivity(in2);
            }
        });

        isPlay = false;
        playStreaming(1);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void playStreaming(int n){

        if(n == 1){
            if(mt != null && mt.isAlive()){
                mt.interrupt();
            }
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    mt = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            /* new 一个播放器 mediaPlayer */
                            mediaPlayer.reset();
                            mediaPlayer.setDisplay(surfaceHolder);
                            try {
//                          mediaPlayer.setDataSource("http://123.207.117.28/video/1.mp4");
                                mediaPlayer.setDataSource("http://123.207.117.28:81/hls/t1.m3u8");
                                mediaPlayer.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mediaPlayer.start();
//            mediaPlayer.reset();      //重置为初始状态
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            /* 设置Video影片以SurfaceHolder播放 */
//
//
//            mediaPlayer.prepare();             //缓冲

//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mp) {
//
//                            }
//                        });
//
//                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                            @Override
//                            public void onCompletion(MediaPlayer mp) {
//                                if(mediaPlayer != null)
//                                    mediaPlayer.release();
//                            }
//                        });
                        }
                    });
                    mt.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
        }else if(n == 2){
            if(mt != null && mt.isAlive()){
                mt.interrupt();
            }
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            /* new 一个播放器 mediaPlayer */
                            mediaPlayer.reset();
                            mediaPlayer.setDisplay(surfaceHolder);
                            try {
//                          mediaPlayer.setDataSource("http://123.207.117.28/video/1.mp4");
                                mediaPlayer.setDataSource("http://123.207.117.28:81/hls/t2.m3u8");
                                mediaPlayer.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mediaPlayer.start();
//            mediaPlayer.reset();      //重置为初始状态
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            /* 设置Video影片以SurfaceHolder播放 */
//
//
//            mediaPlayer.prepare();             //缓冲

//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mp) {
//
//                            }
//                        });
//
//                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                            @Override
//                            public void onCompletion(MediaPlayer mp) {
//                                if(mediaPlayer != null)
//                                    mediaPlayer.release();
//                            }
//                        });
                        }
                    }).start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
        }
    }
}
