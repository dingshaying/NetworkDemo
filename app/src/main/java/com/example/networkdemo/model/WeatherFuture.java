package com.example.networkdemo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * date : 2019-11-13
 * temperature : 7/21℃
 * weather : 多云
 * wid : {"day":"01","night":"01"}
 * direct : 西北风
 */

public class WeatherFuture implements Serializable {
    @JSONField(format = "yyy-MM-dd")
    private Date date;
    private String temperature;
    private String weather;
    private String direct;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String strDate = sdf.format(date);
        return strDate + "：" + temperature + ", " + weather + ", " + direct;
    }

}
