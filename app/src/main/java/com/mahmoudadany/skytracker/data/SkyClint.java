package com.mahmoudadany.skytracker.data;

import com.mahmoudadany.skytracker.pojo.ApiResponse;
import com.mahmoudadany.skytracker.pojo.Current;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkyClint {
    private final String BASE_URL = "https://api.weatherapi.com/v1/";
    private final String API_KEY ="6209245a96a24029b83154237230808";
    private static SkyClint instance;
    private SkyInterface skyInterface;
    private Retrofit retrofit;

    private SkyClint() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        skyInterface = retrofit.create(SkyInterface.class);
    }

    public synchronized static SkyClint getInstance() {
        if (instance == null) {
            instance = new SkyClint();
        }
        return instance;
    }

    public Observable<ApiResponse> getDataFromApi(String country){
        return skyInterface.getDataFromApi(country,"ar",3,"6209245a96a24029b83154237230808");
    }


}
