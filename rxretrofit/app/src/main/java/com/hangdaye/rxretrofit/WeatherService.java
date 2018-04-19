package com.hangdaye.rxretrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by mylovehang on 17/7/20.
 */

public interface WeatherService {

    @GET("weather")
    Call<WeatherData> getWeatherData(@Query("q") String city, @Query("mode") String mode, @Query("APPID") String APPID);

    @GET("weather")
    Observable<WeatherData> getRxJavaWeatherData(@Query("q") String city, @Query("mode") String mode, @Query("APPID") String APPID);
}
