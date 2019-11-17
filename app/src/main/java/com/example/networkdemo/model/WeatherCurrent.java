package com.example.networkdemo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * "temp": "15",
 * "wind_direction": "西北风",
 * "wind_strength": "4级",
 * "humidity": "79%",
 * "time": "08:39"
 */

public class WeatherCurrent  implements Serializable {
    private String temp;

    @JSONField(name = "wind_direction")
    private String windDirection;

    @JSONField(name = "wind_strength")
    private String windStrength;

    private String humidity;

    @JSONField(format = "hh:mm")
    private Date time;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindStrength() {
        return windStrength;
    }

    public void setWindStrength(String windStrength) {
        this.windStrength = windStrength;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        String strTime = sdf.format(time);
        return strTime + "预报的天气情况：" + temp + "度, " +
                windDirection + windStrength + ", 湿度" + humidity;
    }
}
