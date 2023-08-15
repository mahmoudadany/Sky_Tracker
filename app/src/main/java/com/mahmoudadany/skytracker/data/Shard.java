package com.mahmoudadany.skytracker.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Shard {
    private static Shard instance = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Shard(Context context) {
        sharedPreferences = context.getSharedPreferences("shard", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public synchronized static Shard getInstance(Context context) {
        if (instance == null) {
            instance = new Shard(context);
        }
        return instance;
    }

    public void addInShard(String country) {
        editor.putString("country", country);
        editor.apply();
    }

    public String getFromShard() {
        return sharedPreferences.getString("country", "cairo");
    }

    public void setId(int id) {
        editor.putInt("id", id);
        editor.apply();
    }

    public int getId() {
        return sharedPreferences.getInt("id", 0);
    }

}
