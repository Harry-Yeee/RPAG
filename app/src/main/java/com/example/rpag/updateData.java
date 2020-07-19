package com.example.rpag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class updateData extends AppCompatActivity implements Serializable {
    ListView updateViewList;
    DataBaseHelper dataBaseHelper;
    String month = "month";
    String category = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        updateViewList = (ListView)findViewById(R.id.updateViewList);
        dataBaseHelper = new DataBaseHelper(updateData.this);

        if(getIntent().hasExtra("month") && getIntent().hasExtra("category")){
            category = getIntent().getStringExtra("category");
            month = getIntent().getStringExtra("month");
            showList(dataBaseHelper);
        }

        updateViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item clickedItem = (Item)adapterView.getItemAtPosition(position);
                Intent updateItemIntent = new Intent(getApplicationContext(), manualAdd.class);
                updateItemIntent.putExtra("update", "update");
                updateItemIntent.putExtra("idUpdate", clickedItem.getItemId());
                updateItemIntent.putExtra("nameUpdate", clickedItem.getItemName());
                updateItemIntent.putExtra("priceUpdate", clickedItem.getItemPrice());
                updateItemIntent.putExtra("dateUpdate", clickedItem.getItemDate());
                updateItemIntent.putExtra("categoryUpdate", clickedItem.getItemCategory());
                startActivity(updateItemIntent);

            }
        });
    }

    public void showList(DataBaseHelper dataBaseHelper){
        List<Item> itemList = dataBaseHelper.viewData();
        List<Item> list = new ArrayList<>();
        for(Item items: itemList){
            if(items.getItemCategory().equals(category) && items.getItemDate().equals(month)) {
                list.add(items);
            }
        }

        ArrayAdapter itemArrayAdapter = new ArrayAdapter<Item>(updateData.this, android.R.layout.simple_expandable_list_item_1, list);

        updateViewList.setAdapter(itemArrayAdapter);

    }
}