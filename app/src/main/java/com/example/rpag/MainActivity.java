package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //jayesh

        ImageButton reportButton = (ImageButton) findViewById(R.id.reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(getApplicationContext(), report.class);
                //reportIntent.putExtra("com.example.rpag", "Different Reports!");
                startActivity(reportIntent);
            }
        });
    }
}