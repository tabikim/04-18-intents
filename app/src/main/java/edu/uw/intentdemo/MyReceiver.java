package edu.uw.intentdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tabz on 4/18/16.
 */
public class MyReceiver extends BroadcastReceiver {

    private static String TAG = "Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "Received "+intent);

        if(intent.getAction() == Intent.ACTION_BATTERY_LOW) {
            Toast.makeText(context, "Battery is low!", Toast.LENGTH_SHORT).show();
        }

        else if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
            Toast.makeText(context, "Power disconnected!", Toast.LENGTH_SHORT).show();
        }

        else if(intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context, "Power connected!", Toast.LENGTH_SHORT).show();
        }
    }

    // Battery change can be done through extended controls settings for emulator
}
