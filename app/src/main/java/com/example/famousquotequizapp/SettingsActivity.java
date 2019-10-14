package com.example.famousquotequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        aSwitch = findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                 if (isChecked == true) {
                     Intent intentMultiple = new Intent(SettingsActivity.this, MultipleQuizActivity.class);
                     startActivity(intentMultiple);
                 } else {
                     Intent intentBinary = new Intent(SettingsActivity.this, BinaryQuizActivity.class);
                     startActivity(intentBinary);
                 }
            }
        });
    }
}
