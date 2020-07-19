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

import java.util.List;

public class setBudget extends AppCompatActivity {
    Spinner categorySP, monthSP;
    EditText setBudgetText;
    Button setBudgetBtn;

    ArrayAdapter<CharSequence> adapter;
    String categorySelected = "None";
    String monthSelected = "Select Month";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        categorySP = findViewById(R.id.categorySP);
        monthSP = findViewById(R.id.monthSP);
        setBudgetText = (EditText)findViewById(R.id.setBudgetText);
        setBudgetBtn = (Button)findViewById(R.id.setBudgetBtn);
        viewCategory();
        viewMonth();
        setUpBudget();
    }

    public void viewCategory(){
        adapter = ArrayAdapter.createFromResource(this,
                R.array.categoriesAndBudget, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySP.setAdapter(adapter);

        categorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(setBudget.this, categorySelected + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void viewMonth(){
        adapter = ArrayAdapter.createFromResource(this,
                R.array.monthSelection, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSP.setAdapter(adapter);

        monthSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monthSelected = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(setBudget.this, monthSelected + " Selected", Toast.LENGTH_SHORT).show();
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
                try {
                    if (!monthSelected.equals("Select Month")) {
                        Category category;
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(setBudget.this);
                        Category temp = dataBaseHelper.checkCategory(categorySelected, monthSelected); //check to see if the category already exists in database
                        double spent = 0.0;
                        double remaining = Double.parseDouble(setBudgetText.getText().toString());
                        double budget = 0.0;
                        if (temp != null) {
                            spent = temp.getCategorySpent();
                            remaining = Double.parseDouble(setBudgetText.getText().toString()) - spent;
                        }
                        try {
                            if (categorySelected.equals("Total Budget")) {
                                double categorySpent = 0.0;
                                List<Category> categoryList = dataBaseHelper.viewCategoryData("", monthSelected);
                                for (int i = 0; i < categoryList.size(); i++) {
                                    categorySpent += categoryList.get(i).getCategorySpent();
                                }
                                category = new Category(categorySelected, Double.parseDouble(setBudgetText.getText().toString()), categorySpent,
                                        Double.parseDouble(setBudgetText.getText().toString()) - categorySpent,
                                        monthSelected, -1);
                            } else {

                                category = new Category(categorySelected, Double.parseDouble(setBudgetText.getText().toString()), spent, remaining,
                                        monthSelected, -1);
                            }
                            dataBaseHelper.insertData(category);
                            Toast.makeText(setBudget.this, "Budget Set", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(setBudget.this, "Check Input Values", Toast.LENGTH_SHORT).show();
                            //item = new Item("error", 0, 0, -1);
                        }
                    } else {
                        Toast.makeText(setBudget.this, "Select Month", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(setBudget.this, "Specify Budget Amount", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}