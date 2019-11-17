package com.example.networkdemo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * "temperature": "7℃~21℃",
 * "weather": "多云",
 * "weather_id": {
 * "fa": "01",
 * "fb": "01"
 * },
 * "wind": "西北风3-5级",
 * "week": "星期三",
 * "city": "南京",
 * "date_y": "2019年11月13日",
 * "dressing_index": "较舒适",
 * "dressing_advice": "建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。",
 * "uv_index": "弱",
 * "comfort_index": "",
 * "wash_index": "不宜",
 * "travel_index": "较适宜",
 * "exercise_index": "较适宜",
 * "drying_index": ""
 */
public class WeatherToday implements Serializable {
    private String temperature;
    private String weather;
    private String wind;
    private String week;
    private String city;

    @JSONField(name = "date_y", format = "yyyy年MM月dd日")
    private Date date;
    @JSONField(name = "dressing_index")
    private String dressingIndex;
    @JSONField(name = "uv_index")
    private String uvIndex;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDressingIndex() {
        return dressingIndex;
    }

    public void setDressingIndex(String dressingIndex) {
        this.dressingIndex = dressingIndex;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    @Override
    public String toString() {
        return city + "今天的天气\n" + temperature + ", " + weather + ", " + wind +
                ", 紫外线" + uvIndex + ", " + dressingIndex;
    }
}