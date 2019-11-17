package com.example.networkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.networkdemo.model.OilPrice;
import com.example.networkdemo.model.OilPriceBody;
import com.example.networkdemo.model.OilPriceRes;
import com.example.networkdemo.model.WeatherCurrent;
import com.example.networkdemo.model.WeatherDay;
import com.example.networkdemo.model.WeatherRealtime;
import com.example.networkdemo.model.WeatherFuture;
import com.example.networkdemo.model.WeatherToday;
import com.show.api.ShowApiRequest;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThreeDataActivity extends AppCompatActivity {
    //聚合数据的数据URL
    private static final String FIVE_WEATHR_URL = "http://apis.juhe.cn/simpleWeather/query";
    private static final String FIVE_WEATHER_APP_KEY = "37750ecd59ce59e7b48f95adf85af8ca";
    private static final String SEVEN_WEATHER_URL = "http://v.juhe.cn/weather/index";
    private static final String SEVEN_WEATHER_APP_KEY = "eb5900db96285b39222a73ed1437a53b";

    //易源数据的app_id和key
    private static final String APP_ID = "47490";
    private static final String KEY = "c1813441e5a0477cb68618165c4226dc";
    private static final String OIL_URL = "http://route.showapi.com/138-46";

    private TextView tvResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_data);

        tvResult2 = findViewById(R.id.tv_result2);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_five_weather:
                getFiveWeather("南京");
                break;
            case R.id.btn_seven_weather:
                getSevenWeather("南京");
                break;
            case R.id.btn_oil_price:
                getOilPrice("江苏");
                break;
            case R.id.btn_news:
                break;
        }
    }

    private void getSevenWeather(String city) {
        try {
            // 1. 组装数据请求的url
            String url = SEVEN_WEATHER_URL +
                    "?key=" + SEVEN_WEATHER_APP_KEY +
                    "&cityname=" + URLEncoder.encode(city, "utf-8");

            // 2. 使用OkHttp发送请求
            OkHttpClient client = HttpsUtil.handleSSLHandshakeByOkHttp();
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e("ThreeDataActivity", e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful()) {
                        String json = response.body().string();
                        JSONObject result = JSON.parseObject(json);
                        if("200".equals(result.getString("resultcode"))) {
                            // 获取实时天气
                            JSONObject obj = result.getJSONObject("result").getJSONObject("sk");
                            final WeatherCurrent current = JSON.parseObject(obj.toJSONString(), WeatherCurrent.class);

                            // 获取今天的天气
                            obj = result.getJSONObject("result").getJSONObject("today");
                            final WeatherToday today = JSON.parseObject(obj.toJSONString(), WeatherToday.class);

                            // 获取7天的天气趋势
                            obj = result.getJSONObject("result").getJSONObject("future");
                            final List<WeatherDay> days = getWeatherFuture(obj.toJSONString());

                            if(today != null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringBuilder builder = new StringBuilder();
                                        builder.append(current).append("\n\n")
                                                .append(today).append("\n\n")
                                                .append("7天的天气趋势：");
                                        for(WeatherDay day : days) {
                                            builder.append("\n").append(day);
                                        }
                                        tvResult2.setText(builder.toString());
                                    }
                                });
                            }
                        } else {
                            Log.d("ThreeDataActivity", result.getString("reason"));
                        }

                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private List<WeatherDay> getWeatherFuture(String futureJson) {
        List<WeatherDay> days = new ArrayList<>();

        // 1. 获取今天日期的字符串
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        // 2. 获取未来7天的天气对象
        for(int i = 0; i < 7; i++) {
            String day = format.format(calendar.getTime());
            String data = JSON.parseObject(futureJson).getJSONObject("day_" + day).toJSONString();
            days.add(JSON.parseObject(data, WeatherDay.class));

            // 在现有日期上增加1天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    private void getFiveWeather(String city){
        try {
            //1.组装数据请求的url
            String url = FIVE_WEATHR_URL
                    + "?key=" + FIVE_WEATHER_APP_KEY
                    + "&city=" + URLEncoder.encode(city,"utf-8");
            //2.使用OKHttp发送请求
            Request request = new Request.Builder().url(url).build();
            new OkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e("ThreeDataActivity", e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    //3.数据的处理
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        //用Android自带的JSON解析，将json字符串解析为JSONObject
                        JSONObject obj = JSON.parseObject(json);
                        if(obj != null){
                            JSONObject result = obj.getJSONObject("result");
                            JSONObject realtime = result.getJSONObject("realtime");
                            final String city = result.getString("city");


                            //利用FastJSON转对象
                            final WeatherRealtime weather = JSON.parseObject(realtime.toJSONString(), WeatherRealtime.class);

                            // 获取5天的天气趋势
                            JSONArray futureWeather = result.getJSONArray("future");
                            final List<WeatherFuture> weatherFutures = JSON.parseArray(futureWeather.toJSONString(),
                                    WeatherFuture.class);

                            //4.到界面显示获取的数据
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder builder = new StringBuilder();
                                    builder.append(city).append(weather)
                                            .append("\n\n").append("5天的天气趋势：");
                                    for(WeatherFuture future : weatherFutures) {
                                        builder.append("\n").append(future);
                                    }
                                    tvResult2.setText(builder.toString());
                                }
                            });
                        }
                    } else {
                        Log.d("ThreeDataActivity", response.message());
                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void getOilPrice(final String province){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String res = new ShowApiRequest(OIL_URL,APP_ID,KEY)
                        .addTextPara("prov",province)
                        .post();
                tvResult2.post(new Runnable() {
                    @Override
                    public void run() {
                        OilPriceRes priceRes = JSON.parseObject(res, OilPriceRes.class);
                        if(priceRes != null && priceRes.getResCode() == 0) {
                            OilPriceBody body = priceRes.getResBody();
                            if(body != null && body.getRetCode() == 0) {
                                List<OilPrice> prices = body.getList();
                                tvResult2.setText(prices.get(0).toString());
                            }
                        }
                    }
                });
            }
        }).start();
    }
}
