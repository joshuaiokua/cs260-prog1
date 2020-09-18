package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    protected Intent runIntent;
    public static final String RECORDED_DISTANCE = "0";
    public static final String TRANSPORT_OPTIONS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputDistanceText = findViewById(R.id.inputDistance);
        final ChipGroup chipGroup = findViewById(R.id.chipGroup);

        Button goButton = findViewById(R.id.goButton);

        runIntent = new Intent(this, ResultingActivity.class);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sends Distance Input to ResultingActivity
                String inputDistanceString = inputDistanceText.getText().toString();
                runIntent.putExtra(RECORDED_DISTANCE, inputDistanceString);

                // Sends Transportation Option Choices to ResultingActivity
                ArrayList<String> checkedTransportList = new ArrayList<String>();
                List<Integer> ids = chipGroup.getCheckedChipIds();
                for (Integer id:ids){
                    Chip chip = chipGroup.findViewById(id);
                    checkedTransportList.add((String) chip.getText());
                }
                runIntent.putStringArrayListExtra(TRANSPORT_OPTIONS, checkedTransportList);

                //Begins runIntent
                startActivity(runIntent);
            }
        });

    }
}