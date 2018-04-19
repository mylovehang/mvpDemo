package com.hangdaye.rxretrofit;

/**
 * Created by mylovehang on 2018/4/18.
 */

public interface MainMvpPresent<V extends MainMvpView> {
    void getWeatherInfo(String city);
    void getRxJavaWeatherInfo(String[] citys);
}
