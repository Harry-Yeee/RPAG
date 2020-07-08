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

public class viewBudgetMonth extends AppCompatActivity {
    Spinner monthSpinner;
    Button viewBudgetBtn;

    ArrayAdapter<CharSequence> adapter;
    String monthSelected = "Select Month";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget_month);


        monthSpinner = findViewById(R.id.monthSpinner);
        viewBudgetBtn = findViewById(R.id.viewBudgetBtn);

        viewMonth();
        viewBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!monthSelected.equals("Select Month")){
                    Intent viewBudgetIntent = new Intent(getApplicationContext(), viewBudget.class);
                    viewBudgetIntent.putExtra("month", monthSelected);
                    startActivity(viewBudgetIntent);
                }else{
                    Toast.makeText(viewBudgetMonth.this, "Month Not Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void viewMonth(){
        adapter = ArrayAdapter.createFromResource(this,
                R.array.monthSelection, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monthSelected = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(viewBudgetMonth.this, monthSelected + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}