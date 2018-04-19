package com.hangdaye.rxretrofit;

/**
 * Created by mylovehang on 2018/4/18.
 */

public interface MainMvpView {
    void showWeatherInfo(String info);
    void showErrorInfo(String error);
    void showRxJavaWeatherInfo(String info);
}
