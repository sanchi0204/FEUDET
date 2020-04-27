package com.example.fireapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;

public class App extends Application {

    public static final String CHANNEL_1_ID = "channel 1";
    public static final String CHANNEL_2_ID = "channel 2";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        
        createNotificationChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1= new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2= new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannels(Collections.singletonList(channel1));
            manager.createNotificationChannels(Collections.singletonList(channel2));
        }
    }
}

