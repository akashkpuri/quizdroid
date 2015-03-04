package edu.washington.akpuri.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Akash on 3/2/2015.
 */
public class DownloadManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ActionBar", "Inside of DownloadManager");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String url = preferences.getString("prefURL", "www.google.com");
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
    }
}
