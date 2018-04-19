package com.hangdaye.rxretrofit;

/**
 * Created by mylovehang on 2018/4/18.
 */

public interface MainMvpData {
    void getWeatherInfo(String city,WeatherInfoCallBack callBack);

    void getRxJavaWeatherInfo(String[] citys,WeatherInfoCallBack callBack);

    interface WeatherInfoCallBack{
        void onLoadSuccess(WeatherData weatherData);
        void onLoadFailed(String error);
    }
}
