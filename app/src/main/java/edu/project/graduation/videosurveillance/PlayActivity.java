package edu.project.graduation.videosurveillance;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class PlayActivity extends Activity {
    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        surfaceView = (SurfaceView)findViewById(R.id.surfaceView3);
        surfaceView.getHolder().setFixedSize(768, 432);  //设置分辨率

        /*下面设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前*/
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mediaPlayer = new MediaPlayer();
        surfaceHolder =  surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);

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
                            String st = getIntent().getStringExtra("name");
                            mediaPlayer.setDataSource("http://123.207.117.28:81/hls/"+st+".mp4");
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
