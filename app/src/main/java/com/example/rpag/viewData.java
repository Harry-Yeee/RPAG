package com.example.rpag;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class viewData extends AppCompatActivity {
    ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        itemListView = (ListView)findViewById(R.id.itemListView);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(viewData.this);
        List<Item> itemList = dataBaseHelper.viewData();

        ArrayAdapter itemArrayAdapter = new ArrayAdapter<Item>(viewData.this,android.R.layout.simple_expandable_list_item_1, itemList);
        itemListView.setAdapter(itemArrayAdapter);


       // Toast.makeText(viewData.this, itemList.toString(), Toast.LENGTH_LONG).show();
    }
}