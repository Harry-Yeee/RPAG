package com.example.rpag;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;




public class selectActivity extends AppCompatActivity implements NewdbDialog.newdbDialogListener{

    private Spinner spinner;
    private Button dialogButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        // Back arrow on the top
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Edit Text display in the spinner
        final String path = Environment.getDataDirectory().toString()+"/data/com.example.rpag/databases/";
        File f = new File(path);
        final File file[] = f.listFiles();
        spinner = (Spinner)findViewById(R.id.databaseSpinner);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        for (int i = 0; i < file.length; i++) {
            String[] pathName = file[i].toString().split("/");
            String FileName = pathName[pathName.length-1];
            String[] FileExtension = FileName.split("[.]");
            String Extension = FileExtension[FileExtension.length-1];
            if(Extension.equalsIgnoreCase("db")) {
                spinnerAdapter.add(FileName);
            }
        }
        spinnerAdapter.notifyDataSetChanged();

        // Configure select button
        configureSelectButton();

        // Configure Dialog Button
        dialogButton = (Button) findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        // Configure Delete Button
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < file.length; i++) {
                    String[] pathName = file[i].toString().split("/");
                    String FileName = pathName[pathName.length-1];
                    if(String.valueOf(spinner.getSelectedItem().toString()).equals(FileName)) {
                        boolean deleted = file[i].delete();
                        if (deleted) {
                            showMessage("Message","Database: "+FileName+" deleted.");
                            spinnerAdapter.remove(spinner.getSelectedItem().toString());
                        }
                        else {
                            showMessage("Error","Database: "+FileName+" not deleted.");
                        }
                        break;
                    }
                }
            }
        });
    }

    private void configureSelectButton()
    {
        Button manageButton = (Button) findViewById(R.id.selectButton);
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                MainActivity.selectedDatabase = String.valueOf(spinner.getSelectedItem());
                startActivity(new Intent(selectActivity.this, manageActivity.class));
            }
        });
    }

    public void openDialog() {
        NewdbDialog dbDialog = new NewdbDialog();
        dbDialog.show(getSupportFragmentManager(),"New Database Dialog");
    }

    @Override
    public void applyText(String dbname) {
        MainActivity.selectedDatabase = dbname+".db";
        startActivity(new Intent(selectActivity.this, manageActivity.class));
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}