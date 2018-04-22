package edu.project.graduation.videosurveillance.server;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.project.graduation.videosurveillance.R;

public class LoginActivity extends Activity{
    private Button btnlogin;
    private EditText name;
    private EditText passwd;
    private String strUrlPath;
    private String postname;
    private String postpasswd;
    private String encode;
    private String responseResult;
    InputStream inputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = (Button)findViewById(R.id.button);
        name = (EditText)findViewById(R.id.loginname);
        passwd = (EditText)findViewById(R.id.loginpasswd);
        responseResult = null;

        strUrlPath = "http://123.207.117.28/applogint.php";
        encode = "utf-8";

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postname = name.getText().toString();
                postpasswd = passwd.getText().toString();
                final Map<String,String> params = new HashMap<String,String>();
                params.put("username",postname);
                params.put("userpasswd",postpasswd);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        responseResult = HttpUtils.submitPostData(strUrlPath,params,encode);
                        Log.i("responseResult", ": "+responseResult);
                        if (responseResult.equals("1"))
                        {
                            LoginActivity.this.finish();
                        }
                        else
                        {
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this, "密码错误!", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
    }
}
