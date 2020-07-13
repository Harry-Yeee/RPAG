package com.example.rpag;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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
    TextView spentTotal, spentHousing, spentTrans, spentFood, spentUtil;
    TextView spentHealth, spentIns, spentSil, spentPer, spentEnt, spentMis;
    TextView remTotal, remHousing, remTrans, remFood, remUtil;
    TextView remHealth, remIns, remSil, remPer, remEnt, remMis;

    Button btnUpdateBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_ui);

        btnUpdateBudget = (Button)findViewById(R.id.button_updateBudget);

        editTotal = (EditText)findViewById(R.id.tableTotalbudget);
        editHousing = (EditText)findViewById(R.id.tableHousingBudget);
        editTrans = (EditText)findViewById(R.id.tableTransBudget);
        editFood = (EditText)findViewById(R.id.tableFoodBudget);
        editUtil = (EditText)findViewById(R.id.tableUtilBudget);
        editHealth = (EditText)findViewById(R.id.tableHealthBudget);
        editIns = (EditText)findViewById(R.id.tableInsBudget);
        editSil = (EditText)findViewById(R.id.tableSilBudget);
        editPer = (EditText)findViewById(R.id.tablePerBudget);
        editEnt = (EditText)findViewById(R.id.tableEntBudget);
        editMis = (EditText)findViewById(R.id.tableMisBudget);

        spentTotal = (TextView)findViewById(R.id.tableTotalSpent);
        spentHousing = (TextView)findViewById(R.id.tableHousingSpent);
        spentTrans = (TextView)findViewById(R.id.tableTransSpent);
        spentFood = (TextView)findViewById(R.id.tableFoodSpent);
        spentUtil = (TextView)findViewById(R.id.tableUtilSpent);
        spentHealth = (TextView)findViewById(R.id.tableHealthSpent);
        spentIns = (TextView)findViewById(R.id.tableInsSpent);
        spentSil = (TextView)findViewById(R.id.tableSilSpent);
        spentPer = (TextView)findViewById(R.id.tablePerSpent);
        spentEnt = (TextView)findViewById(R.id.tableEntSpent);
        spentMis = (TextView)findViewById(R.id.tableMisSpent);

        remTotal = (TextView)findViewById(R.id.tableTotalRemain);
        remHousing = (TextView)findViewById(R.id.tableHousingRemain);
        remTrans = (TextView)findViewById(R.id.tableTransRemain);
        remFood = (TextView)findViewById(R.id.tableFoodRemain);
        remUtil = (TextView)findViewById(R.id.tableUtilRemain);
        remHealth = (TextView)findViewById(R.id.tableHealthRemain);
        remIns = (TextView)findViewById(R.id.tableInsRemain);
        remSil = (TextView)findViewById(R.id.tableSilRemain);
        remPer = (TextView)findViewById(R.id.tablePerRemain);
        remEnt = (TextView)findViewById(R.id.tableEntRemain);
        remMis = (TextView)findViewById(R.id.tableMisRemain);

        editTotal.setText("1000.00", TextView.BufferType.EDITABLE);
        UpdateData();
        loadSpending();
    }

    public void UpdateData() {
        btnUpdateBudget.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateBudget(inputReader(), String.valueOf(2));
                        if(isUpdate == true)
                            Toast.makeText(budgetUI.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(budgetUI.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
        loadSpending();
    }

    public ArrayList<Double> inputReader(){
        ArrayList<Double> list = new ArrayList<>();
        list.add(Double.parseDouble( editTotal.getText().toString() ));
        list.add(Double.parseDouble( editHousing.getText().toString() ));
        list.add(Double.parseDouble( editTrans.getText().toString() ));
        list.add(Double.parseDouble( editFood.getText().toString() ));
        list.add(Double.parseDouble( editUtil.getText().toString() ));
        list.add(Double.parseDouble( editHealth.getText().toString() ));
        list.add(Double.parseDouble( editIns.getText().toString() ));
        list.add(Double.parseDouble( editSil.getText().toString() ));
        list.add(Double.parseDouble( editPer.getText().toString() ));
        list.add(Double.parseDouble( editEnt.getText().toString() ));
        list.add(Double.parseDouble( editMis.getText().toString() ));

        return list;
    }

    public void loadSpending(){

        // read the database and display the spending of each category
        // if return cursor is empty, create the row and set spending to 0
        Cursor spending = myDb.getBudgetData();
        if(spending.getCount() == 0) {
            myDb.initializeBudget();
            Toast.makeText(budgetUI.this,"Budget Initialized",Toast.LENGTH_LONG).show();
            // re-initialize spending after intialize budget, so that it's not reading empty string later
            spending = myDb.getBudgetData();
        }
        //read the 1st row
        spending.moveToFirst();
        spentTotal.setText(Double.toString(spending.getDouble(1)));
        spentHousing.setText(Double.toString(spending.getDouble(2)));
        spentTrans.setText(Double.toString(spending.getDouble(3)));
        spentFood.setText(Double.toString(spending.getDouble(4)));
        spentUtil.setText(Double.toString(spending.getDouble(5)));
        spentHealth.setText(Double.toString(spending.getDouble(6)));
        spentIns.setText(Double.toString(spending.getDouble(7)));
        spentSil.setText(Double.toString(spending.getDouble(8)));
        spentPer.setText(Double.toString(spending.getDouble(9)));
        spentEnt.setText(Double.toString(spending.getDouble(10)));
        spentMis.setText(Double.toString(spending.getDouble(11)));

        // read the 2nd row
        spending.moveToNext();
        editTotal.setText(Double.toString(spending.getDouble(1)), TextView.BufferType.EDITABLE);
        editHousing.setText(Double.toString(spending.getDouble(2)), TextView.BufferType.EDITABLE);
        editTrans.setText(Double.toString(spending.getDouble(3)), TextView.BufferType.EDITABLE);
        editFood.setText(Double.toString(spending.getDouble(4)), TextView.BufferType.EDITABLE);
        editUtil.setText(Double.toString(spending.getDouble(5)), TextView.BufferType.EDITABLE);
        editHealth.setText(Double.toString(spending.getDouble(6)), TextView.BufferType.EDITABLE);
        editIns.setText(Double.toString(spending.getDouble(7)), TextView.BufferType.EDITABLE);
        editSil.setText(Double.toString(spending.getDouble(8)), TextView.BufferType.EDITABLE);
        editPer.setText(Double.toString(spending.getDouble(9)), TextView.BufferType.EDITABLE);
        editEnt.setText(Double.toString(spending.getDouble(10)), TextView.BufferType.EDITABLE);
        editMis.setText(Double.toString(spending.getDouble(11)), TextView.BufferType.EDITABLE);

        calculateRemain();
    }

    public void calculateRemain(){
        Double[] rem = new Double[11];

        // calculate the remaining
        if(Double.parseDouble( editTotal.getText().toString() ) != 0) {

            rem[0] = Double.parseDouble(editTotal.getText().toString()) - Double.parseDouble(spentTotal.getText().toString());
            rem[1] = Double.parseDouble(editHousing.getText().toString()) - Double.parseDouble(spentHousing.getText().toString());
            rem[2] = Double.parseDouble(editTrans.getText().toString()) - Double.parseDouble(spentTrans.getText().toString());
            rem[3] = Double.parseDouble(editFood.getText().toString()) - Double.parseDouble(spentFood.getText().toString());
            rem[4] = Double.parseDouble(editUtil.getText().toString()) - Double.parseDouble(spentUtil.getText().toString());
            rem[5] = Double.parseDouble(editHealth.getText().toString()) - Double.parseDouble(spentHealth.getText().toString());
            rem[6] = Double.parseDouble(editIns.getText().toString()) - Double.parseDouble(spentIns.getText().toString());
            rem[7] = Double.parseDouble(editSil.getText().toString()) - Double.parseDouble(spentSil.getText().toString());
            rem[8] = Double.parseDouble(editPer.getText().toString()) - Double.parseDouble(spentPer.getText().toString());
            rem[9] = Double.parseDouble(editEnt.getText().toString()) - Double.parseDouble(spentEnt.getText().toString());
            rem[10] = Double.parseDouble(editMis.getText().toString()) - Double.parseDouble(spentMis.getText().toString());

            // turn negative numbers into 0's
            int loop = 0;
            while (loop != 11) {
                if (rem[loop] < 0) {
                    rem[loop] = Double.valueOf(0);
                }
                loop++;
            }

            // set text for remain
            remTotal.setText(Double.toString(rem[0]));
            remHousing.setText(Double.toString(rem[1]));
            remTrans.setText(Double.toString(rem[2]));
            remFood.setText(Double.toString(rem[3]));
            remUtil.setText(Double.toString(rem[4]));
            remHealth.setText(Double.toString(rem[5]));
            remIns.setText(Double.toString(rem[6]));
            remSil.setText(Double.toString(rem[7]));
            remPer.setText(Double.toString(rem[8]));
            remEnt.setText(Double.toString(rem[9]));
            remMis.setText(Double.toString(rem[10]));
        }
    }

}

