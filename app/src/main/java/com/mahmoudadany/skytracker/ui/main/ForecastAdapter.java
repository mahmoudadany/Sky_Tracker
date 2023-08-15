package com.mahmoudadany.skytracker.ui.main;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mahmoudadany.skytracker.R;
import com.mahmoudadany.skytracker.pojo.ForecastDayModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    List<ForecastDayModel> forecastDayModels;

    public void setForecastDayModels(List<ForecastDayModel> forecastDayModels) {
        this.forecastDayModels = forecastDayModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dataitem,null,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position==forecastDayModels.size()-1){
            holder.view.setVisibility(View.GONE);
        }
        holder.date.setText(forecastDayModels.get(position).getDate());
        holder.max.setText((int)forecastDayModels.get(position).getDay().getMaxtemp_c()+"°c");
        holder.min.setText((int)forecastDayModels.get(position).getDay().getMintemp_c()+"°c");
        Picasso.get()
                .load(Uri.parse("https:"+forecastDayModels.get(position).getDay().getCondition().getIcon()))
                .into(holder.icon);


    }

    @Override
    public int getItemCount() {
        if (forecastDayModels==null)return 0;
        return forecastDayModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,max,min;
        ImageView icon;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.dataitem_tv_date);
            max=itemView.findViewById(R.id.dataitem_tv_max);
            min=itemView.findViewById(R.id.dataitem_tv_min);
            icon=itemView.findViewById(R.id.dataitem_iv_weathericon);
            view=itemView.findViewById(R.id.dataitem_view);
        }
    }
}
