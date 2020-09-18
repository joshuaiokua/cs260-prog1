package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulting);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String recordedDistance = intent.getStringExtra(MainActivity.RECORDED_DISTANCE);
        ArrayList<String> recordedTransportationOptions = intent.getStringArrayListExtra(MainActivity.TRANSPORT_OPTIONS);

        // Capture the layout's TextView and set the string as its text
        TextView recordedDistanceTextView = findViewById(R.id.recordedDistanceTextView);
        recordedDistanceTextView.setText(String.format("%s %s", recordedDistance, recordedDistanceTextView.getText()));
        // TextView textView2 = findViewById(R.id.textView2);
        /*assert recordedTransportationOptions != null;
        for(int i = 0; i < recordedTransportationOptions.size(); i++) {
            textView2.append(recordedTransportationOptions.get(i));
        }*/

        /*TextView textView3 = findViewById(R.id.textView3);
        String key = recordedTransportationOptions.get(0);
        AbstractMap.SimpleEntry<Double, Integer> keyedValue = transportOptionsMap.get(key);
        textView3.setText(String.valueOf(keyedValue.getKey()));*/

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(recordedTransportationOptions, recordedDistance);
        recyclerView.setAdapter(adapter);

    }
}