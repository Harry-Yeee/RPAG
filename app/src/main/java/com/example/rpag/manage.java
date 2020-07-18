package com.example.rpag;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class manage extends AppCompatActivity {

    Spinner monthSpinner, categorySpinner;
    Button viewItems;
    ArrayAdapter<CharSequence> adapter;
    String monthSelected = "Select Month";
    String categorySelected = "Select Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        monthSpinner = findViewById(R.id.monthSpinner);
        categorySpinner = findViewById(R.id.categorySpinner);
        viewItems = (Button)findViewById(R.id.viewItems);
        selectMonth();
        selectCategory();
        viewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(manage.this);
                List<Item> itemList = dataBaseHelper.viewData();
                StringBuffer buffer = new StringBuffer();


              //  ArrayAdapter itemArrayAdapter = new ArrayAdapter<Item>(manage.this,android.R.layout.simple_expandable_list_item_1, itemList);
                try{

                    if(!monthSelected.equals("Select Month") && !categorySelected.equals("Select Category")){
                        for(Item item: itemList){
                            if(item.getItemCategory().equals(categorySelected)&&item.getItemDate().equals(monthSelected)) {
                                buffer.append(item + "\n\n");
                            }
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(manage.this);
                        builder.setCancelable(true);
                        builder.setTitle("Items");
                        builder.setMessage(buffer.toString());
                        builder.show();


                    }else{
                        Toast.makeText(manage.this, "Select both Month & Category", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void selectMonth() {

        adapter = ArrayAdapter.createFromResource(this,
                R.array.monthSelection, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                R.array.categoriesSelection, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
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