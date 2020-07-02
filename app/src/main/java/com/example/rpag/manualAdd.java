package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class manualAdd extends AppCompatActivity {
    EditText itemNameText, itemPriceText, itemDateText;
    Button addDataBtn, viewDataBtn, removeDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add);

        itemNameText = (EditText)findViewById(R.id.itemNameText);
        itemPriceText = (EditText)findViewById(R.id.itemPriceText);
        itemDateText = (EditText)findViewById(R.id.itemDateText);
        addDataBtn = (Button)findViewById(R.id.addDataBtn);
        viewDataBtn = (Button)findViewById(R.id.viewDataBtn);
        removeDataBtn = (Button)findViewById(R.id.removeDataBtn);
        addData();
        viewData();
        deleteData();
    }

    public void addData(){
        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item;
                try {
                    item = new Item(itemNameText.getText().toString(), Double.parseDouble(itemPriceText.getText().toString()),
                                   Integer.parseInt(itemDateText.getText().toString()), -1);
                    //Toast.makeText(manualAdd.this, item.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(manualAdd.this, "Check Input Values", Toast.LENGTH_SHORT).show();
                    item = new Item("error", 0, 0, -1);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(manualAdd.this);

                boolean insert = dataBaseHelper.insertData(item);
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
}