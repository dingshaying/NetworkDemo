package com.example.networkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class URLConnectionActivity extends AppCompatActivity {
//    private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=112.2.253.238";
    private static final String IP_BASE_URL = "http://ip.taobao.com/service/getIpInfo.php";
    private static final String IP_URL = IP_BASE_URL + "?ip=112.2.253.238";
    private TextView tvResult;
    private ScrollView scrollView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_connection);

        tvResult = findViewById(R.id.tv_result);
        scrollView = findViewById(R.id.scroll_view);
        imageView = findViewById(R.id.image_view);
        GlideApp.with(this).load("https://www.baidu.com/img/bd_logo1.png")
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageView);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = NetworkUtils.get(IP_URL);
                        if (result != null){
                            Log.d("MainActivity",result);
                            tvResult.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText(result);
                                }
                            });
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText("数据为null");
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.btn_post:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("ip","112.2.253.238"));
                        final String result = NetworkUtils.post(IP_BASE_URL,params);
                        if (result != null){
                            Log.d("MainActivity",result);
                            tvResult.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText(result);
                                }
                            });
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText("请求失败，未获得数据");
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.btn_upload:
                break;
            case R.id.btn_download:
                break;
        }
    }
}
