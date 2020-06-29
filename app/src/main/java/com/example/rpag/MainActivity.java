package com.example.rpag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Jackie Handsome
        configureSettingsButton();
        configureManageButton();
    }

    private void configureSettingsButton()
    {
        Button settingsButton = (Button) findViewById(R.id.settingsWrenchButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }

    private void configureManageButton()
    {
        Button manageButton = (Button) findViewById(R.id.manageButton);
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, manageActivity.class));
            }
        });
    }
}