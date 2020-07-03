package com.example.rpag;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class viewBudget extends AppCompatActivity {
    ListView viewBudgetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);
        viewBudgetList = (ListView)findViewById(R.id.viewBudgetList);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(viewBudget.this);
        List<Category> categoryList = dataBaseHelper.viewCategoryData();

        ArrayAdapter itemArrayAdapter = new ArrayAdapter<Category>(viewBudget.this,android.R.layout.simple_expandable_list_item_1, categoryList);
        viewBudgetList.setAdapter(itemArrayAdapter);

    }
}