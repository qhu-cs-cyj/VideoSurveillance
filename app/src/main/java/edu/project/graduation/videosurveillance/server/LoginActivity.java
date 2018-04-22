package edu.project.graduation.videosurveillance.server;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        postname = name.getText().toString();
        postpasswd = passwd.getText().toString();
        strUrlPath = "http://123.207.117.28/applogint.php";
        encode = "utf-8";

        final Map<String,String> params = new HashMap<String,String>();;
        params.put("username",postname);
        params.put("userpasswd",postpasswd);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseResult = HttpUtils.submitPostData(strUrlPath,params,encode);
                HttpUtils.getRequestData(params,encode);
                Log.i("responseResult", ": "+responseResult);
            }
        });
    }
}
