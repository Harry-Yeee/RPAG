package com.example.rpag;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;


public class manageActivity extends AppCompatActivity {
    public static DatabaseHelper myDb;
    EditText editName, editPrice, editDate,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewSingle;
    Spinner spinner1;

    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Back arrow on the top
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Add item to the database
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editPrice = (EditText)findViewById(R.id.editText_price);
        editDate = (EditText)findViewById(R.id.editText_date);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewSingle = (Button)findViewById(R.id.button_viewSingle);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        spinner1 = findViewById(R.id.spinner1);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        viewSingle();
//        Button openBudget = (Button)findViewById(R.id.budgetInterface_btn);
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(manageActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(manageActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                Double.parseDouble(editPrice.getText().toString()), editDate.getText().toString(),
                                String.valueOf(spinner1.getSelectedItem()));
                        if(isUpdate)
                            Toast.makeText(manageActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(manageActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                Double.parseDouble(editPrice.getText().toString()),
                                editDate.getText().toString(),String.valueOf(spinner1.getSelectedItem()) );
                        if(isInserted)
                            Toast.makeText(manageActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(manageActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData(String.valueOf(spinner1.getSelectedItem()));
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Price :"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void viewSingle() {
        btnviewSingle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editTextId.getText().toString().isEmpty())
                        {
                            // show message
                            showMessage("Error","No Input ID");
                            return;
                        }
                        Cursor res = myDb.getDatabyId(editTextId.getText().toString(),String.valueOf(spinner1.getSelectedItem()));
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","ID not found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        res.moveToFirst();
                        buffer.append("Id :" + res.getString(0) + "\n");
                        buffer.append("Name :" + res.getString(1) + "\n");
                        buffer.append("Price :" + res.getString(2) + "\n");
                        buffer.append("Date :" + res.getString(3) + "\n\n");


                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}