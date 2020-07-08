package com.example.rpag;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class viewBudget extends AppCompatActivity {
    private static final String TAG = "viewBudget";
    ListView viewBudgetList;
    TextView totalBudgetText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(viewBudget.this);
        List<Category> categoryList = dataBaseHelper.viewCategoryData(" ");
        List<Category> totalBudget = dataBaseHelper.viewCategoryData("Total Budget");

        viewBudgetList = (ListView)findViewById(R.id.viewBudgetList);
        totalBudgetText = (TextView)findViewById(R.id.totalBudgetText);

        Category totalBudgetAmount = totalBudget.get(0);
        if(totalBudgetAmount.getCategoryName().equals("Total Budget")) {
            totalBudgetText.setText(totalBudgetAmount.toString());
        }else{
            totalBudgetText.setText("Total Budget Not Set");
        }

        //System.out.println(totalBudgetText.getText().toString());
        //Log.d(TAG, "onCreate: " + totalBudgetText.getText().toString());


        ArrayAdapter itemArrayAdapter = new ArrayAdapter<Category>(viewBudget.this,android.R.layout.simple_expandable_list_item_1, categoryList);
        viewBudgetList.setAdapter(itemArrayAdapter);

    }
}