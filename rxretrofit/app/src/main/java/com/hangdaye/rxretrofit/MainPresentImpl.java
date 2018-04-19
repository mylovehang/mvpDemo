package com.hangdaye.rxretrofit;

/**
 * Created by mylovehang on 2018/4/18.
 */

public class MainPresentImpl<V extends MainMvpView> implements MainMvpPresent {
    private MainMvpView mMainMvpView;
    private MainMvpData mMainMvpData;

    public MainPresentImpl(MainMvpView view) {
        mMainMvpView = view;
        mMainMvpData = new MainDataImpl();
    }

    @Override
    public void getWeatherInfo(String city) {
        mMainMvpData.getWeatherInfo(city, new MainMvpData.WeatherInfoCallBack() {
            @Override
            public void onLoadSuccess(WeatherData weatherData) {
                mMainMvpView.showWeatherInfo(weatherData.toString());
            }

            @Override
            public void onLoadFailed(String error) {
                mMainMvpView.showErrorInfo(error);
            }
        });
    }

    @Override
    public void getRxJavaWeatherInfo(String[] citys) {
        mMainMvpData.getRxJavaWeatherInfo(citys, new MainMvpData.WeatherInfoCallBack() {
            @Override
            public void onLoadSuccess(WeatherData weatherData) {
                mMainMvpView.showRxJavaWeatherInfo(weatherData.toString());
            }

            @Override
            public void onLoadFailed(String error) {
                mMainMvpView.showErrorInfo(error);
            }
        });
    }
}
