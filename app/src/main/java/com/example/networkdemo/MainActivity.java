package com.example.networkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnUrl;
    private Button btnOk;
    private Button btnGain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUrl = findViewById(R.id.btn_url);
        btnUrl.setOnClickListener(this);
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        btnGain = findViewById(R.id.btn_gain);
        btnGain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_url:
                Intent intent = new Intent(MainActivity.this, URLConnectionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ok:
                intent = new Intent(MainActivity.this,OkHttpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gain:
                intent = new Intent(MainActivity.this, ThreeDataActivity.class);
                startActivity(intent);
                break;
        }

    }
}
