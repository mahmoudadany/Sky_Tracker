package com.mahmoudadany.skytracker.data;

import com.mahmoudadany.skytracker.pojo.ApiResponse;
import com.mahmoudadany.skytracker.pojo.Current;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SkyInterface {

    @GET("forecast.json")
    Observable<ApiResponse> getDataFromApi(
            @Query("q")String q,
            @Query("lang")String aqi,
            @Query("days")int days,
            @Query("key")String key
    );
}
