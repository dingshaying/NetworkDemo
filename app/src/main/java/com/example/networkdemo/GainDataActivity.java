package com.example.networkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class GainDataActivity extends AppCompatActivity {
    private TextView tvResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gain_data);

        tvResult2 = findViewById(R.id.tv_result2);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_weather:
                break;
            case R.id.btn_oil_price:
                break;
            case R.id.btn_news:
                break;
        }
    }
}
