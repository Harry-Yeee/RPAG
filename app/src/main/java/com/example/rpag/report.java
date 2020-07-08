package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class report extends AppCompatActivity {
    Spinner categorySpinner;
    ArrayAdapter<CharSequence> adapter;
    String month = "Select Month";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button pieChartBtn = (Button)findViewById(R.id.pieChartBtn);
        pieChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pieChartIntent = new Intent(getApplicationContext(), pieChart.class);

                if(month.equals("Select Month")){
                    Toast.makeText(report.this, "Month Not Selected", Toast.LENGTH_SHORT).show();
                }else{
                    pieChartIntent.putExtra("month", month);
                    startActivity(pieChartIntent);
                }

            }
        });

        Button barChartBtn = (Button)findViewById(R.id.barChartBtn);
        barChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barChartIntent = new Intent(getApplicationContext(), barChart.class);

                if(month.equals("Select Month")){
                    Toast.makeText(report.this, "Month Not Selected", Toast.LENGTH_SHORT).show();
                }else{
                    barChartIntent.putExtra("month", month);
                    startActivity(barChartIntent);
                }
            }
        });

        categorySpinner = findViewById(R.id.categorySpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.monthSelection, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(report.this, month + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // in this example, a LineChart is initialized from xml
        //LineChart chart = (LineChart) findViewById(R.id.chart);

    }
}