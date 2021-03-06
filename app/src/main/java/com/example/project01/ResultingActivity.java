package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

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
        if (recordedDistance != null && recordedDistance.isEmpty()) {
            recordedDistance = "0";
        }
        ArrayList<String> recordedTransportationOptions = intent.getStringArrayListExtra(MainActivity.TRANSPORT_OPTIONS);

        // Capture the layout's TextView and set the string as its text
        TextView recordedDistanceTextView = findViewById(R.id.recordedDistanceTextView);
        recordedDistanceTextView.setText(String.format("%s %s", recordedDistance, recordedDistanceTextView.getText()));

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(recordedTransportationOptions, recordedDistance);
        recyclerView.setAdapter(adapter);

    }
}