package com.mahmoudadany.skytracker.ui.main;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mahmoudadany.skytracker.data.SkyClint;
import com.mahmoudadany.skytracker.pojo.ApiResponse;
import com.mahmoudadany.skytracker.pojo.Current;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SkyViewModle extends ViewModel {
    MutableLiveData<ApiResponse> liveData=new MutableLiveData<>();

    void setLiveData(String country){
        SkyClint clint=SkyClint.getInstance();
        clint.getDataFromApi(country)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ApiResponse apiResponse) {
                        liveData.setValue(apiResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "mahmoudadany onError: "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
