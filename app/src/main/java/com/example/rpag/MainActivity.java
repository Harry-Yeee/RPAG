package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //jayesh

        Button reportButton = (Button) findViewById(R.id.reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(getApplicationContext(), report.class);
                //reportIntent.putExtra("com.example.rpag", "Different Reports!");
                startActivity(reportIntent);
            }
        });

        Button manualAddBtn = (Button)findViewById(R.id.manualAddBtn);
        manualAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manualAddIntent = new Intent(getApplicationContext(), manualAdd.class);
                startActivity(manualAddIntent);
            }
        });

        Button setBudgetBtn = (Button)findViewById(R.id.setBudgetBtn);
        setBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setBudgetIntent = new Intent(getApplicationContext(), setBudget.class);
                startActivity(setBudgetIntent);
            }
        });

        Button viewBudgetBtn = (Button)findViewById(R.id.viewBudgetBtn);
        viewBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewBudgetIntent = new Intent(getApplicationContext(), viewBudgetMonth.class);
                startActivity(viewBudgetIntent);
            }
        });


    }
}