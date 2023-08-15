package com.mahmoudadany.skytracker.ui.main;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mahmoudadany.skytracker.R;
import com.mahmoudadany.skytracker.data.Internet;
import com.mahmoudadany.skytracker.data.Shard;
import com.mahmoudadany.skytracker.databinding.ActivityMainBinding;
import com.mahmoudadany.skytracker.pojo.ApiResponse;
import com.mahmoudadany.skytracker.pojo.Current;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
SkyViewModle viewModle;
ActivityMainBinding binding;
ImageView icon;
RecyclerView recyclerView;
ForecastAdapter adapter;
Shard shard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        boolean b=Internet.intenetConnection(getBaseContext());
        if(!b){
            Toast.makeText(this, "Internet disconnection", Toast.LENGTH_SHORT).show();
        }
        inflate();
        binding.mainSpCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean b=Internet.intenetConnection(getBaseContext());
                if(!b){
                    Toast.makeText(getBaseContext(), "Internet disconnection", Toast.LENGTH_SHORT).show();
                }
                String country=parent.getItemAtPosition(position).toString();
                viewModle.setLiveData(country);
                shard.addInShard(country);
                shard.setId(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewModle.liveData.observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                presentWeatherData(apiResponse);
                adapter.setForecastDayModels(apiResponse.getForecast().getForecastday());
                adapter.notifyDataSetChanged();

            }
        });

    }

    private void inflate() {
        shard=Shard.getInstance(getBaseContext());
        viewModle= ViewModelProviders.of(this).get(SkyViewModle.class);
        viewModle.setLiveData(shard.getFromShard());
        recyclerView=findViewById(R.id.main_rv_date);
        adapter=new ForecastAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        binding.mainSpCountry.setSelection(shard.getId());

    }

    public void presentWeatherData(ApiResponse apiResponse){
        Log.d(TAG, "adany onChanged: "+apiResponse.getCurrent().getCondition().getIcon());
        binding.mianTvTemperature.setText((int)apiResponse.getCurrent().getTemp_c()+"°c");
        binding.mianTvWindKph.setText((int)apiResponse.getCurrent().getWind_kph()+" km\\h");
        binding.mianTvHumidity.setText(apiResponse.getCurrent().getHumidity()+"%");
        binding.mainTvMax.setText((int)apiResponse.getForecast().getForecastday().get(0).getDay().getMaxtemp_c()+"°c");
        binding.mainTvMin.setText((int)apiResponse.getForecast().getForecastday().get(0).getDay().getMintemp_c()+"°c");
        binding.mianTvFeelslike.setText((int)apiResponse.getCurrent().getFeelslike_c()+"%");
        binding.mianTvState.setText(apiResponse.getCurrent().getCondition().getText());



        Uri uri=Uri.parse("https:"+apiResponse.getCurrent().getCondition().getIcon());
        Picasso.get()
                .load(uri)
                .into(binding.mainIvWeather);
    }
}
