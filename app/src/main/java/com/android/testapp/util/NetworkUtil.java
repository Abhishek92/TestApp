package com.android.testapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by hp pc on 25-03-2017.
 */

public final class NetworkUtil {

    private NetworkUtil()
    {

    }

    public static boolean isConnectedToInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
