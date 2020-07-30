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

import java.io.Serializable;

public class manualAdd extends AppCompatActivity implements Serializable {
    EditText itemNameText, itemPriceText;
    Button addDataBtn, viewDataBtn, removeDataBtn;
    Spinner itemMonth, categorySpinner;
    ArrayAdapter<CharSequence> adapter;
    Item itemUpdate = null;
    String monthSelected = "None";
    String categorySelected = "None";
    int idUpdate = 0;
    String nameUpdate = "None";
    double priceUpdate = 0;
    String dateUpdate = "None";
    String categoryUpdate = "None";
    boolean update = false;

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

        if(getIntent().hasExtra("itemName")){
             itemNameText.setText(getIntent().getStringExtra("itemName"));
        }
        if(getIntent().hasExtra("itemPrice")){
            itemPriceText.setText(getIntent().getStringExtra("itemPrice"));
        }


        if(getIntent().hasExtra("update")){
            update = true;
            idUpdate = getIntent().getIntExtra("idUpdate", 0);

            nameUpdate = getIntent().getStringExtra("nameUpdate");
            itemNameText.setText(nameUpdate);

            priceUpdate = getIntent().getDoubleExtra("priceUpdate", 0);
            itemPriceText.setText(String.valueOf(priceUpdate));

            dateUpdate = getIntent().getStringExtra("dateUpdate");

            categoryUpdate = getIntent().getStringExtra("categoryUpdate");

        }


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
                if(!update) {
                    boolean insert = false;
                    try {
                        dataBaseHelper.updateCategory(categorySelected, Double.parseDouble(itemPriceText.getText().toString()),
                                monthSelected);
                        item = new Item(itemNameText.getText().toString(), Double.parseDouble(itemPriceText.getText().toString()),
                                monthSelected, categorySelected, -1);
                        insert = dataBaseHelper.insertData(item);
                        //Toast.makeText(manualAdd.this, item.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(manualAdd.this, "Check Input Values", Toast.LENGTH_SHORT).show();
                        //item = new Item("error", 0, 0, -1);
                    }


                    if (insert) {
                        Toast.makeText(manualAdd.this, "Data Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(manualAdd.this, "Fail to Add Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    try{
                        itemUpdate = new Item(itemNameText.getText().toString(), Double.parseDouble(itemPriceText.getText().toString()),
                                monthSelected, categorySelected, idUpdate);
                        dataBaseHelper.updateData(itemUpdate);
                        Double updatedPrice = Double.parseDouble(itemPriceText.getText().toString());
                        Double itemDifference = 0.0;
                        if(updatedPrice > priceUpdate){
                            itemDifference = updatedPrice - priceUpdate;
                            dataBaseHelper.updateCategory(categorySelected, itemDifference, monthSelected);
                        }else{
                            itemDifference = priceUpdate - updatedPrice;
                            dataBaseHelper.updateCategory(categorySelected, -itemDifference, monthSelected);
                        }
                        Toast.makeText(manualAdd.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(manualAdd.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                    }

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

            int spinnerPosition = adapter.getPosition(dateUpdate);
            itemMonth.setSelection(spinnerPosition);

        itemMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monthSelected = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(manualAdd.this, monthSelected + " Selected", Toast.LENGTH_SHORT).show();
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

        int spinnerPosition = adapter.getPosition(categoryUpdate);
        categorySpinner.setSelection(spinnerPosition);

        if(getIntent().hasExtra("itemCategory")){
            String category = getIntent().getStringExtra("itemCategory");
            spinnerPosition = adapter.getPosition(category);
            categorySpinner.setSelection(spinnerPosition);
        }


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(manualAdd.this, categorySelected + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}