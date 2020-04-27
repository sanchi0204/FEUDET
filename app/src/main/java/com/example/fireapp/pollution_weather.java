package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class pollution_weather extends AppCompatActivity {

    TextView aqius,aqicn,ts;
    String aqiusS,aqicnS,tsS;
    double lat,lon;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution_weather);
        aqius=findViewById(R.id.aqius);
        aqicn=findViewById(R.id.aqicn);
        ts=findViewById(R.id.time_stamp);

        findDetails();






    }

    private void findDetails() {
        lat=28.7041;
        lon=77.1025;

        String url="https://api.airvisual.com/v2/nearest_city?"+lat+"&"+lon+"&key=2b55ab05-d20b-4db2-b730-a1e072d323bc" ;

        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject dataJson=response.getJSONObject("data");
                    JSONObject current=dataJson.getJSONObject("current");
                    JSONObject pollution=current.getJSONObject("pollution");
                    tsS=pollution.getString("ts");
                    aqicnS=String.valueOf(pollution.getDouble("aqicn"));
                    aqiusS=String.valueOf(pollution.getDouble("aqius"));
                    ts.setText(tsS);
                    aqius.setText(aqiusS);
                    aqicn.setText(aqicnS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(pollution_weather.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }
        );

        RequestQueue que= Volley.newRequestQueue(this);
        que.add(jor);

    }
    public void Weather(View view) {
        startActivity(new Intent(pollution_weather.this,Weather.class));
    }

}
