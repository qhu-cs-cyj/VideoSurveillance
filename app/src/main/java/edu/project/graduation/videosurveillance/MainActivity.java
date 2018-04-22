package edu.project.graduation.videosurveillance;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        surfaceView = (SurfaceView)this.findViewById(R.id.surfaceView);
        surfaceView.getHolder().setFixedSize(768, 432);  //设置分辨率

        /*下面设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前*/
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        try {
            /* new 一个播放器 mediaPlayer */
            mediaPlayer = new MediaPlayer();
            surfaceHolder =  surfaceView.getHolder();
            surfaceHolder.setKeepScreenOn(true);
//            surfaceHolder.addCallback(new SurfaceLis);
            mediaPlayer.setDataSource("http://123.207.117.28/video/请听我说 (Gra Roo Nah Fung Hai Job) 中文字幕--音悦Tai.mp4");

//            mediaPlayer.reset();      //重置为初始状态
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            /* 设置Video影片以SurfaceHolder播放 */
//
//
//            mediaPlayer.prepare();             //缓冲

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.setDisplay(surfaceView.getHolder());
                    mediaPlayer.start();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(mediaPlayer != null)
                        mediaPlayer.release();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
