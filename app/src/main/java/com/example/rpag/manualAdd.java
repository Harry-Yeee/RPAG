package com.example.rpag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class manualAdd extends AppCompatActivity {
    /*
    DatabaseHelper sqLiteDataBase;
    EditText editItemName, editItemPrice;
    Button addDataBtn;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add);
        /*
        sqLiteDataBase = new DatabaseHelper(this);
        //jayesh

        editItemName = (EditText) findViewById(R.id.itemName);
        editItemPrice = (EditText) findViewById(R.id.itemPrice);
        addDataBtn = (Button)findViewById(R.id.dataAddBtn);
        addData();

         */

    }
/*
    public void addData (){
        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = sqLiteDataBase.insertData(editItemName.getText().toString(), editItemPrice.getText().toString());
                if(result){
                    Toast.makeText(manualAdd.this, "Data Added", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(manualAdd.this, "Unable to Add Data", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

 */
}