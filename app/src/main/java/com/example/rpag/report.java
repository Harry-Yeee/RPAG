package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button pieChartBtn = (Button)findViewById(R.id.pieChartBtn);
        pieChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pieChartIntent = new Intent(getApplicationContext(), pieChart.class);
                startActivity(pieChartIntent);
            }
        });

        Button barChartBtn = (Button)findViewById(R.id.barChartBtn);
        barChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barChartIntent = new Intent(getApplicationContext(), barChart.class);
                startActivity(barChartIntent);
            }
        });

        // in this example, a LineChart is initialized from xml
        //LineChart chart = (LineChart) findViewById(R.id.chart);

    }
}