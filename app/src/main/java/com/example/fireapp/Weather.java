package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fireapp.Model.DeviceDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

import static com.example.fireapp.App.CHANNEL_1_ID;

public class Weather extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    private String notif_title, notif_msg;
    TextView temp, airpressure, humidity, windSpeed, windDirection, ts;
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    DeviceDetails Dd;
    String tempS,humidityS;
    CardView cardView;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

         notificationManagerCompat = NotificationManagerCompat.from(this);
        temp = findViewById(R.id.temp);
        airpressure = findViewById(R.id.air_pressure);
        humidity = findViewById(R.id.humidity);
        windSpeed = findViewById(R.id.wind_speed);
        windDirection = findViewById(R.id.wind_direction);
        ts = findViewById(R.id.time_stamp);
        cardView=findViewById(R.id.globe);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Weather.this,FireMap.class));
//            }
//        });
        CardView Test = findViewById(R.id.Test);
        Dd=new DeviceDetails();



        RetriveData();
        findData();
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Weather.this, pollution_weather.class));
            }
        });
    }

    private void RetriveData() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                tempS=dataSnapshot.child("DHT22-1").child("Temperature").getValue(String.class);
                SendNotifications();
                humidityS=dataSnapshot.child("DHT22-1").child("Humidity").getValue(String.class);
                temp.setText(tempS);
                humidity.setText(humidityS);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });

    }

    public void SendNotifications() {
    String t;

        t=tempS.substring(0,tempS.length()-2);
        if(Float.parseFloat((t))>20)
        {
            String msg= getString(R.string.text);
            String title= getString(R.string.app_name);

            Intent activityIntent = new Intent(this, Weather.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent,0);
//        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
//        broadcastIntent.putExtra("toastMessage", msg);
//        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.alert)
                    .setContentTitle(title)
                    .setColor(Color.BLUE)
                    .setContentText(msg)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getString(R.string.text))
                            .setBigContentTitle(title)
                            .setSummaryText(getString(R.string.app_name)))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
//                    .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                    .build();

            notificationManagerCompat.notify(1,notification);
        }
    }

    private void findData() {
        String url = "https://api.airvisual.com/v2/nearest_city?\"+lat+\"&\"+lon+\"&key=2b55ab05-d20b-4db2-b730-a1e072d323bc";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONObject current = data.getJSONObject("current");
                    JSONObject weather = current.getJSONObject("weather");

                    //temp.setText(String.valueOf(weather.getDouble("tp")));
                    airpressure.setText(String.valueOf(weather.getDouble("pr")));
                    //humidity.setText(String.valueOf(weather.getDouble("hu")));
                    windSpeed.setText(String.valueOf(weather.getDouble("ws")));
                    windDirection.setText(String.valueOf(weather.getDouble("wd")));
                    ts.setText(weather.getString("ts"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Weather.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue que = Volley.newRequestQueue(this);
        que.add(jor);
    }



}