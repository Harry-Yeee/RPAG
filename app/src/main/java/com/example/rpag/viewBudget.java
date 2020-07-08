package com.example.rpag;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class viewBudget extends AppCompatActivity {
    private static final String TAG = "viewBudget";
    ListView viewBudgetList;
    TextView totalBudgetText;
    String month = "Selected Month";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        if(getIntent().hasExtra("month")){
            month = getIntent().getStringExtra("month");
        }


        DataBaseHelper dataBaseHelper = new DataBaseHelper(viewBudget.this);
        List<Category> categoryList = dataBaseHelper.viewCategoryData(" ", month);
        List<Category> totalBudget = dataBaseHelper.viewCategoryData("Total Budget", month);

        try {

            viewBudgetList = (ListView) findViewById(R.id.viewBudgetList);
            totalBudgetText = (TextView) findViewById(R.id.totalBudgetText);

            Category totalBudgetAmount = totalBudget.get(0);
            if (totalBudgetAmount.getCategoryName().equals("Total Budget") && totalBudgetAmount.getCategoryMonth().equals(month)) {
                totalBudgetText.setText(totalBudgetAmount.toString());
            } else {
                totalBudgetText.setText("Total Budget Not Set");
            }

            ArrayAdapter itemArrayAdapter = new ArrayAdapter<Category>(viewBudget.this,android.R.layout.simple_expandable_list_item_1, categoryList);
            viewBudgetList.setAdapter(itemArrayAdapter);
        } catch (Exception e) {
            Toast.makeText(viewBudget.this, "NO DATA FOUND", Toast.LENGTH_LONG).show();
        }

        //System.out.println(totalBudgetText.getText().toString());
        //Log.d(TAG, "onCreate: " + totalBudgetText.getText().toString());



    }
}