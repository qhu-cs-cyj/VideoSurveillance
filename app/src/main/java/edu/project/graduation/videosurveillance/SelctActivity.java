package edu.project.graduation.videosurveillance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class SelctActivity extends Activity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        btn1 = (Button)findViewById(R.id.button11);
        btn2 = (Button)findViewById(R.id.button12);
        btn3 = (Button)findViewById(R.id.button13);
        btn4 = (Button)findViewById(R.id.button14);
        btn5 = (Button)findViewById(R.id.button15);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent();
                inn.setClass(SelctActivity.this, PlayActivity.class);
                inn.putExtra("name","v1");
                startActivity(inn);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent();
                inn.setClass(SelctActivity.this, PlayActivity.class);
                inn.putExtra("name","v2");
                startActivity(inn);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent();
                inn.setClass(SelctActivity.this, PlayActivity.class);
                inn.putExtra("name","v3");
                startActivity(inn);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent();
                inn.setClass(SelctActivity.this, PlayActivity.class);
                inn.putExtra("name","v4");
                startActivity(inn);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent();
                inn.setClass(SelctActivity.this, PlayActivity.class);
                inn.putExtra("name","v5");
                startActivity(inn);
            }
        });
    }
}
