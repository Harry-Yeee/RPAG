package com.example.rpag;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class setBudget extends AppCompatActivity {
    Spinner categorySP;
    EditText setBudgetText;
    Button setBudgetBtn;

    ArrayAdapter<CharSequence> adapter;
    String categorySelected = "None";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        categorySP = findViewById(R.id.categorySP);
        setBudgetText = (EditText)findViewById(R.id.setBudgetText);
        setBudgetBtn = (Button)findViewById(R.id.setBudgetBtn);
        viewCategory();
        setUpBudget();
    }

    public void viewCategory(){
        adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySP.setAdapter(adapter);

        categorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(setBudget.this, categorySelected + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setUpBudget(){
        setBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(setBudget.this);
                double spent = dataBaseHelper.checkCategory(categorySelected); //check to see if the category already exists in database
                try {
                    category = new Category(categorySelected, Double.parseDouble(setBudgetText.getText().toString()), spent, -1);
                    dataBaseHelper.insertData(category);
                    Toast.makeText(setBudget.this, "Budget Set", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(setBudget.this, "Check Input Values", Toast.LENGTH_SHORT).show();
                    //item = new Item("error", 0, 0, -1);
                }

            }
        });
    }
}