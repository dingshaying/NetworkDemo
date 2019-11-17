package com.example.networkdemo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * "temperature": "7℃~21℃",
 * "weather": "多云",
 * "weather_id": {
 *     "fa": "01",
 *     "fb": "01"
 * },
 * "wind": "西北风3-5级",
 * "week": "星期三",
 * "date": "20191113"
 */
public class WeatherDay implements Serializable {
    private String temperature;
    private String weather;
    private String wind;
    private String week;

    @JSONField(format = "yyyyMMdd")
    private Date date;

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

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String strDate = sdf.format(date);
        return strDate + week +"：" + temperature + ", " + weather + ", " + wind;
    }
}