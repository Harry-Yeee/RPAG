package com.example.rpag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.rpag.MainActivity.myDb;


public class budgetUI extends AppCompatActivity {

    EditText editTotal, editHousing, editTrans, editFood, editUtil;
    EditText editHealth, editIns, editSil, editPer, editEnt, editMis;
    Button btnUpdateBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_ui);

        editTotal = (EditText)findViewById(R.id.tableTotalbudget);
        editHousing = (EditText)findViewById(R.id.tableHousingBudget);
        editTrans = (EditText)findViewById(R.id.tableTransBudget);
        editUtil = (EditText)findViewById(R.id.tableUtilBudget);
        editFood = (EditText)findViewById(R.id.tableFoodBudget);
        editHealth = (EditText)findViewById(R.id.tableHealthBudget);
        editIns = (EditText)findViewById(R.id.tableInsBudget);
        editSil = (EditText)findViewById(R.id.tableSilBudget);
        editPer = (EditText)findViewById(R.id.tablePerBudget);
        editEnt = (EditText)findViewById(R.id.tableEntBudget);
        editMis = (EditText)findViewById(R.id.tableMisBudget);

        editTotal.setText("1000.00", TextView.BufferType.EDITABLE);


    }

    /*public void UpdateData() {
        btnUpdateBudget.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(
                                editTotal.getText().toString(),
                                editHousing.getText().toString(),
                                Double.parseDouble(editPrice.getText().toString()), editDate.getText().toString(),
                                String.valueOf(spinner1.getSelectedItem()));
                        if(isUpdate == true)
                            Toast.makeText(budgetUI.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(budgetUI.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/

    public ArrayList<Double> inputReader(){
        ArrayList<Double> list = new ArrayList<>();
        list.add(Double.parseDouble( editTotal.getText().toString() ));
        list.add(Double.parseDouble( editHousing.getText().toString() ));
        list.add(Double.parseDouble( editTrans.getText().toString() ));
        list.add(Double.parseDouble( editUtil.getText().toString() ));
        list.add(Double.parseDouble( editFood.getText().toString() ));
        list.add(Double.parseDouble( editHealth.getText().toString() ));
        list.add(Double.parseDouble( editIns.getText().toString() ));
        list.add(Double.parseDouble( editSil.getText().toString() ));
        list.add(Double.parseDouble( editPer.getText().toString() ));
        list.add(Double.parseDouble( editEnt.getText().toString() ));
        list.add(Double.parseDouble( editMis.getText().toString() ));

        return list;
    }


}