package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class manualAdd extends AppCompatActivity {
    EditText itemNameText, itemPriceText;
    Button addDataBtn, viewDataBtn, removeDataBtn;
    Spinner itemMonth, categorySpinner;
    ArrayAdapter<CharSequence> adapter;
    String monthSelected = "None";
    String categorySelected = "None";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add);

        itemNameText = (EditText)findViewById(R.id.itemNameText);
        itemPriceText = (EditText)findViewById(R.id.itemPriceText);
        addDataBtn = (Button)findViewById(R.id.addDataBtn);
        viewDataBtn = (Button)findViewById(R.id.viewDataBtn);
        removeDataBtn = (Button)findViewById(R.id.removeDataBtn);
        itemMonth = findViewById(R.id.itemMonth);
        categorySpinner = findViewById(R.id.categorySpinner);

        addData();
        viewData();
        deleteData();
        selectMonth();
        selectCategory();
    }

    public void addData(){
        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(manualAdd.this);
                dataBaseHelper.updateCategory(categorySelected, Double.parseDouble(itemPriceText.getText().toString()));
                boolean insert = false;
                try {
                    item = new Item(itemNameText.getText().toString(), Double.parseDouble(itemPriceText.getText().toString()),
                                   monthSelected, -1);
                    insert = dataBaseHelper.insertData(item);
                    //Toast.makeText(manualAdd.this, item.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(manualAdd.this, "Check Input Values", Toast.LENGTH_SHORT).show();
                    //item = new Item("error", 0, 0, -1);
                }


                if(insert){
                    Toast.makeText(manualAdd.this, "Data Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(manualAdd.this, "Fail to Add Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewData(){
        viewDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent(getApplicationContext(), viewData.class);
                startActivity(viewIntent);
            }
        });
    }

    public void deleteData(){
        removeDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteIntent = new Intent(getApplicationContext(), deleteData.class);
                startActivity(deleteIntent);
            }
        });
    }

    public void selectMonth() {

        adapter = ArrayAdapter.createFromResource(this,
                R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemMonth.setAdapter(adapter);
        itemMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monthSelected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(manualAdd.this, monthSelected + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void selectCategory(){
        adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(manualAdd.this, categorySelected + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}