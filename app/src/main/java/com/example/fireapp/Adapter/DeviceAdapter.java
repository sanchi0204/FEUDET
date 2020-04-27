package com.example.fireapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fireapp.Model.DeviceDetails;
import com.example.fireapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    Context context;
    List<DeviceDetails>deviceList;
    DatabaseReference deviceRef=FirebaseDatabase.getInstance().getReference();

    public DeviceAdapter(Context context, List<DeviceDetails> deviceList) {
        this.context = context;
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_home, parent, false);
        DeviceViewHolder deviceViewHolder = new DeviceViewHolder(view);
        return deviceViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView city,state,country,lat,lon;


        public DeviceViewHolder(@NonNull View itemView) {

            super(itemView);
            city=itemView.findViewById(R.id.city);
            state=itemView.findViewById(R.id.state);
            country=itemView.findViewById(R.id.country);
            lat=itemView.findViewById(R.id.latitude);
            lon=itemView.findViewById(R.id.longitude);
        }
    }
}
