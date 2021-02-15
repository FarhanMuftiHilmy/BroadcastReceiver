package com.rechit.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    private CustomReceiver customReceiver = new CustomReceiver();
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID+".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        this.registerReceiver(customReceiver, filter);

        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(customReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        customBroadcastIntent.putExtra("DATA", "Data Broadcast");
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}