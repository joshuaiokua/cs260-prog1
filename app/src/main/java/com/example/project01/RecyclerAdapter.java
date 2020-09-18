package com.example.project01;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<String> arrayList;
    private String recordedString;
    int[] images = {R.drawable.blob_icon_1, R.drawable.blob_icon_2, R.drawable.blob_icon_3,
            R.drawable.blob_icon_4, R.drawable.blob_icon_5, R.drawable.blob_icon_6, R.drawable.blob_icon_7};

    public RecyclerAdapter(ArrayList<String> arrayList, String recordedString) {
        this.arrayList = arrayList;
        this.recordedString = recordedString;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_row_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String transportName = arrayList.get(position);
        AbstractMap.SimpleEntry transportValues = transportOptionsMap.get(transportName);
        Double transportMileage = (Double) transportValues.getKey();
        Integer transportRange = (Integer) transportValues.getValue();

        Random randomizer = new Random();

        holder.transportName.setText(transportName);
        holder.rowBlob.setImageResource(images[randomizer.nextInt(images.length)]);
        getTime(recordedString, transportMileage, transportRange,
                holder.transportTime, holder.transportMinutes);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView transportName, transportTime, transportMinutes;
        ImageView rowBlob;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            transportName = itemView.findViewById(R.id.transportName);
            transportTime = itemView.findViewById(R.id.transportTime);
            transportMinutes = itemView.findViewById(R.id.transportMinutes);
            rowBlob = itemView.findViewById(R.id.rowBlob);
        }
    }

    private void getTime(String inputDistance, double vehicleMileage, Integer vehicleRange,
                         TextView timeOutputField, TextView minuteOutputField) {
        int inputDistanceNumber = Integer.parseInt(inputDistance);
        if(inputDistanceNumber > vehicleRange) {
            timeOutputField.setText("No");
            minuteOutputField.setText("Range");
        } else {
            String result = String.valueOf( Math.round(inputDistanceNumber / (vehicleMileage / 60)) );
            timeOutputField.setText(result);
        }
    }

    // HashMap of transportation options. Keyed by name with both speed and range values
    public static final Map<String, AbstractMap.SimpleEntry<Double, Integer>> transportOptionsMap = new HashMap<>();
    static {
        transportOptionsMap.put("Foot (Walking)",  new AbstractMap.SimpleEntry(3.1, 30));
        transportOptionsMap.put("Boosted Mini S",  new AbstractMap.SimpleEntry(18.0, 7));
        transportOptionsMap.put("Evolve Bamboo GTR",  new AbstractMap.SimpleEntry(24.0, 31));
        transportOptionsMap.put("Segway Ninebot S+",  new AbstractMap.SimpleEntry(12.0, 22));
        transportOptionsMap.put("Hovertrax Hoverboard",  new AbstractMap.SimpleEntry(9.0, 6));
        transportOptionsMap.put("OneWheel XR",  new AbstractMap.SimpleEntry(19.0, 18));
        transportOptionsMap.put("Mototec Skateboard",  new AbstractMap.SimpleEntry(22.0, 10));
        transportOptionsMap.put("Segway Ninebot S",  new AbstractMap.SimpleEntry(10.0, 13));
        transportOptionsMap.put("Razor Scooter",  new AbstractMap.SimpleEntry(18.0, 15));
        transportOptionsMap.put("GeoBlade 500",  new AbstractMap.SimpleEntry(15.0, 8));
    }
}
