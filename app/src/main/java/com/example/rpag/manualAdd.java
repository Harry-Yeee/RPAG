package com.example.rpag;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class manualAdd extends AppCompatActivity {
    EditText itemNameText, itemPriceText, itemDateText;
    Button addDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add);

        itemNameText = (EditText)findViewById(R.id.itemNameText);
        itemPriceText = (EditText)findViewById(R.id.itemPriceText);
        itemDateText = (EditText)findViewById(R.id.itemDateText);
        addDataBtn = (Button)findViewById(R.id.addDataBtn);
        addData();
    }

    public void addData(){
        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item;
                try {
                    item = new Item(itemNameText.getText().toString(), Double.parseDouble(itemPriceText.getText().toString()),
                                   Double.parseDouble(itemDateText.getText().toString()), -1);
                    Toast.makeText(manualAdd.this, item.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(manualAdd.this, "Check Input Values", Toast.LENGTH_SHORT).show();
                    item = new Item("error", 0, 0, -1);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(manualAdd.this);

                boolean success = dataBaseHelper.insertData(item);
                if(success){
                    Toast.makeText(manualAdd.this, "Data Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(manualAdd.this, "Fail to Add Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}