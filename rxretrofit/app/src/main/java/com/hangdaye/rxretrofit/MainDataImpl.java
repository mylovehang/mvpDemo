package com.hangdaye.rxretrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mylovehang on 2018/4/18.
 */

public class MainDataImpl implements MainMvpData {
    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/";
    private WeatherService mWeatherService;

    public MainDataImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mWeatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public void getWeatherInfo(String city, final WeatherInfoCallBack callBack) {



        mWeatherService.getWeatherData(city, "json", "6c113432fd84a6e28268af291821db16").enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
//                mTvResult.setText(response.body().toString());
                callBack.onLoadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
//                mTvResult.setText(t.toString());
                callBack.onLoadFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getRxJavaWeatherInfo(String[] citys, final WeatherInfoCallBack callBack) {
        Observable.from(citys)
                .flatMap(new Func1<String, Observable<WeatherData>>() {
                    @Override
                    public Observable<WeatherData> call(String city) {
                        return mWeatherService.getRxJavaWeatherData(city, "json", "6c113432fd84a6e28268af291821db16");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
//                        mTvResult.setText(mTvResult.getText() + weatherData.toString());
                        callBack.onLoadSuccess(weatherData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
//                        Toast.makeText(MainActivity.this, "服务器异常", Toast.LENGTH_SHORT).show();
                        callBack.onLoadFailed(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
//                        Toast.makeText(MainActivity.this,"请求完毕",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
