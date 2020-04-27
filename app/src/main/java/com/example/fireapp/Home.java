package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.fireapp.Adapter.DeviceAdapter;
import com.example.fireapp.Model.DeviceDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    DeviceAdapter adapter;
    DatabaseReference ref1=FirebaseDatabase.getInstance().getReference();
    TextView buy;
    List<DeviceDetails> deviceList;
    String latitude,longitude;

   TextView lati,loni,city,state,country;
   double lat,lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);


//        recyclerView=(RecyclerView)findViewById(R.id.recycler_location);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        deviceList =new ArrayList<>();
//        ref1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//             latitude=String.valueOf(dataSnapshot.child("devices").child("DHT22-1").child("Latitude").getValue());
//                adapter=new DeviceAdapter(Home.this,deviceList);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        lati=findViewById(R.id.latitude);
        loni=findViewById(R.id.longitude);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        country=findViewById(R.id.country);

        findData();
    }

    private void findData() {

        lat=28.7041;
        lon=77.1025;
////
////
       String url="https://api.airvisual.com/v2/nearest_city?"+lat+"&"+lon+"&key=2b55ab05-d20b-4db2-b730-a1e072d323bc" ;

        JsonObjectRequest jor =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject data=response.getJSONObject("data");
                    city.setText(data.getString("city"));
                    state.setText(data.getString("state"));
                    country.setText(data.getString("country"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
       }, new Response.ErrorListener() {
           @Override
         public void onErrorResponse(VolleyError error) {
               Toast.makeText(Home.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }
       );
        lati.setText(String.valueOf(lat));
        loni.setText(String.valueOf(lon));
        RequestQueue que= Volley.newRequestQueue(this);
        que.add(jor);
    }
    public void intent(View view)
    {
        startActivity(new Intent(Home.this,FireMap.class));
    }
}
