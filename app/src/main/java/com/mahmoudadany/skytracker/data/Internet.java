package com.mahmoudadany.skytracker.data;

import android.content.Context;
import android.net.ConnectivityManager;

public class Internet {

    public static boolean intenetConnection(Context context){
        ConnectivityManager manager=(ConnectivityManager)context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE );
        return manager.getActiveNetworkInfo()!=null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
