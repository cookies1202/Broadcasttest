package fh.at.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Log.d("MyReceiver", "Strom angeschlossen!");
        } else if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
            boolean state = intent.getBooleanExtra("state", false);
            Log.d("MyReceiver", "Flugmodus geändert: " + (state ? "AN" : "AUS"));
        }
    }
}
